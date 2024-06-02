package com.heli.supply.mapper;

import com.heli.supply.domain.SupplyIndicatorsCollectibleMaterialsTable;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 供应科-指标-集采物料字典Mapper接口
 * @author hong
 * @date 2024-04-28
 */
//@DataSource(value = DataSourceType.SLAVE)
public interface SupplyIndicatorsCollectibleMaterialsTableMapper {

    /**
     * @description: 集采物料台账-批量插入数据库
     * @author: hong
     * @date: 2024/4/28 10:55
     **/
    int batchInsertCollectibleMaterialsTable(@Param("collectibleMaterialsTableList") List<SupplyIndicatorsCollectibleMaterialsTable> collectibleMaterialsTableList);

    /**
     * @description: 重新导入前先清空表
     * @author: hong
     * @date: 2024/4/29 17:48
     **/
    void truncateCollectibleMaterialsTable();

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
     * 删除供应科-指标-集采物料字典
     *
     * @param scmId 供应科-指标-集采物料字典主键
     * @return 结果
     */
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmId(Long scmId);

    /**
     * 批量删除供应科-指标-集采物料字典
     *
     * @param scmIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplyIndicatorsCollectibleMaterialsTableByScmIds(Long[] scmIds);
}
