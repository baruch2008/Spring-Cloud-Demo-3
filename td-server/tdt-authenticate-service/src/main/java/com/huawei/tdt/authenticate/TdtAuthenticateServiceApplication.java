package com.huawei.tdt.authenticate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.huawei.tdt.common.util.SpringUtil;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.huawei.tdt.common.mapper", "com.huawei.tdt.authenticate.mapper"})
@ComponentScan(basePackages = {"com.huawei.tdt"}) // Fix the issue that can not find the component defined in the other jars.
@Import(value={SpringUtil.class})
@ServletComponentScan(basePackages = "com.huawei.tdt.authenticate.servlet")
public class TdtAuthenticateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdtAuthenticateServiceApplication.class, args);
	}
}
