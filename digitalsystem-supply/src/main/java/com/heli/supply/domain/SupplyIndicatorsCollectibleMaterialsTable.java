package com.heli.supply.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @description: 供应科-指标-集采物料字典对象 Supply_Indicators_Collectible_Materials_table
 * @author hong
 * @date 2024-04-28
 */
@Data
public class SupplyIndicatorsCollectibleMaterialsTable {
    private static final long serialVersionUID = 1L;

    /**
     * SCM_ID
     */
    @ExcelIgnore
    private Long scmId;

    /**
     * 序号
     */
    @ExcelProperty(value = "序号")
    private Long materialSerialNumber;

    /**
     * 物料号
     */
    @ExcelProperty(value = "物料号")
    private String materialNumber;

    /**
     * 物料名称
     */
    @ExcelProperty(value = "物料名称")
    private String materialName;

    /**
     * 集采类别
     */
    @ExcelProperty(value = "集采类别")
    private String centralizedProcurementCategory;

    /**
     * 创建人
     */
    @ExcelIgnore
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelIgnore
    private Date createTime;


}
