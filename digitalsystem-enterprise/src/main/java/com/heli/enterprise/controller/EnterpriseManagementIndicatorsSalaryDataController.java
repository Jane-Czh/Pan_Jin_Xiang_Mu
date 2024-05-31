package com.heli.enterprise.controller;

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
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsSalaryData;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsSalaryDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [企业管理]指标工资数据Controller
 *
 * @author hong
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/enterprise/salary")
public class EnterpriseManagementIndicatorsSalaryDataController extends BaseController {
    @Autowired
    private IEnterpriseManagementIndicatorsSalaryDataService enterpriseManagementIndicatorsSalaryDataService;

    /**
     * 查询[企业管理]指标工资数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        startPage();
        List<EnterpriseManagementIndicatorsSalaryData> list = enterpriseManagementIndicatorsSalaryDataService.selectEnterpriseManagementIndicatorsSalaryDataList(enterpriseManagementIndicatorsSalaryData);
        return getDataTable(list);
    }

    /**
     * 导出[企业管理]指标工资数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:export')")
    @Log(title = "[企业管理]指标工资数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        List<EnterpriseManagementIndicatorsSalaryData> list = enterpriseManagementIndicatorsSalaryDataService.selectEnterpriseManagementIndicatorsSalaryDataList(enterpriseManagementIndicatorsSalaryData);
        ExcelUtil<EnterpriseManagementIndicatorsSalaryData> util = new ExcelUtil<EnterpriseManagementIndicatorsSalaryData>(EnterpriseManagementIndicatorsSalaryData.class);
        util.exportExcel(response, list, "[企业管理]指标工资数据数据");
    }

    /**
     * 获取[企业管理]指标工资数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:query')")
    @GetMapping(value = "/{esId}")
    public AjaxResult getInfo(@PathVariable("esId") Long esId) {
        return success(enterpriseManagementIndicatorsSalaryDataService.selectEnterpriseManagementIndicatorsSalaryDataByEsId(esId));
    }

    /**
     * 新增[企业管理]指标工资数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:add')")
    @Log(title = "[企业管理]指标工资数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        return toAjax(enterpriseManagementIndicatorsSalaryDataService.insertEnterpriseManagementIndicatorsSalaryData(enterpriseManagementIndicatorsSalaryData));
    }

    /**
     * 修改[企业管理]指标工资数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:edit')")
    @Log(title = "[企业管理]指标工资数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData) {
        return toAjax(enterpriseManagementIndicatorsSalaryDataService.updateEnterpriseManagementIndicatorsSalaryData(enterpriseManagementIndicatorsSalaryData));
    }

    /**
     * 删除[企业管理]指标工资数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:salary:remove')")
    @Log(title = "[企业管理]指标工资数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{esIds}")
    public AjaxResult remove(@PathVariable Long[] esIds) {
        return toAjax(enterpriseManagementIndicatorsSalaryDataService.deleteEnterpriseManagementIndicatorsSalaryDataByEsIds(esIds));
    }
}
