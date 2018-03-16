package com.huawei.tdt.common.config.servlet;

/**
* CorsFilter.java
* @author lWX537094
*/
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 
 * 跨域过滤器
 * @author meng
 * @version
 * @since 2016年6月19日
 */
@Component
public class CorsFilter implements Filter
{
    /**
     * do Filter
     * @param req ServletRequest
     * @param res ServletResponse
     * @param chain FilterChain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Origin, X-Requested-With, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");

        if (request.getMethod().equals("OPTIONS"))
        {
            response.setStatus(HttpStatus.SC_OK);
            response.getWriter().write("OPTIONS returns OK");
            return;
        }

        chain.doFilter(req, res);
    }

    /**
     * Init
     * @param filterConfig FilterConfig
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig)
    {
    }

    /**
     * Destroy
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy()
    {
    }
}
