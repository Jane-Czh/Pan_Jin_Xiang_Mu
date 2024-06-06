package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 加班申请对象 Production_Overtime_application_form
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public class ProductionOvertimeApplicationForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long poafId;

    /** 序号 */
    @Excel(name = "序号")
    private Long Number;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String idNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 性别 */
    @Excel(name = "性别")
    private String Gender;

    /** 申请加班开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请加班开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date applicationForOvertimeStartTime;

    /** 申请加班结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "申请加班结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date applicationForOvertimeEndTime;

    public void setPoafId(Long poafId) 
    {
        this.poafId = poafId;
    }

    public Long getPoafId() 
    {
        return poafId;
    }
    public void setNumber(Long Number) 
    {
        this.Number = Number;
    }

    public Long getNumber() 
    {
        return Number;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
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
    public void setApplicationForOvertimeStartTime(Date applicationForOvertimeStartTime) 
    {
        this.applicationForOvertimeStartTime = applicationForOvertimeStartTime;
    }

    public Date getApplicationForOvertimeStartTime() 
    {
        return applicationForOvertimeStartTime;
    }
    public void setApplicationForOvertimeEndTime(Date applicationForOvertimeEndTime) 
    {
        this.applicationForOvertimeEndTime = applicationForOvertimeEndTime;
    }

    public Date getApplicationForOvertimeEndTime() 
    {
        return applicationForOvertimeEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("poafId", getPoafId())
            .append("Number", getNumber())
            .append("idNumber", getIdNumber())
            .append("Name", getName())
            .append("Gender", getGender())
            .append("applicationForOvertimeStartTime", getApplicationForOvertimeStartTime())
            .append("applicationForOvertimeEndTime", getApplicationForOvertimeEndTime())
            .toString();
    }
}
