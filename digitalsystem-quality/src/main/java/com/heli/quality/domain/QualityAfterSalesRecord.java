package com.heli.quality.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后台账部分字段对象 Quality_After_Sales_Record
 *
 * @author hong
 * @date 2024-05-16
 */
@Data
@ExcelIgnoreUnannotated
public class QualityAfterSalesRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long qaId;

    /**
     * 售后编号
     */
    @Excel(name = "编号")
    @ExcelProperty(value = "编号")
    private String afterSalesId;

    /**
     * 反馈日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "反馈日期")
    private Date feedbackDate;

    /**
     * 车号
     */
    @Excel(name = "车号")
//    @ExcelProperty(value = "车号")
    private String carNumber;

    /**
     * 销售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty(value = "销售时间")
    private Date saleTime;

    /**
     * 使用时间
     */
    @Excel(name = "使用时间")
    @ExcelProperty(value = "使用时间")
    private String usageTime;

    /**
     * 是否为新车
     */
    @Excel(name = "是否为新车")
    private Integer checkNewcar;

    /**
     * 责任单位
     */
    @Excel(name = "责任单位")
    @ExcelProperty(value = "责任单位")
    private String responsibleUnit;


}
