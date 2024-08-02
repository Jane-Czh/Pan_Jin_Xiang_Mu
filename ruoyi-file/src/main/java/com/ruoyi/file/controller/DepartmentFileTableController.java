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
import com.ruoyi.file.domain.DepartmentFileTable;
import com.ruoyi.file.service.IDepartmentFileTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 部门文件Controller
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
@RestController
@RequestMapping("/departmentFile/table")
public class DepartmentFileTableController extends BaseController
{
    @Autowired
    private IDepartmentFileTableService departmentFileTableService;

    /**
     * 查询部门文件列表
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:list')")
    @GetMapping("/list")
    public TableDataInfo list(DepartmentFileTable departmentFileTable)
    {
        startPage();
        List<DepartmentFileTable> list = departmentFileTableService.selectDepartmentFileTableList(departmentFileTable);
        return getDataTable(list);
    }

    /**
     * 导出部门文件列表
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:export')")
    @Log(title = "部门文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DepartmentFileTable departmentFileTable)
    {
        List<DepartmentFileTable> list = departmentFileTableService.selectDepartmentFileTableList(departmentFileTable);
        ExcelUtil<DepartmentFileTable> util = new ExcelUtil<DepartmentFileTable>(DepartmentFileTable.class);
        util.exportExcel(response, list, "部门文件数据");
    }

    /**
     * 获取部门文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(departmentFileTableService.selectDepartmentFileTableByFileId(fileId));
    }

    /**
     * 新增部门文件
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:add')")
    @Log(title = "部门文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentFileTable departmentFileTable)
    {
        return toAjax(departmentFileTableService.insertDepartmentFileTable(departmentFileTable));
    }

    /**
     * 修改部门文件
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:edit')")
    @Log(title = "部门文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentFileTable departmentFileTable)
    {
        return toAjax(departmentFileTableService.updateDepartmentFileTable(departmentFileTable));
    }

    /**
     * 删除部门文件
     */
    @PreAuthorize("@ss.hasPermi('departmentFile:table:remove')")
    @Log(title = "部门文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(departmentFileTableService.deleteDepartmentFileTableByFileIds(fileIds));
    }
}
