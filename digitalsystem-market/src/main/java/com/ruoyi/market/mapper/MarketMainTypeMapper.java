package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketMainType;
import com.ruoyi.market.domain.MarketSalesTable;
import org.apache.ibatis.annotations.Param;

/**
 * 主要车型字典表Mapper接口
 * 
 * @author Teandron
 * @date 2024-09-13
 */
public interface MarketMainTypeMapper 
{
    /**
     * 查询主要车型字典表
     * 
     * @param id 主要车型字典表主键
     * @return 主要车型字典表
     */
    public MarketMainType selectMarketMainTypeById(Long id);

    /**
     * 查询主要车型字典表列表
     * 
     * @param marketMainType 主要车型字典表
     * @return 主要车型字典表集合
     */
    public List<MarketMainType> selectMarketMainTypeList(MarketMainType marketMainType);

    /**
     * 新增主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    public int insertMarketMainType(MarketMainType marketMainType);

    /**
     * 修改主要车型字典表
     * 
     * @param marketMainType 主要车型字典表
     * @return 结果
     */
    public int updateMarketMainType(MarketMainType marketMainType);

    /**
     * 删除主要车型字典表
     * 
     * @param id 主要车型字典表主键
     * @return 结果
     */
    public int deleteMarketMainTypeById(Long id);

    /**
     * 批量删除主要车型字典表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketMainTypeByIds(Long[] ids);

    public void batchInsert(@Param("list") List<MarketMainType> marketMainType);

    public void deleteAll();
}
