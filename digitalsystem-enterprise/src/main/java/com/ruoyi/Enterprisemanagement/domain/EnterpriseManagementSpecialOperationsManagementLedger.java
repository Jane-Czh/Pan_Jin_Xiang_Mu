package com.ruoyi.Enterprisemanagement.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 特种作业台账导入对象 Enterprise_Management_Special_Operations_Management_Ledger
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
public class EnterpriseManagementSpecialOperationsManagementLedger extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long emsiId;

    /** 序号 */
    @Excel(name = "序号")
    private Long Number;

    /** 部门 */
    @Excel(name = "部门")
    private String Department;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 性别 */
    @Excel(name = "性别")
    private String Gender;

    /** 发证机关 */
    @Excel(name = "发证机关")
    private String issuingAuthority;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String documentType;

    /** 作业类别 */
    @Excel(name = "作业类别")
    private String assignmentCategory;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 证件号 */
    @Excel(name = "证件号")
    private String idCard;

    /** 发证时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发证时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date evidenceCollectionTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date firstExpiration;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date reexaminationFirstly;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date secondExpiration;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date reexaminationSecondly;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date thirdExpiration;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date reexaminationThirdly;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String Warn;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    public void setEmsiId(Long emsiId) 
    {
        this.emsiId = emsiId;
    }

    public Long getEmsiId() 
    {
        return emsiId;
    }
    public void setNumber(Long Number) 
    {
        this.Number = Number;
    }

    public Long getNumber() 
    {
        return Number;
    }
    public void setDepartment(String Department) 
    {
        this.Department = Department;
    }

    public String getDepartment() 
    {
        return Department;
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
    public void setIssuingAuthority(String issuingAuthority) 
    {
        this.issuingAuthority = issuingAuthority;
    }

    public String getIssuingAuthority() 
    {
        return issuingAuthority;
    }
    public void setDocumentType(String documentType) 
    {
        this.documentType = documentType;
    }

    public String getDocumentType() 
    {
        return documentType;
    }
    public void setAssignmentCategory(String assignmentCategory) 
    {
        this.assignmentCategory = assignmentCategory;
    }

    public String getAssignmentCategory() 
    {
        return assignmentCategory;
    }
    public void setIdNumber(String idNumber) 
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setEvidenceCollectionTime(Date evidenceCollectionTime) 
    {
        this.evidenceCollectionTime = evidenceCollectionTime;
    }

    public Date getEvidenceCollectionTime() 
    {
        return evidenceCollectionTime;
    }
    public void setFirstExpiration(Date firstExpiration) 
    {
        this.firstExpiration = firstExpiration;
    }

    public Date getFirstExpiration() 
    {
        return firstExpiration;
    }
    public void setReexaminationFirstly(Date reexaminationFirstly) 
    {
        this.reexaminationFirstly = reexaminationFirstly;
    }

    public Date getReexaminationFirstly() 
    {
        return reexaminationFirstly;
    }
    public void setSecondExpiration(Date secondExpiration) 
    {
        this.secondExpiration = secondExpiration;
    }

    public Date getSecondExpiration() 
    {
        return secondExpiration;
    }
    public void setReexaminationSecondly(Date reexaminationSecondly) 
    {
        this.reexaminationSecondly = reexaminationSecondly;
    }

    public Date getReexaminationSecondly() 
    {
        return reexaminationSecondly;
    }
    public void setThirdExpiration(Date thirdExpiration) 
    {
        this.thirdExpiration = thirdExpiration;
    }

    public Date getThirdExpiration() 
    {
        return thirdExpiration;
    }
    public void setReexaminationThirdly(Date reexaminationThirdly) 
    {
        this.reexaminationThirdly = reexaminationThirdly;
    }

    public Date getReexaminationThirdly() 
    {
        return reexaminationThirdly;
    }
    public void setWarn(String Warn) 
    {
        this.Warn = Warn;
    }

    public String getWarn() 
    {
        return Warn;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("emsiId", getEmsiId())
            .append("Number", getNumber())
            .append("Department", getDepartment())
            .append("Name", getName())
            .append("Gender", getGender())
            .append("issuingAuthority", getIssuingAuthority())
            .append("documentType", getDocumentType())
            .append("assignmentCategory", getAssignmentCategory())
            .append("idNumber", getIdNumber())
            .append("idCard", getIdCard())
            .append("evidenceCollectionTime", getEvidenceCollectionTime())
            .append("firstExpiration", getFirstExpiration())
            .append("reexaminationFirstly", getReexaminationFirstly())
            .append("secondExpiration", getSecondExpiration())
            .append("reexaminationSecondly", getReexaminationSecondly())
            .append("thirdExpiration", getThirdExpiration())
            .append("reexaminationThirdly", getReexaminationThirdly())
            .append("Warn", getWarn())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .toString();
    }
}
