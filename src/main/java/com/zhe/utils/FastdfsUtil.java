package com.zhe.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * ClassName: FastdfsUtil
 * Function:
 * Reason: TODO ADD REASON(可选).
 * date: 2017年03月02日 13:48
 *
 * @author caijun.wei
 * @since JDK 1.7
 */
public class FastdfsUtil {

    private  static TrackerClient trackerClient = null;

    private static TrackerServer trackerServer = null;

    private static StorageServer storageServer = null;

    private static StorageClient1 storageClient = null;

    private static String conf = null;
    /**
     * 初始化线程池
     * @param conf:服务器地址
     */
    public static void setConf(String conf) {
        try {
            ClientGlobal.init(conf);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = null;
            storageClient = new StorageClient1(trackerServer, storageServer);
            ProtoCommon.activeTest(trackerServer.getSocket());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getConf() {
        return conf;
    }

    public static String upload(File file) {
        if(file == null  ) {
            throw new RuntimeException("fastdfs文件上传失败:文件不存在");
        }

        if(!file.exists()) {
            throw new RuntimeException("fastdfs文件上传失败:文件不存在");
        }

        try {
            NameValuePair nameValuePair[] = new NameValuePair[1];
            nameValuePair[0] = new NameValuePair("name", file.getName());
            String result =storageClient.upload_file1(file.getPath(),getExtName(file.getName()) , nameValuePair);
            return result;
        }catch (Exception e) {
            throw new RuntimeException("上次文件失败：" + e.getMessage(), e);
        }
    }

    /**
     * 保存文件到fastdfs
     * @param bytes:文件内容
     * @param fileName:文件名称
     * @param fileSize:文件大小
     * @return
     */
    public static String upload(byte[] bytes,String fileName, long fileSize) {
        try {
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", fileName);
            metaList[1] = new NameValuePair("fileExtName", getExtName(fileName));
            metaList[2] = new NameValuePair("fileLength", String.valueOf(fileSize));
            return "/"+storageClient.upload_file1(bytes, getExtName(fileName), metaList);
        }catch (Exception e) {
            throw new RuntimeException("上传文件失败", e);
        }
    }

    private static String getExtName(String fileName) {
        if(StringUtil.isTrimEmpty(fileName)) {
            return "null";
        }

        if(fileName.contains(".")) {
            try {
                return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            } catch (Exception e){
                return "null";
            }
        } else {
            return "null";
        }
    }
}
