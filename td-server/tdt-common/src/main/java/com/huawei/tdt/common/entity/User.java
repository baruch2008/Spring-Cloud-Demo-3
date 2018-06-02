package com.huawei.tdt.common.entity;

import java.io.Serializable;

/**
 * 用户
 * 
 * 给项目配置Owner会往项目与此实体关联的表中插入一条记录，在转移Owner时会将关联表中原用户对应的记录删除，
 * 
 * 给测试计划配置Owner会往测试计划与此实体关联的表中输入一条记录，在转移Owner时会将关联表中原用户对应的记录删除
 * 
 * @author lWX537094
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = 510060382006874673L;

    /**
     * 用户身份ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;
    
    private String departmentName1;
    
    private String departmentName2;
    
    private String departmentName3;

    public String getDepartmentName1() {
        return departmentName1;
    }

    public void setDepartmentName1(String departmentName1) {
        this.departmentName1 = departmentName1;
    }

    public String getDepartmentName2() {
        return departmentName2;
    }

    public void setDepartmentName2(String departmentName2) {
        this.departmentName2 = departmentName2;
    }

    public String getDepartmentName3() {
        return departmentName3;
    }

    public void setDepartmentName3(String departmentName3) {
        this.departmentName3 = departmentName3;
    }

    public User(String id, String name, String dp1, String dp2, String dp3) {
        this.id = id;
        this.name = name;
        this.departmentName1 = dp1;
        this.departmentName2 = dp2;
        this.departmentName3 = dp3;
    }

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
}
