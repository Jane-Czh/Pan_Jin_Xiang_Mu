package com.ruoyi.Enterprisemanagement.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementLaborContractLedgerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 劳动合同台账导入Controller
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
@RestController
@RequestMapping("/Enterprisemanagement/LbaorLedger")
public class EnterpriseManagementLaborContractLedgerController extends BaseController
{
    @Autowired
    private IEnterpriseManagementLaborContractLedgerService enterpriseManagementLaborContractLedgerService;

    /*表单导入功能*/
//    @Log(title = "[市场]劳动合同台账", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:import')")
    @PostMapping("/LRimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {

            enterpriseManagementLaborContractLedgerService.LRimportInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }
    /**
     * 查询劳动合同台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        startPage();
        List<EnterpriseManagementLaborContractLedger> list = enterpriseManagementLaborContractLedgerService.selectEnterpriseManagementLaborContractLedgerList(enterpriseManagementLaborContractLedger);
        return getDataTable(list);
    }

    /**
     * 导出劳动合同台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:export')")
    @Log(title = "劳动合同台账导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        List<EnterpriseManagementLaborContractLedger> list = enterpriseManagementLaborContractLedgerService.selectEnterpriseManagementLaborContractLedgerList(enterpriseManagementLaborContractLedger);
        ExcelUtil<EnterpriseManagementLaborContractLedger> util = new ExcelUtil<EnterpriseManagementLaborContractLedger>(EnterpriseManagementLaborContractLedger.class);
        util.exportExcel(response, list, "劳动合同台账导入数据");
    }

    /**
     * 获取劳动合同台账导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:query')")
    @GetMapping(value = "/{emlcId}")
    public AjaxResult getInfo(@PathVariable("emlcId") Long emlcId)
    {
        return success(enterpriseManagementLaborContractLedgerService.selectEnterpriseManagementLaborContractLedgerByEmlcId(emlcId));
    }

    /**
     * 新增劳动合同台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:add')")
    @Log(title = "劳动合同台账导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        return toAjax(enterpriseManagementLaborContractLedgerService.insertEnterpriseManagementLaborContractLedger(enterpriseManagementLaborContractLedger));
    }

    /**
     * 修改劳动合同台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:edit')")
    @Log(title = "劳动合同台账导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger)
    {
        return toAjax(enterpriseManagementLaborContractLedgerService.updateEnterpriseManagementLaborContractLedger(enterpriseManagementLaborContractLedger));
    }

    /**
     * 删除劳动合同台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:LbaorLedger:remove')")
    @Log(title = "劳动合同台账导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emlcIds}")
    public AjaxResult remove(@PathVariable Long[] emlcIds)
    {
        return toAjax(enterpriseManagementLaborContractLedgerService.deleteEnterpriseManagementLaborContractLedgerByEmlcIds(emlcIds));
    }
}
