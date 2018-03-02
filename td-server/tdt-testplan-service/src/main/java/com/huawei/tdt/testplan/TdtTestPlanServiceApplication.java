package com.huawei.tdt.testplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.huawei.tdt"})
public class TdtTestPlanServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TdtTestPlanServiceApplication.class, args);
	}
}