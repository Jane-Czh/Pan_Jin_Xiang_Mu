package com.ruoyi.Enterprisemanagement.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementSpecialOperationsManagementLedgerService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementFunctionSpecialOperationWarningService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管特种作业证到期预警Controller
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/Enterprisemanagement/specialwarning")
public class EnterpriseManagementFunctionSpecialOperationWarningController extends BaseController
{
    @Autowired
    private IEnterpriseManagementFunctionSpecialOperationWarningService enterpriseManagementFunctionSpecialOperationWarningService;
    @Autowired
    private IEnterpriseManagementSpecialOperationsManagementLedgerService enterpriseManagementSpecialOperationsManagementLedgerService;
//    /*表单导入功能*/
//    @Log(title = "[市场]特种作业管理台账上传", businessType = BusinessType.INSERT)
//    @PostMapping("/SOimport")
//    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
//        System.out.println("------------import-------import------------");
//        System.out.println("excelFile" + excelFile);
//        try {
//
//            enterpriseManagementFunctionSpecialOperationWarningService.SOimportInterests(excelFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel上传失败");
//        }
//        return success();
//    }
    //同步功能
    @PostMapping("/synchronization")
    public void Database_Synchronization(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning1){
        System.out.println("-----------同步-------同步------------");
        List<EnterpriseManagementSpecialOperationsManagementLedger> list = enterpriseManagementSpecialOperationsManagementLedgerService.selectEnterpriseManagementSpecialOperationsManagementLedgerList1();
        enterpriseManagementFunctionSpecialOperationWarningService.Synchronization(list, enterpriseManagementFunctionSpecialOperationWarning1);
        System.out.println("-----------同步-------同步------------");
    }
    /**
     * 查询企管特种作业证到期预警列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        startPage();
        List<EnterpriseManagementFunctionSpecialOperationWarning> list = enterpriseManagementFunctionSpecialOperationWarningService.selectEnterpriseManagementFunctionSpecialOperationWarningList(enterpriseManagementFunctionSpecialOperationWarning);
        return getDataTable(list);
    }

    /**
     * 导出企管特种作业证到期预警列表
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:export')")
    @Log(title = "企管特种作业证到期预警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        List<EnterpriseManagementFunctionSpecialOperationWarning> list = enterpriseManagementFunctionSpecialOperationWarningService.selectEnterpriseManagementFunctionSpecialOperationWarningList(enterpriseManagementFunctionSpecialOperationWarning);
        ExcelUtil<EnterpriseManagementFunctionSpecialOperationWarning> util = new ExcelUtil<EnterpriseManagementFunctionSpecialOperationWarning>(EnterpriseManagementFunctionSpecialOperationWarning.class);
        util.exportExcel(response, list, "企管特种作业证到期预警数据");
    }

    /**
     * 获取企管特种作业证到期预警详细信息
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:query')")
    @GetMapping(value = "/{emsoId}")
    public AjaxResult getInfo(@PathVariable("emsoId") Long emsoId)
    {
        return success(enterpriseManagementFunctionSpecialOperationWarningService.selectEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(emsoId));
    }

    /**
     * 新增企管特种作业证到期预警
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:add')")
    @Log(title = "企管特种作业证到期预警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        return toAjax(enterpriseManagementFunctionSpecialOperationWarningService.insertEnterpriseManagementFunctionSpecialOperationWarning(enterpriseManagementFunctionSpecialOperationWarning));
    }

    /**
     * 修改企管特种作业证到期预警
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:edit')")
    @Log(title = "企管特种作业证到期预警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning)
    {
        return toAjax(enterpriseManagementFunctionSpecialOperationWarningService.updateEnterpriseManagementFunctionSpecialOperationWarning(enterpriseManagementFunctionSpecialOperationWarning));
    }

    /**
     * 删除企管特种作业证到期预警
     */
    @PreAuthorize("@ss.hasPermi('Enterprisemanagement:specialwarning:remove')")
    @Log(title = "企管特种作业证到期预警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{emsoIds}")
    public AjaxResult remove(@PathVariable Long[] emsoIds)
    {
        return toAjax(enterpriseManagementFunctionSpecialOperationWarningService.deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(emsoIds));
    }
}
