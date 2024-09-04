package com.heli.supply.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.util.ListUtils;
import com.heli.supply.domain.SupplyPurchaseorderTable;
import com.heli.supply.utils.ExcelUtils;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.supply.mapper.SupplyMaterialCategoryDictionaryTableMapper;
import com.heli.supply.domain.SupplyMaterialCategoryDictionaryTable;
import com.heli.supply.service.ISupplyMaterialCategoryDictionaryTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料类别字典存储Service业务层处理
 * 
 * @author Teandron
 * @date 2024-07-29
 */
@Service
public class SupplyMaterialCategoryDictionaryTableServiceImpl implements ISupplyMaterialCategoryDictionaryTableService 
{
    @Autowired
    private SupplyMaterialCategoryDictionaryTableMapper supplyMaterialCategoryDictionaryTableMapper;

    private static final int BATCH_COUNT = 5000; // 批处理数量
    //缓存一批数据
    private List<SupplyMaterialCategoryDictionaryTable> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void importInterests(MultipartFile excelFile) throws IOException {

        InputStream is = null;
        try {
            List<SupplyMaterialCategoryDictionaryTable> SupplyMaterialCategoryDictionaryTables = ExcelUtils.parseExcel2SupplyMaterialCategoryDictionaryTable(excelFile);

            int i = 0;

            supplyMaterialCategoryDictionaryTableMapper.deleteAll();

            while (i < SupplyMaterialCategoryDictionaryTables.size()){
                cachedDataList.add(SupplyMaterialCategoryDictionaryTables.get(i));
                if (cachedDataList.size() >= BATCH_COUNT) {
                    supplyMaterialCategoryDictionaryTableMapper.batchInsert(cachedDataList);
                    cachedDataList.clear();
                    System.out.println("插入一轮");
                }
                i++;
            }

            if (cachedDataList.size() > 0){
                supplyMaterialCategoryDictionaryTableMapper.batchInsert(cachedDataList);
                cachedDataList.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 查询物料类别字典存储
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 物料类别字典存储
     */
    @Override
    public SupplyMaterialCategoryDictionaryTable selectSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId)
    {
        return supplyMaterialCategoryDictionaryTableMapper.selectSupplyMaterialCategoryDictionaryTableBySmcdId(smcdId);
    }

    /**
     * 查询物料类别字典存储列表
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 物料类别字典存储
     */
    @Override
    public List<SupplyMaterialCategoryDictionaryTable> selectSupplyMaterialCategoryDictionaryTableList(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        return supplyMaterialCategoryDictionaryTableMapper.selectSupplyMaterialCategoryDictionaryTableList(supplyMaterialCategoryDictionaryTable);
    }

    /**
     * 新增物料类别字典存储
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 结果
     */
    @Override
    public int insertSupplyMaterialCategoryDictionaryTable(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        return supplyMaterialCategoryDictionaryTableMapper.insertSupplyMaterialCategoryDictionaryTable(supplyMaterialCategoryDictionaryTable);
    }

    /**
     * 修改物料类别字典存储
     * 
     * @param supplyMaterialCategoryDictionaryTable 物料类别字典存储
     * @return 结果
     */
    @Override
    public int updateSupplyMaterialCategoryDictionaryTable(SupplyMaterialCategoryDictionaryTable supplyMaterialCategoryDictionaryTable)
    {
        return supplyMaterialCategoryDictionaryTableMapper.updateSupplyMaterialCategoryDictionaryTable(supplyMaterialCategoryDictionaryTable);
    }

    /**
     * 批量删除物料类别字典存储
     * 
     * @param smcdIds 需要删除的物料类别字典存储主键
     * @return 结果
     */
    @Override
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdIds(Long[] smcdIds)
    {
        return supplyMaterialCategoryDictionaryTableMapper.deleteSupplyMaterialCategoryDictionaryTableBySmcdIds(smcdIds);
    }

    /**
     * 删除物料类别字典存储信息
     * 
     * @param smcdId 物料类别字典存储主键
     * @return 结果
     */
    @Override
    public int deleteSupplyMaterialCategoryDictionaryTableBySmcdId(Long smcdId)
    {
        return supplyMaterialCategoryDictionaryTableMapper.deleteSupplyMaterialCategoryDictionaryTableBySmcdId(smcdId);
    }
}
