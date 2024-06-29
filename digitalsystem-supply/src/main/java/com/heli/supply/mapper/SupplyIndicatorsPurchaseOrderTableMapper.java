package com.heli.supply.mapper;

import java.util.List;

import com.heli.supply.domain.SupplyIndicatorsPurchaseOrderTable;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

/**
 * 供应科-指标-采购订单Mapper接口
 *
 * @author hong
 * @date 2024-04-28
 */
//@DataSource(value = DataSourceType.SLAVE)
public interface SupplyIndicatorsPurchaseOrderTableMapper {

    /**
     * @description: 查询月度管控采购金额
     * @author: hong
     * @date: 2024/4/29 11:04
     **/
    List<DisplayEntity> selectMonthlyControlledMaterialPurchases();

    /**
     * @description: 查询月度采购总金额
     * @author: hong
     * @date: 2024/4/29 11:04
     **/
    List<DisplayEntity> selectMonthlyTotalPurchaseAmount();

    /**
     * @description: 供应订单批量插入数据库
     * @author: hong
     * @date: 2024/4/28 10:55
     **/
    int batchInsertSupplyOrderTable(@Param("supplyOrderTableList") List<SupplyIndicatorsPurchaseOrderTable> supplyOrderTableList);

    /**
     * @description: 重新导入前先清空表
     * @author: hong
     * @date: 2024/4/29 17:48
     **/
    void truncatePurchaseOrderTable();

}
