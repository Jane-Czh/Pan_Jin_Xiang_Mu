package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存车明细对象 Market_Inventory_Car_Detail
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public class MarketInventoryCarDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long micdId;

    /** 上线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date onlineTime;

    /** 车号 */
    @Excel(name = "车号")
    private String wagonNumber;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 门架 */
    @Excel(name = "门架")
    private String doorFrame;

    /** 属具 */
    @Excel(name = "属具")
    private String Accessory;

    /** 阀片数 */
    @Excel(name = "阀片数")
    private Integer valveBlockNumber;

    /** 配置 */
    @Excel(name = "配置")
    private String Configuration;

    /** 计划完工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planndeCompletionTime;

    public void setMicdId(Long micdId) 
    {
        this.micdId = micdId;
    }

    public Long getMicdId() 
    {
        return micdId;
    }
    public void setOnlineTime(Date onlineTime) 
    {
        this.onlineTime = onlineTime;
    }

    public Date getOnlineTime() 
    {
        return onlineTime;
    }
    public void setWagonNumber(String wagonNumber) 
    {
        this.wagonNumber = wagonNumber;
    }

    public String getWagonNumber() 
    {
        return wagonNumber;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setDoorFrame(String doorFrame) 
    {
        this.doorFrame = doorFrame;
    }

    public String getDoorFrame() 
    {
        return doorFrame;
    }
    public void setAccessory(String Accessory) 
    {
        this.Accessory = Accessory;
    }

    public String getAccessory() 
    {
        return Accessory;
    }
    public void setValveBlockNumber(Integer valveBlockNumber) 
    {
        this.valveBlockNumber = valveBlockNumber;
    }

    public Integer getValveBlockNumber() 
    {
        return valveBlockNumber;
    }
    public void setConfiguration(String Configuration) 
    {
        this.Configuration = Configuration;
    }

    public String getConfiguration() 
    {
        return Configuration;
    }
    public void setPlanndeCompletionTime(Date planndeCompletionTime) 
    {
        this.planndeCompletionTime = planndeCompletionTime;
    }

    public Date getPlanndeCompletionTime() 
    {
        return planndeCompletionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("micdId", getMicdId())
            .append("onlineTime", getOnlineTime())
            .append("wagonNumber", getWagonNumber())
            .append("vehicleModel", getVehicleModel())
            .append("doorFrame", getDoorFrame())
            .append("Accessory", getAccessory())
            .append("valveBlockNumber", getValveBlockNumber())
            .append("Configuration", getConfiguration())
            .append("planndeCompletionTime", getPlanndeCompletionTime())
            .toString();
    }
}
