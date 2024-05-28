package com.heli.tech.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.heli.tech.domain.TechAnnualPlanCount;
import com.heli.tech.service.ITechAnnualPlanCountService;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * [技术]指标填报Controller
 *
 * @author hong
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/tech")
public class TechController extends BaseController {
    @Autowired
    private ITechService techService;
    @Autowired
    private ITechAnnualPlanCountService techAnnualPlanCountService;



    /**
     * 新增[技术]指标填报
     */
    @PreAuthorize("@ss.hasPermi('tech:data:add')")
    @Log(title = "[技术]指标填报", businessType = BusinessType.INSERT)
    @PostMapping("/data/monthly")
    public AjaxResult add(Tech tech) {
        if (!techAnnualPlanCountService.checkTechAnnualDataIsExisted(DateUtils.getYear(tech.getYearAndMonth()))) {
            return AjaxResult.error("年度总计划未上传");
        }
        if (!techService.checkTechMonthlyDataIsExisted(DateUtils.getLastMonth(tech.getYearAndMonth()))) {
            return AjaxResult.error("上月数据未填报");
        }
        if (techService.checkTechMonthlyDataIsExisted(tech.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }
        tech.setCreateBy(getUsername());
        return toAjax(techService.insertTech(techService.calculateCompletionRate(tech, techAnnualPlanCountService.selectTechAnnualNumberByYear(DateUtils.getYear(tech.getYearAndMonth())))));
    }

    @PostMapping("/display/employeesAVGMonthlyNumber")
    public TableDataInfo nonStandardAVGPreparationDays(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = techService.selectNonStandardAVGPreparationDays(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }
    @PostMapping("/display/prdScheduleCompletionRate")
    public TableDataInfo prdScheduleCompletionRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = techService.selectPRDScheduleCompletionRate(time.getStartTime(),time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 查询[技术]指标填报列表
     */
    @PreAuthorize("@ss.hasPermi('tech:data:list')")
    @GetMapping("/data/monthly/list")
    public TableDataInfo list(Tech tech) {
        startPage();
        List<Tech> list = techService.selectTechList(tech);
        return getDataTable(list);
    }

    /**
     * 获取[技术]指标填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('tech:data:query')")
    @GetMapping(value = "/data/monthly/{techId}")
    public AjaxResult getInfo(@PathVariable("techId") Long techId) {
        return success(techService.selectTechByTechId(techId));
    }

    /**
     * 修改[技术]指标填报
     */
    @PreAuthorize("@ss.hasPermi('tech:data:edit')")
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
    @PreAuthorize("@ss.hasPermi('tech:data:remove')")
    @Log(title = "[技术]指标填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/data/monthly/{techIds}")
    public AjaxResult remove(@PathVariable Long[] techIds) {
        return toAjax(techService.deleteTechByTechIds(techIds));
    }
}
