package com.angelo.eshop.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class EshopOneServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopOneServiceApplication.class, args);
    }

}
