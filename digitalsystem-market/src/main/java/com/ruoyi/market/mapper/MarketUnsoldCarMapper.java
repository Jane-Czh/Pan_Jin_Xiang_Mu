package com.ruoyi.market.mapper;

import java.util.List;

import com.ruoyi.market.domain.MarketInventoryCarDetail;
import com.ruoyi.market.domain.MarketUnsoldCar;

/**
 * 统计库存车数量Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface MarketUnsoldCarMapper 
{
    /**
     * 查询统计库存车数量
     * 
     * @param ucId 统计库存车数量主键
     * @return 统计库存车数量
     */
    public MarketUnsoldCar selectMarketUnsoldCarByUcId(Long ucId);

    /**
     * 查询统计库存车数量列表
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 统计库存车数量集合
     */
    public List<MarketUnsoldCar> selectMarketUnsoldCarList(MarketUnsoldCar marketUnsoldCar);

    public List<MarketInventoryCarDetail> selectMarketInventoryCarDetailByType(String type);
    /**
     * 查询统计全部库存车数量列表
     *
     *
     *
     */
    public List<MarketUnsoldCar> selectAllData();

    /**
     * 新增统计库存车数量
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 结果
     */
    public int insertMarketUnsoldCar(MarketUnsoldCar marketUnsoldCar);

    /**
     * 修改统计库存车数量
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 结果
     */
    public int updateMarketUnsoldCar(MarketUnsoldCar marketUnsoldCar);

    /**
     * 删除统计库存车数量
     * 
     * @param ucId 统计库存车数量主键
     * @return 结果
     */
    public int deleteMarketUnsoldCarByUcId(Long ucId);

    /**
     * 批量删除统计库存车数量
     * 
     * @param ucIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketUnsoldCarByUcIds(Long[] ucIds);

    void deleteAllMarketUnsoldCar();
}
