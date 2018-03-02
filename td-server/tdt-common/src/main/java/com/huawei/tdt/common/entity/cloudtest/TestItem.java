package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;
import java.util.List;

/**
 * 特性或文件夹
 * 
 * @author lWX537094
 *
 */
public class TestItem implements Serializable {

    private static final long serialVersionUID = -5759301732055543303L;

    private String uri;

    private String name;

    private String parentUri;

    private String parentPath;

    /**
     * 特性或文件夹Number，非必需值
     */
    private String number;

    /**
     * 是否为特性 1：是，0：否，表示为文件夹
     */
    private String isFeature;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIsFeature() {
        return isFeature;
    }

    public void setIsFeature(String isFeature) {
        this.isFeature = isFeature;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
