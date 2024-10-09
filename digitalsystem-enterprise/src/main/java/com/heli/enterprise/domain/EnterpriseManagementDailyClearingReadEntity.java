package com.heli.enterprise.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data
public class EnterpriseManagementDailyClearingReadEntity {

    /**
     * 年月
     */
    @ExcelProperty(value = "月份")
    private String yearAndMonth;

    @ExcelProperty(value = "值")
    private String value;

    /**
     * 销售订单录入不及时比例
     */
    @ExcelProperty(value = "销售订单录入不及时比例")
    private String orderEntryDelayRatio;

    /**
     * 销售订单不及时发货比例
     */
    @Excel(name = "销售订单不及时发货比例")
    private String shipmentDelayRatio;

    /**
     * 生产订单不及时报工比例
     */
    @Excel(name = "生产订单不及时报工比例")
    private String productionReportDelayRatio;

    /**
     * 成品检验业务不及时率
     */
    @Excel(name = "成品检验业务不及时率")
    private String inspectionDelayRate;

    /**
     * 销售发票过账不及时率
     */
    @Excel(name = "销售发票过账不及时率")
    private String invoicePostingDelayRate;

    /**
     * 客户未清账比例
     */
    @Excel(name = "客户未清账比例")
    private String unsettledAccountsRatio;
}



