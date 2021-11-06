package com.codingzx.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "skill_ad")
@Data
@ToString
public class AdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public AdEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    /**
     * 跳转地址
     */
    @Column(name = "click_url")
    private String clickUrl;

    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;
    /**
     * 审核状态0 未生效 1 已生效
     */
    @Column(name = "status")
    private String status;


    /**
     * 排序
     */
    @Column(name = "order")
    private Integer order;

}
