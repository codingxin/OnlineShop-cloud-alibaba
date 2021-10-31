package com.codingzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ShopCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCartApplication.class);
    }
}
