package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务-资产负债对象 Financial_Balance_table
 *
 * @author ruoyi
 * @date 2024-03-31
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
     * 月度存货总金额(导入来源为存货列)
     */
    @RecordTemplate(rowNo = 28, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "月度存货总金额(导入来源为存货列)")
    private BigDecimal monthlyInventoryTotalAmount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public BigDecimal getInTransitInventory() {
        return inTransitInventory;
    }

    public void setInTransitInventory(BigDecimal inTransitInventory) {
        this.inTransitInventory = inTransitInventory;
    }

    public BigDecimal getMaterials() {
        return materials;
    }

    public void setMaterials(BigDecimal materials) {
        this.materials = materials;
    }

    public BigDecimal getMaterialCostVariance() {
        return materialCostVariance;
    }

    public void setMaterialCostVariance(BigDecimal materialCostVariance) {
        this.materialCostVariance = materialCostVariance;
    }

    public BigDecimal getMaterialCostVarianceUnallocated() {
        return materialCostVarianceUnallocated;
    }

    public void setMaterialCostVarianceUnallocated(BigDecimal materialCostVarianceUnallocated) {
        this.materialCostVarianceUnallocated = materialCostVarianceUnallocated;
    }

    public BigDecimal getMonthlyRawMaterialInventory() {
        return monthlyRawMaterialInventory;
    }

    public void setMonthlyRawMaterialInventory(BigDecimal monthlyRawMaterialInventory) {
        this.monthlyRawMaterialInventory = monthlyRawMaterialInventory;
    }

    public BigDecimal getWorkInProgressSemiFinishedGoods() {
        return workInProgressSemiFinishedGoods;
    }

    public void setWorkInProgressSemiFinishedGoods(BigDecimal workInProgressSemiFinishedGoods) {
        this.workInProgressSemiFinishedGoods = workInProgressSemiFinishedGoods;
    }

    public BigDecimal getProductCostVarianceSemiFinishedGoods() {
        return productCostVarianceSemiFinishedGoods;
    }

    public void setProductCostVarianceSemiFinishedGoods(BigDecimal productCostVarianceSemiFinishedGoods) {
        this.productCostVarianceSemiFinishedGoods = productCostVarianceSemiFinishedGoods;
    }

    public BigDecimal getWorkInProgressEndOfMonth() {
        return workInProgressEndOfMonth;
    }

    public void setWorkInProgressEndOfMonth(BigDecimal workInProgressEndOfMonth) {
        this.workInProgressEndOfMonth = workInProgressEndOfMonth;
    }

    public BigDecimal getMonthlyWorkInProgressInventory() {
        return monthlyWorkInProgressInventory;
    }

    public void setMonthlyWorkInProgressInventory(BigDecimal monthlyWorkInProgressInventory) {
        this.monthlyWorkInProgressInventory = monthlyWorkInProgressInventory;
    }

    public BigDecimal getMonthlyInventoryTotalAmount() {
        return monthlyInventoryTotalAmount;
    }

    public void setMonthlyInventoryTotalAmount(BigDecimal monthlyInventoryTotalAmount) {
        this.monthlyInventoryTotalAmount = monthlyInventoryTotalAmount;
    }

    public FinancialBalanceTable() {
    }

    public FinancialBalanceTable(Long fbId, String createdBy, Date createdTime, Date yearAndMonth, BigDecimal inTransitInventory, BigDecimal materials, BigDecimal materialCostVariance, BigDecimal materialCostVarianceUnallocated, BigDecimal monthlyRawMaterialInventory, BigDecimal workInProgressSemiFinishedGoods, BigDecimal productCostVarianceSemiFinishedGoods, BigDecimal workInProgressEndOfMonth, BigDecimal monthlyWorkInProgressInventory, BigDecimal monthlyInventoryTotalAmount) {
        this.fbId = fbId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.yearAndMonth = yearAndMonth;
        this.inTransitInventory = inTransitInventory;
        this.materials = materials;
        this.materialCostVariance = materialCostVariance;
        this.materialCostVarianceUnallocated = materialCostVarianceUnallocated;
        this.monthlyRawMaterialInventory = monthlyRawMaterialInventory;
        this.workInProgressSemiFinishedGoods = workInProgressSemiFinishedGoods;
        this.productCostVarianceSemiFinishedGoods = productCostVarianceSemiFinishedGoods;
        this.workInProgressEndOfMonth = workInProgressEndOfMonth;
        this.monthlyWorkInProgressInventory = monthlyWorkInProgressInventory;
        this.monthlyInventoryTotalAmount = monthlyInventoryTotalAmount;
    }


    @Override
    public String toString() {
        return "FinancialBalanceTable{" +
                "fbId=" + fbId +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", yearAndMonth=" + yearAndMonth +
                ", inTransitInventory=" + inTransitInventory +
                ", materials=" + materials +
                ", materialCostVariance=" + materialCostVariance +
                ", materialCostVarianceUnallocated=" + materialCostVarianceUnallocated +
                ", monthlyRawMaterialInventory=" + monthlyRawMaterialInventory +
                ", workInProgressSemiFinishedGoods=" + workInProgressSemiFinishedGoods +
                ", productCostVarianceSemiFinishedGoods=" + productCostVarianceSemiFinishedGoods +
                ", workInProgressEndOfMonth=" + workInProgressEndOfMonth +
                ", monthlyWorkInProgressInventory=" + monthlyWorkInProgressInventory +
                ", monthlyInventoryTotalAmount=" + monthlyInventoryTotalAmount +
                '}';
    }
}
