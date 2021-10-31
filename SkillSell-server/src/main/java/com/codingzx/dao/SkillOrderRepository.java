package com.codingzx.dao;

import com.codingzx.entity.SkillOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author codingzx
 * @description
 * @date 2021/10/30 16:14
 */
@Repository
public interface SkillOrderRepository extends JpaRepository<SkillOrder,Long> {
}