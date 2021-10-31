package com.codingzx.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestOriginParserDefinition implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 每个请求都会校验参数中是否包含productId 如果不包含则抛异常
        if(httpServletRequest.getRequestURI().startsWith("/hot")){
            String name = httpServletRequest.getParameter("productId");
            if(StringUtils.isEmpty(name)){
                throw new RuntimeException("productId is null");
            }
            return name;
        }
        return "";
    }
}
