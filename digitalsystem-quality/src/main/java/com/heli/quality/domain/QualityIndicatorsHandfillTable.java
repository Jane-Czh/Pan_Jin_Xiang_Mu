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
 * [质量]指标填报对象 Quality_Indicators_HandFill_table
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Data
public class QualityIndicatorsHandfillTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long qihfId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 外部质量损失率的分子(手动填报)
     */
    @Excel(name = "外部质量损失率的分子(手动填报)")
    private BigDecimal moleculeExternalMassLossRate;

    /**
     * 外部质量损失率
     */
    @Excel(name = "外部质量损失率")
    private BigDecimal externalMassLossRate;

    /**
     * 质量考核季度排名
     */
    @Excel(name = "质量考核季度排名")
    private Long quarterlyRank;

    /**
     * 平均无故障时间
     */
    private BigDecimal meantimeWithoutFailure;

    /**
     * 供应商不合格件返厂及时率
     */
    @Excel(name = "供应商不合格件返厂及时率")
    private BigDecimal intimeReturnrate;

    /**
     * 班组自查合格率
     */
    @Excel(name = "班组自查合格率")
    private BigDecimal selfcheckPassrate;

    /**
     * 下道工序反馈合格率
     */
    @Excel(name = "下道工序反馈合格率")
    private BigDecimal nextprocessFeedbackPassrate;

}
