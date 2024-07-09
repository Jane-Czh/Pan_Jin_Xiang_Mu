package com.heli.supply.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @description: 供应科-指标-采购订单对象 Supply_Indicators_PurchaseOrder_table
 * @author hong
 * @date 2024-04-28
 */
@Data
public class SupplyIndicatorsPurchaseOrderTable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelIgnore
    private Long spId;

    /**
     * 凭证日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelProperty(value = "凭证日期")
    @DateTimeFormat("yyyy/mm/dd")
    private Date documentDate;

    /**
     * 采购凭证
     */
    @ExcelProperty(value = "采购凭证")
    private String purchasingDocuments;

    /**
     * 物料号
     */
    @ExcelProperty(value = "物料")
    private String materialNumber;

    /**
     * 是否为集采(0/1)
     */
    @ExcelIgnore
    private Integer collectiveProcurement;

    /**
     * 短文本
     */
    @ExcelProperty(value = "短文本")
    private String shortText;

    /**
     * 单位
     */
    @ExcelProperty(value = "单位")
    private String unit;

    /**
     * 数量
     */
    @ExcelProperty(index = 7)
    private Long quantity;

    /**
     * 订单净值
     */
    @ExcelProperty(value = "订单净值")
    private BigDecimal orderNetValue;

    /**
     * 供应商/供应工厂
     */
    @ExcelProperty(value = "供应商/供应工厂")
    private String supplier;

}
