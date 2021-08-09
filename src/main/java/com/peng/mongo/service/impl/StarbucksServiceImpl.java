package com.peng.mongo.service.impl;

import com.peng.mongo.common.component.CustomPoint;
import com.peng.mongo.dao.MongoStarbucksRepository;
import com.peng.mongo.model.MongoStarbucks;
import com.peng.mongo.service.StarbucksService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by peng on 2021/8/9.
 */
@Log4j2
@Service
public class StarbucksServiceImpl implements StarbucksService {
    @Autowired
    private MongoStarbucksRepository mongoStarbucksRepository;

    @Override
    public MongoStarbucks insert(MongoStarbucks starbucks) {
        starbucks.setId(null);
        return mongoStarbucksRepository.insert(starbucks);
    }

    @Override
    public List<MongoStarbucks> insertAll(List<MongoStarbucks> starbucks) {
        for (MongoStarbucks starbuck : starbucks) {
            starbuck.setId(null);
        }
        return mongoStarbucksRepository.insert(starbucks);
    }

    @Override
    public long deleteAll() {
        long count = mongoStarbucksRepository.count();
        mongoStarbucksRepository.deleteAll();
        return count;
    }

    @Override
    public List<MongoStarbucks> findStarBucksWithin(List<CustomPoint> customPoints) {
        List<Point> points = new ArrayList<>();
        for (CustomPoint customPoint : customPoints) {
            points.add(new Point(customPoint.getLng(), customPoint.getLat()));
        }
        if (!(points.size() == 0)) {
            throw new RuntimeException("没有有效请求范围数据");
        }
        GeoJsonPolygon geoJsonPolygon = new GeoJsonPolygon(points);
        return mongoStarbucksRepository.findByLocationWithin(geoJsonPolygon);
    }
}
