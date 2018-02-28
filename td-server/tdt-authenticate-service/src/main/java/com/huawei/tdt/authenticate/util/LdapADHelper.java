package com.huawei.tdt.authenticate.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.tdt.authenticate.model.DomainUser;
import com.huawei.tdt.common.util.SpringUtil;

public class LdapADHelper {
	public LdapADHelper() {

	}

	private static final Logger logger = LoggerFactory.getLogger(LdapADHelper.class);

	private static String url = "ldap://lggad05-dc:389/";// 默认端口为80的可以不用填写，其他端口需要填写，如ldap://xxx.com:8080

	private String adminName = "china\\pITtoolCI";// 注意用户名的写法：domain\User 或 User@domain.com

	private DynamicConfig dynamicConfig = SpringUtil.getBean(DynamicConfig.class);
	
	private DirContext ctx = null;

	/**
	 * 
	 * 初始化ldap
	 * 
	 */

	public void initLdap() {
		Hashtable<String, String> ldapEnv = new Hashtable<String, String>(20);

		ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别

		ldapEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User

		ldapEnv.put(Context.SECURITY_CREDENTIALS, dynamicConfig.getLdapAdminPassword()); // AD Password

		ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类

		// ldapEnv.put(Context.REFERRAL, "throw");

		// ldapEnv.put("java.naming.ldap.version", "2");

		ldapEnv.put("com.sun.jndi.ldap.read.timeout", "8000");

		ldapEnv.put(Context.PROVIDER_URL, url);

		try {
			ctx = new InitialLdapContext(ldapEnv, null);
			logger.info("初始化ldap成功！");

		} catch (NamingException e) {
			logger.error("Init ldap failed. The login user:" + adminName, e);
		}

	}

	/**
	 * 
	 * 关闭ldap
	 * 
	 */

	public void closeLdap() {

		try {

			this.ctx.close();

		} catch (NamingException e) {
			logger.error("Close ldap failed.", e);
		}

	}

	/**
	 * 
	 * @param type
	 *            查询类型
	 * 
	 * @param filter
	 *            条件字段
	 * 
	 * @param value
	 *            条件值
	 * 
	 * @param attr
	 *            需要查询的属性列表,以为;号分割
	 * 
	 * @return
	 * 
	 */

	public DomainUser getADInfo(String type, String filter, String value, String attributes) {

		DomainUser user = new DomainUser();
		try {
			// 域节点
			String searchBase = "dc=china,dc=huawei,dc=com";

			// LDAP搜索过滤器类

			// cn=*name*模糊查询 cn=name 精确查询

			// String searchFilter = "(&(objectClass="+type+")("+filter+"=*" + name + "*))";
			// //这个是模糊查询，速度很慢

			String searchFilter = "(&(objectClass=" + type + ")(" + filter + "=" + value + "))";

			// 创建搜索控制器

			SearchControls searchCtls = new SearchControls();

			// 设置搜索范围

			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			if (attributes != null && !"".equals(attributes)) {

				String returnedAtts[] = attributes.split(";"); // 定制返回属性

				searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集, 不设置则返回所有属性

			}

			// 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果

			NamingEnumeration<?> answer = ctx.search(searchBase, searchFilter, searchCtls);// Search for objects using
																							// the filter

			while (answer.hasMore()) {// 遍历结果集

				SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN

				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集

				if (Attrs != null) {
					if (Attrs.get("mail") != null) {
						user.setUserEmail((String) Attrs.get("mail").get(0));
					}
					if (Attrs.get("extensionAttribute1") != null) {
						user.setUserName((String) Attrs.get("extensionAttribute1").get(0));
					}
					if (Attrs.get("mobile") != null) {
						user.setUserTelephone((String) Attrs.get("mobile").get(0));
					}
					String departmentInfo = "";
					if (Attrs.get("hw-DepartName1") != null) {
						departmentInfo += (String) Attrs.get("hw-DepartName1").get(0) + "/";
					}
					if (Attrs.get("hw-DepartName2") != null) {
						departmentInfo += (String) Attrs.get("hw-DepartName2").get(0) + "/";
					}
					if (Attrs.get("hw-DepartName3") != null) {
						departmentInfo += (String) Attrs.get("hw-DepartName3").get(0) + "/";
					}
					if (Attrs.get("hw-DepartName4") != null) {
						departmentInfo += (String) Attrs.get("hw-DepartName4").get(0) + "/";
					}
					if (Attrs.get("hw-DepartName5") != null) {
						departmentInfo += (String) Attrs.get("hw-DepartName5").get(0) + "/";
					}
					user.setInformation(departmentInfo);
					/*
					 * if (departmentInfo.indexOf("IT产品线")!=-1){ user.setIsFromIT(1); }else{
					 * user.setIsFromIT(0); }
					 */
				} // if

			} // while

		} catch (NamingException e) {
			logger.error("", e);
		}
		return user;
	}

