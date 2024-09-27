package com.heli.safety.service;

import com.heli.safety.domain.SafetyEp;
import com.ruoyi.common.core.domain.DisplayEntity;

import java.util.Date;
import java.util.List;

public interface ISafetyEpDisplayService {


    /**
     * 当月度设备维修总费用 指标23
     */
    List<DisplayEntity> selectCurEquipmentMaintenanceCost(Date startTime, Date endTime);

    /**
     * 重点设备故障率 指标45
     */
    List<DisplayEntity> selectKeyEquipmentFailureRate(Date startTime, Date endTime);

    /**
     * 当月设备故障累计停产时间 指标46
     */
    List<DisplayEntity> selectCurEquipmentFailuresTotaltime(Date startTime, Date endTime);

    /**
     * 主要设备故障总次数 指标67
     */
    List<DisplayEntity> selectKeyEquipmentTotalFailureCount(Date startTime, Date endTime);

    /**
     * 与其他的展示方式不同-设备故障类别次数分布图 指标68
     */
    List<DisplayEntity> selectOutputPercapitavalue(Date startTime, Date endTime);
//    List<DisplayEntity> selectOutputPercapitavalue(Date startTime, Date endTime);

    /**
     * 当月设备维修成本-替换件成本 指标69
     */
    List<DisplayEntity> selectCurEquipmentReplacementCost(Date startTime, Date endTime);

    SafetyEp selectNewData();

    Date selectMaxDateFromFailureCategoryDistribution();
}
