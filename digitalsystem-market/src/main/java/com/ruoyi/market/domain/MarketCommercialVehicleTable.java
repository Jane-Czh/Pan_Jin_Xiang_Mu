package com.ruoyi.market.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * 商品车台账对象 Market_Commercial_Vehicle_table
 *
 * @author ruoyi
 * @date 2024-04-12
 */
@Data
public class MarketCommercialVehicleTable {
    private static final long serialVersionUID = 1L;
    /*
     * 不需要持久化的数据，用于接收前端入参
     * */
    @Transient
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    private String Month;

    private Double Average_Days_Difference;

    private Long Over_20_Percent_Count;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Transient
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Transient
    private Integer numberInput;
    /**
     * MCV_ID
     */
    private Long mcvId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 接单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptanceDate;

    /**
     * 上线日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date launchDate;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    /**
     * 合同号
     */
    @Excel(name = "合同号")
    private String contractNumber;

    /**
     * 车号
     */
    @Excel(name = "车号")
    private String carNumber;

    /**
     * 车型
     */
    @Excel(name = "车型")
    private String vehicleModel;

    /**
     * 门架高度
     */
    @Excel(name = "门架高度")
    private String doorFrameHeight;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Integer Number;

    /**
     * 属具
     */
    @Excel(name = "属具")
    private String Accessory;

    /**
     * 阀片（数量）
     */
    @Excel(name = "阀片", readConverterExp = "数=量")
    private String valveBlock;

    /**
     * 配置
     */
    @Excel(name = "配置")
    private String Configuration;

    /**
     * 计划完工期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedCompletionPeriod;

    /**
     * 客户
     */
    @Excel(name = "客户")
    private String Customer;

    /**
     * 车体上线日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车体上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vehicleLaunchDate;

    /**
     * 门架合装完工期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "门架合装完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionPeriodOfDoorFrameAssembly;

    /**
     * 试车完工期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "试车完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trialCompletionPeriod;

    /**
     * 特种作业
     */
    @Excel(name = "特种作业")
    private String specialOperations;

    /**
     * 精整完工期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "精整完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date precisionCompletionPeriod;

    /**
     * 生产现场问题
     */
    @Excel(name = "生产现场问题")
    private String productionSiteissues;

    /**
     * 现场异常问题采购
     */
    @Excel(name = "现场异常问题采购")
    private String procurementOfOnSiteAbnormalIssues;

    /**
     * 现场异常问题质量
     */
    @Excel(name = "现场异常问题质量")
    private String qualityOfOnSiteAbnormalIssues;

    /**
     * 上线
     */
    @Excel(name = "上线")
    private String goLive;

    /**
     * 入库
     */
    @Excel(name = "入库")
    private String Warehousing;

    /**
     * 生产周期
     */
    @Excel(name = "生产周期")
    private String productionCycle;

    public void setMcvId(Long mcvId)
    {
        this.mcvId = mcvId;
    }

