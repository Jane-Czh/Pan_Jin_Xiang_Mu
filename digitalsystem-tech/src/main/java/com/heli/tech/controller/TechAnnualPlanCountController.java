package com.heli.tech.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.tech.service.ITechService;
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
import com.heli.tech.domain.TechAnnualPlanCount;
import com.heli.tech.service.ITechAnnualPlanCountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【技术】总计划年初填报Controller
 *
 * @author hong
 * @date 2024-04-27
 */
@RestController
@RequestMapping("/tech/data/annual")
public class TechAnnualPlanCountController extends BaseController {
    @Autowired
    private ITechAnnualPlanCountService techAnnualPlanCountService;
    @Autowired
    private ITechService techService;

    /**
     * 修改【技术】总计划年初填报,并更新月度完成数
     */
    @PreAuthorize("@ss.hasPermi('Tech:data:edit')")
    @Log(title = "【技术】总计划年初填报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechAnnualPlanCount techAnnualPlanCount) {
        techAnnualPlanCount.setUpdateBy(getUsername());
        techAnnualPlanCountService.updateTechAnnualPlanCount(techAnnualPlanCount);
        if (techService.checkTechMonthlyDataIsExistedByYear(techAnnualPlanCount.getNaturalYear())){
            Date date = new Date(techAnnualPlanCount.getNaturalYear() - 1900 , Calendar.JANUARY, 1);
            techService.batchUpdateTech(date);
        }

        return AjaxResult.success();
    }

    /**
     * 查询【技术】总计划年初填报列表
     */
    @PreAuthorize("@ss.hasPermi('Tech:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechAnnualPlanCount techAnnualPlanCount) {
        startPage();
        List<TechAnnualPlanCount> list = techAnnualPlanCountService.selectTechAnnualPlanCountList(techAnnualPlanCount);
        return getDataTable(list);
    }

    /**
     * 获取【技术】总计划年初填报详细信息
     */
    @PreAuthorize("@ss.hasPermi('Tech:data:query')")
    @GetMapping(value = "/{tapcId}")
    public AjaxResult getInfo(@PathVariable("tapcId") Long tapcId) {
        return success(techAnnualPlanCountService.selectTechAnnualPlanCountByTapcId(tapcId));
    }

    /**
     * 新增【技术】总计划年初填报
     */
    @PreAuthorize("@ss.hasPermi('Tech:data:add')")
    @Log(title = "【技术】总计划年初填报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechAnnualPlanCount techAnnualPlanCount) {
        if (techAnnualPlanCountService.checkTechAnnualDataIsExisted(techAnnualPlanCount.getNaturalYear())) {
            return AjaxResult.error("年度总计划已上传");
        }
        techAnnualPlanCount.setCreateBy(getUsername());
        return toAjax(techAnnualPlanCountService.insertTechAnnualPlanCount(techAnnualPlanCount));
    }

    /**
     * 删除【技术】总计划年初填报
     */
    @PreAuthorize("@ss.hasPermi('Tech:data:remove')")
    @Log(title = "【技术】总计划年初填报", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tapcIds}")
    public AjaxResult remove(@PathVariable Long[] tapcIds) {
        return toAjax(techAnnualPlanCountService.deleteTechAnnualPlanCountByTapcIds(tapcIds));
    }
}
