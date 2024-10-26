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
import com.ruoyi.file.domain.DepartmentFoldersTable;
import com.ruoyi.file.service.IDepartmentFoldersTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * foldersController
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/file/folders")
public class DepartmentFoldersTableController extends BaseController
{
    @Autowired
    private IDepartmentFoldersTableService departmentFoldersTableService;

    /**
     * 查询folders列表
     */
    @PreAuthorize("@ss.hasPermi('file:folders:list')")
    @GetMapping("/list")
    public TableDataInfo list(DepartmentFoldersTable departmentFoldersTable)
    {
        List<DepartmentFoldersTable> list = departmentFoldersTableService.selectDepartmentFoldersTableList(departmentFoldersTable);
        return getDataTable(list);
    }

    /**
     * 导出folders列表
     */
    @PreAuthorize("@ss.hasPermi('file:folders:export')")
    @Log(title = "folders", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DepartmentFoldersTable departmentFoldersTable)
    {
        List<DepartmentFoldersTable> list = departmentFoldersTableService.selectDepartmentFoldersTableList(departmentFoldersTable);
        ExcelUtil<DepartmentFoldersTable> util = new ExcelUtil<DepartmentFoldersTable>(DepartmentFoldersTable.class);
        util.exportExcel(response, list, "folders数据");
    }

    /**
     * 获取folders详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:folders:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(departmentFoldersTableService.selectDepartmentFoldersTableById(id));
    }

    /**
     * 新增folders
     */
    @PreAuthorize("@ss.hasPermi('file:folders:add')")
    @Log(title = "folders", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentFoldersTable departmentFoldersTable)
    {
        return toAjax(departmentFoldersTableService.insertDepartmentFoldersTable(departmentFoldersTable));
    }

    /**
     * 修改folders
     */
    @PreAuthorize("@ss.hasPermi('file:folders:edit')")
    @Log(title = "folders", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentFoldersTable departmentFoldersTable)
    {
        return toAjax(departmentFoldersTableService.updateDepartmentFoldersTable(departmentFoldersTable));
    }

    /**
     * 删除folders
     */
    @PreAuthorize("@ss.hasPermi('file:folders:remove')")
    @Log(title = "folders", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(departmentFoldersTableService.deleteDepartmentFoldersTableByIds(ids));
    }
}
