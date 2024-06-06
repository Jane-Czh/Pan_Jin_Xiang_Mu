package com.ruoyi.file.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 制度执行率对象 Regulations_Implementation_table
 *
 * @author ruoyi
 * @date 2024-05-30
 */
public class RegulationsImplementationTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Date startTime;

    private Date endTime;

    /** 主键 */
    private Long id;

    /** 年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /** 实际执行制度次数 */
    @Excel(name = "实际执行制度次数")
    private Long actualImplementedTimes;

    /** 应执行制度次数 */
    @Excel(name = "应执行制度次数")
    private Long shouldImplementedTimes;

    /** 制度执行率 */
    @Excel(name = "制度执行率")
    private BigDecimal regulationsImplementationRate;

    // startTime和endTime的getter和setter
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setYearAndMonth(Date yearAndMonth)
    {
        this.yearAndMonth = yearAndMonth;
    }

    public Date getYearAndMonth()
    {
        return yearAndMonth;
    }
    public void setActualImplementedTimes(Long actualImplementedTimes)
    {
        this.actualImplementedTimes = actualImplementedTimes;
    }

    public Long getActualImplementedTimes()
    {
        return actualImplementedTimes;
    }
    public void setShouldImplementedTimes(Long shouldImplementedTimes)
    {
        this.shouldImplementedTimes = shouldImplementedTimes;
    }

    public Long getShouldImplementedTimes()
    {
        return shouldImplementedTimes;
    }
    public void setRegulationsImplementationRate(BigDecimal regulationsImplementationRate)
    {
        this.regulationsImplementationRate = regulationsImplementationRate;
    }

    public BigDecimal getRegulationsImplementationRate()
    {
        return regulationsImplementationRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("yearAndMonth", getYearAndMonth())
                .append("actualImplementedTimes", getActualImplementedTimes())
                .append("shouldImplementedTimes", getShouldImplementedTimes())
                .append("regulationsImplementationRate", getRegulationsImplementationRate())
                .toString();
    }
}
