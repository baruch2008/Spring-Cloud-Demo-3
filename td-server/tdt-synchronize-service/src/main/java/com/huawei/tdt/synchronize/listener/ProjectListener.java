package com.huawei.tdt.synchronize.listener;

import java.util.concurrent.atomic.AtomicBoolean;

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
    private static AtomicBoolean executedFlag = new AtomicBoolean(false);

    /**
     * 用于启动后台数据同步服务线程
     * 
     * @param contextRefreshedEvent 上下文刷新事件对象
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        if (executedFlag.getAndSet(true))
        {
            return;
        }

        Logger logger = LoggerFactory.getLogger(this.getClass());
        try
        {
            ProjectSynchronizeManager projectSyncMgr = new ProjectSynchronizeManager();
            
            projectSyncMgr.run();

            logger.info("Starting project synchronize manager successed.");
        }
        catch (Exception e)
        {
            logger.error("Starting project synchronize manager failed.", e);
        }
    }
}
