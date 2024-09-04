package com.heli.supply.service;

import java.io.IOException;
import java.util.List;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 采购订单导入Service接口
 * 
 * @author Teandron
 * @date 2024-07-30
 */
public interface ISupplyPurchaseorderTableService 
{

    /**
     * 导入excel
     *
     *
     *
     */
    public void importInterests(MultipartFile excelFile) throws IOException;

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
     * 批量删除采购订单导入
     * 
     * @param spIds 需要删除的采购订单导入主键集合
     * @return 结果
     */
    public int deleteSupplyPurchaseorderTableBySpIds(Long[] spIds);

    /**
     * 删除采购订单导入信息
     * 
     * @param spId 采购订单导入主键
     * @return 结果
     */
    public int deleteSupplyPurchaseorderTableBySpId(Long spId);
}
