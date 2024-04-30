package com.ruoyi.market.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketFunctionComparisonDeliverydaysMapper;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import com.ruoyi.market.service.IMarketFunctionComparisonDeliverydaysService;

/**
 * 同配置车型交货天数对比功能Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@Service
public class MarketFunctionComparisonDeliverydaysServiceImpl implements IMarketFunctionComparisonDeliverydaysService 
{
    @Autowired
    private MarketFunctionComparisonDeliverydaysMapper marketFunctionComparisonDeliverydaysMapper;

    @Override
    public void Synchronization(List<MarketCommercialVehicleTable> list, MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays1) {
        int count = 0;
        int in = 0;
        LocalDateTime currentDateTime = LocalDateTime.now(); //获取当前时间.
        LocalDate currentDate = LocalDate.now();
        Date currentDateold = new Date();
        int year = getTime.getYear(currentDateTime);
        int month = getTime.getMonth(currentDateTime);
        int day = getTime.getDay(currentDateTime);
        int weeknow = currentDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());



        //        先删除数据库中当天的所有数据
        List<MarketFunctionComparisonDeliverydays> list1 = selectMarketFunctionComparisonDeliverydaysList(marketFunctionComparisonDeliverydays1);
        if (list1 != null&& list1.size()>0){
            int x = 0;
            Long[] deleteId = new Long[list1.size()];
            while (x < list1.size()){
                MarketFunctionComparisonDeliverydays value = list1.get(x);
                if (SplitDate.splitDate(value.getCreateTime())[0] == year&&SplitDate.splitDate(value.getCreateTime())[1] == month&&SplitDate.splitDate(value.getCreateTime())[2] == day)
                    deleteId[x] = value.getMfcdId();
                x++;
            }
            deleteMarketFunctionComparisonDeliverydaysByMfcdIds(deleteId);
            System.out.println("删除成功");
        }


        // 您的MarketCommercialVehicleTable列表
        //筛选出今天的车型
        List<MarketCommercialVehicleTable> vehiclesweek = new ArrayList<>();
        for (MarketCommercialVehicleTable velocity1 : list) {
            LocalDate launchDate = velocity1.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int weekOfYear = launchDate.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
            if (SplitDate.splitDate(velocity1.getLaunchDate())[0] == year && weekOfYear == weeknow) {
                vehiclesweek.add(velocity1);
            }
        }
//        // 您的MarketCommercialVehicleTable列表
//        //筛选出今天的车型
//        List<MarketCommercialVehicleTable> vehiclesweek = new ArrayList<>();
//        for (MarketCommercialVehicleTable velocity1 : list) {
//            if (SplitDate.splitDate(velocity1.getLaunchDate())[0] == year && SplitDate.splitDate(velocity1.getLaunchDate())[1] == month && SplitDate.splitDate(velocity1.getLaunchDate())[2] == day) {
//                vehiclesweek.add(velocity1);
//            }
//        }

        HashMap<String, MarketFunctionComparisonDeliverydays> Cartype = new HashMap<String, MarketFunctionComparisonDeliverydays>();
        while (count < vehiclesweek.size()) {
            MarketCommercialVehicleTable marketCommercialVehicleTable = vehiclesweek.get(count);
            Date acceptanceDate = marketCommercialVehicleTable.getLaunchDate();//上线日期，对应A列
            Date OrderAcceptanceTime = marketCommercialVehicleTable.getAcceptanceDate(); //接单日期，对应表格B列
            String VehicleModel = extractVehicleType(marketCommercialVehicleTable.getVehicleModel()); //车型，对应表格F列
            Date PrecisionCompletionPeriod = marketCommercialVehicleTable.getPrecisionCompletionPeriod(); //精整玩工期，对应表格R列
            Calendar lacunchDate = Calendar.getInstance();
            lacunchDate.setTime(acceptanceDate);
            int week = lacunchDate.get(Calendar.WEEK_OF_YEAR);
            System.out.println((Cartype.get(VehicleModel)==null)+"**********************");
            if (Cartype.get(VehicleModel)==null){

                MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays=new MarketFunctionComparisonDeliverydays();
                //存入车型
                marketFunctionComparisonDeliverydays.setVehicleModel(VehicleModel);
                if (PrecisionCompletionPeriod!=null&&PrecisionCompletionPeriod.getTime()-OrderAcceptanceTime.getTime() > 0){//精整完工期不为null
                //存入相关数据
                marketFunctionComparisonDeliverydays.setNumberOfDayDelivery((PrecisionCompletionPeriod.getTime()-OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                marketFunctionComparisonDeliverydays.setNumberOfWeekDelivery((PrecisionCompletionPeriod.getTime() - OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                marketFunctionComparisonDeliverydays.setCarNumberDay(1L);
                marketFunctionComparisonDeliverydays.setCarNumberWeek(1L);
                marketFunctionComparisonDeliverydays.setCumulativeNumberOfDays((PrecisionCompletionPeriod.getTime() - OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                marketFunctionComparisonDeliverydays.setCumulativeNumberOfWeek((PrecisionCompletionPeriod.getTime() - OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                marketFunctionComparisonDeliverydays.setCreateTime(currentDateold);
                //插入hash
                Cartype.put(VehicleModel,marketFunctionComparisonDeliverydays);
                System.out.println("已插入表格");
                }
                System.out.println("---------------"+VehicleModel+"----------------------");
//                System.out.println(in++);
            }
            else {
                MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays=Cartype.get(VehicleModel);//拿出hashmap信息
//                System.out.println("车辆信息"+marketFunctionComparisonDeliverydays);
//                System.out.println(SplitDate.splitDate(acceptanceDate)[0] == year&&SplitDate.splitDate(acceptanceDate)[1] == month&&SplitDate.splitDate(acceptanceDate)[2] == day);
//                    long i=marketFunctionComparisonDeliverydays.getCarNumberDay();
//                    i++;
//                    System.out.println(i);
//                    long CND=marketFunctionComparisonDeliverydays.getCumulativeNumberOfDays();
//                    System.out.println("前者每日累计交货天数"+marketFunctionComparisonDeliverydays.getCumulativeNumberOfDays());
//                    CND=CND+((PrecisionCompletionPeriod.getTime()-OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
//                    marketFunctionComparisonDeliverydays.setCumulativeNumberOfDays(CND);
//                    System.out.println("后者累计交货天数"+CND);
//                    long ND=CND/i;
//                    marketFunctionComparisonDeliverydays.setNumberOfDayDelivery(ND);

//                if (SplitDate.splitDate(acceptanceDate)[0] == year&&week==weeknow) {
//                    long j=marketFunctionComparisonDeliverydays.getCarNumberWeek();
//                    j++;
//                    long CNW=marketFunctionComparisonDeliverydays.getCumulativeNumberOfWeek();
//                    CNW=CNW+((PrecisionCompletionPeriod.getTime()-OrderAcceptanceTime.getTime())/(1000 * 60 * 60 * 24));
//                    long NW=CNW/j;
//                    marketFunctionComparisonDeliverydays.setNumberOfWeekDelivery(NW);
//                }
                //处理本周交货天数
                System.out.println("本次精整完工期为"+(PrecisionCompletionPeriod!=null));
                if(PrecisionCompletionPeriod!=null) {
                    long j = marketFunctionComparisonDeliverydays.getCarNumberWeek();
                    j++;
                    marketFunctionComparisonDeliverydays.setCarNumberWeek(j);
                    System.out.println("每周交货车数"+j);
                    long CNW = marketFunctionComparisonDeliverydays.getCumulativeNumberOfWeek();
                    System.out.println("前者每周累计交货天数" + marketFunctionComparisonDeliverydays.getCumulativeNumberOfWeek());
                    CNW = CNW + ((PrecisionCompletionPeriod.getTime() - OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                    marketFunctionComparisonDeliverydays.setCumulativeNumberOfWeek(CNW);
                    System.out.println("后者每周累计交货天数" + CNW);
                    long NW = CNW / j;
                    marketFunctionComparisonDeliverydays.setNumberOfWeekDelivery(NW);

                    //处理当天交货天数
                    if (SplitDate.splitDate(acceptanceDate)[0] == year && SplitDate.splitDate(acceptanceDate)[1] == month && SplitDate.splitDate(acceptanceDate)[2] == day) {
                        long i = marketFunctionComparisonDeliverydays.getCarNumberDay();
                        i++;
                        System.out.println(i);
                        marketFunctionComparisonDeliverydays.setCarNumberDay(i);
                        long CND = marketFunctionComparisonDeliverydays.getCumulativeNumberOfDays();
                        System.out.println("前者每日累计交货天数" + marketFunctionComparisonDeliverydays.getCumulativeNumberOfDays());
                        CND = CND + ((PrecisionCompletionPeriod.getTime() - OrderAcceptanceTime.getTime()) / (1000 * 60 * 60 * 24));
                        marketFunctionComparisonDeliverydays.setCumulativeNumberOfDays(CND);
                        System.out.println("后者累计交货天数" + CND);
                        long ND = CND / i;
                        marketFunctionComparisonDeliverydays.setNumberOfDayDelivery(ND);
                    }


                    Cartype.replace(VehicleModel, marketFunctionComparisonDeliverydays);
                    System.out.println("=============");
                }
            }
            count++;



        }


        //依次插入新生成的快报
        Long id = 0L;
        for (Map.Entry<String, MarketFunctionComparisonDeliverydays> entry : Cartype.entrySet()){
            System.out.println("-------------------");
            MarketFunctionComparisonDeliverydays insertValue = entry.getValue();
            insertValue.setMfcdId(GenerateId.getNextId(id));
            MarketFunctionComparisonDeliverydays existingRecord = selectMarketFunctionComparisonDeliverydaysByMfcdId(insertValue.getMfcdId());
            System.out.println(insertValue.getMfcdId()+"-----------");
            System.out.println(existingRecord+"*************");
            if (existingRecord != null) {
                // 处理已存在相同 mfcdId 的情况
                // 可以选择更新数据或者其他处理方式
                // 如果已存在相同 mfcdId 的数据，则向数据库中插入新数据
                Long lastId = selectLastId();// 获取一个新的唯一 mfcdId
                System.out.println("lastid为"+lastId+"============");
                id = lastId+1;
                System.out.println("id为"+id+"----------");
                insertValue.setMfcdId(id);  // 设置新的唯一 mfcdId
                insertMarketFunctionComparisonDeliverydays(insertValue); // 向数据库中插入带有新 mfcdId 的数据
            } else {
                System.out.println("id为"+id+"----------");
                insertValue.setMfcdId(id);
                insertMarketFunctionComparisonDeliverydays(insertValue);
            }
//            insertValue.setMfcdId(GenerateId.getNextId(id));
//            insertMarketFunctionComparisonDeliverydays(insertValue);
            id++;
            System.out.println("插入成功");
        }
    }
    public Long selectLastId(){
        return marketFunctionComparisonDeliverydaysMapper.selectLastId();
    }
    // 提取车型的前半段数字部分的函数
    private String extractVehicleType(String vehicleModel) {
        String regex = "(\\D*)(\\d+)(-.*)"; // 匹配车型的前半段数字部分的正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(vehicleModel);
        if (matcher.find()) {
            return matcher.group(2); // 返回匹配的数字部分
        } else {
            return ""; // 如果未匹配到则返回空字符串，需根据实际情况处理
        }
    }

    /**
     * 查询同配置车型交货天数对比功能
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 同配置车型交货天数对比功能
     */
    @Override
    public MarketFunctionComparisonDeliverydays selectMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId)
    {
        return marketFunctionComparisonDeliverydaysMapper.selectMarketFunctionComparisonDeliverydaysByMfcdId(mfcdId);
    }

    /**
     * 查询同配置车型交货天数对比功能列表
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 同配置车型交货天数对比功能
     */
    @Override
    public List<MarketFunctionComparisonDeliverydays> selectMarketFunctionComparisonDeliverydaysList(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        return marketFunctionComparisonDeliverydaysMapper.selectMarketFunctionComparisonDeliverydaysList(marketFunctionComparisonDeliverydays);
    }

    /**
     * 新增同配置车型交货天数对比功能
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 结果
     */
    @Override
    public int insertMarketFunctionComparisonDeliverydays(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        return marketFunctionComparisonDeliverydaysMapper.insertMarketFunctionComparisonDeliverydays(marketFunctionComparisonDeliverydays);
    }

    /**
     * 修改同配置车型交货天数对比功能
     * 
     * @param marketFunctionComparisonDeliverydays 同配置车型交货天数对比功能
     * @return 结果
     */
    @Override
    public int updateMarketFunctionComparisonDeliverydays(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        return marketFunctionComparisonDeliverydaysMapper.updateMarketFunctionComparisonDeliverydays(marketFunctionComparisonDeliverydays);
    }

    /**
     * 批量删除同配置车型交货天数对比功能
     * 
     * @param mfcdIds 需要删除的同配置车型交货天数对比功能主键
     * @return 结果
     */
    @Override
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdIds(Long[] mfcdIds)
    {
        return marketFunctionComparisonDeliverydaysMapper.deleteMarketFunctionComparisonDeliverydaysByMfcdIds(mfcdIds);
    }

    /**
     * 删除同配置车型交货天数对比功能信息
     * 
     * @param mfcdId 同配置车型交货天数对比功能主键
     * @return 结果
     */
    @Override
    public int deleteMarketFunctionComparisonDeliverydaysByMfcdId(Long mfcdId)
    {
        return marketFunctionComparisonDeliverydaysMapper.deleteMarketFunctionComparisonDeliverydaysByMfcdId(mfcdId);
    }
}
