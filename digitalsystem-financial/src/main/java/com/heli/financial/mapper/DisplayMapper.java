package com.heli.financial.mapper;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;

import java.util.List;

public interface DisplayMapper {

    public List<DisplayEntity> selectTotalSalesRevenue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectExternalGroupSalesRevenue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectTotalVehicleProduction(DisplayEntity displayEntity);
    public List<DisplayEntity> selectTotalVehicleSales(DisplayEntity displayEntity);
    public List<DisplayEntity> selectNewProductSalesRevenue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectSpecialtyProductRevenue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectTotalSalesCost(DisplayEntity displayEntity);
    public List<DisplayEntity> selectManufacturingExpensesMonth(DisplayEntity displayEntity);
    public List<DisplayEntity> selectMonthlyInventoryStockValue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectCapitalTurnoverRate(DisplayEntity displayEntity);
    public List<DisplayEntity> selectInventoryTurnoverRate(DisplayEntity displayEntity);
    public List<DisplayEntity> selectRawMaterialTurnoverRate(DisplayEntity displayEntity);
    public List<DisplayEntity> selectInprogressTurnoverRate(DisplayEntity displayEntity);
    public List<DisplayEntity> selectLongEstimatedItems(DisplayEntity displayEntity);
    public List<DisplayEntity> selectInprogressDayrevenue(DisplayEntity displayEntity);
    public List<DisplayEntity> selectAddedValueMonthly(DisplayEntity displayEntity);


}
