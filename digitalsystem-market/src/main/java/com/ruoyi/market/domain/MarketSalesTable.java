package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 市场科销售台账对象 Market_Sales_table
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Data
public class MarketSalesTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;
    @Transient
    private Integer numberInput;
    /** MS_ID */
    private Long msId;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

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

    /** 备注 */
    @Excel(name = "备注")
    private String Remarks;

    /** 交货形式 */
    @Excel(name = "交货形式")
    private String deliveryForm;

    /** 交货地点 */
    @Excel(name = "交货地点")
    private String deliveryLocation;

    /** 联系人 */
    @Excel(name = "联系人")
    private String Contacts;

    /** 电话 */
    @Excel(name = "电话")
    private String Telephone;

    /** 订单系统交货期（年月日） */
    @Excel(name = "订单系统交货期", readConverterExp = "年=月日")
    private Date orderSystemDeliveryTime;

    /** 订单超期预警 */
    @Excel(name = "订单超期预警")
    private String orderOverdueWarning;

    /** 计划发车日期（年月日） */
    @Excel(name = "计划发车日期", readConverterExp = "年=月日")
    private Date plannedDepartureDate;

    /** 实际发车日期（年月日） */
    @Excel(name = "实际发车日期", readConverterExp = "年=月日")
    private Date actualDepartureDate;

    /** 系统交货期（年月日） */
    @Excel(name = "系统交货期", readConverterExp = "年=月日")
    private Date systemDeliveryTime;

    /** 交货单号 */
    @Excel(name = "交货单号")
    private String deliveryNoteNumber;

    /** 技术准备完成时间（年月日） */
    @Excel(name = "技术准备完成时间", readConverterExp = "年=月日")
    private Date technicalPreparationCompletionTime;

    public void setMsId(Long msId) 
    {
        this.msId = msId;
    }

    public Long getMsId() 
    {
        return msId;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
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
    public void setRemarks(String Remarks) 
    {
        this.Remarks = Remarks;
    }

    public String getRemarks() 
    {
        return Remarks;
    }
    public void setDeliveryForm(String deliveryForm) 
    {
        this.deliveryForm = deliveryForm;
    }

    public String getDeliveryForm() 
    {
        return deliveryForm;
    }
    public void setDeliveryLocation(String deliveryLocation) 
    {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDeliveryLocation() 
    {
        return deliveryLocation;
    }
    public void setContacts(String Contacts) 
    {
        this.Contacts = Contacts;
    }

    public String getContacts() 
    {
        return Contacts;
    }
    public void setTelephone(String Telephone) 
    {
        this.Telephone = Telephone;
    }

    public String getTelephone() 
    {
        return Telephone;
    }
    public void setOrderSystemDeliveryTime(Date orderSystemDeliveryTime) 
    {
        this.orderSystemDeliveryTime = orderSystemDeliveryTime;
    }

    public Date getOrderSystemDeliveryTime() 
    {
        return orderSystemDeliveryTime;
    }
    public void setOrderOverdueWarning(String orderOverdueWarning) 
    {
        this.orderOverdueWarning = orderOverdueWarning;
    }

    public String getOrderOverdueWarning() 
    {
        return orderOverdueWarning;
    }
    public void setPlannedDepartureDate(Date plannedDepartureDate) 
    {
        this.plannedDepartureDate = plannedDepartureDate;
    }

    public Date getPlannedDepartureDate() 
    {
        return plannedDepartureDate;
    }
    public void setActualDepartureDate(Date actualDepartureDate) 
    {
        this.actualDepartureDate = actualDepartureDate;
    }

    public Date getActualDepartureDate() 
    {
        return actualDepartureDate;
    }
    public void setSystemDeliveryTime(Date systemDeliveryTime) 
    {
        this.systemDeliveryTime = systemDeliveryTime;
    }

    public Date getSystemDeliveryTime() 
    {
        return systemDeliveryTime;
    }
    public void setDeliveryNoteNumber(String deliveryNoteNumber) 
    {
        this.deliveryNoteNumber = deliveryNoteNumber;
    }

    public String getDeliveryNoteNumber() 
    {
        return deliveryNoteNumber;
    }
    public void setTechnicalPreparationCompletionTime(Date technicalPreparationCompletionTime) 
    {
        this.technicalPreparationCompletionTime = technicalPreparationCompletionTime;
    }

    public Date getTechnicalPreparationCompletionTime() 
    {
        return technicalPreparationCompletionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("msId", getMsId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
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
            .append("Remarks", getRemarks())
            .append("deliveryForm", getDeliveryForm())
            .append("deliveryLocation", getDeliveryLocation())
            .append("Contacts", getContacts())
            .append("Telephone", getTelephone())
            .append("orderSystemDeliveryTime", getOrderSystemDeliveryTime())
            .append("orderOverdueWarning", getOrderOverdueWarning())
            .append("plannedDepartureDate", getPlannedDepartureDate())
            .append("actualDepartureDate", getActualDepartureDate())
            .append("systemDeliveryTime", getSystemDeliveryTime())
            .append("deliveryNoteNumber", getDeliveryNoteNumber())
            .append("technicalPreparationCompletionTime", getTechnicalPreparationCompletionTime())
            .toString();
    }
}
