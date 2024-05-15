package com.ruoyi.market.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 售后台账导入对象 Market_After_Sale_Ledger
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@Data
public class MarketAfterSaleLedger extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Transient
    private Date startTime;
    @Transient
    private Date endTime;
    @Transient
    private Integer numberInput;
    /** 主键 */
    private Long maslId;

    /** 编号 */
    @Excel(name = "编号")
    private String Number;

    /** 用户单位 */
    @Excel(name = "用户单位")
    private String userUnit;

    /** 联系人 */
    @Excel(name = "联系人")
    private String Contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String Phone;

    /** 反馈日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackDate;

    /** 整车生产单位 */
    @Excel(name = "整车生产单位")
    private String vehicleProductionUnit;

    /** 车 */
    @Excel(name = "车")
    private String Vehicle;

    /** 吨 */
    @Excel(name = "吨")
    private Integer Ton;

    /** 发 */
    @Excel(name = "发")
    private String Launch;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 车号 */
    @Excel(name = "车号")
    private String wagonNumber;

    /** 销售时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "销售时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sellingTime;

    /** 使用时间 */
    @Excel(name = "使用时间")
    private BigDecimal useHours;

    /** 是否新车 */
    @Excel(name = "是否新车")
    private String newOrNot;

    /** 存在问题 */
    @Excel(name = "存在问题")
    private String existingProblem;

    /** 一级分类 */
    @Excel(name = "一级分类")
    private String primaryClassification;

    /** 二级分类 */
    @Excel(name = "二级分类")
    private String secondaryClassification;

    /** 最终处理方法 */
    @Excel(name = "最终处理方法")
    private String finalTreatmentMethod;

    /** 赔偿件 */
    @Excel(name = "赔偿件")
    private String Indemnity;

    /** 零件编号 */
    @Excel(name = "零件编号")
    private String partNumber;

    /** 供应商 */
    @Excel(name = "供应商")
    private String Supplier;

    /** 数量 */
    @Excel(name = "数量")
    private Integer Quantity;

    /** 责任单位 */
    @Excel(name = "责任单位")
    private String accountabilityUnit;

    /** 故障级别 */
    @Excel(name = "故障级别")
    private String faultLevel;

    /** 评分 */
    @Excel(name = "评分")
    private Integer Score;

    /** 发件方式 */
    @Excel(name = "发件方式")
    private String sendingMethod;

    /** 一级网点 */
    @Excel(name = "一级网点")
    private String primaryBranch;

    /** 二级网点 */
    @Excel(name = "二级网点")
    private String secondaryBranch;

    /** 发货方式 */
    @Excel(name = "发货方式")
    private String despatchMode;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private String paymentsMode;

    /** 反馈方式 */
    @Excel(name = "反馈方式")
    private String feedbackMode;

    /** 预留单号 */
    @Excel(name = "预留单号")
    private String reservationNumber;

    /** 考核情况 */
    @Excel(name = "考核情况")
    private String assessmentSituation;

    public void setMaslId(Long maslId) 
    {
        this.maslId = maslId;
    }

    public Long getMaslId() 
    {
        return maslId;
    }
    public void setNumber(String Number) 
    {
        this.Number = Number;
    }

    public String getNumber() 
    {
        return Number;
    }
    public void setUserUnit(String userUnit) 
    {
        this.userUnit = userUnit;
    }

    public String getUserUnit() 
    {
        return userUnit;
    }
    public void setContacts(String Contacts) 
    {
        this.Contacts = Contacts;
    }

    public String getContacts() 
    {
        return Contacts;
    }
    public void setPhone(String Phone) 
    {
        this.Phone = Phone;
    }

    public String getPhone() 
    {
        return Phone;
    }
    public void setFeedbackDate(Date feedbackDate) 
    {
        this.feedbackDate = feedbackDate;
    }

    public Date getFeedbackDate() 
    {
        return feedbackDate;
    }
    public void setVehicleProductionUnit(String vehicleProductionUnit) 
    {
        this.vehicleProductionUnit = vehicleProductionUnit;
    }

    public String getVehicleProductionUnit() 
    {
        return vehicleProductionUnit;
    }
    public void setVehicle(String Vehicle) 
    {
        this.Vehicle = Vehicle;
    }

    public String getVehicle() 
    {
        return Vehicle;
    }
    public void setTon(Integer Ton) 
    {
        this.Ton = Ton;
    }

    public Integer getTon() 
    {
        return Ton;
    }
    public void setLaunch(String Launch) 
    {
        this.Launch = Launch;
    }

    public String getLaunch() 
    {
        return Launch;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setWagonNumber(String wagonNumber) 
    {
        this.wagonNumber = wagonNumber;
    }

    public String getWagonNumber() 
    {
        return wagonNumber;
    }
    public void setSellingTime(Date sellingTime) 
    {
        this.sellingTime = sellingTime;
    }

    public Date getSellingTime() 
    {
        return sellingTime;
    }
    public void setUseHours(BigDecimal useHours) 
    {
        this.useHours = useHours;
    }

    public BigDecimal getUseHours() 
    {
        return useHours;
    }
    public void setNewOrNot(String newOrNot) 
    {
        this.newOrNot = newOrNot;
    }

    public String getNewOrNot() 
    {
        return newOrNot;
    }
    public void setExistingProblem(String existingProblem) 
    {
        this.existingProblem = existingProblem;
    }

    public String getExistingProblem() 
    {
        return existingProblem;
    }
    public void setPrimaryClassification(String primaryClassification) 
    {
        this.primaryClassification = primaryClassification;
    }

    public String getPrimaryClassification() 
    {
        return primaryClassification;
    }
    public void setSecondaryClassification(String secondaryClassification) 
    {
        this.secondaryClassification = secondaryClassification;
    }

    public String getSecondaryClassification() 
    {
        return secondaryClassification;
    }
    public void setFinalTreatmentMethod(String finalTreatmentMethod) 
    {
        this.finalTreatmentMethod = finalTreatmentMethod;
    }

    public String getFinalTreatmentMethod() 
    {
        return finalTreatmentMethod;
    }
    public void setIndemnity(String Indemnity) 
    {
        this.Indemnity = Indemnity;
    }

    public String getIndemnity() 
    {
        return Indemnity;
    }
    public void setPartNumber(String partNumber) 
    {
        this.partNumber = partNumber;
    }

    public String getPartNumber() 
    {
        return partNumber;
    }
    public void setSupplier(String Supplier) 
    {
        this.Supplier = Supplier;
    }

    public String getSupplier() 
    {
        return Supplier;
    }
    public void setQuantity(Integer Quantity) 
    {
        this.Quantity = Quantity;
    }

    public Integer getQuantity() 
    {
        return Quantity;
    }
    public void setAccountabilityUnit(String accountabilityUnit) 
    {
        this.accountabilityUnit = accountabilityUnit;
    }

    public String getAccountabilityUnit() 
    {
        return accountabilityUnit;
    }
    public void setFaultLevel(String faultLevel) 
    {
        this.faultLevel = faultLevel;
    }

    public String getFaultLevel() 
    {
        return faultLevel;
    }
    public void setScore(Integer Score) 
    {
        this.Score = Score;
    }

    public Integer getScore() 
    {
        return Score;
    }
    public void setSendingMethod(String sendingMethod) 
    {
        this.sendingMethod = sendingMethod;
    }

    public String getSendingMethod() 
    {
        return sendingMethod;
    }
    public void setPrimaryBranch(String primaryBranch) 
    {
        this.primaryBranch = primaryBranch;
    }

    public String getPrimaryBranch() 
    {
        return primaryBranch;
    }
    public void setSecondaryBranch(String secondaryBranch) 
    {
        this.secondaryBranch = secondaryBranch;
    }

    public String getSecondaryBranch() 
    {
        return secondaryBranch;
    }
    public void setDespatchMode(String despatchMode) 
    {
        this.despatchMode = despatchMode;
    }

    public String getDespatchMode() 
    {
        return despatchMode;
    }
    public void setPaymentsMode(String paymentsMode) 
    {
        this.paymentsMode = paymentsMode;
    }

    public String getPaymentsMode() 
    {
        return paymentsMode;
    }
    public void setFeedbackMode(String feedbackMode) 
    {
        this.feedbackMode = feedbackMode;
    }

    public String getFeedbackMode() 
    {
        return feedbackMode;
    }
    public void setReservationNumber(String reservationNumber) 
    {
        this.reservationNumber = reservationNumber;
    }

    public String getReservationNumber() 
    {
        return reservationNumber;
    }
    public void setAssessmentSituation(String assessmentSituation) 
    {
        this.assessmentSituation = assessmentSituation;
    }

    public String getAssessmentSituation() 
    {
        return assessmentSituation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("maslId", getMaslId())
            .append("Number", getNumber())
            .append("userUnit", getUserUnit())
            .append("Contacts", getContacts())
            .append("Phone", getPhone())
            .append("feedbackDate", getFeedbackDate())
            .append("vehicleProductionUnit", getVehicleProductionUnit())
            .append("Vehicle", getVehicle())
            .append("Ton", getTon())
            .append("Launch", getLaunch())
            .append("vehicleModel", getVehicleModel())
            .append("wagonNumber", getWagonNumber())
            .append("sellingTime", getSellingTime())
            .append("useHours", getUseHours())
            .append("newOrNot", getNewOrNot())
            .append("existingProblem", getExistingProblem())
            .append("primaryClassification", getPrimaryClassification())
            .append("secondaryClassification", getSecondaryClassification())
            .append("finalTreatmentMethod", getFinalTreatmentMethod())
            .append("Indemnity", getIndemnity())
            .append("partNumber", getPartNumber())
            .append("Supplier", getSupplier())
            .append("Quantity", getQuantity())
            .append("accountabilityUnit", getAccountabilityUnit())
            .append("faultLevel", getFaultLevel())
            .append("Score", getScore())
            .append("sendingMethod", getSendingMethod())
            .append("primaryBranch", getPrimaryBranch())
            .append("secondaryBranch", getSecondaryBranch())
            .append("despatchMode", getDespatchMode())
            .append("paymentsMode", getPaymentsMode())
            .append("feedbackMode", getFeedbackMode())
            .append("reservationNumber", getReservationNumber())
            .append("assessmentSituation", getAssessmentSituation())
            .toString();
    }
}
