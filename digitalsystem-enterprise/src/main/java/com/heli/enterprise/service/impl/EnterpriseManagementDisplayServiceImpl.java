package com.heli.enterprise.service.impl;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsDailyClearingSettlement;
import com.heli.enterprise.domain.EnterpriseManagementIndicatorsManagement;
import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import com.heli.enterprise.mapper.EnterpriseManagementDisplayMapper;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.heli.enterprise.service.IEnterpriseManagementIndicatorsDailyClearingSettlementService;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EnterpriseManagementDisplayServiceImpl implements IEnterpriseManagementDisplayService {
    @Autowired
    private EnterpriseManagementDisplayMapper enterpriseManagementDisplayMapper;
    @Autowired
    private IEnterpriseManagementIndicatorsDailyClearingSettlementService dailyClearingSettlementService;

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

    @Override
    public List<EnterpriseManagementIndicatorsDailyClearingSettlement> selectDailyClearingSettlement(Date startTime, Date endTime) {
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> list = enterpriseManagementDisplayMapper.selectDailyClearingSettlement(startTime, endTime);

        EnterpriseManagementIndicatorsDailyClearingSettlement dailyClearingSettlement = new EnterpriseManagementIndicatorsDailyClearingSettlement();

        // 获取当前Date的1月1日
        Date date = new Date(DateUtils.getYear(startTime) - 1900, 0, 1);
        log.info("date: " + date);
        dailyClearingSettlement.setFlag(1);
        List<EnterpriseManagementIndicatorsDailyClearingSettlement> target = dailyClearingSettlementService.selectEnterpriseManagementIndicatorsDailyClearingSettlementList(dailyClearingSettlement);

        list.add(target.get(0));
        return list;
    }

    @Override
    public List<EnterpriseManagementIndicatorsManagement> selectManagement(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectManagement(startTime,endTime);
    }

    @Override
    public EnterpriseManagementMonthlyData selectSalaryNewData() {
        return enterpriseManagementDisplayMapper.selectSalaryNewData();
    }

    @Override
    public EnterpriseManagementMonthlyData selectMonthlyNewData() {
        return enterpriseManagementDisplayMapper.selectMonthlyNewData();
    }

}
