package com.huawei.tdt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan(basePackages = {"com.huawei.tdt"}) // Fix the issue that can not find the component defined in the other jars.
@EnableFeignClients(basePackages = {"com.huawei.tdt"})
public class TdtGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdtGatewayServiceApplication.class, args);
	}
	
/*	@Bean
	public AccessAuthenticateFilter accessAuthenticateFilter() {
		return new AccessAuthenticateFilter();
	}
	
	@Bean
	public AccessAuthorizeFilter accessAuthorizeFilter() {
		return new AccessAuthorizeFilter();
	}*/
}
