package com.peng.mongo.dao;


import com.peng.mongo.model.MongoMemberIDAndProductID;
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
     * 根据文本搜索
     *
     * @param criteria 文本描述
     */
    List<MongoMemberReadHistory> findAllBy(TextCriteria criteria);

    /**
     * 查询某个用户某个产品的浏览你信息
     *
     * @param memberId  用户id
     * @param productId 产品id
     */
    @Query("{'memberId':?0,'productId':?1}")
    List<MongoMemberReadHistory> findMemberProductReadHis(Long memberId, Long productId);

    /**
     * 统计浏览产品浏览次数
     *
     * @param sort 排序方式
     */
    //db.mongoMemberReadHistory.aggregate([{$group:{_id:{memberId:"$memberId",productId:"$productId"},total:{$sum:1}}}]).pretty()
    @Aggregation("{$group:{_id:{memberId:'$memberId',productId:'$productId'}, total:{$sum:1}}}")
    List<MongoMemberIDAndProductID> countByProducts(Sort sort);

    /**
     * 统计用户浏览商品个数
     *
     * @param memberId 用户id
     */
    //mongodb3.4 才支持$count的聚合操作，早期版本需要用group
    // db.mongoMemberReadHistory.aggregate([{$match:{memberId: 2}},{$group:{_id:null, total:{$sum:1}}},{$project:{_id:0,total:1}}]).pretty()
    @Aggregation(pipeline = {"{$match:{memberId: ?0}}", "{$group:{_id:null, total:{$sum:1}}}","{$project:{_id:0,total:1}}"})
    Long countReadHistoryByMember(Long memberId);

    /**
     * 统计用户某个商品的浏览次数
     *
     * @param memberId 用户id
     */
    //> db.mongoMemberReadHistory.aggregate([{$match:{memberId: 2}},{$match:{productId:1}},{$group:{_id:null, total:{$sum:1}}},{$project:{_id:0,total:1}}]).pretty()
    @Aggregation(pipeline = {"{$match:{memberId: ?0}}", "{$match:{productId:?1}}","{$group:{_id:null, total:{$sum:1}}}","{$project:{_id:0,total:1}}"})
    Long countReadHistoryByMemberAndProduct(Long memberId, Long productId);

}