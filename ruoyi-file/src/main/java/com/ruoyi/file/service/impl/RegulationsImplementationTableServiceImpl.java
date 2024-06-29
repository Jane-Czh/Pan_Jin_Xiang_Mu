package com.ruoyi.file.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.file.entity.regulationImplementationRateRespondEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.RegulationsImplementationTableMapper;
import com.ruoyi.file.domain.RegulationsImplementationTable;
import com.ruoyi.file.service.IRegulationsImplementationTableService;

/**
 * 制度执行率Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-30
 */
@Service
public class RegulationsImplementationTableServiceImpl implements IRegulationsImplementationTableService
{
    @Autowired
    private RegulationsImplementationTableMapper regulationsImplementationTableMapper;

    /**
     * 查询制度执行率
     *
     * @param id 制度执行率主键
     * @return 制度执行率
     */
    @Override
    public RegulationsImplementationTable selectRegulationsImplementationTableById(Long id)
    {
        return regulationsImplementationTableMapper.selectRegulationsImplementationTableById(id);
    }

    /**
     * 查询制度执行率列表
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 制度执行率
     */
    @Override
    public List<RegulationsImplementationTable> selectRegulationsImplementationTableList(RegulationsImplementationTable regulationsImplementationTable)
    {
        return regulationsImplementationTableMapper.selectRegulationsImplementationTableList(regulationsImplementationTable);
    }

    /**
     * 新增制度执行率
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 结果
     */
    @Override
    public int insertRegulationsImplementationTable(RegulationsImplementationTable regulationsImplementationTable)
    {
        return regulationsImplementationTableMapper.insertRegulationsImplementationTable(regulationsImplementationTable);
    }

    /**
     * 修改制度执行率
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 结果
     */
    @Override
    public int updateRegulationsImplementationTable(RegulationsImplementationTable regulationsImplementationTable)
    {
        return regulationsImplementationTableMapper.updateRegulationsImplementationTable(regulationsImplementationTable);
    }

