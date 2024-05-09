package com.heli.enterprise.service;

import com.ruoyi.common.core.domain.DisplayEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IEnterpriseManagementDisplayService {

    List<Map<Date, Object>> selectEmployeesNumber(Date startTime, Date endTime);
    List<Map<Date, Object>> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime);
    List<Map<Date, Object>> selectEmployeesAVGAnnualNumber(Date startTime, Date endTime);
    List<Map<Date, Object>> selectAnnualSalaryRatio(Date startTime, Date endTime);
    List<Map<Date, Object>> selectCumulativeAverageIncome(Date startTime, Date endTime);
    List<Map<Date, Object>> selectMonthlyProductionAVGIncome(Date startTime, Date endTime);
    List<Map<Date, Object>> selectMonthlyFunctionalAVGIncome(Date startTime, Date endTime);
    List<Map<Date, Object>> selectFunctionalDeptOvertimeCost(Date startTime, Date endTime);




//    List<DisplayEntity> selectEmployeesNumber(Date startTime, Date endTime);
//    List<DisplayEntity> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime);
}
