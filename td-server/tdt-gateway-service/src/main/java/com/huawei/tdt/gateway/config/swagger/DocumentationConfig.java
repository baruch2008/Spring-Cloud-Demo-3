package com.huawei.tdt.gateway.config.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * DocumentationConfig.java
 * @author lWX537094
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider
{
    private final RouteLocator routeLocator;

    /**
     * 构造函数
     * @param routeLocator RouteLocator
     */
    public DocumentationConfig(RouteLocator routeLocator)
    {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get()
    {
        List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> 
        {
            if (!route.getId().endsWith("-service"))
            {
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0"));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version)
    {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
