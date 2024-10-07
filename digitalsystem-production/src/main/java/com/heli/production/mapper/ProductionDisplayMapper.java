package com.heli.production.mapper;

import com.heli.production.domain.ProductionFilling;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface ProductionDisplayMapper {
    /**
     * 当月单台非BOM物料费用 指标24
     */
    List<DisplayEntity> selectCurNonBomMaterialCost(@Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime);

    /**
     * 当月单台低值易耗费用 指标25
     */
    List<DisplayEntity> selectCurLowValueConsumables(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 在制物资年化周转天数 指标29
     */
    List<DisplayEntity> selectInventoryTurnoverdays(@Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime);

    /**
     * 人均生产台数 指标37
     */
    List<DisplayEntity> selectOutputPercapitacounts(@Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime);

    /**
     * 人均产值 指标38
     */
    List<DisplayEntity> selectOutputPercapitavalue(@Param("startTime") Date startTime,
                                                   @Param("endTime") Date endTime);

    /**
     * 上线及时率 指标41
     */
    List<DisplayEntity> selectOnlineOntimerate(@Param("startTime") Date startTime,
                                               @Param("endTime") Date endTime);

    /**
     * 一线当月加班时长 指标48
     */
    List<DisplayEntity> selectOvertimeFrontlinemonth(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 每日上线数和完工数
     */
    List<DisplayEntity> selectDailyLaunchAndCompletion(@Param("startTime") Date startTime,
                                                       @Param("endTime") Date endTime);

    /**
     * 每月上线数和完工数
     */
    List<DisplayEntity> selectMonthlyLaunchAndCompletion(@Param("startTime") Date startTime,
                                                         @Param("endTime") Date endTime);

    /**
     * 每年上线数和完工数
     */
    List<DisplayEntity> selectAnnualLaunchAndCompletion(@Param("startTime") Date startTime,
                                                        @Param("endTime") Date endTime);


    ProductionFilling selectNewFillingData();

    DisplayEntity selectNewLaunchData();

    DisplayEntity selectNewCompletionData();
}
