package com.huawei.tdt.synchronize.listener;

import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.huawei.tdt.synchronize.thread.ProjectSynchronizeManager;

/**
 * 在容器初始化完成启动后台数据定时同步任务
 * 
 * @author lWX537094
 */
@Component
public class ProjectListener implements ApplicationListener<ContextRefreshedEvent>
{
    /**
     * 用于启动后台数据同步服务线程
     * 
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        try
        {
            Executors.newSingleThreadExecutor().submit(new ProjectSynchronizeManager());
            logger.info("Starting project synchronize manager successed.");
        }
        catch (Exception e)
        {
            logger.error("Starting project synchronize manager failed.", e);
        }
    }
