package com.huawei.tdt.authenticate.model;

public class DomainUser implements java.io.Serializable {

    private static final long serialVersionUID = 1943087776943004827L;
    private String userName;
	private String userEmail;
	private String userTelephone;
	private String information;
	private int isFromIT;
	private String englishname;
	private String chinesename;
	private String departName;
	public DomainUser() {
	}
	
	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public int getIsFromIT() {
		return isFromIT;
	}
	public void setIsFromIT(int isFromIT) {
		this.isFromIT = isFromIT;
	}
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public String getChinesename() {
		return chinesename;
	}
	public void setChinesename(String chinesename) {
		this.chinesename = chinesename;
	}
}
