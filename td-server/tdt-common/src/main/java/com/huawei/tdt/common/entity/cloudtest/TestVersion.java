package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;
import java.util.List;

/**
 * 分支或迭代
 * 
 * @author lWX537094
 *
 */
public class TestVersion implements Serializable {

    private static final long serialVersionUID = 5161685775400125195L;

    private String uri;

    private String name;

    private String parentUri;

    private String parentPath;

    private String pbiId;

    /**
     * 是否为迭代 1:是，0：否，表示为分支
     */
    private String isIterator;

    /**
     * 所属服务名
     */
    private String serviceName;

    /**
     * 在为分支的情况下，是否为主分支 1:是，0：否
     */
    private String isMaster;

    private List<TestCase> testCases;

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

    public String getParentUri() {
        return parentUri;
    }

    public void setParentUri(String parentUri) {
        this.parentUri = parentUri;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getPbiId() {
        return pbiId;
    }

    public void setPbiId(String pbiId) {
        this.pbiId = pbiId;
    }

    public String getIsIterator() {
        return isIterator;
    }

    public void setIsIterator(String isIterator) {
        this.isIterator = isIterator;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
