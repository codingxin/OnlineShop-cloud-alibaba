package com.codingzx.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void init(){
        WebCallbackManager.setRequestOriginParser(new RequestOriginParserDefinition());
    }
}
