package com.heli.financial.service.impl;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.mapper.DisplayMapper;
import com.heli.financial.service.DisplayService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.financial.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-01  12:00
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayMapper displayMapper;


    @Override
    public List<DisplayEntity> selectTotalSalesRevenue(DisplayEntity displayEntity) {
        return displayMapper.selectTotalSalesRevenue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectExternalGroupSalesRevenue(DisplayEntity displayEntity) {
        return displayMapper.selectExternalGroupSalesRevenue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleProduction(DisplayEntity displayEntity) {
        return displayMapper.selectTotalVehicleProduction(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleSales(DisplayEntity displayEntity) {
        return displayMapper.selectTotalVehicleSales(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectNewProductSalesRevenue(DisplayEntity displayEntity) {
        return displayMapper.selectNewProductSalesRevenue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectSpecialtyProductRevenue(DisplayEntity displayEntity) {
        return displayMapper.selectSpecialtyProductRevenue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesCost(DisplayEntity displayEntity) {
        return displayMapper.selectTotalSalesCost(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectManufacturingExpensesMonth(DisplayEntity displayEntity) {
        return displayMapper.selectManufacturingExpensesMonth(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectMonthlyInventoryStockValue(DisplayEntity displayEntity) {
        return displayMapper.selectMonthlyInventoryStockValue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectCapitalTurnoverRate(DisplayEntity displayEntity) {
        return displayMapper.selectCapitalTurnoverRate(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverRate(DisplayEntity displayEntity) {
        return displayMapper.selectInventoryTurnoverRate(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectRawMaterialTurnoverRate(DisplayEntity displayEntity) {
        return displayMapper.selectRawMaterialTurnoverRate(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectInprogressTurnoverRate(DisplayEntity displayEntity) {
        return displayMapper.selectInprogressTurnoverRate(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectLongEstimatedItems(DisplayEntity displayEntity) {
        return displayMapper.selectLongEstimatedItems(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectInprogressDayrevenue(DisplayEntity displayEntity) {
        return displayMapper.selectInprogressDayrevenue(displayEntity);
    }

    @Override
    public List<DisplayEntity> selectAddedValueMonthly(DisplayEntity displayEntity) {
        return displayMapper.selectAddedValueMonthly(displayEntity);
    }
}
