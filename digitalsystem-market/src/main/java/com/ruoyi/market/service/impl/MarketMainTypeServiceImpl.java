package com.ruoyi.market.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketMainTypeMapper;
import com.ruoyi.market.domain.MarketMainType;
import com.ruoyi.market.service.IMarketMainTypeService;

/**
 * 主要车型字典表Service业务层处理
 * 
 * @author Teandron
 * @date 2024-09-13
 */
@Service
public class MarketMainTypeServiceImpl implements IMarketMainTypeService 
{
    @Autowired
    private MarketMainTypeMapper marketMainTypeMapper;

    /**
     * 查询主要车型字典表
     * 
     * @param id 主要车型字典表主键
     * @return 主要车型字典表
     */
    @Override
    public MarketMainType selectMarketMainTypeById(Long id)
    {
        return marketMainTypeMapper.selectMarketMainTypeById(id);
    }

    /**
     * 查询主要车型字典表列表
     * 
     * @param marketMainType 主要车型字典表
     * @return 主要车型字典表
     */
    @Override
    public List<MarketMainType> selectMarketMainTypeList(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.selectMarketMainTypeList(marketMainType);
    }

    @Override
    public List<MarketMainType> selectMarketMainTypeList1() {
        return marketMainTypeMapper.selectMarketMainTypeList1();
    }

    /**
     * 新增主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    @Override
    public int insertMarketMainType(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.insertMarketMainType(marketMainType);
    }

    /**
     * 修改主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    @Override
    public int updateMarketMainType(MarketMainType marketMainType)
    {
        return marketMainTypeMapper.updateMarketMainType(marketMainType);
    }

    /**
     * 批量删除主要车型字典表
     * 
     * @param ids 需要删除的主要车型字典表主键
     * @return 结果
     */
    @Override
    public int deleteMarketMainTypeByIds(Long[] ids)
    {
        return marketMainTypeMapper.deleteMarketMainTypeByIds(ids);
    }

    /**
     * 删除主要车型字典表信息
     * 
     * @param id 主要车型字典表主键
     * @return 结果
     */
    @Override
    public int deleteMarketMainTypeById(Long id)
    {
        return marketMainTypeMapper.deleteMarketMainTypeById(id);
    }
}
