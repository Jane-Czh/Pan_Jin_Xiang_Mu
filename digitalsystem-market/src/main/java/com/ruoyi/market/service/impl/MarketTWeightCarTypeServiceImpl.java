package com.ruoyi.market.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketTWeightCarTypeMapper;
import com.ruoyi.market.domain.MarketTWeightCarType;
import com.ruoyi.market.service.IMarketTWeightCarTypeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-15
 */
@Service
public class MarketTWeightCarTypeServiceImpl implements IMarketTWeightCarTypeService 
{
    @Autowired
    private MarketTWeightCarTypeMapper marketTWeightCarTypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MarketTWeightCarType selectMarketTWeightCarTypeById(Long id)
    {
        return marketTWeightCarTypeMapper.selectMarketTWeightCarTypeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MarketTWeightCarType> selectMarketTWeightCarTypeList(MarketTWeightCarType marketTWeightCarType)
    {
        return marketTWeightCarTypeMapper.selectMarketTWeightCarTypeList(marketTWeightCarType);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMarketTWeightCarType(MarketTWeightCarType marketTWeightCarType)
    {
        return marketTWeightCarTypeMapper.insertMarketTWeightCarType(marketTWeightCarType);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMarketTWeightCarType(MarketTWeightCarType marketTWeightCarType)
    {
        return marketTWeightCarTypeMapper.updateMarketTWeightCarType(marketTWeightCarType);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMarketTWeightCarTypeByIds(Long[] ids)
    {
        return marketTWeightCarTypeMapper.deleteMarketTWeightCarTypeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMarketTWeightCarTypeById(Long id)
    {
        return marketTWeightCarTypeMapper.deleteMarketTWeightCarTypeById(id);
    }
}
