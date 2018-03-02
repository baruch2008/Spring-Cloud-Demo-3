package com.huawei.tdt.authenticate.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ldap {
    private static final Logger LOGGER = LoggerFactory.getLogger(Ldap.class);

    // userName与passWord不能为空
    public static boolean isValid(String userName, String passWord) {
        boolean result = false;

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://LGGAD03-DC:389");
        env.put(Context.SECURITY_PRINCIPAL, userName + "@china.huawei.com");
        env.put(Context.SECURITY_CREDENTIALS, passWord);
        try {
            LdapContext ctx = new InitialLdapContext(env, null);
            ctx.close();
            result = true;
        } catch (Exception e) {
            LOGGER.error("Authenticate failed", e);
            result = false;
        }

        return result;
    }
}
