package com.codingzx.controller;

import com.codingzx.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingzx
 * @description
 * @date 2021/8/14 15:17
 */


@RestController
public class SkillSellController {

    @Autowired
    private SkillGoodService skillGoodService;

//    @GetMapping("/remove/{productId}")
//    public String reduceStore(@PathVariable Integer productId) {
//        return "商品：" + productId + "已被移除";
//    }

    @GetMapping("/skill")
    public String reduceStore(String userId, Long productId) {
        try {
            skillGoodService.skill(productId, userId);
            return "用户: " + userId + "秒杀商品：" + productId + "已被秒杀";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
