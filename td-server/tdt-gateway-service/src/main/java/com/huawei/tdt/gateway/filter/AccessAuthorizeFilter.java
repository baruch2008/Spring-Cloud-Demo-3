package com.huawei.tdt.gateway.filter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 访问授权过滤器，处理用户登录
 * 
 * @author lWX537094
 *
 */
public class AccessAuthorizeFilter extends ZuulFilter {
	@Value("${app.product.cookiename:DEFAULTSESSION}")
	private String cookieName;

	@Value("${app.product.session.timeout:30}")
	private int sessionTimeoutInMinuts;

	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String path = ctx.getRequest().getRequestURI();

		// 请求路径为/auth/login则处理，其它的不处理
		if (null != path && (path.startsWith("/auth/login") || path.startsWith("project/login"))) {
			return true;
		}

		return false;
	}

	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();

		if (isSuccessLogin(ctx)) {
			// TODO
		}

		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	private boolean isSuccessLogin(RequestContext ctx) {
		RibbonApacheHttpResponse ribbonResp = (RibbonApacheHttpResponse) ctx.get("ribbonResponse");
		String isSuccess = ribbonResp.getHttpHeaders().getFirstValue("isSuccess");
		if (HttpServletResponse.SC_OK != ribbonResp.getStatus()) {
			return false;
		}

		if ("true".equals(isSuccess)) {
			return true;
		}

		return false;
	}
}
