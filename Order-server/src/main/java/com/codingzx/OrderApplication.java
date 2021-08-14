package com.codingzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:53
 */
@SpringBootApplication
public class OrderApplication {


    @Bean
//    @LoadBalanced
    public RestTemplate create() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

}
