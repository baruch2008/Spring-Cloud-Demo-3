package com.huawei.tdt.gateway.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.huawei.tdt.common.authorization.manager.TokenManager;
import com.huawei.tdt.common.authorization.model.Token;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 对除静态资源访问、授权请求之外的请求进行鉴权
 * 
 * @author lWX537094
 *
 */
public class AccessAuthenticateFilter extends ZuulFilter {

	@Autowired
	private TokenManager tokenMgr;

	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String path = ctx.getRequest().getRequestURI();

		// 请求路径以/web打头的表示请求web服务,访问静态资源,如html、JS等,不进行过滤
		if (null == path || path.startsWith("/web") || path.startsWith("/auth/login") || path.endsWith("v2/api-docs")) {
			return false;
		}

		return true;
	}

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		Cookie[] cookies = request.getCookies();
		if (null == cookies || 0 == cookies.length) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.set("isSuccess", false);
			ctx.setResponseBody("{\"result\":\"Not login!\"}");
			return null;
		} else {
			String token = null;
			String userId = null;
			for (Cookie cookie : cookies) {
				if ("authorization".equals(cookie.getName())) {
					token = cookie.getValue();
				} else if ("userId".equals(cookie.getName())) {
					userId = cookie.getValue();
				}
			}
			
			if (null == token || null == userId) {
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				ctx.set("isSuccess", false);
				ctx.setResponseBody("{\"result\":\"Not login!\"}");
				return null;
			} else {
				Token model = new Token(userId, token);
				boolean result = tokenMgr.checkToken(model);
				if (!result) {
					ctx.setSendZuulResponse(false);
					ctx.setResponseStatusCode(401);
					ctx.set("isSuccess", false);
					ctx.setResponseBody("{\"result\":\"Not login!\"}");
					return null;
				}
			}
		}
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
