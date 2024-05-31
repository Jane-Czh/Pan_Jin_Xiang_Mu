package com.heli.quality.service;

import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IQualityDisplayService {

    //三包期内新车返修率 指标57
    List<DisplayEntity> selectWarrantyRepairRate(Date startTime, Date endTime);

    //三包期内整车月度返修率 指标58
    List<DisplayEntity> selectWarrantyVehicleRepairRate(Date startTime, Date endTime);

    //外部质量损失率 指标59
    List<DisplayEntity> selectExternalLossRate(Date startTime, Date endTime);

    //月度售后质量问题总数  指标61
    List<DisplayEntity> selectMonthlyAfterSalesIssues(Date startTime, Date endTime);

    //售后问题生产责任次数 指标62
    List<DisplayEntity> selectProductionLiabilityIssues(Date startTime, Date endTime);

    //质量考核季度排名 指标63
    List<DisplayEntity> selectQuarterlyRank(Date startTime, Date endTime);

    // 平均无故障时间 指标64
    List<DisplayEntity> selectMeantimeWithoutFailure(Date startTime, Date endTime);

    //电车、大吨位一次交检合格率
    List<DisplayEntity> selectSingleInspectionPassRate(Date startTime, Date endTime);

    //供应商不合格件返厂及时率
    List<DisplayEntity> selectInTimeReturnRate(Date startTime, Date endTime);

    //班组自查合格率、下道工序反馈合格率
    List<DisplayEntity> selectPartQualificationRate(Date startTime, Date endTime);


}
