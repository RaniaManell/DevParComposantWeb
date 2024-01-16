package com.example.statsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
//@EnableHystrixDashboar
//j'ai mit le @EnableHystrixDashboar en commentaire car ça plante le projet à cause de version de java/springboot

public class StatsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatsServiceApplication.class, args);
    }

}