    public Long getMcvId()
    {
        return mcvId;
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
    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }
    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }
    public void setCarNumber(String carNumber)
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber()
    {
        return carNumber;
    }
    public void setVehicleModel(String vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()
    {
        return vehicleModel;
    }
    public void setDoorFrameHeight(String doorFrameHeight)
    {
        this.doorFrameHeight = doorFrameHeight;
    }

    public String getDoorFrameHeight()
    {
        return doorFrameHeight;
    }
    public void setNumber(Integer Number)
    {
        this.Number = Number;
    }

    public Integer getNumber()
    {
        return Number;
    }
    public void setAccessory(String Accessory)
    {
        this.Accessory = Accessory;
    }

    public String getAccessory()
    {
        return Accessory;
    }
    public void setValveBlock(String valveBlock)
    {
        this.valveBlock = valveBlock;
    }

    public String getValveBlock()
    {
        return valveBlock;
    }
    public void setConfiguration(String Configuration)
    {
        this.Configuration = Configuration;
    }

    public String getConfiguration()
    {
        return Configuration;
    }
    public void setPlannedCompletionPeriod(Date plannedCompletionPeriod)
    {
        this.plannedCompletionPeriod = plannedCompletionPeriod;
    }

    public Date getPlannedCompletionPeriod()
    {
        return plannedCompletionPeriod;
    }
    public void setCustomer(String Customer)
    {
        this.Customer = Customer;
    }

    public String getCustomer()
    {
        return Customer;
    }
    public void setVehicleLaunchDate(Date vehicleLaunchDate)
    {
        this.vehicleLaunchDate = vehicleLaunchDate;
    }

    public Date getVehicleLaunchDate()
    {
        return vehicleLaunchDate;
    }
    public void setCompletionPeriodOfDoorFrameAssembly(Date completionPeriodOfDoorFrameAssembly)
    {
        this.completionPeriodOfDoorFrameAssembly = completionPeriodOfDoorFrameAssembly;
    }

    public Date getCompletionPeriodOfDoorFrameAssembly()
    {
        return completionPeriodOfDoorFrameAssembly;
    }
    public void setTrialCompletionPeriod(Date trialCompletionPeriod)
    {
        this.trialCompletionPeriod = trialCompletionPeriod;
    }

    public Date getTrialCompletionPeriod()
    {
        return trialCompletionPeriod;
    }
    public void setSpecialOperations(String specialOperations)
    {
        this.specialOperations = specialOperations;
    }

    public String getSpecialOperations()
    {
        return specialOperations;
    }
    public void setPrecisionCompletionPeriod(Date precisionCompletionPeriod)
    {
        this.precisionCompletionPeriod = precisionCompletionPeriod;
    }

    public Date getPrecisionCompletionPeriod()
    {
        return precisionCompletionPeriod;
    }
    public void setProductionSiteissues(String productionSiteissues)
    {
        this.productionSiteissues = productionSiteissues;
    }

    public String getProductionSiteissues()
    {
        return productionSiteissues;
    }
    public void setProcurementOfOnSiteAbnormalIssues(String procurementOfOnSiteAbnormalIssues)
    {
        this.procurementOfOnSiteAbnormalIssues = procurementOfOnSiteAbnormalIssues;
    }

    public String getProcurementOfOnSiteAbnormalIssues()
    {
        return procurementOfOnSiteAbnormalIssues;
    }
    public void setQualityOfOnSiteAbnormalIssues(String qualityOfOnSiteAbnormalIssues)
    {
        this.qualityOfOnSiteAbnormalIssues = qualityOfOnSiteAbnormalIssues;
    }

    public String getQualityOfOnSiteAbnormalIssues()
    {
        return qualityOfOnSiteAbnormalIssues;
    }
    public void setGoLive(String goLive)
    {
        this.goLive = goLive;
    }

    public String getGoLive()
    {
        return goLive;
    }
    public void setWarehousing(String Warehousing)
    {
        this.Warehousing = Warehousing;
    }

    public String getWarehousing()
    {
        return Warehousing;
    }
    public void setProductionCycle(String productionCycle)
    {
        this.productionCycle = productionCycle;
    }

    public String getProductionCycle()
    {
        return productionCycle;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mcvId", getMcvId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("acceptanceDate", getAcceptanceDate())
            .append("launchDate", getLaunchDate())
            .append("orderNumber", getOrderNumber())
            .append("contractNumber", getContractNumber())
            .append("carNumber", getCarNumber())
            .append("vehicleModel", getVehicleModel())
            .append("doorFrameHeight", getDoorFrameHeight())
            .append("Number", getNumber())
            .append("Accessory", getAccessory())
            .append("valveBlock", getValveBlock())
            .append("Configuration", getConfiguration())
            .append("plannedCompletionPeriod", getPlannedCompletionPeriod())
            .append("Customer", getCustomer())
            .append("vehicleLaunchDate", getVehicleLaunchDate())
            .append("completionPeriodOfDoorFrameAssembly", getCompletionPeriodOfDoorFrameAssembly())
            .append("trialCompletionPeriod", getTrialCompletionPeriod())
            .append("specialOperations", getSpecialOperations())
            .append("precisionCompletionPeriod", getPrecisionCompletionPeriod())
            .append("productionSiteissues", getProductionSiteissues())
            .append("procurementOfOnSiteAbnormalIssues", getProcurementOfOnSiteAbnormalIssues())
            .append("qualityOfOnSiteAbnormalIssues", getQualityOfOnSiteAbnormalIssues())
            .append("goLive", getGoLive())
            .append("Warehousing", getWarehousing())
            .append("productionCycle", getProductionCycle())
            .toString();
    }
}
