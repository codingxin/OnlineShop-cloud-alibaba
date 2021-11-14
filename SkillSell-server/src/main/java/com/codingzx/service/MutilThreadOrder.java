package com.codingzx.service;


import com.codingzx.dao.SkillOrderRepository;
import com.codingzx.entity.SkillGoods;
import com.codingzx.entity.SkillOrder;
import com.codingzx.entity.SkillRedisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.codingzx.service.SkillGoodService.SKILL_GOODS_ONLY;
import static com.codingzx.service.SkillGoodService.SKILL_GOODS_QUEUE;


@Component
public class MutilThreadOrder {
    @Autowired
    private ProductService productService;
    @Autowired
    private SkillOrderRepository skillOrderRepository;
    @Autowired
    private RedisTemplate redisTemplate;




    @Async
    public void createOrder() throws Exception {
        System.out.println("开始异步抢单");
        // 1.从内存中获取 用户信息和商品信息
        SkillRedisEntity skillEntity = (SkillRedisEntity) redisTemplate.boundListOps(SkillGoodService.SKILL_GOODS_LIST).rightPop();
        System.out.println("从redis序列化的实体为：" + skillEntity);
        if (skillEntity == null) {
            return;
        }
        Long productId = skillEntity.getProductId();
        String userId = skillEntity.getUserId();
//        Thread.sleep(10000);


        // 2.从内存中获取商品信息
        SkillGoods skillGood = productService.getGoodById(productId);
        if (skillGood == null) {
            throw new Exception("商品已经被抢光拉");
        }

        // 3.从内存中获取商品库存信息  rightPop同时会删减内存
        Long stockId = (Long) redisTemplate.boundListOps(SKILL_GOODS_QUEUE + productId).rightPop();
        if (stockId == null) {
            System.out.println("该商品已被秒杀完毕");
            redisTemplate.boundHashOps(SKILL_GOODS_ONLY).delete(userId);
            redisTemplate.boundHashOps(SkillGoodService.SKILL_GOODS_PHONE).delete(skillGood.getId());
            skillGood.setStockCount(0);
            productService.update(skillGood);
            return;
        }

        // 4.保存订单信息入库
        SkillOrder skillOrder = new SkillOrder();
        skillOrder.setMoney(skillGood.getCostPrice());
        skillOrder.setPayTime(new Date());
        skillOrder.setStatus("0");
        skillOrder.setUserId(userId);
        skillOrder.setCreateTime(new Date());
        skillOrder.setSkillId(productId);
        skillOrderRepository.save(skillOrder);


        // 5.更新商品信息内存   扣减库存
        skillGood.setStockCount(skillGood.getStockCount() - 1);
        redisTemplate.boundHashOps(SkillGoodService.SKILL_GOODS_PHONE).put(skillGood.getId(), skillGood);
        System.out.println("成功秒杀 剩余库存：" + skillGood.getStockCount());

        // 7.更新商品信息入库
        productService.update(skillGood);
        System.out.println("结束异步抢单");
    }
}
