package com.angelo.eshop.service.fallback;

import com.angelo.eshop.service.SayHelloService;
import org.springframework.stereotype.Component;

@Component
public class SayHelloServiceFallback implements SayHelloService {
    @Override
    public String sayHello(String name) {
        return "error, " + name;
    }
}