	public List<DomainUser> getInfo(String value) {
		String attributes = "department;givenname;SAMAccountName;extensionAttribute1;mail;mobile;hw-DepartName1;hw-DepartName2;hw-DepartName3;hw-DepartName4;hw-DepartName5";
		List<DomainUser> resultList = new ArrayList<DomainUser>();
		String searchBase = "dc=china,dc=huawei,dc=com";
		String searchFilter = "(&(objectClass=user)(|(SAMAccountName=" + value + "*)(givenname=" + value + ")(sn="
				+ value + ")(cn=" + value + "*)))";
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		if (attributes != null && !"".equals(attributes)) {
			String returnedAtts[] = attributes.split(";");
			searchCtls.setReturningAttributes(returnedAtts);
		}
		try {
			NamingEnumeration<?> answer = ctx.search(searchBase, searchFilter, searchCtls);
			int num = 0;
			while (answer.hasMore()) {

				SearchResult sr = (SearchResult) answer.next();
				Attributes Attrs = sr.getAttributes();
				if (Attrs.get("givenname") == null && Attrs.get("extensionAttribute1") == null) {
					continue;
				}
				DomainUser user = new DomainUser();
				if (Attrs.get("mail") != null) {
					user.setUserEmail((String) Attrs.get("mail").get(0));
				}
				// System.out.println("department:"+Attrs.get("department").get(0));
				/*
				 * System.out.println("SAMAccountName:"+Attrs.get("SAMAccountName").get(0)); if
				 * (Attrs.get("givenname") != null) {
				 * System.out.println("givenname:"+Attrs.get("givenname").get(0)); } if
				 * (Attrs.get("sn") != null) { System.out.println("sn:"+Attrs.get("sn").get(0));
				 * } if (Attrs.get("cn") != null) {
				 * System.out.println("cn:"+Attrs.get("cn").get(0)); }
				 * System.out.println("extensionAttribute1:"+Attrs.get("extensionAttribute1").
				 * get(0));
				 */

				if (Attrs.get("department") != null) {
					user.setDepartName((String) Attrs.get("department").get(0));
				}
				if (Attrs.get("SAMAccountName") != null) {
					user.setUserName(Attrs.get("SAMAccountName").get(0).toString().toLowerCase(Locale.US));
				}
				if (Attrs.get("givenname") != null) {
					user.setEnglishname((String) Attrs.get("givenname").get(0));
				}
				if (Attrs.get("extensionAttribute1") != null) {

					user.setChinesename((String) Attrs.get("extensionAttribute1").get(0));
				}
				if (Attrs.get("mobile") != null) {
					user.setUserTelephone((String) Attrs.get("mobile").get(0));
				}
				String departmentInfo = "";
				if (Attrs.get("hw-DepartName1") != null) {
					departmentInfo += (String) Attrs.get("hw-DepartName1").get(0) + "/";
				}
				if (Attrs.get("hw-DepartName2") != null) {
					departmentInfo += (String) Attrs.get("hw-DepartName2").get(0) + "/";
				}
				if (Attrs.get("hw-DepartName3") != null) {
					departmentInfo += (String) Attrs.get("hw-DepartName3").get(0) + "/";
				}
				if (Attrs.get("hw-DepartName4") != null) {
					departmentInfo += (String) Attrs.get("hw-DepartName4").get(0) + "/";
				}
				if (Attrs.get("hw-DepartName5") != null) {
					departmentInfo += (String) Attrs.get("hw-DepartName5").get(0) + "/";
				}
				user.setInformation(departmentInfo);
				if (departmentInfo.indexOf("IT产品线") != -1) {
					user.setIsFromIT(1);
				} else {
					user.setIsFromIT(0);
				}
				if (StringUtils.isEmpty(user.getChinesename())) {
					user.setChinesename(user.getEnglishname());
				}
				resultList.add(user);
				if (num >= 5) {
					break;
				}
				num++;
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public DomainUser getDepartmentInfo(String type, String filter, String value, String attributes) {

		DomainUser user = new DomainUser();
		try {
			// 域节点
			String searchBase = "dc=china,dc=huawei,dc=com";
			String searchFilter = "(&(objectClass=" + type + ")(" + filter + "=" + value + "))";
			// 创建搜索控制器
			SearchControls searchCtls = new SearchControls();
			// 设置搜索范围
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			if (attributes != null && !"".equals(attributes)) {
				String returnedAtts[] = attributes.split(";"); // 定制返回属性
				searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集, 不设置则返回所有属性
			}

			// 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
			NamingEnumeration<?> answer = ctx.search(searchBase, searchFilter, searchCtls);

			while (answer.hasMore()) {
				// 遍历结果集
				SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN
				Attributes Attrs = sr.getAttributes();// 得到符合条件的属性集
				if (Attrs != null) {
					if (Attrs.get("mail") != null) {
						user.setUserEmail((String) Attrs.get("mail").get(0));
					}
					if (Attrs.get("SAMAccountName") != null) {
						user.setUserName(Attrs.get("SAMAccountName").get(0).toString().toLowerCase(Locale.US));
					}
					if (Attrs.get("givenname") != null) {
						user.setEnglishname((String) Attrs.get("givenname").get(0));
					}
					if (Attrs.get("extensionAttribute1") != null) {
						user.setChinesename((String) Attrs.get("extensionAttribute1").get(0));
					}
					if (Attrs.get("mobile") != null) {
						user.setUserTelephone((String) Attrs.get("mobile").get(0));
					}
					String departmentName = "";
					String departmentInfo = "";
					if (Attrs.get("hw-DepartName1") != null) {
						String name = (String) Attrs.get("hw-DepartName1").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName2") != null) {
						String name = (String) Attrs.get("hw-DepartName2").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName3") != null) {
						String name = (String) Attrs.get("hw-DepartName3").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName4") != null) {
						String name = (String) Attrs.get("hw-DepartName4").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName5") != null) {
						String name = (String) Attrs.get("hw-DepartName5").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName6") != null) {
						String name = (String) Attrs.get("hw-DepartName6").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					if (Attrs.get("hw-DepartName7") != null) {
						String name = (String) Attrs.get("hw-DepartName7").get(0);
						departmentInfo += name + "/";
						departmentName = name;
					}
					user.setInformation(departmentInfo);
					user.setDepartName(departmentName);
				}
			}

		} catch (NamingException e) {
			logger.error("", e);
		}
		return user;
	}

	public static List<DomainUser> userInfo(String value) {
		LdapADHelper ad = new LdapADHelper();
		ad.initLdap();
		List<DomainUser> resultList = new ArrayList<DomainUser>();
		;
		try {
			resultList = ad.getInfo(value);
			ad.closeLdap();
		} catch (Exception e) {
			logger.error("获取用户信息失败！" + e);
		}
		return resultList;
	}

	public static DomainUser getUserInfo(String huaweiID) {
		LdapADHelper ad = new LdapADHelper();
		ad.initLdap();
		// 通过域账号查找用户所有属性extensionAttribute1;mail;mobile
		DomainUser user = null;
		try {
			user = ad.getADInfo("user", "SAMAccountName", huaweiID,
					"department;givenname;SAMAccountName;extensionAttribute1;mail;mobile;hw-DepartName1;hw-DepartName2;hw-DepartName3;hw-DepartName4;hw-DepartName5");
			ad.closeLdap();
		} catch (Exception e) {
			logger.error("获取用户信息失败！" + e);
		}
		return user;
	}

	public static DomainUser getUserDepartmentInfo(String huaweiID) {
		LdapADHelper ad = new LdapADHelper();
		ad.initLdap();
		// 通过域账号查找用户部门信息
		DomainUser user = null;
		try {
			user = ad.getDepartmentInfo("user", "SAMAccountName", huaweiID,
					"givenname;SAMAccountName;extensionAttribute1;mail;mobile;hw-DepartName1;hw-DepartName2;hw-DepartName3;hw-DepartName4;hw-DepartName5;hw-DepartName6;hw-DepartName7");
			ad.closeLdap();
		} catch (Exception e) {
			logger.error("获取用户信息失败！" + e);
		}
		return user;
	}
}
