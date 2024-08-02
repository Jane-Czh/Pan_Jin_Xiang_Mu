package com.heli.quality.service.impl;

import com.heli.quality.mapper.QualityDisplayMapper;
import com.heli.quality.service.IQualityDisplayService;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.quality.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-26  14:27
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class QualityDisplayServiceImpl implements IQualityDisplayService {
    @Autowired
    private QualityDisplayMapper qualityDisplayMapper;

    /**
     * 三包期内新车返修率 指标57
     */
    @Override
    public List<DisplayEntity> selectWarrantyRepairRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectWarrantyRepairRate(startTime, endTime);
    }

    /**
     * 三包期内整车月度返修率 指标58
     */
    @Override
    public List<DisplayEntity> selectWarrantyVehicleRepairRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectWarrantyVehicleRepairRate(startTime, endTime);

    }

    /**
     * 外部质量损失率 指标59
     */
    @Override
    public List<DisplayEntity> selectExternalLossRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectExternalLossRate(startTime, endTime);
    }

    /**
     * 月度售后质量问题总数  指标61
     */
    @Override
    public List<DisplayEntity> selectMonthlyAfterSalesIssues(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectMonthlyAfterSalesIssues(startTime, endTime);
    }

    /**
     * 售后问题生产责任次数 指标62
     */
    @Override
    public List<DisplayEntity> selectProductionLiabilityIssues(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectProductionLiabilityIssues(startTime, endTime);
    }

    /**
     * 质量考核季度排名 指标63
     */
    @Override
    public List<DisplayEntity> selectQuarterlyRank(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectQuarterlyRank(startTime, endTime);
    }

    /**
     * 平均无故障时间 指标64
     */
    @Override
    public List<DisplayEntity> selectMeantimeWithoutFailure(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectMeantimeWithoutFailure(startTime, endTime);
    }

    /**
     * 电车、大吨位一次交检合格率
     */
    @Override
//    public List<DisplayEntity> selectSingleInspectionPassRate(Date startTime, Date endTime) {
    public List<Map<String, Object>> selectSingleInspectionPassRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectSingleInspectionPassRate(startTime, endTime);
    }

    /**
     * 供应商不合格件返厂及时率
     */
    @Override
    public List<DisplayEntity> selectInTimeReturnRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectInTimeReturnRate(startTime, endTime);
    }

    /**
     * 班组自查合格率、下道工序反馈合格率
     */
    @Override
    public List<DisplayEntity> selectPartQualificationRate(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectPartQualificationRate(startTime, endTime);
    }
}
