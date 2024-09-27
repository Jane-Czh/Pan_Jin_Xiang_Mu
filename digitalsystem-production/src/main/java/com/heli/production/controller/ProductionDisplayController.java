package com.heli.production.controller;

import com.heli.production.domain.ProductionFilling;
import com.heli.production.service.IProductionDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 生产指标展示
 * @author: hong
 * @date: 2024/4/22 10:26
 * @param:
 * @return:
 **/
@RestController
@RequestMapping("/production/display")
public class ProductionDisplayController extends BaseController {

    @Autowired
    private IProductionDisplayService productionDisplayService;

    /**
     * @description: 生产概览页面数据展示，获取最新一期数据
     * @author: hong
     * @date: 2024/9/27 9:12
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('production:display:newFillingData')")
    @GetMapping("/newFillingData")
    public AjaxResult newFillingData() {
        ProductionFilling fillingData = productionDisplayService.selectNewFillingData();
        return success(fillingData);
    }

    /**
     * @description: 生产概览页面数据展示，获取最新一期统计数据，用于上线数
     * @author: hong
     * @date: 2024/9/27 9:12
     * @version: 1.0Production_Launch_Completion_Daily_Number
     */
    @PreAuthorize("@ss.hasPermi('production:display:newLaunchData')")
    @GetMapping("/newLaunchData")
    public AjaxResult newLaunchData() {
        DisplayEntity data = productionDisplayService.selectNewLaunchData();
        return success(data);
    }

    /**
     * @description: 生产概览页面数据展示，获取最新一期统计数据，用于完工数
     * @author: hong
     * @date: 2024/9/27 9:12
     * @version: 1.0Production_Launch_Completion_Daily_Number
     */
    @PreAuthorize("@ss.hasPermi('production:display:newCompletionData')")
    @GetMapping("/newCompletionData")
    public AjaxResult newCompletionData() {
        DisplayEntity data = productionDisplayService.selectNewCompletionData();
        return success(data);
    }


    /**
     * 当月单台非BOM物料费用 指标24
     */
    @PreAuthorize("@ss.hasPermi('production:display:curNonBomMaterialCost')")
    @PostMapping("/curNonBomMaterialCost")
    public TableDataInfo curNonBomMaterialCost(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectCurNonBomMaterialCost(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月单台低值易耗费用 指标25
     */
    @PreAuthorize("@ss.hasPermi('production:display:curLowValueConsumables')")
    @PostMapping("/curLowValueConsumables")
    public TableDataInfo curLowValueConsumables(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectCurLowValueConsumables(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 在制物资年化周转天数 指标29
     */
    @PreAuthorize("@ss.hasPermi('production:display:inventoryTurnoverdays')")
    @PostMapping("/inventoryTurnoverdays")
    public TableDataInfo inventoryTurnoverdays(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectInventoryTurnoverdays(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 人均生产台数 指标37
     */
    @PreAuthorize("@ss.hasPermi('production:display:outputPercapitacounts')")
    @PostMapping("/outputPercapitacounts")
    public TableDataInfo outputPercapitacounts(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectOutputPercapitacounts(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 人均产值 指标38
     */
    @PreAuthorize("@ss.hasPermi('production:display:outputPercapitavalue')")
    @PostMapping("/outputPercapitavalue")
    public TableDataInfo outputPercapitavalue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectOutputPercapitavalue(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 上线及时率 指标41
     */
    @PreAuthorize("@ss.hasPermi('production:display:onlineOntimerate')")
    @PostMapping("/onlineOntimerate")
    public TableDataInfo onlineOntimerate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectOnlineOntimerate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 一线当月加班时长 指标48
     */
    @PreAuthorize("@ss.hasPermi('production:display:overtimeFrontlinemonth')")
    @PostMapping("/overtimeFrontlinemonth")
    public TableDataInfo overtimeFrontlinemonth(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectOvertimeFrontlinemonth(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 每日上线和完工数 指标49
     */
    @PreAuthorize("@ss.hasPermi('production:display:dailyLaunchAndCompletion')")
    @PostMapping("/dailyLaunchAndCompletion")
    public TableDataInfo dailyLaunchAndCompletion(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectDailyLaunchAndCompletion(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 每月上线和完工数 指标49
     */
    @PreAuthorize("@ss.hasPermi('production:display:monthlyLaunchAndCompletion')")
    @PostMapping("/monthlyLaunchAndCompletion")
    public TableDataInfo monthlyLaunchAndCompletion(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectMonthlyLaunchAndCompletion(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 每年上线和完工数 指标49
     */
    @PreAuthorize("@ss.hasPermi('production:display:yearlyLaunchAndCompletion')")
    @PostMapping("/annualLaunchAndCompletion")
    public TableDataInfo annualLaunchAndCompletion(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = productionDisplayService.selectAnnualLaunchAndCompletion(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }


}
