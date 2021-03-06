package com.zooplus.orderManagementService.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    private static final String basePackage = "com.zooplus.orderManagementService.controller";


    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage(basePackage))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
            "Zooplus Order Management Service",
            "The service is to generate endpoints for order management",
            "API Version",
            "Terms of service",
            new Contact("benimadhabkayal", "https://github.com/code-bmk/order-management-system", "benimadhab11@gmail.com"),
            "License of API",
            "License URL",
            Collections.emptyList());
        return apiInfo;
    }
}
