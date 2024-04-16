package com.heli.financial.mapper;

import com.heli.financial.domain.DisplayEntity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//@DataSource(value = DataSourceType.SLAVE)
public interface DisplayMapper {
    /**
     * 主营业务收入 指标1
     */
    List<DisplayEntity> selectMainRevenue(@Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime);

    /**
     * 整机销售收入 指标2
     */
    List<DisplayEntity> selectTotalSalesRevenue(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    /**
     * 集团外部销售收入 指标3
     */
    List<DisplayEntity> selectExternalGroupSalesRevenue(@Param("startTime") Date startTime,
                                                        @Param("endTime") Date endTime);

    /**
     * 整车产量 指标4
     */
    List<DisplayEntity> selectTotalVehicleProduction(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 整车销量 指标5
     */
    List<DisplayEntity> selectTotalVehicleSales(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    /**
     * 新产品销售收入 指标6
     */
    List<DisplayEntity> selectNewProductSalesRevenue(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 特色产品收入 指标7
     */
    List<DisplayEntity> selectSpecialtyProductRevenue(@Param("startTime") Date startTime,
                                                      @Param("endTime") Date endTime);

    /**
     * 主营业务成本 指标8
     */
    List<DisplayEntity> COGS(@Param("startTime") Date startTime,
                             @Param("endTime") Date endTime);

    /**
     * 整机销售成本 指标9
     */
    List<DisplayEntity> selectTotalSalesCost(@Param("startTime") Date startTime,
                                             @Param("endTime") Date endTime);

    /**
     * 净利润 指标10
     */
    List<DisplayEntity> selectNetProfit(@Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime);

    /**
     * 当月管理费用 指标20
     */
    List<DisplayEntity> selectManagementExpense(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);

    /**
     * 当月研发费用 指标21
     */
    List<DisplayEntity> selectRdExpense(@Param("startTime") Date startTime,
                                        @Param("endTime") Date endTime);

    /**
     * 当月制造费用 指标22
     */
    List<DisplayEntity> selectManufacturingExpensesMonth(@Param("startTime") Date startTime,
                                                         @Param("endTime") Date endTime);

    /**
     * 当月原材料存货额 指标26
     */
    List<DisplayEntity> selectMonthlyRawMaterialInventory(@Param("startTime") Date startTime,
                                                          @Param("endTime") Date endTime);

    /**
     * 当月在制品存货额 指标27
     */
    List<DisplayEntity> selectMonthlyWorkInProgressInventory(@Param("startTime") Date startTime,
                                                             @Param("endTime") Date endTime);

    /**
     * 当月库存商品存货额 指标28
     */
    List<DisplayEntity> selectMonthAmountInStock(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime);

    /**
     * 存货增长率/销售增长率 指标30
     */
    List<DisplayEntity> selectGrowthRateInventorySales(@Param("startTime") Date startTime,
                                                       @Param("endTime") Date endTime);

    /**
     * 应收帐款周转率 指标32
     */
    List<DisplayEntity> selectTurnoverRateReceivable(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 资金周转率 指标33
     */
    List<DisplayEntity> selectCapitalTurnoverRate(@Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime);

    /**
     * 库存商品周转率 指标34
     */
    List<DisplayEntity> selectInventoryTurnoverRate(@Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime);

    /**
     * 原材料周转率 指标35
     */
    List<DisplayEntity> selectRawMaterialTurnoverRate(@Param("startTime") Date startTime,
                                                      @Param("endTime") Date endTime);

    /**
     * 在制品周转率 指标36
     */
    List<DisplayEntity> selectInprogressTurnoverRate(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 一年以上暂估行项目 指标66
     */
    List<DisplayEntity> selectLongEstimatedItems(@Param("startTime") Date startTime,
                                                 @Param("endTime") Date endTime);

    /**
     * 当日在制品金额 指标70
     */
    List<DisplayEntity> selectInprogressDayrevenue(@Param("startTime") Date startTime,
                                                   @Param("endTime") Date endTime);

    /**
     * 月度存货总金额 指标77
     */
    List<DisplayEntity> selectMonthlyInventoryTotalAmount(@Param("startTime") Date startTime,
                                                          @Param("endTime") Date endTime);

    /**
     * 当月经济增加值 指标78
     */
    List<DisplayEntity> selectAddedValueMonthly(@Param("startTime") Date startTime,
                                                @Param("endTime") Date endTime);
}
