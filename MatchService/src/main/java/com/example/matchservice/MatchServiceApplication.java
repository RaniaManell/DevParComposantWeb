package com.example.matchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
//@EnableHystrixDashboar
//j'ai mit le @EnableHystrixDashboar en commentaire car ça plante le projet à cause de version de java/springboot

public class MatchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchServiceApplication.class, args);
    }

}
