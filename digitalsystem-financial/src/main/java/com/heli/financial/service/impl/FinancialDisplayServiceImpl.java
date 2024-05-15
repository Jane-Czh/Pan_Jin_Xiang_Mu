package com.heli.financial.service.impl;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.heli.financial.mapper.FinancialDisplayMapper;
import com.heli.financial.service.IFinancialDisplayService;
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
public class FinancialDisplayServiceImpl implements IFinancialDisplayService {

    @Autowired
    private FinancialDisplayMapper financialDisplayMapper;


    @Override
    public List<DisplayEntity> selectMainRevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectMainRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesRevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectTotalSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectExternalGroupSalesRevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectExternalGroupSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleProduction(Date startTime, Date endTime) {
        return financialDisplayMapper.selectTotalVehicleProduction(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalVehicleSales(Date startTime, Date endTime) {
        return financialDisplayMapper.selectTotalVehicleSales(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectNewProductSalesRevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectNewProductSalesRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectSpecialtyProductRevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectSpecialtyProductRevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> COGS(Date startTime, Date endTime) {
        return financialDisplayMapper.COGS(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTotalSalesCost(Date startTime, Date endTime) {
        return financialDisplayMapper.selectTotalSalesCost(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectNetProfit(Date startTime, Date endTime) {
        return financialDisplayMapper.selectNetProfit(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectManagementExpense(Date startTime, Date endTime) {
        return financialDisplayMapper.selectManagementExpense(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectRdExpense(Date startTime, Date endTime) {
        return financialDisplayMapper.selectRdExpense(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectManufacturingExpensesMonth(Date startTime, Date endTime) {
        return financialDisplayMapper.selectManufacturingExpensesMonth(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyRawMaterialInventory(Date startTime, Date endTime) {
        return financialDisplayMapper.selectMonthlyRawMaterialInventory(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyWorkInProgressInventory(Date startTime, Date endTime) {
        return financialDisplayMapper.selectMonthlyWorkInProgressInventory(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthAmountInStock(Date startTime, Date endTime) {
        return financialDisplayMapper.selectMonthAmountInStock(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectGrowthRateInventoryAndSales(Date startTime, Date endTime) {
        return financialDisplayMapper.selectGrowthRateInventoryAndSales(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectTurnoverRateReceivable(Date startTime, Date endTime) {
        return financialDisplayMapper.selectTurnoverRateReceivable(startTime, endTime);
    }


    @Override
    public List<DisplayEntity> selectCapitalTurnoverRate(Date startTime, Date endTime) {
        return financialDisplayMapper.selectCapitalTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInventoryTurnoverRate(Date startTime, Date endTime) {
        return financialDisplayMapper.selectInventoryTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectRawMaterialTurnoverRate(Date startTime, Date endTime) {
        return financialDisplayMapper.selectRawMaterialTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInprogressTurnoverRate(Date startTime, Date endTime) {
        return financialDisplayMapper.selectInprogressTurnoverRate(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectLongEstimatedItems(Date startTime, Date endTime) {
        return financialDisplayMapper.selectLongEstimatedItems(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectInprogressDayrevenue(Date startTime, Date endTime) {
        return financialDisplayMapper.selectInprogressDayrevenue(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectMonthlyInventoryTotalAmount(Date startTime, Date endTime) {
        return financialDisplayMapper.selectMonthlyInventoryTotalAmount(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectAddedValueMonthly(Date startTime, Date endTime) {
        return financialDisplayMapper.selectAddedValueMonthly(startTime, endTime);
    }
}
