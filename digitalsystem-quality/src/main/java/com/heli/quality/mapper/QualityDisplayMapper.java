package com.heli.quality.mapper;

import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface QualityDisplayMapper {

    //三包期内新车返修率 指标57
    List<DisplayEntity> selectWarrantyRepairRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //三包期内整车月度返修率 指标58
    List<DisplayEntity> selectWarrantyVehicleRepairRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //外部质量损失率 指标59
    List<DisplayEntity> selectExternalLossRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //月度售后质量问题总数  指标61
    List<DisplayEntity> selectMonthlyAfterSalesIssues(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //售后问题生产责任次数 指标62
    List<DisplayEntity> selectProductionLiabilityIssues(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //质量考核季度排名 指标63
    List<DisplayEntity> selectQuarterlyRank(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    // 平均无故障时间 指标64
    List<DisplayEntity> selectMeantimeWithoutFailure(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //电车、大吨位一次交检合格率
    List<DisplayEntity> selectSingleInspectionPassRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //供应商不合格件返厂及时率
    List<DisplayEntity> selectInTimeReturnRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //班组自查合格率、下道工序反馈合格率
    List<DisplayEntity> selectPartQualificationRate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);


}
