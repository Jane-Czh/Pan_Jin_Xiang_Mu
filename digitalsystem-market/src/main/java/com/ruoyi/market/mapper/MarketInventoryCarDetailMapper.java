package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketInventoryCarDetail;

/**
 * 库存车明细Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface MarketInventoryCarDetailMapper 
{
    /**
     * 查询库存车明细
     * 
     * @param micdId 库存车明细主键
     * @return 库存车明细
     */
    public MarketInventoryCarDetail selectMarketInventoryCarDetailByMicdId(Long micdId);

    public List<MarketInventoryCarDetail> selectAllData();

    //查询数据库最后一条数据id
    public Long selectLastId();


    /**
     * 查询库存车明细列表
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 库存车明细集合
     */
    public List<MarketInventoryCarDetail> selectMarketInventoryCarDetailList(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 新增库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    public int insertMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 修改库存车明细
     * 
     * @param marketInventoryCarDetail 库存车明细
     * @return 结果
     */
    public int updateMarketInventoryCarDetail(MarketInventoryCarDetail marketInventoryCarDetail);

    /**
     * 删除库存车明细
     * 
     * @param micdId 库存车明细主键
     * @return 结果
     */
    public int deleteMarketInventoryCarDetailByMicdId(Long micdId);

    /**
     * 批量删除库存车明细
     * 
     * @param micdIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketInventoryCarDetailByMicdIds(Long[] micdIds);
}
