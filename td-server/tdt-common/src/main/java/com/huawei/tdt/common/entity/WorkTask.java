package com.huawei.tdt.common.entity;

import java.io.Serializable;

public class WorkTask implements Serializable {

    private static final long serialVersionUID = -2039523494842308234L;
    
    private String id;
    
    private String name;
    
    /**
     * 0: 挑选用例，1：分解用例
     */
    private int type;
    
    private String projectId;
    
    /**
     * 任务执行者ID
     */
    private String executor;
    
    private String testPlanId;
    
    /**
     * 0:未结束，1:关闭(已完成)
     */
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
