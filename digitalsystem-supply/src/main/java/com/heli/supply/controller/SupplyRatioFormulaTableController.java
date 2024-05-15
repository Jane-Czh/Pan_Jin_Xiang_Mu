package com.heli.supply.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.service.ISupplyPurchaseorderTableService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.supply.domain.SupplyRatioFormulaTable;
import com.heli.supply.service.ISupplyRatioFormulaTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 月供货比例统计Controller
 *
 * @author Teandron
 * @date 2024-04-24
 */
@RestController
@RequestMapping("/supply/ratio")
public class SupplyRatioFormulaTableController extends BaseController
{
    @Autowired
    private ISupplyRatioFormulaTableService supplyRatioFormulaTableService;

    @Autowired
    private ISupplyPurchaseorderTableService supplyPurchaseorderTableService;

    /**
     * 同步录入数据库
     */
    @PostMapping("/synchronization")
    public void Database_Synchronization(){
        System.out.println("------同步------");
        SupplyPurchaseorderTable supplyPurchaseorderTable = new SupplyPurchaseorderTable();
        List<SupplyPurchaseorderTable> list = supplyPurchaseorderTableService.selectSupplyPurchaseorderTableList(supplyPurchaseorderTable);
        supplyRatioFormulaTableService.Synchronization(list);
    }

    /**
     * 导入excel
     */
    @Log(title = "[供应]供货比例公式表上传", businessType = BusinessType.INSERT)
    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {
            supplyRatioFormulaTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }

    /**
     * 查询月供货比例统计列表
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        startPage();
        List<SupplyRatioFormulaTable> list = supplyRatioFormulaTableService.selectSupplyRatioFormulaTableList(supplyRatioFormulaTable);
        return getDataTable(list);
    }

    /**
     * 导出月供货比例统计列表
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:export')")
    @Log(title = "月供货比例统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        List<SupplyRatioFormulaTable> list = supplyRatioFormulaTableService.selectSupplyRatioFormulaTableList(supplyRatioFormulaTable);
        ExcelUtil<SupplyRatioFormulaTable> util = new ExcelUtil<SupplyRatioFormulaTable>(SupplyRatioFormulaTable.class);
        util.exportExcel(response, list, "月供货比例统计数据");
    }

    /**
     * 获取月供货比例统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:query')")
    @GetMapping(value = "/{srftId}")
    public AjaxResult getInfo(@PathVariable("srftId") Long srftId)
    {
        return success(supplyRatioFormulaTableService.selectSupplyRatioFormulaTableBySrftId(srftId));
    }

    /**
     * 新增月供货比例统计
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:add')")
    @Log(title = "月供货比例统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        return toAjax(supplyRatioFormulaTableService.insertSupplyRatioFormulaTable(supplyRatioFormulaTable));
    }

    /**
     * 修改月供货比例统计
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:edit')")
    @Log(title = "月供货比例统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyRatioFormulaTable supplyRatioFormulaTable)
    {
        return toAjax(supplyRatioFormulaTableService.updateSupplyRatioFormulaTable(supplyRatioFormulaTable));
    }

    /**
     * 删除月供货比例统计
     */
    @PreAuthorize("@ss.hasPermi('supply:ratio:remove')")
    @Log(title = "月供货比例统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{srftIds}")
    public AjaxResult remove(@PathVariable Long[] srftIds)
    {
        return toAjax(supplyRatioFormulaTableService.deleteSupplyRatioFormulaTableBySrftIds(srftIds));
    }
}
