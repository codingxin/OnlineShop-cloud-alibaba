package com.codingzx.service;

import com.codingzx.dao.SkillGoodRepository;
import com.codingzx.entity.SkillGoods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class SkillGoodService {

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String SKILL_GOODS_PHONE = "SKILL_GOODS_PHONE";
    public static final String SKILL_GOODS_QUEUE = "SKILL_GOODS_QUEUE";

    @Autowired
    private SkillGoodRepository skillGoodRepository;

    /**
     * 每五秒执行一次 将需要参与秒杀的商品列表加载到内存
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void prepareGood() {
        System.out.println("开始加载商品");
        //获取所有已经在内存当中的商品ID列表
        Set<Long> set = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        List<Long> ids = new ArrayList<>();
        for (Long id : set) {
            ids.add(id);
        }
        List<SkillGoods> list = null;
        //只查询出 不在内存当中的商品信息，并加载到内存
        if (CollectionUtils.isEmpty(ids)) {
            list = skillGoodRepository.findSkillAll();
        } else {
            list = skillGoodRepository.findSkill(ids);
        }
        // 需要新增的信息
        if (!CollectionUtils.isEmpty(list)) {
            for (SkillGoods skillGood : list) {
                // 商品信息
                redisTemplate.boundHashOps(SKILL_GOODS_PHONE).put(skillGood.getId(), skillGood);
                // 商品库存信息
                redisTemplate.boundListOps(SKILL_GOODS_QUEUE + skillGood.getId()).leftPushAll(
                        convertToArry(skillGood.getStockCount(), skillGood.getId()));
            }
        }
        // 查看当前缓存中所有的商品信息
        Set keys = redisTemplate.boundHashOps(SKILL_GOODS_PHONE).keys();
        for (Object s : keys) {
            SkillGoods skillGood = (SkillGoods) redisTemplate.boundHashOps(SKILL_GOODS_PHONE).get(s);
            System.out.println(skillGood.getName() + " 库存剩余：" + skillGood.getStockCount());
        }
    }

    private Long[] convertToArry(Integer stockCount, Long id) {
        // 商品id为 255 ，库存为 10  则 数组为 255 255 255  ..... 10个 255
        Long[] idsLong = new Long[stockCount];
        Arrays.fill(idsLong, id);
        return idsLong;
    }

    // 提供查询商品信息的方法
    public SkillGoods queryProduct(Long productId) {
        return (SkillGoods) redisTemplate.boundHashOps(SKILL_GOODS_PHONE).get(productId);
    }

    public void update(SkillGoods skillGood) {
        skillGoodRepository.save(skillGood);
    }
}