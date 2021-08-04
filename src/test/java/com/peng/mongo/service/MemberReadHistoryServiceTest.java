package com.peng.mongo.service;

import com.peng.mongo.model.MongoMemberReadHistory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Log4j2
@SpringBootTest
class MemberReadHistoryServiceTest {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @Test
    void create() {
        MongoMemberReadHistory memberReadHistory = new MongoMemberReadHistory();
        memberReadHistory.setMemberId(1L);
        memberReadHistory.setMemberNickname("zhangsan");
        memberReadHistory.setMemberIcon("abc");
        memberReadHistory.setProductId(3L);
        memberReadHistory.setProductName("gaoda");
        memberReadHistory.setProductPic("4");
        memberReadHistory.setProductSubTitle("wuwa");
        memberReadHistory.setProductPrice("5.236");
        memberReadHistory.setCreateTime(new Date());

        log.info(memberReadHistoryService.create(memberReadHistory));
        Assertions.assertEquals(memberReadHistoryService.create(memberReadHistory).getMemberId(),1L);
    }

    @Test
    void list() {
        log.info(memberReadHistoryService.listByMemberId(1L));
        Assertions.assertEquals(memberReadHistoryService.listByMemberId(1L).get(0).getProductId(),3L);
    }
}