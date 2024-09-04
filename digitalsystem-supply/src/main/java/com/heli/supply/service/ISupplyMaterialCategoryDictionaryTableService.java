package com.heli.supply.service;

import java.io.IOException;
import java.util.List;
import com.heli.supply.domain.SupplyMaterialCategoryDictionaryTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料类别字典存储Service接口
 * 
 * @author Teandron
 * @date 2024-07-29
 */
public interface ISupplyMaterialCategoryDictionaryTableService 
{

    /**
     * 导入excel
     *
     *
     *
     */
    public void importInterests(MultipartFile excelFile) throws IOException;


    /**
     * 查询物料类别字典存储
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 物料类别字典存储
     */
    public SupplyMaterialCategoryDictionaryTable selectSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId);

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
     * 批量删除物料类别字典存储
     * 
     * @param smcdIds 需要删除的物料类别字典存储主键集合
     * @return 结果
     */
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdIds(Long[] smcdIds);

    /**
     * 删除物料类别字典存储信息
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 结果
     */
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId);
}
