package com.codingzx.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户排队抢单信息实体  需要持久化redis
 */
@ToString
@Data
public class SkillRedisEntity implements Serializable {

    private Long productId;
    private String userId;


}
