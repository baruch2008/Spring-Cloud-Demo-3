package com.huawei.tdt.common.quartz;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.tdt.common.constants.Constants;
import com.huawei.tdt.common.constants.QuartzTaskType;
import com.huawei.tdt.common.util.SpringUtil;

/**
 * Quartz Task Manager
 * 
 * @author lWX537094
 */
public final class QuartzTaskManager
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTaskManager.class);

    private static QuartzTaskManager taskMgr;

    private Scheduler scheduler;

    private QuartzTaskManager()
    {

    }

    /**
     * Get QuartzTaskManager instance
     * 
     * @return QuartzTaskManager
     */
    public static synchronized QuartzTaskManager getTaskManager()
    {
        if (null == taskMgr)
        {
            taskMgr = new QuartzTaskManager();
            taskMgr.setScheduler(SpringUtil.getBean(Scheduler.class));
        }

        return taskMgr;
    }

    public void setScheduler(Scheduler scheduler)
    {
        this.scheduler = scheduler;
    }

    /**
     * 添加任务
     * 
     * @param task 任务
     * @throws Exception 异常
     */
    public void addQuartzTask(QuartzTask task) throws Exception
    {
        if (null == task)
        {
            return;
        }

        if (QuartzTaskType.SIMPLE.equals(task.getTaskType()))
        {
            addSimpleQuartzTask(task);
        }
        else if (QuartzTaskType.CRON.equals(task.getTaskType()))
        {
            addCronQuartzTask(task);
        }
        else
        {
            LOGGER.error("The task type is invalid." + task.getTaskType());
        }
    }

    /**
     * 移除任务
     * 
     * @param identity 任务标示
     */
    public void removeQuartzTask(String identity)
    {
        try
        {
            TriggerKey triggerKey = TriggerKey.triggerKey(identity, Constants.TRIGGER_GROUP);

            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(identity, Constants.JOB_GROUP));
        }
        catch (Exception e)
        {
            LOGGER.error("Remove task failed. Identity:" + identity);
        }
    }

    /**
     * 修改任务调度
     * 
     * @param identity 任务标示
     * @param schedule 新的任务调度
     */
    public void modifyQuartzTask(String identity, String schedule)
    {
        try
        {
            TriggerKey triggerKey = TriggerKey.triggerKey(identity, Constants.TRIGGER_GROUP);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null)
            {
                return;
            }

            String oldSchedule = trigger.getCronExpression();
            if (!oldSchedule.equalsIgnoreCase(schedule))
            {
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(identity, Constants.JOB_GROUP);
                triggerBuilder.startNow();
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(schedule));
                trigger = (CronTrigger) triggerBuilder.build();
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Modify task failed. Identity:" + identity + ", Schedule:" + schedule);
        }
    }

    /**
     * 添加单次运行的任务
     * 
     * @param task QuartzTask
     * @throws Exception 异常
     */
    private void addSimpleQuartzTask(QuartzTask task) throws Exception
    {
        Date triggerDate = null;
        if (null != task.getSchedule())
        {
            triggerDate = DateUtils.parseDate(task.getSchedule(), "yyyy-MM-dd HH:mm:ss");
        }
        else
        {
            triggerDate = new Date();
        }

        removeQuartzTask(task.getIdentity());
        
        JobDetail job = JobBuilder.newJob(task.getClass()).withIdentity(task.getIdentity(), Constants.JOB_GROUP)
                .usingJobData(buildJobDataMap(task)).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getIdentity(), Constants.TRIGGER_GROUP)
                .startAt(triggerDate).build();
        scheduler.scheduleJob(job, trigger);
    }

    /**
     * 添加周期性任务
     * 
     * @param task QuartzTask
     * @throws Exception 异常
     */
    private void addCronQuartzTask(QuartzTask task) throws Exception
    {
        if (null == task.getSchedule())
        {
            LOGGER.error("The task schedule is null.");
        }

        removeQuartzTask(task.getIdentity());
        
        JobDetail job = JobBuilder.newJob(task.getClass()).withIdentity(task.getIdentity(), Constants.JOB_GROUP)
                .usingJobData(buildJobDataMap(task)).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getIdentity(), Constants.TRIGGER_GROUP)
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getSchedule())).build();
        scheduler.scheduleJob(job, trigger);
    }

    private JobDataMap buildJobDataMap(QuartzTask task)
    {
        JobDataMap dataMap = new JobDataMap();
        if (null != task.getData() && 0 != task.getData().size())
        {
            dataMap.putAll(task.getData());
        }

        return dataMap;
    }
}
