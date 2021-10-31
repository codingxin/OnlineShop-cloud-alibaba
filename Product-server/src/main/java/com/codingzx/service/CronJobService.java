package com.codingzx.service;

/**
 * @author codingzx
 * @description
 * @date 2021/10/30 15:55
 */

import com.codingzx.dao.SkillGoodRepository;
import com.codingzx.entity.SkillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CronJobService {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String SKILL_GOODS_PHONE = "SKILL_GOODS_PHONE";

    @Autowired
    private SkillGoodRepository skillGoodRepository;

    /**
     * 每五秒执行一次 将需要参与秒杀的商品列表加载到内存
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void prepareGood() {
        System.out.println(LocalDate.now() + "开始加载商品");
        //获取所有已经在内存当中的商品ID列表
        Set<Long> set = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        List<Long> ids = new ArrayList<>();
        for (Long id : set) {
            ids.add(id);
        }
        List<SkillGoods> list = null;
        //只查询出不在内存当中的商品信息，并加载到内存
        if (CollectionUtils.isEmpty(ids)) {
            list = skillGoodRepository.findSkillAll();
        } else {
            list = skillGoodRepository.findSkill(ids);
        }
        if (!CollectionUtils.isEmpty(list)) {
            for (SkillGoods skillGood : list) {
                // redis 存储hash结构
                redisTemplate.boundHashOps(SKILL_GOODS_PHONE).put(skillGood.getId(), skillGood);
            }
        }
        // 查看当前缓存中所有的商品信息
        Set keys = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        for (Object s : keys) {
            SkillGoods skillGood = (SkillGoods) redisTemplate.boundHashOps(SKILL_GOODS_PHONE).get(s);
            System.out.println(skillGood.getName() + " 库存剩余：" + skillGood.getStockCount());
        }
    }


}