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

/**
 * LDAP帮助类.
 */
public class LdapADHelper
{
    /**
     * 用来输出日志的静态对象.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LdapADHelper.class);

    /**
     * 规避“魔数”.
     */
    private static final int ARRAY_CAPACITY = 20;

    /**
     * 规避“魔数”.
     */
    private static final int BREAK_IF_NUM_GTE = 5;

    /**
     * LDAP登录的url. 默认端口为80的可以不用填写，其他端口需要填写，如ldap://xxx.com:8080
     */
    private static String url = "ldap://lggad05-dc:389/";

    private static LdapADHelper ldapADHelper = null;

    /**
     * LDAP管理员名称. 注意用户名的写法：domain\User 或 User@domain.com
     */
    private String adminName = "china\\pITtoolCI";

    private String searchBase = "dc=china,dc=huawei,dc=com";

    private String attributes = "department;givenname;SAMAccountName;" + "extensionAttribute1;mail;mobile;"
            + "hw-DepartName1;hw-DepartName2;hw-DepartName3;" + "hw-DepartName4;hw-DepartName5;hw-DepartName6;"
            + "hw-DepartName7";

    /**
     * 鉴权动态配置.
     */
    private AuthDynamicConfig dynamicConfig = SpringUtil.getBean(AuthDynamicConfig.class);

    /**
     * 上下文.
     */
    private DirContext ctx = null;

    /**
     * 构造函数.
     */
    private LdapADHelper()
    {

    }

    /**
     * Get LdapADHelper
     * @return LdapADHelper
     */
    public static synchronized LdapADHelper getLdapADHelper()
    {
        if (null == ldapADHelper)
        {
            ldapADHelper = new LdapADHelper();
        }

        return ldapADHelper;
    }

    /**
     * 初始化ldap.
     */

    private void initLdap()
    {
        Hashtable<String, String> ldapEnv = new Hashtable<String, String>(ARRAY_CAPACITY);
        // LDAP访问安全级别
        ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        // AD User
        ldapEnv.put(Context.SECURITY_PRINCIPAL, adminName);
        // AD Password
        ldapEnv.put(Context.SECURITY_CREDENTIALS, dynamicConfig.getLdapAdminPassword());
        // LDAP工厂类
        ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

        // ldapEnv.put(Context.REFERRAL, "throw");

        // ldapEnv.put("java.naming.ldap.version", "2");

        ldapEnv.put("com.sun.jndi.ldap.read.timeout", "8000");

        ldapEnv.put(Context.PROVIDER_URL, url);

        try
        {
            ctx = new InitialLdapContext(ldapEnv, null);
            LOGGER.info("初始化ldap成功！");

        }
        catch (NamingException e)
        {
            LOGGER.error("Init ldap failed. The login user:" + adminName, e);
        }

    }

    /**
     * 关闭ldap.
     */

    private void closeLdap()
    {
        try
        {
            if (null != ctx)
            {
                ctx.close();
            }

        }
        catch (NamingException e)
        {
            LOGGER.error("Close ldap failed.", e);
        }

    }

    /**
     * 从参数value中解析获取信息.
     *
     * @param value 字符串值(用户账号、中文名或英文名)
     * @return 域用户列表
     */
    public List<DomainUser> fuzzyQueryUser(String value)
    {
        List<DomainUser> resultList = new ArrayList<DomainUser>();

        String searchFilter = "(&(objectClass=user)(|(SAMAccountName=" + value + "*)(givenname=" + value + ")(sn="
                + value + ")(cn=" + value + "*)))";
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        if (attributes != null && !"".equals(attributes))
        {
            String[] returnedAtts = attributes.split(";");
            searchCtls.setReturningAttributes(returnedAtts);
        }
        try
        {
            initLdap();

            NamingEnumeration< ? > answer = ctx.search(searchBase, searchFilter, searchCtls);
            int num = 0;
            while (answer.hasMore())
            {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                if (null == attrs)
                {
                    continue;
                }
                DomainUser user = parseUserAttributes(attrs);
                resultList.add(user);
                if (num >= BREAK_IF_NUM_GTE)
                {
                    break;
                }
                num++;
            }

        }
        catch (NamingException e)
        {
            LOGGER.error("Getting user info failed.", e);
        }
        finally
        {
            closeLdap();
        }

        return resultList;
    }

    /**
     * 从华为id解析域用户对象.
     *
     * @param huaweiID 华为id
     * @return 域用户对象
     */
    public DomainUser getUserInfo(String huaweiID)
    {
        DomainUser user = null;
        try
        {
            user = getUserInfo("user", "SAMAccountName", huaweiID, attributes);
        }
        catch (Exception e)
        {
            LOGGER.error("获取用户信息失败！" + e);
        }

        return user;
    }

