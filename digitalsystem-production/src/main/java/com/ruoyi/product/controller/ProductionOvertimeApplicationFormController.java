package com.ruoyi.product.controller;

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
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.service.IProductionOvertimeApplicationFormService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 加班申请Controller
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/product/Oaform")
public class ProductionOvertimeApplicationFormController extends BaseController
{
    @Autowired
    private IProductionOvertimeApplicationFormService productionOvertimeApplicationFormService;
    /**
     * 导入excel表格功能
     */
    @Log(title = "[生产]表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('product:Oaform:import')")
    @PostMapping("/Oaimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {
            productionOvertimeApplicationFormService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    /**
     * 查询加班申请列表
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        startPage();
        List<ProductionOvertimeApplicationForm> list = productionOvertimeApplicationFormService.selectProductionOvertimeApplicationFormList(productionOvertimeApplicationForm);
        return getDataTable(list);
    }

    /**
     * 导出加班申请列表
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:export')")
    @Log(title = "加班申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        List<ProductionOvertimeApplicationForm> list = productionOvertimeApplicationFormService.selectProductionOvertimeApplicationFormList(productionOvertimeApplicationForm);
        ExcelUtil<ProductionOvertimeApplicationForm> util = new ExcelUtil<ProductionOvertimeApplicationForm>(ProductionOvertimeApplicationForm.class);
        util.exportExcel(response, list, "加班申请数据");
    }

    /**
     * 获取加班申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:query')")
    @GetMapping(value = "/{poafId}")
    public AjaxResult getInfo(@PathVariable("poafId") Long poafId)
    {
        return success(productionOvertimeApplicationFormService.selectProductionOvertimeApplicationFormByPoafId(poafId));
    }

    /**
     * 新增加班申请
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:add')")
    @Log(title = "加班申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        return toAjax(productionOvertimeApplicationFormService.insertProductionOvertimeApplicationForm(productionOvertimeApplicationForm));
    }

    /**
     * 修改加班申请
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:edit')")
    @Log(title = "加班申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        return toAjax(productionOvertimeApplicationFormService.updateProductionOvertimeApplicationForm(productionOvertimeApplicationForm));
    }

    /**
     * 删除加班申请
     */
    @PreAuthorize("@ss.hasPermi('product:Oaform:remove')")
    @Log(title = "加班申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{poafIds}")
    public AjaxResult remove(@PathVariable Long[] poafIds)
    {
        return toAjax(productionOvertimeApplicationFormService.deleteProductionOvertimeApplicationFormByPoafIds(poafIds));
    }
}
