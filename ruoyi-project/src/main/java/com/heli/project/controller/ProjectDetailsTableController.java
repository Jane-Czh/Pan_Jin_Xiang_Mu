package com.heli.project.controller;

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
import com.heli.project.domain.ProjectDetailsTable;
import com.heli.project.service.IProjectDetailsTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目详情Controller
 * 
 * @author Teandron
 * @date 2024-04-26
 */
@RestController
@RequestMapping("/project/details")
public class ProjectDetailsTableController extends BaseController
{
    @Autowired
    private IProjectDetailsTableService projectDetailsTableService;

    /**
     * 查询项目详情列表
     */
    @PreAuthorize("@ss.hasPermi('project:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectDetailsTable projectDetailsTable)
    {
        startPage();
        List<ProjectDetailsTable> list = projectDetailsTableService.selectProjectDetailsTableList(projectDetailsTable);
        return getDataTable(list);
    }

    /**
     * 导出项目详情列表
     */
    @PreAuthorize("@ss.hasPermi('project:details:export')")
    @Log(title = "项目详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectDetailsTable projectDetailsTable)
    {
        List<ProjectDetailsTable> list = projectDetailsTableService.selectProjectDetailsTableList(projectDetailsTable);
        ExcelUtil<ProjectDetailsTable> util = new ExcelUtil<ProjectDetailsTable>(ProjectDetailsTable.class);
        util.exportExcel(response, list, "项目详情数据");
    }

    /**
     * 获取项目详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:details:query')")
    @GetMapping(value = "/{detailsId}")
    public AjaxResult getInfo(@PathVariable("detailsId") Long detailsId)
    {
        return success(projectDetailsTableService.selectProjectDetailsTableByDetailsId(detailsId));
    }

    /**
     * 新增项目详情
     */
    @PreAuthorize("@ss.hasPermi('project:details:add')")
    @Log(title = "项目详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectDetailsTable projectDetailsTable)
    {
        return toAjax(projectDetailsTableService.insertProjectDetailsTable(projectDetailsTable));
    }

    /**
     * 修改项目详情
     */
    @PreAuthorize("@ss.hasPermi('project:details:edit')")
    @Log(title = "项目详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectDetailsTable projectDetailsTable)
    {
        return toAjax(projectDetailsTableService.updateProjectDetailsTable(projectDetailsTable));
    }

    /**
     * 删除项目详情
     */
    @PreAuthorize("@ss.hasPermi('project:details:remove')")
    @Log(title = "项目详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailsIds}")
    public AjaxResult remove(@PathVariable Long[] detailsIds)
    {
        return toAjax(projectDetailsTableService.deleteProjectDetailsTableByDetailsIds(detailsIds));
    }
}
