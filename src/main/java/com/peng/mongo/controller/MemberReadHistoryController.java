package com.peng.mongo.controller;

import com.peng.mongo.common.api.CommonResult;
import com.peng.mongo.common.compent.MongoMember;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/createMany", method = RequestMethod.POST)
    public CommonResult<List<MongoMemberReadHistory>> create(@RequestBody List<MongoMemberReadHistory> memberReadHistoryList) {
        return CommonResult.success(memberReadHistoryService.createMany(memberReadHistoryList));
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

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listAll() {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listAll();
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/listByMemberId", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listByMemberId(@RequestParam("memberId") Long memberId) {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listByMemberId(memberId);
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/listProdIdsByMemberName", method = RequestMethod.GET)
    public CommonResult<List<Long>> listProdIdsByMemberName(@RequestParam("memberName") String memberName) {
        List<Long> ids = memberReadHistoryService.findProductIdsByMemberNickname(memberName);
        return CommonResult.success(ids);
    }

    @RequestMapping(value = "/listMemberByProductId", method = RequestMethod.GET)
    public CommonResult<List<MongoMember>> listMemberByProductId(@RequestParam("productId") Long productId) {
        List<MongoMember> members = memberReadHistoryService.findMemberByProductId(productId);
        return CommonResult.success(members);
    }

    @RequestMapping(value = "/listByProductDesc", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listByProductDesc(@RequestParam("productDesc") String productDesc) {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.findByProductDesc(productDesc);
        return CommonResult.success(memberReadHistoryList);
    }
}
