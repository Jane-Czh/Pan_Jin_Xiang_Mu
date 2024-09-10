package com.heli.enterprise.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.service.IEnterpriseManagementMonthlyDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [企业管理]指标月度数据Controller
 *
 * @author hong
 * @date 2024-05-09
 */
@RestController
@RequestMapping("/enterprise/data/monthly")
public class EnterpriseManagementMonthlyDataController extends BaseController {
    @Autowired
    private IEnterpriseManagementMonthlyDataService enterpriseManagementMonthlyDataService;


    @PostMapping("/test")
    public void test(Date yearAndMonth) {
//        enterpriseManagementMonthlyDataService.countEmployeesNumber(yearAndMonth);
//        enterpriseManagementMonthlyDataService.calculateEmployeesNumber(yearAndMonth);
        enterpriseManagementMonthlyDataService.calculateSalaryFillNumber(yearAndMonth);
    }

    /**
     * 导出[企业管理]指标月度数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:export')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        List<EnterpriseManagementMonthlyData> list = enterpriseManagementMonthlyDataService.selectEnterpriseManagementMonthlyDataList(enterpriseManagementMonthlyData);
        ExcelUtil<EnterpriseManagementMonthlyData> util = new ExcelUtil<EnterpriseManagementMonthlyData>(EnterpriseManagementMonthlyData.class);
        util.exportExcel(response, list, "企管月度数据");
    }


    /**
     * 查询[企业管理]指标月度数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        startPage();
        List<EnterpriseManagementMonthlyData> list = enterpriseManagementMonthlyDataService.selectEnterpriseManagementMonthlyDataList(enterpriseManagementMonthlyData);
        return getDataTable(list);
    }


    /**
     * 获取[企业管理]指标月度数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:query')")
    @GetMapping(value = "/{esId}")
    public AjaxResult getInfo(@PathVariable("esId") Long esId) {
        return success(enterpriseManagementMonthlyDataService.selectEnterpriseManagementMonthlyDataByEsId(esId));
    }

    /**
     * 新增[企业管理]指标月度数据
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:Data:add')")
//    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
//        return toAjax(enterpriseManagementMonthlyDataService.insertEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData));
//    }

    /**
     * 修改[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:edit')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementMonthlyData enterpriseManagementMonthlyData) {
        enterpriseManagementMonthlyData.setUpdateBy(getUsername());
        return toAjax(enterpriseManagementMonthlyDataService.updateEnterpriseManagementMonthlyData(enterpriseManagementMonthlyData));
    }

    /**
     * 删除[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:monthly:remove')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{esIds}")
    public AjaxResult remove(@PathVariable Long[] esIds) {
        return toAjax(enterpriseManagementMonthlyDataService.deleteEnterpriseManagementMonthlyDataByEsIds(esIds));
    }
}
