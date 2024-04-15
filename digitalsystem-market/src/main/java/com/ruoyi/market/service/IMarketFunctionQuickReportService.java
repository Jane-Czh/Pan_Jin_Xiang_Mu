package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.domain.MarketSalesTable;

/**
 * 市场科销售快报功能Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface IMarketFunctionQuickReportService 
{


    /**
     * 同步数据库销售快报数据
     *
     *
     *
     */
    public void Synchronization(List<MarketSalesTable> list, MarketFunctionQuickReport marketFunctionQuickReport1);



    /**
     * 查询市场科销售快报功能
     * 
     * @param mfqrId 市场科销售快报功能主键
     * @return 市场科销售快报功能
     */
    public MarketFunctionQuickReport selectMarketFunctionQuickReportByMfqrId(Long mfqrId);

    /**
     * 查询市场科销售快报功能列表
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 市场科销售快报功能集合
     */
    public List<MarketFunctionQuickReport> selectMarketFunctionQuickReportList(MarketFunctionQuickReport marketFunctionQuickReport);

    /**
     * 新增市场科销售快报功能
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 结果
     */
    public int insertMarketFunctionQuickReport(MarketFunctionQuickReport marketFunctionQuickReport);

    /**
     * 修改市场科销售快报功能
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 结果
     */
    public int updateMarketFunctionQuickReport(MarketFunctionQuickReport marketFunctionQuickReport);

    /**
     * 批量删除市场科销售快报功能
     * 
     * @param mfqrIds 需要删除的市场科销售快报功能主键集合
     * @return 结果
     */
    public int deleteMarketFunctionQuickReportByMfqrIds(Long[] mfqrIds);

    /**
     * 删除市场科销售快报功能信息
     * 
     * @param mfqrId 市场科销售快报功能主键
     * @return 结果
     */
    public int deleteMarketFunctionQuickReportByMfqrId(Long mfqrId);
}
