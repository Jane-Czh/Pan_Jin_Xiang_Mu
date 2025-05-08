package com.heli.production.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProductionCommercialVehicleTable {

    /**
     * MCV_ID
     */
    private String mcvId;

    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat("yyyy/MM/dd")
    private Date acceptanceDate;

    /**
     * 车号
     */
    @ExcelProperty(value = "车号")
    private String carNumber;


    /**
     * 上线日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat("yyyy/MM/dd")
    private Date launchDate;


    /**
     * 数量
     */
    private Integer amount;


    /**
     * 计划完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat("yyyy/MM/dd")
    private Date plannedCompletionPeriod;


    /**
     * 精整完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @DateTimeFormat("yyyy/MM/dd")
    private Date precisionCompletionPeriod;
}
