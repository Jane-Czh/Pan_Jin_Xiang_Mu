package com.ruoyi.Enterprisemanagement.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工统计对象 Enterprise_Management_function_Employee_statistics
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
public class EnterpriseManagementFunctionEmployeeStatistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long emesId;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 性别 */
    @Excel(name = "性别")
    private String Gender;

    /** 员工组 */
    @Excel(name = "员工组")
    private String employeeGroup;

    /** 实习生总人数 */
    @Excel(name = "实习生总人数")
    private Long totalNumberOfInterns;

    /** 劳动派遣总人数 */
    @Excel(name = "劳动派遣总人数")
    private Long ttotalNumberOfLaborDispatch;

    /** 合同工总人数 */
    @Excel(name = "合同工总人数")
    private Long totalNumberOfContracted;

    public void setEmesId(Long emesId) 
    {
        this.emesId = emesId;
    }

    public Long getEmesId() 
    {
        return emesId;
    }
    public void setEmployeeId(String employeeId) 
    {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() 
    {
        return employeeId;
    }
    public void setName(String Name) 
    {
        this.Name = Name;
    }

    public String getName() 
    {
        return Name;
    }
    public void setGender(String Gender) 
    {
        this.Gender = Gender;
    }

    public String getGender() 
    {
        return Gender;
    }
    public void setEmployeeGroup(String employeeGroup) 
    {
        this.employeeGroup = employeeGroup;
    }

    public String getEmployeeGroup() 
    {
        return employeeGroup;
    }
    public void setTotalNumberOfInterns(Long totalNumberOfInterns) 
    {
        this.totalNumberOfInterns = totalNumberOfInterns;
    }

    public Long getTotalNumberOfInterns() 
    {
        return totalNumberOfInterns;
    }
    public void setTtotalNumberOfLaborDispatch(Long ttotalNumberOfLaborDispatch) 
    {
        this.ttotalNumberOfLaborDispatch = ttotalNumberOfLaborDispatch;
    }

    public Long getTtotalNumberOfLaborDispatch() 
    {
        return ttotalNumberOfLaborDispatch;
    }
    public void setTotalNumberOfContracted(Long totalNumberOfContracted) 
    {
        this.totalNumberOfContracted = totalNumberOfContracted;
    }

    public Long getTotalNumberOfContracted() 
    {
        return totalNumberOfContracted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("emesId", getEmesId())
            .append("employeeId", getEmployeeId())
            .append("Name", getName())
            .append("Gender", getGender())
            .append("employeeGroup", getEmployeeGroup())
            .append("totalNumberOfInterns", getTotalNumberOfInterns())
            .append("ttotalNumberOfLaborDispatch", getTtotalNumberOfLaborDispatch())
            .append("totalNumberOfContracted", getTotalNumberOfContracted())
            .toString();
    }
}
