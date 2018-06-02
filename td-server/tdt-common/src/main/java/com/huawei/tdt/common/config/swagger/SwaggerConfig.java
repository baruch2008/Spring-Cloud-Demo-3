package com.huawei.tdt.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket testApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*"))) // 屏蔽默认的basic-error-controller
                .build()
                .apiInfo(testApiInfo());
    }
    
    private ApiInfo testApiInfo()
    {
        return new ApiInfoBuilder().title("TDT API Interfaces")
                .build();
    }
}
