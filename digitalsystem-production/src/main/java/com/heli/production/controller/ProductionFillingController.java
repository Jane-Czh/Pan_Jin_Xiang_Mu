package com.heli.production.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.production.domain.ProductionFilling;
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
import com.heli.production.service.IProductionFillingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * @description: [生产]手动填报指标功能Controller
 * @author: hong
 * @date: 2024/4/11 20:11
 **/
@RestController
@RequestMapping("/production/data/fill")
public class ProductionFillingController extends BaseController {
    @Autowired
    private IProductionFillingService productionFillingService;


    /**
     * @description: 获取已上传数据的月份,防止重复上传
     * @author: hong
     * @date: 2024/4/8 14:55
     **/
//    @PreAuthorize("@ss.hasPermi('production:fill:getAllMonth')")
//    @GetMapping("/getExistedYearAndMonth")
//    public TableDataInfo getAllYearAndMonth() {
//        List<Date> list = productionFillingService.selectExistedYearAndMonth();
//        return getDataTable(list);
//    }

    /**
     * 查询[生产]手动填报指标功能列表
     */
    @PreAuthorize("@ss.hasPermi('production:fill:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionFilling ProductionFilling) {
        startPage();
        List<ProductionFilling> list = productionFillingService.selectProductionList(ProductionFilling);
        return getDataTable(list);
    }

    /**
     * 获取[生产]手动填报指标功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('production:fill:query')")
    @GetMapping(value = "/{productionId}")
    public AjaxResult getInfo(@PathVariable("productionId") Long productionId) {
        return success(productionFillingService.selectProductionByProductionId(productionId));
    }

    /**
     * 新增[生产]手动填报指标功能
     */
    @PreAuthorize("@ss.hasPermi('production:fill:add')")
    @Log(title = "[生产]手动填报指标功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionFilling productionFilling) {
        if (productionFillingService.checkProductionFillingDataIsExisted(productionFilling.getYearAndMonth()))
            return AjaxResult.error("当月数据已填报");
        productionFilling.setCreateBy(getUsername());
        return toAjax(productionFillingService.insertProduction(productionFilling));
    }

    /**
     * 修改[生产]手动填报指标功能
     */
    @PreAuthorize("@ss.hasPermi('production:fill:edit')")
    @Log(title = "[生产]手动填报指标功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionFilling productionFilling) {
        productionFilling.setUpdateBy(getUsername());
        return toAjax(productionFillingService.updateProduction(productionFilling));
    }

    /**
     * 删除[生产]手动填报指标功能
     */
    @PreAuthorize("@ss.hasPermi('production:fill:remove')")
    @Log(title = "[生产]手动填报指标功能", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productionIds}")
    public AjaxResult remove(@PathVariable Long[] productionIds) {
        return toAjax(productionFillingService.deleteProductionByProductionIds(productionIds));
    }
}
