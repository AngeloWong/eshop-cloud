package com.angelo.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class SleuthServer {

    public static void main(String[] args) {
        SpringApplication.run(SleuthServer.class, args);
    }
}
