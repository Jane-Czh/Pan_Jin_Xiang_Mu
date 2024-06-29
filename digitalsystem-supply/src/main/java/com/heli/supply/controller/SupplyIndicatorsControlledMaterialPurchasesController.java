package com.heli.supply.controller;

import java.util.List;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
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
import com.heli.supply.domain.SupplyIndicatorsControlledMaterialPurchases;
import com.heli.supply.service.ISupplyIndicatorsControlledMaterialPurchasesService;
import com.ruoyi.common.core.page.TableDataInfo;


/**
 * @description: 供应-指标-集团管控物资占比Controller,包括展示
 * @author: hong
 * @date: 2024/4/29 22:47
 **/
@RestController
@RequestMapping("/supply")
public class SupplyIndicatorsControlledMaterialPurchasesController extends BaseController {
    @Autowired
    private ISupplyIndicatorsControlledMaterialPurchasesService supplyIndicatorsControlledMaterialPurchasesService;

    /**
     * 当月度设备维修总费用 指标23-展示
     */
    @PreAuthorize("@ss.hasPermi('supply:display:controlledPurchaseAmountRatio')")
    @PostMapping("/display/controlledPurchaseAmountRatio")
    public TableDataInfo selectControlledPurchaseAmountRatio(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = supplyIndicatorsControlledMaterialPurchasesService.selectControlledPurchaseAmountRatio(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 查询供应-指标-集团管控物资占比列表
     */
    @PreAuthorize("@ss.hasPermi('supply:data:list')")
    @GetMapping("/data/list")
    public TableDataInfo list(SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        startPage();
        List<SupplyIndicatorsControlledMaterialPurchases> list = supplyIndicatorsControlledMaterialPurchasesService.selectSupplyIndicatorsControlledMaterialPurchasesList(supplyIndicatorsControlledMaterialPurchases);
        return getDataTable(list);
    }

    /**
     * 获取供应-指标-集团管控物资占比详细信息
     */
    @PreAuthorize("@ss.hasPermi('supply:data:query')")
    @GetMapping(value = "/data/{scpId}")
    public AjaxResult getInfo(@PathVariable("scpId") Long scpId) {
        return success(supplyIndicatorsControlledMaterialPurchasesService.selectSupplyIndicatorsControlledMaterialPurchasesByScpId(scpId));
    }

    /**
     * 新增供应-指标-集团管控物资占比
     */
    @PreAuthorize("@ss.hasPermi('supply:data:add')")
    @Log(title = "供应-指标-集团管控物资占比", businessType = BusinessType.INSERT)
    @PostMapping("/data")
    public AjaxResult add(@RequestBody SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        if (supplyIndicatorsControlledMaterialPurchasesService.checkControlledPurchaseAmountRatioIsExisted(supplyIndicatorsControlledMaterialPurchases.getYearAndMonth())){
            return AjaxResult.error("当月数据已上传");
        }
        supplyIndicatorsControlledMaterialPurchases.setCreateBy(getUsername());
        return toAjax(supplyIndicatorsControlledMaterialPurchasesService.insertSupplyIndicatorsControlledMaterialPurchases(supplyIndicatorsControlledMaterialPurchases));
    }

    /**
     * 修改供应-指标-集团管控物资占比
     */
    @PreAuthorize("@ss.hasPermi('supply:data:edit')")
    @Log(title = "供应-指标-集团管控物资占比", businessType = BusinessType.UPDATE)
    @PutMapping("/data")
    public AjaxResult edit(@RequestBody SupplyIndicatorsControlledMaterialPurchases supplyIndicatorsControlledMaterialPurchases) {
        supplyIndicatorsControlledMaterialPurchases.setUpdateBy(getUsername());
        return toAjax(supplyIndicatorsControlledMaterialPurchasesService.updateSupplyIndicatorsControlledMaterialPurchases(supplyIndicatorsControlledMaterialPurchases));
    }

    /**
     * 删除供应-指标-集团管控物资占比
     */
    @PreAuthorize("@ss.hasPermi('supply:data:remove')")
    @Log(title = "供应-指标-集团管控物资占比", businessType = BusinessType.DELETE)
    @DeleteMapping("/data/{scpIds}")
    public AjaxResult remove(@PathVariable Long[] scpIds) {
        return toAjax(supplyIndicatorsControlledMaterialPurchasesService.deleteSupplyIndicatorsControlledMaterialPurchasesByScpIds(scpIds));
    }
}
