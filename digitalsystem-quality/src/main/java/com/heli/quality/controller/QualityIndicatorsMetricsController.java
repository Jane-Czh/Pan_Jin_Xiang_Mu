package com.heli.quality.controller;

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
import com.heli.quality.domain.QualityIndicatorsMetrics;
import com.heli.quality.service.IQualityIndicatorsMetricsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 质量指标-统计Controller
 *
 * @author hong
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/quality/Metrics")
public class QualityIndicatorsMetricsController extends BaseController {
    @Autowired
    private IQualityIndicatorsMetricsService qualityIndicatorsMetricsService;

    /**
     * 查询质量指标-统计列表
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        startPage();
        List<QualityIndicatorsMetrics> list = qualityIndicatorsMetricsService.selectQualityIndicatorsMetricsList(qualityIndicatorsMetrics);
        return getDataTable(list);
    }

    /**
     * 导出质量指标-统计列表
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:export')")
    @Log(title = "质量指标-统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        List<QualityIndicatorsMetrics> list = qualityIndicatorsMetricsService.selectQualityIndicatorsMetricsList(qualityIndicatorsMetrics);
        ExcelUtil<QualityIndicatorsMetrics> util = new ExcelUtil<QualityIndicatorsMetrics>(QualityIndicatorsMetrics.class);
        util.exportExcel(response, list, "质量指标-统计数据");
    }

    /**
     * 获取质量指标-统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:query')")
    @GetMapping(value = "/{qcId}")
    public AjaxResult getInfo(@PathVariable("qcId") Long qcId) {
        return success(qualityIndicatorsMetricsService.selectQualityIndicatorsMetricsByQcId(qcId));
    }

    /**
     * 新增质量指标-统计
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:add')")
    @Log(title = "质量指标-统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        return toAjax(qualityIndicatorsMetricsService.insertQualityIndicatorsMetrics(qualityIndicatorsMetrics));
    }

    /**
     * 修改质量指标-统计
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:edit')")
    @Log(title = "质量指标-统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        return toAjax(qualityIndicatorsMetricsService.updateQualityIndicatorsMetrics(qualityIndicatorsMetrics));
    }

    /**
     * 删除质量指标-统计
     */
    @PreAuthorize("@ss.hasPermi('quality:Metrics:remove')")
    @Log(title = "质量指标-统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qcIds}")
    public AjaxResult remove(@PathVariable Long[] qcIds) {
        return toAjax(qualityIndicatorsMetricsService.deleteQualityIndicatorsMetricsByQcIds(qcIds));
    }
}
