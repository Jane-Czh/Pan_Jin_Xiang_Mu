package com.heli.financial.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heli.financial.service.IFinancialDisplayService;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @description: 数据展示Controller
 * @author: hong
 * @date: 2024/4/8 11:05
 * @param: year
 * @return: data
 **/
@RestController
@RequestMapping("/financial/display")
public class FinancialDisplayController extends BaseController {
    @Autowired
    private IFinancialDisplayService IFinancialDisplayService;


    /**
     * 主营业务收入 指标1
     */
    @PreAuthorize("@ss.hasPermi('financial:display:mainRevenue')")
    @PostMapping("/mainRevenue")
    public TableDataInfo mainRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMainRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 整机销售收入 指标2
     */
    @PreAuthorize("@ss.hasPermi('financial:display:totalSalesRevenue')")
    @PostMapping("/totalSalesRevenue")
    public TableDataInfo totalSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 集团外部销售收入 指标3
     */
    @PreAuthorize("@ss.hasPermi('financial:display:externalGroupSalesRevenue')")
    @PostMapping("/externalGroupSalesRevenue")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public TableDataInfo externalGroupSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectExternalGroupSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 整车产量 指标4
     */
    @PreAuthorize("@ss.hasPermi('financial:display:totalVehicleProduction')")
    @PostMapping("/totalVehicleProduction")
    public TableDataInfo totalVehicleProduction(@RequestBody DisplayRequestParam time) {

        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleProduction(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 整车销量 指标5
     */
    @PreAuthorize("@ss.hasPermi('financial:display:totalVehicleSales')")
    @PostMapping("/totalVehicleSales")
    public TableDataInfo totalVehicleSales(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleSales(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 新产品销售收入 指标6
     */
    @PreAuthorize("@ss.hasPermi('financial:display:newProductSalesRevenue')")
    @PostMapping("/newProductSalesRevenue")
    public TableDataInfo newProductSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectNewProductSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 特色产品收入 指标7
     */
    @PreAuthorize("@ss.hasPermi('financial:display:specialtyProductRevenue')")
    @PostMapping("/specialtyProductRevenue")
    public TableDataInfo specialtyProductRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectSpecialtyProductRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 主营业务成本 指标8
     */
    @PreAuthorize("@ss.hasPermi('financial:display:COGS')")
    @PostMapping("/COGS")
    public TableDataInfo COGS(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.COGS(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 整机销售成本 指标9
     */
    @PreAuthorize("@ss.hasPermi('financial:display:totalSalesCost')")
    @PostMapping("/totalSalesCost")
    public TableDataInfo totalSalesCost(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesCost(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 净利润 指标10
     */
    @PreAuthorize("@ss.hasPermi('financial:display:netProfit')")
    @PostMapping("/netProfit")
    public TableDataInfo netProfit(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectNetProfit(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月管理费用 指标20
     */
    @PreAuthorize("@ss.hasPermi('financial:display:managementExpense')")
    @PostMapping("/managementExpense")
    public TableDataInfo managementExpense(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectManagementExpense(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月研发费用 指标21
     */
    @PreAuthorize("@ss.hasPermi('financial:display:rdExpense')")
    @PostMapping("/rdExpense")
    public TableDataInfo rdExpense(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectRdExpense(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月制造费用 指标22
     */
    @PreAuthorize("@ss.hasPermi('financial:display:manufacturingExpensesMonth')")
    @PostMapping("/manufacturingExpensesMonth")
    public TableDataInfo manufacturingExpensesMonth(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectManufacturingExpensesMonth(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月原材料存货额 指标26
     */
    @PreAuthorize("@ss.hasPermi('financial:display:monthlyRawMaterialInventory')")
    @PostMapping("/monthlyRawMaterialInventory")
    public TableDataInfo monthlyRawMaterialInventory(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyRawMaterialInventory(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月在制品存货额 指标27
     */
    @PreAuthorize("@ss.hasPermi('financial:display:monthlyWorkInProgressInventory')")
    @PostMapping("/monthlyWorkInProgressInventory")
    public TableDataInfo monthlyWorkInProgressInventory(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyWorkInProgressInventory(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月库存商品存货额 指标28
     */
    @PreAuthorize("@ss.hasPermi('financial:display:monthAmountInStock')")
    @PostMapping("/monthAmountInStock")
    public TableDataInfo monthAmountInStock(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthAmountInStock(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 存货增长率/销售增长率 指标30
     */
    @PreAuthorize("@ss.hasPermi('financial:display:growthRateInventoryAndSales')")
    @PostMapping("/growthRateInventoryAndSales")
    public TableDataInfo growthRateInventoryAndSales(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectGrowthRateInventoryAndSales(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 应收帐款周转率 指标32
     */
    @PreAuthorize("@ss.hasPermi('financial:display:turnoverRateReceivable')")
    @PostMapping("/turnoverRateReceivable")
    public TableDataInfo turnoverRateReceivable(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTurnoverRateReceivable(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 资金周转率 指标33
     */
    @PreAuthorize("@ss.hasPermi('financial:display:capitalTurnoverRate')")
    @PostMapping("/capitalTurnoverRate")
    public TableDataInfo capitalTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectCapitalTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 库存商品周转率 指标34
     */
    @PreAuthorize("@ss.hasPermi('financial:display:inventoryTurnoverRate')")
    @PostMapping("/inventoryTurnoverRate")
    public TableDataInfo inventoryTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInventoryTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 原材料周转率 指标35
     */
    @PreAuthorize("@ss.hasPermi('financial:display:rawMaterialTurnoverRate')")
    @PostMapping("/rawMaterialTurnoverRate")
    public TableDataInfo rawMaterialTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectRawMaterialTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 在制品周转率 指标36
     */
    @PreAuthorize("@ss.hasPermi('financial:display:inprogressTurnoverRate')")
    @PostMapping("/inprogressTurnoverRate")
    public TableDataInfo inprogressTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 一年以上暂估行项目 指标66
     */
    @PreAuthorize("@ss.hasPermi('financial:display:longEstimatedItems')")
    @PostMapping("/longEstimatedItems")
    public TableDataInfo longEstimatedItems(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectLongEstimatedItems(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当日在制品金额 指标70
     */
    @PreAuthorize("@ss.hasPermi('financial:display:inprogressDayrevenue')")
    @PostMapping("/inprogressDayrevenue")
    public TableDataInfo inprogressDayrevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressDayrevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 月度存货总金额 指标77
     */
    @PreAuthorize("@ss.hasPermi('financial:display:monthlyInventoryTotalAmount')")
    @PostMapping("/monthlyInventoryTotalAmount")
    public TableDataInfo monthlyInventoryTotalAmount(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyInventoryTotalAmount(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月经济增加值 指标78
     */
    @PreAuthorize("@ss.hasPermi('financial:display:addedValueMonthly')")
    @PostMapping("/addedValueMonthly")
    public TableDataInfo addedValueMonthly(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectAddedValueMonthly(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

}
