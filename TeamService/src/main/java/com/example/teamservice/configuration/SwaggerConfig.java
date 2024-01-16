package com.example.teamservice.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
// ça cause une erreur 1 donc je commente
//@EnableSwagger2
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
