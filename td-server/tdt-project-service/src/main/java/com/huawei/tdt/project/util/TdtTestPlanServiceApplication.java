package com.huawei.tdt.testplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TdtTestPlanServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TdtTestPlanServiceApplication.class, args);
	}
}
