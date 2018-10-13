package com.huawei.tdt.codeactivity.model;

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
	
	private String departName1;
	private String departName2;
	private String departName3;
	private String departName4;
	private String departName5;
	private String departName6;
	
	public String getDepartName1() {
        return departName1;
    }

    public void setDepartName1(String departName1) {
        this.departName1 = departName1;
    }

    public String getDepartName2() {
        return departName2;
    }

    public void setDepartName2(String departName2) {
        this.departName2 = departName2;
    }

    public String getDepartName3() {
        return departName3;
    }

    public void setDepartName3(String departName3) {
        this.departName3 = departName3;
    }

    public String getDepartName4() {
        return departName4;
    }

    public void setDepartName4(String departName4) {
        this.departName4 = departName4;
    }

    public String getDepartName5() {
        return departName5;
    }

    public void setDepartName5(String departName5) {
        this.departName5 = departName5;
    }

    public String getDepartName6() {
        return departName6;
    }

    public void setDepartName6(String departName6) {
        this.departName6 = departName6;
    }

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
