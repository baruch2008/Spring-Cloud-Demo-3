package com.huawei.tdt.gateway.filter;

import com.huawei.tdt.gateway.rpc.IErrorCallTest;
import com.netflix.zuul.ZuulFilter;

//@Component
public class ErrorFilter extends ZuulFilter {

	//@Autowired
	private IErrorCallTest errorCall;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		try {
			errorCall.test();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
