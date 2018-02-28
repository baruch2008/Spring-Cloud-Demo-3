package com.huawei.tdt.gateway.model;

import java.io.Serializable;

public class UserParams implements Serializable{
	private static final long serialVersionUID = -5050677211082623931L;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
