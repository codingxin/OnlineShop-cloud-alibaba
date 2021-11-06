package com.codingzx.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author codingzx
 * @description
 * @date 2021/11/6 13:02
 */
@Entity
@Table(name = "user")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "passwd")
    private String passwd;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_role")
    private String userRole;

}