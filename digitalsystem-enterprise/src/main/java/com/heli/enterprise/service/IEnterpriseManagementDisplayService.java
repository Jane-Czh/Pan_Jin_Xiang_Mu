package com.heli.enterprise.service;

import com.ruoyi.common.core.domain.entity.DisplayEntity;

import java.util.Date;
import java.util.List;

public interface IEnterpriseManagementDisplayService {

    List<DisplayEntity> selectEmployeesNumber(Date startTime, Date endTime);
    List<DisplayEntity> selectEmployeesAVGMonthlyNumber(Date startTime, Date endTime);
}
