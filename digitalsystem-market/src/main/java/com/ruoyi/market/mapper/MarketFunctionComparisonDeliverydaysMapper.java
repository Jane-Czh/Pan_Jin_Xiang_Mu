package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;

/**
 * 同配置车型交货天数对比功能Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public interface MarketFunctionComparisonDeliverydaysMapper 
{
    /**
     * 查询同配置车型交货天数对比功能
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 同配置车型交货天数对比功能
     */
    public MarketFunctionComparisonDeliverydays selectMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId);
    //查询数据库最后一条数据id
    public Long selectLastId();

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
     * 删除同配置车型交货天数对比功能
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 结果
     */
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId);

    /**
     * 批量删除同配置车型交货天数对比功能
     * 
     * @param mfcdIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdIds(Long[] mfcdIds);
}
