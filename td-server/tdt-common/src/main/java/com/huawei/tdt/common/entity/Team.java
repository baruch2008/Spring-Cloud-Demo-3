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
     * 0:表示整个项目团队，1:表示项目中小组
     * 
     * 给项目中配置成员时，先创建先类型为0的Team，再往User表以及Team与User关联表中插入相关数据
     */
    private int type;

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

}
