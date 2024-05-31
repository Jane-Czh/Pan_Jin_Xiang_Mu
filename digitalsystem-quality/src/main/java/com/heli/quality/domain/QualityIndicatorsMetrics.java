package com.heli.quality.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 质量指标-统计对象 Quality_Indicators_Metrics
 *
 * @author hong
 * @date 2024-05-21
 */
@Data
public class QualityIndicatorsMetrics extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long qcId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 当月反馈新车病车数
     */
    @Excel(name = "当月反馈新车病车数")
    private Long newCarDefects;

    /**
     * 三包期内新车返修率
     */
    @Excel(name = "三包期内新车返修率")
    private BigDecimal warrantyRepairRate;

    /**
     * 月度售后质量问题总数
     */
    @Excel(name = "月度售后质量问题总数")
    private Long monthlyAfterSalesIssues;

    /**
     * 三包期内整车月度返修率
     */
    @Excel(name = "三包期内整车月度返修率")
    private BigDecimal warrantyVehicleRepairRate;

    /**
     * 外部质量损失率
     */
    @Excel(name = "外部质量损失率")
    private BigDecimal externalLossRate;

    /**
     * 售后问题生产责任次数
     */
    @Excel(name = "售后问题生产责任次数")
    private Long productionLiabilityIssues;

}
