package com.huawei.tdt.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 项目成员组，表示项目中团队
 * 
 * @author lWX537094
 *
 */
public class Team implements Serializable {

    private static final long serialVersionUID = 6493215110468146619L;

    /**
     * 组ID，对应DB中的ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 所属项目ID，对应DB中的ID
     */
    private String projectId;

    private List<User> members;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

}
