package com.codingzx.entity;

/**
 * @author codingzx
 * @description
 * @date 2021/11/27 13:25
 */
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
@Data
public class Stock implements Serializable {
    public Stock() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    @Column(name = "count")
    private Integer count;

    /**
     *
     */
    @Column(name = "product_id")
    private Integer productId;


}