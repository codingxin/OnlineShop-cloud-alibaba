package com.codingzx.controller;

import com.codingzx.entity.SkillGoods;
import com.codingzx.service.SkillGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 商品服务
 */
@RestController
public class ProductionController {

    @Autowired
    private SkillGoodService skillGoodService;


    @GetMapping("/product/{productId}")
    @ResponseBody
    public SkillGoods getProduct(@PathVariable Long productId) {
        System.out.println("调用商品服务");
        SkillGoods skillGood = skillGoodService.queryProduct(productId);
        return skillGood;
    }

    @PostMapping("/product")
    public String update(@RequestBody SkillGoods skillGood) {
        System.out.println("更新商品库存");
        skillGoodService.update(skillGood);
        return "更新库存成功";
    }


}
