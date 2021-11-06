package com.codingzx.controller;

import com.codingzx.dao.UserRepository;
import com.codingzx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author codingzx
 * @description
 * @date 2021/11/6 13:09
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    public UserRepository userRepository;


    @GetMapping("getByName")
    public User getByName(){
        return userRepository.queryByUserName("ziya");
    }

    /**
     * 获取授权的用户信息
     * @param principal 当前用户
     * @return 授权信息
     */
    @GetMapping("current/get")
    public Principal user(Principal principal){
        return principal;
    }
}
