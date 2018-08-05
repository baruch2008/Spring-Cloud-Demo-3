package com.huawei.allinone.common.lock;

import java.util.concurrent.TimeUnit;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedLock implements IDistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedLock.class);

    private static final String ROOT_PATH = "/allinone_lock";

    private String zkAddress = "192.168.154.180:2181";

    private CuratorFramework client;

    private InterProcessMutex lock;

    private String lockPath;

    public DistributedLock(String node) {
        this.lockPath = ROOT_PATH + "/" + node;
        this.init();
    }

    private void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.newClient(this.zkAddress, retryPolicy);
        this.client.start();
        this.lock = new InterProcessMutex(this.client, lockPath);
    }

    @Override
    public void lock() {
        try {
            this.lock.acquire();
        } catch (Exception e) {
            LOGGER.error("Lock failed", e);
        }
    }

    @Override
    public void unLock() {
        try {
            this.lock.release();
        } catch (Exception e) {
            LOGGER.error("UnLock failed", e);
        }
    }

    @Override
    public boolean tryLock() {
        try {
            return this.lock.acquire(1L, TimeUnit.SECONDS);
        } catch (Exception e) {
            LOGGER.error("Try lock failed", e);
            return false;
        }
    }

    @Override
    public boolean tryLock(long waitTime, TimeUnit timeUnit) {
        try {
            return this.lock.acquire(waitTime, timeUnit);
        } catch (Exception e) {
            LOGGER.error("Try lock failed", e);
            return false;
        }
    }

    public static void main(String[] args) {
        IDistributedLock distributedLock = new DistributedLock("TestMgr");
        long st = System.currentTimeMillis();
        if (!distributedLock.tryLock()) {
            return;
        }
        long et = System.currentTimeMillis();
        System.out.println("Time:" + (et - st));

        try {
            System.out.println("Hello");
        } finally {
            distributedLock.unLock();
        }
    }
}
