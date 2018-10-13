package com.huawei.tdt.codeactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.huawei.tdt.common.util.SpringUtil;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = { "com.huawei.tdt.common.mapper", "com.huawei.tdt.codeactivity.mapper" })
@ComponentScan(basePackages = { "com.huawei.allinone.common", "com.huawei.tdt.common.config.quartz",
		"com.huawei.tdt.common.config.redis", "com.huawei.tdt.common.config.servlet", "com.huawei.tdt.common.config.swagger",
		"com.huawei.tdt.common.authorization",
		"com.huawei.tdt.codeactivity" }) // Fix the issue that can not find the
											// component defined in the other
											// jars.
@Import(value = { SpringUtil.class })
@ServletComponentScan(basePackages = "com.huawei.tdt.codeactivity.servlet")
public class TdtCodeActivityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdtCodeActivityServiceApplication.class, args);
	}
}
