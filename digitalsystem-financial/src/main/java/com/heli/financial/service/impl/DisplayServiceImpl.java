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
 * @Author: hong
 * @CreateTime: 2024-04-01 12:00
 * @Description: 数据展示-服务实现层
 * @Return: 数据列表
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayMapper displayMapper;


    @Override
    public List<DisplayEntity> selectTotalSalesRevenue() {
        return displayMapper.selectTotalSalesRevenue();
    }

    @Override
    public List<DisplayEntity> selectExternalGroupSalesRevenue() {
        return displayMapper.selectExternalGroupSalesRevenue();
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleProduction() {
        return displayMapper.selectTotalVehicleProduction();
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleSales() {
        return displayMapper.selectTotalVehicleSales();
    }

    @Override
    public List<DisplayEntity> selectNewProductSalesRevenue() {
        return displayMapper.selectNewProductSalesRevenue();
    }

    @Override
    public List<DisplayEntity> selectSpecialtyProductRevenue() {
        return displayMapper.selectSpecialtyProductRevenue();
    }

    @Override
    public List<DisplayEntity> selectTotalSalesCost() {
        return displayMapper.selectTotalSalesCost();
    }

    @Override
    public List<DisplayEntity> selectManufacturingExpensesMonth() {
        return displayMapper.selectManufacturingExpensesMonth();
    }

    @Override
    public List<DisplayEntity> selectreserveCarAmount() {
        return displayMapper.selectreserveCarAmount();
    }

    @Override
    public List<DisplayEntity> selectCapitalTurnoverRate() {
        return displayMapper.selectCapitalTurnoverRate();
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverRate() {
        return displayMapper.selectInventoryTurnoverRate();
    }

    @Override
    public List<DisplayEntity> selectRawMaterialTurnoverRate() {
        return displayMapper.selectRawMaterialTurnoverRate();
    }

    @Override
    public List<DisplayEntity> selectInprogressTurnoverRate() {
        return displayMapper.selectInprogressTurnoverRate();
    }

    @Override
    public List<DisplayEntity> selectLongEstimatedItems() {
        return displayMapper.selectLongEstimatedItems();
    }

    @Override
    public List<DisplayEntity> selectInprogressDayrevenue() {
        return displayMapper.selectInprogressDayrevenue();
    }

    @Override
    public List<DisplayEntity> selectAddedValueMonthly() {
        return displayMapper.selectAddedValueMonthly();
    }
}
