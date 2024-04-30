package com.heli.financial.service;

import com.ruoyi.common.core.domain.entity.DisplayEntity;

import java.util.Date;
import java.util.List;

/**
 * @description: 数据展示接口
 * @author: hong
 * @date: 2024/4/2 14:16
 **/

public interface IFinancialDisplayService {
    /**
     * 主营业务收入 指标1
     */
    List<DisplayEntity> selectMainRevenue(Date startTime, Date endTime);

    /**
     * 整机销售收入 指标2
     */
    List<DisplayEntity> selectTotalSalesRevenue(Date startTime, Date endTime);

    /**
     * 集团外部销售收入 指标3
     */
    List<DisplayEntity> selectExternalGroupSalesRevenue(Date startTime, Date endTime);

    /**
     * 整车产量 指标4
     */
    List<DisplayEntity> selectTotalVehicleProduction(Date startTime, Date endTime);

    /**
     * 整车销量 指标5
     */
    List<DisplayEntity> selectTotalVehicleSales(Date startTime, Date endTime);

    /**
     * 新产品销售收入 指标6
     */
    List<DisplayEntity> selectNewProductSalesRevenue(Date startTime, Date endTime);

    /**
     * 特色产品收入 指标7
     */
    List<DisplayEntity> selectSpecialtyProductRevenue(Date startTime, Date endTime);

    /**
     * 主营业务成本 指标8
     */
    List<DisplayEntity> COGS(Date startTime, Date endTime);

    /**
     * 整机销售成本 指标9
     */
    List<DisplayEntity> selectTotalSalesCost(Date startTime, Date endTime);

    /**
     * 净利润 指标10
     */
    List<DisplayEntity> selectNetProfit(Date startTime, Date endTime);

    /**
     * 当月管理费用 指标20
     */
    List<DisplayEntity> selectManagementExpense(Date startTime, Date endTime);

    /**
     * 当月研发费用 指标21
     */
    List<DisplayEntity> selectRdExpense(Date startTime, Date endTime);

    /**
     * 当月制造费用 指标22
     */
    List<DisplayEntity> selectManufacturingExpensesMonth(Date startTime, Date endTime);

    /**
     * 当月原材料存货额 指标26
     */
    List<DisplayEntity> selectMonthlyRawMaterialInventory(Date startTime, Date endTime);

    /**
     * 当月在制品存货额 指标27
     */
    List<DisplayEntity> selectMonthlyWorkInProgressInventory(Date startTime, Date endTime);

    /**
     * 当月库存商品存货额 指标28
     */
    List<DisplayEntity> selectMonthAmountInStock(Date startTime, Date endTime);

    /**
     * 存货增长率/销售增长率 指标30
     */
    List<DisplayEntity> selectGrowthRateInventoryAndSales(Date startTime, Date endTime);

    /**
     * 应收帐款周转率 指标32
     */
    List<DisplayEntity> selectTurnoverRateReceivable(Date startTime, Date endTime);

    /**
     * 资金周转率 指标33
     */
    List<DisplayEntity> selectCapitalTurnoverRate(Date startTime, Date endTime);

    /**
     * 库存商品周转率 指标34
     */
    List<DisplayEntity> selectInventoryTurnoverRate(Date startTime, Date endTime);

    /**
     * 原材料周转率 指标35
     */
    List<DisplayEntity> selectRawMaterialTurnoverRate(Date startTime, Date endTime);

    /**
     * 在制品周转率 指标36
     */
    List<DisplayEntity> selectInprogressTurnoverRate(Date startTime, Date endTime);

    /**
     * 一年以上暂估行项目 指标66
     */
    List<DisplayEntity> selectLongEstimatedItems(Date startTime, Date endTime);

    /**
     * 当日在制品金额 指标70
     */
    List<DisplayEntity> selectInprogressDayrevenue(Date startTime, Date endTime);

    /**
     * 月度存货总金额 指标77
     */
    List<DisplayEntity> selectMonthlyInventoryTotalAmount(Date startTime, Date endTime);

    /**
     * 当月经济增加值 指标78
     */
    List<DisplayEntity> selectAddedValueMonthly(Date startTime, Date endTime);
}
