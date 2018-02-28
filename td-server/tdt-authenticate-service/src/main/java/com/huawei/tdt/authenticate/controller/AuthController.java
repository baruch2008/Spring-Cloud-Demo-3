package com.huawei.tdt.authenticate.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.tdt.authenticate.model.DomainUser;
import com.huawei.tdt.authenticate.util.Ldap;
import com.huawei.tdt.authenticate.util.LdapADHelper;
import com.huawei.tdt.common.authorization.annotation.Authorization;
import com.huawei.tdt.common.authorization.constants.ResponseStatusEnum;
import com.huawei.tdt.common.authorization.manager.TokenManager;
import com.huawei.tdt.common.authorization.model.TokenModel;
import com.huawei.tdt.common.entity.User;
import com.huawei.tdt.common.mapper.UserMapper;
import com.huawei.tdt.common.model.ResponseResult;
import com.huawei.tdt.common.model.UserVo;

@RestController
public class AuthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TokenManager tokenMgr;

	@Autowired
	private HttpServletResponse response;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseResult login(@RequestBody UserVo userVo) {

		ResponseResult respResult = new ResponseResult();
		response.setHeader("isSuccess", "false");

		boolean isPassed = false;
		if (!StringUtils.isEmpty(userVo.getUsername()) && !StringUtils.isEmpty(userVo.getPassword())) {
			isPassed = Ldap.isValid(userVo.getUsername(), userVo.getPassword());
		} else {
			respResult.setStatus(ResponseStatusEnum.FAIL);
			respResult.setCode("用户名和密码不能为空");
			return respResult;
		}

		if (isPassed) {
			respResult.setStatus(ResponseStatusEnum.SUCCESS);
			response.setHeader("isSuccess", "true");
			List<DomainUser> users = LdapADHelper.userInfo(userVo.getUsername());
			if (!users.isEmpty()) {
				DomainUser domainUser = users.get(0);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("chinesename", domainUser.getChinesename());
				map.put("username", domainUser.getUserName());

				respResult.setData(map);

				User user = userMapper.getUserById(userVo.getUsername());
				if (null == user) {
					user = new User(userVo.getUsername(), domainUser.getChinesename());
					userMapper.insertUser(user);
				} else {
					user.setName(domainUser.getChinesename());
					userMapper.updateUser(user);
				}

			}

			TokenModel tokenModel = tokenMgr.createToken(userVo.getUsername());

			Cookie authorizationCookie = new Cookie("authorization", tokenModel.getToken());
			authorizationCookie.setHttpOnly(true);
			authorizationCookie.setMaxAge(-1);
			response.addCookie(authorizationCookie);

			response.addCookie(new Cookie("userId", tokenModel.getUserId()));

			LOGGER.debug("The user {} logined.", userVo.getUsername());

		} else {
			respResult.setStatus(ResponseStatusEnum.FAIL);
			respResult.setCode("用户名或密码不正确");
		}

		return respResult;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	@Authorization
	public ResponseEntity<String> logout(@RequestParam String userId) {
		tokenMgr.deleteToken(userId);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
