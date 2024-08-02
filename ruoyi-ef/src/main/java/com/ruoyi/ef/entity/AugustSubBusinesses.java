package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 August_sub_businesses
 * 
 * @author ruoyi
 * @date 2024-08-01
 */
public class AugustSubBusinesses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long subbId;

    /** 细分业务名称 */
    @Excel(name = "细分业务名称")
    private String subBusinessesName;

    /** 上级部门 */
    @Excel(name = "上级部门")
    private String parentDepartment;

    /** 上级业务模块 */
    @Excel(name = "上级业务模块")
    private String parentModule;

    /** 逻辑删除flag */
    @Excel(name = "逻辑删除flag")
    private Integer isDeleted;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    public void setSubbId(Long subbId) 
    {
        this.subbId = subbId;
    }

    public Long getSubbId() 
    {
        return subbId;
    }
    public void setSubBusinessesName(String subBusinessesName) 
    {
        this.subBusinessesName = subBusinessesName;
    }

    public String getSubBusinessesName() 
    {
        return subBusinessesName;
    }
    public void setParentDepartment(String parentDepartment) 
    {
        this.parentDepartment = parentDepartment;
    }

    public String getParentDepartment() 
    {
        return parentDepartment;
    }
    public void setParentModule(String parentModule) 
    {
        this.parentModule = parentModule;
    }

    public String getParentModule() 
    {
        return parentModule;
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
            .append("subbId", getSubbId())
            .append("subBusinessesName", getSubBusinessesName())
            .append("parentDepartment", getParentDepartment())
            .append("parentModule", getParentModule())
            .append("isDeleted", getIsDeleted())
            .append("description", getDescription())
            .toString();
    }
}
