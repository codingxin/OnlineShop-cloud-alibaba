//package com.codingzx.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//
//    // 根据不同数据源注入  jdbc   redis等
//    @Autowired
//    private DataSource dataSource;
//
//
//    @Autowired
//    private AccessManager accessManager;
//
//
//    // 责任链
//    @Bean
//    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
//        //token管理器     jdbctoken redistoken
//        ReactiveAuthenticationManager tokenAuthenticationManager = new ReactiveJdbcAuthenticationManager(new JdbcTokenStore(dataSource));
//        //认证过滤器
//        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
//        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
//
//        http.httpBasic().disable()
//                // 禁用csrf
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyExchange().access(accessManager)
//                .and()
//                // 跨域过滤器
//                //oauth2认证过滤器
//                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
//        return http.build();
//    }
//}
