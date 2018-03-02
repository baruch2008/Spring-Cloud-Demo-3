package com.huawei.tdt.common.entity;

import java.io.Serializable;

/**
 * 项目里程碑
 * 
 * @author lWX537094
 *
 */
public class Milestone implements Serializable {
    private static final long serialVersionUID = -1808589303203673054L;

    /**
     * 里程碑ID，对应DB中的ID
     */
    private String id;

    /**
     * 里程碑名称
     */
    private String name;

    /**
     * 里程碑时间点
     */
    private long time;

    /**
     * 项目ID，对应DB中的ID
     */
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
