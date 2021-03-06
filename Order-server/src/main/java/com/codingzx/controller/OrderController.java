package com.codingzx.controller;

import com.codingzx.dao.OrderResposity;
import com.codingzx.entity.Order;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


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
    public String createOrderTmp(Integer userId, Integer productId) {
        String productName = restTemplate.getForObject("http://127.0.0.1:9000/product/" + productId, String.class);

        String userName = restTemplate.getForObject("http://127.0.0.1:10000/user/" + userId, String.class);
        String result = restTemplate.getForObject("http://127.0.0.1:11000/stock/reduct/" + productId, String.class);
        String shopCartResult = restTemplate.getForObject("http://127.0.0.1:12000/shopcart/remove?productId=" + productId, String.class);

        return "user:" + userName + "购买了商品" + productName + "，" + result + "" + shopCartResult;
    }


    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private OrderResposity orderResposity;

    @GetMapping("/order")
    public String getStock() {
        List<ServiceInstance> list = discoveryClient.getInstances("stock-service");
        String a = "";
        for (ServiceInstance serviceInstance : list) {
            a = a + " " + serviceInstance.getHost();
        }
        return a;
    }



    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GetMapping("/order/create")
    public String createOrder(Integer productId, Integer userId) {
        /**
         * 分布式事务seata 测试
         * 订单接口  先保存订单
         * 调用扣减库存服务接口
         *  如果发生异常，订单回滚
         *  否则扣减库存
         *
         *
         *
         */
        Order order = new Order();
        order.setProductId(productId);
        order.setUserId(userId);
        orderResposity.save(order);
        String result = restTemplate.getForObject("http://stock-service/stock/stock/reduce/" + productId, String.class);
        if (!result.equals("success")) {
            throw new RuntimeException();
        }
        return result;
    }



}
