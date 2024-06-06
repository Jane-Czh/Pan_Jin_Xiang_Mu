package com.heli.quality.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.quality.service.IQualityAfterSalesRecordService;
import com.ruoyi.common.core.domain.R;
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
@RequestMapping("/quality/data/metrics")
public class QualityIndicatorsMetricsController extends BaseController {
    @Autowired
    private IQualityIndicatorsMetricsService qualityIndicatorsMetricsService;
    @Autowired
    private IQualityAfterSalesRecordService qualityAfterSalesRecordService;


    /**
     * 更新列表操作。
     * 该方法通过计算给定时间范围内的质量指标来更新相关的列表信息。
     */
    @PreAuthorize("@ss.hasPermi('quality:data:update')")
    @PostMapping("/updateList")
    public R<String> calculateQualityIndicators(Date startTime, Date endTime) {
        // 计算指定时间范围内的质量指标
        return qualityAfterSalesRecordService.calculateQualityIndicators(startTime, endTime);
    }


    /**
     * 查询质量指标-统计列表
     */
    @PreAuthorize("@ss.hasPermi('quality:metrics:list')")
    @GetMapping("/list")
    public TableDataInfo list(QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        startPage();
        List<QualityIndicatorsMetrics> list = qualityIndicatorsMetricsService.selectQualityIndicatorsMetricsList(qualityIndicatorsMetrics);
        return getDataTable(list);
    }


    /**
     * 获取质量指标-统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('quality:metrics:query')")
    @GetMapping(value = "/{qcId}")
    public AjaxResult getInfo(@PathVariable("qcId") Long qcId) {
        return success(qualityIndicatorsMetricsService.selectQualityIndicatorsMetricsByQcId(qcId));
    }

    /**
     * 新增质量指标-统计
     */
//    @PreAuthorize("@ss.hasPermi('quality:Metrics:add')")
//    @Log(title = "质量指标-统计", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody QualityIndicatorsMetrics qualityIndicatorsMetrics) {
//        return toAjax(qualityIndicatorsMetricsService.insertQualityIndicatorsMetrics(qualityIndicatorsMetrics));
//    }

    /**
     * 修改质量指标-统计
     */
    @PreAuthorize("@ss.hasPermi('quality:metrics:edit')")
    @Log(title = "质量指标-统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QualityIndicatorsMetrics qualityIndicatorsMetrics) {
        return toAjax(qualityIndicatorsMetricsService.updateQualityIndicatorsMetrics(qualityIndicatorsMetrics));
    }

    /**
     * 删除质量指标-统计
     */
    @PreAuthorize("@ss.hasPermi('quality:metrics:remove')")
    @Log(title = "质量指标-统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{qcIds}")
    public AjaxResult remove(@PathVariable Long[] qcIds) {
        return toAjax(qualityIndicatorsMetricsService.deleteQualityIndicatorsMetricsByQcIds(qcIds));
    }
}
