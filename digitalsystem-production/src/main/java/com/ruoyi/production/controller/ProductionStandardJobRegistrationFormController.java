package com.ruoyi.production.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.production.domain.ProductionStandardJobRegistrationForm;
import com.ruoyi.production.service.IProductionStandardJobRegistrationFormService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 记录标准作业信息Controller
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/production/register")
public class ProductionStandardJobRegistrationFormController extends BaseController
{
    @Autowired
    private IProductionStandardJobRegistrationFormService productionStandardJobRegistrationFormService;

    /**
     * 查询记录标准作业信息列表
     */
    @PreAuthorize("@ss.hasPermi('production:register:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        startPage();
        List<ProductionStandardJobRegistrationForm> list = productionStandardJobRegistrationFormService.selectProductionStandardJobRegistrationFormList(productionStandardJobRegistrationForm);
        return getDataTable(list);
    }

    /**
     * 导出记录标准作业信息列表
     */
    @PreAuthorize("@ss.hasPermi('production:register:export')")
    @Log(title = "记录标准作业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        List<ProductionStandardJobRegistrationForm> list = productionStandardJobRegistrationFormService.selectProductionStandardJobRegistrationFormList(productionStandardJobRegistrationForm);
        ExcelUtil<ProductionStandardJobRegistrationForm> util = new ExcelUtil<ProductionStandardJobRegistrationForm>(ProductionStandardJobRegistrationForm.class);
        util.exportExcel(response, list, "记录标准作业信息数据");
    }

    /**
     * 获取记录标准作业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('production:register:query')")
    @GetMapping(value = "/{psjrfId}")
    public AjaxResult getInfo(@PathVariable("psjrfId") Long psjrfId)
    {
        return success(productionStandardJobRegistrationFormService.selectProductionStandardJobRegistrationFormByPsjrfId(psjrfId));
    }

    /**
     * 新增记录标准作业信息
     */
    @PreAuthorize("@ss.hasPermi('production:register:add')")
    @Log(title = "记录标准作业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        return toAjax(productionStandardJobRegistrationFormService.insertProductionStandardJobRegistrationForm(productionStandardJobRegistrationForm));
    }

    /**
     * 修改记录标准作业信息
     */
    @PreAuthorize("@ss.hasPermi('production:register:edit')")
    @Log(title = "记录标准作业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        return toAjax(productionStandardJobRegistrationFormService.updateProductionStandardJobRegistrationForm(productionStandardJobRegistrationForm));
    }

    /**
     * 删除记录标准作业信息
     */
    @PreAuthorize("@ss.hasPermi('production:register:remove')")
    @Log(title = "记录标准作业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{psjrfIds}")
    public AjaxResult remove(@PathVariable Long[] psjrfIds)
    {
        return toAjax(productionStandardJobRegistrationFormService.deleteProductionStandardJobRegistrationFormByPsjrfIds(psjrfIds));
    }
}
