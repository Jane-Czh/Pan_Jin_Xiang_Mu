package com.ruoyi.market.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 统计库存车数量对象 Market_Unsold_Car
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class MarketUnsoldCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long ucId;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 数量 */
    @Excel(name = "数量")
    private Long Number;

    public void setUcId(Long ucId) 
    {
        this.ucId = ucId;
    }

    public Long getUcId() 
    {
        return ucId;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setNumber(Long Number) 
    {
        this.Number = Number;
    }

    public Long getNumber() 
    {
        return Number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ucId", getUcId())
            .append("vehicleModel", getVehicleModel())
            .append("Number", getNumber())
            .toString();
    }
}
