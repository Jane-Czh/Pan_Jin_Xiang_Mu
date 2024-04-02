package com.heli.financial.service;

import com.heli.financial.domain.DisplayEntity;

import java.util.List;

/**
 * @description: 数据展示接口
 * @author: hong
 * @date: 2024/4/2 14:16
 **/

public interface DisplayService {

    public List<DisplayEntity> selectTotalSalesRevenue();

    public List<DisplayEntity> selectExternalGroupSalesRevenue();

    public List<DisplayEntity> selectTotalVehicleProduction();

    public List<DisplayEntity> selectTotalVehicleSales();

    public List<DisplayEntity> selectNewProductSalesRevenue();

    public List<DisplayEntity> selectSpecialtyProductRevenue();

    public List<DisplayEntity> selectTotalSalesCost();

    public List<DisplayEntity> selectManufacturingExpensesMonth();

    public List<DisplayEntity> selectreserveCarAmount();

    public List<DisplayEntity> selectCapitalTurnoverRate();

    public List<DisplayEntity> selectInventoryTurnoverRate();

    public List<DisplayEntity> selectRawMaterialTurnoverRate();

    public List<DisplayEntity> selectInprogressTurnoverRate();

    public List<DisplayEntity> selectLongEstimatedItems();

    public List<DisplayEntity> selectInprogressDayrevenue();

    public List<DisplayEntity> selectAddedValueMonthly();

}
