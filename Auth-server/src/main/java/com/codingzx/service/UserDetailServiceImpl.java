package com.codingzx.service;

import com.codingzx.dao.UserRepository;
import com.codingzx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author codingzx
 * @description
 * @date 2021/11/6 13:04
 */
@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取本地用户
        User user = userRepository.queryByUserName(userName);
        if (user != null) {
            //返回oauth2的用户
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPasswd(),
                    AuthorityUtils.createAuthorityList(user.getPasswd()));
        } else {
            throw new UsernameNotFoundException("用户[" + userName + "]不存在");
        }
    }
}
