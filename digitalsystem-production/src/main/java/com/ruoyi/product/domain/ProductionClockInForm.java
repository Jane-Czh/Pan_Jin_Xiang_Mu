package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工打卡表对象 Production_Clock_in_form
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public class ProductionClockInForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long pcifId;

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

    /** 第一次上班打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "第一次上班打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date firstTimeClockingInAtWork;

    /** 第一次下班打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "第一次下班打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date firstTimeClockingInAfterWork;

    /** 第二次上班打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "第二次上班打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date secondTimeClockingInAtWork;

    /** 第二次下班打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "第二次下班打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date secondTimeClockingInAfterWork;

    /** 正常上班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "正常上班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date normalWorkingHours;

    /** 正常下班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "正常下班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date normalClosingTime;

    public void setPcifId(Long pcifId) 
    {
        this.pcifId = pcifId;
    }

    public Long getPcifId() 
    {
        return pcifId;
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
    public void setFirstTimeClockingInAtWork(Date firstTimeClockingInAtWork) 
    {
        this.firstTimeClockingInAtWork = firstTimeClockingInAtWork;
    }

    public Date getFirstTimeClockingInAtWork() 
    {
        return firstTimeClockingInAtWork;
    }
    public void setFirstTimeClockingInAfterWork(Date firstTimeClockingInAfterWork) 
    {
        this.firstTimeClockingInAfterWork = firstTimeClockingInAfterWork;
    }

    public Date getFirstTimeClockingInAfterWork() 
    {
        return firstTimeClockingInAfterWork;
    }
    public void setSecondTimeClockingInAtWork(Date secondTimeClockingInAtWork) 
    {
        this.secondTimeClockingInAtWork = secondTimeClockingInAtWork;
    }

    public Date getSecondTimeClockingInAtWork() 
    {
        return secondTimeClockingInAtWork;
    }
    public void setSecondTimeClockingInAfterWork(Date secondTimeClockingInAfterWork) 
    {
        this.secondTimeClockingInAfterWork = secondTimeClockingInAfterWork;
    }

    public Date getSecondTimeClockingInAfterWork() 
    {
        return secondTimeClockingInAfterWork;
    }
    public void setNormalWorkingHours(Date normalWorkingHours) 
    {
        this.normalWorkingHours = normalWorkingHours;
    }

    public Date getNormalWorkingHours() 
    {
        return normalWorkingHours;
    }
    public void setNormalClosingTime(Date normalClosingTime) 
    {
        this.normalClosingTime = normalClosingTime;
    }

    public Date getNormalClosingTime() 
    {
        return normalClosingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pcifId", getPcifId())
            .append("Number", getNumber())
            .append("idNumber", getIdNumber())
            .append("Name", getName())
            .append("Gender", getGender())
            .append("firstTimeClockingInAtWork", getFirstTimeClockingInAtWork())
            .append("firstTimeClockingInAfterWork", getFirstTimeClockingInAfterWork())
            .append("secondTimeClockingInAtWork", getSecondTimeClockingInAtWork())
            .append("secondTimeClockingInAfterWork", getSecondTimeClockingInAfterWork())
            .append("normalWorkingHours", getNormalWorkingHours())
            .append("normalClosingTime", getNormalClosingTime())
            .toString();
    }
}
