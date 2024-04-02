package com.heli.financial.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
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
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-资产负债Controller
 *
 * @author ruoyi
 * @date 2024-03-31
 */
@RestController
@RequestMapping("/financial/balance")
public class FinancialBalanceTableController extends BaseController {
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;


    /**
     * 查询财务-资产负债列表
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialBalanceTable financialBalanceTable) {
        startPage();
        List<FinancialBalanceTable> list = financialBalanceTableService.selectFinancialBalanceTableList(financialBalanceTable);
        return getDataTable(list);
    }

    /**
     * 导出财务-资产负债列表
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:export')")
    @Log(title = "财务-资产负债", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialBalanceTable financialBalanceTable) {
        List<FinancialBalanceTable> list = financialBalanceTableService.selectFinancialBalanceTableList(financialBalanceTable);
        ExcelUtil<FinancialBalanceTable> util = new ExcelUtil<FinancialBalanceTable>(FinancialBalanceTable.class);
        util.exportExcel(response, list, "财务-资产负债数据");
    }

    /**
     * 获取财务-资产负债详细信息
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:query')")
    @GetMapping(value = "/{fbId}")
    public AjaxResult getInfo(@PathVariable("fbId") Long fbId) {
        return success(financialBalanceTableService.selectFinancialBalanceTableByFbId(fbId));
    }

    /**
     * 新增财务-资产负债
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:add')")
    @Log(title = "财务-资产负债", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialBalanceTable financialBalanceTable) {
        return toAjax(financialBalanceTableService.insertFinancialBalanceTable(financialBalanceTable));
    }

    /**
     * 修改财务-资产负债
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:edit')")
    @Log(title = "财务-资产负债", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialBalanceTable financialBalanceTable) {
        return toAjax(financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable));
    }

    /**
     * 删除财务-资产负债
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:remove')")
    @Log(title = "财务-资产负债", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fbIds}")
    public AjaxResult remove(@PathVariable Long[] fbIds) {
        return toAjax(financialBalanceTableService.deleteFinancialBalanceTableByFbIds(fbIds));
    }
}
