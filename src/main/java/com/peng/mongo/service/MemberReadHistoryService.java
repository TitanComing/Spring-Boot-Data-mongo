package com.peng.mongo.service;

import com.peng.mongo.model.MongoMemberReadHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员浏览记录管理Service
 * Created by peng on 2021/8/3.
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    MongoMemberReadHistory create(MongoMemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 删除所有记录
     */
    long deleteAll();

    /**
     * 获取用户浏览历史记录
     */
    List<MongoMemberReadHistory> list(Long memberId);
}
