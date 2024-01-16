package com.example.teamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
@EnableHystrix
//@EnableHystrixDashboar
//j'ai mit le @EnableHystrixDashboar en commentaire car ça plante le projet à cause de version de java/springboot

public class TeamServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamServiceApplication.class, args);
    }

}
