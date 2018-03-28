package com.huawei.tdt.authenticate.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 鉴权动态配置.
 */
@Component
final class AuthDynamicConfig
{
    /**
     * LDAP管理员密码.
     */
    @Value("${pITtoolCI.password}")
    private String ldapAdminPassword;

    /**
     * 获取LDAP管理员密码.
     *
     * @return LDAP管理员密码
     */
    public String getLdapAdminPassword()
    {
        return ldapAdminPassword;
    }
}
