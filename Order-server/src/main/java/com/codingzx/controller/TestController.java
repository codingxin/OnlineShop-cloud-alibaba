package com.codingzx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author codingzx
 * @description
 * @date 2021/8/12 20:56
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/order")
    public String getStock() {
        List<ServiceInstance> list = discoveryClient.getInstances("stock-server");
        String a="";
        for(ServiceInstance serviceInstance:list){
            a=a+" "+serviceInstance.getHost();
        }
        return a;
    }


    @GetMapping("/hello")
    public String testHello(String name, Integer id) {

        return "name is " + name + " , id " + id;

    }

}
