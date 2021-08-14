package com.codingzx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:56
 */
@RestController
@RequestMapping("/test")
public class TestController {





    @GetMapping("/hello")
    public String testHello(String name, Integer id) {

        return "name is " + name + " , id " + id;

    }

}
