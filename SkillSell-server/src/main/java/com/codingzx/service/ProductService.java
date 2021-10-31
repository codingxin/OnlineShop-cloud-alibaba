package com.codingzx.service;

import com.codingzx.entity.SkillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author codingzx
 * @description
 * @date 2021/10/30 16:15
 */
@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    public SkillGoods getGoodById(Long productId) {
        return restTemplate.getForObject("http://product-service/product/" + productId, SkillGoods.class);
    }

    public void update(SkillGoods skillGood) {
        ResponseEntity<String> result = restTemplate.postForEntity("http://product-service/product/", skillGood, String.class);
        System.out.println(result.getBody());
    }




}
