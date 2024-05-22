package com.heli.quality.service;

import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IQualityDisplayService {

    /**
     * 质量考核季度排名 指标63
     */
    List<DisplayEntity> selectQuarterlyRank(Date startTime, Date endTime);

    /**
     * 平均无故障时间 指标64
     */
    List<DisplayEntity> selectMeantimeWithoutFailure(Date startTime, Date endTime);

    List<DisplayEntity> selectInTimeReturnRate(Date startTime, Date endTime);

    List<DisplayEntity> selectPartQualificationRate(Date startTime, Date endTime);
}
