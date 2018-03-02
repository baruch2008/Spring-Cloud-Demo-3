package com.huawei.tdt.common.entity;

import java.io.Serializable;

/**
 * 阶段，对应B版本、迭代等，具有起止时间的
 * 
 * @author lWX537094
 *
 */
public class Stage implements Serializable {

    private static final long serialVersionUID = 6521519283300623828L;

    /**
     * 阶段名称
     */
    private String name;

    /**
     * 阶段起始时间
     */
    private long startTime;

    /**
     * 阶段结束时间
     */
    private long endTime;

    /**
     * 关联的项目ID，对应DB中的ID
     */
    private String projectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
