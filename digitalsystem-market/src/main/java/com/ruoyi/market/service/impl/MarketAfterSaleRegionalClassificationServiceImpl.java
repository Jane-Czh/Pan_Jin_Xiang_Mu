package com.ruoyi.market.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.market.domain.*;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketAfterSaleRegionalClassificationMapper;
import com.ruoyi.market.service.IMarketAfterSaleRegionalClassificationService;

/**
 * 售后区域分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@Service
public class MarketAfterSaleRegionalClassificationServiceImpl implements IMarketAfterSaleRegionalClassificationService 
{
    @Autowired
    private MarketAfterSaleRegionalClassificationMapper marketAfterSaleRegionalClassificationMapper;

    @Override
    public void Synchronization(List<MarketAfterSaleLedger> list){
        //同步数据库售后台账表
        //遍历list，选出反馈日期为本周的数据
        List<MarketAfterSaleLedger> marketAfterSaleLedgers = new ArrayList<>();
        int i = 0;
        while (i < list.size()){
            MarketAfterSaleLedger marketAfterSaleLedger = list.get(i);
            if (getTime.isCurrentWeek(marketAfterSaleLedger.getFeedbackDate())){
                marketAfterSaleLedgers.add(marketAfterSaleLedger);
            }
            i++;
        }
        //对选出的本周数据进行分类（按照一级网点）
        HashMap<String, List<MarketAfterSaleLedger>> hashMap = new HashMap<>();
        i = 0;
        while (i < marketAfterSaleLedgers.size()){
            String Primary_Branch = marketAfterSaleLedgers.get(i).getPrimaryBranch();
            if (!hashMap.containsKey(Primary_Branch)){
                //hashmap中不存在当前网点
                List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = new ArrayList<>();
                marketAfterSaleLedgers1.add(marketAfterSaleLedgers.get(i));
                hashMap.put(Primary_Branch,marketAfterSaleLedgers1);
            }else {
                //hashmap中存在当前网点
                List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = hashMap.get(Primary_Branch);
                marketAfterSaleLedgers1.add(marketAfterSaleLedgers.get(i));
                hashMap.replace(Primary_Branch,marketAfterSaleLedgers1);
            }
            i++;
        }

        //删除数据库中旧数据
        MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification1 = new MarketAfterSaleRegionalClassification();
        List<MarketAfterSaleRegionalClassification> list1 = selectMarketAfterSaleRegionalClassificationList(marketAfterSaleRegionalClassification1);
        if (list1.size() > 0){
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()){
                MarketAfterSaleRegionalClassification value = list1.get(x);
                deleteId[x] = value.getMasrcId();
                x++;
            }
            deleteMarketAfterSaleRegionalClassificationByMasrcIds(deleteId);
            System.out.println("删除成功");
        }

        //本周总问题数
        int Problem_Number = marketAfterSaleLedgers.size();
        //分类完成后对网点内问题进行统计
        for (Map.Entry<String, List<MarketAfterSaleLedger>> entry : hashMap.entrySet()){
            String Primary_Branch = entry.getKey();
            List<MarketAfterSaleLedger> problem = entry.getValue();
            //循环变量
            int j = 0;
            //该区域问题数量
            int problem_count = problem.size();
            //未处理的问题数量
            int count = 0;
            //遍历同一网点的数据
            while (j < problem.size()){
                MarketAfterSaleLedger marketAfterSaleLedger = problem.get(j);
                if (marketAfterSaleLedger.getFinalTreatmentMethod() == null || marketAfterSaleLedger.getFinalTreatmentMethod().equals("")){
                    count++;
                }
                j++;
            }

            //该区域问题数量占比
            double proportion = (double) problem_count / Problem_Number;
            proportion = Math.round(proportion * 100.0) / 100.0;

            //插入数据库
            MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification = new MarketAfterSaleRegionalClassification();
            Long last_id = selectLastId();
            if (last_id == null){
                last_id = 0L;
            }

            System.out.println(Primary_Branch);
            if (Primary_Branch == null || Primary_Branch.equals("")){
                continue;
            }
            marketAfterSaleRegionalClassification.setMasrcId(GenerateId.getNextId(last_id));
            marketAfterSaleRegionalClassification.setArea(Primary_Branch);
            marketAfterSaleRegionalClassification.setRegionalProblemsProportion(BigDecimal.valueOf(proportion));
            marketAfterSaleRegionalClassification.setUnprocessedNmber((long)count);
            marketAfterSaleRegionalClassificationMapper.insertMarketAfterSaleRegionalClassification(marketAfterSaleRegionalClassification);
            System.out.println("插入-------成功");
        }

    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketAfterSaleRegionalClassificationMapper.selectLastId();
    }

    /**
     * 查询售后区域分类
     * 
     * @param masrcId 售后区域分类主键
     * @return 售后区域分类
     */
    @Override
    public MarketAfterSaleRegionalClassification selectMarketAfterSaleRegionalClassificationByMasrcId(Long masrcId)
    {
        return marketAfterSaleRegionalClassificationMapper.selectMarketAfterSaleRegionalClassificationByMasrcId(masrcId);
    }

    /**
     * 查询售后区域分类列表
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 售后区域分类
     */
    @Override
    public List<MarketAfterSaleRegionalClassification> selectMarketAfterSaleRegionalClassificationList(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        return marketAfterSaleRegionalClassificationMapper.selectMarketAfterSaleRegionalClassificationList(marketAfterSaleRegionalClassification);
    }

    /**
     * 新增售后区域分类
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 结果
     */
    @Override
    public int insertMarketAfterSaleRegionalClassification(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        return marketAfterSaleRegionalClassificationMapper.insertMarketAfterSaleRegionalClassification(marketAfterSaleRegionalClassification);
    }

    /**
     * 修改售后区域分类
     * 
     * @param marketAfterSaleRegionalClassification 售后区域分类
     * @return 结果
     */
    @Override
    public int updateMarketAfterSaleRegionalClassification(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        return marketAfterSaleRegionalClassificationMapper.updateMarketAfterSaleRegionalClassification(marketAfterSaleRegionalClassification);
    }

    /**
     * 批量删除售后区域分类
     * 
     * @param masrcIds 需要删除的售后区域分类主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleRegionalClassificationByMasrcIds(Long[] masrcIds)
    {
        return marketAfterSaleRegionalClassificationMapper.deleteMarketAfterSaleRegionalClassificationByMasrcIds(masrcIds);
    }

    /**
     * 删除售后区域分类信息
     * 
     * @param masrcId 售后区域分类主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleRegionalClassificationByMasrcId(Long masrcId)
    {
        return marketAfterSaleRegionalClassificationMapper.deleteMarketAfterSaleRegionalClassificationByMasrcId(masrcId);
    }
}
