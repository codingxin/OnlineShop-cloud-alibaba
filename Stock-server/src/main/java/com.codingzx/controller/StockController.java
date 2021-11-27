package com.codingzx.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.codingzx.dao.StockResposity;
import com.codingzx.entity.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.event.NamingEvent;
import java.awt.*;
import java.util.EventListener;
import java.util.Properties;
import java.util.Random;

/**
 * @author codingzx
 * @description
 * @date 2021/8/14 15:17
 */

@RequestMapping("/stock")
@RestController
public class StockController {

    // http://stock-server/stock/remove?productId=
    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private Random random;

    @Autowired
    private StockResposity resposity;

    @GetMapping("/remove/{productId}")
    public String reduceStore(@PathVariable Integer productId) throws NacosException {
        System.out.println("该节点接收到请求");


        return "商品：" + productId + "已被移除";
    }


    /**
     * 扣减库存
     * @param productId
     * @return
     */
    @GetMapping("/stock/reduce/{productId}")
    public String reduce(@PathVariable Integer productId) {
//        LOGGER.info("Storage Service Begin ... xid: " + RootContext.getXID());
        if (random.nextBoolean()) {
            throw new RuntimeException("this is a mock Exception");
        }
        Stock stock = resposity.getFirstByProductId(productId);
        if (stock != null) {
            stock.setCount(stock.getCount() - 1);
            resposity.save(stock);
            return "success";
        }
        return "fail";
    }


}
