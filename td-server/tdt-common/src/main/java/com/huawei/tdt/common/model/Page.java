package com.huawei.tdt.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Page
 * @author lWX537094
 * @param <T> 页面数据模型的类型
 */
public class Page<T> implements Serializable
{
    private static final long serialVersionUID = 8710855335100504484L;

    private int totalNum = 0;

    private int totalPageNum = 0;

    private int pageNo;

    private int pageSize;

    private List<T> data;

    public int getTotalNum()
    {
        return totalNum;
    }

    public int getTotalPageNum()
    {
        return totalPageNum;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public List<T> getData()
    {
        return data;
    }

    public void setTotalNum(int totalNum)
    {
        this.totalNum = totalNum;
    }

    public void setTotalPageNum(int totalPageNum)
    {
        this.totalPageNum = totalPageNum;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setData(List<T> data)
    {
        this.data = data;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }
}
