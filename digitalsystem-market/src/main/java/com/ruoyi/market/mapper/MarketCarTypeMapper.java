package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketCarType;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import org.apache.ibatis.annotations.Param;

/**
 * 主要车型维护Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface MarketCarTypeMapper 
{
    /**
     * 查询主要车型维护
     * 
     * @param mctId 主要车型维护主键
     * @return 主要车型维护
     */
    public MarketCarType selectMarketCarTypeByMctId(Long mctId);

    //查询数据库最后一条数据id
    public Long selectLastId();

    /**
     * 查询主要车型维护列表
     * 
     * @param marketCarType 主要车型维护
     * @return 主要车型维护集合
     */
    public List<MarketCarType> selectMarketCarTypeList(MarketCarType marketCarType);

    /**
     * 新增主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    public int insertMarketCarType(MarketCarType marketCarType);

    /**
     * 修改主要车型维护
     * 
     * @param marketCarType 主要车型维护
     * @return 结果
     */
    public int updateMarketCarType(MarketCarType marketCarType);

    /**
     * 删除主要车型维护
     * 
     * @param mctId 主要车型维护主键
     * @return 结果
     */
    public int deleteMarketCarTypeByMctId(Long mctId);

    /**
     * 批量删除主要车型维护
     * 
     * @param mctIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketCarTypeByMctIds(Long[] mctIds);

    public void batchInsert(@Param("list") List<MarketCarType> marketCarTypes);

    public List<MarketCarType> selectMarketCarTypeList1();
}
