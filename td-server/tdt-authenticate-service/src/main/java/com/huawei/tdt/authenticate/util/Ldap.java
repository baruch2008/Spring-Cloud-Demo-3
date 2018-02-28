package com.huawei.tdt.authenticate.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class Ldap
{
    //userName与passWord不能为空
    public static boolean isValid(String userName, String passWord)
    {
        boolean result = false;
        
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://LGGAD03-DC:389");
        env.put(Context.SECURITY_PRINCIPAL, userName + "@china.huawei.com");
        env.put(Context.SECURITY_CREDENTIALS, passWord);
        try
        {
            LdapContext ctx = new InitialLdapContext(env, null);
            ctx.close();
            result = true;
        }
        catch (Exception e)
        {
            result = false;
            //e.printStackTrace();
        }
        
        return result;
    }
    
    public static void main(String[] args)
    {
        System.out.println(Ldap.isValid("1", "1"));
        
    }
}
