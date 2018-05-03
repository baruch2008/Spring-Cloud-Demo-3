package com.huawei.tdt.common.config.db;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置.
 */
@Configuration
public class DataSourceConfig
{
    /**
     * 创建数据源.
     *
     * @return 数据源对象
     */
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "tdt.dbcp")
    public DataSource tdtDataSource()
    {
        return DataSourceBuilder.create().type(org.apache.commons.dbcp2.BasicDataSource.class).build();
    }
    
    @Bean(name = "etlApiDataSource")
    @ConfigurationProperties(prefix = "etlapi.dbcp")
    public DataSource etlapiDataSource()
    {
        return DataSourceBuilder.create().type(org.apache.commons.dbcp2.BasicDataSource.class).build();
    }

    /**
     * 创建sql会话工厂.
     *
     * @return sql会话工厂bean
     * @throws Exception 异常
     */
    @Bean(name="sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(tdtDataSource());
        return bean.getObject();
    }
    
    /**
     * 创建会话模板
     * @return SqlSessionTemplate
     * @throws Exception 异常
     */
    @Bean(name="sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory()); 
        return template;
    }
    
    /**
     * 创建用于连接etlapi服务数据库的sql会话工厂.
     *
     * @return sql会话工厂bean
     * @throws Exception 异常
     */
    @Bean(name="sqlSessionFactoryForEtlApi")
    public SqlSessionFactory sqlSessionFactoryForEtlApi() throws Exception
    {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(etlapiDataSource());

        return factoryBean.getObject();

    }

    /**
     * 创建用于连接etlapi服务数据库的会话模板
     * @return SqlSessionTemplate
     * @throws Exception 异常
     */
    @Bean(name="sqlSessionTemplateForEtlApi")
    public SqlSessionTemplate sqlSessionTemplateForEtlApi() throws Exception
    {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactoryForEtlApi());
        return template;
    }
}
