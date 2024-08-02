package com.ruoyi.common.indicator.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指标-索引对象 indicators_index
 *
 * @author ruoyi
 * @date 2024-07-30
 */
public class IndicatorsIndex extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Integer indicatorId;

    /**
     * $column.columnComment
     */

    private String indicatorNameCn;

    /**
     * $column.columnComment
     */

    private String indicatorNameEn;

    /**
     * $column.columnComment
     */

    private String indicatorDeptCn;

    /**
     * $column.columnComment
     */

    private String indicatorDeptEn;

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorNameCn(String indicatorNameCn) {
        this.indicatorNameCn = indicatorNameCn;
    }

    public String getIndicatorNameCn() {
        return indicatorNameCn;
    }

    public void setIndicatorNameEn(String indicatorNameEn) {
        this.indicatorNameEn = indicatorNameEn;
    }

    public String getIndicatorNameEn() {
        return indicatorNameEn;
    }

    public void setIndicatorDeptCn(String indicatorDeptCn) {
        this.indicatorDeptCn = indicatorDeptCn;
    }

    public String getIndicatorDeptCn() {
        return indicatorDeptCn;
    }

    public void setIndicatorDeptEn(String indicatorDeptEn) {
        this.indicatorDeptEn = indicatorDeptEn;
    }

    public String getIndicatorDeptEn() {
        return indicatorDeptEn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("indicatorId", getIndicatorId())
                .append("indicatorNameCn", getIndicatorNameCn())
                .append("indicatorNameEn", getIndicatorNameEn())
                .append("indicatorDeptCn", getIndicatorDeptCn())
                .append("indicatorDeptEn", getIndicatorDeptEn())
                .toString();
    }
}
