package com.ruoyi.market.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketAfterSalesRecurrenceRateMapper;
import com.ruoyi.market.domain.MarketAfterSalesRecurrenceRate;
import com.ruoyi.market.service.IMarketAfterSalesRecurrenceRateService;
import com.ruoyi.market.utils.*;

/**
 * 售后问题复发率Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@Service
public class MarketAfterSalesRecurrenceRateServiceImpl implements IMarketAfterSalesRecurrenceRateService 
{
    @Autowired
    private MarketAfterSalesRecurrenceRateMapper marketAfterSalesRecurrenceRateMapper;

    @Override
    public void Synchronization(List<MarketAfterSaleLedger> list, MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate){
        //同步数据库售后台账表
        //遍历list，选出反馈日期为当月的数据
        List<MarketAfterSaleLedger> marketAfterSaleLedgers = new ArrayList<>();
        int i = 0;
        while (i < list.size()){
            MarketAfterSaleLedger marketAfterSaleLedger = list.get(i);
            if (getTime.isCurrentMonth(marketAfterSaleLedger.getFeedbackDate())){
                marketAfterSaleLedgers.add(marketAfterSaleLedger);
            }
            i++;
        }

        //对选出的当月数据进行分类（按照车型）
        HashMap<String, List<MarketAfterSaleLedger>> hashMap = new HashMap<>();
        i = 0;
        while (i < marketAfterSaleLedgers.size()){
            String Vehicle_Model = marketAfterSaleLedgers.get(i).getVehicleModel();
            if (!hashMap.containsKey(Vehicle_Model)){
                //hashmap中不存在当前车型
                List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = new ArrayList<>();
                marketAfterSaleLedgers1.add(marketAfterSaleLedgers.get(i));
                hashMap.put(Vehicle_Model,marketAfterSaleLedgers1);
            }else {
                //hashmap中存在当前车型
                List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = hashMap.get(Vehicle_Model);
                marketAfterSaleLedgers1.add(marketAfterSaleLedgers.get(i));
                hashMap.replace(Vehicle_Model,marketAfterSaleLedgers1);
            }
            i++;
        }

        //分类完成后对一级分类和二级分类进行统计
        for (Map.Entry<String, List<MarketAfterSaleLedger>> entry : hashMap.entrySet()){
            String Vehicle_Model = entry.getKey();
            List<MarketAfterSaleLedger> problem = entry.getValue();
            HashMap<String, Integer> hashmap = new HashMap<>();
            int j = 0;
            //遍历同一车型的数据
            while (j < problem.size()){
                MarketAfterSaleLedger marketAfterSaleLedger = problem.get(j);
                //一级分类不为空
                if (!marketAfterSaleLedger.getPrimaryClassification().equals("")){
                    if (!hashmap.containsKey(marketAfterSaleLedger.getPrimaryClassification()+marketAfterSaleLedger.getSecondaryClassification())){
                        hashmap.put(marketAfterSaleLedger.getPrimaryClassification()+marketAfterSaleLedger.getSecondaryClassification(),1);
                    }
                    else {
                        int count = hashmap.get(marketAfterSaleLedger.getPrimaryClassification()+marketAfterSaleLedger.getSecondaryClassification());
                        count++;
                        hashmap.replace(marketAfterSaleLedger.getPrimaryClassification()+marketAfterSaleLedger.getSecondaryClassification(),count);
                    }
                }
                else {
                    if (!hashmap.containsKey("null")){
                        hashmap.put("null",1);
                    }
                    else {
                        int count = hashmap.get("null");
                        count++;
                        hashmap.replace("null",count);
                    }
                }
                j++;
            }

            int problem_number = problem.size();
            int repeat_question = 0;
            for (Map.Entry<String, Integer> entry1 : hashmap.entrySet()){
                repeat_question = repeat_question + entry1.getValue() - 1;
            }
            int different_problem = hashmap.size();
            // 将其中一个操作数转换为 double 类型
            double Recurrence_Rate = (double) repeat_question / problem_number;
            // 格式化保留小数点后两位
            Recurrence_Rate = Math.round(Recurrence_Rate * 100.0) / 100.0;

            double Different_Problem_Occurrence_Rate = (double) different_problem / problem_number;
            Different_Problem_Occurrence_Rate = Math.round(Different_Problem_Occurrence_Rate* 100.0) / 100.0;

            //插入数据库
            MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate1 = new MarketAfterSalesRecurrenceRate();
            Long last_id = selectLastId();
            if (last_id == null){
                last_id = 0L;
            }

            marketAfterSalesRecurrenceRate1.setMasrrId(GenerateId.getNextId(last_id));
            marketAfterSalesRecurrenceRate1.setVehicleModel(Vehicle_Model);
            marketAfterSalesRecurrenceRate1.setProblemRecurrenceRate(BigDecimal.valueOf(Recurrence_Rate));
            marketAfterSalesRecurrenceRate1.setDifferentProblemOccurrenceRate(BigDecimal.valueOf(Different_Problem_Occurrence_Rate));
            System.out.println(marketAfterSalesRecurrenceRate1);
            marketAfterSalesRecurrenceRateMapper.insertMarketAfterSalesRecurrenceRate(marketAfterSalesRecurrenceRate1);
            System.out.println("插入-------成功");
        }

    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketAfterSalesRecurrenceRateMapper.selectLastId();
    }

    /**
     * 查询售后问题复发率
     * 
     * @param masrrId 售后问题复发率主键
     * @return 售后问题复发率
     */
    @Override
    public MarketAfterSalesRecurrenceRate selectMarketAfterSalesRecurrenceRateByMasrrId(Long masrrId)
    {
        return marketAfterSalesRecurrenceRateMapper.selectMarketAfterSalesRecurrenceRateByMasrrId(masrrId);
    }

    /**
     * 查询售后问题复发率列表
     * 
     * @param marketAfterSalesRecurrenceRate 售后问题复发率
     * @return 售后问题复发率
     */
    @Override
    public List<MarketAfterSalesRecurrenceRate> selectMarketAfterSalesRecurrenceRateList(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        return marketAfterSalesRecurrenceRateMapper.selectMarketAfterSalesRecurrenceRateList(marketAfterSalesRecurrenceRate);
    }

    /**
     * 新增售后问题复发率
     * 
     * @param marketAfterSalesRecurrenceRate 售后问题复发率
     * @return 结果
     */
    @Override
    public int insertMarketAfterSalesRecurrenceRate(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        return marketAfterSalesRecurrenceRateMapper.insertMarketAfterSalesRecurrenceRate(marketAfterSalesRecurrenceRate);
    }

    /**
     * 修改售后问题复发率
     * 
     * @param marketAfterSalesRecurrenceRate 售后问题复发率
     * @return 结果
     */
    @Override
    public int updateMarketAfterSalesRecurrenceRate(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        return marketAfterSalesRecurrenceRateMapper.updateMarketAfterSalesRecurrenceRate(marketAfterSalesRecurrenceRate);
    }

    /**
     * 批量删除售后问题复发率
     * 
     * @param masrrIds 需要删除的售后问题复发率主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSalesRecurrenceRateByMasrrIds(Long[] masrrIds)
    {
        return marketAfterSalesRecurrenceRateMapper.deleteMarketAfterSalesRecurrenceRateByMasrrIds(masrrIds);
    }

    /**
     * 删除售后问题复发率信息
     * 
     * @param masrrId 售后问题复发率主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSalesRecurrenceRateByMasrrId(Long masrrId)
    {
        return marketAfterSalesRecurrenceRateMapper.deleteMarketAfterSalesRecurrenceRateByMasrrId(masrrId);
    }
}
