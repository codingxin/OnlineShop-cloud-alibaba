package com.codingzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:53
 */
@SpringBootApplication
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class);
    }

    @Bean
    public Random generate(){
        return new Random();
    }


}
