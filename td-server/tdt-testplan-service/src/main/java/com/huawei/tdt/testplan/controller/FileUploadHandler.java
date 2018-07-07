 package com.huawei.tdt.testplan.controller;

import java.io.IOException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.tdt.common.util.SpringUtil;
import com.huawei.tdt.testplan.config.FastDFSConfig;

/**
 * FileUploadUtil
 * 
 * @author lWX537094
 */
public class FileUploadHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadHandler.class);
    
    private static FileUploadHandler handler = null;
    
    /**
     * 私有构造函数
     */
    private FileUploadHandler()
    {
        FastDFSConfig fastDFSCfg = SpringUtil.getBean(FastDFSConfig.class);
        try
        {
            ClientGlobal.initByTrackers(fastDFSCfg.getTrackerHost() + ":" + fastDFSCfg.getTrackerPort());
        }
        catch (Exception e)
        {
            LOGGER.error("Init global tracker client failed.", e);
        }
    }
    
    /**
     * 获取FileUploadHandler
     * 
     * @return FileUploadHandler
     */
    public static synchronized FileUploadHandler getFileUploadHandler()
    {
        if (null == handler)
        {
            handler = new FileUploadHandler();
        }

        return handler;
    }
    
    /**
     * 上传文件
     * 
     * @param bytes 文件内容
     * @param fileName 文件名称
     * @param fileLength 文件长度
     * @return 文件上传结果
     * @throws IOException 异常
     */
    public String[] uploadFile(byte[] bytes, String fileName, long fileLength) throws IOException
    {
        String fileExtName = "";
        if (fileName.contains("."))
        {
            fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        else
        {
            LOGGER.error("Fail to upload file, because the format of filename is illegal.");
            return null;
        }

        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);

        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", fileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

        String[] results = null;
        try
        {
            results = client.upload_file(bytes, fileExtName, metaList);
        }
        catch (Exception e)
        {
            LOGGER.error("Upload file \"" + fileName + "\"fails", e);
        }
        finally
        {
            if (null != trackerServer)
            {
                trackerServer.close();
            }
        }
        
        return results;
    }
}
