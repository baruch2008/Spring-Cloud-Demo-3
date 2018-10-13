package com.huawei.tdt.codeactivity.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * MyServlet
 * 
 * @author lWX537094
 */
@WebServlet(name="myServlet",urlPatterns="/servlet/myServlet")
public class MyServlet extends HttpServlet
{
    private static final long serialVersionUID = -4945572424045694225L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        CookieStore cookieStore = new BasicCookieStore();
        
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
     
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
            .setDefaultCookieStore(cookieStore).build();
        
        //HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost("https://login.huawei.com/login/login.do");
        
        List<NameValuePair> nvps = new ArrayList <NameValuePair>();    
        nvps.add(new BasicNameValuePair("actionFlag", "loginAuthenticate"));
        nvps.add(new BasicNameValuePair("loginMethod", "login"));
        nvps.add(new BasicNameValuePair("loginPageType", "mix"));
        nvps.add(new BasicNameValuePair("redirect", "http://localhost:8080"));
        nvps.add(new BasicNameValuePair("uid", "lwx537094"));
        nvps.add(new BasicNameValuePair("password", "Tiger@1234"));
        nvps.add(new BasicNameValuePair("verifyCode", "2345"));
            
        httpost.setEntity(new UrlEncodedFormEntity(nvps));  
        
        httpClient.execute(httpost);
        //HttpResponse httpResponse = httpClient.execute(httpost);
        //HttpEntity httpEntity = httpResponse.getEntity();
        
        
        //httpEntity.writeTo(resp.getOutputStream());
        
        //HttpGet httpGet = new HttpGet("http://w3.huawei.com/bicloud/mobileApp/component.jsp?appId=D8D2FA7E12F4478085734CD6FF8F3627&charId=144CCF181FA64398A4F70540608B906F");
        HttpGet httpGet = new HttpGet("https://szxy1-cdt.huawei.com/cloudtest/frame/routing/atcase/layLoadPbiTree?resourceType=402-00024751&pbiId=7684573");
        HttpResponse httpResponse2 = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse2.getEntity();
        httpEntity.writeTo(resp.getOutputStream());
        
        /*for (Cookie cookie : cookieStore.getCookies())
        {
            System.out.println(cookie.getName() + ":" + cookie.getValue());
        }*/
    }
}
