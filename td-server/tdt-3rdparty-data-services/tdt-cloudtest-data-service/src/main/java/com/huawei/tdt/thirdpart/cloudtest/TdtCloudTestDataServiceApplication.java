package com.huawei.tdt.thirdpart.cloudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.huawei.tdt.common.config.db", "com.huawei.tdt.common.config.swagger"})
public class TdtCloudTestDataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TdtCloudTestDataServiceApplication.class, args);
	}
}
