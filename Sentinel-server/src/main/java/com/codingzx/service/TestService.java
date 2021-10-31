package com.codingzx.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class TestService {


    // 设置了 test  链路模式  入口资源为  test2
    @SentinelResource("test")
    public void test(){
        System.out.println("test");
    }
}
