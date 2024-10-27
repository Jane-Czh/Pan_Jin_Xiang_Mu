package com.ruoyi.file.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * departmentfile对象 Department_Files_table
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@Data
public class DepartmentFilesTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    @Excel(name = "文件类型")
    private String fileType;

    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    /** 所属文件夹 */
    @Excel(name = "所属文件夹")
    private Long folderId;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;


}
