package com.abnamro.assignment.recipeapp;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productAPI() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/v1.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Recipe assignment")
                .description("Recipe API reference for developers")
                .termsOfServiceUrl("https://recipeAndHealth.org/terms")
                .contact(new Contact("Recipe assignment", "https://recipeAndHealth.org", "mraheja2@gmail.com"))
                .license("ABN Amro Assignmeent")
                .licenseUrl("https://google.com").version("1.0").build();
    }
}
