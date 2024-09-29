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
 * 表单文件管理对象 Form_Info_table
 *
 * @author ruoyi
 * @date 2024-05-07
 */
@Data
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

    /** 业务模块 */
    @Excel(name = "业务模块")
    private String businesses;

    /** 细分业务 */
    @Excel(name = "细分业务")
    private String subBusinesses;

    /** 关联制度 */
    private String regulationId;

}
