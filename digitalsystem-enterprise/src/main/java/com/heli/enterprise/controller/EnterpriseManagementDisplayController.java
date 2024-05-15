package com.heli.enterprise.controller;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enterprise/display")
public class EnterpriseManagementDisplayController extends BaseController {

    @Autowired
    private IEnterpriseManagementDisplayService enterpriseManagementDisplayService;

    @PostMapping("/employeesNumber")
    public TableDataInfo employeesNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/employeesAVGMonthlyNumber")
    public TableDataInfo employeesAVGMonthlyNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesAVGMonthlyNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/employeesAVGAnnualNumber")
    public TableDataInfo employeesAVGAnnualNumber(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectEmployeesAVGAnnualNumber(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/annualSalaryRatio")
    public TableDataInfo annualSalaryRatio(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectAnnualSalaryRatio(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/cumulativeAverageIncome")
    public TableDataInfo cumulativeAverageIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectCumulativeAverageIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/monthlyProductionAVGIncome")
    public TableDataInfo monthlyProductionAVGIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectMonthlyProductionAVGIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/monthlyFunctionalAVGIncome")
    public TableDataInfo monthlyFunctionalAVGIncome(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectMonthlyFunctionalAVGIncome(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/functionalDeptOvertimeCost")
    public TableDataInfo functionalDeptOvertimeCost(@RequestBody DisplayRequestParam time) {
        List<Map<Date, Object>> list = enterpriseManagementDisplayService.selectFunctionalDeptOvertimeCost(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PostMapping("/dailyClearingSettlement")
    public TableDataInfo selectDailyClearingSettlement(@RequestBody DisplayRequestParam time) {
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> enterpriseManagementIndicatorsDailyClearingSettlements = enterpriseManagementDisplayService.selectDailyClearingSettlement(time.getStartTime(), time.getEndTime());
        return getDataTable(enterpriseManagementIndicatorsDailyClearingSettlements);
    }

    @PostMapping("/management")
    public TableDataInfo selectManagement(@RequestBody DisplayRequestParam time) {
        List<EnterpriseManagementIndicatorsManagement> enterpriseManagementIndicatorsManagements = enterpriseManagementDisplayService.selectManagement(time.getStartTime(), time.getEndTime());
        return getDataTable(enterpriseManagementIndicatorsManagements);
    }

}

