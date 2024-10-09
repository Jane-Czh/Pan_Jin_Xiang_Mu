package com.ruoyi.common.indicator.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.indicator.domain.IndicatorsIndex;
import com.ruoyi.common.indicator.domain.IndicatorsTarget;
import com.ruoyi.common.indicator.domain.TargetByDeptParam;
import com.ruoyi.common.indicator.domain.TargetByNameParam;
import com.ruoyi.common.indicator.service.IIndicatorsIndexService;
import com.ruoyi.common.indicator.service.IIndicatorsTargetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.*;
import java.util.Date;
import java.util.List;

/**
 * 指标-目标值Controller
 *
 * @author hong
 * @date 2024-07-30
 */
@Slf4j
@RestController
@RequestMapping("/indicators/target")
public class IndicatorsTargetController extends BaseController {
    @Autowired
    private IIndicatorsTargetService indicatorsTargetService;
    @Autowired
    private IIndicatorsIndexService indicatorsIndexService;

    /**
     * @description: 根据指标名称和起止时间查询指标-目标值列表
     * @author: hong
     * @date: 2024/9/29 19:11
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:list')")
    @PostMapping("/targetInfoByIndicator")
    public TableDataInfo targetList(@RequestBody TargetByNameParam targetByNameParam) {
        log.info("根据指标名称和起止时间查询指标-目标值列表: " + targetByNameParam);

        Date startDate = targetByNameParam.getStartDate();
        Date endDate = targetByNameParam.getEndDate();

        // 将startDate和endDate 转为同年1月1日
        startDate.setMonth(0);
        startDate.setDate(1);
        endDate.setMonth(0);
        endDate.setDate(1);

//        LocalDate newStartDate = startDate.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//        LocalDate newEndDate = endDate.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//        try {
//            // 将当前日期转换为同一年的1月1日
//            LocalDate start = newStartDate.withMonth(1).withDayOfMonth(1);
//            LocalDate end = newEndDate.withMonth(1).withDayOfMonth(1);
//            Instant startInstant = start.atStartOfDay(ZoneId.systemDefault()).toInstant();
//            Instant endInstant = end.atStartOfDay(ZoneId.systemDefault()).toInstant();
//
//            targetByNameParam.setStartDate(Date.from(startInstant));
//            targetByNameParam.setStartDate(Date.from(endInstant));
//        } catch (DateTimeException e) {
//            // 处理日期时间异常，例如月份天数无效
//            System.err.println("Error setting date: " + e.getMessage());
//        }

        log.info("targetByNameParam: " + targetByNameParam);

        List<IndicatorsTarget> list = indicatorsTargetService.selectTargetListByNameAndDate(targetByNameParam);
        return getDataTable(list);
    }

    /**
     * @description: 根据部门查询指标-目标值全部信息
     * @author: hong
     * @date: 2024/7/30 17:16
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:list')")
    @PostMapping("/allTargetInfoByDept")
    public TableDataInfo allList(@RequestBody TargetByDeptParam targetByDeptParam) {
        log.info("+1+1+1+1+1+1+1" + String.valueOf(targetByDeptParam));

        IndicatorsTarget target = new IndicatorsTarget();

        if (targetByDeptParam.getDate() != null) {

            Date utilDate = targetByDeptParam.getDate();

            LocalDate currentDate = utilDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            try {
                // 将当前日期转换为同一年的1月1日
                LocalDate newDate = currentDate.withMonth(1).withDayOfMonth(1);
                log.info("newDate: " + newDate);
                Instant instant = newDate.atStartOfDay(ZoneId.systemDefault()).toInstant();

                target.setNatureYear(Date.from(instant));
            } catch (DateTimeException e) {
                // 处理日期时间异常，例如月份天数无效
                System.err.println("Error setting date: " + e.getMessage());
            }

        } else {
            target.setNatureYear(null);
        }


        log.info("根据部门查询指标-目标值全部信息: " + targetByDeptParam);

        target.setIndicatorDept(targetByDeptParam.getDeptName());

        log.info("target: " + target);
        List<IndicatorsTarget> list = indicatorsTargetService.selectIndicatorsTargetList(target);
        return getDataTable(list);
    }

    /**
     * 查询指标-目标值列表
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:list')")
    @GetMapping("/list")
    public TableDataInfo list(IndicatorsTarget indicatorsTarget) {
        startPage();
        List<IndicatorsTarget> list = indicatorsTargetService.selectIndicatorsTargetList(indicatorsTarget);
        return getDataTable(list);
    }

    /**
     * 导出指标-目标值列表
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:export')")
    @Log(title = "指标-目标值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IndicatorsTarget indicatorsTarget) {
        List<IndicatorsTarget> list = indicatorsTargetService.selectIndicatorsTargetList(indicatorsTarget);
        ExcelUtil<IndicatorsTarget> util = new ExcelUtil<IndicatorsTarget>(IndicatorsTarget.class);
        util.exportExcel(response, list, "指标-目标值数据");
    }

    /**
     * 获取指标-目标值详细信息
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:query')")
    @GetMapping(value = "/{itId}")
    public AjaxResult getInfo(@PathVariable("itId") Long itId) {
        return success(indicatorsTargetService.selectIndicatorsTargetByItId(itId));
    }

    /**
     * 新增指标-目标值
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:add')")
    @Log(title = "指标-目标值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IndicatorsTarget indicatorsTarget) {
        log.info("指标-目标值: " + indicatorsTarget);
        //dept中存的是indicatorId
        String indicatorId = indicatorsTarget.getIndicatorDept();
        // string转int
        Integer indicatorIdInt = Integer.parseInt(indicatorId);
        // 根据indicatorId查询指标
        IndicatorsIndex indicatorsIndex = indicatorsIndexService.selectIndicatorsIndexByIndicatorId(indicatorIdInt);
        indicatorsTarget.setIndicatorName(indicatorsIndex.getIndicatorNameEn());
        indicatorsTarget.setIndicatorDept(indicatorsIndex.getIndicatorDeptEn());
        indicatorsTarget.setIndicatorNameCn(indicatorsIndex.getIndicatorNameCn());

        IndicatorsTarget temp = new IndicatorsTarget();
        temp.setNatureYear(indicatorsTarget.getNatureYear());
        temp.setIndicatorName(indicatorsTarget.getIndicatorName());

        // 根据目标值年份和指标en名判断是否已存在
        if (indicatorsTargetService.selectIndicatorsTargetList(temp).size() > 0) {
            return AjaxResult.error(indicatorsIndex.getIndicatorNameCn() + "当年目标值已设置");
        }

        indicatorsTarget.setCreateBy(getUsername());
        log.info("indicator目标值: " + indicatorsTarget);

        return toAjax(indicatorsTargetService.insertIndicatorsTarget(indicatorsTarget));
    }

    /**
     * 修改指标-目标值
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:edit')")
    @Log(title = "指标-目标值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IndicatorsTarget indicatorsTarget) {
        indicatorsTarget.setUpdateBy(getUsername());
        return toAjax(indicatorsTargetService.updateIndicatorsTarget(indicatorsTarget));
    }

    /**
     * 删除指标-目标值
     */
    @PreAuthorize("@ss.hasPermi('indicators:target:remove')")
    @Log(title = "指标-目标值", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itIds}")
    public AjaxResult remove(@PathVariable Long[] itIds) {
        return toAjax(indicatorsTargetService.deleteIndicatorsTargetByItIds(itIds));
    }
}
