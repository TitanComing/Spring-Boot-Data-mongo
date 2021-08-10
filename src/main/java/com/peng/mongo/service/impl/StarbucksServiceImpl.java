package com.peng.mongo.service.impl;

import com.peng.mongo.common.component.CustomPoint;
import com.peng.mongo.common.component.CustomPointWithRange;
import com.peng.mongo.dao.MongoStarbucksRepository;
import com.peng.mongo.model.MongoStarbucks;
import com.peng.mongo.service.StarbucksService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
            points.add(new Point(customPoint.getLongitude(), customPoint.getLatitude()));
        }
        if (points.size() == 0) {
            throw new RuntimeException("没有有效请求范围数据");
        }
        GeoJsonPolygon geoJsonPolygon = new GeoJsonPolygon(points);
        return mongoStarbucksRepository.findByLocationWithin(geoJsonPolygon);
    }

    @Override
    public List<MongoStarbucks> findStarBucksNear(CustomPointWithRange customPointWithRange) {
        GeoJsonPoint geoJsonPoint= new GeoJsonPoint(customPointWithRange.getLongitude(),customPointWithRange.getLatitude());
        Distance distance = new Distance(customPointWithRange.getRange(), Metrics.KILOMETERS);
        return mongoStarbucksRepository.findByLocationNear(geoJsonPoint, distance);
    }
}
