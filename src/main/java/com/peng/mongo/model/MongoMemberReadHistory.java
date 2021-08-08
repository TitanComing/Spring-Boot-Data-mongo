package com.peng.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户商品浏览历史记录
 * Create by peng on 2021/8/3.
 */
@Data
@Document
public class MongoMemberReadHistory {
    @Id
    private String id;
    //仅仅是标识索引，不会自动创建索引，但是不创建普通索引也能运行
    @Indexed
    private Long memberId;
    private String memberNickname;
    private String memberIcon;
    @Indexed
    private Long productId;
    //仅仅是标识索引，不会自动创建索引，但是不创建文字索引会报错
    //一个集合只能有一个文本搜索索引，但是该索引可以覆盖多个字段
    @TextIndexed
    private String productName;
    private String productPic;
    private String productSubTitle;
    //BigDecimal会被映射成String存储，此时排序是不对
    //private BigDecimal productPrice;
    private Double productPrice;
    //仅仅是标识索引，不会自动创建索引，但是不创建文字索引会报错
    @TextIndexed
    private String productDesc;
    private Date createTime;
}
