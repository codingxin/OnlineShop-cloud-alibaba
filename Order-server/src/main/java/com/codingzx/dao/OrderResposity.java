package com.codingzx.dao;

import com.codingzx.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author codingzx
 * @description
 * @date 2021/11/27 13:31
 */
@Repository
public interface OrderResposity extends JpaRepository<Order, Integer> {

}
