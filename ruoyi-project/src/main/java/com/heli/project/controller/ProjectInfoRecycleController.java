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
import com.heli.project.domain.ProjectInfoRecycle;
import com.heli.project.service.IProjectInfoRecycleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目信息回收Controller
 * 
 * @author Teandron
 * @date 2024-08-06
 */
@RestController
@RequestMapping("/project/recycle")
public class ProjectInfoRecycleController extends BaseController
{
    @Autowired
    private IProjectInfoRecycleService projectInfoRecycleService;

    /**
     * 查询项目信息回收列表
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectInfoRecycle projectInfoRecycle)
    {
        startPage();
        List<ProjectInfoRecycle> list = projectInfoRecycleService.selectProjectInfoRecycleList(projectInfoRecycle);
        return getDataTable(list);
    }

    /**
     * 导出项目信息回收列表
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:export')")
    @Log(title = "项目信息回收", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectInfoRecycle projectInfoRecycle)
    {
        List<ProjectInfoRecycle> list = projectInfoRecycleService.selectProjectInfoRecycleList(projectInfoRecycle);
        ExcelUtil<ProjectInfoRecycle> util = new ExcelUtil<ProjectInfoRecycle>(ProjectInfoRecycle.class);
        util.exportExcel(response, list, "项目信息回收数据");
    }

    /**
     * 获取项目信息回收详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(projectInfoRecycleService.selectProjectInfoRecycleByProjectId(projectId));
    }

    /**
     * 新增项目信息回收
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:add')")
    @Log(title = "项目信息回收", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfoRecycle projectInfoRecycle)
    {
        System.out.println("--------"+projectInfoRecycle);
        return toAjax(projectInfoRecycleService.insertProjectInfoRecycle(projectInfoRecycle));
    }

    /**
     * 修改项目信息回收
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:edit')")
    @Log(title = "项目信息回收", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfoRecycle projectInfoRecycle)
    {
        return toAjax(projectInfoRecycleService.updateProjectInfoRecycle(projectInfoRecycle));
    }

    /**
     * 删除项目信息回收
     */
    @PreAuthorize("@ss.hasPermi('project:recycle:remove')")
    @Log(title = "项目信息回收", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectInfoRecycleService.deleteProjectInfoRecycleByProjectIds(projectIds));
    }
}
