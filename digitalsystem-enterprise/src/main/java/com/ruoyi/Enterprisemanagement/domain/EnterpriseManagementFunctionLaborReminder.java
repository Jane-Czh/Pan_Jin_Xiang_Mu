package com.ruoyi.Enterprisemanagement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 企管劳动合同到期提醒对象 Enterprise_Management_function_labor_reminder
 *
 * @author ruoyi
 * @date 2024-04-23
 */
public class EnterpriseManagementFunctionLaborReminder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long emlwId;

    /** 编号 */
    @Excel(name = "编号")
    private Long Number;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 科室 */
    @Excel(name = "科室")
    private String Document;

    /** 就职状态 */
    @Excel(name = "就职状态")
    private String employmentStatus;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 第一次合同起始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第一次合同起始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstContractPeriodStart;

    /** 第一次合同结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第一次合同结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date firstContractPeriodEnd;

    /** 第一次合同状况 */
    @Excel(name = "第一次合同状况")
    private String contractStatusFirstly;

    /** 第二次合同起始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第二次合同起始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date secondContractPeriodStart;

    /** 第二次合同结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第二次合同结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date secondContractPeriodEnd;

    /** 第二次合同状况 */
    @Excel(name = "第二次合同状况")
    private String contractStatusSecondly;

    /** 第三次合同起始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第三次合同起始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date thirdContractPeriodStart;

    /** 第三次合同结束 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "第三次合同结束", width = 30, dateFormat = "yyyy-MM-dd")
    private Date thirdContractPeriodEnd;

    /** 第三次合同状况 */
    @Excel(name = "第三次合同状况")
    private String contractStatusThirdly;

    /** 是否预警 */
    @Excel(name = "是否预警")
    private String Warn;

    public void setEmlwId(Long emlwId)
    {
        this.emlwId = emlwId;
    }

    public Long getEmlwId()
    {
        return emlwId;
    }
    public void setNumber(Long Number)
    {
        this.Number = Number;
    }

    public Long getNumber()
    {
        return Number;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getName()
    {
        return Name;
    }
    public void setDocument(String Document)
    {
        this.Document = Document;
    }

    public String getDocument()
    {
        return Document;
    }
    public void setEmploymentStatus(String employmentStatus)
    {
        this.employmentStatus = employmentStatus;
    }

    public String getEmploymentStatus()
    {
        return employmentStatus;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setFirstContractPeriodStart(Date firstContractPeriodStart)
    {
        this.firstContractPeriodStart = firstContractPeriodStart;
    }

    public Date getFirstContractPeriodStart()
    {
        return firstContractPeriodStart;
    }
    public void setFirstContractPeriodEnd(Date firstContractPeriodEnd)
    {
        this.firstContractPeriodEnd = firstContractPeriodEnd;
    }

    public Date getFirstContractPeriodEnd()
    {
        return firstContractPeriodEnd;
    }
    public void setContractStatusFirstly(String contractStatusFirstly)
    {
        this.contractStatusFirstly = contractStatusFirstly;
    }

    public String getContractStatusFirstly()
    {
        return contractStatusFirstly;
    }
    public void setSecondContractPeriodStart(Date secondContractPeriodStart)
    {
        this.secondContractPeriodStart = secondContractPeriodStart;
    }

    public Date getSecondContractPeriodStart()
    {
        return secondContractPeriodStart;
    }
    public void setSecondContractPeriodEnd(Date secondContractPeriodEnd)
    {
        this.secondContractPeriodEnd = secondContractPeriodEnd;
    }

    public Date getSecondContractPeriodEnd()
    {
        return secondContractPeriodEnd;
    }
    public void setContractStatusSecondly(String contractStatusSecondly)
    {
        this.contractStatusSecondly = contractStatusSecondly;
    }

    public String getContractStatusSecondly()
    {
        return contractStatusSecondly;
    }
    public void setThirdContractPeriodStart(Date thirdContractPeriodStart)
    {
        this.thirdContractPeriodStart = thirdContractPeriodStart;
    }

    public Date getThirdContractPeriodStart()
    {
        return thirdContractPeriodStart;
    }
    public void setThirdContractPeriodEnd(Date thirdContractPeriodEnd)
    {
        this.thirdContractPeriodEnd = thirdContractPeriodEnd;
    }

    public Date getThirdContractPeriodEnd()
    {
        return thirdContractPeriodEnd;
    }
    public void setContractStatusThirdly(String contractStatusThirdly)
    {
        this.contractStatusThirdly = contractStatusThirdly;
    }

    public String getContractStatusThirdly()
    {
        return contractStatusThirdly;
    }
    public void setWarn(String Warn)
    {
        this.Warn = Warn;
    }

    public String getWarn()
    {
        return Warn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("emlwId", getEmlwId())
                .append("Number", getNumber())
                .append("Name", getName())
                .append("Document", getDocument())
                .append("employmentStatus", getEmploymentStatus())
                .append("idNumber", getIdNumber())
                .append("phoneNumber", getPhoneNumber())
                .append("firstContractPeriodStart", getFirstContractPeriodStart())
                .append("firstContractPeriodEnd", getFirstContractPeriodEnd())
                .append("contractStatusFirstly", getContractStatusFirstly())
                .append("secondContractPeriodStart", getSecondContractPeriodStart())
                .append("secondContractPeriodEnd", getSecondContractPeriodEnd())
                .append("contractStatusSecondly", getContractStatusSecondly())
                .append("thirdContractPeriodStart", getThirdContractPeriodStart())
                .append("thirdContractPeriodEnd", getThirdContractPeriodEnd())
                .append("contractStatusThirdly", getContractStatusThirdly())
                .append("Warn", getWarn())
                .toString();
    }
}