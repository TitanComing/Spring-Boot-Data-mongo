package com.peng.mongo.service;

import com.peng.mongo.common.component.CustomPoint;
import com.peng.mongo.common.component.CustomPointWithRange;
import com.peng.mongo.model.MongoStarbucks;
import org.springframework.data.geo.Polygon;

import java.util.List;

/**
 * 星巴克地理位置Service
 * Created by peng on 2021/8/9.
 */
public interface StarbucksService {
    /**
     * 插入一条星巴克位置信息
     */
    MongoStarbucks insert(MongoStarbucks starbucks);


    /**
     * 插入多条星巴克位置信息
     */
    List<MongoStarbucks> insertAll(List<MongoStarbucks> starbucks);

    /**
     * 删除所有星巴克位置信息
     */
    long deleteAll();

    /**
     * 查询某个范围内的星巴克
     */
    List<MongoStarbucks> findStarBucksWithin(List<CustomPoint> customPoints);

    List<MongoStarbucks> findStarBucksNear(CustomPointWithRange customPointWithRange);
}
