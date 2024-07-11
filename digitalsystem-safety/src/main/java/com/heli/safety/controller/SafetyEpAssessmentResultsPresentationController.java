package com.heli.safety.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.safety.domain.SafetyEpAssessEntering;
import com.heli.safety.service.ISafetyEpAssessEnteringService;
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
import com.heli.safety.domain.SafetyEpAssessmentResultsPresentation;
import com.heli.safety.service.ISafetyEpAssessmentResultsPresentationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成绩考核展示Controller
 * 
 * @author heli
 * @date 2024-04-21
 */
@RestController
@RequestMapping("/safety/presentation")
public class SafetyEpAssessmentResultsPresentationController extends BaseController
{
    @Autowired
    private ISafetyEpAssessmentResultsPresentationService safetyEpAssessmentResultsPresentationService;
    @Autowired
    private ISafetyEpAssessEnteringService safetyEpAssessEnteringService;

    /**
     * 同步录入数据库中的考核成绩
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:syncReport')")
    @PostMapping("/synchronization")
    public void Database_Synchronization(){
        System.out.println("------同步------");
        SafetyEpAssessEntering safetyEpAssessEntering = new SafetyEpAssessEntering();
        List<SafetyEpAssessEntering> list = safetyEpAssessEnteringService.selectSafetyEpAssessEnteringList(safetyEpAssessEntering);
        safetyEpAssessmentResultsPresentationService.Synchronization(list);
    }
    /**
     * 查询成绩考核展示列表
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        startPage();
        List<SafetyEpAssessmentResultsPresentation> list = safetyEpAssessmentResultsPresentationService.selectSafetyEpAssessmentResultsPresentationList(safetyEpAssessmentResultsPresentation);
        return getDataTable(list);
    }

    /**
     * 导出成绩考核展示列表
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:export')")
    @Log(title = "成绩考核展示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        List<SafetyEpAssessmentResultsPresentation> list = safetyEpAssessmentResultsPresentationService.selectSafetyEpAssessmentResultsPresentationList(safetyEpAssessmentResultsPresentation);
        ExcelUtil<SafetyEpAssessmentResultsPresentation> util = new ExcelUtil<SafetyEpAssessmentResultsPresentation>(SafetyEpAssessmentResultsPresentation.class);
        util.exportExcel(response, list, "成绩考核展示数据");
    }

    /**
     * 获取成绩考核展示详细信息
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:query')")
    @GetMapping(value = "/{searpId}")
    public AjaxResult getInfo(@PathVariable("searpId") Long searpId)
    {
        return success(safetyEpAssessmentResultsPresentationService.selectSafetyEpAssessmentResultsPresentationBySearpId(searpId));
    }

    /**
     * 新增成绩考核展示
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:add')")
    @Log(title = "成绩考核展示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        return toAjax(safetyEpAssessmentResultsPresentationService.insertSafetyEpAssessmentResultsPresentation(safetyEpAssessmentResultsPresentation));
    }

    /**
     * 修改成绩考核展示
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:edit')")
    @Log(title = "成绩考核展示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEpAssessmentResultsPresentation safetyEpAssessmentResultsPresentation)
    {
        return toAjax(safetyEpAssessmentResultsPresentationService.updateSafetyEpAssessmentResultsPresentation(safetyEpAssessmentResultsPresentation));
    }

    /**
     * 删除成绩考核展示
     */
    @PreAuthorize("@ss.hasPermi('safety:presentation:remove')")
    @Log(title = "成绩考核展示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{searpIds}")
    public AjaxResult remove(@PathVariable Long[] searpIds)
    {
        return toAjax(safetyEpAssessmentResultsPresentationService.deleteSafetyEpAssessmentResultsPresentationBySearpIds(searpIds));
    }
}
