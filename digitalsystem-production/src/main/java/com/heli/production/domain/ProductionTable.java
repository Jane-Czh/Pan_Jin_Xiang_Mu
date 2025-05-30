package com.heli.production.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @description: 商品车台账对象 Market_Commercial_Vehicle_table
 * @author: hong
 * @date: 2024/4/11 20:13
 **/
@Data
public class ProductionTable {
    private static final long serialVersionUID = 1L;

    /**
     * MCV_ID
     */
    private String mcvId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @ExcelProperty(value = "接单日期")
    @DateTimeFormat("yyyy/MM/dd")
    private Date acceptanceDate;

    /**
     * 上线日期
     * 修改统计为----车体上线日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @ExcelProperty(value = "车体上线日期")
    @DateTimeFormat("yyyy/MM/dd")
    private Date launchDate;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String orderNumber;

    /**
     * 合同号
     */
    @ExcelProperty(value = "合同号")
    private String contractNumber;

    /**
     * 车号
     */
    @ExcelProperty(value = "车号")
    private String carNumber;

    /**
     * 车型
     */
    @ExcelProperty(value = "车型")
    private String vehicleModel;

    /**
     * 门架高度
     */
//    @ExcelProperty(value = "门架高度")
    @ExcelIgnore
    private String doorFrameHeight;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private Integer amount;

    /**
     * 属具
     */
//    @ExcelProperty(value = "属具")
    @ExcelIgnore
    private String accessory;

    /**
     * 阀片（数量）
     */
//    @ExcelProperty(value = "阀片")
    @ExcelIgnore
    private String valveBlock;

    /**
     * 配置
     */
//    @ExcelProperty(value = "配置")
    @ExcelIgnore
    private String carConfiguration;

    /**
     * 计划完工期 <--- !!!
     * 修改为统计-----精整完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @ExcelProperty(value = "精整完工期")
    @ExcelProperty(value = "计划完工期")
    @DateTimeFormat("yyyy/MM/dd")
    private Date plannedCompletionPeriod;

    /**
     * 客户
     */
//    @ExcelProperty(value = "客户")
    @ExcelIgnore
    private String customer;

    /**
     * 车体上线日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @ExcelProperty(value = "车体上线日期")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date vehicleLaunchDate;

    /**
     * 门架合装完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @ExcelProperty(value = "门架合装完工期")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date completionPeriodOfDoorFrameAssembly;

    /**
     * 试车完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @ExcelProperty(value = "试车完工期")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date trialCompletionPeriod;

    /**
     * 特种作业
     */
//    @ExcelProperty(value = "特种作业")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date specialOperations;

    /**
     * 精整完工期
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @ExcelProperty(value = "精整完工期")
    @DateTimeFormat("yyyy/MM/dd")
    private Date precisionCompletionPeriod;

    /**
     * 生产现场问题
     */
//    @ExcelProperty(value = "生产现场问题")
    @ExcelIgnore
    private String productionSiteIssues;

    /**
     * 现场异常问题采购
     */
//    @ExcelProperty(value = "现场异常问题采购")
    @ExcelIgnore
    private String procurementOfOnSiteAbnormalIssues;

    /**
     * 现场异常问题质量
     */
//    @ExcelProperty(value = "现场异常问题质量")
    @ExcelIgnore
    private String qualityOfOnSiteAbnormalIssues;

    /**
     * 上线
     */
//    @ExcelProperty(value = "上线")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date goLive;

    /**
     * 入库
     */
//    @ExcelProperty(value = "入库")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelIgnore
    private Date warehousing;

    /**
     * 生产周期
     */
//    @ExcelProperty(value = "生产周期")
    @ExcelIgnore
    private String productionCycle;

}
