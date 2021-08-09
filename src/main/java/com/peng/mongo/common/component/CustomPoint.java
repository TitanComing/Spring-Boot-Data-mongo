package com.peng.mongo.common.component;

import lombok.Data;

/**
 * 坐标点对象，用于接收前端参数
 * Create by peng on 2021/8/9.
 */
@Data
public class CustomPoint {
    //经度值
    private float lng;
    //维度值
    private float lat;
}
