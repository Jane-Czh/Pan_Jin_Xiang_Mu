package com.heli.production.domain;

import lombok.Data;

import java.util.Date;

/**
 * @description: 统计数值实体
 * @author: hong
 * @date: 2024/4/11 20:12
 **/
@Data
public class ProductionCountNumberEntity {

    private Date realTime;

    private Integer launch;

    private Integer completion;

}
