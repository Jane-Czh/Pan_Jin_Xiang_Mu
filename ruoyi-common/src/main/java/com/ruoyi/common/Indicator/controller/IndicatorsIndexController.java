package com.ruoyi.common.indicator.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.indicator.domain.IndicatorsIndex;
import com.ruoyi.common.indicator.service.IIndicatorsIndexService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 指标-索引Controller
 *
 * @author ruoyi
 * @date 2024-07-30
 */
@RestController
@RequestMapping("/indicators")
public class IndicatorsIndexController extends BaseController {
    @Autowired
    private IIndicatorsIndexService indicatorsIndexService;

    /**
     * 查询指标-索引列表
     */
    @PreAuthorize("@ss.hasPermi('system:index:list')")
    @GetMapping("/list")
    public TableDataInfo list(IndicatorsIndex indicatorsIndex) {
        startPage();
        List<IndicatorsIndex> list = indicatorsIndexService.selectIndicatorsIndexList(indicatorsIndex);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('system:index:list')")
    @GetMapping("/allList")
    public TableDataInfo allList() {
        List<IndicatorsIndex> list = indicatorsIndexService.selectIndicatorsIndexAllList();
        return getDataTable(list);
    }

    /**
     * 导出指标-索引列表
     */
    @PreAuthorize("@ss.hasPermi('system:index:export')")
    @Log(title = "指标-索引", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IndicatorsIndex indicatorsIndex) {
        List<IndicatorsIndex> list = indicatorsIndexService.selectIndicatorsIndexList(indicatorsIndex);
        ExcelUtil<IndicatorsIndex> util = new ExcelUtil<IndicatorsIndex>(IndicatorsIndex.class);
        util.exportExcel(response, list, "指标-索引数据");
    }

    /**
     * 获取指标-索引详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:index:query')")
    @GetMapping(value = "/{indicatorId}")
    public AjaxResult getInfo(@PathVariable("indicatorId") Integer indicatorId) {
        return success(indicatorsIndexService.selectIndicatorsIndexByIndicatorId(indicatorId));
    }

    /**
     * 新增指标-索引
     */
    @PreAuthorize("@ss.hasPermi('system:index:add')")
    @Log(title = "指标-索引", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IndicatorsIndex indicatorsIndex) {
        return toAjax(indicatorsIndexService.insertIndicatorsIndex(indicatorsIndex));
    }

    /**
     * 修改指标-索引
     */
    @PreAuthorize("@ss.hasPermi('system:index:edit')")
    @Log(title = "指标-索引", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IndicatorsIndex indicatorsIndex) {
        return toAjax(indicatorsIndexService.updateIndicatorsIndex(indicatorsIndex));
    }

    /**
     * 删除指标-索引
     */
    @PreAuthorize("@ss.hasPermi('system:index:remove')")
    @Log(title = "指标-索引", businessType = BusinessType.DELETE)
    @DeleteMapping("/{indicatorIds}")
    public AjaxResult remove(@PathVariable Integer[] indicatorIds) {
        return toAjax(indicatorsIndexService.deleteIndicatorsIndexByIndicatorIds(indicatorIds));
    }
}
