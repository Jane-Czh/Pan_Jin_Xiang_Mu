package com.ruoyi.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目修改记录对象 project_info_recode
 * 
 * @author Teandron
 * @date 2024-09-17
 */
public class ProjectInfoRecode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long recodeId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 主责部门 */
    @Excel(name = "主责部门")
    private String department;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date importDate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date associationDate;

    /** 项目总进度 */
    @Excel(name = "项目总进度")
    private String progressAlloverProgress;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String status;

    /** 是否有相关方案或计划 */
    @Excel(name = "是否有相关方案或计划")
    private String remake;

    /** 完成工作事项 */
    @Excel(name = "完成工作事项")
    private String completionSummary;

    /** 交付物 */
    @Excel(name = "交付物")
    private String progress;

    /** 关键事项说明 */
    @Excel(name = "关键事项说明")
    private String description;

    /** 待定1 */
    @Excel(name = "待定1")
    private String determined1;

    /** 待定2 */
    @Excel(name = "待定2")
    private String determined2;

    public void setRecodeId(Long recodeId) 
    {
        this.recodeId = recodeId;
    }

    public Long getRecodeId() 
    {
        return recodeId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setDepartment(String department) 
    {
        this.department = department;
    }

    public String getDepartment() 
    {
        return department;
    }
    public void setImportDate(Date importDate) 
    {
        this.importDate = importDate;
    }

    public Date getImportDate() 
    {
        return importDate;
    }
    public void setAssociationDate(Date associationDate) 
    {
        this.associationDate = associationDate;
    }

    public Date getAssociationDate() 
    {
        return associationDate;
    }
    public void setProgressAlloverProgress(String progressAlloverProgress) 
    {
        this.progressAlloverProgress = progressAlloverProgress;
    }

    public String getProgressAlloverProgress() 
    {
        return progressAlloverProgress;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setRemake(String remake) 
    {
        this.remake = remake;
    }

    public String getRemake() 
    {
        return remake;
    }
    public void setCompletionSummary(String completionSummary) 
    {
        this.completionSummary = completionSummary;
    }

    public String getCompletionSummary() 
    {
        return completionSummary;
    }
    public void setProgress(String progress) 
    {
        this.progress = progress;
    }

    public String getProgress() 
    {
        return progress;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setDetermined1(String determined1) 
    {
        this.determined1 = determined1;
    }

    public String getDetermined1() 
    {
        return determined1;
    }
    public void setDetermined2(String determined2) 
    {
        this.determined2 = determined2;
    }

    public String getDetermined2() 
    {
        return determined2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recodeId", getRecodeId())
            .append("projectName", getProjectName())
            .append("department", getDepartment())
            .append("importDate", getImportDate())
            .append("associationDate", getAssociationDate())
            .append("progressAlloverProgress", getProgressAlloverProgress())
            .append("status", getStatus())
            .append("remake", getRemake())
            .append("completionSummary", getCompletionSummary())
            .append("progress", getProgress())
            .append("description", getDescription())
            .append("determined1", getDetermined1())
            .append("updateTime", getUpdateTime())
            .append("determined2", getDetermined2())
            .toString();
    }
}
