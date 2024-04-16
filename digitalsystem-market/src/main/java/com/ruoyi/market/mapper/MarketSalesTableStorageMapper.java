package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketSalesTableStorage;

/**
 * 销售台账存储Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
public interface MarketSalesTableStorageMapper 
{
    /**
     * 查询销售台账存储
     * 
     * @param mstsId 销售台账存储主键
     * @return 销售台账存储
     */
    public MarketSalesTableStorage selectMarketSalesTableStorageByMstsId(Long mstsId);

    //查询数据库最后一条数据id
    public Long selectLastId();


    /**
     * 查询销售台账存储列表
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 销售台账存储集合
     */
    public List<MarketSalesTableStorage> selectMarketSalesTableStorageList(MarketSalesTableStorage marketSalesTableStorage);

    /**
     * 新增销售台账存储
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 结果
     */
    public int insertMarketSalesTableStorage(MarketSalesTableStorage marketSalesTableStorage);

    /**
     * 修改销售台账存储
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 结果
     */
    public int updateMarketSalesTableStorage(MarketSalesTableStorage marketSalesTableStorage);

    /**
     * 删除销售台账存储
     * 
     * @param mstsId 销售台账存储主键
     * @return 结果
     */
    public int deleteMarketSalesTableStorageByMstsId(Long mstsId);

    /**
     * 批量删除销售台账存储
     * 
     * @param mstsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketSalesTableStorageByMstsIds(Long[] mstsIds);
}
