package com.ruoyi.file.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.file.domain.DepartmentFilesTable;
import com.ruoyi.file.service.IDepartmentFilesTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * departmentfileController
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@RestController
@RequestMapping("/file/departmentfile")
public class DepartmentFilesTableController extends BaseController
{
    @Autowired
    private IDepartmentFilesTableService departmentFilesTableService;

    /**
     * 查询departmentfile列表
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:list')")
    @GetMapping("/list")
    public TableDataInfo list(DepartmentFilesTable departmentFilesTable)
    {
        startPage();
        List<DepartmentFilesTable> list = departmentFilesTableService.selectDepartmentFilesTableList(departmentFilesTable);
        return getDataTable(list);
    }

    /**
     * 导出departmentfile列表
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:export')")
    @Log(title = "departmentfile", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DepartmentFilesTable departmentFilesTable)
    {
        List<DepartmentFilesTable> list = departmentFilesTableService.selectDepartmentFilesTableList(departmentFilesTable);
        ExcelUtil<DepartmentFilesTable> util = new ExcelUtil<DepartmentFilesTable>(DepartmentFilesTable.class);
        util.exportExcel(response, list, "departmentfile数据");
    }

    /**
     * 获取departmentfile详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(departmentFilesTableService.selectDepartmentFilesTableById(id));
    }

    /**
     * 查询当前文件夹以及子文件夹下的所有文件
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:query')")
    @GetMapping(value = "quary/{id}")
    public TableDataInfo getAllfiles(@PathVariable("id") Long folderId,  @RequestParam("fileName") String fileName)
    {
        startPage();
        List<DepartmentFilesTable> list = departmentFilesTableService.getAllfilesByFolderId(folderId,fileName);
        return getDataTable(list);
    }

    /**
     * 新增departmentfile
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:add')")
    @Log(title = "departmentfile", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentFilesTable departmentFilesTable)
    {
        return toAjax(departmentFilesTableService.insertDepartmentFilesTable(departmentFilesTable));
    }

    /**
     * 修改departmentfile
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:edit')")
    @Log(title = "departmentfile", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentFilesTable departmentFilesTable)
    {
        return toAjax(departmentFilesTableService.updateDepartmentFilesTable(departmentFilesTable));
    }

    /**
     * 删除departmentfile
     */
    @PreAuthorize("@ss.hasPermi('file:departmentfile:remove')")
    @Log(title = "departmentfile", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(departmentFilesTableService.deleteDepartmentFilesTableByIds(ids));
    }
}
