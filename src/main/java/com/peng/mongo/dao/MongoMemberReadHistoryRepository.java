package com.peng.mongo.dao;

import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

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
}