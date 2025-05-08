package com.ruoyi.market.mapper;

import java.util.List;

import com.ruoyi.market.domain.AverageResult;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketSalesTable;

/**
 * 商品车台账Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
public interface MarketCommercialVehicleTableMapper 
{
    /**
     * 查询商品车台账
     * 
     * @param mcvId 商品车台账主键
     * @return 商品车台账
     */
    public MarketCommercialVehicleTable selectMarketCommercialVehicleTableByMcvId(Long mcvId);

    /**
     * 查询商品车台账列表
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 商品车台账集合
     */
    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList(MarketCommercialVehicleTable marketCommercialVehicleTable);
    //无参数方法
    public List<MarketCommercialVehicleTable> selectMarketCommercialVehicleTableList1();
    //查询数据库最后一条数据id
    public Long selectLastId();
    /**
     * 新增商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    public int insertMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable);
    /**
     * 批量导入商品车台账
     *
     */
    void batchInsertMarketCommercialVehicleTable(List<MarketCommercialVehicleTable> marketCommercialVehicleTables);


    /**
     * 修改商品车台账
     * 
     * @param marketCommercialVehicleTable 商品车台账
     * @return 结果
     */
    public int updateMarketCommercialVehicleTable(MarketCommercialVehicleTable marketCommercialVehicleTable);

    /**
     * 删除商品车台账
     * 
     * @param mcvId 商品车台账主键
     * @return 结果
     */
    public int deleteMarketCommercialVehicleTableByMcvId(Long mcvId);

    /**
     * 批量删除商品车台账
     * 
     * @param mcvIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketCommercialVehicleTableByMcvIds(Long[] mcvIds);

    public List<AverageResult> selectcountAverage();

    public List<AverageResult> selectcount19();


    List<AverageResult> selectLightWeight();

    List<AverageResult> selectBigWeight();

    List<AverageResult> selectcountAverage45();

    List<AverageResult> selectcountAverage46();

    List<AverageResult> selectcountAverage47();

    List<AverageResult> selectcountAverage48();

    List<MarketCommercialVehicleTable> selectListByProductionDept();
}
