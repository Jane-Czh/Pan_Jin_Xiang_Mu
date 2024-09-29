package com.heli.tech.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TechNewProjectCompletionDTO {
    private Date yearAndMonth;
    //    （1）截止当月项目总数
    private Long totalProjectCount;
    //（2）截止当月项目积分占比（各项目所得的积分和/所有项目的总积分）
    private BigDecimal projectPointsPercentage;
}
