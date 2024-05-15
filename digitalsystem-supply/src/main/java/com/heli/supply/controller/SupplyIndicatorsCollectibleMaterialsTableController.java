package com.heli.supply.controller;

import java.util.List;

import com.heli.supply.domain.SupplyIndicatorsCollectibleMaterialsTable;
import com.heli.supply.service.ISupplyIndicatorsCollectibleMaterialsTableService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * @description: 供应科-指标-集采物料字典Controller
 * @author: hong
 * @date: 2024/4/29 22:49
 **/
@RestController
@RequestMapping("/supply/dictionary")
public class SupplyIndicatorsCollectibleMaterialsTableController extends BaseController {
    @Autowired
    private ISupplyIndicatorsCollectibleMaterialsTableService supplyIndicatorsCollectibleMaterialsTableService;

    /**
     * 查询供应科-指标-集采物料字典列表
     */
    @PreAuthorize("@ss.hasPermi('supply:indicators:list')")
    @GetMapping("/list")
    public TableDataInfo list(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        startPage();
        List<SupplyIndicatorsCollectibleMaterialsTable> list = supplyIndicatorsCollectibleMaterialsTableService.selectSupplyIndicatorsCollectibleMaterialsTableList(supplyIndicatorsCollectibleMaterialsTable);
        return getDataTable(list);
    }

    /**
     * 获取供应科-指标-集采物料字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('supply:indicators:query')")
    @GetMapping(value = "/{scmId}")
    public AjaxResult getInfo(@PathVariable("scmId") Long scmId) {
        return success(supplyIndicatorsCollectibleMaterialsTableService.selectSupplyIndicatorsCollectibleMaterialsTableByScmId(scmId));
    }

    /**
     * 新增供应科-指标-集采物料字典
     */
    @PreAuthorize("@ss.hasPermi('supply:indicators:add')")
    @Log(title = "供应科-指标-集采物料字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        supplyIndicatorsCollectibleMaterialsTable.setCreateBy(getUsername());
        return toAjax(supplyIndicatorsCollectibleMaterialsTableService.insertSupplyIndicatorsCollectibleMaterialsTable(supplyIndicatorsCollectibleMaterialsTable));
    }

    /**
     * 修改供应科-指标-集采物料字典
     */
    @PreAuthorize("@ss.hasPermi('supply:indicators:edit')")
    @Log(title = "供应科-指标-集采物料字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable) {
        supplyIndicatorsCollectibleMaterialsTable.setCreateBy(getUsername());
        return toAjax(supplyIndicatorsCollectibleMaterialsTableService.updateSupplyIndicatorsCollectibleMaterialsTable(supplyIndicatorsCollectibleMaterialsTable));
    }

    /**
     * 删除供应科-指标-集采物料字典
     */
    @PreAuthorize("@ss.hasPermi('supply:indicators:remove')")
    @Log(title = "供应科-指标-集采物料字典", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scmIds}")
    public AjaxResult remove(@PathVariable Long[] scmIds) {
        return toAjax(supplyIndicatorsCollectibleMaterialsTableService.deleteSupplyIndicatorsCollectibleMaterialsTableByScmIds(scmIds));
    }
}
