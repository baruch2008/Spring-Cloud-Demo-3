package com.huawei.tdt.authenticate.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.tdt.common.authorization.annotation.Authorization;
import com.huawei.tdt.common.authorization.manager.TokenManager;
import com.huawei.tdt.common.authorization.model.Token;
import com.huawei.tdt.common.model.ResponseResult;
import com.huawei.tdt.common.model.UserVo;

@Api(value = "AuthController", tags = { "用户管理相关接口" })
@RestController
public class AuthController {
    @Autowired
    private TokenManager tokenMgr;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "根据域用户名和密码对用户身份进行验证", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = Constants.HTTP_OK, message = "登录成功后返回数据", response = ResponseResult.class) })
    public ResponseResult login(@RequestBody UserVo userVo) {
        ResponseResult respResult = new ResponseResult();
        response.setHeader("isSuccess", "true");
        Token token = tokenMgr.createToken(userVo.getUsername());

        Cookie authorizationCookie = new Cookie("authorization", token.getToken());
        authorizationCookie.setHttpOnly(true);
        authorizationCookie.setMaxAge(-1);
        response.addCookie(authorizationCookie);

        response.addCookie(new Cookie("userId", token.getUserId()));

        return respResult;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    @Authorization
    public ResponseEntity<String> logout(@RequestParam String userId) {
        tokenMgr.deleteToken(userId);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
