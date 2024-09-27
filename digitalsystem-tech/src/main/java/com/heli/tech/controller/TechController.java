package com.heli.tech.controller;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.heli.tech.domain.TechNonStandardDisplayDTO;
import com.heli.tech.service.ITechAnnualPlanCountService;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
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
import com.heli.tech.domain.Tech;
import com.heli.tech.service.ITechService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * @description: 技术月度指标接口 + 技术展示接口
 * @author: hong
 * @date: 2024/5/29 19:32
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/tech")
public class TechController extends BaseController {
    @Autowired
    private ITechService techService;
    @Autowired
    private ITechAnnualPlanCountService techAnnualPlanCountService;

    @PreAuthorize("@ss.hasPermi('tech:monthly:update')")
    @Log(title = "[技术]计算", businessType = BusinessType.UPDATE)
    @PostMapping("/data/update")
    public AjaxResult test() {
        return toAjax(techService.updateCompletionRate());
    }

    /**
     * 新增[技术]指标填报
     */
    @PreAuthorize("@ss.hasPermi('tech:monthly:add')")
    @Log(title = "[技术]指标填报", businessType = BusinessType.INSERT)
    @PostMapping("/data/monthly")
    public AjaxResult add(@RequestBody Tech tech) {
        if (!techAnnualPlanCountService.checkTechAnnualDataIsExisted(DateUtils.getYear(tech.getYearAndMonth()))) {
            return AjaxResult.error("年度总计划未上传");
        }
//        if (!techService.checkTechMonthlyDataIsExisted(DateUtils.getLastMonth(tech.getYearAndMonth()))
//            && techService.checkDataExist()) {
//            return AjaxResult.error("上月数据未填报");
//        }
        if (techService.checkTechMonthlyDataIsExisted(tech.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }
        tech.setCreateBy(getUsername());
//        return toAjax(techService.insertTech(techService.calculateCompletionRate(tech)));
        return toAjax(techService.insertTech(tech));
    }

//    @PreAuthorize("@ss.hasPermi('tech:display:nonStandardAVGPreparationDays')")
//    @PostMapping("/display/nonStandardAVGPreparationDays")
//    public TableDataInfo nonStandardAVGPreparationDays(@RequestBody DisplayRequestParam time) {
//        List<DisplayEntity> list = techService.selectNonStandardAVGPreparationDays(time.getStartTime(), time.getEndTime());
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('tech:display:nonStandardAVGPreparationDays')")
    @PostMapping("/display/nonStandardAVGPreparationDays")
    public TableDataInfo nonStandardAVGPreparationDays(@RequestBody DisplayRequestParam time) {
        List<TechNonStandardDisplayDTO> list = techService.selectNonStandardAVGDaysAndNum(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('tech:display:prdScheduleCompletionRate')")
    @PostMapping("/display/prdScheduleCompletionRate")
    public TableDataInfo prdScheduleCompletionRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = techService.selectPRDScheduleCompletionRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 查询[技术]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('tech:monthly:list')")
    @GetMapping("/data/monthly/list")
    public TableDataInfo list(Tech tech) {
        startPage();
        List<Tech> list = techService.selectTechList(tech);
        return getDataTable(list);
    }

    /**
     * 获取[技术]指标填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('tech:monthly:query')")
    @GetMapping(value = "/data/monthly/{techId}")
    public AjaxResult getInfo(@PathVariable("techId") Long techId) {
        return success(techService.selectTechByTechId(techId));
    }

    /**
     * 修改[技术]指标填报
     */
    @PreAuthorize("@ss.hasPermi('tech:monthly:edit')")
    @Log(title = "[技术]指标填报", businessType = BusinessType.UPDATE)
    @PutMapping("/data/monthly")
    public AjaxResult edit(@RequestBody Tech tech) {
        tech.setUpdateBy(getUsername());
        if (!Objects.equals(techService.selectTechByTechId(tech.getTechId()).getCompletedmonthlyPlancounts(), tech.getCompletedmonthlyPlancounts())) {
            techService.updateTech(tech);
            techService.batchUpdateTech(tech.getYearAndMonth());
            return AjaxResult.success();
        }
        return toAjax(techService.updateTech(tech));
    }

    /**
     * 删除[技术]指标填报
     */
    @PreAuthorize("@ss.hasPermi('tech:monthly:remove')")
    @Log(title = "[技术]指标填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/data/monthly/{techIds}")
    public AjaxResult remove(@PathVariable Long[] techIds) {
        return toAjax(techService.deleteTechByTechIds(techIds));
    }
}
