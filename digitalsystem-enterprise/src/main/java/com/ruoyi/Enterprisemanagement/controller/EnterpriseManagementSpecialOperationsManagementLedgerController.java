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
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementSpecialOperationsManagementLedgerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 特种作业台账导入Controller
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
@RestController
@RequestMapping("/Enterprisemanagement/Ledger")
public class EnterpriseManagementSpecialOperationsManagementLedgerController extends BaseController
{
    @Autowired
    private IEnterpriseManagementSpecialOperationsManagementLedgerService enterpriseManagementSpecialOperationsManagementLedgerService;

    /*表单导入功能*/
    @Log(title = "[市场]特种作业管理台账上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:import')")
    @PostMapping("/SOimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {

            enterpriseManagementSpecialOperationsManagementLedgerService.SOimportInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }
    /**
     * 查询特种作业台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        startPage();
        List<EnterpriseManagementSpecialOperationsManagementLedger> list = enterpriseManagementSpecialOperationsManagementLedgerService.selectEnterpriseManagementSpecialOperationsManagementLedgerList(enterpriseManagementSpecialOperationsManagementLedger);
        return getDataTable(list);
    }

    /**
     * 导出特种作业台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:export')")
    @Log(title = "特种作业台账导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        List<EnterpriseManagementSpecialOperationsManagementLedger> list = enterpriseManagementSpecialOperationsManagementLedgerService.selectEnterpriseManagementSpecialOperationsManagementLedgerList(enterpriseManagementSpecialOperationsManagementLedger);
        ExcelUtil<EnterpriseManagementSpecialOperationsManagementLedger> util = new ExcelUtil<EnterpriseManagementSpecialOperationsManagementLedger>(EnterpriseManagementSpecialOperationsManagementLedger.class);
        util.exportExcel(response, list, "特种作业台账导入数据");
    }

    /**
     * 获取特种作业台账导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:query')")
    @GetMapping(value = "/{emsiId}")
    public AjaxResult getInfo(@PathVariable("emsiId") Long emsiId)
    {
        return success(enterpriseManagementSpecialOperationsManagementLedgerService.selectEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(emsiId));
    }

    /**
     * 新增特种作业台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:add')")
    @Log(title = "特种作业台账导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        return toAjax(enterpriseManagementSpecialOperationsManagementLedgerService.insertEnterpriseManagementSpecialOperationsManagementLedger(enterpriseManagementSpecialOperationsManagementLedger));
    }

    /**
     * 修改特种作业台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:edit')")
    @Log(title = "特种作业台账导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger)
    {
        return toAjax(enterpriseManagementSpecialOperationsManagementLedgerService.updateEnterpriseManagementSpecialOperationsManagementLedger(enterpriseManagementSpecialOperationsManagementLedger));
    }

    /**
     * 删除特种作业台账导入
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:Ledger:remove')")
    @Log(title = "特种作业台账导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emsiIds}")
    public AjaxResult remove(@PathVariable Long[] emsiIds)
    {
        return toAjax(enterpriseManagementSpecialOperationsManagementLedgerService.deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiIds(emsiIds));
    }
}
