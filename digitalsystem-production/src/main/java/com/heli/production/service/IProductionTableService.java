package com.heli.production.service;

import com.heli.production.domain.ProductionTable;
import com.ruoyi.common.core.domain.R;

import java.io.InputStream;
import java.util.List;


/**
 * @description:
 * @author: hong
 * @date: 2024/4/11 20:15
 **/
public interface IProductionTableService {
    public R<String> readProductionExcelToDB(String fileName, InputStream inputStream);


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
    public List<ProductionTable> selectProductionTableList();
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
     * 批量删除商品车台账
     *
     * @param mcvIds 需要删除的商品车台账主键集合
     * @return 结果
     */
    public int deleteProductionTableByMcvIds(Long[] mcvIds);

    /**
     * 删除商品车台账信息
     *
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    public int deleteProductionTableByMcvId(Long mcvId);
}

