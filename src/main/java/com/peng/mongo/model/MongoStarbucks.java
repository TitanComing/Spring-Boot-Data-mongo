package com.peng.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Create by peng on 2021/8/9.
 */
@Data
@Document(collection = "mongoStarbucks")
public class MongoStarbucks {
    @Id
    private String id;
    private String name;
    private String street;
    private String city;
    private GeoJsonPoint location;
}
