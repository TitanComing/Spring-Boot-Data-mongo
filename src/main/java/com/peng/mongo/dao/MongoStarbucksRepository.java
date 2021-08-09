package com.peng.mongo.dao;

import com.peng.mongo.model.MongoStarbucks;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoStarbucksRepository extends MongoRepository<MongoStarbucks,String> {
    /**
     * 查询某个地理方位内的星巴克
     * 需要创建"2d"或者“2dsphere”索引，示例：db.places.createIndex( { location: "2dsphere" } )
     * @param polygon 地理范围
     */
    List<MongoStarbucks> findByLocationWithin(Polygon polygon);

    /**
     * 查询某个地理位置点附近的点
     * 需要创建"2d"或者“2dsphere”索引，示例：db.places.createIndex( { location: "2dsphere" } )
     * @param point 地理点
     * @param distance 范围
     */
    List<MongoStarbucks> findByLocationNear(Point point, Distance distance);
}
