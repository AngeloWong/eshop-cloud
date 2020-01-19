package com.angelo.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class HystrixTurbineServer {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineServer.class, args);
//        new SpringApplicationBuilder(HystrixTurbineServer.class).web(WebApplicationType.SERVLET).run(args);
    }
}
