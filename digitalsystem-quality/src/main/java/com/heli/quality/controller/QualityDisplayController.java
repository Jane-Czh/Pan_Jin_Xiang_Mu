package com.heli.quality.controller;

import com.heli.quality.service.IQualityDisplayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.quality.controller
 * @Author: hong
 * @CreateTime: 2024-04-26  14:29
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/quality/display")
public class QualityDisplayController extends BaseController {
    @Autowired
    private IQualityDisplayService qualityDisplayService;

    /**
     * 三包期内新车返修率 指标57
     */
    @PreAuthorize("@ss.hasPermi('quality:display:warrantyRepairRate')")
    @PostMapping("/warrantyRepairRate")
    public TableDataInfo warrantyRepairRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectWarrantyRepairRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 三包期内整车月度返修率 指标58
     */
    @PreAuthorize("@ss.hasPermi('quality:display:warrantyVehicleRepairRate')")
    @PostMapping("/warrantyVehicleRepairRate")
    public TableDataInfo warrantyVehicleRepairRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectWarrantyVehicleRepairRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 外部质量损失率 指标59
     */
    @PreAuthorize("@ss.hasPermi('quality:display:externalLossRate')")
    @PostMapping("/externalLossRate")
    public TableDataInfo externalLossRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectExternalLossRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 月度售后质量问题总数  指标61
     */
    @PreAuthorize("@ss.hasPermi('quality:display:monthlyAfterSalesIssues')")
    @PostMapping("/monthlyAfterSalesIssues")
    public TableDataInfo monthlyAfterSalesIssues(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectMonthlyAfterSalesIssues(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 售后问题生产责任次数 指标62
     */
    @PreAuthorize("@ss.hasPermi('quality:display:productionLiabilityIssues')")
    @PostMapping("/productionLiabilityIssues")
    public TableDataInfo productionLiabilityIssues(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectProductionLiabilityIssues(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 质量考核季度排名 指标63
     */
    @PreAuthorize("@ss.hasPermi('quality:display:quarterlyRank')")
    @PostMapping("/quarterlyRank")
    public TableDataInfo quarterlyRank(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectQuarterlyRank(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 平均无故障时间 指标64
     */
    @PreAuthorize("@ss.hasPermi('quality:display:meantimeWithoutFailure')")
    @PostMapping("/meantimeWithoutFailure")
    public TableDataInfo meantimeWithoutFailure(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectMeantimeWithoutFailure(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 电车、大吨位一次交检合格率
     */
    @PreAuthorize("@ss.hasPermi('quality:display:singleInspectionPassRate')")
    @PostMapping("/singleInspectionPassRate")
    public TableDataInfo singleInspectionPassRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectSingleInspectionPassRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

    /**
     * 供应商不合格件返厂及时率
     */
    @PreAuthorize("@ss.hasPermi('quality:display:inTimeReturnRate')")
    @PostMapping("/inTimeReturnRate")
    public TableDataInfo inTimeReturnRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectInTimeReturnRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }


    /**
     * 班组自查合格率 下道工序反馈合格率
     */
    @PreAuthorize("@ss.hasPermi('quality:display:partQualificationRate')")
    @PostMapping("/partQualificationRate")
    public TableDataInfo partQualificationRate(@RequestBody DisplayRequestParam time) {
        List<DisplayEntity> list = qualityDisplayService.selectPartQualificationRate(time.getStartTime(), time.getEndTime());
        return getDataTable(list);
    }

}
