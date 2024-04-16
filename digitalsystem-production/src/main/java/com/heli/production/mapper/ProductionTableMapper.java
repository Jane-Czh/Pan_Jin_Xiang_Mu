package com.heli.production.mapper;

import com.heli.production.domain.ProductionCountNumberEntity;
import com.heli.production.domain.ProductionTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 商品车台账Mapper接口,统计每日产量
 * @author: hong
 * @date: 2024/4/11 20:14

 **/
public interface ProductionTableMapper {

    List<ProductionCountNumberEntity> selectLaunchNumGroupByDate();

    List<ProductionCountNumberEntity> selectCompletionNumGroupByDate();

    int batchInsertOrUpdateLaunch(@Param("productionCountNumberEntityList") List<ProductionCountNumberEntity> productionCountNumberEntityList);
    int batchInsertOrUpdateCompletion(@Param("productionCountNumberEntityList") List<ProductionCountNumberEntity> productionCountNumberEntityList);

    int batchInsertProductionTable(@Param("productionTableList") List<ProductionTable> productionTableList);

    /**
     * 查询商品车台账
     *
     * @param mcvId 商品车台账主键
     * @return 商品车台账
     */
    public ProductionTable selectProductionTableByMcvId(Long mcvId);

    /**
     * 查询商品车台账列表
     *
     * @param productionTable 商品车台账
     * @return 商品车台账集合
     */
    public List<ProductionTable> selectProductionTableList(ProductionTable productionTable);

    /**
     * 新增商品车台账
     *
     * @param productionTable 商品车台账
     * @return 结果
     */
    public int insertProductionTable(ProductionTable productionTable);

    /**
     * 修改商品车台账
     *
     * @param productionTable 商品车台账
     * @return 结果
     */
    public int updateProductionTable(ProductionTable productionTable);

    /**
     * 删除商品车台账
     *
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    public int deleteProductionTableByMcvId(Long mcvId);

    /**
     * 批量删除商品车台账
     *
     * @param mcvIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionTableByMcvIds(Long[] mcvIds);
}