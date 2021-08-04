package com.peng.mongo.common.compent;

import lombok.Data;

/**
 * 用户实体类
 * Create by peng on 2021/8/4.
 */
@Data
public class MongoMember {
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
}
