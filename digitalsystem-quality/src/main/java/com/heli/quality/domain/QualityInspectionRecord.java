package com.heli.quality.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质检部分字段对象 Quality_Inspection_Record
 *
 * @author ruoyi
 * @date 2024-05-16
 */
@Data
public class QualityInspectionRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long qiId;

    /**
     * 质检月份
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "质检月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 电车的问题数量
     */
    @Excel(name = "电车的问题数量")
    @RecordTemplate(rowNo = 3, columnNo = 6, comment = FillCommentEnum.EMPTY, name = "电车的问题数量")
    private Long electricCarProblemCount;

    /**
     * K2型号中小于5吨的问题数量
     */
    @Excel(name = "K2型号中小于5吨的问题数量")
    @RecordTemplate(rowNo = 3, columnNo = 7, comment = FillCommentEnum.EMPTY, name = "K2型号中小于5吨的问题数量")
    private Long k2lessthan5tonProblemCount;

    /**
     * K2型号中大吨位的问题数量
     */
    @Excel(name = "K2型号中大吨位的问题数量")
    @RecordTemplate(rowNo = 3, columnNo = 8, comment = FillCommentEnum.EMPTY, name = "K2型号中大吨位的问题数量")
    private Long k2largetonnageProblemCount;

    /**
     * 电车的问题车数量
     */
    @Excel(name = "电车的问题车数量")
    @RecordTemplate(rowNo = 2, columnNo = 6, comment = FillCommentEnum.EMPTY, name = "电车的问题车数量")
    private Long electricCarProblemVehicles;

    /**
     * K2型号中小于5吨的问题车数量
     */
    @Excel(name = "K2型号中小于5吨的问题车数量 ")
    @RecordTemplate(rowNo = 2, columnNo = 7, comment = FillCommentEnum.EMPTY, name = "K2型号中小于5吨的问题车数量")
    private Long k2lessthan5tonProblemVehicles;

    /**
     * K2型号中大吨位的问题车数量
     */
    @Excel(name = "K2型号中大吨位的问题车数量")
    @RecordTemplate(rowNo = 2, columnNo = 8, comment = FillCommentEnum.EMPTY, name = "K2型号中大吨位的问题车数量")
    private Long k2largetonnageProblemVehicles;

    /**
     * 电车、大吨位一次交检合格率
     */
    @Excel(name = "电车、大吨位一次交检合格率")
    private BigDecimal singleInspectionPassRate;

}
