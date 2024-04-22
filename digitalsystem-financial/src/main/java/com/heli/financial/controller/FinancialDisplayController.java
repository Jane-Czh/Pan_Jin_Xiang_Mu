package com.heli.financial.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.heli.financial.service.IFinancialDisplayService;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import com.ruoyi.common.core.domain.entity.DisplayRequestParam;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/mainRevenue")
    public TableDataInfo mainRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMainRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 整机销售收入 指标2
     */
    @PostMapping("/totalSalesRevenue")
    public TableDataInfo totalSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 集团外部销售收入 指标3
     */
    @PostMapping("/externalGroupSalesRevenue")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public TableDataInfo externalGroupSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectExternalGroupSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 整车产量 指标4
     */
    @PostMapping("/totalVehicleProduction")
    public TableDataInfo totalVehicleProduction(@RequestBody DisplayRequestParam time) {

        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleProduction(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 整车销量 指标5
     */
    @PostMapping("/totalVehicleSales")
    public TableDataInfo totalVehicleSales(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleSales(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 新产品销售收入 指标6
     */
    @PostMapping("/newProductSalesRevenue")
    public TableDataInfo newProductSalesRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectNewProductSalesRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 特色产品收入 指标7
     */
    @PostMapping("/specialtyProductRevenue")
    public TableDataInfo specialtyProductRevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectSpecialtyProductRevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 主营业务成本 指标8
     */
    @PostMapping("/COGS")
    public TableDataInfo COGS(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.COGS(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 整机销售成本 指标9
     */
    @PostMapping("/totalSalesCost")
    public TableDataInfo totalSalesCost(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesCost(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 净利润 指标10
     */
    @PostMapping("/netProfit")
    public TableDataInfo netProfit(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectNetProfit(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月管理费用 指标20
     */
    @PostMapping("/managementExpense")
    public TableDataInfo managementExpense(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectManagementExpense(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月研发费用 指标21
     */
    @PostMapping("/rdExpense")
    public TableDataInfo rdExpense(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectRdExpense(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月制造费用 指标22
     */
    @PostMapping("/manufacturingExpensesMonth")
    public TableDataInfo manufacturingExpensesMonth(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectManufacturingExpensesMonth(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月原材料存货额 指标26
     */
    @PostMapping("/monthlyRawMaterialInventory")
    public TableDataInfo monthlyRawMaterialInventory(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyRawMaterialInventory(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月在制品存货额 指标27
     */
    @PostMapping("/monthlyWorkInProgressInventory")
    public TableDataInfo monthlyWorkInProgressInventory(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyWorkInProgressInventory(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月库存商品存货额 指标28
     */
    @PostMapping("/monthAmountInStock")
    public TableDataInfo monthAmountInStock(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthAmountInStock(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 存货增长率/销售增长率 指标30
     */
    @PostMapping("/growthRateInventorySales")
    public TableDataInfo growthRateInventorySales(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectGrowthRateInventorySales(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 应收帐款周转率 指标32
     */
    @PostMapping("/turnoverRateReceivable")
    public TableDataInfo turnoverRateReceivable(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectTurnoverRateReceivable(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 资金周转率 指标33
     */
    @PostMapping("/capitalTurnoverRate")
    public TableDataInfo capitalTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectCapitalTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 库存商品周转率 指标34
     */
    @PostMapping("/inventoryTurnoverRate")
    public TableDataInfo inventoryTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInventoryTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 原材料周转率 指标35
     */
    @PostMapping("/rawMaterialTurnoverRate")
    public TableDataInfo rawMaterialTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectRawMaterialTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 在制品周转率 指标36
     */
    @PostMapping("/inprogressTurnoverRate")
    public TableDataInfo inprogressTurnoverRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressTurnoverRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 一年以上暂估行项目 指标66
     */
    @PostMapping("/longEstimatedItems")
    public TableDataInfo longEstimatedItems(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectLongEstimatedItems(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当日在制品金额 指标70
     */
    @PostMapping("/inprogressDayrevenue")
    public TableDataInfo inprogressDayrevenue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressDayrevenue(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 月度存货总金额 指标77
     */
    @PostMapping("/monthlyInventoryTotalAmount")
    public TableDataInfo monthlyInventoryTotalAmount(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyInventoryTotalAmount(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月经济增加值 指标78
     */
    @PostMapping("/addedValueMonthly")
    public TableDataInfo addedValueMonthly(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = IFinancialDisplayService.selectAddedValueMonthly(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }


//    /**
////     * 主营业务收入 指标1
////     */
//    @GetMapping("/mainRevenue/{year}")
//    public TableDataInfo mainRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectMainRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售收入 指标2
//     */
//    @GetMapping("/totalSalesRevenue/{year}")
//    public TableDataInfo totalSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 集团外部销售收入 指标3
//     */
//    @GetMapping("/externalGroupSalesRevenue/{year}")
//    public TableDataInfo externalGroupSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectExternalGroupSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车产量 指标4
//     */
//    @GetMapping("/totalVehicleProduction/{year}")
//    public TableDataInfo totalVehicleProduction(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleProduction(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车销量 指标5
//     */
//    @GetMapping("/totalVehicleSales/{year}")
//    public TableDataInfo totalVehicleSales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectTotalVehicleSales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 新产品销售收入 指标6
//     */
//    @GetMapping("/newProductSalesRevenue/{year}")
//    public TableDataInfo newProductSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectNewProductSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 特色产品收入 指标7
//     */
//    @GetMapping("/specialtyProductRevenue/{year}")
//    public TableDataInfo specialtyProductRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectSpecialtyProductRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 主营业务成本 指标8
//     */
//    @GetMapping("/COGS/{year}")
//    public TableDataInfo COGS(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.COGS(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售成本 指标9
//     */
//    @GetMapping("/totalSalesCost/{year}")
//    public TableDataInfo totalSalesCost(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectTotalSalesCost(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 净利润 指标10
//     */
//    @GetMapping("/netProfit/{year}")
//    public TableDataInfo netProfit(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectNetProfit(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月管理费用 指标20
//     */
//    @GetMapping("/managementExpense/{year}")
//    public TableDataInfo managementExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectManagementExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月研发费用 指标21
//     */
//    @GetMapping("/rdExpense/{year}")
//    public TableDataInfo rdExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectRdExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月制造费用 指标22
//     */
//    @GetMapping("/manufacturingExpensesMonth/{year}")
//    public TableDataInfo manufacturingExpensesMonth(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectManufacturingExpensesMonth(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月原材料存货额 指标26
//     */
//    @GetMapping("/monthlyRawMaterialInventory/{year}")
//    public TableDataInfo monthlyRawMaterialInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyRawMaterialInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月在制品存货额 指标27
//     */
//    @GetMapping("/monthlyWorkInProgressInventory/{year}")
//    public TableDataInfo monthlyWorkInProgressInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyWorkInProgressInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月库存商品存货额 指标28
//     */
//    @GetMapping("/monthAmountInStock/{year}")
//    public TableDataInfo monthAmountInStock(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectMonthAmountInStock(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 存货增长率/销售增长率 指标30
//     */
//    @GetMapping("/growthRateInventorySales/{year}")
//    public TableDataInfo growthRateInventorySales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectGrowthRateInventorySales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 应收帐款周转率 指标32
//     */
//    @GetMapping("/turnoverRateReceivable/{year}")
//    public TableDataInfo turnoverRateReceivable(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectTurnoverRateReceivable(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 资金周转率 指标33
//     */
//    @GetMapping("/capitalTurnoverRate/{year}")
//    public TableDataInfo capitalTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectCapitalTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 库存商品周转率 指标34
//     */
//    @GetMapping("/inventoryTurnoverRate/{year}")
//    public TableDataInfo inventoryTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectInventoryTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 原材料周转率 指标35
//     */
//    @GetMapping("/rawMaterialTurnoverRate/{year}")
//    public TableDataInfo rawMaterialTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectRawMaterialTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 在制品周转率 指标36
//     */
//    @GetMapping("/inprogressTurnoverRate/{year}")
//    public TableDataInfo inprogressTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 一年以上暂估行项目 指标66
//     */
//    @GetMapping("/longEstimatedItems/{year}")
//    public TableDataInfo longEstimatedItems(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectLongEstimatedItems(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当日在制品金额 指标70
//     */
//    @GetMapping("/inprogressDayrevenue/{year}")
//    public TableDataInfo inprogressDayrevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectInprogressDayrevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 月度存货总金额 指标77
//     */
//    @GetMapping("/monthlyInventoryTotalAmount/{year}")
//    public TableDataInfo monthlyInventoryTotalAmount(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectMonthlyInventoryTotalAmount(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月经济增加值 指标78
//     */
//    @GetMapping("/addedValueMonthly/{year}")
//    public TableDataInfo addedValueMonthly(@PathVariable("year") String year) {
//        List<DisplayEntity> list = IFinancialDisplayService.selectAddedValueMonthly(year);
//        return getDataTable(list);
//    }

}
