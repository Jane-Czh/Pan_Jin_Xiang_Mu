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
import com.heli.project.domain.ProjectHistoryInfoTable;
import com.heli.project.service.IProjectHistoryInfoTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 历史项目信息管理Controller
 * 
 * @author Teandron
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/project/history")
public class ProjectHistoryInfoTableController extends BaseController
{
    @Autowired
    private IProjectHistoryInfoTableService projectHistoryInfoTableService;

    /**
     * 查询历史项目信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        startPage();
        List<ProjectHistoryInfoTable> list = projectHistoryInfoTableService.selectProjectHistoryInfoTableList(projectHistoryInfoTable);
        return getDataTable(list);
    }

    /**
     * 导出历史项目信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('project:history:export')")
    @Log(title = "历史项目信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        List<ProjectHistoryInfoTable> list = projectHistoryInfoTableService.selectProjectHistoryInfoTableList(projectHistoryInfoTable);
        ExcelUtil<ProjectHistoryInfoTable> util = new ExcelUtil<ProjectHistoryInfoTable>(ProjectHistoryInfoTable.class);
        util.exportExcel(response, list, "历史项目信息管理数据");
    }

    /**
     * 获取历史项目信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:history:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(projectHistoryInfoTableService.selectProjectHistoryInfoTableByProjectId(projectId));
    }

    /**
     * 新增历史项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('project:history:add')")
    @Log(title = "历史项目信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        return toAjax(projectHistoryInfoTableService.insertProjectHistoryInfoTable(projectHistoryInfoTable));
    }

    /**
     * 修改历史项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('project:history:edit')")
    @Log(title = "历史项目信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectHistoryInfoTable projectHistoryInfoTable)
    {
        return toAjax(projectHistoryInfoTableService.updateProjectHistoryInfoTable(projectHistoryInfoTable));
    }

    /**
     * 删除历史项目信息管理
     */
    @PreAuthorize("@ss.hasPermi('project:history:remove')")
    @Log(title = "历史项目信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectHistoryInfoTableService.deleteProjectHistoryInfoTableByProjectIds(projectIds));
    }
}
