package com.huawei.allinone.common.dao;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huawei.allinone.common.config.MongoDBConfig;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;

public class BaseMongoDBDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMongoDBDao.class);

    @Autowired
    private MongoDBConfig dbConfig;
    
    private MongoClient mongoClient = null;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    protected MongoClient getMongoClient() {
        lock.writeLock().lock();
        try {
            if (null == mongoClient) {
                MongoClientOptions.Builder build = new MongoClientOptions.Builder();
                build.connectionsPerHost(50);
                build.threadsAllowedToBlockForConnectionMultiplier(50);

                build.maxWaitTime(1000 * 60 * 2);
                build.connectTimeout(1000 * 60 * 1);

                MongoClientOptions myOptions = build.build();
                try {
                    mongoClient = new MongoClient(dbConfig.getHost(), myOptions);
                } catch (MongoException e) {
                    LOGGER.error("Getting mongo client failed.", e);
                }
            }
        } finally {
            lock.writeLock().unlock();
        }

        return mongoClient;
    }
}
