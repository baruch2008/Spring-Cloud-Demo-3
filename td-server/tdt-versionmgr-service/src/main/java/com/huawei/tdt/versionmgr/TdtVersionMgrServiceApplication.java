package com.huawei.tdt.versionmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.huawei.tdt"})
public class TdtVersionMgrServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TdtVersionMgrServiceApplication.class, args);
	}
}
