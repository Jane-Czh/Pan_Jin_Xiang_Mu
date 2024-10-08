package com.heli.enterprise.controller;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enterprise/display")
public class EnterpriseManagementDisplayController extends BaseController {

    @Autowired
    private IEnterpriseManagementDisplayService enterpriseManagementDisplayService;


    /**
     * @description: 概览页面-工资数据，最新一期
     * @author: hong
     * @date: 2024/9/27 13:48
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('enterprise:display:salaryNewData')")
    @GetMapping("/salaryNewData")
    public AjaxResult salaryNewData() {
        EnterpriseManagementMonthlyData data = enterpriseManagementDisplayService.selectSalaryNewData();
        return AjaxResult.success(data);
    }

    /**
     * @description: 概览页面-其他数据数据，最新一期
     * @author: hong
     * @date: 2024/9/27 13:48
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('enterprise:display:monthlyNewData')")
    @GetMapping("/monthlyNewData")
    public AjaxResult monthlyNewData() {
        EnterpriseManagementMonthlyData data = enterpriseManagementDisplayService.selectMonthlyNewData();
        return AjaxResult.success(data);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:employeesNumber')")
    @PostMapping("/employeesNumber")
    public TableDataInfo employeesNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

//    @PreAuthorize("@ss.hasPermi('enterprise:display:employeesAVGMonthlyNumber')")
    @PostMapping("/employeesAVGMonthlyNumber")
    public TableDataInfo employeesAVGMonthlyNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesAVGMonthlyNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:employeesAVGAnnualNumber')")
    @PostMapping("/employeesAVGAnnualNumber")
    public TableDataInfo employeesAVGAnnualNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesAVGAnnualNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:annualSalaryRatio')")
    @PostMapping("/annualSalaryRatio")
    public TableDataInfo annualSalaryRatio(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectAnnualSalaryRatio(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:cumulativeAverageIncome')")
    @PostMapping("/cumulativeAverageIncome")
    public TableDataInfo cumulativeAverageIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectCumulativeAverageIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:monthlyProductionAVGIncome')")
    @PostMapping("/monthlyProductionAVGIncome")
    public TableDataInfo monthlyProductionAVGIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectMonthlyProductionAVGIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:monthlyFunctionalAVGIncome')")
    @PostMapping("/monthlyFunctionalAVGIncome")
    public TableDataInfo monthlyFunctionalAVGIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectMonthlyFunctionalAVGIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:functionalDeptOvertimeCost')")
    @PostMapping("/functionalDeptOvertimeCost")
    public TableDataInfo functionalDeptOvertimeCost(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectFunctionalDeptOvertimeCost(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:dailyClearingSettlement')")
    @PostMapping("/dailyClearingSettlement")
    public TableDataInfo selectDailyClearingSettlement(@RequestBody DisplayRequestParam time) {
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> enterpriseManagementIndicatorsDailyClearingSettlements = enterpriseManagementDisplayService.selectDailyClearingSettlement(time.getStartTime(), time.getEndTime());
        return getDataTable(enterpriseManagementIndicatorsDailyClearingSettlements);
    }

    @PreAuthorize("@ss.hasPermi('enterprise:display:management')")
    @PostMapping("/management")
    public TableDataInfo selectManagement(@RequestBody DisplayRequestParam time) {
        List<EnterpriseManagementIndicatorsManagement> enterpriseManagementIndicatorsManagements = enterpriseManagementDisplayService.selectManagement(time.getStartTime(), time.getEndTime());
        return getDataTable(enterpriseManagementIndicatorsManagements);
    }

}

