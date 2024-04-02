package com.heli.financial.controller;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.service.DisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01  12:19
 * @Description: TODO
 */
@RestController
@RequestMapping("/financial/display")
public class DisplayController extends BaseController {
    @Autowired
    private DisplayService displayService;

    /**
     * 整机销售收入
     */
    @GetMapping("/totalSalesRevenue")
    public TableDataInfo totalSalesRevenue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectTotalSalesRevenue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 集团外部销售收入
     */
    @GetMapping("/externalGroupSalesRevenue")
    public TableDataInfo externalGroupSalesRevenue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectExternalGroupSalesRevenue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 整车产量
     */
    @GetMapping("/totalVehicleProduction")
    public TableDataInfo totalVehicleProduction(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectTotalVehicleProduction(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 整车销量
     */
    @GetMapping("/totalVehicleSales")
    public TableDataInfo totalVehicleSales(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectTotalVehicleSales(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 新产品销售收入
     */
    @GetMapping("/newProductSalesRevenue")
    public TableDataInfo newProductSalesRevenue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectNewProductSalesRevenue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 特色产品收入
     */
    @GetMapping("/specialtyProductRevenue")
    public TableDataInfo specialtyProductRevenue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectSpecialtyProductRevenue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 整机销售成本
     */
    @GetMapping("/totalSalesCost")
    public TableDataInfo totalSalesCost(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectTotalSalesCost(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 当月制造费用
     */
    @GetMapping("/manufacturingExpensesMonth")
    public TableDataInfo manufacturingExpensesMonth(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectManufacturingExpensesMonth(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 当月库存商品存货额
     */
    @GetMapping("/monthlyInventoryStockValue")
    public TableDataInfo monthlyInventoryStockValue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectMonthlyInventoryStockValue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 资金周转率
     */
    @GetMapping("/capitalTurnoverRate")
    public TableDataInfo capitalTurnoverRate(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectCapitalTurnoverRate(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 库存商品周转率
     */
    @GetMapping("/inventoryTurnoverRate")
    public TableDataInfo inventoryTurnoverRate(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectInventoryTurnoverRate(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 原材料周转率
     */
    @GetMapping("/rawMaterialTurnoverRate")
    public TableDataInfo rawMaterialTurnoverRate(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectRawMaterialTurnoverRate(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 在制品周转率
     */
    @GetMapping("/inprogressTurnoverRate")
    public TableDataInfo inprogressTurnoverRate(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectInprogressTurnoverRate(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 一年以上暂估行项目
     */
    @GetMapping("/longEstimatedItems")
    public TableDataInfo longEstimatedItems(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectLongEstimatedItems(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 当日在制品金额
     */
    @GetMapping("/inprogressDayrevenue")
    public TableDataInfo inprogressDayrevenue(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectInprogressDayrevenue(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 当月经济增加值
     */
    @GetMapping("/addedValueMonthly")
    public TableDataInfo addedValueMonthly(DisplayEntity displayEntity) {
        List<DisplayEntity> list = displayService.selectAddedValueMonthly(displayEntity);
        System.out.println(list);
        return getDataTable(list);
    }

}
