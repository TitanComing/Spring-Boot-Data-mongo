package com.peng.mongo.controller;

import com.peng.mongo.common.api.CommonResult;
import com.peng.mongo.common.component.CustomPoint;
import com.peng.mongo.common.component.CustomPointWithRange;
import com.peng.mongo.model.MongoMemberReadHistory;
import com.peng.mongo.model.MongoStarbucks;
import com.peng.mongo.service.StarbucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 星巴克位置信息服务
 * Create by peng on 2021/8/9.
 */
@RestController
@RequestMapping("/starbucks/location")
public class StarbucksController {
    @Autowired
    private StarbucksService starbucksService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<MongoStarbucks> create(@RequestBody MongoStarbucks starbucks) {
        return CommonResult.success(starbucksService.insert(starbucks));
    }

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    public CommonResult<List<MongoStarbucks>> createAll(@RequestBody List<MongoStarbucks> starbucksList) {
        return CommonResult.success(starbucksService.insertAll(starbucksList));
    }

    @RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
    public CommonResult<Long> deleteAll() {
        return CommonResult.success(starbucksService.deleteAll());
    }

    @RequestMapping(value = "/findStarBucksWithin", method = RequestMethod.POST)
    public CommonResult<List<MongoStarbucks>> findStarBucksWithin(@RequestBody List<CustomPoint> customPoints) {
        List<MongoStarbucks> mongoStarbucksList = starbucksService.findStarBucksWithin(customPoints);
        return CommonResult.success(mongoStarbucksList);
    }

    @RequestMapping(value = "/findStarBucksNear", method = RequestMethod.POST)
    public CommonResult<List<MongoStarbucks>> findStarBucksWithin(@RequestBody CustomPointWithRange customPointWithRange) {
        List<MongoStarbucks> mongoStarbucksList = starbucksService.findStarBucksNear(customPointWithRange);
        return CommonResult.success(mongoStarbucksList);
    }



}
