package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.MarketUnsoldCar;
import com.ruoyi.market.domain.MarketInventoryCarDetail;
/**
 * 统计库存车数量Service接口
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
public interface IMarketUnsoldCarService 
{
    /**
     * 查询统计库存车数量
     * 
     * @param ucId 统计库存车数量主键
     * @return 统计库存车数量
     */
    public MarketUnsoldCar selectMarketUnsoldCarByUcId(Long ucId);

    public List<MarketInventoryCarDetail> selectMarketInventoryCarDetailByType(String type);

    /**
     * 查询统计库存车数量列表
     * 
     * @param marketUnsoldCar 统计库存车数量
     * @return 统计库存车数量集合
     */
    public List<MarketUnsoldCar> selectMarketUnsoldCarList(MarketUnsoldCar marketUnsoldCar);

    public void Synchronization();
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
     * 批量删除统计库存车数量
     * 
     * @param ucIds 需要删除的统计库存车数量主键集合
     * @return 结果
     */
    public int deleteMarketUnsoldCarByUcIds(Long[] ucIds);

    /**
     * 删除统计库存车数量信息
     * 
     * @param ucId 统计库存车数量主键
     * @return 结果
     */
    public int deleteMarketUnsoldCarByUcId(Long ucId);
}
