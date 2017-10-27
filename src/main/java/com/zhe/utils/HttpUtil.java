package com.zhe.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * user:weicj
 * company:TMG
 * detetime:2014/12/8 11:02
 * Description:HTTP工具处理类
 */
public class HttpUtil {
    protected final static CloseableHttpClient httpClient ;
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(15000).build();
        HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    /**
     *  发送POST请求
     * @param url:url地址
     * @param params:参数
     * @return:请求的返回结果
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, params, "UTF-8");
    }


    /**
     *  发送POST请求
     * @param url:url地址
     * @param params:参数
     * @param charset:编码
     * @return:请求的返回结果
     */
    public static String post(String url, Map<String, String> params, String charset) {
        if(url == null || url.trim().length() == 0) {
            return null;
        }

        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,charset));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = "";
            if (entity != null){
                result = EntityUtils.toString(entity);
            }
            EntityUtils.consume(entity);
            try {
                response.close();
            }catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return result;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送GET请求
     * @param url:URL地址
     * @return: 请求的返回结果
     */
    public static String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            } else {
                HttpEntity entity = response.getEntity();
                String result = "";
                if (entity != null){
                    result = EntityUtils.toString(entity);
                }
                EntityUtils.consume(entity);
                try{
                    response.close();
                }catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException("GET请求到" +url+"失败: " + e.getMessage(), e);
        }
    }
}
