package com.codingzx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author ziya
 * @date 2021/2/20 3:58 下午
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product/create")
    public String createOrder(String userName,Integer productId) {
        String user = restTemplate.getForObject("http://127.0.0.1:9000/user/" + userName, String.class);

        String product = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);
        String stock = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);
        String result = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);

        return "user:" + user + "购买了商品" + product + "，" + stock + "";
    }
}
