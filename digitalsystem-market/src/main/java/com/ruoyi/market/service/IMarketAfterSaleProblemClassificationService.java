package com.ruoyi.market.service;

import java.util.List;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketAfterSaleProblemClassification;

/**
 * 售后问题分类Service接口
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public interface IMarketAfterSaleProblemClassificationService 
{

    public void Synchronization(List<MarketAfterSaleLedger> list);

    /**
     * 查询售后问题分类
     * 
     * @param maspcId 售后问题分类主键
     * @return 售后问题分类
     */
    public MarketAfterSaleProblemClassification selectMarketAfterSaleProblemClassificationByMaspcId(Long maspcId);

    /**
     * 查询售后问题分类列表
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 售后问题分类集合
     */
    public List<MarketAfterSaleProblemClassification> selectMarketAfterSaleProblemClassificationList(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification);

    /**
     * 新增售后问题分类
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 结果
     */
    public int insertMarketAfterSaleProblemClassification(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification);

    /**
     * 修改售后问题分类
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 结果
     */
    public int updateMarketAfterSaleProblemClassification(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification);

    /**
     * 批量删除售后问题分类
     * 
     * @param maspcIds 需要删除的售后问题分类主键集合
     * @return 结果
     */
    public int deleteMarketAfterSaleProblemClassificationByMaspcIds(Long[] maspcIds);

    /**
     * 删除售后问题分类信息
     * 
     * @param maspcId 售后问题分类主键
     * @return 结果
     */
    public int deleteMarketAfterSaleProblemClassificationByMaspcId(Long maspcId);
}
