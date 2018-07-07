package com.huawei.tdt.testplan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * FastDFSConfig
 * 
 * @author lWX537094
 */
@Component
public class FastDFSConfig
{
    @Value("${fastdfs.tracker.host}")
    private String trackerHost;

    @Value("${fastdfs.tracker.port}")
    private String trackerPort;

    @Value("${fastdfs.storage.host}")
    private String storageHost;

    @Value("${fastdfs.storage.port}")
    private String storagePort;

    @Value("${nginx.fastdfs.tracker.host}")
    private String nginxTrackerHost;

    @Value("${nginx.fastdfs.tracker.port}")
    private String nginxTrackerPort;

    @Value("${nginx.fastdfs.storage.host}")
    private String nginxStorageHost;

    @Value("${nginx.fastdfs.storage.port}")
    private String nginxStoragePort;

    public String getTrackerHost()
    {
        return trackerHost;
    }

    public String getTrackerPort()
    {
        return trackerPort;
    }

    public String getStorageHost()
    {
        return storageHost;
    }

    public String getStoragePort()
    {
        return storagePort;
    }

    public String getNginxTrackerHost()
    {
        return nginxTrackerHost;
    }

    public String getNginxTrackerPort()
    {
        return nginxTrackerPort;
    }

    public String getNginxStorageHost()
    {
        return nginxStorageHost;
    }

    public String getNginxStoragePort()
    {
        return nginxStoragePort;
    }

    public void setTrackerHost(String trackerHost)
    {
        this.trackerHost = trackerHost;
    }

    public void setTrackerPort(String trackerPort)
    {
        this.trackerPort = trackerPort;
    }

    public void setStorageHost(String storageHost)
    {
        this.storageHost = storageHost;
    }

    public void setStoragePort(String storagePort)
    {
        this.storagePort = storagePort;
    }

    public void setNginxTrackerHost(String nginxTrackerHost)
    {
        this.nginxTrackerHost = nginxTrackerHost;
    }

    public void setNginxTrackerPort(String nginxTrackerPort)
    {
        this.nginxTrackerPort = nginxTrackerPort;
    }

    public void setNginxStorageHost(String nginxStorageHost)
    {
        this.nginxStorageHost = nginxStorageHost;
    }

    public void setNginxStoragePort(String nginxStoragePort)
    {
        this.nginxStoragePort = nginxStoragePort;
    }
}
