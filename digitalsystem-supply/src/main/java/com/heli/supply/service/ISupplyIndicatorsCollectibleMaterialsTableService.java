package com.heli.supply.service;

import com.heli.supply.domain.SupplyIndicatorsCollectibleMaterialsTable;
import com.ruoyi.common.core.domain.R;

import java.io.InputStream;
import java.util.List;


/**
 * 供应科-指标-集采物料字典Service接口
 *
 * @author hong
 * @date 2024-04-28
 */
public interface ISupplyIndicatorsCollectibleMaterialsTableService {

    /**
     * @description: 读取集采物料台账
     * @author: hong
     * @date: 2024/4/29 16:43
     **/
    R<String> readCollectibleMaterialsTableToDB(String fileName, InputStream inputStream);

    /**
     * 查询供应科-指标-集采物料字典
     *
     * @param scmId 供应科-指标-集采物料字典主键
     * @return 供应科-指标-集采物料字典
     */
    public SupplyIndicatorsCollectibleMaterialsTable selectSupplyIndicatorsCollectibleMaterialsTableByScmId(Long scmId);

    /**
     * 查询供应科-指标-集采物料字典列表
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 供应科-指标-集采物料字典集合
     */
    public List<SupplyIndicatorsCollectibleMaterialsTable> selectSupplyIndicatorsCollectibleMaterialsTableList(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable);

    /**
     * 新增供应科-指标-集采物料字典
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 结果
     */
    public int insertSupplyIndicatorsCollectibleMaterialsTable(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable);

    /**
     * 修改供应科-指标-集采物料字典
     *
     * @param supplyIndicatorsCollectibleMaterialsTable 供应科-指标-集采物料字典
     * @return 结果
     */
    public int updateSupplyIndicatorsCollectibleMaterialsTable(SupplyIndicatorsCollectibleMaterialsTable supplyIndicatorsCollectibleMaterialsTable);

    /**
     * 批量删除供应科-指标-集采物料字典
     *
     * @param scmIds 需要删除的供应科-指标-集采物料字典主键集合
     * @return 结果
     */
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmIds(Long[] scmIds);

    /**
     * 删除供应科-指标-集采物料字典信息
     *
     * @param scmId 供应科-指标-集采物料字典主键
     * @return 结果
     */
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmId(Long scmId);
}
