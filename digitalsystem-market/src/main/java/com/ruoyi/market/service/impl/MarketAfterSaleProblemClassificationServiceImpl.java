package com.ruoyi.market.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.StringUtils;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketAfterSaleProblemClassificationMapper;
import com.ruoyi.market.domain.MarketAfterSaleProblemClassification;
import com.ruoyi.market.service.IMarketAfterSaleProblemClassificationService;

/**
 * 售后问题分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
@Service
public class MarketAfterSaleProblemClassificationServiceImpl implements IMarketAfterSaleProblemClassificationService 
{
    @Autowired
    private MarketAfterSaleProblemClassificationMapper marketAfterSaleProblemClassificationMapper;

    @Override
    public void Synchronization(List<MarketAfterSaleLedger> list){
        //同步数据库售后台账表
        //遍历list，选出反馈日期为当月的数据
        List<MarketAfterSaleLedger> marketAfterSaleLedgers = new ArrayList<>();
        int i = 0;
        while (i < list.size()){
            MarketAfterSaleLedger marketAfterSaleLedger = list.get(i);
            if (getTime.isCurrentWeek(marketAfterSaleLedger.getFeedbackDate())){
                marketAfterSaleLedgers.add(marketAfterSaleLedger);
            }
            i++;
        }

        //对选出的本周数据进行分类（按照车型）
        HashMap<String, List<MarketAfterSaleLedger>> hashMap = new HashMap<>();
//        HashMap<String, HashMap<String,List<MarketAfterSaleLedger>>> hashMap = new HashMap<>();
        i = 0;
        while (i < marketAfterSaleLedgers.size()){
//            String Vehicle_Model = StringUtils.getPrefix(marketAfterSaleLedgers.get(i).getVehicleModel());
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

        //分类完成后对一级网点分类
        HashMap<String, HashMap<String,List<MarketAfterSaleLedger>>> result = new HashMap<>();
        for (Map.Entry<String, List<MarketAfterSaleLedger>> entry : hashMap.entrySet()){
            String Vehicle_Model = entry.getKey();
            List<MarketAfterSaleLedger> problem = entry.getValue();
            //当前获取到了该车型的list，需要进一步细分
            HashMap<String, List<MarketAfterSaleLedger>> branch = new HashMap<>(); //存储一级网点和对应网点MarketAfterSaleLedger链表之间的映射
            int j = 0;
            while (j < problem.size()){
                MarketAfterSaleLedger marketAfterSaleLedger = problem.get(j);
                String PrimaryBranch = marketAfterSaleLedger.getPrimaryBranch();
                if (!branch.containsKey(PrimaryBranch)){
                    //branch中不存在该网点
                    List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = new ArrayList<>();
                    marketAfterSaleLedgers1.add(marketAfterSaleLedger);
                    branch.put(PrimaryBranch,marketAfterSaleLedgers1);
                }
                else {
                    List<MarketAfterSaleLedger> marketAfterSaleLedgers1 = branch.get(PrimaryBranch);
                    marketAfterSaleLedgers1.add(marketAfterSaleLedger);
                    branch.replace(PrimaryBranch,marketAfterSaleLedgers1);
                }
                j++;
            }
            result.put(Vehicle_Model,branch);
        }

        for (Map.Entry<String,HashMap<String,List<MarketAfterSaleLedger>>> entry : result.entrySet()){
            String Vehicle_Model = entry.getKey();
            if (Vehicle_Model.equals("")){
                continue;
            }
            HashMap<String,List<MarketAfterSaleLedger>> branch = entry.getValue();
//            List<MarketAfterSaleLedger> problem = entry.getValue();
            //拆分网点
            for (Map.Entry<String,List<MarketAfterSaleLedger>> entry1 : branch.entrySet()){
                String PrimaryBranch = entry1.getKey();
                List<MarketAfterSaleLedger> marketAfterSaleLedgerList = entry1.getValue();
                HashMap<String, Integer> hashmap = new HashMap<>();
                int j = 0;
                //遍历同一车型同一网点的数据
                while (j < marketAfterSaleLedgerList.size()){
                    MarketAfterSaleLedger marketAfterSaleLedger = marketAfterSaleLedgerList.get(j);
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

                int problem_number = marketAfterSaleLedgerList.size();
                int repeat_question = 0;
                for (Map.Entry<String, Integer> entry2 : hashmap.entrySet()){
                    repeat_question = repeat_question + entry2.getValue() - 1;
                }
                int different_problem = hashmap.size();
                // 将其中一个操作数转换为 double 类型
                double Recurrence_Rate = (double) repeat_question / problem_number;
                // 格式化保留小数点后两位
                Recurrence_Rate = Math.round(Recurrence_Rate * 100.0) / 100.0;

                double Different_Problem_Occurrence_Rate = (double) different_problem / problem_number;
                Different_Problem_Occurrence_Rate = Math.round(Different_Problem_Occurrence_Rate* 100.0) / 100.0;

                //插入数据库
                MarketAfterSaleProblemClassification marketAfterSaleProblemClassification = new MarketAfterSaleProblemClassification();
                Long last_id = selectLastId();
                if (last_id == null){
                    last_id = 0L;
                }

                marketAfterSaleProblemClassification.setMaspcId(GenerateId.getNextId(last_id));
                marketAfterSaleProblemClassification.setVehicleModel(Vehicle_Model);
                marketAfterSaleProblemClassification.setBranch(PrimaryBranch);
                marketAfterSaleProblemClassification.setProblemRecurrenceRate(BigDecimal.valueOf(Recurrence_Rate));
                marketAfterSaleProblemClassification.setDifferentProblemOccurrenceRate(BigDecimal.valueOf(Different_Problem_Occurrence_Rate));
                LocalDate currentDate = LocalDate.now();
                // 获取当前日期所在的周数
                int weekOfYear = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
                marketAfterSaleProblemClassification.setWeek(weekOfYear);

                System.out.println(marketAfterSaleProblemClassification);
                marketAfterSaleProblemClassificationMapper.insertMarketAfterSaleProblemClassification(marketAfterSaleProblemClassification);
                System.out.println("插入-------成功");
            }



        }

    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketAfterSaleProblemClassificationMapper.selectLastId();
    }

    /**
     * 查询售后问题分类
     * 
     * @param maspcId 售后问题分类主键
     * @return 售后问题分类
     */
    @Override
    public MarketAfterSaleProblemClassification selectMarketAfterSaleProblemClassificationByMaspcId(Long maspcId)
    {
        return marketAfterSaleProblemClassificationMapper.selectMarketAfterSaleProblemClassificationByMaspcId(maspcId);
    }

    /**
     * 查询售后问题分类列表
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 售后问题分类
     */
    @Override
    public List<MarketAfterSaleProblemClassification> selectMarketAfterSaleProblemClassificationList(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        return marketAfterSaleProblemClassificationMapper.selectMarketAfterSaleProblemClassificationList(marketAfterSaleProblemClassification);
    }

    /**
     * 新增售后问题分类
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 结果
     */
    @Override
    public int insertMarketAfterSaleProblemClassification(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        return marketAfterSaleProblemClassificationMapper.insertMarketAfterSaleProblemClassification(marketAfterSaleProblemClassification);
    }

    /**
     * 修改售后问题分类
     * 
     * @param marketAfterSaleProblemClassification 售后问题分类
     * @return 结果
     */
    @Override
    public int updateMarketAfterSaleProblemClassification(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        return marketAfterSaleProblemClassificationMapper.updateMarketAfterSaleProblemClassification(marketAfterSaleProblemClassification);
    }

    /**
     * 批量删除售后问题分类
     * 
     * @param maspcIds 需要删除的售后问题分类主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleProblemClassificationByMaspcIds(Long[] maspcIds)
    {
        return marketAfterSaleProblemClassificationMapper.deleteMarketAfterSaleProblemClassificationByMaspcIds(maspcIds);
    }

    /**
     * 删除售后问题分类信息
     * 
     * @param maspcId 售后问题分类主键
     * @return 结果
     */
    @Override
    public int deleteMarketAfterSaleProblemClassificationByMaspcId(Long maspcId)
    {
        return marketAfterSaleProblemClassificationMapper.deleteMarketAfterSaleProblemClassificationByMaspcId(maspcId);
    }
}
