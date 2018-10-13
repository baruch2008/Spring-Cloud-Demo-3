package com.huawei.tdt.codeactivity.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huawei.tdt.common.entity.User;

/**
 * 用户映射类.
 */
@Repository
public interface UserMapper2
{
    /**
     * 用id获取用户.
     *
     * @param id 用户id
     * @return 用户对象
     */
    User getUserById(String id);

    /**
     * 插入用户.
     *
     * @param user 用户对象
     */
    void insertUser(User user);
    
    /**
     * 保存或更新用户
     * @param user 用户
     */
    void saveOrUpdate(User user);

    /**
     * 更新用户.
     *
     * @param user 用户对象
     */
    void updateUser(User user);

    /**
     * 查询用户在指定项目中的权限
     * 
     * @param projectId 项目ID
     * @param userId 用户ID
     * @return 权限集
     */
    List<String> getUserPrivileges(String projectId, String userId);
    
    int selectTest();
}
