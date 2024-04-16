package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售台账存储对象 Market_Sales_Table_Storage
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
public class MarketSalesTableStorage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long mstsId;

    /** 网点 */
    @Excel(name = "网点")
    private String Branch;

    /** 合同号 */
    @Excel(name = "合同号")
    private String contractNumber;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNumber;

    /** 接单日期（年月日） */
    @Excel(name = "接单日期", readConverterExp = "年=月日")
    private Date orderAcceptanceTime;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModel;

    /** 数量 */
    @Excel(name = "数量")
    private Long Number;

    /** 阀片 */
    @Excel(name = "阀片")
    private String valveBlock;

    /** 货叉 */
    @Excel(name = "货叉")
    private String Fork;

    /** 门架 */
    @Excel(name = "门架")
    private String doorFrame;

    /** 空滤 */
    @Excel(name = "空滤")
    private String airFilter;

    /** 属具 */
    @Excel(name = "属具")
    private String Accessory;

    /** 轮胎 */
    @Excel(name = "轮胎")
    private String Tyre;

    /** 配置 */
    @Excel(name = "配置")
    private String Configuration;

    /** 车号 */
    @Excel(name = "车号")
    private String carNumber;

    /** 订单系统交货期（年月日） */
    @Excel(name = "订单系统交货期", readConverterExp = "年=月日")
    private Date orderSystemDeliveryTime;

    public void setMstsId(Long mstsId) 
    {
        this.mstsId = mstsId;
    }

    public Long getMstsId() 
    {
        return mstsId;
    }
    public void setBranch(String Branch) 
    {
        this.Branch = Branch;
    }

    public String getBranch() 
    {
        return Branch;
    }
    public void setContractNumber(String contractNumber) 
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() 
    {
        return contractNumber;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setOrderAcceptanceTime(Date orderAcceptanceTime) 
    {
        this.orderAcceptanceTime = orderAcceptanceTime;
    }

    public Date getOrderAcceptanceTime() 
    {
        return orderAcceptanceTime;
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
    public void setValveBlock(String valveBlock) 
    {
        this.valveBlock = valveBlock;
    }

    public String getValveBlock() 
    {
        return valveBlock;
    }
    public void setFork(String Fork) 
    {
        this.Fork = Fork;
    }

    public String getFork() 
    {
        return Fork;
    }
    public void setDoorFrame(String doorFrame) 
    {
        this.doorFrame = doorFrame;
    }

    public String getDoorFrame() 
    {
        return doorFrame;
    }
    public void setAirFilter(String airFilter) 
    {
        this.airFilter = airFilter;
    }

    public String getAirFilter() 
    {
        return airFilter;
    }
    public void setAccessory(String Accessory) 
    {
        this.Accessory = Accessory;
    }

    public String getAccessory() 
    {
        return Accessory;
    }
    public void setTyre(String Tyre) 
    {
        this.Tyre = Tyre;
    }

    public String getTyre() 
    {
        return Tyre;
    }
    public void setConfiguration(String Configuration) 
    {
        this.Configuration = Configuration;
    }

    public String getConfiguration() 
    {
        return Configuration;
    }
    public void setCarNumber(String carNumber) 
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber() 
    {
        return carNumber;
    }
    public void setOrderSystemDeliveryTime(Date orderSystemDeliveryTime) 
    {
        this.orderSystemDeliveryTime = orderSystemDeliveryTime;
    }

    public Date getOrderSystemDeliveryTime() 
    {
        return orderSystemDeliveryTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mstsId", getMstsId())
            .append("Branch", getBranch())
            .append("contractNumber", getContractNumber())
            .append("orderNumber", getOrderNumber())
            .append("orderAcceptanceTime", getOrderAcceptanceTime())
            .append("vehicleModel", getVehicleModel())
            .append("Number", getNumber())
            .append("valveBlock", getValveBlock())
            .append("Fork", getFork())
            .append("doorFrame", getDoorFrame())
            .append("airFilter", getAirFilter())
            .append("Accessory", getAccessory())
            .append("Tyre", getTyre())
            .append("Configuration", getConfiguration())
            .append("carNumber", getCarNumber())
            .append("orderSystemDeliveryTime", getOrderSystemDeliveryTime())
            .toString();
    }
}
