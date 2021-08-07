package com.peng.mongo.service;

import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    MongoMemberReadHistory insert(MongoMemberReadHistory memberReadHistory);

    /**
     * 生成批量浏览记录
     */
    List<MongoMemberReadHistory> insertAll(List<MongoMemberReadHistory>  memberReadHistoryList);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 删除所有记录
     */
    long deleteAll();

    /**
     * 修改浏览记录
     */
    MongoMemberReadHistory update(MongoMemberReadHistory memberReadHistory);

    /**
     * 保存或修改浏览对象
     */
    MongoMemberReadHistory insertOrUpdate(MongoMemberReadHistory memberReadHistory);

    /**
     * 查询所有记录
     */
    List<MongoMemberReadHistory> listAll();

    /**
     * 通过用户id获取用户浏览历史记录
     */
    List<MongoMemberReadHistory> listByMemberId(Long memberId);

    /**
     * 通过产品id获取用户浏览历史记录
     */
    List<MongoMemberReadHistory> listByProductId(Long memberId);

    /**
     *  分页查询用户浏览历史
     */
    Page<MongoMemberReadHistory> listPagedMemberReadHistory(int page, int size);

    /**
     *  根据对象查找
     */
    List<MongoMemberReadHistory> listMemberReadHistoryByExample(MongoMemberReadHistory memberReadHistory);

    /**
     *  查询某个用户某个产品的浏览你信息
     */
    List<MongoMemberReadHistory> listMemberProductReadHis(Long memberId, Long productId);

    /**
     *  统计浏览产品总价格（测试用，没啥意义）
     */
    List<MongoMemberReadHistory> countByProducts(Sort sort);

    /**
     *  统计用户浏览次数
     */
    Long countReadHistoryByMember(Long memberId);

    /**
     *  统计用户不同商品浏览次数
     */
    Long countReadHistoryByMemberAndProduct(Long memberId);

    /**
     * 根据商品描述模糊查找
     */
    List<MongoMemberReadHistory> listByProductDesc(String productDesc);

    /**
     * 根据会员名称按时间倒序获取商品ids
     */
    List<Long> listProductIdsByMemberNickname(String memberNickname);

    /**
     * 根据商品id按时间倒序获取浏览人信息
     */
    List<MongoMember> listMemberByProductId(Long productId);
}
