package com.codingzx.controller;


import org.springframework.web.bind.annotation.*;


/**
 * 商品服务
 */
@RestController
public class ConfigController {


    @GetMapping("/config/")
    @ResponseBody
    public String getProduct(@PathVariable Long productId) {
        return "这是config服务";
    }


}
