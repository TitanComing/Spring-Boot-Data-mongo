package com.peng.mongo.service.impl;

import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.dao.MongoMemberReadHistoryRepository;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.TextCriteria;
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
    public List<MongoMemberReadHistory> createMany(List<MongoMemberReadHistory> memberReadHistoryList) {
        for (MongoMemberReadHistory memberReadHistory : memberReadHistoryList) {
            memberReadHistory.setId(null);
            memberReadHistory.setCreateTime(new Date());
        }
        return memberReadHistoryRepository.saveAll(memberReadHistoryList);
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
    public List<MongoMemberReadHistory> listAll() {
        return memberReadHistoryRepository.findAll();
    }

    @Override
    public List<MongoMemberReadHistory> listByMemberId(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }

    @Override
    public Page<MongoMemberReadHistory> getPagedMemberReadHistory(Pageable pageable) {
        //PageRequest pageRequest = PageRequest.of(0,2, Sort.by(Sort.Direction.ASC, "productPrice"));
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Order.asc("productPrice"), Sort.Order.desc("productId")));
        return memberReadHistoryRepository.findPagedMemberReadHistoryBy(pageRequest);
    }

    @Override
    public List<MongoMemberReadHistory> getMemberReadHistoryByExample(MongoMemberReadHistory memberReadHistory) {
        Example<MongoMemberReadHistory> historyExample = Example.of(memberReadHistory);
        //Example<MongoMemberReadHistory> historyExample = Example.of(memberReadHistory, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.REGEX))
        return memberReadHistoryRepository.findAll(historyExample);
    }

    @Override
    public List<MongoMemberReadHistory> getReadHistoryByDesc(String desc) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(desc);
        return memberReadHistoryRepository.findAllBy(criteria);
    }

    @Override
    public List<Long> findProductIdsByMemberNickname(String memberNickname) {
        return memberReadHistoryRepository.findProductIdByMemberNicknameOrderByCreateTimeDesc(memberNickname);
    }

    @Override
    public List<MongoMember> findMemberByProductId(Long productId) {
        return memberReadHistoryRepository.findMemberIdAndMemberNicknameAndMemberIconByProductIdOrderByCreateTimeDesc(productId);
    }

    @Override
    public List<MongoMemberReadHistory> findByProductDesc(String productDesc) {
        return memberReadHistoryRepository.findByProductDesc(productDesc);
    }
}
