package com.heli.enterprise.controller;

import com.heli.enterprise.domain.EnterpriseManagementAnnualData;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementAnnualDataService;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise/data")
public class EnterpriseManagementDataController extends BaseController {

    @Autowired
    private IEnterpriseManagementAnnualDataService enterpriseManagementAnnualDataService;
    @Autowired
    private IEnterpriseManagementMonthlyDataService enterpriseManagementMonthlyDataService;

    /**
     * 新增[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:add')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
    @PostMapping("monthly")
    public AjaxResult add(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        if (!enterpriseManagementMonthlyDataService.checkEMMonthlyDataIsExisted(DateUtils.getLastMonth(enterpriseManagementMonthlyData.getYearAndMonth()))) {
            return AjaxResult.error("上月数据未填报，请有序填报");
        }
        if (enterpriseManagementMonthlyDataService.checkEMMonthlyDataIsExisted(enterpriseManagementMonthlyData.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }
        enterpriseManagementMonthlyData.setCreateBy(getUsername());
        return toAjax(enterpriseManagementMonthlyDataService.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData));
    }


    /**
     * 新增[企业管理]指标年度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:data:add')")
    @Log(title = "[企业管理]指标年度数据", businessType = BusinessType.INSERT)
    @PostMapping("annual")
    public AjaxResult add(EnterpriseManagementAnnualData enterpriseManagementAnnualData) {
        if (enterpriseManagementAnnualDataService.checkEMAnnualDataIsExisted(enterpriseManagementAnnualData.getNaturalYear())) {
            return AjaxResult.error("年度数据已填报");
        }
        enterpriseManagementAnnualData.setCreateBy(getUsername());
        return toAjax(enterpriseManagementAnnualDataService.insertEnterpriseManagementAnnualData(enterpriseManagementAnnualData));
    }


}
