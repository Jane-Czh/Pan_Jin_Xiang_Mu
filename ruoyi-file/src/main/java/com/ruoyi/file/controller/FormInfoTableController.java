package com.ruoyi.file.controller;

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
import com.ruoyi.file.domain.FormInfoTable;
import com.ruoyi.file.service.IFormInfoTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 表单文件管理Controller
 * 
 * @author ruoyi
 * @date 2024-04-14
 */
@RestController
@RequestMapping("/file/formfilemanagement")
public class FormInfoTableController extends BaseController
{
    @Autowired
    private IFormInfoTableService formInfoTableService;

    /**
     * 查询表单文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(FormInfoTable formInfoTable)
    {
        startPage();
        List<FormInfoTable> list = formInfoTableService.selectFormInfoTableList(formInfoTable);
        return getDataTable(list);
    }

    /**
     * 导出表单文件管理列表
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:export')")
    @Log(title = "表单文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FormInfoTable formInfoTable)
    {
        List<FormInfoTable> list = formInfoTableService.selectFormInfoTableList(formInfoTable);
        ExcelUtil<FormInfoTable> util = new ExcelUtil<FormInfoTable>(FormInfoTable.class);
        util.exportExcel(response, list, "表单文件管理数据");
    }

    /**
     * 获取表单文件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:query')")
    @GetMapping(value = "/{formId}")
    public AjaxResult getInfo(@PathVariable("formId") Long formId)
    {
        return success(formInfoTableService.selectFormInfoTableByFormId(formId));
    }

    /**
     * 新增表单文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:add')")
    @Log(title = "表单文件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FormInfoTable formInfoTable)
    {
        return toAjax(formInfoTableService.insertFormInfoTable(formInfoTable));
    }

    /**
     * 修改表单文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:edit')")
    @Log(title = "表单文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FormInfoTable formInfoTable)
    {
        return toAjax(formInfoTableService.updateFormInfoTable(formInfoTable));
    }

    /**
     * 删除表单文件管理
     */
    @PreAuthorize("@ss.hasPermi('file:formfilemanagement:remove')")
    @Log(title = "表单文件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{formIds}")
    public AjaxResult remove(@PathVariable Long[] formIds)
    {
        return toAjax(formInfoTableService.deleteFormInfoTableByFormIds(formIds));
    }
}
