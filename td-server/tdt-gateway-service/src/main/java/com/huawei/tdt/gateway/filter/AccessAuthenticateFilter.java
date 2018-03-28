package com.huawei.tdt.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.huawei.tdt.common.authorization.manager.TokenManager;
import com.huawei.tdt.common.authorization.model.Token;
import com.huawei.tdt.common.constants.Constants;
import com.huawei.tdt.common.util.CommonUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 对除静态资源访问、授权请求之外的请求进行鉴权.
 *
 * @author lWX537094
 */
public class AccessAuthenticateFilter extends ZuulFilter
{
    /**
     * 规避“魔数”.
     */
    private static final int HTTP_CODE_NOT_LOGIN = 401;

    /**
     * token管理员bean.
     */
    @Autowired
    private TokenManager tokenMgr;

    @Value("${tdt.isNeedAuthFilter}")
    private boolean isNeedAuthFilter;

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

        // 请求路径以/web打头的表示请求web服务,访问静态资源,如html、JS等,不进行过滤
        if (null == path || path.startsWith("/web") || path.startsWith("/auth/login") || path.endsWith("v2/api-docs"))
        {
            return false;
        }

        return true;
    }

    /**
     * 执行过滤.
     *
     * @return 过滤的执行结果
     */
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String authentication = CommonUtil.getValue(request, Constants.AUTHORIZATION);
        if (null == authentication)
        {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HTTP_CODE_NOT_LOGIN);
            ctx.set("isSuccess", false);
            ctx.setResponseBody("{\"result\":\"Not login!\"}");
        }
        else
        {
            Token token = tokenMgr.getToken(authentication);
            boolean result = tokenMgr.checkToken(token);
            if (result)
            {
                request.setAttribute(Constants.CURRENT_USER_ID, token.getUserId());
            }
            else
            {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HTTP_CODE_NOT_LOGIN);
                ctx.set("isSuccess", false);
                ctx.setResponseBody("{\"result\":\"Not login!\"}");
            }
        }

        return null;
    }

    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return Constants.TWO;
    }

}
