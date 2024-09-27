package com.heli.tech.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [技术]指标填报对象 Tech
 *
 * @author hong
 * @date 2024-04-27
 */
@Data
public class Tech extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Tech_ID
     */
    private Long techId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 非标准单平均技术准备天数
     */
    @Excel(name = "非标准单平均技术准备天数")
    private BigDecimal nonStandardAvgPreparationDays;

    /**
     * 非标准单条数
     */
    private Integer nonStandardNum;

    /**
     * 非标准单超时条数
     */
    private Integer nonStandardOvertimeNum;

    /**
     * 当月完成的计划
     */
    @Excel(name = "当月完成的计划")
    private Long completedmonthlyPlancounts;

    private Long completedPlanCount;

    /**
     * 研发项目计划进度完成率
     */
    @Excel(name = "研发项目计划进度完成率")
    private BigDecimal prdscheduleCompletionrate;


}
