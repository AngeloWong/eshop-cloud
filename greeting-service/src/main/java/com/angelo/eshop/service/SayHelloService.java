package com.angelo.eshop.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("say-hello-service")
public interface SayHelloService {

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String name);
}
