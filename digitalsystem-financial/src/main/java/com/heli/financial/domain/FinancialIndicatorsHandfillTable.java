package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [财务]手动填报指标对象 Financial_Indicators_HandFill_table
 *
 * @author loophong
 * @date 2024-03-29
 */
@Data
public class FinancialIndicatorsHandfillTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long fihfId;

    /**
     * 数据产生的年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 整机销售收入
     */
    private BigDecimal totalSalesRevenue;

    /**
     * 集团外部销售收入
     */
    private BigDecimal externalGroupSalesRevenue;

    /**
     * 整车产量
     */
    private Long totalVehicleProduction;

    /**
     * 整车销量
     */
    private Long totalVehicleSales;

    /**
     * 新产品销售收入
     */
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

    /**
     * 在制品周转率
     */
    private BigDecimal inprogressTurnoverRate;

    /**
     * 一年以上暂估行项目
     */
    private Long longEstimatedItems;

    /**
     * 当日在制品金额
     */
    private BigDecimal inprogressDayrevenue;

    /**
     * 当月经济增加值
     */
    private BigDecimal addedValueMonthly;


}
