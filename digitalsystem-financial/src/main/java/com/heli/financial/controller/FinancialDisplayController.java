package com.heli.financial.controller;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.DisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


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
    private DisplayService displayService;


    /**
     * 主营业务收入 指标1
     */
    @GetMapping("/mainRevenue")
    public TableDataInfo mainRevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectMainRevenue(startTime,endTime);
        return getDataTable(list);
    }


    /**
     * 整机销售收入 指标2
     */
    @GetMapping("/totalSalesRevenue")
    public TableDataInfo totalSalesRevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectTotalSalesRevenue(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 集团外部销售收入 指标3
     */
    @GetMapping("/externalGroupSalesRevenue")
    public TableDataInfo externalGroupSalesRevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectExternalGroupSalesRevenue(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 整车产量 指标4
     */
    @GetMapping("/totalVehicleProduction")
    public TableDataInfo totalVehicleProduction(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectTotalVehicleProduction(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 整车销量 指标5
     */
    @GetMapping("/totalVehicleSales")
    public TableDataInfo totalVehicleSales(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectTotalVehicleSales(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 新产品销售收入 指标6
     */
    @GetMapping("/newProductSalesRevenue")
    public TableDataInfo newProductSalesRevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectNewProductSalesRevenue(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 特色产品收入 指标7
     */
    @GetMapping("/specialtyProductRevenue")
    public TableDataInfo specialtyProductRevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectSpecialtyProductRevenue(startTime,endTime);
        return getDataTable(list);
    }


    /**
     * 主营业务成本 指标8
     */
    @GetMapping("/COGS")
    public TableDataInfo COGS(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.COGS(startTime,endTime);
        return getDataTable(list);
    }


    /**
     * 整机销售成本 指标9
     */
    @GetMapping("/totalSalesCost")
    public TableDataInfo totalSalesCost(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectTotalSalesCost(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 净利润 指标10
     */
    @GetMapping("/netProfit")
    public TableDataInfo netProfit(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectNetProfit(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月管理费用 指标20
     */
    @GetMapping("/managementExpense")
    public TableDataInfo managementExpense(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectManagementExpense(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月研发费用 指标21
     */
    @GetMapping("/rdExpense")
    public TableDataInfo rdExpense(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectRdExpense(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月制造费用 指标22
     */
    @GetMapping("/manufacturingExpensesMonth")
    public TableDataInfo manufacturingExpensesMonth(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectManufacturingExpensesMonth(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月原材料存货额 指标26
     */
    @GetMapping("/monthlyRawMaterialInventory")
    public TableDataInfo monthlyRawMaterialInventory(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectMonthlyRawMaterialInventory(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月在制品存货额 指标27
     */
    @GetMapping("/monthlyWorkInProgressInventory")
    public TableDataInfo monthlyWorkInProgressInventory(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectMonthlyWorkInProgressInventory(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月库存商品存货额 指标28
     */
    @GetMapping("/monthAmountInStock")
    public TableDataInfo monthAmountInStock(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectMonthAmountInStock(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 存货增长率/销售增长率 指标30
     */
    @GetMapping("/growthRateInventorySales")
    public TableDataInfo growthRateInventorySales(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectGrowthRateInventorySales(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 应收帐款周转率 指标32
     */
    @GetMapping("/turnoverRateReceivable")
    public TableDataInfo turnoverRateReceivable(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectTurnoverRateReceivable(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 资金周转率 指标33
     */
    @GetMapping("/capitalTurnoverRate")
    public TableDataInfo capitalTurnoverRate(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectCapitalTurnoverRate(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 库存商品周转率 指标34
     */
    @GetMapping("/inventoryTurnoverRate")
    public TableDataInfo inventoryTurnoverRate(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectInventoryTurnoverRate(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 原材料周转率 指标35
     */
    @GetMapping("/rawMaterialTurnoverRate")
    public TableDataInfo rawMaterialTurnoverRate(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectRawMaterialTurnoverRate(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 在制品周转率 指标36
     */
    @GetMapping("/inprogressTurnoverRate")
    public TableDataInfo inprogressTurnoverRate(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectInprogressTurnoverRate(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 一年以上暂估行项目 指标66
     */
    @GetMapping("/longEstimatedItems")
    public TableDataInfo longEstimatedItems(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectLongEstimatedItems(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当日在制品金额 指标70
     */
    @GetMapping("/inprogressDayrevenue")
    public TableDataInfo inprogressDayrevenue(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectInprogressDayrevenue(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 月度存货总金额 指标77
     */
    @GetMapping("/monthlyInventoryTotalAmount")
    public TableDataInfo monthlyInventoryTotalAmount(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectMonthlyInventoryTotalAmount(startTime,endTime);
        return getDataTable(list);
    }

    /**
     * 当月经济增加值 指标78
     */
    @GetMapping("/addedValueMonthly")
    public TableDataInfo addedValueMonthly(Date startTime, Date endTime) {
        List<DisplayEntity> list = displayService.selectAddedValueMonthly(startTime,endTime);
        return getDataTable(list);
    }


//    /**
////     * 主营业务收入 指标1
////     */
//    @GetMapping("/mainRevenue/{year}")
//    public TableDataInfo mainRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectMainRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售收入 指标2
//     */
//    @GetMapping("/totalSalesRevenue/{year}")
//    public TableDataInfo totalSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectTotalSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 集团外部销售收入 指标3
//     */
//    @GetMapping("/externalGroupSalesRevenue/{year}")
//    public TableDataInfo externalGroupSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectExternalGroupSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车产量 指标4
//     */
//    @GetMapping("/totalVehicleProduction/{year}")
//    public TableDataInfo totalVehicleProduction(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectTotalVehicleProduction(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车销量 指标5
//     */
//    @GetMapping("/totalVehicleSales/{year}")
//    public TableDataInfo totalVehicleSales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectTotalVehicleSales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 新产品销售收入 指标6
//     */
//    @GetMapping("/newProductSalesRevenue/{year}")
//    public TableDataInfo newProductSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectNewProductSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 特色产品收入 指标7
//     */
//    @GetMapping("/specialtyProductRevenue/{year}")
//    public TableDataInfo specialtyProductRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectSpecialtyProductRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 主营业务成本 指标8
//     */
//    @GetMapping("/COGS/{year}")
//    public TableDataInfo COGS(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.COGS(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售成本 指标9
//     */
//    @GetMapping("/totalSalesCost/{year}")
//    public TableDataInfo totalSalesCost(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectTotalSalesCost(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 净利润 指标10
//     */
//    @GetMapping("/netProfit/{year}")
//    public TableDataInfo netProfit(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectNetProfit(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月管理费用 指标20
//     */
//    @GetMapping("/managementExpense/{year}")
//    public TableDataInfo managementExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectManagementExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月研发费用 指标21
//     */
//    @GetMapping("/rdExpense/{year}")
//    public TableDataInfo rdExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectRdExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月制造费用 指标22
//     */
//    @GetMapping("/manufacturingExpensesMonth/{year}")
//    public TableDataInfo manufacturingExpensesMonth(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectManufacturingExpensesMonth(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月原材料存货额 指标26
//     */
//    @GetMapping("/monthlyRawMaterialInventory/{year}")
//    public TableDataInfo monthlyRawMaterialInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectMonthlyRawMaterialInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月在制品存货额 指标27
//     */
//    @GetMapping("/monthlyWorkInProgressInventory/{year}")
//    public TableDataInfo monthlyWorkInProgressInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectMonthlyWorkInProgressInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月库存商品存货额 指标28
//     */
//    @GetMapping("/monthAmountInStock/{year}")
//    public TableDataInfo monthAmountInStock(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectMonthAmountInStock(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 存货增长率/销售增长率 指标30
//     */
//    @GetMapping("/growthRateInventorySales/{year}")
//    public TableDataInfo growthRateInventorySales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectGrowthRateInventorySales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 应收帐款周转率 指标32
//     */
//    @GetMapping("/turnoverRateReceivable/{year}")
//    public TableDataInfo turnoverRateReceivable(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectTurnoverRateReceivable(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 资金周转率 指标33
//     */
//    @GetMapping("/capitalTurnoverRate/{year}")
//    public TableDataInfo capitalTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectCapitalTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 库存商品周转率 指标34
//     */
//    @GetMapping("/inventoryTurnoverRate/{year}")
//    public TableDataInfo inventoryTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectInventoryTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 原材料周转率 指标35
//     */
//    @GetMapping("/rawMaterialTurnoverRate/{year}")
//    public TableDataInfo rawMaterialTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectRawMaterialTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 在制品周转率 指标36
//     */
//    @GetMapping("/inprogressTurnoverRate/{year}")
//    public TableDataInfo inprogressTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectInprogressTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 一年以上暂估行项目 指标66
//     */
//    @GetMapping("/longEstimatedItems/{year}")
//    public TableDataInfo longEstimatedItems(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectLongEstimatedItems(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当日在制品金额 指标70
//     */
//    @GetMapping("/inprogressDayrevenue/{year}")
//    public TableDataInfo inprogressDayrevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectInprogressDayrevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 月度存货总金额 指标77
//     */
//    @GetMapping("/monthlyInventoryTotalAmount/{year}")
//    public TableDataInfo monthlyInventoryTotalAmount(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectMonthlyInventoryTotalAmount(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月经济增加值 指标78
//     */
//    @GetMapping("/addedValueMonthly/{year}")
//    public TableDataInfo addedValueMonthly(@PathVariable("year") String year) {
//        List<DisplayEntity> list = displayService.selectAddedValueMonthly(year);
//        return getDataTable(list);
//    }

}
