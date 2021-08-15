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
    public String createOrder(Integer productId) {
//        String productName = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);
//
//        String userName = restTemplate.getForObject("http://127.0.0.1:10000/user/" + userId, String.class);
//        String result = restTemplate.getForObject("http://127.0.0.1:11000/stock/reduct/" + productId, String.class);
        String shopCartResult = restTemplate.getForObject("http://stock-server/stock/remove/" + productId, String.class);

        return "结果为" + shopCartResult;
    }

    @GetMapping("/product/createTmp")
    public String createOrderTmp(Integer userId,Integer productId) {
        String productName = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);

        String userName = restTemplate.getForObject("http://127.0.0.1:10000/user/" + userId, String.class);
        String result = restTemplate.getForObject("http://127.0.0.1:11000/stock/reduct/" + productId, String.class);
        String shopCartResult = restTemplate.getForObject("http://127.0.0.1:12000/shopcart/remove?productId=" + productId, String.class);

        return "user:" + userName + "购买了商品" + productName + "，" + result + "" + shopCartResult;
    }

}
