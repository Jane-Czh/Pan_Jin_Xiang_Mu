package com.heli.enterprise.service;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.apache.ibatis.annotations.Param;

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


    List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectDailyClearingSettlement(Date startTime, Date endTime);

    List<EnterpriseManagementIndicatorsManagement> selectManagement(Date startTime, Date endTime);

    EnterpriseManagementMonthlyData selectSalaryNewData();

    EnterpriseManagementMonthlyData selectMonthlyNewData();
}
