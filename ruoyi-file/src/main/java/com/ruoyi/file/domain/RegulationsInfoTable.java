package com.ruoyi.file.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件管理对象 Regulations_Info_table
 *
 * @author ruoyi
 * @date 2024-04-10
 */
public class RegulationsInfoTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id(主键) */
    private Long regulationsId;

    /** 制度标题 */
    @Excel(name = "制度标题")
    private String regulationsTitle;

    /** 适用范围 */
    @Excel(name = "适用范围")
    private String useScope;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadDate;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 制度内容（链接地址） */
    @Excel(name = "制度内容", readConverterExp = "链=接地址")
    private String regulationsAddress;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件类型（word/pdf） */
    @Excel(name = "文件类型", readConverterExp = "w=ord/pdf")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    /** 制度创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "制度创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 制度创建人 */
    @Excel(name = "制度创建人")
    private String createUsername;

    /** 制度使用状态 */
    @Excel(name = "制度使用状态")
    private String useState;

    /** 制度所属科室 */
    @Excel(name = "制度所属科室")
    private String departmentCategory;

    /** 制度标签名称 */
    @Excel(name = "制度标签名称")
    private String fileTag;

    /** 历史版本制度 */
    @Excel(name = "历史版本制度")
    private Long oldRegulationsId;

    /** 修订时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修订时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date revisionDate;

    /** 修订内容 */
    @Excel(name = "修订内容")
    private String revisionContent;

    /** 修订人 */
    @Excel(name = "修订人")
    private String reviser;

    public void setRegulationsId(Long regulationsId)
    {
        this.regulationsId = regulationsId;
    }

    public Long getRegulationsId()
    {
        return regulationsId;
    }
    public void setRegulationsTitle(String regulationsTitle)
    {
        this.regulationsTitle = regulationsTitle;
    }

    public String getRegulationsTitle()
    {
        return regulationsTitle;
    }
    public void setUseScope(String useScope)
    {
        this.useScope = useScope;
    }

    public String getUseScope()
    {
        return useScope;
    }
    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate()
    {
        return uploadDate;
    }
    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }
    public void setRegulationsAddress(String regulationsAddress)
    {
        this.regulationsAddress = regulationsAddress;
    }

    public String getRegulationsAddress()
    {
        return regulationsAddress;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }
    public void setFileSize(BigDecimal fileSize)
    {
        this.fileSize = fileSize;
    }

    public BigDecimal getFileSize()
    {
        return fileSize;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate()
    {
        return createDate;
    }
    public void setCreateUsername(String createUsername)
    {
        this.createUsername = createUsername;
    }

    public String getCreateUsername()
    {
        return createUsername;
    }
    public void setUseState(String useState)
    {
        this.useState = useState;
    }

    public String getUseState()
    {
        return useState;
    }
    public void setDepartmentCategory(String departmentCategory)
    {
        this.departmentCategory = departmentCategory;
    }

    public String getDepartmentCategory()
    {
        return departmentCategory;
    }
    public void setFileTag(String fileTag)
    {
        this.fileTag = fileTag;
    }

    public String getFileTag()
    {
        return fileTag;
    }
    public void setOldRegulationsId(Long oldRegulationsId)
    {
        this.oldRegulationsId = oldRegulationsId;
    }

    public Long getOldRegulationsId()
    {
        return oldRegulationsId;
    }
    public void setRevisionDate(Date revisionDate)
    {
        this.revisionDate = revisionDate;
    }

    public Date getRevisionDate()
    {
        return revisionDate;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("regulationsId", getRegulationsId())
                .append("regulationsTitle", getRegulationsTitle())
                .append("useScope", getUseScope())
                .append("uploadDate", getUploadDate())
                .append("effectiveDate", getEffectiveDate())
                .append("regulationsAddress", getRegulationsAddress())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .append("fileType", getFileType())
                .append("fileSize", getFileSize())
                .append("createDate", getCreateDate())
                .append("createUsername", getCreateUsername())
                .append("useState", getUseState())
                .append("departmentCategory", getDepartmentCategory())
                .append("fileTag", getFileTag())
                .append("oldRegulationsId", getOldRegulationsId())
                .append("revisionDate", getRevisionDate())
                .append("revisionContent", getRevisionContent())
                .append("reviser", getReviser())
                .toString();
    }
}
