package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 August_business_modules
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
public class AugustBusinessModules extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long bmId;

    /** 业务模块名称 */
    @Excel(name = "业务模块名称")
    private String moduleName;

    /** 上级部门 */
    @Excel(name = "上级部门")
    private String parentDepartment;

    /** 逻辑删除flag */
    @Excel(name = "逻辑删除flag")
    private Integer isDeleted;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setBmId(Long bmId) 
    {
        this.bmId = bmId;
    }

    public Long getBmId() 
    {
        return bmId;
    }
    public void setModuleName(String moduleName) 
    {
        this.moduleName = moduleName;
    }

    public String getModuleName() 
    {
        return moduleName;
    }
    public void setParentDepartment(String parentDepartment) 
    {
        this.parentDepartment = parentDepartment;
    }

    public String getParentDepartment() 
    {
        return parentDepartment;
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
            .append("bmId", getBmId())
            .append("moduleName", getModuleName())
            .append("parentDepartment", getParentDepartment())
            .append("isDeleted", getIsDeleted())
            .append("description", getDescription())
            .toString();
    }
}
