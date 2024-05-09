package com.heli.quality.service.impl;

import com.heli.quality.mapper.QualityDisplayMapper;
import com.heli.quality.service.IQualityDisplayService;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
     * 质量考核季度排名 指标63
     */
    @Override
    public List<DisplayEntity> selectQuarterlyRank(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectQuarterlyRank(startTime,endTime);
    }

    /**
     * 平均无故障时间 指标64
     */
    @Override
    public List<DisplayEntity> selectMeantimeWithoutFailure(Date startTime, Date endTime) {
        return qualityDisplayMapper.selectMeantimeWithoutFailure(startTime,endTime);
    }
}
