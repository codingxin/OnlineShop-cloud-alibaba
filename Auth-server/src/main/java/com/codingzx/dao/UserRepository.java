package com.codingzx.dao;

import com.codingzx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author codingzx
 * @description
 * @date 2021/11/6 13:03
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User queryByUserName(String userName);

}