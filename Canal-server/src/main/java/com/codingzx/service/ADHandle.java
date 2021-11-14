package com.codingzx.service;

import com.codingzx.entity.AdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author codingzx
 * @description  接收Canal消息的处理类
 * @date 2021/11/6 10:45
 */
@Component
public class ADHandle implements EntryHandler<AdEntity> {
    @Autowired
    private ADService adService;

    @Override
    public void insert(AdEntity adEntity) {
        adService.modify(adEntity);
    }

    @Override
    public void update(AdEntity before, AdEntity after) {
        adService.modify(after);
    }

    @Override
    public void delete(AdEntity adEntity) {
        adService.delete(adEntity.getId());
    }
}