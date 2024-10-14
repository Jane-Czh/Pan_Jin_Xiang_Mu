package com.heli.project.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.heli.project.domain.UpdataRecode;
import com.heli.project.service.IUpdataRecodeService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.heli.project.domain.ProjectInfoTable;
import com.heli.project.service.IProjectInfoTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.project.domain.ProjectInfoRecode;
import com.ruoyi.project.service.IProjectInfoRecodeService;

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

    @Autowired
    private IProjectInfoRecodeService projectInfoRecodeService;


    @PreAuthorize("@ss.hasPermi('project:Info:import')")
    @PostMapping("/import")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        try {
            projectInfoTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }


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
     * 获取项目修改信息
     */
    @PreAuthorize("@ss.hasPermi('project:Info:recode')")
    @GetMapping(value = "/recoding/{projectName}")
    public TableDataInfo getInfoRecode(@PathVariable("projectName") String projectName)
    {
        System.out.println("-------" + "query" + "-------" +projectName+ "recode" +"-------");
        System.out.println(projectInfoTableService.selectProjectRecodingByProjectName(projectName));
        return getDataTable(projectInfoTableService.selectProjectRecodingByProjectName(projectName));
    }
    /**
     * 进度上报时记录修改信息
     */
    @PreAuthorize("@ss.hasPermi('updata_recode:recode:add')")
    @Log(title = "更新记录", businessType = BusinessType.INSERT)
    @PostMapping(value = "/recodeAdd")
    public AjaxResult addUpdateRecode(@RequestBody ProjectInfoRecode projectInfoRecode)
    {
        System.out.println("----------");
        System.out.println(projectInfoRecode);

        return toAjax(projectInfoRecodeService.insertProjectInfoRecode(projectInfoRecode));
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
    @PreAuthorize("@ss.hasPermi('project:Info:relevance')")
    @PutMapping("/updataHistory")
    @Log(title = "项目基本信息", businessType = BusinessType.UPDATE)
    public AjaxResult updataHistory(@RequestBody ProjectInfoTable projectInfoTable)
    {
        System.out.println("-----test------"+projectInfoTable);
        Long[] oldProjectList = projectInfoTable.getOldProjectList();
        StringBuilder message = new StringBuilder();
        for (Long aLong : oldProjectList) {
            message.append(aLong.toString());
            message.append(",");
        }
//        List<Long> collect = Arrays.stream(oldProjectList).collect(Collectors.toList());
        projectInfoTable.setOldProjectId(message.toString());
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

    /**
     * 移入回收站
     */
//    @PreAuthorize("@ss.hasPermi('project:Info:recycle')")
//    @Log(title = "项目基本信息", businessType = BusinessType.DELETE)
//    @DeleteMapping("recycle/{projectIds}")
//    public AjaxResult recycle(@PathVariable Long[] projectIds)
//    {
//        System.out.println(projectIds);
////        return toAjax(projectInfoTableService.deleteProjectInfoTableByProjectIds(projectIds));
//        return toAjax(1);
//    }
}
