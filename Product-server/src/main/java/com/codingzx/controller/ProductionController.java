package com.codingzx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品服务
 */
@RestController
public class ProductionController {
    @GetMapping("/product/{productId}")
    public String getProduct(@PathVariable  Integer productId){
        System.out.println("调用商品服务");
        return "IPHONE 12";
    }
}
