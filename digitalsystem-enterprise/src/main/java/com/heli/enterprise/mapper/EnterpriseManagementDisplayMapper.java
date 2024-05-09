package com.heli.enterprise.mapper;

import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EnterpriseManagementDisplayMapper {
    List<Map<Date, Object>> selectEmployeesNumber(@Param("startTime") Date startTime,
                                                  @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectEmployeesAVGMonthlyNumber(@Param("startTime") Date startTime,
                                                            @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectEmployeesAVGAnnualNumber(@Param("startTime") Date startTime,
                                                           @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectAnnualSalaryRatio(@Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectCumulativeAverageIncome(@Param("startTime") Date startTime,
                                                          @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectMonthlyProductionAVGIncome(@Param("startTime") Date startTime,
                                                             @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectMonthlyFunctionalAVGIncome(@Param("startTime") Date startTime,
                                                             @Param("endTime") Date endTime);

    List<Map<Date, Object>> selectFunctionalDeptOvertimeCost(@Param("startTime") Date startTime,
                                                             @Param("endTime") Date endTime);


//    List<DisplayEntity> selectEmployeesNumber(@Param("startTime") Date startTime,
//                                              @Param("endTime") Date endTime);

//    List<DisplayEntity> selectEmployeesAVGMonthlyNumber(@Param("startTime") Date startTime,
//                                                             @Param("endTime") Date endTime);
}
