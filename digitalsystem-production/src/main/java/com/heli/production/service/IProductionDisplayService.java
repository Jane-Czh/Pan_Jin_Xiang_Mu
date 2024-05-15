package com.heli.production.service;

import com.ruoyi.common.core.domain.DisplayEntity;

import java.util.Date;
import java.util.List;

/**
 * @description: 生产指标展示接口
 * @author: hong
 * @date: 2024/4/22 10:30
 **/
public interface IProductionDisplayService {

    /**
     * 当月单台非BOM物料费用 指标24
     */
    List<DisplayEntity> selectCurNonBomMaterialCost(Date startTime, Date endTime);

    /**
     * 当月单台低值易耗费用 指标25
     */
    List<DisplayEntity> selectCurLowValueConsumables(Date startTime, Date endTime);

    /**
     * 在制物资年化周转天数 指标29
     */
    List<DisplayEntity> selectInventoryTurnoverdays(Date startTime, Date endTime);

    /**
     * 人均生产台数 指标37
     */
    List<DisplayEntity> selectOutputPercapitacounts(Date startTime, Date endTime);

    /**
     * 人均产值 指标38
     */
    List<DisplayEntity> selectOutputPercapitavalue(Date startTime, Date endTime);

    /**
     * 上线及时率 指标41
     */
    List<DisplayEntity> selectOnlineOntimerate(Date startTime, Date endTime);

    /**
     * 一线当月加班时长 指标48
     */
    List<DisplayEntity> selectOvertimeFrontlinemonth(Date startTime, Date endTime);

    /**
     * 每日上线数和完工数
     */
    List<DisplayEntity> selectDailyLaunchAndCompletion(Date startTime, Date endTime);
    /**
     * 每月上线数和完工数
     */
    List<DisplayEntity> selectMonthlyLaunchAndCompletion(Date startTime, Date endTime);
    /**
     * 每年上线数和完工数
     */
    List<DisplayEntity> selectAnnualLaunchAndCompletion(Date startTime, Date endTime);




}
