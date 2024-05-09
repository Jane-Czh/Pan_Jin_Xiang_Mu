package com.heli.quality.mapper;

import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface QualityDisplayMapper {

    //    质量考核季度排名 指标63
    List<DisplayEntity> selectQuarterlyRank(@Param("startTime") Date startTime,
                                            @Param("endTime") Date endTime);

    //    平均无故障时间 指标64
    List<DisplayEntity> selectMeantimeWithoutFailure(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);


}
