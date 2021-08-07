package com.peng.mongo.dao;


import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.Aggregation;
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
     *
     * @param memberId 会员id
     */
    List<MongoMemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);

    /**
     * 根据商品id按照价格获取浏览记录
     *
     * @param productId 会员id
     */
    List<MongoMemberReadHistory> findByProductId(Long productId, Sort sort);

    /**
     * 根据商品描述搜索
     *
     * @param criteria 商品描述
     */
    List<MongoMemberReadHistory> findAllByProductDesc(TextCriteria criteria);

    /**
     * 查询某个用户某个产品的浏览你信息
     *
     * @param memberId  用户id
     * @param productId 产品id
     */
    @Query("{'memberId':?0,'productId':?1}")
    List<MongoMemberReadHistory> findMemberProductReadHis(Long memberId, Long productId);

    /**
     * 统计浏览产品总价格（测试用，没啥意义）
     *
     * @param sort 排序方式
     */
    @Aggregation("{$group:{_id:$productId, totalPrice:{$sum:$productPrice}}")
    List<MongoMemberReadHistory> countByProducts(Sort sort);

    /**
     * 统计用户浏览次数
     *
     * @param memberId 用户id
     */
    @Aggregation(pipeline = {"{$match:{memberId: ?0}}", "{$count: total}"})
    Long countReadHistoryByMember(Long memberId);

    /**
     * 统计用户商品的浏览次数
     *
     * @param memberId 用户id
     */
    @Aggregation(pipeline = {"{$match:{memberId: ?0}}", "{$group:{_id:$productId}}", "{$count: total}"})
    Long countReadHistoryByMemberAndProduct(Long memberId);

}