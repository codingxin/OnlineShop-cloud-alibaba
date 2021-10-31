package com.codingzx.config;
import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Desc  配置类开放全部链路
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY,"false");
        registrationBean.setName("sentinelFilter");
        return registrationBean;
    }
}
