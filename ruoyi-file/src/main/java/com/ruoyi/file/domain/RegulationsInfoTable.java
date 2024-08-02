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
 * @date 2024-04-17
 */
public class RegulationsInfoTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    // 指标查询流程更改次数时通过api传递过来的 时间参数：startTime、endTime

    private Date startTime;

    private Date endTime;

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

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** pdf路径 */
    @Excel(name = "pdf路径")
    private String pdfPath;

    /** word路径 */
    @Excel(name = "word路径")
    private String wordPath;

    /** 文件类型（word/pdf） */
    @Excel(name = "文件类型", readConverterExp = "w=ord/pdf")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    /** pdf文件大小 */
    @Excel(name = "pdf文件大小")
    private BigDecimal pdfSize;

    /** word文件大小 */
    @Excel(name = "word文件大小")
    private BigDecimal wordSize;

    /** 文件内容 */
    @Excel(name = "文件内容")
    private String fileContent;

    /** 制度创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "制度创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 制度上传人 */
    @Excel(name = "制度上传人")
    private String uploadUsername;

    /** 制度使用状态 */
    @Excel(name = "制度使用状态")
    private String useState;

    /** 制度所属科室 */
    @Excel(name = "制度所属科室")
    private String departmentCategory;

    /** 制度主责部门 */
    @Excel(name = "制度主责部门")
    private String mainResponsibleDepartment;

    /** 制度专业分类 */
    @Excel(name = "制度专业分类")
    private String classificationOfSpecialties;

    /** 制度等级 */
    @Excel(name = "制度等级")
    private String regulationLeval;

    /** 制度编号 */
    @Excel(name = "制度编号")
    private String regulationNumber;

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

    /** 绑定的流程ids */
    @Excel(name = "绑定的流程ids")
    private String projectIds;

    /** 标志位(是否为最新文件) */
    @Excel(name = "标志位(是否为最新文件)")
    private Integer newFlag;

    /** 标志位(本文件上一旧文件id) */
    @Excel(name = "标志位(本文件上一旧文件id)")
    private Long newRegulationsId;

    /** 业务模块 */
    @Excel(name = "业务模块")
    private String businesses;

    /** 细分业务 */
    @Excel(name = "细分业务")
    private String subBusinesses;

    // startTime和endTime的getter和setter
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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
    public void setNewFlag(Integer newFlag)
    {
        this.newFlag = newFlag;
    }

    public Integer getNewFlag()
    {
        return newFlag;
    }
    public void setNewRegulationsId(Long NewRegulationsId)
    {
        this.newRegulationsId = NewRegulationsId;
    }

    public Long getNewRegulationsId()
    {
        return newRegulationsId;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getWordPath() {
        return wordPath;
    }

    public void setWordPath(String wordPath) {
        this.wordPath = wordPath;
    }

    public BigDecimal getPdfSize() {
        return pdfSize;
    }

    public void setPdfSize(BigDecimal pdfSize) {
        this.pdfSize = pdfSize;
    }

    public BigDecimal getWordSize() {
        return wordSize;
    }

    public void setWordSize(BigDecimal wordSize) {
        this.wordSize = wordSize;
    }

    public String getMainResponsibleDepartment() {
        return mainResponsibleDepartment;
    }

    public void setMainResponsibleDepartment(String mainResponsibleDepartment) {
        this.mainResponsibleDepartment = mainResponsibleDepartment;
    }

    public String getClassificationOfSpecialties() {
        return classificationOfSpecialties;
    }

    public void setClassificationOfSpecialties(String classificationOfSpecialties) {
        this.classificationOfSpecialties = classificationOfSpecialties;
    }

    public String getRegulationLeval() {
        return regulationLeval;
    }

    public void setRegulationLeval(String regulationLeval) {
        this.regulationLeval = regulationLeval;
    }

    public String getRegulationNumber() {
        return regulationNumber;
    }

    public void setRegulationNumber(String regulationNumber) {
        this.regulationNumber = regulationNumber;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getBusinesses() {
        return businesses;
    }

    public void setBusinesses(String businesses) {
        this.businesses = businesses;
    }

    public String getSubBusinesses() {
        return subBusinesses;
    }

    public void setSubBusinesses(String subBusinesses) {
        this.subBusinesses = subBusinesses;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("regulationsId", getRegulationsId())
                .append("regulationsTitle", getRegulationsTitle())
                .append("useScope", getUseScope())
                .append("uploadDate", getUploadDate())
                .append("effectiveDate", getEffectiveDate())
                .append("fileName", getFileName())
                .append("filePath", getFilePath())
                .append("pdfPath", getPdfPath())
                .append("wordPath", getWordPath())
                .append("fileType", getFileType())
                .append("fileSize", getFileSize())
                .append("pdfSize", getPdfSize())
                .append("wordSize", getWordSize())
                .append("fileContent", getFileContent())
                .append("createDate", getCreateDate())
                .append("uploadUsername", getUploadUsername())
                .append("useState", getUseState())
                .append("departmentCategory", getDepartmentCategory())
                .append("mainResponsibleDepartment", getMainResponsibleDepartment())
                .append("classificationOfSpecialties", getClassificationOfSpecialties())
                .append("regulationLeval", getRegulationLeval())
                .append("regulationNumber", getRegulationNumber())
                .append("fileTag", getFileTag())
                .append("oldRegulationsId", getOldRegulationsId())
                .append("revisionDate", getRevisionDate())
                .append("revisionContent", getRevisionContent())
                .append("reviser", getReviser())
                .append("projectIds", getProjectIds())
                .append("newFlag", getNewFlag())
                .append("newRegulationsId", getNewRegulationsId())
                .append("businesses", getBusinesses())
                .append("subBusinesses", getSubBusinesses())
                .toString();
    }
}