    /**
     * 批量查询用户
     * 
     * @param userIds 用户ID集
     * @return 用户集
     */
    public List<DomainUser> batchQueryUserInfos(List<String> userIds)
    {
        List<DomainUser> resultList = new ArrayList<DomainUser>(userIds.size());

        StringBuilder searchFilter = new StringBuilder();
        searchFilter.append("(&(objectClass=user)(|");
        for (String userId : userIds)
        {
            searchFilter.append("(SAMAccountName=");
            searchFilter.append(userId);
            searchFilter.append(")");
        }
        searchFilter.append("))");

        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        if (attributes != null && !"".equals(attributes))
        {
            String[] returnedAtts = attributes.split(";");
            searchCtls.setReturningAttributes(returnedAtts);
        }
        try
        {
            initLdap();

            NamingEnumeration< ? > answer = ctx.search(searchBase, searchFilter.toString(), searchCtls);
            while (answer.hasMore())
            {
                SearchResult sr = (SearchResult) answer.next();
                Attributes attrs = sr.getAttributes();
                if (null == attrs)
                {
                    continue;
                }
                DomainUser user = parseUserAttributes(attrs);
                resultList.add(user);
            }
        }
        catch (NamingException e)
        {
            LOGGER.error("Getting user info failed.", e);
        }
        finally
        {
            closeLdap();
        }

        return resultList;
    }

    /**
     * 解析部门信息
     * 
     * @param attrs attrs
     * @param user user
     * @throws NamingException
     */
    private void parseDepartmentInfo(Attributes attrs, DomainUser user) throws NamingException
    {
        String departmentInfo = "";
        if (attrs.get("hw-DepartName1") != null)
        {
            user.setDepartName1((String) attrs.get("hw-DepartName1").get(0));
            departmentInfo += user.getDepartName1() + "/";

        }
        if (attrs.get("hw-DepartName2") != null)
        {
            user.setDepartName2((String) attrs.get("hw-DepartName2").get(0));
            departmentInfo += user.getDepartName2() + "/";
        }
        if (attrs.get("hw-DepartName3") != null)
        {
            user.setDepartName3((String) attrs.get("hw-DepartName3").get(0));
            departmentInfo += user.getDepartName3() + "/";
        }
        if (attrs.get("hw-DepartName4") != null)
        {
            user.setDepartName4((String) attrs.get("hw-DepartName4").get(0));
            departmentInfo += user.getDepartName4() + "/";
        }
        if (attrs.get("hw-DepartName5") != null)
        {
            user.setDepartName5((String) attrs.get("hw-DepartName5").get(0));
            departmentInfo += user.getDepartName5() + "/";
        }

        if (attrs.get("hw-DepartName6") != null)
        {
            user.setDepartName6((String) attrs.get("hw-DepartName6").get(0));
            departmentInfo += user.getDepartName6() + "/";
        }

        if (attrs.get("hw-DepartName7") != null)
        {
            user.setDepartName6((String) attrs.get("hw-DepartName7").get(0));
            departmentInfo += user.getDepartName6() + "/";
        }

        user.setInformation(departmentInfo);
    }

    /**
     * parseUserAttributes
     * 
     * @param attrs
     * @return DomainUser
     * @throws NamingException
     */
    private DomainUser parseUserAttributes(Attributes attrs) throws NamingException
    {
        DomainUser user = new DomainUser();
        if (attrs.get("mail") != null)
        {
            user.setUserEmail((String) attrs.get("mail").get(0));
        }

        if (attrs.get("department") != null)
        {
            user.setDepartName((String) attrs.get("department").get(0));
        }
        if (attrs.get("SAMAccountName") != null)
        {
            user.setUserName(attrs.get("SAMAccountName").get(0).toString().toLowerCase(Locale.US));
        }
        if (attrs.get("givenname") != null)
        {
            user.setEnglishname((String) attrs.get("givenname").get(0));
        }
        if (attrs.get("extensionAttribute1") != null)
        {

            user.setChinesename((String) attrs.get("extensionAttribute1").get(0));
        }
        if (attrs.get("mobile") != null)
        {
            user.setUserTelephone((String) attrs.get("mobile").get(0));
        }

        parseDepartmentInfo(attrs, user);
        if (user.getInformation().indexOf("IT产品线") != -1)
        {
            user.setIsFromIT(1);
        }
        else
        {
            user.setIsFromIT(0);
        }
        if (StringUtils.isEmpty(user.getChinesename()))
        {
            user.setChinesename(user.getEnglishname());
        }
        return user;
    }

    /**
     * 获取AD信息.
     *
     * @param type 查询类型
     * @param filter 条件字段
     * @param value 条件值
     * @param attrParams 需要查询的属性列表,以为;号分割
     * @return 域用户对象
     */
    private DomainUser getUserInfo(String type, String filter, String value, String attrParams)
    {
        DomainUser user = null;
        try
        {
            // LDAP搜索过滤器类
            String searchFilter = "(&(objectClass=" + type + ")(" + filter + "=" + value + "))";

            // 创建搜索控制器
            SearchControls searchCtls = new SearchControls();

            // 设置搜索范围
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            if (attrParams != null && !"".equals(attrParams))
            {
                // 定制返回属性
                String[] returnedAtts = attrParams.split(";");
                // 设置返回属性集, 不设置则返回所有属性
                searchCtls.setReturningAttributes(returnedAtts);
            }

            initLdap();

            // 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
            // Search for objects using the filter
            NamingEnumeration< ? > answer = ctx.search(searchBase, searchFilter, searchCtls);
            // 遍历结果集
            while (answer.hasMore())
            {
                // 得到符合搜索条件的DN
                SearchResult sr = (SearchResult) answer.next();
                // 得到符合条件的属性集
                Attributes attrs = sr.getAttributes();
                if (attrs != null)
                {
                    user = parseUserAttributes(attrs);
                }
            }
        }
        catch (NamingException e)
        {
            LOGGER.error("Getting user info failed.", e);
        }
        finally
        {
            closeLdap();
        }

        return user;
    }
}
