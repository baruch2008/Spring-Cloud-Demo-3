package com.huawei.tdt.common.config.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * QuartzConfig
 * 
 * @author lWX537094
 */
@Configuration
public class QuartzConfig
{
    @Autowired
    private SpringJobFactory springJobFactory;

    /**
     * 配置SchedulerFactoryBean
     * @return SchedulerFactoryBean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean()
    {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(springJobFactory);
        return schedulerFactoryBean;
    }

    /**
     * 配置Scheduler
     * @return Scheduler
     */
    @Bean
    @Primary
    public Scheduler scheduler()
    {
        return schedulerFactoryBean().getScheduler();
    }
}
