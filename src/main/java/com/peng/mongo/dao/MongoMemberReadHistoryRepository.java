package com.peng.mongo.dao;

import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 分页查询浏览记录
     *
     * @param pageable 查询分页参数
     */
    Page<MongoMemberReadHistory> findPagedMemberReadHistoryBy(Pageable pageable);

    /**
     * 统计产品总价格（测试用，没啥意义）
     *
     * @param sort 排序方式
     */
    @Aggregation("{$group:{_id:$productId, totalPrice:{$sum:$productPrice}}")
    List<MongoMemberReadHistory> countByProducts(Sort sort);

    /**
     * 统计用户浏览次数
     * @param memberId 用户id
     */
    @Aggregation(pipeline = {"{$match:{memberId: ?0}}", "{$count: total}"})
    Long countReadHistoryByMember(Long memberId);

    /**
     * 根据会员名称按时间倒序获取浏览记录
     *
     * @param memberNickname 会员名称
     */
    List<MongoMemberReadHistory> findByMemberNicknameOrderByCreateTimeDesc(String memberNickname);

    /**
     * 根据商品描述搜索
     * @param criteria 商品描述
     */
    List<MongoMemberReadHistory> findAllBy(TextCriteria criteria);

    /**
     * 根据会员名称按时间倒序获取商品ids
     *
     * @param memberNickname 会员名称
     */
    List<Long> findProductIdByMemberNicknameOrderByCreateTimeDesc(String memberNickname);

    /**
     * 根据商品id按时间倒序获取浏览人信息
     *
     * @param productId 商品id
     */
    List<MongoMember> findMemberIdAndMemberNicknameAndMemberIconByProductIdOrderByCreateTimeDesc(Long productId);

    /**
     * 根据商品描述模糊查找
     *
     * @param productDesc 商品描述
     */
    @Query(value = "select * from mongoMemberReadHistory where productDesc like ?1", sort = "productPrice")
    List<MongoMemberReadHistory> findByProductDesc(String productDesc);

}