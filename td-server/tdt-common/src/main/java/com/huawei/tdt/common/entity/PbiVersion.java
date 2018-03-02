package com.huawei.tdt.common.entity;

import java.io.Serializable;

/**
 * PBI
 * 
 * @author lWX537094
 *
 */
public class PbiVersion implements Serializable {
    private static final long serialVersionUID = 3481538248497465979L;

    /**
     * PBI的ID
     */
    private String id;

    private String category;

    private String typeName;

    private String cn;

    private String en;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
