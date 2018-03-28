package com.huawei.tdt.gateway.config.swagger;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        List<Route> routes = routeLocator.getRoutes();
        List<SwaggerResource> resources = routes.stream().filter(new Predicate<Route>()
        {
            @Override
            public boolean test(Route route)
            {
                return !route.getId().endsWith("-service");
            }
        }).map(new Function<Route, SwaggerResource>()
        {
            @Override
            public SwaggerResource apply(Route route)
            {
                return swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "2.0");
            }
        }).collect(Collectors.toList());

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
