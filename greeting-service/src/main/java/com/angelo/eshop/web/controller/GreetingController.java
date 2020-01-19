package com.angelo.eshop.web.controller;

import com.angelo.eshop.service.GreetService;
import com.angelo.eshop.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Value("${server.port}")
    String port;

    /**
     * ribbon+rest是比较底层的调用方式，其实一般不常用
     */
//    @Autowired
//    private GreetService greetingService;
//
//    @RequestMapping(value = "/greeting")
//    public String greeting(@RequestParam String name){
//        return greetingService.greeting(name);
//    }


    /**
     * fegion，声明式的服务调用，类似于rpc风格的服务调用，默认集成了ribbon做负载均衡，集成eureka做服务发现
     */
    @Autowired
    private SayHelloService sayHelloService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam String name){
        String result = sayHelloService.sayHello(name);
        result += ", through greeting service from port: " + port;
        return result;
    }
}
