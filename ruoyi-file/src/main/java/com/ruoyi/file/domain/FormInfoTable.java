package com.ruoyi.file.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 表单文件管理对象 Form_Info_table
 *
 * @author ruoyi
 * @date 2024-05-07
 */
public class FormInfoTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id(主键) */
    private Long formId;

    /** 表单标题 */
    @Excel(name = "表单标题")
    private String formTitle;

    /** 存储表单内容 */
    @Excel(name = "存储表单内容")
    private String scope;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String formName;

    /** 表单类型 */
    @Excel(name = "表单类型")
    private String formType;

    /** 表单路径 */
    @Excel(name = "表单路径")
    private String formPath;

    /** 表单大小 */
    @Excel(name = "表单大小")
    private BigDecimal formSize;

    /** 上传人 */
    @Excel(name = "上传人")
    private String createUsername;

    /** 表单所属科室 */
    @Excel(name = "表单所属科室")
    private String departmentCategory;

    /** 历史表单 */
    @Excel(name = "历史表单")
    private Long oldFormId;

    /** 修订时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date revisionTime;

    /** 修订内容 */
    @Excel(name = "修订内容")
    private String revisionContent;

    /** 修订人 */
    @Excel(name = "修订人")
    private String reviser;

    /** 标志位(是否为最新文件) */
    @Excel(name = "标志位(是否为最新文件)")
    private Integer newFlag;

    /** 该表单下一表单id */
    @Excel(name = "该表单下一表单id")
    private Long newFormId;

    public void setFormId(Long formId)
    {
        this.formId = formId;
    }

    public Long getFormId()
    {
        return formId;
    }
    public void setFormTitle(String formTitle)
    {
        this.formTitle = formTitle;
    }

    public String getFormTitle()
    {
        return formTitle;
    }
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    public String getScope()
    {
        return scope;
    }
    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }
    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    public String getFormName()
    {
        return formName;
    }
    public void setFormType(String formType)
    {
        this.formType = formType;
    }

    public String getFormType()
    {
        return formType;
    }
    public void setFormPath(String formPath)
    {
        this.formPath = formPath;
    }

    public String getFormPath()
    {
        return formPath;
    }
    public void setFormSize(BigDecimal formSize)
    {
        this.formSize = formSize;
    }

    public BigDecimal getFormSize()
    {
        return formSize;
    }
    public void setCreateUsername(String createUsername)
    {
        this.createUsername = createUsername;
    }

    public String getCreateUsername()
    {
        return createUsername;
    }
    public void setDepartmentCategory(String departmentCategory)
    {
        this.departmentCategory = departmentCategory;
    }

    public String getDepartmentCategory()
    {
        return departmentCategory;
    }
    public void setOldFormId(Long oldFormId)
    {
        this.oldFormId = oldFormId;
    }

    public Long getOldFormId()
    {
        return oldFormId;
    }
    public void setRevisionTime(Date revisionTime)
    {
        this.revisionTime = revisionTime;
    }

    public Date getRevisionTime()
    {
        return revisionTime;
    }
    public void setRevisionContent(String revisionContent)
    {
        this.revisionContent = revisionContent;
    }

    public String getRevisionContent()
    {
        return revisionContent;
    }
    public void setReviser(String reviser)
    {
        this.reviser = reviser;
    }

    public String getReviser()
    {
        return reviser;
    }
    public void setNewFlag(Integer newFlag)
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag()
    {
        return newFlag;
    }
    public void setNewFormId(Long newFormId)
    {
        this.newFormId = newFormId;
    }

    public Long getNewFormId()
    {
        return newFormId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("formId", getFormId())
                .append("formTitle", getFormTitle())
                .append("scope", getScope())
                .append("effectiveDate", getEffectiveDate())
                .append("formName", getFormName())
                .append("formType", getFormType())
                .append("formPath", getFormPath())
                .append("formSize", getFormSize())
                .append("createUsername", getCreateUsername())
                .append("departmentCategory", getDepartmentCategory())
                .append("remark", getRemark())
                .append("oldFormId", getOldFormId())
                .append("revisionTime", getRevisionTime())
                .append("revisionContent", getRevisionContent())
                .append("reviser", getReviser())
                .append("newFlag", getNewFlag())
                .append("newFormId", getNewFormId())
                .toString();
    }
}
