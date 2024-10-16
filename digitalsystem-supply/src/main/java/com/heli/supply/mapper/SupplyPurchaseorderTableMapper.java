package com.heli.supply.mapper;

import java.util.List;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import org.apache.ibatis.annotations.Param;

/**
 * 采购订单导入Mapper接口
 * 
 * @author Teandron
 * @date 2024-07-30
 */
public interface SupplyPurchaseorderTableMapper 
{
    /**
     * 查询采购订单导入
     * 
     * @param spId 采购订单导入主键
     * @return 采购订单导入
     */
    public SupplyPurchaseorderTable selectSupplyPurchaseorderTableBySpId(Long spId);

    /**
     * 查询采购订单导入列表
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 采购订单导入集合
     */
    public List<SupplyPurchaseorderTable> selectSupplyPurchaseorderTableList(SupplyPurchaseorderTable supplyPurchaseorderTable);

    /**
     * 新增采购订单导入
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 结果
     */
    public int insertSupplyPurchaseorderTable(SupplyPurchaseorderTable supplyPurchaseorderTable);

    /**
     * 修改采购订单导入
     * 
     * @param supplyPurchaseorderTable 采购订单导入
     * @return 结果
     */
    public int updateSupplyPurchaseorderTable(SupplyPurchaseorderTable supplyPurchaseorderTable);

    /**
     * 删除采购订单导入
     * 
     * @param spId 采购订单导入主键
     * @return 结果
     */
    public int deleteSupplyPurchaseorderTableBySpId(Long spId);

    /**
     * 批量删除采购订单导入
     * 
     * @param spIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyPurchaseorderTableBySpIds(Long[] spIds);

    public void batchInsert(@Param("list") List<SupplyPurchaseorderTable> supplyPurchaseorderTable);


    public Double calculateSupplierRatioByQuantity(@Param("materialClass") String materialClass, @Param("supplierCode") String supplierCode);

    public Double calculateSupplierRatioByNetValueOrder(@Param("materialClass") String materialClass, @Param("supplierCode") String supplierCode);

    public void deleteAll();
}
