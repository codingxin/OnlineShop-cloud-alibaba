package com.codingzx.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author codingzx
 * @description
 * @date 2021/11/27 13:31
 */
@Entity
@Table(name = "order_tb")
@Data
public class Order implements Serializable {
    public Order() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 原价格
     */
    @Column(name = "user_id")
    private Integer userId;

}