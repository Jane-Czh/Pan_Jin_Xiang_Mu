package com.ruoyi.market.mapper;

import java.util.List;
import com.ruoyi.market.domain.MarketAfterSaleRegionalClassification;

/**
 * 售后区域分类Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
public interface MarketAfterSaleRegionalClassificationMapper 
{
    /**
     * 查询售后区域分类
     * 
     * @param masrcId 售后区域分类主键
     * @return 售后区域分类
     */
    public MarketAfterSaleRegionalClassification selectMarketAfterSaleRegionalClassificationByMasrcId(Long masrcId);

    //查询数据库最后一条数据id
    public Long selectLastId();

    /**
     * 查询售后区域分类列表
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 售后区域分类集合
     */
    public List<MarketAfterSaleRegionalClassification> selectMarketAfterSaleRegionalClassificationList(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification);

    /**
     * 新增售后区域分类
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 结果
     */
    public int insertMarketAfterSaleRegionalClassification(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification);

    /**
     * 修改售后区域分类
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 结果
     */
    public int updateMarketAfterSaleRegionalClassification(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification);

    /**
     * 删除售后区域分类
     * 
     * @param masrcId 售后区域分类主键
     * @return 结果
     */
    public int deleteMarketAfterSaleRegionalClassificationByMasrcId(Long masrcId);

    /**
     * 批量删除售后区域分类
     * 
     * @param masrcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMarketAfterSaleRegionalClassificationByMasrcIds(Long[] masrcIds);
}
