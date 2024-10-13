package com.heli.enterprise.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EnterpriseManagementManagementReadEntity {

    /**
     * 月份
     */
    @ExcelProperty(value = "月份")
    private String yearAndMonth;

    @ExcelProperty(value = "值")
    private String value;

    /**
     * SD 销售订单有效性考核
     */
    @ExcelProperty(value = "SD销售订单有效性考核")
    private String sdSalesordervalidity;

    /**
     * PP 手工创建生产订单比例
     */
    @ExcelProperty(value = "PP手工创建生产订单比例")
    private String ppManualpocreationratio;

    /**
     * PP 生产订单已收货未报工的比例
     */
    @ExcelProperty(value = "PP生产订单已收货未报工的比例")
    private String ppDeliveredunreportedratio;

    /**
     * MES 报工不及时率比率
     */
    @ExcelProperty(value = "MES报工不及时率比率")
    private String mesLateworkreportingrate;

    /**
     * QM 外检业务不及时率
     */
    @ExcelProperty(value = "QM外检业务不及时率")
    private String qmExternalinspectiondelay;

    /**
     * MM 采购订单交货不及时的比例
     */
    @ExcelProperty(value = "MM采购订单交货不及时的比例")
    private String mmPurchaseorderlatedelivery;

    /**
     * MM 手工创建采购订单比例
     */
    @ExcelProperty(value = "MM手工创建采购订单比例")
    private String mmManualpocreation;


    /**
     * MM 未清采购申请
     */
    @ExcelProperty(value = "MM未清采购申请")
    private String mmUnsettledpurchaserequests;

    /**
     * FICO 月度标准价格与周期单位价格综合差异率
     */
    @ExcelProperty(value = "FICO月度标准价格与周期单位价格综合差异率")
    private String ficoMonthlystandardpricevariation;

    /**
     * 跨月生产订单比例
     */
    @ExcelProperty(value = "跨月生产订单比例")
    private String crossMonthProductionOrders;

    /**
     * PM 维修订单完工不及时率
     */
    @ExcelProperty(value = "PM维修订单完工不及时率")
    private String pmLatemaintenanceordercompletion;

    /**
     * 预留字段 指标1
     */
//    @ExcelProperty(value = "指标1")
    @ExcelIgnore
    private String indicator1;

    /**
     * 预留字段 指标2
     */
//    @ExcelProperty(value = "指标2")
    @ExcelIgnore
    private String indicator2;
}
