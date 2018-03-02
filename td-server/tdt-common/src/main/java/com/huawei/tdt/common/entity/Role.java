package com.huawei.tdt.common.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = -9218875318144916630L;

    private String id;

    private String name;

    /**
     * 1:测试经理、2:TSE、3:TE、4:游客
     */
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
