package com.codingzx.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author codingzx
 * @description
 * @date 2021/10/30 16:13
 */
@Entity
@Table(name = "skill_order")
@Data
public class SkillOrder implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 秒杀商品ID
     */
    @Column(name = "skill_id")
    private Long skillId;

    /**
     * 支付金额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 用户
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    private static final long serialVersionUID = 1L;

}