package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务-资产负债对象 Financial_Balance_table
 *
 * @author ruoyi
 * @date 2024-04-03
 */
public class FinancialBalanceTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id(主键)
     */
    private Long fbId;

    /**
     * 导入人
     */
    private String createdBy;

    /**
     * 导入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 在途物资
     */
    @RecordTemplate(rowNo = 29, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "在途物资")
    private BigDecimal inTransitInventory;

    /**
     * 原材料
     */
    @RecordTemplate(rowNo = 30, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "原材料")
    private BigDecimal materials;

    /**
     * 材料成本差异
     */
    @RecordTemplate(rowNo = 31, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "材料成本差异")
    private BigDecimal materialCostVariance;

    /**
     * 材料成本差异-差异待分摊
     */
    @RecordTemplate(rowNo = 32, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "材料成本差异-差异待分摊")
    private BigDecimal materialCostVarianceUnallocated;

    /**
     * 当月原材料存货额(前四行计算和)
     */
    private BigDecimal monthlyRawMaterialInventory;

    /**
     * 库存商品-半成品
     */
    @RecordTemplate(rowNo = 33, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "库存商品-半成品")
    private BigDecimal workInProgressSemiFinishedGoods;

    /**
     * 产品成本差异-半成品
     */
    @RecordTemplate(rowNo = 35, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "产品成本差异-半成品")
    private BigDecimal productCostVarianceSemiFinishedGoods;

    /**
     * 月末在制品
     */
    @RecordTemplate(rowNo = 45, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "月末在制品")
    private BigDecimal workInProgressEndOfMonth;

    /**
     * 当月在制品存货额(前三行和)
     */
    private BigDecimal monthlyWorkInProgressInventory;

    /**
     * 库存商品-整车
     */
    @RecordTemplate(rowNo = 34, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "库存商品-整车")
    private BigDecimal inventoryVehicles;

    /**
     * 产品成本差异-产成品
     */
    @RecordTemplate(rowNo = 36, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "营业收产品成本差异-产成品入")
    private BigDecimal pcvFinished;

    /**
     * 当月库存商品存货额(计算)
     */
    private BigDecimal monthAmountInStock;

    /**
     * 月度存货总金额(导入来源为存货列)
     */
    @RecordTemplate(rowNo = 28, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "月度存货总金额")
    private BigDecimal monthlyInventoryTotalAmount;

    /**
     * 存货增长率/销售增长率(计算)
     */
    private Double growthRateInventorySales;

    /**
     * 应收账款
     */
    @RecordTemplate(rowNo = 28, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "营业收入")
    private BigDecimal receivables;

    /**
     * 应收帐款周转率(计算)
     */
    private Double turnoverRateReceivable;

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public Long getFbId() {
        return fbId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setInTransitInventory(BigDecimal inTransitInventory) {
        this.inTransitInventory = inTransitInventory;
    }

    public BigDecimal getInTransitInventory() {
        return inTransitInventory;
    }

    public void setMaterials(BigDecimal materials) {
        this.materials = materials;
    }

    public BigDecimal getMaterials() {
        return materials;
    }

    public void setMaterialCostVariance(BigDecimal materialCostVariance) {
        this.materialCostVariance = materialCostVariance;
    }

    public BigDecimal getMaterialCostVariance() {
        return materialCostVariance;
    }

    public void setMaterialCostVarianceUnallocated(BigDecimal materialCostVarianceUnallocated) {
        this.materialCostVarianceUnallocated = materialCostVarianceUnallocated;
    }

    public BigDecimal getMaterialCostVarianceUnallocated() {
        return materialCostVarianceUnallocated;
    }

    public void setMonthlyRawMaterialInventory(BigDecimal monthlyRawMaterialInventory) {
        this.monthlyRawMaterialInventory = monthlyRawMaterialInventory;
    }

    public BigDecimal getMonthlyRawMaterialInventory() {
        return monthlyRawMaterialInventory;
    }

    public void setWorkInProgressSemiFinishedGoods(BigDecimal workInProgressSemiFinishedGoods) {
        this.workInProgressSemiFinishedGoods = workInProgressSemiFinishedGoods;
    }

    public BigDecimal getWorkInProgressSemiFinishedGoods() {
        return workInProgressSemiFinishedGoods;
    }

    public void setProductCostVarianceSemiFinishedGoods(BigDecimal productCostVarianceSemiFinishedGoods) {
        this.productCostVarianceSemiFinishedGoods = productCostVarianceSemiFinishedGoods;
    }

    public BigDecimal getProductCostVarianceSemiFinishedGoods() {
        return productCostVarianceSemiFinishedGoods;
    }

    public void setWorkInProgressEndOfMonth(BigDecimal workInProgressEndOfMonth) {
        this.workInProgressEndOfMonth = workInProgressEndOfMonth;
    }

    public BigDecimal getWorkInProgressEndOfMonth() {
        return workInProgressEndOfMonth;
    }

    public void setMonthlyWorkInProgressInventory(BigDecimal monthlyWorkInProgressInventory) {
        this.monthlyWorkInProgressInventory = monthlyWorkInProgressInventory;
    }

    public BigDecimal getMonthlyWorkInProgressInventory() {
        return monthlyWorkInProgressInventory;
    }

    public void setInventoryVehicles(BigDecimal inventoryVehicles) {
        this.inventoryVehicles = inventoryVehicles;
    }

    public BigDecimal getInventoryVehicles() {
        return inventoryVehicles;
    }

    public void setPcvFinished(BigDecimal pcvFinished) {
        this.pcvFinished = pcvFinished;
    }

    public BigDecimal getPcvFinished() {
        return pcvFinished;
    }

    public void setMonthAmountInStock(BigDecimal monthAmountInStock) {
        this.monthAmountInStock = monthAmountInStock;
    }

    public BigDecimal getMonthAmountInStock() {
        return monthAmountInStock;
    }

    public void setMonthlyInventoryTotalAmount(BigDecimal monthlyInventoryTotalAmount) {
        this.monthlyInventoryTotalAmount = monthlyInventoryTotalAmount;
    }

    public BigDecimal getMonthlyInventoryTotalAmount() {
        return monthlyInventoryTotalAmount;
    }

    public void setGrowthRateInventorySales(Double growthRateInventorySales) {
        this.growthRateInventorySales = growthRateInventorySales;
    }

    public Double getGrowthRateInventorySales() {
        return growthRateInventorySales;
    }

    public void setReceivables(BigDecimal receivables) {
        this.receivables = receivables;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public void setTurnoverRateReceivable(Double turnoverRateReceivable) {
        this.turnoverRateReceivable = turnoverRateReceivable;
    }

    public Double getTurnoverRateReceivable() {
        return turnoverRateReceivable;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fbId", getFbId())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("yearAndMonth", getYearAndMonth())
                .append("inTransitInventory", getInTransitInventory())
                .append("materials", getMaterials())
                .append("materialCostVariance", getMaterialCostVariance())
                .append("materialCostVarianceUnallocated", getMaterialCostVarianceUnallocated())
                .append("monthlyRawMaterialInventory", getMonthlyRawMaterialInventory())
                .append("workInProgressSemiFinishedGoods", getWorkInProgressSemiFinishedGoods())
                .append("productCostVarianceSemiFinishedGoods", getProductCostVarianceSemiFinishedGoods())
                .append("workInProgressEndOfMonth", getWorkInProgressEndOfMonth())
                .append("monthlyWorkInProgressInventory", getMonthlyWorkInProgressInventory())
                .append("inventoryVehicles", getInventoryVehicles())
                .append("pcvFinished", getPcvFinished())
                .append("monthAmountInStock", getMonthAmountInStock())
                .append("monthlyInventoryTotalAmount", getMonthlyInventoryTotalAmount())
                .append("growthRateInventorySales", getGrowthRateInventorySales())
                .append("receivables", getReceivables())
                .append("turnoverRateReceivable", getTurnoverRateReceivable())
                .toString();
    }
}
