package com.huawei.tdt.testplan.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 此文件仅是为了配置访问塔台数据库的需要
 * @author lWX537094
 */
@Configuration
@MapperScan(basePackages = { "com.huawei.etlapi.tmss.mapper" }, sqlSessionFactoryRef = "sqlSessionFactoryForEtlApi")
public class EtlApiTmssMapperScanConfig
{

}
