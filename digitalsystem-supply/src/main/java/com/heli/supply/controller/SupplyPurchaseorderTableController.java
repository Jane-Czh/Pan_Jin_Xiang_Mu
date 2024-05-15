package com.heli.supply.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.service.ISupplyPurchaseorderTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购订单导入Controller
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
@RestController
@RequestMapping("/supply/purchase")
public class SupplyPurchaseorderTableController extends BaseController
{
    @Autowired
    private ISupplyPurchaseorderTableService supplyPurchaseorderTableService;

    /**
     * 导入excel
     */
    @Log(title = "[供应]采购订单表表上传", businessType = BusinessType.INSERT)
    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {
            supplyPurchaseorderTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }

    /**
     * 查询采购订单导入列表
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        startPage();
        List<SupplyPurchaseorderTable> list = supplyPurchaseorderTableService.selectSupplyPurchaseorderTableList(supplyPurchaseorderTable);
        return getDataTable(list);
    }

    /**
     * 导出采购订单导入列表
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:export')")
    @Log(title = "采购订单导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        List<SupplyPurchaseorderTable> list = supplyPurchaseorderTableService.selectSupplyPurchaseorderTableList(supplyPurchaseorderTable);
        ExcelUtil<SupplyPurchaseorderTable> util = new ExcelUtil<SupplyPurchaseorderTable>(SupplyPurchaseorderTable.class);
        util.exportExcel(response, list, "采购订单导入数据");
    }

    /**
     * 获取采购订单导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:query')")
    @GetMapping(value = "/{spId}")
    public AjaxResult getInfo(@PathVariable("spId") Long spId)
    {
        return success(supplyPurchaseorderTableService.selectSupplyPurchaseorderTableBySpId(spId));
    }

    /**
     * 新增采购订单导入
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:add')")
    @Log(title = "采购订单导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        return toAjax(supplyPurchaseorderTableService.insertSupplyPurchaseorderTable(supplyPurchaseorderTable));
    }

    /**
     * 修改采购订单导入
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:edit')")
    @Log(title = "采购订单导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyPurchaseorderTable supplyPurchaseorderTable)
    {
        return toAjax(supplyPurchaseorderTableService.updateSupplyPurchaseorderTable(supplyPurchaseorderTable));
    }

    /**
     * 删除采购订单导入
     */
    @PreAuthorize("@ss.hasPermi('supply:purchase:remove')")
    @Log(title = "采购订单导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{spIds}")
    public AjaxResult remove(@PathVariable Long[] spIds)
    {
        return toAjax(supplyPurchaseorderTableService.deleteSupplyPurchaseorderTableBySpIds(spIds));
    }
}
