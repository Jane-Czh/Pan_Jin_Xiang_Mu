package com.ruoyi.Technology.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 申报材料统计对象 Technology_Function_material_retention
 *
 * @author ruoyi
 * @date 2024-05-28
 */
public class TechnologyFunctionMaterialRetention extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long tfemId;

    /** 制度标题 */
    @Excel(name = "制度标题")
    private String regulationsTitle;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadDate;

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

    /** 制度上传人 */
    @Excel(name = "制度上传人")
    private String uploadUsername;

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

    /** 标志位(是否为最新文件) */
    @Excel(name = "标志位(是否为最新文件)")
    private Integer newFlag;

    /** 该制度下一制度id */
    @Excel(name = "该制度下一制度id")
    private Long newRegulationsId;

    public void setTfemId(Long tfemId)
    {
        this.tfemId = tfemId;
    }

    public Long getTfemId()
    {
        return tfemId;
    }
    public void setRegulationsTitle(String regulationsTitle)
    {
        this.regulationsTitle = regulationsTitle;
    }

    public String getRegulationsTitle()
    {
        return regulationsTitle;
    }
    public void setUploadDate(Date uploadDate)
    {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate()
    {
        return uploadDate;
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
    public void setUploadUsername(String uploadUsername)
    {
        this.uploadUsername = uploadUsername;
    }

    public String getUploadUsername()
    {
        return uploadUsername;
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
    public void setNewFlag(Integer newFlag)
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag()
    {
        return newFlag;
    }
    public void setNewRegulationsId(Long newRegulationsId)
    {
        this.newRegulationsId = newRegulationsId;
    }

    public Long getNewRegulationsId()
    {
        return newRegulationsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("tfemId", getTfemId())
                .append("regulationsTitle", getRegulationsTitle())
                .append("uploadDate", getUploadDate())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .append("fileType", getFileType())
                .append("fileSize", getFileSize())
                .append("createDate", getCreateDate())
                .append("uploadUsername", getUploadUsername())
                .append("departmentCategory", getDepartmentCategory())
                .append("fileTag", getFileTag())
                .append("oldRegulationsId", getOldRegulationsId())
                .append("revisionDate", getRevisionDate())
                .append("revisionContent", getRevisionContent())
                .append("reviser", getReviser())
                .append("newFlag", getNewFlag())
                .append("newRegulationsId", getNewRegulationsId())
                .toString();
    }
}