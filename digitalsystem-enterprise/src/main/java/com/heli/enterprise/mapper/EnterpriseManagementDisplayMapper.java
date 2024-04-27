package com.heli.enterprise.mapper;

import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnterpriseManagementDisplayMapper {
    List<DisplayEntity> selectEmployeesNumber(@Param("startTime") Date startTime,
                                              @Param("endTime") Date endTime);

    List<DisplayEntity> selectEmployeesAVGMonthlyNumber(@Param("startTime") Date startTime,
                                                        @Param("endTime") Date endTime);
}
