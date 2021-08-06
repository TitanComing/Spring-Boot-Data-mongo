package com.peng.mongo.service;

import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员浏览记录管理Service
 * Created by peng on 2021/8/3.
 */
public interface MemberReadHistoryService {
    /**
     * 生成单条浏览记录
     */
    MongoMemberReadHistory create(MongoMemberReadHistory memberReadHistory);

    /**
     * 生成批量浏览记录
     */
    List<MongoMemberReadHistory> createMany(List<MongoMemberReadHistory>  memberReadHistoryList);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 删除所有记录
     */
    long deleteAll();

    /**
     * 查询所有记录
     */
    List<MongoMemberReadHistory> listAll();

    /**
     * 通过用户id获取用户浏览历史记录
     */
    List<MongoMemberReadHistory> listByMemberId(Long memberId);

    /**
     *  分页查询用户浏览历史
     */
    Page<MongoMemberReadHistory> getPagedMemberReadHistory(Pageable pageable);

    /**
     *  根据对象查找
     */
    List<MongoMemberReadHistory> getMemberReadHistoryByExample(MongoMemberReadHistory memberReadHistory);

    /**
     *  根据商品描述搜索
     */
    List<MongoMemberReadHistory> getReadHistoryByDesc(String desc);

    /**
     * 根据会员名称按时间倒序获取商品ids
     */
    List<Long> findProductIdsByMemberNickname(String memberNickname);

    /**
     * 根据商品id按时间倒序获取浏览人信息
     */
    List<MongoMember> findMemberByProductId(Long productId);

    /**
     * 根据商品描述模糊查找
     */
    List<MongoMemberReadHistory> findByProductDesc(String productDesc);

}
