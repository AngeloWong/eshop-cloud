package com.angelo.eshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class SayHelloServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SayHelloServiceApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/sayHello")
    public String home(@RequestParam String name) {
        return "hello, " + name + " from port:" + port;
    }
}
