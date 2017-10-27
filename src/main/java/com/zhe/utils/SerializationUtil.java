package com.zhe.utils;

import java.io.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
/**
 * ClassName: ${name}
 * Function:
 * Reason: TODO ADD REASON(可选).
 *
 * @author zhe.yang
 * @since JDK 1.8
 */
public class SerializationUtil {

    private static final String TEMP_ENCODING = "ISO-8859-1";
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 将对象序列化为字符串
     * @param obj
     * @return
     */
    public static String serialization(Serializable obj) {
        if(obj == null) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String serStr = byteArrayOutputStream.toString(TEMP_ENCODING);
            return URLEncoder.encode(serStr, DEFAULT_ENCODING);
        }catch (Exception e) {
            throw new RuntimeException("序列化失败:" + e.getMessage(), e);
        }finally {
            close(objectOutputStream);
            close(byteArrayOutputStream);
        }
    }


    /**
     * 将字符串反序列化为对象
     * @param ser
     * @return
     */
    public static Object deserialize(String ser) {
        if(StringUtil.isTrimEmpty(ser)) {
            return null;
        }

        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            String deserStr = URLDecoder.decode(ser, DEFAULT_ENCODING);
            byteArrayInputStream = new ByteArrayInputStream(deserStr.getBytes(TEMP_ENCODING));
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        }catch (Exception e) {
            throw new RuntimeException("反序列化失败:" + e.getMessage(), e);
        }finally {
            close(objectInputStream);
            close(byteArrayInputStream);
        }
    }

    /**
     * 关闭对象
     * @param os
     */
    private static void close(Closeable os) {
        if(os != null) {
            try {
                os.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}