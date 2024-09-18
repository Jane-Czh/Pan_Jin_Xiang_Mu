package com.ruoyi.project.controller;

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
import com.ruoyi.project.domain.ProjectInfoRecode;
import com.ruoyi.project.service.IProjectInfoRecodeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目修改记录Controller
 * 
 * @author Teandron
 * @date 2024-09-17
 */
@RestController
@RequestMapping("/project/recode")
public class ProjectInfoRecodeController extends BaseController
{
    @Autowired
    private IProjectInfoRecodeService projectInfoRecodeService;

    /**
     * 查询项目修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('project:recode:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectInfoRecode projectInfoRecode)
    {
        startPage();
        List<ProjectInfoRecode> list = projectInfoRecodeService.selectProjectInfoRecodeList(projectInfoRecode);
        return getDataTable(list);
    }

    /**
     * 导出项目修改记录列表
     */
    @PreAuthorize("@ss.hasPermi('project:recode:export')")
    @Log(title = "项目修改记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectInfoRecode projectInfoRecode)
    {
        List<ProjectInfoRecode> list = projectInfoRecodeService.selectProjectInfoRecodeList(projectInfoRecode);
        ExcelUtil<ProjectInfoRecode> util = new ExcelUtil<ProjectInfoRecode>(ProjectInfoRecode.class);
        util.exportExcel(response, list, "项目修改记录数据");
    }

    /**
     * 获取项目修改记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:recode:query')")
    @GetMapping(value = "/{recodeId}")
    public AjaxResult getInfo(@PathVariable("recodeId") Long recodeId)
    {
        return success(projectInfoRecodeService.selectProjectInfoRecodeByRecodeId(recodeId));
    }

    /**
     * 新增项目修改记录
     */
    @PreAuthorize("@ss.hasPermi('project:recode:add')")
    @Log(title = "项目修改记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectInfoRecode projectInfoRecode)
    {
        return toAjax(projectInfoRecodeService.insertProjectInfoRecode(projectInfoRecode));
    }

    /**
     * 修改项目修改记录
     */
    @PreAuthorize("@ss.hasPermi('project:recode:edit')")
    @Log(title = "项目修改记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectInfoRecode projectInfoRecode)
    {
        return toAjax(projectInfoRecodeService.updateProjectInfoRecode(projectInfoRecode));
    }

    /**
     * 删除项目修改记录
     */
    @PreAuthorize("@ss.hasPermi('project:recode:remove')")
    @Log(title = "项目修改记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recodeIds}")
    public AjaxResult remove(@PathVariable Long[] recodeIds)
    {
        return toAjax(projectInfoRecodeService.deleteProjectInfoRecodeByRecodeIds(recodeIds));
    }
}
