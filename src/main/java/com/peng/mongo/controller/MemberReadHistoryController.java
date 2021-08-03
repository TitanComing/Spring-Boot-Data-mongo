package com.peng.mongo.controller;

import com.peng.mongo.common.api.CommonResult;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员浏览记录管理Service
 * Create by peng on 2021/8/3.
 */
@RestController
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<MongoMemberReadHistory> create(@RequestBody MongoMemberReadHistory memberReadHistory) {
        return CommonResult.success(memberReadHistoryService.create(memberReadHistory));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult<Integer> delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public CommonResult<Long> deleteAll() {
       return CommonResult.success(memberReadHistoryService.deleteAll());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> list(@RequestParam("memberId") Long memberId) {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }
}
