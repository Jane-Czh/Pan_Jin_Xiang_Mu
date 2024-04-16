package com.ruoyi.market.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后问题复发率对象 Market_After_Sales_Recurrence_Rate
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public class MarketAfterSalesRecurrenceRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long masrrId;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 相同问题复发率 */
    @Excel(name = "相同问题复发率")
    private BigDecimal problemRecurrenceRate;

    /** 不同问题出现率 */
    @Excel(name = "不同问题出现率")
    private BigDecimal differentProblemOccurrenceRate;

    public void setMasrrId(Long masrrId) 
    {
        this.masrrId = masrrId;
    }

    public Long getMasrrId() 
    {
        return masrrId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("masrrId", getMasrrId())
            .append("vehicleModel", getVehicleModel())
            .append("problemRecurrenceRate", getProblemRecurrenceRate())
            .append("differentProblemOccurrenceRate", getDifferentProblemOccurrenceRate())
            .toString();
    }
}
