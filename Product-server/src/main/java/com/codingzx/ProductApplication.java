package com.codingzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:53
 */
@SpringBootApplication
@EnableScheduling
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }



}
