package com.tcs.employees.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/*
* This class holds configuration related to Swagger2
* */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

/*
    The following implementation will document API's inside  "com.tcs.employees" package
*/
    @Bean
    public Docket swaggerConfig() {
        //return prepared docket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.tcs.employees"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Employee CRUD API",
                "Sample REST API for interview purpose",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Tushar Thakur", "http://test_url", "test@gmail.com"),
                "API license",
                "http://test_license_url",
                Collections.emptyList()
        );
    }
}
