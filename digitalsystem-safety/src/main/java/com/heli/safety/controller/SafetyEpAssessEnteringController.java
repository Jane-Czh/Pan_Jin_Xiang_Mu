package com.heli.safety.controller;

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
import com.heli.safety.domain.SafetyEpAssessEntering;
import com.heli.safety.service.ISafetyEpAssessEnteringService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 安环科考核录入Controller
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/safety/entering")
public class SafetyEpAssessEnteringController extends BaseController
{
    @Autowired
    private ISafetyEpAssessEnteringService safetyEpAssessEnteringService;

    /**
     * 查询安环科考核录入列表
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:list')")
    @GetMapping("/list")
    public TableDataInfo list(SafetyEpAssessEntering safetyEpAssessEntering)
    {
        startPage();
        List<SafetyEpAssessEntering> list = safetyEpAssessEnteringService.selectSafetyEpAssessEnteringList(safetyEpAssessEntering);
        return getDataTable(list);
    }

    /**
     * 导出安环科考核录入列表
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:export')")
    @Log(title = "安环科考核录入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafetyEpAssessEntering safetyEpAssessEntering)
    {
        List<SafetyEpAssessEntering> list = safetyEpAssessEnteringService.selectSafetyEpAssessEnteringList(safetyEpAssessEntering);
        ExcelUtil<SafetyEpAssessEntering> util = new ExcelUtil<SafetyEpAssessEntering>(SafetyEpAssessEntering.class);
        util.exportExcel(response, list, "安环科考核录入数据");
    }

    /**
     * 获取安环科考核录入详细信息
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:query')")
    @GetMapping(value = "/{seaeId}")
    public AjaxResult getInfo(@PathVariable("seaeId") Long seaeId)
    {
        return success(safetyEpAssessEnteringService.selectSafetyEpAssessEnteringBySeaeId(seaeId));
    }

    /**
     * 新增安环科考核录入
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:add')")
    @Log(title = "安环科考核录入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafetyEpAssessEntering safetyEpAssessEntering)
    {
        return toAjax(safetyEpAssessEnteringService.insertSafetyEpAssessEntering(safetyEpAssessEntering));
    }

    /**
     * 修改安环科考核录入
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:edit')")
    @Log(title = "安环科考核录入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafetyEpAssessEntering safetyEpAssessEntering)
    {
        System.out.println("-------"+ safetyEpAssessEntering);
        return toAjax(safetyEpAssessEnteringService.updateSafetyEpAssessEntering(safetyEpAssessEntering));
    }

    /**
     * 删除安环科考核录入
     */
    @PreAuthorize("@ss.hasPermi('safety:entering:remove')")
    @Log(title = "安环科考核录入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{seaeIds}")
    public AjaxResult remove(@PathVariable Long[] seaeIds)
    {
        return toAjax(safetyEpAssessEnteringService.deleteSafetyEpAssessEnteringBySeaeIds(seaeIds));
    }
}
