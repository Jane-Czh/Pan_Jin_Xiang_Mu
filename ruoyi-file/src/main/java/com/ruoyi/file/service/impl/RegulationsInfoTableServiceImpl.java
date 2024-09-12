package com.ruoyi.file.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.file.entity.regulationCountsRespondEntity;
import com.ruoyi.file.entity.regulationRespondEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.RegulationsInfoTableMapper;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IRegulationsInfoTableService;

/**
 * 文件管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class RegulationsInfoTableServiceImpl implements IRegulationsInfoTableService
{
    @Autowired
    private RegulationsInfoTableMapper regulationsInfoTableMapper;

    /**
     * 根据id查找filename
     * 流程部分依赖使用
     * @param regulationsIds
     * @return
     */
    @Override
    public List<String> selectFileNamesByIds(String regulationsIds) {
        // 去除方括号和空格，将字符串解析为ID列表
        String cleanedIds = regulationsIds.replaceAll("[\\[\\]\\s]", "");
        List<String> idsList = Arrays.asList(cleanedIds.split(","));
        System.out.println("regulationsIdsList==========>"+idsList);
        List<String> fileNames = regulationsInfoTableMapper.selectFileNamesByIds(idsList);
        System.out.println("regulationsFileNamesList===>"+fileNames);
        return fileNames;
    }

    /**
     * 查询文件管理
     *
     * @param regulationsId 文件管理主键
     * @return 文件管理
     */
    @Override
    public RegulationsInfoTable selectRegulationsInfoTableByRegulationsId(Long regulationsId)
    {
        return regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(regulationsId);
    }

    /**
     * 查询文件管理列表
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理
     */
    @Override
    public List<RegulationsInfoTable> selectRegulationsInfoTableList(RegulationsInfoTable regulationsInfoTable)
    {
        return regulationsInfoTableMapper.selectRegulationsInfoTableList(regulationsInfoTable);
    }

    /**
     * 查询文件管理列表(全部)
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理
     */
    @Override
    public List<RegulationsInfoTable> selectAllRegulationsList(RegulationsInfoTable regulationsInfoTable)
    {
        return regulationsInfoTableMapper.selectAllRegulationsList(regulationsInfoTable);
    }

    /**
     * 查询历史版本文件列表
     *
     * @param currentId 文件管理
     * @return 文件管理
     */
    @Override
    public List<RegulationsInfoTable> getRegulationsHistory(Long currentId) {
        //返回结果
        List<RegulationsInfoTable> historyList = new ArrayList<>();
        Long oldId = regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(currentId).getOldRegulationsId();
        while (oldId != null) {
            RegulationsInfoTable record = regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(oldId);
            if (record != null) {
                historyList.add(record);
                oldId = record.getOldRegulationsId();
            } else {
                break;
            }
        }
        return historyList;
    }

    /**
     * 新增文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    @Override
    public int insertRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable)
    {
        regulationsInfoTableMapper.insertRegulationsInfoTable(regulationsInfoTable);
        Long rId = regulationsInfoTable.getRegulationsId();
        return Math.toIntExact(rId);
    }

    /**
     * 修改文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    @Override
    public int updateRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable)
    {
        return regulationsInfoTableMapper.updateRegulationsInfoTable(regulationsInfoTable);
    }

    /**
     * 批量删除文件管理
     *
     * @param regulationsIds 需要删除的文件管理主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsInfoTableByRegulationsIds(Long[] regulationsIds)
    {
        return regulationsInfoTableMapper.deleteRegulationsInfoTableByRegulationsIds(regulationsIds);
    }

    /**
     * 删除文件管理信息
     *
     * @param regulationsId 文件管理主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsInfoTableByRegulationsId(Long regulationsId)
    {
        return regulationsInfoTableMapper.deleteRegulationsInfoTableByRegulationsId(regulationsId);
    }

    @Override
    public List<regulationRespondEntity> selectRevisionFrequency(Date startTime, Date endTime,Long regulationId) {
//        return regulationsInfoTableMapper.selectRevisionFrequency(startTime, endTime);

        //根据传递的regulationId查询当前制度的历史制度list
        List<RegulationsInfoTable> historyRegulations = getRegulationsHistory(regulationId);

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
            List<regulationRespondEntity> res = new ArrayList<>();

            Map<Date, regulationRespondEntity> entityMap = new HashMap<>();

            // 创建包含指定时间范围内所有年份和月份的 [startTime, endTime] --> IndicatorRespondEntity 对象
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            while (calendar.getTime().before(endTime) || calendar.getTime().equals(endTime)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String stringYearMonth = dateFormat.format(calendar.getTime());
                Date yearMonth = dateFormat.parse(stringYearMonth);
                regulationRespondEntity entity = new regulationRespondEntity();
                entity.setYearAndMonth(yearMonth);
                //默认数值counts = 0
                entity.setTimes(0);
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
            for (RegulationsInfoTable regulation : historyRegulations) {
                System.out.println("RegulationsInfoTable==>" + regulation);
                Date uploadDate = regulation.getUploadDate();


                // 转换string --> date. 跳过updateDate为null的数据.
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
                        regulationRespondEntity entity = entityMap.get(yearMonth);
                        System.out.println("get key entity ===> "+ entity);

                        if (entity != null) {
                            entity.setTimes(entity.getTimes() + 1); // 更新计数
                            entity.addUpdateDate(uploadDate); // 添加具体的日期--updateDate
                        }
                    }
                }
            }

            // 将Map中的所有值添加到结果列表中
            res.addAll(entityMap.values());

            // 对结果列表按照yearAndMonth字段进行排序
            Collections.sort(res, new Comparator<regulationRespondEntity>() {
                @Override
                public int compare(regulationRespondEntity o1, regulationRespondEntity o2) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    Date date1 = o1.getYearAndMonth();
                    Date date2 = o2.getYearAndMonth();
                    return date1.compareTo(date2);
                }
            });

            for (regulationRespondEntity re : res) {
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

    /**
    * 各部门制度总数统计
    *
    * */
    @Override
    public List<regulationCountsRespondEntity> selectRegulationsCounts(Date startTime, Date endTime) {
        System.out.println("selectRegulationsCounts:"+regulationsInfoTableMapper.selectRegulationsCounts(startTime, endTime));
        return regulationsInfoTableMapper.selectRegulationsCounts(startTime, endTime);
    }

}
