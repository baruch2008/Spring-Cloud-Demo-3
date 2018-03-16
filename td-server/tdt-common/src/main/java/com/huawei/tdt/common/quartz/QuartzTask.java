package com.huawei.tdt.common.quartz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;

import com.huawei.tdt.common.constants.Constants;
import com.huawei.tdt.common.constants.QuartzTaskType;
import com.huawei.tdt.common.entity.SystemTask;

/**
 * 所有Quartz任务的基类
 * 
 * @author lWX537094
 */
public abstract class QuartzTask implements Job
{
    private String identity;
    
    /**
     * 关联的后台任务
     */
    private SystemTask systemTask;

    /**
     * 任务类型
     */
    private QuartzTaskType taskType;

    private String schedule;

    private Map<String, Object> data = new HashMap<String, Object>(Constants.TEN);
    
    public SystemTask getSystemTask()
    {
        return systemTask;
    }

    protected void setSystemTask(SystemTask systemTask)
    {
        this.systemTask = systemTask;
    }

    public QuartzTaskType getTaskType()
    {
        return taskType;
    }

    public void setTaskType(QuartzTaskType taskType)
    {
        this.taskType = taskType;
    }

    public String getSchedule()
    {
        return schedule;
    }

    public void setSchedule(String schedule)
    {
        this.schedule = schedule;
    }

    public Map<String, Object> getData()
    {
        return data;
    }

    public void setData(Map<String, Object> data)
    {
        this.data.putAll(data);
    }
    
    /**
     * 添加数据
     * 
     * @param key KEY
     * @param value Value
     */
    public void putData(String key, Object value)
    {
        this.data.put(key, value);
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    /**
     * 将后台任务保存至数据库中
     * 
     * 在子类的execute方法中调用
     */
    protected void saveSystemTask()
    {

    }

    /**
     * 更新后台任务数据
     */
    protected void updateSystemTask()
    {

    }
}
