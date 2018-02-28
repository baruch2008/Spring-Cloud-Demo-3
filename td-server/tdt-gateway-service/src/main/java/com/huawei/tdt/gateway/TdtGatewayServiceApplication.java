package com.huawei.tdt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.huawei.tdt.gateway.filter.AccessAuthenticateFilter;
import com.huawei.tdt.gateway.filter.AccessAuthorizeFilter;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages = {"com.huawei"}) // Fix the issue that can not find the component defined in the other jars.
public class TdtGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdtGatewayServiceApplication.class, args);
	}
	
	@Bean
	public AccessAuthenticateFilter accessAuthenticateFilter() {
		return new AccessAuthenticateFilter();
	}
	
	@Bean
	public AccessAuthorizeFilter accessAuthorizeFilter() {
		return new AccessAuthorizeFilter();
	}
}
