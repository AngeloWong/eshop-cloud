package com.angelo.eshop.price;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EshopPriceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopPriceServiceApplication.class, args);
    }

}
