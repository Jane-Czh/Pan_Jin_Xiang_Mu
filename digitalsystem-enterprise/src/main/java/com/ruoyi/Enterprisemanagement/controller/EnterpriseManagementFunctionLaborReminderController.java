package com.ruoyi.Enterprisemanagement.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementLaborContractLedgerService;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionLaborReminderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管劳动合同到期提醒Controller
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/Enterprisemanagement/reminder")
public class EnterpriseManagementFunctionLaborReminderController extends BaseController
{
    @Autowired
    private IEnterpriseManagementFunctionLaborReminderService enterpriseManagementFunctionLaborReminderService;
    @Autowired
    private IEnterpriseManagementLaborContractLedgerService enterpriseManagementLaborContractLedgerService;
//    /*表单导入功能*/
//    @Log(title = "[市场]劳动合同台账", businessType = BusinessType.INSERT)
//    @PostMapping("/LRimport")
//    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
//        System.out.println("------------import-------import------------");
//        System.out.println("excelFile" + excelFile);
//        try {
//
//            enterpriseManagementFunctionLaborReminderService.LRimportInterests(excelFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel上传失败");
//        }
//        return success();
//    }
    //同步功能
    @PostMapping("/synchronization")
    public void Database_Synchronization(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder1){
        System.out.println("-----------同步-------同步------------");
        List<EnterpriseManagementLaborContractLedger> list = enterpriseManagementLaborContractLedgerService.selectEnterpriseManagementLaborContractLedgerList1();
        enterpriseManagementFunctionLaborReminderService.Synchronization(list, enterpriseManagementFunctionLaborReminder1);
        System.out.println("-----------同步-------同步------------");
    }
    /**
     * 查询企管劳动合同到期提醒列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        startPage();
        List<EnterpriseManagementFunctionLaborReminder> list = enterpriseManagementFunctionLaborReminderService.selectEnterpriseManagementFunctionLaborReminderList(enterpriseManagementFunctionLaborReminder);
        return getDataTable(list);
    }

    /**
     * 导出企管劳动合同到期提醒列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:export')")
    @Log(title = "企管劳动合同到期提醒", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        List<EnterpriseManagementFunctionLaborReminder> list = enterpriseManagementFunctionLaborReminderService.selectEnterpriseManagementFunctionLaborReminderList(enterpriseManagementFunctionLaborReminder);
        ExcelUtil<EnterpriseManagementFunctionLaborReminder> util = new ExcelUtil<EnterpriseManagementFunctionLaborReminder>(EnterpriseManagementFunctionLaborReminder.class);
        util.exportExcel(response, list, "企管劳动合同到期提醒数据");
    }

    /**
     * 获取企管劳动合同到期提醒详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:query')")
    @GetMapping(value = "/{emlwId}")
    public AjaxResult getInfo(@PathVariable("emlwId") Long emlwId)
    {
        return success(enterpriseManagementFunctionLaborReminderService.selectEnterpriseManagementFunctionLaborReminderByEmlwId(emlwId));
    }

    /**
     * 新增企管劳动合同到期提醒
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:add')")
    @Log(title = "企管劳动合同到期提醒", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        return toAjax(enterpriseManagementFunctionLaborReminderService.insertEnterpriseManagementFunctionLaborReminder(enterpriseManagementFunctionLaborReminder));
    }

    /**
     * 修改企管劳动合同到期提醒
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:edit')")
    @Log(title = "企管劳动合同到期提醒", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder)
    {
        return toAjax(enterpriseManagementFunctionLaborReminderService.updateEnterpriseManagementFunctionLaborReminder(enterpriseManagementFunctionLaborReminder));
    }

    /**
     * 删除企管劳动合同到期提醒
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:reminder:remove')")
    @Log(title = "企管劳动合同到期提醒", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emlwIds}")
    public AjaxResult remove(@PathVariable Long[] emlwIds)
    {
        return toAjax(enterpriseManagementFunctionLaborReminderService.deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(emlwIds));
    }
}
