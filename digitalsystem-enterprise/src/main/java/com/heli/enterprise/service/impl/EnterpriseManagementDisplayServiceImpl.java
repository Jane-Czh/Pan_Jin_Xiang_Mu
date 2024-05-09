package com.heli.enterprise.service.impl;

import com.heli.enterprise.mapper.EnterpriseManagementDisplayMapper;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.ruoyi.common.core.domain.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EnterpriseManagementDisplayServiceImpl implements IEnterpriseManagementDisplayService {
    @Autowired
    private EnterpriseManagementDisplayMapper enterpriseManagementDisplayMapper;

    @Override
    public List<Map<Date, Object>> selectEmployeesNumber(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectEmployeesNumber(startTime, endTime);
    }
//    @Override
//    public List<DisplayEntity> selectEmployeesNumber(Date startTime, Date endTime) {
//        return enterpriseManagementDisplayMapper.selectEmployeesNumber(startTime, endTime);
//    }

//    @Override
//    public List<DisplayEntity> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime) {
//        return enterpriseManagementDisplayMapper.selectEmployeesAVGMonthlyNumber(startTime, endTime);
//    }

    @Override
    public List<Map<Date, Object>> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectEmployeesAVGMonthlyNumber(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectEmployeesAVGAnnualNumber(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectEmployeesAVGAnnualNumber(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectAnnualSalaryRatio(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectAnnualSalaryRatio(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectCumulativeAverageIncome(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectCumulativeAverageIncome(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectMonthlyProductionAVGIncome(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectMonthlyProductionAVGIncome(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectMonthlyFunctionalAVGIncome(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectMonthlyFunctionalAVGIncome(startTime, endTime);
    }

    @Override
    public List<Map<Date, Object>> selectFunctionalDeptOvertimeCost(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectFunctionalDeptOvertimeCost(startTime, endTime);
    }

}
