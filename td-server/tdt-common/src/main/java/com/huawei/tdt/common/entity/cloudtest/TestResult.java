package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;

public class TestResult implements Serializable {

    private static final long serialVersionUID = -310360803334939409L;

    private String testCaseUri;

    private String iterOrTaskUri;

    /**
     * 迭代或任务
     * 0:Iterator,1:Task
     */
    private String type;

    private String lastResult;

    private long updateTime;
    
    private String branchUri;

    public String getTestCaseUri() {
        return testCaseUri;
    }

    public void setTestCaseUri(String testCaseUri) {
        this.testCaseUri = testCaseUri;
    }

    public String getIterOrTaskUri() {
        return iterOrTaskUri;
    }

    public void setIterOrTaskUri(String iterOrTaskUri) {
        this.iterOrTaskUri = iterOrTaskUri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getBranchUri() {
        return branchUri;
    }

    public void setBranchUri(String branchUri) {
        this.branchUri = branchUri;
    }
}
