package com.ruoyi.file.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 部门文件对象 Department_File_table
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
public class DepartmentFileTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件id */
    private Long fileId;

    /** 文件标题 */
    @Excel(name = "文件标题")
    private String fileTitle;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    /** 所属科室 */
    @Excel(name = "所属科室")
    private String departmentCategory;

    /** 标签 */
    @Excel(name = "标签")
    private String fileTag;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadDate;

    /** 上传人 */
    @Excel(name = "上传人")
    private String uploadUsername;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setFileTitle(String fileTitle) 
    {
        this.fileTitle = fileTitle;
    }

    public String getFileTitle() 
    {
        return fileTitle;
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
    public void setUploadDate(Date uploadDate) 
    {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() 
    {
        return uploadDate;
    }
    public void setUploadUsername(String uploadUsername) 
    {
        this.uploadUsername = uploadUsername;
    }

    public String getUploadUsername() 
    {
        return uploadUsername;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("fileTitle", getFileTitle())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileType", getFileType())
            .append("fileSize", getFileSize())
            .append("departmentCategory", getDepartmentCategory())
            .append("fileTag", getFileTag())
            .append("uploadDate", getUploadDate())
            .append("uploadUsername", getUploadUsername())
            .toString();
    }
}
