package com.huawei.tdt.synchronize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.huawei.tdt.common.util.SpringUtil;

/**
 * 同步服务的应用程序主类.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = { "com.huawei.tdt" })
@EnableTransactionManagement
@Import(value = { SpringUtil.class })
public class TdtSynchronizeServiceApplication
{
    /**
     * 同步服务主函数.
     *
     * @param args 字符串参数
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(TdtSynchronizeServiceApplication.class, args);
    }
}
