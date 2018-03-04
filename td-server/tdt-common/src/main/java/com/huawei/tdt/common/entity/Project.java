package com.huawei.tdt.common.entity;

import java.io.Serializable;
import java.util.List;

import com.huawei.tdt.common.entity.cloudtest.TestVersion;

/**
 * 项目
 * 
 * @author lWX537094
 *
 */
public class Project implements Serializable {

    private static final long serialVersionUID = -4744302740771409233L;

    /**
     * 项目ID，对应DB中ID值
     */
    private String projectId;

    /**
     * 所属父项目ID
     */
    private String parentProjectId;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目描述
     */
    private String desc;

    /**
     * 工具链模式：0，服务化产品；1，非服务化产品
     */
    private int mode;

    /**
     * 项目创建者身份ID
     */
    private String creatorId;

    /**
     * 项目创建者名称
     */
    private String creatorName;

    /**
     * 项目关联的PBI的ID
     */
    private String pbiId;

    private List<TestPlan> testPlans;

    private List<Milestone> milestones;

    private List<Stage> stages;

    private List<Team> teams;

    private List<User> owners;

    /**
     * 项目对应的分支
     */
    private TestVersion testVersion;
    
    /**
     * 是否已归档
     * 
     */
    private boolean isArchived;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(String parentProjectId) {
        this.parentProjectId = parentProjectId;
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPbiId() {
        return pbiId;
    }

    public void setPbiId(String pbiId) {
        this.pbiId = pbiId;
    }

    public List<TestPlan> getTestPlans() {
        return testPlans;
    }

    public void setTestPlans(List<TestPlan> testPlans) {
        this.testPlans = testPlans;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public TestVersion getTestVersion() {
        return testVersion;
    }

    public void setTestVersion(TestVersion testVersion) {
        this.testVersion = testVersion;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }
}
