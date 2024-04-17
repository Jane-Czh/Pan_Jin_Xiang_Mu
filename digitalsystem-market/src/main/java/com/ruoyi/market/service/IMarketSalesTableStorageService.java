package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.MarketSalesTableStorage;

/**
 * 销售台账存储Service接口
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
public interface IMarketSalesTableStorageService 
{
    /**
     * 查询销售台账存储
     * 
     * @param mstsId 销售台账存储主键
     * @return 销售台账存储
     */
    public MarketSalesTableStorage selectMarketSalesTableStorageByMstsId(Long mstsId);

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
     * 批量删除销售台账存储
     * 
     * @param mstsIds 需要删除的销售台账存储主键集合
     * @return 结果
     */
    public int deleteMarketSalesTableStorageByMstsIds(Long[] mstsIds);

    /**
     * 删除销售台账存储信息
     * 
     * @param mstsId 销售台账存储主键
     * @return 结果
     */
    public int deleteMarketSalesTableStorageByMstsId(Long mstsId);
}
