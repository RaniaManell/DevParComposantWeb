package com.example.playerservice.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
    @Configuration
    //J'ai mit l'annotation en commentaire car ça plante le TP
   // @EnableSwagger2
    public class SwaggerConfig {
/*
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.playerservice.controller"))
                    .build();
        }*/
    }


