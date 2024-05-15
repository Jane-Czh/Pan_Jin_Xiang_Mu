package com.heli.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目详情对象 Project_Details_table
 * 
 * @author Teandron
 * @date 2024-04-26
 */
public class ProjectDetailsTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id(主键) */
    private Long detailsId;

    /** 项目(外键) */
    @Excel(name = "项目(外键)")
    private Long projectId;

    /** 负责人 */
    @Excel(name = "负责人")
    private String manager;

    /** 立项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "立项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 组成员 */
    @Excel(name = "组成员")
    private String teamMembers;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String status;

    /** 项目进度 */
    @Excel(name = "项目进度")
    private String progress;

    /** 项目现状 */
    @Excel(name = "项目现状")
    private String currentStatus;

    /** 目标 */
    @Excel(name = "目标")
    private String goal;

    /** 范围 */
    @Excel(name = "范围")
    private String scope;

    /** 计划结项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedCompletionTime;

    /** 已过天数(自动计算) */
    @Excel(name = "已过天数(自动计算)")
    private Integer daysPassed;

    /** 剩余天数(自动计算) */
    @Excel(name = "剩余天数(自动计算)")
    private Integer daysRemaining;

    /** 完成内容概述 */
    @Excel(name = "完成内容概述")
    private String completionSummary;

    public void setDetailsId(Long detailsId) 
    {
        this.detailsId = detailsId;
    }

    public Long getDetailsId() 
    {
        return detailsId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setTeamMembers(String teamMembers) 
    {
        this.teamMembers = teamMembers;
    }

    public String getTeamMembers() 
    {
        return teamMembers;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setProgress(String progress) 
    {
        this.progress = progress;
    }

    public String getProgress() 
    {
        return progress;
    }
    public void setCurrentStatus(String currentStatus) 
    {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatus() 
    {
        return currentStatus;
    }
    public void setGoal(String goal) 
    {
        this.goal = goal;
    }

    public String getGoal() 
    {
        return goal;
    }
    public void setScope(String scope) 
    {
        this.scope = scope;
    }

    public String getScope() 
    {
        return scope;
    }
    public void setPlannedCompletionTime(Date plannedCompletionTime) 
    {
        this.plannedCompletionTime = plannedCompletionTime;
    }

    public Date getPlannedCompletionTime() 
    {
        return plannedCompletionTime;
    }
    public void setDaysPassed(Integer daysPassed) 
    {
        this.daysPassed = daysPassed;
    }

    public Integer getDaysPassed() 
    {
        return daysPassed;
    }
    public void setDaysRemaining(Integer daysRemaining) 
    {
        this.daysRemaining = daysRemaining;
    }

    public Integer getDaysRemaining() 
    {
        return daysRemaining;
    }
    public void setCompletionSummary(String completionSummary) 
    {
        this.completionSummary = completionSummary;
    }

    public String getCompletionSummary() 
    {
        return completionSummary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailsId", getDetailsId())
            .append("projectId", getProjectId())
            .append("manager", getManager())
            .append("startDate", getStartDate())
            .append("teamMembers", getTeamMembers())
            .append("status", getStatus())
            .append("progress", getProgress())
            .append("currentStatus", getCurrentStatus())
            .append("goal", getGoal())
            .append("scope", getScope())
            .append("plannedCompletionTime", getPlannedCompletionTime())
            .append("daysPassed", getDaysPassed())
            .append("daysRemaining", getDaysRemaining())
            .append("completionSummary", getCompletionSummary())
            .toString();
    }
}
