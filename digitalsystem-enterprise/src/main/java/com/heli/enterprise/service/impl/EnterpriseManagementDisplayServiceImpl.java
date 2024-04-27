package com.heli.enterprise.service.impl;

import com.heli.enterprise.mapper.EnterpriseManagementDisplayMapper;
import com.heli.enterprise.service.IEnterpriseManagementDisplayService;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnterpriseManagementDisplayServiceImpl implements IEnterpriseManagementDisplayService {
    @Autowired
    private EnterpriseManagementDisplayMapper enterpriseManagementDisplayMapper;

    @Override
    public List<DisplayEntity> selectEmployeesNumber(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectEmployeesNumber(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime) {
        return enterpriseManagementDisplayMapper.selectEmployeesAVGMonthlyNumber(startTime, endTime);
    }

}
