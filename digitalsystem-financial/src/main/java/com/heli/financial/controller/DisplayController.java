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
 * @Description: 数据展示Controller
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
    public TableDataInfo totalSalesRevenue() {
        List<DisplayEntity> list = displayService.selectTotalSalesRevenue();
        return getDataTable(list);
    }

    /**
     * 集团外部销售收入
     */
    @GetMapping("/externalGroupSalesRevenue")
    public TableDataInfo externalGroupSalesRevenue() {
        List<DisplayEntity> list = displayService.selectExternalGroupSalesRevenue();
        return getDataTable(list);
    }

    /**
     * 整车产量
     */
    @GetMapping("/totalVehicleProduction")
    public TableDataInfo totalVehicleProduction() {
        List<DisplayEntity> list = displayService.selectTotalVehicleProduction();
        return getDataTable(list);
    }

    /**
     * 整车销量
     */
    @GetMapping("/totalVehicleSales")
    public TableDataInfo totalVehicleSales() {
        List<DisplayEntity> list = displayService.selectTotalVehicleSales();
        return getDataTable(list);
    }

    /**
     * 新产品销售收入
     */
    @GetMapping("/newProductSalesRevenue")
    public TableDataInfo newProductSalesRevenue() {
        List<DisplayEntity> list = displayService.selectNewProductSalesRevenue();
        return getDataTable(list);
    }

    /**
     * 特色产品收入
     */
    @GetMapping("/specialtyProductRevenue")
    public TableDataInfo specialtyProductRevenue() {
        List<DisplayEntity> list = displayService.selectSpecialtyProductRevenue();
        return getDataTable(list);
    }

    /**
     * 整机销售成本
     */
    @GetMapping("/totalSalesCost")
    public TableDataInfo totalSalesCost() {
        List<DisplayEntity> list = displayService.selectTotalSalesCost();
        return getDataTable(list);
    }

    /**
     * 当月制造费用
     */
    @GetMapping("/manufacturingExpensesMonth")
    public TableDataInfo manufacturingExpensesMonth() {
        List<DisplayEntity> list = displayService.selectManufacturingExpensesMonth();
        return getDataTable(list);
    }

    /**
     * 当月库存商品存货额
     */
    @GetMapping("/reserveCarAmount")
    public TableDataInfo reserveCarAmount() {
        List<DisplayEntity> list = displayService.selectreserveCarAmount();
        return getDataTable(list);
    }

    /**
     * 资金周转率
     */
    @GetMapping("/capitalTurnoverRate")
    public TableDataInfo capitalTurnoverRate() {
        List<DisplayEntity> list = displayService.selectCapitalTurnoverRate();
        return getDataTable(list);
    }

    /**
     * 库存商品周转率
     */
    @GetMapping("/inventoryTurnoverRate")
    public TableDataInfo inventoryTurnoverRate() {
        List<DisplayEntity> list = displayService.selectInventoryTurnoverRate();
        return getDataTable(list);
    }

    /**
     * 原材料周转率
     */
    @GetMapping("/rawMaterialTurnoverRate")
    public TableDataInfo rawMaterialTurnoverRate() {
        List<DisplayEntity> list = displayService.selectRawMaterialTurnoverRate();
        return getDataTable(list);
    }

    /**
     * 在制品周转率
     */
    @GetMapping("/inprogressTurnoverRate")
    public TableDataInfo inprogressTurnoverRate() {
        List<DisplayEntity> list = displayService.selectInprogressTurnoverRate();
        return getDataTable(list);
    }

    /**
     * 一年以上暂估行项目
     */
    @GetMapping("/longEstimatedItems")
    public TableDataInfo longEstimatedItems() {
        List<DisplayEntity> list = displayService.selectLongEstimatedItems();
        return getDataTable(list);
    }

    /**
     * 当日在制品金额
     */
    @GetMapping("/inprogressDayrevenue")
    public TableDataInfo inprogressDayrevenue() {
        List<DisplayEntity> list = displayService.selectInprogressDayrevenue();
        return getDataTable(list);
    }

    /**
     * 当月经济增加值
     */
    @GetMapping("/addedValueMonthly")
    public TableDataInfo addedValueMonthly() {
        List<DisplayEntity> list = displayService.selectAddedValueMonthly();
        return getDataTable(list);
    }

}
