package com.huawei.etlapi.tmss.mapper;

import org.springframework.stereotype.Repository;

/**
 * ProjectCTMapper.java
 * 
 * @author lWX537094
 */
@Repository
public interface ProjectCloudTestMapper {
    /**
     * 查询指定分支下的迭代信息
     * 
     * @param parentUri 迭代关联的分支URI
     * @return 迭代列表
     */
    //List<CloudTestIteratorVo> getIterators(String parentUri);
}
