package com.heli.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
import com.heli.project.domain.ProjectInfoTable;
import com.heli.project.service.IProjectInfoTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目基本信息Controller
 * 
 * @author Teandron
 * @date 2024-05-08
 */
@RestController
@RequestMapping("/project/Info")
public class ProjectInfoTableController extends BaseController
{
    @Autowired
    private IProjectInfoTableService projectInfoTableService;

    /**
     * 查询项目基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:Info:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectInfoTable projectInfoTable)
    {
        startPage();
        List<ProjectInfoTable> list = projectInfoTableService.selectProjectInfoTableList(projectInfoTable);
        return getDataTable(list);
    }

    /**
     * 导出项目基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:Info:export')")
    @Log(title = "项目基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectInfoTable projectInfoTable)
    {
        List<ProjectInfoTable> list = projectInfoTableService.selectProjectInfoTableList(projectInfoTable);
        ExcelUtil<ProjectInfoTable> util = new ExcelUtil<ProjectInfoTable>(ProjectInfoTable.class);
        util.exportExcel(response, list, "项目基本信息数据");
    }

    /**
     * 获取项目基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:Info:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(projectInfoTableService.selectProjectInfoTableByProjectId(projectId));
    }

    /**
     * 新增项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('project:Info:add')")
    @Log(title = "项目基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfoTable projectInfoTable)
    {
        return toAjax(projectInfoTableService.insertProjectInfoTable(projectInfoTable));
    }

    /**
     * 修改项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('project:Info:edit')")
    @Log(title = "项目基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfoTable projectInfoTable)
    {
        return toAjax(projectInfoTableService.updateProjectInfoTable(projectInfoTable));
    }

    /**
     * 关联历史项目
     */
    @PutMapping("/updataHistory")
    public AjaxResult updataHistory(@RequestBody ProjectInfoTable projectInfoTable)
    {
        System.out.println("-----test------"+projectInfoTable);
        Long[] oldProjectList = projectInfoTable.getOldProjectList();
        List<Long> collect = Arrays.stream(oldProjectList).collect(Collectors.toList());
        projectInfoTable.setOldProjectId(collect.get(0));
//        System.out.println(Arrays.toString(oldProjectList));
//        System.out.println(oldProjectList);

        return toAjax(projectInfoTableService.updateProjectInfoTable(projectInfoTable));
    }

    /**
     * 删除项目基本信息
     */
    @PreAuthorize("@ss.hasPermi('project:Info:remove')")
    @Log(title = "项目基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectInfoTableService.deleteProjectInfoTableByProjectIds(projectIds));
    }
}
