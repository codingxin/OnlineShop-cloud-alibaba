package com.codingzx.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.event.NamingEvent;
import java.awt.*;
import java.util.EventListener;
import java.util.Properties;

/**
 * @author codingzx
 * @description
 * @date 2021/8/14 15:17
 */

@RequestMapping("/stock")
@RestController
public class StockController {

    // http://stock-server/stock/remove?productId=

    @GetMapping("/remove/{productId}")
    public String reduceStore(@PathVariable Integer productId) throws NacosException {
        System.out.println("该节点接收到请求");


        return "商品：" + productId + "已被移除";
    }

}
