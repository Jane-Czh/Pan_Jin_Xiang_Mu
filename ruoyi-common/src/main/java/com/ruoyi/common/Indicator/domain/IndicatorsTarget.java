package com.ruoyi.common.indicator.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 指标-目标值对象 indicators_target
 *
 * @author hong
 * @date 2024-07-30
 */
@Data
public class IndicatorsTarget extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long itId;

    /**
     * 指标中文名
     */
    @Excel(name = "指标中文名")
    private String indicatorNameCn;

    /**
     * 指标英文名
     */
    @Excel(name = "指标英文名")
    private String indicatorName;

    /**
     * 指标所属部门
     */
    @Excel(name = "指标所属部门")
    private String indicatorDept;

    /**
     * 年
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年", width = 30, dateFormat = "yyyy-MM-dd")
    private Date natureYear;

    /**
     * 目标值
     */
    @Excel(name = "目标值")
    private BigDecimal targetValue;

    /**
     * 目标下限
     */
    @Excel(name = "目标下限")
    private BigDecimal targetLowerLimit;

    /**
     * 目标上限
     */
    @Excel(name = "目标上限")
    private BigDecimal targetUpperLimit;




}
