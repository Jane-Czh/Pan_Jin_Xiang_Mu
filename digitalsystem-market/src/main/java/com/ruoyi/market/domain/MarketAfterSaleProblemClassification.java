package com.ruoyi.market.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后问题分类对象 Market_After_Sale_Problem_Classification
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public class MarketAfterSaleProblemClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long maspcId;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 相同问题复发率 */
    @Excel(name = "相同问题复发率")
    private BigDecimal problemRecurrenceRate;

    /** 不同问题出现率 */
    @Excel(name = "不同问题出现率")
    private BigDecimal differentProblemOccurrenceRate;

    /** 一级网点 */
    @Excel(name = "一级网点")
    private String Branch;

    public void setMaspcId(Long maspcId) 
    {
        this.maspcId = maspcId;
    }

    public Long getMaspcId() 
    {
        return maspcId;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setProblemRecurrenceRate(BigDecimal problemRecurrenceRate) 
    {
        this.problemRecurrenceRate = problemRecurrenceRate;
    }

    public BigDecimal getProblemRecurrenceRate() 
    {
        return problemRecurrenceRate;
    }
    public void setDifferentProblemOccurrenceRate(BigDecimal differentProblemOccurrenceRate) 
    {
        this.differentProblemOccurrenceRate = differentProblemOccurrenceRate;
    }

    public BigDecimal getDifferentProblemOccurrenceRate() 
    {
        return differentProblemOccurrenceRate;
    }
    public void setBranch(String Branch) 
    {
        this.Branch = Branch;
    }

    public String getBranch() 
    {
        return Branch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("maspcId", getMaspcId())
            .append("vehicleModel", getVehicleModel())
            .append("problemRecurrenceRate", getProblemRecurrenceRate())
            .append("differentProblemOccurrenceRate", getDifferentProblemOccurrenceRate())
            .append("Branch", getBranch())
            .toString();
    }
}
