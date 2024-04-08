package com.heli.financial.service;

import com.heli.financial.domain.DisplayEntity;

import java.util.List;

/**
 * @description: 数据展示接口
 * @author: hong
 * @date: 2024/4/2 14:16
 **/

public interface DisplayService {
    /**
     * 主营业务收入 指标1
     */
    List<DisplayEntity> selectMainRevenue(String year);

    /**
     * 整机销售收入 指标2
     */
    List<DisplayEntity> selectTotalSalesRevenue(String year);

    /**
     * 集团外部销售收入 指标3
     */
    List<DisplayEntity> selectExternalGroupSalesRevenue(String year);

    /**
     * 整车产量 指标4
     */
    List<DisplayEntity> selectTotalVehicleProduction(String year);

    /**
     * 整车销量 指标5
     */
    List<DisplayEntity> selectTotalVehicleSales(String year);

    /**
     * 新产品销售收入 指标6
     */
    List<DisplayEntity> selectNewProductSalesRevenue(String year);

    /**
     * 特色产品收入 指标7
     */
    List<DisplayEntity> selectSpecialtyProductRevenue(String year);

    /**
     * 主营业务成本 指标8
     */
    List<DisplayEntity> COGS(String year);

    /**
     * 整机销售成本 指标9
     */
    List<DisplayEntity> selectTotalSalesCost(String year);

    /**
     * 净利润 指标10
     */
    List<DisplayEntity> selectNetProfit(String year);

    /**
     * 当月管理费用 指标20
     */
    List<DisplayEntity> selectManagementExpense(String year);

    /**
     * 当月研发费用 指标21
     */
    List<DisplayEntity> selectRdExpense(String year);

    /**
     * 当月制造费用 指标22
     */
    List<DisplayEntity> selectManufacturingExpensesMonth(String year);

    /**
     * 当月原材料存货额 指标26
     */
    List<DisplayEntity> selectMonthlyRawMaterialInventory(String year);

    /**
     * 当月在制品存货额 指标27
     */
    List<DisplayEntity> selectMonthlyWorkInProgressInventory(String year);

    /**
     * 当月库存商品存货额 指标28
     */
    List<DisplayEntity> selectMonthAmountInStock(String year);

    /**
     * 存货增长率/销售增长率 指标30
     */
    List<DisplayEntity> selectGrowthRateInventorySales(String year);

    /**
     * 应收帐款周转率 指标32
     */
    List<DisplayEntity> selectTurnoverRateReceivable(String year);

    /**
     * 资金周转率 指标33
     */
    List<DisplayEntity> selectCapitalTurnoverRate(String year);

    /**
     * 库存商品周转率 指标34
     */
    List<DisplayEntity> selectInventoryTurnoverRate(String year);

    /**
     * 原材料周转率 指标35
     */
    List<DisplayEntity> selectRawMaterialTurnoverRate(String year);

    /**
     * 在制品周转率 指标36
     */
    List<DisplayEntity> selectInprogressTurnoverRate(String year);

    /**
     * 一年以上暂估行项目 指标66
     */
    List<DisplayEntity> selectLongEstimatedItems(String year);

    /**
     * 当日在制品金额 指标70
     */
    List<DisplayEntity> selectInprogressDayrevenue(String year);

    /**
     * 月度存货总金额 指标77
     */
    List<DisplayEntity> selectMonthlyInventoryTotalAmount(String year);

    /**
     * 当月经济增加值 指标78
     */
    List<DisplayEntity> selectAddedValueMonthly(String year);
}
