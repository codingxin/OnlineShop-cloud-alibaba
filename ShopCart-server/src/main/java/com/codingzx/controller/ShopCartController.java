package com.codingzx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2021/8/14 15:17
 */

@RequestMapping("/shopcart")
@RestController
public class ShopCartController {


    @GetMapping("/remove/{productId}")
    public String reduceStore(@PathVariable Integer productId) {
        return "商品：" + productId + "已被移除";
    }

}
