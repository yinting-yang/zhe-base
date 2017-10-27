package com.zhe.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;
/**
 * @Author:zhe.yang
 * @Description:redis工具包
 * @DATE:Created in 11:06 2017/10/27 0027
 */

public class RedisUtil {
    private static JedisPool jedisPool = null;

    private static String addr;

    private static int port = 6379;

    /**
     * 初始化redis连接
     *
     * @param addr:redis服务器地址
     * @param port:端口
     * @param minIdle:最小空闲连接数
     * @param maxTotal：最大连接数
     * @param maxWaitMillis:获取连接最大等待时间(毫秒)
     * @param minEvictableIdleTimeMillis:释放空闲连接时间(连接若在此配置时间内没有操作则释放连接，毫秒)
     */
    public static void init(String addr, int port, int minIdle, int maxTotal, int maxWaitMillis, int minEvictableIdleTimeMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        config.setMinIdle(minIdle);

        jedisPool = new JedisPool(config, addr, port);
    }

    public static void init(String add, int p) {
        init(add, p, 3, 8, 1500, 900000);
    }

    public static void init() {
        init(addr, port);
    }

    public static String getAddr() {
        return addr;
    }

    public static void setAddr(String addr) {
        RedisUtil.addr = addr;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        RedisUtil.port = port;
    }

    /**
     * 获取redis连接
     *
     * @return:redis连接
     */
    private synchronized static Jedis getJedis() {
        if (jedisPool == null) {
            throw new NullPointerException("redis未初始化连接池");
        }
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            throw new RuntimeException("获取redis连接失败", e);
        }
    }

    /**
     * 释放连接
     *
     * @param jedis
     */
    private static void returnJedis(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * 写入redis
     *
     * @param key:key
     * @param obj:写入对象
     * @param time:过期时间
     */
    public static void set(String key, Serializable obj, Integer time) {
        if (obj != null) {
            set(key, SerializationUtil.serialization(obj), time);
        } else {
            del(key);
        }
    }

    /**
     * 写入redis
     *
     * @param key：key
     * @param value：写入值
     * @param time:过期时间(秒) NULL表示不过期
     */
    private static void set(String key, String value, Integer time) {
        if (key == null || key.trim().length() == 0) {
            throw new RuntimeException("写入redis key不能为空");
        }
        if (value == null) {
            del(key);
        }
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
            if (time != null) jedis.expire(key, time);
        } catch (Exception e) {
            throw new RuntimeException("redis写入失败", e);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 读取redis
     *
     * @param key:key
     * @return:读取结果
     */
    public static Object get(String key) {
        if (key == null || key.trim().length() == 0) {
            throw new RuntimeException("读取redis key不能为空");
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            String value = jedis.get(key);
            if (StringUtil.isTrimEmpty(value)) {
                return null;
            } else {
                return SerializationUtil.deserialize(value);
            }
        } catch (Exception e) {
            throw new RuntimeException("读取redis失败", e);
        } finally {
            if (jedis != null) returnJedis(jedis);
        }
    }

    public static void del(String key) {
        if (key == null || key.trim().length() == 0) {
            throw new RuntimeException("读取redis key不能为空");
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.del(key);
        } catch (Exception e) {
            throw new RuntimeException("删除redis失败", e);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param time:过期时间 (秒)
     */
    public static void expire(String key, int time) {
        if (key == null || key.trim().length() == 0) {
            throw new RuntimeException("读取redis key不能为空");
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key, time);
        } catch (Exception e) {
            throw new RuntimeException("删除redis失败", e);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public static void reLock(String key) {
        del(key);
    }

    /**
     * 获取锁
     *
     * @param key：获取锁的key
     * @param autoReleaseTime:自动释放时间(秒)
     * @param timeout:在timeout时间内未获取到锁则跑出运行时异常(秒)
     */
    public static void lock(String key, int autoReleaseTime, int timeout) {
        long currentTime = System.currentTimeMillis();
        try {
            while (true) {
                if (setnx(key, "1")) {
                    expire(key, timeout);
                    break;
                } else {
                    if (System.currentTimeMillis() > (currentTime + timeout * 1000)) {
                        throw new RuntimeException("获取redis锁失败:获取超时");
                    }
                }
                Thread.sleep(500L);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 设置值到redis(key不存在时才能写入成功)
     *
     * @param key:key的值
     * @param value:写入数据
     * @return: true:写入成功 false:写入失败
     */
    private static boolean setnx(String key, String value) {
        if (StringUtil.isTrimEmpty(key) || StringUtil.isTrimEmpty(value)) {
            throw new RuntimeException("key或value不能为空");
        }
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return (jedis.setnx(key, value) == 1);
        } catch (Exception e) {
            throw new RuntimeException("删除redis失败", e);
        } finally {
            returnJedis(jedis);
        }
    }

    public static void main(String[] args) {
        init("172.16.103.202", 6379);
        set("asd", "asdasa", 88);
        System.out.println(get("asd"));
    }
}
