package com.ruoyi.Enterprisemanagement.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementPersonnelRosterService;
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
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionEmployeeStatistics;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionEmployeeStatisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工统计Controller
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
@RestController
@RequestMapping("/Enterprisemanagement/statistics")
public class EnterpriseManagementFunctionEmployeeStatisticsController extends BaseController
{
    @Autowired
    private IEnterpriseManagementFunctionEmployeeStatisticsService enterpriseManagementFunctionEmployeeStatisticsService;
    @Autowired
    private IEnterpriseManagementPersonnelRosterService enterpriseManagementPersonnelRosterService;

    //同步功能
    @PostMapping("/synchronization")
    public void Database_Synchronization(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics1){
        System.out.println("-----------同步-------同步------------");
        List<EnterpriseManagementPersonnelRoster> list = enterpriseManagementPersonnelRosterService.selectEnterpriseManagementPersonnelRosterList1();
        System.out.println("-----------同步++++++++++同步------------");
        enterpriseManagementFunctionEmployeeStatisticsService.Synchronization(list, enterpriseManagementFunctionEmployeeStatistics1);
        System.out.println("-----------同步-------同步------------");
    }
    /**
     * 查询员工统计列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        startPage();
        List<EnterpriseManagementFunctionEmployeeStatistics> list = enterpriseManagementFunctionEmployeeStatisticsService.selectEnterpriseManagementFunctionEmployeeStatisticsList(enterpriseManagementFunctionEmployeeStatistics);
        return getDataTable(list);
    }

    /**
     * 导出员工统计列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:export')")
//    @Log(title = "员工统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        List<EnterpriseManagementFunctionEmployeeStatistics> list = enterpriseManagementFunctionEmployeeStatisticsService.selectEnterpriseManagementFunctionEmployeeStatisticsList(enterpriseManagementFunctionEmployeeStatistics);
        ExcelUtil<EnterpriseManagementFunctionEmployeeStatistics> util = new ExcelUtil<EnterpriseManagementFunctionEmployeeStatistics>(EnterpriseManagementFunctionEmployeeStatistics.class);
        util.exportExcel(response, list, "员工统计数据");
    }

    /**
     * 获取员工统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:query')")
    @GetMapping(value = "/{emesId}")
    public AjaxResult getInfo(@PathVariable("emesId") Long emesId)
    {
        return success(enterpriseManagementFunctionEmployeeStatisticsService.selectEnterpriseManagementFunctionEmployeeStatisticsByEmesId(emesId));
    }

    /**
     * 新增员工统计
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:add')")
    @Log(title = "员工统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        return toAjax(enterpriseManagementFunctionEmployeeStatisticsService.insertEnterpriseManagementFunctionEmployeeStatistics(enterpriseManagementFunctionEmployeeStatistics));
    }

    /**
     * 修改员工统计
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:edit')")
    @Log(title = "员工统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics)
    {
        return toAjax(enterpriseManagementFunctionEmployeeStatisticsService.updateEnterpriseManagementFunctionEmployeeStatistics(enterpriseManagementFunctionEmployeeStatistics));
    }

    /**
     * 删除员工统计
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:statistics:remove')")
    @Log(title = "员工统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emesIds}")
    public AjaxResult remove(@PathVariable Long[] emesIds)
    {
        return toAjax(enterpriseManagementFunctionEmployeeStatisticsService.deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesIds(emesIds));
    }
}
