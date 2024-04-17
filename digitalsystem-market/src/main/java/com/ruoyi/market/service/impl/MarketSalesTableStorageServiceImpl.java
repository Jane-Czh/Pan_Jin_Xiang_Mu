package com.ruoyi.market.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketSalesTableStorageMapper;
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.service.IMarketSalesTableStorageService;

/**
 * 销售台账存储Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
@Service
public class MarketSalesTableStorageServiceImpl implements IMarketSalesTableStorageService 
{
    @Autowired
    private MarketSalesTableStorageMapper marketSalesTableStorageMapper;

    /**
     * 查询销售台账存储
     * 
     * @param mstsId 销售台账存储主键
     * @return 销售台账存储
     */
    @Override
    public MarketSalesTableStorage selectMarketSalesTableStorageByMstsId(Long mstsId)
    {
        return marketSalesTableStorageMapper.selectMarketSalesTableStorageByMstsId(mstsId);
    }

    /**
     * 查询销售台账存储列表
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 销售台账存储
     */
    @Override
    public List<MarketSalesTableStorage> selectMarketSalesTableStorageList(MarketSalesTableStorage marketSalesTableStorage)
    {
        return marketSalesTableStorageMapper.selectMarketSalesTableStorageList(marketSalesTableStorage);
    }

    /**
     * 新增销售台账存储
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 结果
     */
    @Override
    public int insertMarketSalesTableStorage(MarketSalesTableStorage marketSalesTableStorage)
    {
        return marketSalesTableStorageMapper.insertMarketSalesTableStorage(marketSalesTableStorage);
    }

    /**
     * 修改销售台账存储
     * 
     * @param marketSalesTableStorage 销售台账存储
     * @return 结果
     */
    @Override
    public int updateMarketSalesTableStorage(MarketSalesTableStorage marketSalesTableStorage)
    {
        return marketSalesTableStorageMapper.updateMarketSalesTableStorage(marketSalesTableStorage);
    }

    /**
     * 批量删除销售台账存储
     * 
     * @param mstsIds 需要删除的销售台账存储主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableStorageByMstsIds(Long[] mstsIds)
    {
        return marketSalesTableStorageMapper.deleteMarketSalesTableStorageByMstsIds(mstsIds);
    }

    /**
     * 删除销售台账存储信息
     * 
     * @param mstsId 销售台账存储主键
     * @return 结果
     */
    @Override
    public int deleteMarketSalesTableStorageByMstsId(Long mstsId)
    {
        return marketSalesTableStorageMapper.deleteMarketSalesTableStorageByMstsId(mstsId);
    }
}
