package com.peng.mongo.service.impl;

import com.peng.mongo.dao.MongoMemberReadHistoryRepository;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员浏览记录管理Service实现类
 * Create by peng on 2021/8/3.
 */
@Service
public class MongoMemberReadHistoryImpl implements MemberReadHistoryService {
    @Autowired
    private MongoMemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public MongoMemberReadHistory create(MongoMemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        return memberReadHistoryRepository.save(memberReadHistory);
    }

    @Override
    public int delete(List<String> ids) {
        if (!(ids.size() > 0)) {
            return 0;
        }
        List<MongoMemberReadHistory> deleteList = new ArrayList<>();
        for (String id : ids) {
            MongoMemberReadHistory memberReadHistory = new MongoMemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }
        memberReadHistoryRepository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public long deleteAll() {
        long count = memberReadHistoryRepository.count();
        memberReadHistoryRepository.deleteAll();
        return count;
    }

    @Override
    public List<MongoMemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
