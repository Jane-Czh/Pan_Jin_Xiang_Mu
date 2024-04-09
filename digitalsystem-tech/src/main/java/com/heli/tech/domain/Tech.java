package com.heli.tech.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: [技术]指标对象 Tech
 * @author: hong
 * @date: 2024/4/9 15:53
 **/
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
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    /**
     * 非标准单平均技术准备天数
     */
    @Excel(name = "非标准单平均技术准备天数")
    private Long nonStandardAvgPreparationDays;

    /**
     * 当月完成的计划
     */
    @Excel(name = "当月完成的计划")
    private Long completedmonthlyPlancounts;

    /**
     * 年度总计划（年初填报一次）
     */
    @Excel(name = "年度总计划", readConverterExp = "年=初填报一次")
    private Long annualPlancounts;

    /**
     * 研发项目计划进度完成率
     */
    @Excel(name = "研发项目计划进度完成率")
    private Double prdscheduleCompletionrate;

    public void setTechId(Long techId) {
        this.techId = techId;
    }

    public Long getTechId() {
        return techId;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setNonStandardAvgPreparationDays(Long nonStandardAvgPreparationDays) {
        this.nonStandardAvgPreparationDays = nonStandardAvgPreparationDays;
    }

    public Long getNonStandardAvgPreparationDays() {
        return nonStandardAvgPreparationDays;
    }

    public void setCompletedmonthlyPlancounts(Long completedmonthlyPlancounts) {
        this.completedmonthlyPlancounts = completedmonthlyPlancounts;
    }

    public Long getCompletedmonthlyPlancounts() {
        return completedmonthlyPlancounts;
    }

    public void setAnnualPlancounts(Long annualPlancounts) {
        this.annualPlancounts = annualPlancounts;
    }

    public Long getAnnualPlancounts() {
        return annualPlancounts;
    }

    public void setPrdscheduleCompletionrate(Double prdscheduleCompletionrate) {
        this.prdscheduleCompletionrate = prdscheduleCompletionrate;
    }

    public Double getPrdscheduleCompletionrate() {
        return prdscheduleCompletionrate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("techId", getTechId())
                .append("yearAndMonth", getYearAndMonth())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("updatedBy", getUpdatedBy())
                .append("updatedTime", getUpdatedTime())
                .append("nonStandardAvgPreparationDays", getNonStandardAvgPreparationDays())
                .append("completedmonthlyPlancounts", getCompletedmonthlyPlancounts())
                .append("annualPlancounts", getAnnualPlancounts())
                .append("prdscheduleCompletionrate", getPrdscheduleCompletionrate())
                .toString();
    }
}
