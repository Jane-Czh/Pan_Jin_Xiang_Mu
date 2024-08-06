package com.heli.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目信息回收对象 project_info_recycle
 * 
 * @author Teandron
 * @date 2024-08-06
 */
public class ProjectInfoRecycle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id(主键) */
    private Long projectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 项目类别 */
    @Excel(name = "项目类别")
    private String category;

    /** 项目等级 */
    @Excel(name = "项目等级")
    private String level;

    /** 主责部门 */
    @Excel(name = "主责部门")
    private String department;

    /** 负责人 */
    @Excel(name = "负责人")
    private String manager;

    /** 项目组成员 */
    @Excel(name = "项目组成员")
    private String teamMembers;

    /** 项目现状 */
    @Excel(name = "项目现状")
    private String currentStatus;

    /** 项目目标 */
    @Excel(name = "项目目标")
    private String goal;

    /** 项目范围 */
    @Excel(name = "项目范围")
    private String scope;

    /** 立项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "立项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 计划结项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedCompletionTime;

    /** 项目归属 */
    @Excel(name = "项目归属")
    private String attribute;

    /** 历史项目 */
    @Excel(name = "历史项目")
    private String oldProjectId;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String status;

    /** 项目总进度 */
    @Excel(name = "项目总进度")
    private String progressAlloverProgress;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String description;

    /** 导入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "导入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date importDate;

    /** 描述 */
    @Excel(name = "描述")
    private String remake;

    /** 关联时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "关联时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date associationDate;

    /** 项目进度 */
    @Excel(name = "项目进度")
    private String progress;

    /** 已过天数(自动计算) */
    @Excel(name = "已过天数(自动计算)")
    private Long daysPassed;

    /** 剩余天数(自动计算) */
    @Excel(name = "剩余天数(自动计算)")
    private Long daysRemaining;

    /** 完成内容概述 */
    @Excel(name = "完成内容概述")
    private String completionSummary;

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setTeamMembers(String teamMembers) 
    {
        this.teamMembers = teamMembers;
    }

    public String getTeamMembers() 
    {
        return teamMembers;
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
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setPlannedCompletionTime(Date plannedCompletionTime) 
    {
        this.plannedCompletionTime = plannedCompletionTime;
    }

    public Date getPlannedCompletionTime() 
    {
        return plannedCompletionTime;
    }
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setOldProjectId(String oldProjectId) 
    {
        this.oldProjectId = oldProjectId;
    }

    public String getOldProjectId() 
    {
        return oldProjectId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setProgressAlloverProgress(String progressAlloverProgress) 
    {
        this.progressAlloverProgress = progressAlloverProgress;
    }

    public String getProgressAlloverProgress() 
    {
        return progressAlloverProgress;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setImportDate(Date importDate) 
    {
        this.importDate = importDate;
    }

    public Date getImportDate() 
    {
        return importDate;
    }
    public void setRemake(String remake) 
    {
        this.remake = remake;
    }

    public String getRemake() 
    {
        return remake;
    }
    public void setAssociationDate(Date associationDate) 
    {
        this.associationDate = associationDate;
    }

    public Date getAssociationDate() 
    {
        return associationDate;
    }
    public void setProgress(String progress) 
    {
        this.progress = progress;
    }

    public String getProgress() 
    {
        return progress;
    }
    public void setDaysPassed(Long daysPassed) 
    {
        this.daysPassed = daysPassed;
    }

    public Long getDaysPassed() 
    {
        return daysPassed;
    }
    public void setDaysRemaining(Long daysRemaining) 
    {
        this.daysRemaining = daysRemaining;
    }

    public Long getDaysRemaining() 
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
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("category", getCategory())
            .append("level", getLevel())
            .append("department", getDepartment())
            .append("manager", getManager())
            .append("teamMembers", getTeamMembers())
            .append("currentStatus", getCurrentStatus())
            .append("goal", getGoal())
            .append("scope", getScope())
            .append("startDate", getStartDate())
            .append("plannedCompletionTime", getPlannedCompletionTime())
            .append("attribute", getAttribute())
            .append("oldProjectId", getOldProjectId())
            .append("status", getStatus())
            .append("progressAlloverProgress", getProgressAlloverProgress())
            .append("description", getDescription())
            .append("importDate", getImportDate())
            .append("remake", getRemake())
            .append("associationDate", getAssociationDate())
            .append("progress", getProgress())
            .append("daysPassed", getDaysPassed())
            .append("daysRemaining", getDaysRemaining())
            .append("completionSummary", getCompletionSummary())
            .toString();
    }
}
