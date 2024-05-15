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
import com.heli.enterprise.domain.EnterpriseManagementEmployeesData;
import com.heli.enterprise.service.IEnterpriseManagementEmployeesDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [企业管理]指标月度数据Controller
 *
 * @author hong
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/enterprise/monthlyData")
public class EnterpriseManagementEmployeesDataController extends BaseController {
    @Autowired
    private IEnterpriseManagementEmployeesDataService enterpriseManagementEmployeesDataService;

    /**
     * 查询[企业管理]指标月度数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        startPage();
        List<EnterpriseManagementEmployeesData> list = enterpriseManagementEmployeesDataService.selectEnterpriseManagementEmployeesDataList(enterpriseManagementEmployeesData);
        return getDataTable(list);
    }

    /**
     * 导出[企业管理]指标月度数据列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:export')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        List<EnterpriseManagementEmployeesData> list = enterpriseManagementEmployeesDataService.selectEnterpriseManagementEmployeesDataList(enterpriseManagementEmployeesData);
        ExcelUtil<EnterpriseManagementEmployeesData> util = new ExcelUtil<EnterpriseManagementEmployeesData>(EnterpriseManagementEmployeesData.class);
        util.exportExcel(response, list, "[企业管理]指标月度数据数据");
    }

    /**
     * 获取[企业管理]指标月度数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:query')")
    @GetMapping(value = "/{emId}")
    public AjaxResult getInfo(@PathVariable("emId") Long emId) {
        return success(enterpriseManagementEmployeesDataService.selectEnterpriseManagementEmployeesDataByEmId(emId));
    }

    /**
     * 新增[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:add')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        return toAjax(enterpriseManagementEmployeesDataService.insertEnterpriseManagementEmployeesData(enterpriseManagementEmployeesData));
    }

    /**
     * 修改[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:edit')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementEmployeesData enterpriseManagementEmployeesData) {
        return toAjax(enterpriseManagementEmployeesDataService.updateEnterpriseManagementEmployeesData(enterpriseManagementEmployeesData));
    }

    /**
     * 删除[企业管理]指标月度数据
     */
    @PreAuthorize("@ss.hasPermi('enterprise:Data:remove')")
    @Log(title = "[企业管理]指标月度数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{emIds}")
    public AjaxResult remove(@PathVariable Long[] emIds) {
        return toAjax(enterpriseManagementEmployeesDataService.deleteEnterpriseManagementEmployeesDataByEmIds(emIds));
    }
}
