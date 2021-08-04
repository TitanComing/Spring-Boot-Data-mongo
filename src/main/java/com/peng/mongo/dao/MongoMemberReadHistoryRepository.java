package com.peng.mongo.dao;

import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 会员商品浏览历史Repository
 * Create by peng on 2021/8/3.
 */
public interface MongoMemberReadHistoryRepository extends MongoRepository<MongoMemberReadHistory, String> {
    /**
     * 根据会员id按时间倒序获取浏览记录
     * @param memberId 会员id
     */
    List<MongoMemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

    /**
     * 根据会员名称按时间倒序获取浏览记录
     * @param memberNickname 会员名称
     */
    List<MongoMemberReadHistory> findByMemberNicknameOrderByCreateTimeDesc(String memberNickname);

    /**
     * 根据会员名称按时间倒序获取商品ids
     * @param memberNickname 会员名称
     */
    List<Long> findProductIdByMemberNicknameOrderByCreateTimeDesc(String memberNickname);

    /**
     * 根据商品id按时间倒序获取浏览人信息
     * @param productId 商品id
     */
    List<MongoMember> findMemberIdAndMemberNicknameAndMemberIconByProductIdOrderByCreateTimeDesc(Long productId);

    /**
     * 根据商品描述模糊查找
     * @param productDesc 商品描述
     */
    @Query(value ="select * from mongoMemberReadHistory where productDesc like ?1", sort = "productPrice")
    List<MongoMemberReadHistory> findByProductDesc(String productDesc);

}