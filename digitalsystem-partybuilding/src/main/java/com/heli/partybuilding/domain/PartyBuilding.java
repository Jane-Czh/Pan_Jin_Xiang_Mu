package com.heli.partybuilding.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: [党建]指标对象 PartyBuilding
 * @author: hong
 * @date: 2024/4/9 15:47
 **/
public class PartyBuilding extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * PB_ID
     */
    private String pbId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 党建排名
     */
    @Excel(name = "党建排名")
    private Integer Ranking;

    /**
     * 党建分数
     */
    @Excel(name = "党建分数")
    private Double score;

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

    public void setPbId(String pbId) {
        this.pbId = pbId;
    }

    public String getPbId() {
        return pbId;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setRanking(Integer Ranking) {
        this.Ranking = Ranking;
    }

    public Integer getRanking() {
        return Ranking;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScore() {
        return score;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("pbId", getPbId())
                .append("yearAndMonth", getYearAndMonth())
                .append("Ranking", getRanking())
                .append("score", getScore())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("updatedBy", getUpdatedBy())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
