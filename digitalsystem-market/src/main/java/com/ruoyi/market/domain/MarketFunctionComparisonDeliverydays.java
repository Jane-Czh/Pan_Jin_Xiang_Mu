package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 同配置车型交货天数对比功能对象 Market_function_Comparison_deliverydays
 *
 * @author ruoyi
 * @date 2024-04-26
 */
public class MarketFunctionComparisonDeliverydays extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long mfcdId;

    /** 交货天数（每周） */
    @Excel(name = "交货天数", readConverterExp = "每=周")
    private Long numberOfWeekDelivery;

    /** 交货天数（每天） */
    @Excel(name = "交货天数", readConverterExp = "每=天")
    private Long numberOfDayDelivery;

    /** 车数（同吨位每天） */
    @Excel(name = "车数", readConverterExp = "同=吨位每天")
    private Long carNumberDay;

    /** 车型（取吨位数字对比） */
    @Excel(name = "车型", readConverterExp = "取=吨位数字对比")
    private String vehicleModel;

    /** 精整完工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "精整完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date precisionCompletionPeriod;

    /** 接单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptanceDate;

    /** 上线日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date launchDate;

    /** 吨位 */
    @Excel(name = "吨位")
    private Long tonnage;

    /** 车数（同吨位每周） */
    @Excel(name = "车数", readConverterExp = "同=吨位每周")
    private Long carNumberWeek;

    /** 累计天数（天） */
    @Excel(name = "累计天数", readConverterExp = "天=")
    private Long cumulativeNumberOfDays;

    /** 累计天数（周） */
    @Excel(name = "累计天数", readConverterExp = "周=")
    private Long cumulativeNumberOfWeek;

    public void setMfcdId(Long mfcdId)
    {
        this.mfcdId = mfcdId;
    }

    public Long getMfcdId()
    {
        return mfcdId;
    }
    public void setNumberOfWeekDelivery(Long numberOfWeekDelivery)
    {
        this.numberOfWeekDelivery = numberOfWeekDelivery;
    }

    public Long getNumberOfWeekDelivery()
    {
        return numberOfWeekDelivery;
    }
    public void setNumberOfDayDelivery(Long numberOfDayDelivery)
    {
        this.numberOfDayDelivery = numberOfDayDelivery;
    }

    public Long getNumberOfDayDelivery()
    {
        return numberOfDayDelivery;
    }
    public void setCarNumberDay(Long carNumberDay)
    {
        this.carNumberDay = carNumberDay;
    }

    public Long getCarNumberDay()
    {
        return carNumberDay;
    }
    public void setVehicleModel(String vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()
    {
        return vehicleModel;
    }
    public void setPrecisionCompletionPeriod(Date precisionCompletionPeriod)
    {
        this.precisionCompletionPeriod = precisionCompletionPeriod;
    }

    public Date getPrecisionCompletionPeriod()
    {
        return precisionCompletionPeriod;
    }
    public void setAcceptanceDate(Date acceptanceDate)
    {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getAcceptanceDate()
    {
        return acceptanceDate;
    }
    public void setLaunchDate(Date launchDate)
    {
        this.launchDate = launchDate;
    }

    public Date getLaunchDate()
    {
        return launchDate;
    }
    public void setTonnage(Long tonnage)
    {
        this.tonnage = tonnage;
    }

    public Long getTonnage()
    {
        return tonnage;
    }
    public void setCarNumberWeek(Long carNumberWeek)
    {
        this.carNumberWeek = carNumberWeek;
    }

    public Long getCarNumberWeek()
    {
        return carNumberWeek;
    }
    public void setCumulativeNumberOfDays(Long cumulativeNumberOfDays)
    {
        this.cumulativeNumberOfDays = cumulativeNumberOfDays;
    }

    public Long getCumulativeNumberOfDays()
    {
        return cumulativeNumberOfDays;
    }
    public void setCumulativeNumberOfWeek(Long cumulativeNumberOfWeek)
    {
        this.cumulativeNumberOfWeek = cumulativeNumberOfWeek;
    }

    public Long getCumulativeNumberOfWeek()
    {
        return cumulativeNumberOfWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("mfcdId", getMfcdId())
                .append("numberOfWeekDelivery", getNumberOfWeekDelivery())
                .append("numberOfDayDelivery", getNumberOfDayDelivery())
                .append("carNumberDay", getCarNumberDay())
                .append("vehicleModel", getVehicleModel())
                .append("precisionCompletionPeriod", getPrecisionCompletionPeriod())
                .append("acceptanceDate", getAcceptanceDate())
                .append("launchDate", getLaunchDate())
                .append("tonnage", getTonnage())
                .append("carNumberWeek", getCarNumberWeek())
                .append("cumulativeNumberOfDays", getCumulativeNumberOfDays())
                .append("cumulativeNumberOfWeek", getCumulativeNumberOfWeek())
                .append("createTime", getCreateTime())
                .toString();
    }
}