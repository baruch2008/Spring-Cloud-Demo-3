package com.huawei.tdt.common.entity;

import java.io.Serializable;
import java.util.List;

import com.huawei.tdt.common.entity.cloudtest.TestCase;
import com.huawei.tdt.common.entity.cloudtest.TestVersion;

/**
 * 测试计划
 * 
 * @author lWX537094
 *
 */
public class TestPlan implements Serializable {

    private static final long serialVersionUID = 1802528273562519495L;

    /**
     * 测试计划ID，对应DB中的ID
     */
    private String id;

    /**
     * 测试计划名称
     */
    private String name;

    /**
     * 所属父测试计划ID，对应DB中的ID
     */
    private String parentId;

    /**
     * 测试计划描述
     */
    private String desc;

    /**
     * 测试计划预计开始时间
     */
    private long startTime;

    /**
     * 测试计划预计结束时间
     */
    private long endTime;

    /**
     * 测试计划实际结束时间
     */
    private long realEndTime;

    /**
     * 预估工作量
     */
    private double estimatedWorkload;

    /**
     * 实际工作量
     */
    private double realWorkload;

    /**
     * 测试进度
     */
    private double progress;

    /**
     * 测试计划是否已逾期
     */
    private boolean isExpired;

    /**
     * 创建者身份ID
     */
    private String creatorId;

    /**
     * 创建者名称
     */
    private String creatorName;

    /**
     * 所属项目ID，对应DB中的ID
     */
    private String projectId;

    /**
     * 自定义字段1
     */
    private String customField1;

    /**
     * 用例总数
     */
    private int totalCasesNum;

    /**
     * 所有已分解用例数
     */
    private int totalAssignedCasesNum;

    /**
     * 所有已执行用例数
     */
    private int totalExecutedCasesNum;

    private List<TestCase> testCases;

    private List<User> owners;

    private TestVersion testVersion;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public long getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(long realEndTime) {
        this.realEndTime = realEndTime;
    }

    public double getEstimatedWorkload() {
        return estimatedWorkload;
    }

    public void setEstimatedWorkload(double estimatedWorkload) {
        this.estimatedWorkload = estimatedWorkload;
    }

    public double getRealWorkload() {
        return realWorkload;
    }

    public void setRealWorkload(double realWorkload) {
        this.realWorkload = realWorkload;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public int getTotalCasesNum() {
        return totalCasesNum;
    }

    public void setTotalCasesNum(int totalCasesNum) {
        this.totalCasesNum = totalCasesNum;
    }

    public int getTotalAssignedCasesNum() {
        return totalAssignedCasesNum;
    }

    public void setTotalAssignedCasesNum(int totalAssignedCasesNum) {
        this.totalAssignedCasesNum = totalAssignedCasesNum;
    }

    public int getTotalExecutedCasesNum() {
        return totalExecutedCasesNum;
    }

    public void setTotalExecutedCasesNum(int totalExecutedCasesNum) {
        this.totalExecutedCasesNum = totalExecutedCasesNum;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
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
}
