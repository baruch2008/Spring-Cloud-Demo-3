package com.huawei.tdt.common.entity.cloudtest;

import java.io.Serializable;

/**
 * 用例
 * 
 * @author lWX537094
 *
 */
public class TestCase implements Serializable {

    private static final long serialVersionUID = -2521700153098264344L;

    private String uri;

    /**
     * 用例名
     * 
     * 不能为空
     */
    private String name;

    private String parentUri;

    private String parentPath;

    /**
     * 用例Number
     * 
     * 不能为空，且唯一
     */
    private String number;

    /**
     * 所属阶段
     * 
     * 不能为空 1：Alpha，2：Beta，3：Gamma
     */
    private String stage;

    private String autoType;

    /**
     * 测试类型
     * 
     * 如：功能测试、性能测试等
     */
    private String testType;

    /**
     * 如：Service Function Test(API)、Security Test等
     */
    private String activity;

    private String author;

    /**
     * 上一次执行结果
     * 
     * Passed,Failed,Unavailable,Investigated,Blocked
     * 
     * 因为一个用例可能会被分配多个迭代中，所以执行结果记录会有多条，但用例上的最终结果以最后一次被执行的结果为准 在同一个迭代中用例可能会被分配到多个任务中，每个任务有各自的用例执行结果
     * 
     * 从迭代的维度以最后一个被执行任务中的用例结果为准 从分支的维度以迭代中最新的一次执行结果为准
     * 
     */
    private String lastResult;

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

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getAutoType() {
        return autoType;
    }

    public void setAutoType(String autoType) {
        this.autoType = autoType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }
}
