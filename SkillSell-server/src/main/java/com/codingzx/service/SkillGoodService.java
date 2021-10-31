package com.codingzx.service;



import com.codingzx.entity.SkillRedisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class SkillGoodService {

    // 存储商品信息
    public static final String SKILL_GOODS_PHONE = "SKILL_GOODS_PHONE";
    // 存储商品数量list
    public static final String SKILL_GOODS_LIST = "SKILL_GOODS_LIST";
    public static final String SKILL_GOODS_ONLY = "SKILL_GOODS_ONLY";
    // 存储商品库存信息  SKILL_GOODS_QUEUE+id
    public static final String SKILL_GOODS_QUEUE = "SKILL_GOODS_QUEUE";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MutilThreadOrder mutilThreadOrder;

    @Transactional
    public void skill(Long productId, String userId) throws Exception {
        //判断这个用户是否参加过抢单
        Long time = redisTemplate.boundHashOps(SKILL_GOODS_ONLY).increment(userId, 1L);
//        if (time > 1) {
//            throw new Exception("重复抢单，不要太贪心");
//        }

        // 先封装对象 并且放入redis 队列
        SkillRedisEntity skillEntity = new SkillRedisEntity();
        skillEntity.setProductId(productId);
        skillEntity.setUserId(userId);
        // 把 用户信息 和商品信息
        redisTemplate.boundListOps(SKILL_GOODS_LIST).leftPush(skillEntity);
        mutilThreadOrder.createOrder();

    }
}
