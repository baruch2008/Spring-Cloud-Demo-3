package com.huawei.tdt.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 访问授权过滤器，处理用户登录.
 *
 * @author lWX537094
 */
public class AccessAuthorizeFilter extends ZuulFilter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessAuthorizeFilter.class);

    @Value("${tdt.isNeedAuthFilter}")
    private boolean isNeedAuthFilter = true;

    /**
     * 是否应该对当前上下文中的请求做过滤.
     *
     * @return true：过滤；false：不过滤
     */
    public boolean shouldFilter()
    {
        if (!isNeedAuthFilter)
        {
            return false;
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        String path = ctx.getRequest().getRequestURI();

        // 请求路径为/auth/login则处理，其它的不处理
        if (null != path && path.startsWith("/auth/login"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 执行过滤.
     *
     * @return 过滤的执行结果
     */
    public Object run()
    {

        RequestContext ctx = RequestContext.getCurrentContext();

        if (isSuccessLogin(ctx))
        {
            LOGGER.info("Login successed.");
        }

        return null;
    }

    @Override
    public String filterType()
    {
        return "post";
    }

    @Override
    public int filterOrder()
    {
        return 1;
    }

    /**
     * 是否登录成功.
     *
     * @param ctx 当前上下文
     * @return true：成功；false：失败
     */
    private boolean isSuccessLogin(final RequestContext ctx)
    {
        RibbonApacheHttpResponse ribbonResp = (RibbonApacheHttpResponse) ctx.get("ribbonResponse");
        String isSuccess = ribbonResp.getHttpHeaders().getFirstValue("isSuccess");
        if (HttpServletResponse.SC_OK != ribbonResp.getStatus())
        {
            return false;
        }

        if ("true".equals(isSuccess))
        {
            return true;
        }

        return false;
    }
}
