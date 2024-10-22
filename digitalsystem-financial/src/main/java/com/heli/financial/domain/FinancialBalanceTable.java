package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务-资产负债对象 Financial_Balance_table
 *
 * @author ruoyi
 * @date 2024-04-03
 */
@Data
public class FinancialBalanceTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id(主键)
     */
    private Long fbId;

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
     * 存货增长率(计算)
     */
    private Double growthRateInventory;

    /**
     * 销售增长率(计算)
     */
    private Double lowValueConsumablesAmount;

    /**
     * 应收账款
     */
    @RecordTemplate(rowNo = 10, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "应收账款")
    private BigDecimal receivables;

    /**
     * 应收帐款周转率(计算)
     */
    private Double turnoverRateReceivable;

}
