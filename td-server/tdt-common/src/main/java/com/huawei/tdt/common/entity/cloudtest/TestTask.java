package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;
import java.util.List;

public class TestTask implements Serializable {

    private static final long serialVersionUID = -5073710137912480391L;

    private String uri;

    private String name;

    private String stage;

    private String taskType;

    private List<TestCase> testCases;
    
    private String iteratorUri;
    
    private String branchUri;
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getIteratorUri() {
        return iteratorUri;
    }

    public void setIteratorUri(String iteratorUri) {
        this.iteratorUri = iteratorUri;
    }

    public String getBranchUri() {
        return branchUri;
    }

    public void setBranchUri(String branchUri) {
        this.branchUri = branchUri;
    }
}
