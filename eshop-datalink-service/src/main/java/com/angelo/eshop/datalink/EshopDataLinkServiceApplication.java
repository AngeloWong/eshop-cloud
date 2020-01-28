package com.angelo.eshop.datalink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class EshopDataLinkServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopDataLinkServiceApplication.class, args);
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000 * 10);
        config.setTestOnBorrow(true);
        // 连接twemproxy设置的redis主集群
        return new JedisPool(config, "10.0.3.11", 1111);
//        return new JedisPool(config, "localhost", 6379);
    }
}
