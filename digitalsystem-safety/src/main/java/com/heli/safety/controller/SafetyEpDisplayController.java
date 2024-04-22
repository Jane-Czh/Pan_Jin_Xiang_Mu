package com.heli.safety.controller;

import com.heli.safety.service.ISafetyEpDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import com.ruoyi.common.core.domain.entity.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 安环指标展示
 * @author: hong
 * @date: 2024/4/22 11:39
 * @param:
 * @return:
 **/
@RestController
@RequestMapping("/safetyEp/display")
public class SafetyEpDisplayController extends BaseController {

    @Autowired
    private ISafetyEpDisplayService safetyEpDisplayService;

    /**
     * 当月度设备维修总费用 指标23
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:curEquipmentMaintenanceCost')")
    @PostMapping("/curEquipmentMaintenanceCost")
    public TableDataInfo selectCurEquipmentMaintenanceCost(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectCurEquipmentMaintenanceCost(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 重点设备故障率 指标45
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:keyEquipmentFailureRate')")
    @PostMapping("/keyEquipmentFailureRate")
    public TableDataInfo selectKeyEquipmentFailureRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectKeyEquipmentFailureRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月设备故障累计停产时间 指标46
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:curEquipmentFailuresTotaltime')")
    @PostMapping("/curEquipmentFailuresTotaltime")
    public TableDataInfo selectCurEquipmentFailuresTotaltime(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectCurEquipmentFailuresTotaltime(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 主要设备故障总次数 指标67
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:keyEquipmentTotalFailureCount')")
    @PostMapping("/keyEquipmentTotalFailureCount")
    public TableDataInfo selectKeyEquipmentTotalFailureCount(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectKeyEquipmentTotalFailureCount(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 与其他的展示方式不同-设备故障类别次数分布图 指标68
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:outputPercapitavalue')")
    @PostMapping("/outputPercapitavalue")
    public TableDataInfo selectOutputPercapitavalue(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectOutputPercapitavalue(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 当月设备维修成本-替换件成本 指标69
     */
    @PreAuthorize("@ss.hasPermi('safetyEp:display:curEquipmentReplacementCost')")
    @PostMapping("/curEquipmentReplacementCost")
    public TableDataInfo selectCurEquipmentReplacementCost(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = safetyEpDisplayService.selectCurEquipmentReplacementCost(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }


}
