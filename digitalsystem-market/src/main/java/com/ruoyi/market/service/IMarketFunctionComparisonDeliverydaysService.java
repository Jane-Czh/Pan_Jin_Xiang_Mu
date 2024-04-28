package com.ruoyi.market.service;

import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;

/**
 * 同配置车型交货天数对比功能Service接口
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public interface IMarketFunctionComparisonDeliverydaysService 
{
    /**
     * 同步数据库销售快报数据
     *
     *
     *
     */
    public void Synchronization(List<MarketCommercialVehicleTable> list, MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays1);
    /**
    /**
     * 查询同配置车型交货天数对比功能
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 同配置车型交货天数对比功能
     */
    public MarketFunctionComparisonDeliverydays selectMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId);

    /**
     * 查询同配置车型交货天数对比功能列表
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 同配置车型交货天数对比功能集合
     */
    public List<MarketFunctionComparisonDeliverydays> selectMarketFunctionComparisonDeliverydaysList(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays);

    /**
     * 新增同配置车型交货天数对比功能
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 结果
     */
    public int insertMarketFunctionComparisonDeliverydays(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays);

    /**
     * 修改同配置车型交货天数对比功能
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 结果
     */
    public int updateMarketFunctionComparisonDeliverydays(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays);

    /**
     * 批量删除同配置车型交货天数对比功能
     * 
     * @param mfcdIds 需要删除的同配置车型交货天数对比功能主键集合
     * @return 结果
     */
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdIds(Long[] mfcdIds);

    /**
     * 删除同配置车型交货天数对比功能信息
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 结果
     */
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId);
}
