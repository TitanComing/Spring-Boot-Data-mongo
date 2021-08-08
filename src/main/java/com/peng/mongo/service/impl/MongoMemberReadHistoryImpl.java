package com.peng.mongo.service.impl;

import com.peng.mongo.dao.MongoMemberReadHistoryRepository;
import com.peng.mongo.model.MongoMemberIDAndProductID;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 会员浏览记录管理Service实现类
 * Create by peng on 2021/8/3.
 */
@Log4j2
@Service
public class MongoMemberReadHistoryImpl implements MemberReadHistoryService {
    @Autowired
    private MongoMemberReadHistoryRepository memberReadHistoryRepository;

    @Override
    public MongoMemberReadHistory insert(MongoMemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        return memberReadHistoryRepository.insert(memberReadHistory);
    }

    @Override
    public List<MongoMemberReadHistory> insertAll(List<MongoMemberReadHistory> memberReadHistoryList) {
        for (MongoMemberReadHistory memberReadHistory : memberReadHistoryList) {
            memberReadHistory.setId(null);
            memberReadHistory.setCreateTime(new Date());
        }
        return memberReadHistoryRepository.insert(memberReadHistoryList);
    }

    @Override
    public int delete(List<String> ids) {
        if (!(ids.size() > 0)) {
            return 0;
        }
        memberReadHistoryRepository.deleteAllById(ids);
        return ids.size();
    }

    @Override
    public long deleteAll() {
        long count = memberReadHistoryRepository.count();
        memberReadHistoryRepository.deleteAll();
        return count;
    }

    @Override
    public MongoMemberReadHistory update(MongoMemberReadHistory memberReadHistory) {
        if (Objects.isNull(memberReadHistory.getMemberId())) {
            throw new RuntimeException("id不能为空");
        }
        if (!memberReadHistoryRepository.findById(memberReadHistory.getId()).isPresent()) {
            throw new RuntimeException("查找不到需更新的对象");
        }
        return memberReadHistoryRepository.save(memberReadHistory);
    }

    @Override
    public MongoMemberReadHistory insertOrUpdate(MongoMemberReadHistory memberReadHistory) {
        if(memberReadHistoryRepository.findById(memberReadHistory.getId()).isPresent()){
            return memberReadHistoryRepository.save(memberReadHistory);
        }
        memberReadHistory.setId(null);
        return memberReadHistoryRepository.insert(memberReadHistory);
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
    public List<MongoMemberReadHistory> listByProductId(Long productId) {
        return memberReadHistoryRepository.findByProductId(productId, Sort.by(Sort.Order.desc("productPrice")));
    }

    @Override
    public Page<MongoMemberReadHistory> listPagedMemberReadHistory(int page, int size) {
        //PageRequest pageRequest = PageRequest.of(0,2, Sort.by(Sort.Direction.ASC, "productPrice"));
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Order.asc("productPrice"), Sort.Order.desc("productId")));
        return memberReadHistoryRepository.findAll(pageRequest);
    }

    @Override
    public List<MongoMemberReadHistory> listMemberReadHistoryByExample(MongoMemberReadHistory memberReadHistory) {
        Example<MongoMemberReadHistory> historyExample = Example.of(memberReadHistory);
        //Example<MongoMemberReadHistory> historyExample = Example.of(memberReadHistory, ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.REGEX))
        return memberReadHistoryRepository.findAll(historyExample);
    }

    @Override
    public List<MongoMemberReadHistory> listByText(String text) {
        //默认是英文文本搜索
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(text);
        return memberReadHistoryRepository.findAllBy(criteria);
    }

    @Override
    public List<MongoMemberReadHistory> listMemberProductReadHis(Long memberId, Long productId) {
        return memberReadHistoryRepository.findMemberProductReadHis(memberId, productId);
    }

    @Override
    public List<MongoMemberIDAndProductID> countByProducts() {
        return memberReadHistoryRepository.countByProducts(Sort.by(Sort.Direction.DESC, "productPrice"));
    }

    @Override
    public Long countReadHistoryByMember(Long memberId) {
        return memberReadHistoryRepository.countReadHistoryByMember(memberId);
    }

    @Override
    public Long countReadHistoryByMemberAndProduct(Long memberId, Long productId) {
        return memberReadHistoryRepository.countReadHistoryByMemberAndProduct(memberId, productId);
    }
}
