package com.ruoyi.Technology.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.Technology.service.FileConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Technology.domain.TechnologyFunctionMaterialRetention;
import com.ruoyi.Technology.service.ITechnologyFunctionMaterialRetentionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 申报材料统计Controller
 * 
 * @author ruoyi
 * @date 2024-05-28
 */
@RestController
@RequestMapping("/Technology/retention")
public class TechnologyFunctionMaterialRetentionController extends BaseController
{


    @Autowired
    private ITechnologyFunctionMaterialRetentionService technologyFunctionMaterialRetentionService;
    @Autowired
    private FileConversionService fileConversionService;

    /**
     * 执行Word到PDF转换
     */
//    @PostMapping("/convert")
//    public AjaxResult convertWordToPdf(@RequestParam("filePath") String filePath, @RequestParam("pdfFilePath") String pdfFilePath) {
//        try {
//            System.out.println("-----------"+"filePath"+"-----------");
//            fileConversionService.convertWordToPdf(filePath, pdfFilePath);
//            return AjaxResult.success("Word文件转换为PDF文件成功！");
//        } catch (IOException e) {
//            return AjaxResult.error("Word文件转换为PDF文件失败: " + e.getMessage());
//        }
//    }
    /**
     * 查询申报材料统计列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        startPage();
        List<TechnologyFunctionMaterialRetention> list = technologyFunctionMaterialRetentionService.selectTechnologyFunctionMaterialRetentionList(technologyFunctionMaterialRetention);
        return getDataTable(list);
    }
    /**
     * 查询历史版本文件列表
     */
    @PreAuthorize("@ss.hasPermi('file:filemanagement:list')")
    @GetMapping(value = "/history/{currentId}")
    public List<TechnologyFunctionMaterialRetention> getRegulationsHistory(@PathVariable Long currentId) {
//        startPage();
        System.out.println("123 currentId" + currentId);
        List<TechnologyFunctionMaterialRetention> list = technologyFunctionMaterialRetentionService.getRegulationsHistory(currentId);
        return list;
    }

    /**
     * 导出申报材料统计列表
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:export')")
    @Log(title = "申报材料统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        List<TechnologyFunctionMaterialRetention> list = technologyFunctionMaterialRetentionService.selectTechnologyFunctionMaterialRetentionList(technologyFunctionMaterialRetention);
        ExcelUtil<TechnologyFunctionMaterialRetention> util = new ExcelUtil<TechnologyFunctionMaterialRetention>(TechnologyFunctionMaterialRetention.class);
        util.exportExcel(response, list, "申报材料统计数据");
    }

    /**
     * 获取申报材料统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:query')")
    @GetMapping(value = "/{tfemId}")
    public AjaxResult getInfo(@PathVariable("tfemId") Long tfemId)
    {
        return success(technologyFunctionMaterialRetentionService.selectTechnologyFunctionMaterialRetentionByTfemId(tfemId));
    }

    /**
     * 新增申报材料统计
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:add')")
    @Log(title = "申报材料统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        return toAjax(technologyFunctionMaterialRetentionService.insertTechnologyFunctionMaterialRetention(technologyFunctionMaterialRetention));
    }

    /**
     * 修改申报材料统计
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:edit')")
    @Log(title = "申报材料统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        return toAjax(technologyFunctionMaterialRetentionService.updateTechnologyFunctionMaterialRetention(technologyFunctionMaterialRetention));
    }

    /**
     * 删除申报材料统计
     */
    @PreAuthorize("@ss.hasPermi('Technology:retention:remove')")
    @Log(title = "申报材料统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tfemIds}")
    public AjaxResult remove(@PathVariable Long[] tfemIds)
    {
        return toAjax(technologyFunctionMaterialRetentionService.deleteTechnologyFunctionMaterialRetentionByTfemIds(tfemIds));
    }


}
