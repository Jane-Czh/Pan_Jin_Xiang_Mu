package com.heli.production.mapper;

import com.heli.production.domain.ProductionCommercialVehicleTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductionCommercialVehicleTableMapper {
    int batchInsertProductionCommercialVehicleTable(@Param("productionTableList") List<ProductionCommercialVehicleTable> productionTableList);
}
