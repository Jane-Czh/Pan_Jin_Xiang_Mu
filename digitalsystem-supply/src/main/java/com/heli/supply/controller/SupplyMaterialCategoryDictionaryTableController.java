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
import com.heli.supply.domain.SupplyMaterialCategoryDictionaryTable;
import com.heli.supply.service.ISupplyMaterialCategoryDictionaryTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料类别字典存储Controller
 * 
 * @author Teandron
 * @date 2024-07-29
 */
@RestController
@RequestMapping("/supply/categorydicTable")
public class SupplyMaterialCategoryDictionaryTableController extends BaseController
{
    @Autowired
    private ISupplyMaterialCategoryDictionaryTableService supplyMaterialCategoryDictionaryTableService;

    /**
     * 导入excel
     */
    @Log(title = "[供应]物料类别字典表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:import')")
    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {
            supplyMaterialCategoryDictionaryTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }


    /**
     * 查询物料类别字典存储列表
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        startPage();
        List<SupplyMaterialCategoryDictionaryTable> list = supplyMaterialCategoryDictionaryTableService.selectSupplyMaterialCategoryDictionaryTableList(supplyMaterialCategoryDictionaryTable);
        return getDataTable(list);
    }

    /**
     * 导出物料类别字典存储列表
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:export')")
    @Log(title = "物料类别字典存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        List<SupplyMaterialCategoryDictionaryTable> list = supplyMaterialCategoryDictionaryTableService.selectSupplyMaterialCategoryDictionaryTableList(supplyMaterialCategoryDictionaryTable);
        ExcelUtil<SupplyMaterialCategoryDictionaryTable> util = new ExcelUtil<SupplyMaterialCategoryDictionaryTable>(SupplyMaterialCategoryDictionaryTable.class);
        util.exportExcel(response, list, "物料类别字典存储数据");
    }

    /**
     * 获取物料类别字典存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:query')")
    @GetMapping(value = "/{smcdId}")
    public AjaxResult getInfo(@PathVariable("smcdId") Long smcdId)
    {
        return success(supplyMaterialCategoryDictionaryTableService.selectSupplyMaterialCategoryDictionaryTableBySmcdId(smcdId));
    }

    /**
     * 新增物料类别字典存储
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:add')")
    @Log(title = "物料类别字典存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        return toAjax(supplyMaterialCategoryDictionaryTableService.insertSupplyMaterialCategoryDictionaryTable(supplyMaterialCategoryDictionaryTable));
    }

    /**
     * 修改物料类别字典存储
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:edit')")
    @Log(title = "物料类别字典存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        return toAjax(supplyMaterialCategoryDictionaryTableService.updateSupplyMaterialCategoryDictionaryTable(supplyMaterialCategoryDictionaryTable));
    }

    /**
     * 删除物料类别字典存储
     */
    @PreAuthorize("@ss.hasPermi('supply:categorydicTable:remove')")
    @Log(title = "物料类别字典存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{smcdIds}")
    public AjaxResult remove(@PathVariable Long[] smcdIds)
    {
        return toAjax(supplyMaterialCategoryDictionaryTableService.deleteSupplyMaterialCategoryDictionaryTableBySmcdIds(smcdIds));
    }
}
