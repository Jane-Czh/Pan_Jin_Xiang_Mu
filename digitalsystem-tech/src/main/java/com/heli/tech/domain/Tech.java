package com.heli.tech.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * [技术]指标填报对象 Tech
 *
 * @author hong
 * @date 2024-09-28
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
     * 非标订单条数
     */
    @Excel(name = "非标订单条数")
    private Long nonStandardNum;

    /**
     * 非标订单超时条数
     */
    @Excel(name = "非标订单超时条数")
    private Long nonStandardOvertimeNum;

    /**
     * 非标订单同比增幅情况
     */
    @Excel(name = "非标订单同比增幅情况")
    private BigDecimal nonStandardOrderGrowthRate;

    /**
     * 项目总数
     */
    @Excel(name = "项目总数")
    private Long totalProjectCount;

    /**
     * 未开始项目数
     */
    @Excel(name = "未开始项目数")
    private Long unstartedProjectCount;

    /**
     * 进行中项目数
     */
    @Excel(name = "进行中项目数")
    private Long ongoingProjectCount;

    /**
     * 已完成项目数
     */
    @Excel(name = "已完成项目数")
    private Long completedProjectCount;

    /**
     * 总积分数
     */
    @Excel(name = "总积分数")
    private BigDecimal totalPoints;

    /**
     * 累计得分
     */
    @Excel(name = "累计得分")
    private BigDecimal accumulatedScore;

    /**
     * 项目积分占比
     */
    @Excel(name = "项目积分占比")
    private BigDecimal projectPointsPercentage;


}


//package com.heli.tech.domain;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import com.ruoyi.common.annotation.Excel;
//import com.ruoyi.common.core.domain.BaseEntity;
//
///**
// * [技术]指标填报对象 Tech
// *
// * @author hong
// * @date 2024-04-27
// */
//@Data
//public class Tech extends BaseEntity {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * Tech_ID
//     */
//    private Long techId;
//
//    /**
//     * 年月
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date yearAndMonth;
//
//    /**
//     * 非标准单平均技术准备天数
//     */
//    @Excel(name = "非标准单平均技术准备天数")
//    private BigDecimal nonStandardAvgPreparationDays;
//
//    /**
//     * 非标准单条数
//     */
//    private Integer nonStandardNum;
//
//    /**
//     * 非标准单超时条数
//     */
//    private Integer nonStandardOvertimeNum;
//
//    /**
//     * 当月完成的计划
//     */
//    @Excel(name = "当月完成的计划")
//    private Long completedmonthlyPlancounts;
//
//    private Long completedPlanCount;
//
//    /**
//     * 研发项目计划进度完成率
//     */
//    @Excel(name = "研发项目计划进度完成率")
//    private BigDecimal prdscheduleCompletionrate;
//
//
//}
