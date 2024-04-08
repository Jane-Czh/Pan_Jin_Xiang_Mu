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
public class DisplayServiceImpl implements DisplayService {

    @Autowired
    private DisplayMapper displayMapper;


    @Override
    public List<DisplayEntity> selectMainRevenue(String year) {
        return displayMapper.selectMainRevenue(year);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesRevenue(String year) {
        return displayMapper.selectTotalSalesRevenue(year);
    }

    @Override
    public List<DisplayEntity> selectExternalGroupSalesRevenue(String year) {
        return displayMapper.selectExternalGroupSalesRevenue(year);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleProduction(String year) {
        return displayMapper.selectTotalVehicleProduction(year);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleSales(String year) {
        return displayMapper.selectTotalVehicleSales(year);
    }

    @Override
    public List<DisplayEntity> selectNewProductSalesRevenue(String year) {
        return displayMapper.selectNewProductSalesRevenue(year);
    }

    @Override
    public List<DisplayEntity> selectSpecialtyProductRevenue(String year) {
        return displayMapper.selectSpecialtyProductRevenue(year);
    }

    @Override
    public List<DisplayEntity> COGS(String year) {
        return displayMapper.COGS(year);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesCost(String year) {
        return displayMapper.selectTotalSalesCost(year);
    }

    @Override
    public List<DisplayEntity> selectNetProfit(String year) {
        return displayMapper.selectNetProfit(year);
    }

    @Override
    public List<DisplayEntity> selectManagementExpense(String year) {
        return displayMapper.selectManagementExpense(year);
    }

    @Override
    public List<DisplayEntity> selectRdExpense(String year) {
        return displayMapper.selectRdExpense(year);
    }

    @Override
    public List<DisplayEntity> selectManufacturingExpensesMonth(String year) {
        return displayMapper.selectManufacturingExpensesMonth(year);
    }

    @Override
    public List<DisplayEntity> selectMonthlyRawMaterialInventory(String year) {
        return displayMapper.selectMonthlyRawMaterialInventory(year);
    }

    @Override
    public List<DisplayEntity> selectMonthlyWorkInProgressInventory(String year) {
        return displayMapper.selectMonthlyWorkInProgressInventory(year);
    }

    @Override
    public List<DisplayEntity> selectMonthAmountInStock(String year) {
        return displayMapper.selectMonthAmountInStock(year);
    }

    @Override
    public List<DisplayEntity> selectGrowthRateInventorySales(String year) {
        return displayMapper.selectGrowthRateInventorySales(year);
    }

    @Override
    public List<DisplayEntity> selectTurnoverRateReceivable(String year) {
        return displayMapper.selectTurnoverRateReceivable(year);
    }


    @Override
    public List<DisplayEntity> selectCapitalTurnoverRate(String year) {
        return displayMapper.selectCapitalTurnoverRate(year);
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverRate(String year) {
        return displayMapper.selectInventoryTurnoverRate(year);
    }

    @Override
    public List<DisplayEntity> selectRawMaterialTurnoverRate(String year) {
        return displayMapper.selectRawMaterialTurnoverRate(year);
    }

    @Override
    public List<DisplayEntity> selectInprogressTurnoverRate(String year) {
        return displayMapper.selectInprogressTurnoverRate(year);
    }

    @Override
    public List<DisplayEntity> selectLongEstimatedItems(String year) {
        return displayMapper.selectLongEstimatedItems(year);
    }

    @Override
    public List<DisplayEntity> selectInprogressDayrevenue(String year) {
        return displayMapper.selectInprogressDayrevenue(year);
    }

    @Override
    public List<DisplayEntity> selectMonthlyInventoryTotalAmount(String year) {
        return displayMapper.selectMonthlyInventoryTotalAmount(year);
    }

    @Override
    public List<DisplayEntity> selectAddedValueMonthly(String year) {
        return displayMapper.selectAddedValueMonthly(year);
    }
}
