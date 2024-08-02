package com.heli.supply.mapper;

import java.util.List;
import com.heli.supply.domain.SupplyMaterialCategoryDictionaryTable;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import org.apache.ibatis.annotations.Param;

/**
 * 物料类别字典存储Mapper接口
 * 
 * @author Teandron
 * @date 2024-07-29
 */
public interface SupplyMaterialCategoryDictionaryTableMapper 
{
    /**
     * 查询物料类别字典存储
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 物料类别字典存储
     */
    public SupplyMaterialCategoryDictionaryTable selectSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId);

    public String selectSupplyMaterialCategoryDictionaryTableByMaterialNumber(String MaterialNumber);

    /**
     * 查询物料类别字典存储列表
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 物料类别字典存储集合
     */
    public List<SupplyMaterialCategoryDictionaryTable> selectSupplyMaterialCategoryDictionaryTableList(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable);

    /**
     * 新增物料类别字典存储
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 结果
     */
    public int insertSupplyMaterialCategoryDictionaryTable(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable);

    /**
     * 修改物料类别字典存储
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 结果
     */
    public int updateSupplyMaterialCategoryDictionaryTable(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable);

    /**
     * 删除物料类别字典存储
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 结果
     */
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId);

    /**
     * 批量删除物料类别字典存储
     * 
     * @param smcdIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdIds(Long[] smcdIds);

    public void batchInsert(@Param("list") List<SupplyMaterialCategoryDictionaryTable> supplyMaterialCategoryDictionaryTables);

    public void deleteAll();

}
