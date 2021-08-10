package com.peng.mongo.common.component;

import lombok.Data;

/**
 *  坐标点对象再加一个范围
 * Create by peng on 2021/8/10.
 */
@Data
public class CustomPointWithRange extends CustomPoint{
    //距离点的范围
    private double range;
}
