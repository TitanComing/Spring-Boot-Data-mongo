package com.peng.mongo.controller;

import com.peng.mongo.common.api.CommonResult;
import com.peng.mongo.model.MongoMemberIDAndProductID;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return CommonResult.success(memberReadHistoryService.insert(memberReadHistory));
    }

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    public CommonResult<List<MongoMemberReadHistory>> createAll(@RequestBody List<MongoMemberReadHistory> memberReadHistoryList) {
        return CommonResult.success(memberReadHistoryService.insertAll(memberReadHistoryList));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult<MongoMemberReadHistory> update(@RequestBody MongoMemberReadHistory memberReadHistory) {
        return CommonResult.success(memberReadHistoryService.update(memberReadHistory));
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    public CommonResult<MongoMemberReadHistory> insertOrUpdate(@RequestBody MongoMemberReadHistory memberReadHistory) {
        return CommonResult.success(memberReadHistoryService.insertOrUpdate(memberReadHistory));
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

    @RequestMapping(value = "/listByProductId", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listByProductId(@RequestParam("productId") Long productId) {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listByProductId(productId);
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/listPagedMemberReadHistory", method = RequestMethod.GET)
    public CommonResult<Page<MongoMemberReadHistory>> listPagedMemberReadHistory(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listPagedMemberReadHistory(page, size);
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/listMemberReadHistoryByExample", method = RequestMethod.POST)
    public CommonResult<List<MongoMemberReadHistory>> listMemberReadHistoryByExample(@RequestBody MongoMemberReadHistory memberReadHistory) {
        return CommonResult.success(memberReadHistoryService.listMemberReadHistoryByExample(memberReadHistory));
    }

    //一个集合只能有一个文本搜索索引，但是该索引可以覆盖多个字段
    @RequestMapping(value = "/listByText", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listByProductDesc(@RequestParam("text") String text) {
        List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listByText(text);
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/listMemberProductReadHis", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberReadHistory>> listMemberProductReadHis(@RequestParam("memberId") long memberId, @RequestParam("productId") long productId) {
      List<MongoMemberReadHistory> memberReadHistoryList = memberReadHistoryService.listMemberProductReadHis(memberId, productId);
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/countByProducts", method = RequestMethod.GET)
    public CommonResult<List<MongoMemberIDAndProductID>> countByProducts() {
        List<MongoMemberIDAndProductID> memberReadHistoryList = memberReadHistoryService.countByProducts();
        return CommonResult.success(memberReadHistoryList);
    }

    @RequestMapping(value = "/countReadHistoryByMember", method = RequestMethod.GET)
    public CommonResult<Long> countReadHistoryByMember(@RequestParam("memberId") long memberId) {
        Long count = memberReadHistoryService.countReadHistoryByMember(memberId);
        return CommonResult.success(count);
    }

    @RequestMapping(value = "/countReadHistoryByMemberAndProduct", method = RequestMethod.GET)
    public CommonResult<Long> countReadHistoryByMemberAndProduct(@RequestParam("memberId") long memberId,@RequestParam("productId") long productId) {
        Long count = memberReadHistoryService.countReadHistoryByMemberAndProduct(memberId,productId);
        return CommonResult.success(count);
    }


}
