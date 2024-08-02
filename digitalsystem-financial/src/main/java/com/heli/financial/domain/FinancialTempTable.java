package com.heli.financial.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 中转表对象 Financial_Temp_Table
 *
 * @author hong
 * @date 2024-07-31
 */
public class FinancialTempTable {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelIgnore
    private Long ftId;

    /**
     * 行名
     */
    @ExcelProperty(index = 0)
    private String rowName;

    /**
     * 数值
     */
    @ExcelProperty(index = 1)
    private String columnNum;

    public void setFtId(Long ftId) {
        this.ftId = ftId;
    }

    public Long getFtId() {
        return ftId;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getRowName() {
        return rowName;
    }

    public void setColumnNum(String columnNum) {
        this.columnNum = columnNum;
    }

    public String getColumnNum() {
        return columnNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ftId", getFtId())
                .append("rowName", getRowName())
                .append("columnNum", getColumnNum())
                .toString();
    }
}
