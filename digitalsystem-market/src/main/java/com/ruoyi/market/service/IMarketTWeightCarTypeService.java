package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.MarketTWeightCarType;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-09-15
 */
public interface IMarketTWeightCarTypeService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MarketTWeightCarType selectMarketTWeightCarTypeById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MarketTWeightCarType> selectMarketTWeightCarTypeList(MarketTWeightCarType marketTWeightCarType);

    /**
     * 新增【请填写功能名称】
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 结果
     */
    public int insertMarketTWeightCarType(MarketTWeightCarType marketTWeightCarType);

    /**
     * 修改【请填写功能名称】
     * 
     * @param marketTWeightCarType 【请填写功能名称】
     * @return 结果
     */
    public int updateMarketTWeightCarType(MarketTWeightCarType marketTWeightCarType);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMarketTWeightCarTypeByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMarketTWeightCarTypeById(Long id);
}
