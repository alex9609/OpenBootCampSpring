package com.example.ejecicios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/*
    http://localhost:8081/swagger-ui/
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public  Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())//Para indicar que queremos que construya sobre todas las rutas que tenemos
                .paths(PathSelectors.any()) //Indicamos que queremos todos los paths
                .build(); //Hace que se construya todo
    }


    private ApiInfo apiDetails(){
        return new ApiInfo("Spring boot book API REST",
                "Library Api rest docs","1.0",
                "http://www.google.com",
                new Contact("Pepe Rodriguez","http://www.google.com","ejemplo@gmail.com"),
                "MIT",
                "http://www.google.com",
                Collections.EMPTY_LIST);
    }

}
