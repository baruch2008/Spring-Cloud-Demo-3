package com.huawei.tdt.common.entity;

import java.io.Serializable;

public class SystemTask implements Serializable {

    private static final long serialVersionUID = -4114641303155008970L;
    
    private String id;
    
    private String name;
    
    /**
     * 名称参数
     */
    private String params;
    
    private double progress;
    
    /**
     * 0:Failed,1:Successed,2:In progress
     */
    private int status;
    
    /**
     * 备注
     * 
     * 用于记录任务结果
     */
    private String remark;
    
    /**
     * 备注参数
     */
    private String remarkParams;
    
    private String projectId;

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

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkParams() {
        return remarkParams;
    }

    public void setRemarkParams(String remarkParams) {
        this.remarkParams = remarkParams;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