    /**
     * 批量删除制度执行率
     *
     * @param ids 需要删除的制度执行率主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsImplementationTableByIds(Long[] ids)
    {
        return regulationsImplementationTableMapper.deleteRegulationsImplementationTableByIds(ids);
    }

    /**
     * 删除制度执行率信息
     *
     * @param id 制度执行率主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsImplementationTableById(Long id)
    {
        return regulationsImplementationTableMapper.deleteRegulationsImplementationTableById(id);
    }

    @Override
    public List<regulationImplementationRateRespondEntity> countImplementationRate(Date startTime, Date endTime,RegulationsImplementationTable regulationsImplementationTable) {

        //查询列表
        List<RegulationsImplementationTable> regulationsImplementationRates = selectRegulationsImplementationTableList(regulationsImplementationTable);// 检查列表是否为空
        if (regulationsImplementationRates == null) {
            System.out.println("regulationsImplementationRates is null");
        } else if (regulationsImplementationRates.isEmpty()) {
            System.out.println("regulationsImplementationRates is empty");
        } else {
            // 遍历并打印每个 RegulationsImplementationTable 对象
            for (RegulationsImplementationTable table : regulationsImplementationRates) {
                System.out.println("RegulationsImplementationTable====== " + table);
            }
        }

//        int count = 1;
//        for (RegulationsInfoTable historyRegulation : historyRegulations) {
//            System.out.println("historyRegulation==> count ==>" + count + " //" + historyRegulation);
//            count++;
//        }

        //根据传递的时间区间 [startTime, endTime] 进行统计数据
        //example：
        //historyProject==> count ==>1 //ProjectEntity( id=KkQWMxQC7oq74Uvg6ns-0, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=2024-05-06 14:35:49.0, updateBy=admin/研发, oldVersion=e9wi3CUTHpl4O9L14WS6o, newest=1, state=[193], type=[149])
        //historyProject==> count ==>2 //ProjectEntity( id=e9wi3CUTHpl4O9L14WS6o, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=2024-05-06 14:32:53.0, updateBy=admin/研发, oldVersion=MR_gLCUwVKtcjWE1gXwwL, newest=0, state=null, type=null)
        //historyProject==> count ==>3 //ProjectEntity( id=MR_gLCUwVKtcjWE1gXwwL, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=null, updateBy=null, oldVersion=null, newest=0, state=null, type=null)

        // 过滤规则：去掉updateDate=null的数据(这是第一次创建的流程), then 根据updateDate进行统计次数(按照MM-dd进行统计), 同时还需要保留原始的updateDate作为详细的时间节点作为返回值的一个属性
        try {
            List<regulationImplementationRateRespondEntity> res = new ArrayList<>();

            Map<Date, regulationImplementationRateRespondEntity> entityMap = new HashMap<>();

            // 创建包含指定时间范围内所有年份和月份的 [startTime, endTime] --> IndicatorRespondEntity 对象
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            while (calendar.getTime().before(endTime) || calendar.getTime().equals(endTime)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String stringYearMonth = dateFormat.format(calendar.getTime());
                Date yearMonth = dateFormat.parse(stringYearMonth);
                regulationImplementationRateRespondEntity entity = new regulationImplementationRateRespondEntity();
                entity.setYearAndMonth(yearMonth);
                entity.setRate(BigDecimal.valueOf(0));
                //默认数值counts = 0
//                entity.setTimes(0);
                entityMap.put(yearMonth, entity);
                calendar.add(Calendar.MONTH, 1);
                System.out.println("entity ===> "+ entity);
            }



            for (Date date : entityMap
                    .keySet()
            ) {
                System.out.println("entityMap keySet===>"+date);

            }


            //正式 统计历史流程数据, 按照year-month进行统计更改次数counts
            for (RegulationsImplementationTable regulation : regulationsImplementationRates) {
                System.out.println("RegulationsInfoTable==>" + regulation);
                Date uploadDate = regulation.getYearAndMonth();


                // 跳过updateDate为null的数据.
                if (uploadDate == null) {
                    continue;
                } else {
                    // == convertedUpdateDate ==>Thu May 09 00:00:00 CST 2024
                    // == convertedUpdateDate ==>Tue May 07 00:00:00 CST 2024
                    // == convertedUpdateDate ==>Tue May 07 00:00:00 CST 2024
//                    System.out.println(" == convertedUpdateDate ==>" + convertedUpdateDate);

                    // 检查updateDate是否在时间区间内、equals
                    if ((uploadDate.toInstant().isAfter(startTime.toInstant()) && uploadDate.toInstant().isBefore(endTime.toInstant()))
                            || uploadDate.toInstant().equals(startTime.toInstant()) || uploadDate.toInstant().equals(endTime.toInstant())
                    ) {

                        System.out.println("uploadDate before convert ==>"+ uploadDate);
                        // 提取uploadDate中的年份和月份, convert example: Mon Jul 01 00:00:00 CST 2024 --> 2024-07
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                        String stringYearMonth = dateFormat.format(uploadDate);
                        Date yearMonth = dateFormat.parse(stringYearMonth);
//                        System.out.println("uploadDate after convert ==>"+ yearMonth);

                        // 从Map中获取对应的regulationRespondEntity对象，并更新计数
                        regulationImplementationRateRespondEntity entity = entityMap.get(yearMonth);
                        System.out.println("get key entity ===> "+ entity);

                        if (entity != null) {
                            // 将 actualImplementedTimes 和 shouldImplementedTimes 转换为 BigDecimal
                            BigDecimal actual = BigDecimal.valueOf(regulation.getActualImplementedTimes());
                            BigDecimal should = BigDecimal.valueOf(regulation.getShouldImplementedTimes());

                            // 使用 BigDecimal 的 divide 方法进行除法运算，并设置精度和舍入模式
                            BigDecimal rate = actual.divide(should, 2, BigDecimal.ROUND_HALF_UP);
                            System.out.println("rate=========>" + rate);
                            entity.setRate(rate); // 计算每月的制度执行率
                            System.out.println("entity33333 ===> "+ entity);
                        }
                    }
                }
            }

            // 将Map中的所有值添加到结果列表中
            res.addAll(entityMap.values());

            // 对结果列表按照yearAndMonth字段进行排序
            Collections.sort(res, new Comparator<regulationImplementationRateRespondEntity>() {
                @Override
                public int compare(regulationImplementationRateRespondEntity o1, regulationImplementationRateRespondEntity o2) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    Date date1 = o1.getYearAndMonth();
                    Date date2 = o2.getYearAndMonth();
                    return date1.compareTo(date2);
                }
            });

            for (regulationImplementationRateRespondEntity re : res) {
                System.out.println("res ==>  "+ re);
            }
            /**
             * entityMap keySet===>Thu Feb 01 00:00:00 CST 2024
             * entityMap keySet===>Mon Apr 01 00:00:00 CST 2024
             * entityMap keySet===>Wed May 01 00:00:00 CST 2024
             * entityMap keySet===>Fri Mar 01 00:00:00 CST 2024
             * entityMap keySet===>Mon Jan 01 00:00:00 CST 2024
             */

            //做封装处理返回respond
            return res;


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //返回过滤后的数据
    }
}
