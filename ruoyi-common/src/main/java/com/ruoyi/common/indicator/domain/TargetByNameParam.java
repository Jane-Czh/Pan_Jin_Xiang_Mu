package com.ruoyi.common.indicator.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TargetByNameParam {
    private Date startDate;
    private Date endDate;
    private String name;
}
