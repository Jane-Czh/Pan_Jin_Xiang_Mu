package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 August_departments
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
public class AugustDepartments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long depId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String departmentName;

    /** 逻辑删除flag */
    @Excel(name = "逻辑删除flag")
    private Integer isDeleted;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setDepId(Long depId) 
    {
        this.depId = depId;
    }

    public Long getDepId() 
    {
        return depId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("depId", getDepId())
            .append("departmentName", getDepartmentName())
            .append("isDeleted", getIsDeleted())
            .append("description", getDescription())
            .toString();
    }
}
