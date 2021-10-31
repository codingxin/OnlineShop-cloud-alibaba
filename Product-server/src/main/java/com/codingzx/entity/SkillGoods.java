package com.codingzx.entity;

/**
 * @author codingzx
 * @description
 * @date 2021/10/30 16:20
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "skill_goods")
@Data
public class SkillGoods implements Serializable {

    public SkillGoods() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @Column(name = "name")
    private String name;

    /**
     * 原价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 秒杀价格
     */
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    /**
     * 审核状态
     */
    @Column(name = "status")
    private String status;


    /**
     * 秒杀商品数
     */
    @Column(name = "num")
    private Integer num;

    /**
     * 剩余库存数
     */
    @Column(name = "stock_count")
    private Integer stockCount;

    /**
     * 描述
     */
    @Column(name = "introduction")
    private String introduction;

    private static final long serialVersionUID = 1L;

}