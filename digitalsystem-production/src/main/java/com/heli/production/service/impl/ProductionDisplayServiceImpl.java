package com.heli.production.service.impl;

import com.heli.production.mapper.ProductionDisplayMapper;
import com.heli.production.service.IProductionDisplayService;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.production.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-22  10:29
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class ProductionDisplayServiceImpl implements IProductionDisplayService {

    @Autowired
    private ProductionDisplayMapper productionDisplayMapper;

    @Override
    public List<DisplayEntity> selectCurNonBomMaterialCost(Date startTime, Date endTime) {
        return productionDisplayMapper.selectCurNonBomMaterialCost(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectCurLowValueConsumables(Date startTime, Date endTime) {
        return productionDisplayMapper.selectCurLowValueConsumables(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverdays(Date startTime, Date endTime) {
        return productionDisplayMapper.selectInventoryTurnoverdays(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectOutputPercapitacounts(Date startTime, Date endTime) {
        return productionDisplayMapper.selectOutputPercapitacounts(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectOutputPercapitavalue(Date startTime, Date endTime) {
        return productionDisplayMapper.selectOutputPercapitavalue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectOnlineOntimerate(Date startTime, Date endTime) {
        return productionDisplayMapper.selectOnlineOntimerate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectOvertimeFrontlinemonth(Date startTime, Date endTime) {
        return productionDisplayMapper.selectOvertimeFrontlinemonth(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectDailyLaunchAndCompletion(Date startTime, Date endTime) {
        return productionDisplayMapper.selectDailyLaunchAndCompletion(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyLaunchAndCompletion(Date startTime, Date endTime) {
        return productionDisplayMapper.selectMonthlyLaunchAndCompletion(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectAnnualLaunchAndCompletion(Date startTime, Date endTime) {
        return productionDisplayMapper.selectAnnualLaunchAndCompletion(startTime, endTime);
    }

}
