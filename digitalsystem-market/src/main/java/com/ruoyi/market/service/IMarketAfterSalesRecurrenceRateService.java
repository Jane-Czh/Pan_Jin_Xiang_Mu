package com.ruoyi.market.service;

import java.util.List;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketAfterSalesRecurrenceRate;

/**
 * 售后台账问题复发率Service接口
 * 
 * @author ruoyi
 * @date 2024-04-17
 */
public interface IMarketAfterSalesRecurrenceRateService 
{

    public void Synchronization(List<MarketAfterSaleLedger> list, MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate);

    /**
     * 查询售后台账问题复发率
     * 
     * @param masrrId 售后台账问题复发率主键
     * @return 售后台账问题复发率
     */
    public MarketAfterSalesRecurrenceRate selectMarketAfterSalesRecurrenceRateByMasrrId(Long masrrId);

    /**
     * 查询售后台账问题复发率列表
     * 
     * @param marketAfterSalesRecurrenceRate 售后台账问题复发率
     * @return 售后台账问题复发率集合
     */
    public List<MarketAfterSalesRecurrenceRate> selectMarketAfterSalesRecurrenceRateList(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate);

    /**
     * 新增售后台账问题复发率
     * 
     * @param marketAfterSalesRecurrenceRate 售后台账问题复发率
     * @return 结果
     */
    public int insertMarketAfterSalesRecurrenceRate(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate);

    /**
     * 修改售后台账问题复发率
     * 
     * @param marketAfterSalesRecurrenceRate 售后台账问题复发率
     * @return 结果
     */
    public int updateMarketAfterSalesRecurrenceRate(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate);

    /**
     * 批量删除售后台账问题复发率
     * 
     * @param masrrIds 需要删除的售后台账问题复发率主键集合
     * @return 结果
     */
    public int deleteMarketAfterSalesRecurrenceRateByMasrrIds(Long[] masrrIds);

    /**
     * 删除售后台账问题复发率信息
     * 
     * @param masrrId 售后台账问题复发率主键
     * @return 结果
     */
    public int deleteMarketAfterSalesRecurrenceRateByMasrrId(Long masrrId);
}
