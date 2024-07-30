package com.ruoyi.common.indicator.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class IndicatorsTarget extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long itId;

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

    public void setItId(Long itId) {
        this.itId = itId;
    }

    public Long getItId() {
        return itId;
    }

    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    public String getIndicatorName() {
        return indicatorName;
    }

    public void setIndicatorDept(String indicatorDept) {
        this.indicatorDept = indicatorDept;
    }

    public String getIndicatorDept() {
        return indicatorDept;
    }

    public void setNatureYear(Date natureYear) {
        this.natureYear = natureYear;
    }

    public Date getNatureYear() {
        return natureYear;
    }

    public void setTargetValue(BigDecimal targetValue) {
        this.targetValue = targetValue;
    }

    public BigDecimal getTargetValue() {
        return targetValue;
    }

    public void setTargetLowerLimit(BigDecimal targetLowerLimit) {
        this.targetLowerLimit = targetLowerLimit;
    }

    public BigDecimal getTargetLowerLimit() {
        return targetLowerLimit;
    }

    public void setTargetUpperLimit(BigDecimal targetUpperLimit) {
        this.targetUpperLimit = targetUpperLimit;
    }

    public BigDecimal getTargetUpperLimit() {
        return targetUpperLimit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("itId", getItId())
                .append("indicatorName", getIndicatorName())
                .append("indicatorDept", getIndicatorDept())
                .append("natureYear", getNatureYear())
                .append("targetValue", getTargetValue())
                .append("targetLowerLimit", getTargetLowerLimit())
                .append("targetUpperLimit", getTargetUpperLimit())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
