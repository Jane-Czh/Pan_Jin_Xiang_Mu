package com.heli.safety.service.impl;

import com.heli.safety.mapper.SafetyEpDisplayMapper;
import com.heli.safety.service.ISafetyEpDisplayService;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.safety.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-22  13:45
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class SafetyEpDisplayServiceImpl implements ISafetyEpDisplayService {

    @Autowired
    private SafetyEpDisplayMapper safetyEpDisplayMapper;

    @Override
    public List<DisplayEntity> selectCurEquipmentMaintenanceCost(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectCurEquipmentMaintenanceCost(startTime,endTime);
    }

    @Override
    public List<DisplayEntity> selectKeyEquipmentFailureRate(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectKeyEquipmentFailureRate(startTime,endTime);
    }

    @Override
    public List<DisplayEntity> selectCurEquipmentFailuresTotaltime(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectCurEquipmentFailuresTotaltime(startTime,endTime);
    }

    @Override
    public List<DisplayEntity> selectKeyEquipmentTotalFailureCount(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectKeyEquipmentTotalFailureCount(startTime,endTime);
    }

    @Override
    public List<DisplayEntity> selectOutputPercapitavalue(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectOutputPercapitavalue(startTime,endTime);
    }

    @Override
    public List<DisplayEntity> selectCurEquipmentReplacementCost(Date startTime, Date endTime) {
        return safetyEpDisplayMapper.selectCurEquipmentReplacementCost(startTime,endTime);
    }
}
