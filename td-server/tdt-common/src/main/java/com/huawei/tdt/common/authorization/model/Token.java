package com.huawei.tdt.common.authorization.model;

public class Token {

    //用户id
    private String userId;

    //随机生成的uuid
    private String token;

    public Token(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
