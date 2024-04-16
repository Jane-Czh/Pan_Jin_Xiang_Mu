package com.heli.financial.service.impl;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.mapper.DisplayMapper;
import com.heli.financial.service.DisplayService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01 12:00
 * @Description: 数据展示-服务实现层
 * @Return: 数据列表
 */
@Service
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayMapper displayMapper;


    @Override
    public List<DisplayEntity> selectMainRevenue(Date startTime, Date endTime) {
        return displayMapper.selectMainRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesRevenue(Date startTime, Date endTime) {
        return displayMapper.selectTotalSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectExternalGroupSalesRevenue(Date startTime, Date endTime) {
        return displayMapper.selectExternalGroupSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleProduction(Date startTime, Date endTime) {
        return displayMapper.selectTotalVehicleProduction(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleSales(Date startTime, Date endTime) {
        return displayMapper.selectTotalVehicleSales(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectNewProductSalesRevenue(Date startTime, Date endTime) {
        return displayMapper.selectNewProductSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectSpecialtyProductRevenue(Date startTime, Date endTime) {
        return displayMapper.selectSpecialtyProductRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> COGS(Date startTime, Date endTime) {
        return displayMapper.COGS(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesCost(Date startTime, Date endTime) {
        return displayMapper.selectTotalSalesCost(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectNetProfit(Date startTime, Date endTime) {
        return displayMapper.selectNetProfit(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectManagementExpense(Date startTime, Date endTime) {
        return displayMapper.selectManagementExpense(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectRdExpense(Date startTime, Date endTime) {
        return displayMapper.selectRdExpense(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectManufacturingExpensesMonth(Date startTime, Date endTime) {
        return displayMapper.selectManufacturingExpensesMonth(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyRawMaterialInventory(Date startTime, Date endTime) {
        return displayMapper.selectMonthlyRawMaterialInventory(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyWorkInProgressInventory(Date startTime, Date endTime) {
        return displayMapper.selectMonthlyWorkInProgressInventory(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthAmountInStock(Date startTime, Date endTime) {
        return displayMapper.selectMonthAmountInStock(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectGrowthRateInventorySales(Date startTime, Date endTime) {
        return displayMapper.selectGrowthRateInventorySales(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTurnoverRateReceivable(Date startTime, Date endTime) {
        return displayMapper.selectTurnoverRateReceivable(startTime, endTime);
    }


    @Override
    public List<DisplayEntity> selectCapitalTurnoverRate(Date startTime, Date endTime) {
        return displayMapper.selectCapitalTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverRate(Date startTime, Date endTime) {
        return displayMapper.selectInventoryTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectRawMaterialTurnoverRate(Date startTime, Date endTime) {
        return displayMapper.selectRawMaterialTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInprogressTurnoverRate(Date startTime, Date endTime) {
        return displayMapper.selectInprogressTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectLongEstimatedItems(Date startTime, Date endTime) {
        return displayMapper.selectLongEstimatedItems(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInprogressDayrevenue(Date startTime, Date endTime) {
        return displayMapper.selectInprogressDayrevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyInventoryTotalAmount(Date startTime, Date endTime) {
        return displayMapper.selectMonthlyInventoryTotalAmount(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectAddedValueMonthly(Date startTime, Date endTime) {
        return displayMapper.selectAddedValueMonthly(startTime, endTime);
    }
}
