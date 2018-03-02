package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;

public class TestResult implements Serializable {

    private static final long serialVersionUID = -310360803334939409L;

    private String testCaseUri;

    private String iterOrTaskUri;

    private String type;

    private String lastResult;

    private long updateTime;

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
}
