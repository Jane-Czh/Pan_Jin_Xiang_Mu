package com.heli.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目基本信息表对象 Project_Info_table
 * 
 * @author Teandron
 * @date 2024-04-26
 */
public class ProjectInfoTable extends BaseEntity
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

    /** 承接属性 */
    @Excel(name = "承接属性")
    private String attribute;

    /** 项目描述 */
    @Excel(name = "项目描述")
    private String description;

    /** 立项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "立项时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目总进度 */
    @Excel(name = "项目总进度")
    private String progressAlloverProgress;

    /** 导入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "导入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date importDate;

    /** 描述 */
    @Excel(name = "描述")
    private String remake;

    /** 历史项目 */
    @Excel(name = "历史项目")
    private Long oldProjectId;

    /** 关联时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "关联时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date associationDate;

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
    public void setAttribute(String attribute) 
    {
        this.attribute = attribute;
    }

    public String getAttribute() 
    {
        return attribute;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setProgressAlloverProgress(String progressAlloverProgress) 
    {
        this.progressAlloverProgress = progressAlloverProgress;
    }

    public String getProgressAlloverProgress() 
    {
        return progressAlloverProgress;
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
    public void setOldProjectId(Long oldProjectId) 
    {
        this.oldProjectId = oldProjectId;
    }

    public Long getOldProjectId() 
    {
        return oldProjectId;
    }
    public void setAssociationDate(Date associationDate) 
    {
        this.associationDate = associationDate;
    }

    public Date getAssociationDate() 
    {
        return associationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("category", getCategory())
            .append("level", getLevel())
            .append("department", getDepartment())
            .append("attribute", getAttribute())
            .append("description", getDescription())
            .append("startDate", getStartDate())
            .append("progressAlloverProgress", getProgressAlloverProgress())
            .append("importDate", getImportDate())
            .append("remake", getRemake())
            .append("oldProjectId", getOldProjectId())
            .append("associationDate", getAssociationDate())
            .toString();
    }
}
