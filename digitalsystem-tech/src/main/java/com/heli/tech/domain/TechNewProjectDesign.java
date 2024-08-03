package com.heli.tech.domain;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技术-项目计划完成统计对象 Tech_New_Project_Design
 *
 * @author hong
 * @date 2024-08-03
 */
public class TechNewProjectDesign extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelIgnore
    private Long tdId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    @ExcelIgnore
    private Date yearAndMonth;

    /**
     * 序号
     */
    @Excel(name = "序号")
    @ExcelProperty(value = "序号")
    private String projectId;

    /**
     * 项目名称
     */
    @Excel(name = "项目名称")
    @ExcelProperty(value = "项目名称")
    private String projectName;

    /**
     * 项目类型
     */
    @Excel(name = "项目类型")
    @ExcelProperty(value = "项目类型")
    private String projectType;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    @ExcelProperty(value = "负责人")
    private String projectLeader;

    /**
     * 参与人
     */
    @Excel(name = "参与人")
    @ExcelProperty(value = "参与人")
    private String projectParticipants;

    /**
     * 项目立项
     */
    @Excel(name = "项目立项")
    @ExcelProperty(value = "项目立项")
    private String establishmentState;

    /**
     * 方案设计
     */
    @Excel(name = "方案设计")
    @ExcelProperty(value = "方案设计")
    private String designPlanState;

    /**
     * 图纸设计
     */
    @Excel(name = "图纸设计")
    @ExcelProperty(value = "图纸设计")
    private String drawingDesignState;

    /**
     * 样机试制
     */
    @Excel(name = "样机试制")
    @ExcelProperty(value = "样机试制")
    private String prototypeProductionState;

    /**
     * 样机试验
     */
    @Excel(name = "样机试验")
    @ExcelProperty(value = "样机试验")
    private String prototypeTestState;

    /**
     * 样机评审
     */
    @Excel(name = "样机评审")
    @ExcelProperty(value = "样机评审")
    private String prototypeReviewState;

    /**
     * 小批量上市
     */
    @Excel(name = "小批量上市")
    @ExcelProperty(value = "小批量上市")
    private String pilotReleaseState;

    /**
     * 批量上市
     */
    @Excel(name = "批量上市")
    @ExcelProperty(value = "批量上市")
    private String massReleaseState;

    /**
     * 项目完成率
     */
    @Excel(name = "项目完成率")
    @ExcelProperty(value = "项目完成率")
    private String completionRate;

    /**
     * 备注
     */
    @Excel(name = "备注")
    @ExcelProperty(value = "备注")
    private String remarks;

    /**
     * 项目是否完成
     */
    @Excel(name = "项目是否完成")
    @ExcelIgnore
    private Integer isCompleted;

    public void setTdId(Long tdId) {
        this.tdId = tdId;
    }

    public Long getTdId() {
        return tdId;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectParticipants(String projectParticipants) {
        this.projectParticipants = projectParticipants;
    }

    public String getProjectParticipants() {
        return projectParticipants;
    }

    public void setEstablishmentState(String establishmentState) {
        this.establishmentState = establishmentState;
    }

    public String getEstablishmentState() {
        return establishmentState;
    }

    public void setDesignPlanState(String designPlanState) {
        this.designPlanState = designPlanState;
    }

    public String getDesignPlanState() {
        return designPlanState;
    }

    public void setDrawingDesignState(String drawingDesignState) {
        this.drawingDesignState = drawingDesignState;
    }

    public String getDrawingDesignState() {
        return drawingDesignState;
    }

    public void setPrototypeProductionState(String prototypeProductionState) {
        this.prototypeProductionState = prototypeProductionState;
    }

    public String getPrototypeProductionState() {
        return prototypeProductionState;
    }

    public void setPrototypeTestState(String prototypeTestState) {
        this.prototypeTestState = prototypeTestState;
    }

    public String getPrototypeTestState() {
        return prototypeTestState;
    }

    public void setPrototypeReviewState(String prototypeReviewState) {
        this.prototypeReviewState = prototypeReviewState;
    }

    public String getPrototypeReviewState() {
        return prototypeReviewState;
    }

    public void setPilotReleaseState(String pilotReleaseState) {
        this.pilotReleaseState = pilotReleaseState;
    }

    public String getPilotReleaseState() {
        return pilotReleaseState;
    }

    public void setMassReleaseState(String massReleaseState) {
        this.massReleaseState = massReleaseState;
    }

    public String getMassReleaseState() {
        return massReleaseState;
    }

    public void setCompletionRate(String completionRate) {
        this.completionRate = completionRate;
    }

    public String getCompletionRate() {
        return completionRate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tdId", getTdId())
                .append("yearAndMonth", getYearAndMonth())
                .append("projectId", getProjectId())
                .append("projectName", getProjectName())
                .append("projectType", getProjectType())
                .append("projectLeader", getProjectLeader())
                .append("projectParticipants", getProjectParticipants())
                .append("establishmentState", getEstablishmentState())
                .append("designPlanState", getDesignPlanState())
                .append("drawingDesignState", getDrawingDesignState())
                .append("prototypeProductionState", getPrototypeProductionState())
                .append("prototypeTestState", getPrototypeTestState())
                .append("prototypeReviewState", getPrototypeReviewState())
                .append("pilotReleaseState", getPilotReleaseState())
                .append("massReleaseState", getMassReleaseState())
                .append("completionRate", getCompletionRate())
                .append("remarks", getRemarks())
                .append("isCompleted", getIsCompleted())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
