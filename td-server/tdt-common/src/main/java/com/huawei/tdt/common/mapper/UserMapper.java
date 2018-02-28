package com.huawei.tdt.common.mapper;

import org.springframework.stereotype.Repository;

import com.huawei.tdt.common.entity.User;

@Repository
public interface UserMapper {
	User getUserById(String id);
	
	void insertUser(User user);
	
	void updateUser(User user);
}
