package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [财务]手动填报指标对象 Financial_Indicators_HandFill_table
 * 
 * @author loophong
 * @date 2024-03-29
 */
public class FinancialIndicatorsHandfillTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long fihfId;

    /** 整机销售收入 */
    private BigDecimal totalSalesRevenue;

    /** 集团外部销售收入 */
    private BigDecimal externalGroupSalesRevenue;

    /** 整车产量 */
    private Long totalVehicleProduction;

    /** 整车销量 */
    private Long totalVehicleSales;

    /** 新产品销售收入 */
    private BigDecimal newProductSalesRevenue;

    /**
     * 特色产品收入
     */
    private BigDecimal specialtyProductRevenue;

    /**
     * 整机销售成本
     */
    private BigDecimal totalSalesCost;

    /**
     * 当月制造费用
     */
    private BigDecimal manufacturingExpensesMonth;

    /**
     * 储备车金额
     */
    private BigDecimal reserveCarAmount;

    /**
     * 资金周转率
     */
    private BigDecimal capitalTurnoverRate;

    /**
     * 库存商品周转率
     */
    private BigDecimal inventoryTurnoverRate;

    /**
     * 原材料周转率
     */
    private BigDecimal rawMaterialTurnoverRate;

    /** 在制品周转率 */
    private BigDecimal inprogressTurnoverRate;

    /** 一年以上暂估行项目 */
    private Long longEstimatedItems;

    /** 当日在制品金额 */
    private BigDecimal inprogressDayrevenue;

    /** 当月经济增加值 */
    private BigDecimal addedValueMonthly;

    /** 填报人 */
    private String Reporter;

    /** 填报时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reportingDate;

    /** 更新人 */
    private String updatedBy;

    /** 跟新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;

    public void setFihfId(Long fihfId) 
    {
        this.fihfId = fihfId;
    }

    public Long getFihfId() 
    {
        return fihfId;
    }
    public void setTotalSalesRevenue(BigDecimal totalSalesRevenue) 
    {
        this.totalSalesRevenue = totalSalesRevenue;
    }

    public BigDecimal getTotalSalesRevenue() 
    {
        return totalSalesRevenue;
    }
    public void setExternalGroupSalesRevenue(BigDecimal externalGroupSalesRevenue) 
    {
        this.externalGroupSalesRevenue = externalGroupSalesRevenue;
    }

    public BigDecimal getExternalGroupSalesRevenue() 
    {
        return externalGroupSalesRevenue;
    }
    public void setTotalVehicleProduction(Long totalVehicleProduction) 
    {
        this.totalVehicleProduction = totalVehicleProduction;
    }

    public Long getTotalVehicleProduction() 
    {
        return totalVehicleProduction;
    }
    public void setTotalVehicleSales(Long totalVehicleSales) 
    {
        this.totalVehicleSales = totalVehicleSales;
    }

    public Long getTotalVehicleSales() 
    {
        return totalVehicleSales;
    }
    public void setNewProductSalesRevenue(BigDecimal newProductSalesRevenue) 
    {
        this.newProductSalesRevenue = newProductSalesRevenue;
    }

    public BigDecimal getNewProductSalesRevenue() 
    {
        return newProductSalesRevenue;
    }
    public void setSpecialtyProductRevenue(BigDecimal specialtyProductRevenue) 
    {
        this.specialtyProductRevenue = specialtyProductRevenue;
    }

    public BigDecimal getSpecialtyProductRevenue() 
    {
        return specialtyProductRevenue;
    }
    public void setTotalSalesCost(BigDecimal totalSalesCost) 
    {
        this.totalSalesCost = totalSalesCost;
    }

    public BigDecimal getTotalSalesCost() 
    {
        return totalSalesCost;
    }
    public void setManufacturingExpensesMonth(BigDecimal manufacturingExpensesMonth) {
        this.manufacturingExpensesMonth = manufacturingExpensesMonth;
    }

    public BigDecimal getManufacturingExpensesMonth() {
        return manufacturingExpensesMonth;
    }

    public void setReserveCarAmount(BigDecimal reserveCarAmount) {
        this.reserveCarAmount = reserveCarAmount;
    }

    public BigDecimal getReserveCarAmount() {
        return reserveCarAmount;
    }

    public void setCapitalTurnoverRate(BigDecimal capitalTurnoverRate)
    {
        this.capitalTurnoverRate = capitalTurnoverRate;
    }

    public BigDecimal getCapitalTurnoverRate() 
    {
        return capitalTurnoverRate;
    }
    public void setInventoryTurnoverRate(BigDecimal inventoryTurnoverRate) 
    {
        this.inventoryTurnoverRate = inventoryTurnoverRate;
    }

    public BigDecimal getInventoryTurnoverRate() 
    {
        return inventoryTurnoverRate;
    }
    public void setRawMaterialTurnoverRate(BigDecimal rawMaterialTurnoverRate) 
    {
        this.rawMaterialTurnoverRate = rawMaterialTurnoverRate;
    }

    public BigDecimal getRawMaterialTurnoverRate() 
    {
        return rawMaterialTurnoverRate;
    }
    public void setInprogressTurnoverRate(BigDecimal inprogressTurnoverRate) 
    {
        this.inprogressTurnoverRate = inprogressTurnoverRate;
    }

    public BigDecimal getInprogressTurnoverRate() 
    {
        return inprogressTurnoverRate;
    }
    public void setLongEstimatedItems(Long longEstimatedItems) 
    {
        this.longEstimatedItems = longEstimatedItems;
    }

    public Long getLongEstimatedItems() 
    {
        return longEstimatedItems;
    }
    public void setInprogressDayrevenue(BigDecimal inprogressDayrevenue) 
    {
        this.inprogressDayrevenue = inprogressDayrevenue;
    }

    public BigDecimal getInprogressDayrevenue() 
    {
        return inprogressDayrevenue;
    }
    public void setAddedValueMonthly(BigDecimal addedValueMonthly) 
    {
        this.addedValueMonthly = addedValueMonthly;
    }

    public BigDecimal getAddedValueMonthly() 
    {
        return addedValueMonthly;
    }
    public void setReporter(String Reporter) 
    {
        this.Reporter = Reporter;
    }

    public String getReporter() 
    {
        return Reporter;
    }
    public void setReportingDate(Date reportingDate) 
    {
        this.reportingDate = reportingDate;
    }

    public Date getReportingDate() 
    {
        return reportingDate;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setUpdatedDate(Date updatedDate) 
    {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() 
    {
        return updatedDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fihfId", getFihfId())
                .append("totalSalesRevenue", getTotalSalesRevenue())
                .append("externalGroupSalesRevenue", getExternalGroupSalesRevenue())
                .append("totalVehicleProduction", getTotalVehicleProduction())
                .append("totalVehicleSales", getTotalVehicleSales())
                .append("newProductSalesRevenue", getNewProductSalesRevenue())
                .append("specialtyProductRevenue", getSpecialtyProductRevenue())
                .append("totalSalesCost", getTotalSalesCost())
                .append("manufacturingExpensesMonth", getManufacturingExpensesMonth())
            .append("reserveCarAmount", getReserveCarAmount())
            .append("capitalTurnoverRate", getCapitalTurnoverRate())
            .append("inventoryTurnoverRate", getInventoryTurnoverRate())
            .append("rawMaterialTurnoverRate", getRawMaterialTurnoverRate())
            .append("inprogressTurnoverRate", getInprogressTurnoverRate())
            .append("longEstimatedItems", getLongEstimatedItems())
            .append("inprogressDayrevenue", getInprogressDayrevenue())
            .append("addedValueMonthly", getAddedValueMonthly())
            .append("Reporter", getReporter())
            .append("reportingDate", getReportingDate())
            .append("updatedBy", getUpdatedBy())
            .append("updatedDate", getUpdatedDate())
            .toString();
    }
}
