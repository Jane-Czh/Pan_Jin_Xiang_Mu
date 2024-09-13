package com.ruoyi.market.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
//import com.heli.production.domain.ProductionTable;



import com.ruoyi.common.core.controller.BaseController;

import com.ruoyi.market.domain.*;

import com.ruoyi.market.mapper.MarketOrderSumnumberMapper;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
import com.ruoyi.market.service.IMarketCarTypeService;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
import com.ruoyi.market.service.IMarketSalesTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/market/Index")
public class MarketIndexController extends BaseController {


    /*
    指标11
    */
//TODO 规定起止时间只看年月，现在部分是看到日期的
    @Autowired
     private IMarketSalesTableService iMarketSalesTableService;
    @Autowired
    private IMarketAfterSaleLedgerService iMarketAfterSaleLedgerService;
    @Autowired
    private IMarketCommercialVehicleTableService iMarketCommercialVehicleTableService;

    @Autowired
    private IMarketCarTypeService iMarketCarTypeService;

    //用来获取订单总数的列表
    @Autowired
    private MarketOrderSumnumberMapper marketOrderSumnumberMapper;


    @PostMapping("/IClassProportion")
    public  List<VoEntity>  ProportionOfClassITrams(@RequestBody MarketSalesTable marketSalesTable){
//        System.out.println("获取到的实体类"+marketSalesTable);
//        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
//        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        Date orderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime();
         String vehicleModel = marketSalesTable.getVehicleModel();
//        System.out.println("获取到的车型"+vehicleModel);
//        String substring = vehicleModel.substring(0, 3);

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        List<String> specifiedBranches = Arrays.asList("天津", "山东", "辽宁", "哈尔滨", "长春", "内蒙古", "北京", "河北");

//        List<MarketCarType> marketCarTables = iMarketCarTypeService.selectMarketCarTypeList1();

//        System.out.println("获取到的车型数据： "+marketSalesTables);
//        Map<String, String> vehicleCategoryMap = marketCarTables.stream()
//                .collect(Collectors.toMap(MarketCarType::getVehicleModel, MarketCarType::getCategory));

//        System.out.println("所有的车型数据： "+vehicleCategoryMap);

        int numberInput=0;
        //获取订单总台数做为分母
//       if(marketSalesTable.getNumberInput()==null||marketSalesTable.getNumberInput()==0)
//       {
//           numberInput=1;
//       }else
//
//           numberInput= marketSalesTable.getNumberInput();
//        long allSum = marketSalesTables.stream().mapToLong(MarketSalesTable::getNumber).sum();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        int finalNumberInput = 1;
        Map<String, Map<String, Double>> cpd = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter((MarketSalesTable a) ->
        {         LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           //判段是否超出长度
            String model = a.getVehicleModel();
            String branch = a.getBranch();
//            String category = vehicleCategoryMap.get(model);

            return  model != null && model.length() >= 3 && model.substring(0, 3).equals("CPD") &&
                    (( !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime))
                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
                    && specifiedBranches.contains(branch); // 增加branch筛选条件
//                    && "I".equals(category); // 增加车型类别筛选条件;
        }).collect(Collectors.groupingBy(
           a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                 Collectors.groupingBy(
                         MarketSalesTable::getBranch,
                         Collectors.collectingAndThen(
                                 Collectors.summingLong(MarketSalesTable::getNumber),
                                 sum -> Math.round((sum / (double) finalNumberInput) * 1000) / 1000.0
                         )
                 )                ));
//        {2024-01={天津={10},上海={2},合肥={4}}}

//        System.out.println("收集到的list"+cpd);

        //ToDo 按照地区和日期统计地区的订单总数。
         List<MarketOrderSumnumber> marketOrderSumnumbers = marketOrderSumnumberMapper.selectMarketOrderSumnumberList1();
        System.out.println("测试查询"+marketOrderSumnumbers);
        Map<String, Map<String, Long>> result = marketOrderSumnumbers.stream()
                // 根据时间分组
                .collect(Collectors.groupingBy(
                        a -> a.getMarketTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        // 根据地区分组并求和
                        Collectors.groupingBy(
                                MarketOrderSumnumber::getMarketLedger,
                                Collectors.summingLong(MarketOrderSumnumber::getMarketOrderSumnumber)
                        )
                ));
//        System.out.println("测试订单结果"+result);

        MarketIndexResult marketIndexResult = new MarketIndexResult();
//        marketIndexResult.setMapMap(cpd);
//
         Map<String, Map<String, Double>> stringMapMap = calculateOrderRatios(cpd, result);
//        System.out.println(stringMapMap);
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(stringMapMap);
        return voEntities;
//收集到的list{2024-02={山东=1.0, 长春=2.0, 哈尔滨=2.0, 辽宁=16.0, 天津=55.0, 江苏=1.0},
// 2024-01={山东=13.0, 内蒙古=4.0, 长春=6.0, 辽宁=20.0, 哈尔滨=19.0, 天津=28.0, 浙江=5.0, 江苏=1.0}, 2024-04={山东=16.0, 内蒙古=2.0, 长春=3.0, 哈尔滨=13.0, 辽宁=34.0, 天津=84.0, 浙江=9.0, 江苏=2.0}, 2024-03={山东=61.0, 内蒙古=2.0, 长春=26.0, 辽宁=37.0, 哈尔滨=31.0, 天津=44.0, 浙江=5.0}, 2024-06={山东=14.0, 内蒙古=3.0, 长春=6.0, 福建=1.0, 辽宁=33.0, 哈尔滨=20.0, 天津=30.0, 浙江=2.0}, 2024-05={山东=17.0, 内蒙古=2.0, 永恒力=6.0, 长春=5.0, 辽宁=25.0, 哈尔滨=39.0, 天津=34.0, 浙江=1.0}, 2024-08={山东=16.0, 内蒙古=2.0, 长春=5.0, 辽宁=36.0, 哈尔滨=41.0, 天津=18.0, 河北=34.0, 北京=4.0}, 2024-07={山东=24.0, 内蒙古=3.0, 长春=10.0, 哈尔滨=42.0, 辽宁=27.0, 天津=26.0, 河北=11.0, 浙江=5.0, 江苏=2.0, 北京=1.0}}
    }

    public static Map<String, Map<String, Double>> calculateOrderRatios(
            Map<String, Map<String, Double>> cpd,
            Map<String, Map<String, Long>> ordersum) {

        Map<String, Map<String, Double>> orderRatios = new TreeMap<>();

        // 遍历订单数Map
        for (Map.Entry<String, Map<String, Double>> entryCpd : cpd.entrySet()) {
            String month = entryCpd.getKey();
            Map<String, Double> regionsCpd = entryCpd.getValue();

            // 确保在订单比例Map中存在该月份的条目
            orderRatios.putIfAbsent(month, new HashMap<>());

            // 获取该月份的订单总数Map
            Map<String, Long> regionsOrdersum = ordersum.get(month);

            // 计算每个地区的订单比例
            for (Map.Entry<String, Double> regionCpd : regionsCpd.entrySet()) {
                String region = regionCpd.getKey();
                Double cpdValue = regionCpd.getValue();

                // 确保订单总数Map中存在该地区
                if (regionsOrdersum != null && regionsOrdersum.containsKey(region)) {
                    Long ordersumValue = regionsOrdersum.get(region);
                    Double ratio = cpdValue / ordersumValue; // 计算比例
                    orderRatios.get(month).put(region, ratio);
                }
            }
        }

        return orderRatios;
    }
    /*
    *指标12   TODO CPCD是否也算CPC的车型？
    **/
    @PostMapping("/IVVClassProportion")
    public   List<VoEntity> ProportionOfClassIVVTrams(@RequestBody MarketSalesTable marketSalesTable){

//        System.out.println("获取到的实体类"+marketSalesTable);
//        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
//        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        List<String> specifiedBranches = Arrays.asList("天津", "山东", "辽宁", "哈尔滨", "长春", "内蒙古", "北京", "河北");

        int numberInput=0;
        //获取订单总台数做为分母
        if(marketSalesTable.getNumberInput()==null||marketSalesTable.getNumberInput()==0)
        {
            numberInput=1;
        }else

            numberInput= marketSalesTable.getNumberInput();
//        long allSum = marketSalesTables.stream().mapToLong(MarketSalesTable::getNumber).sum();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        int finalNumberInput = numberInput;
        Map<String, Map<String, Double>> cpc = marketSalesTables.stream()
                  .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter((MarketSalesTable a) ->
        {         LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //判段是否超出长度
            String model = a.getVehicleModel();
            String branch = a.getBranch();
            System.out.println("网点获取：  "+branch);

            return  model != null && model.length() >= 3 && model.substring(0, 3).equals("CPC") &&
                    ( (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime))
                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
                    && specifiedBranches.contains(branch);
        })
                .peek(filtered -> System.out.println("筛选后的数据: " + filtered))
                .collect(Collectors.groupingBy(
                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                Collectors.groupingBy(
                        MarketSalesTable::getBranch,
                        Collectors.collectingAndThen(
                                Collectors.summingLong(MarketSalesTable::getNumber),
                                sum -> Math.round((sum / (double) finalNumberInput) * 1000) / 1000.0
                        )
                )                ));
//        System.out.println("按照地区分类，选出车型为cpc和日期符合的数据"+cpc);

//         List<VoEntity> convert = VoEntity.convert(cpc);
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(cpc);
//        MarketIndexResult marketIndexResult = new MarketIndexResult();
//        marketIndexResult.setMapMap(cpc);

        return voEntities;
    }

    /*
     *指标13各主要车型产量月度比率
//     **/
    @PostMapping("/ModelMonthlyProduction")
    public   List<VoEntity>  ModelMonthlyProduction(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){
            //获取到商品车台账表全部数据
         List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        // 获取newtype表中的数据
//        List<NewType> newTypes = iNewTypeService.selectNewTypeList(); // 假设有一个iNewTypeService来获取newtype表的数据

        System.out.println("获取所有商品车台账数据"+marketCommercialVehicleTables);
        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //没有车型表，先写具体主要车型
        //筛选出cpc,cpd,cpcd的所有月份对应的数量,按照上线日期确定产量
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        //各个月份各个车型的月度产量
//         Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream().filter(a -> {
//
//            LocalDate acceptanceTime = a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
//            return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
//        })
//                .collect(Collectors.groupingBy(
//                        a -> a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
//                        Collectors.groupingBy(
//                                a -> getVehicleModelPrefix(a.getVehicleModel()), Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
//                        )
//                ));
        //各个月份所有车型的总产量
        Map<String, Long> monthlyTotalProduction = marketCommercialVehicleTables.stream()
                .filter(a -> a.getLaunchDate() != null) // 过滤掉 launchDate 为空的元素
                .filter(a -> {
                    LocalDate acceptanceTime = a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 检查日期是否在起止时间范围内，包括等于起止时间的情况
                    return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
                })
                .collect(Collectors.groupingBy(
                        a -> a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                ));
        //各个月份各个车型的月度产量比例
        Map<String, Map<String, Double>> collect = marketCommercialVehicleTables.stream()
                .filter(a -> a.getLaunchDate() != null) // 过滤掉 launchDate 为空的元素
                .filter(a -> {
                    LocalDate acceptanceTime = a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 检查日期是否在起止时间范围内，包括等于起止时间的情况
                    return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
                })
                .collect(Collectors.groupingBy(
                        a -> a.getLaunchDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.groupingBy(
                                a -> getVehicleModelPrefix(a.getVehicleModel()),
//                                a -> carModelToTypeMap.getOrDefault(a.getVehicleModel(), "Unknown"), // 使用newtype中的type属性表示车型
                                Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream().collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e -> (double) e.getValue() / monthlyTotalProduction.get(entry.getKey())
                        ))
                ));


//         List<Map<String, Map<Integer, Long>>> listMap = marketIndexResult.getListMap();
        System.out.println("筛选出cpc,cpd,cpcd的所有月份对应的数量"+collect);
//        System.out.println(cpd);
//        System.out.println(cpcd);
//        listMap.add(cpc);listMap.add(cpd);listMap.add(cpcd);
        // 过滤掉 "Unknown" 的数据
//        collect.entrySet().removeIf(entry -> "Unknown".equals(entry.getKey()));
         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(collect);


        return voEntities;
    }
 /*
     *指标14 各主要车型同比增长比例
     **/

    //TODO 前端日期选择器的变更；
    @PostMapping("/ModelGrowthProportion")
    public List<VoEntity> ModelGrowthProportion(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("接收对象的起止时间"+marketSalesTable);
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();


        // 获取newtype表中的数据
//        List<NewType> newTypes = iNewTypeService.selectNewTypeList(); // 假设有一个iNewTypeService来获取newtype表的数据

        // 创建一个Map来存储carmodel到type的映射
//        Map<String, String> carModelToTypeMap = newTypes.stream()
//                .collect(Collectors.toMap(NewType::getCarModel, NewType::getType));


        //取出今年，取出当年的，各个主要车型的各个月的总数
        System.out.println(marketSalesTables);
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
         List<MarketSalesTable> collect = marketSalesTables.stream().filter(a -> a.getOrderAcceptanceTime().getYear() == 2024).collect(Collectors.toList());
        System.out.println("获取2024的数据"+collect);
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        int currentYear = Year.now().getValue();
         Map<String, Map<String, Long>> curMonth = marketSalesTables.stream()
                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                 .filter(a -> {
                     LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     int current = LocalDate.now().getYear();// 获取当前年份
                    //筛选2024年满足起止时间的数据，在选出年份为2024，且月份是开始那个月的数据
                     return (acceptanceTime.isAfter(startTime) || acceptanceTime.isEqual(startTime))
                             && (acceptanceTime.isBefore(endTime) || acceptanceTime.isEqual(endTime))
                             && (acceptanceTime.getYear() == current);
//                             && (acceptanceTime.getYear() == current)&&(acceptanceTime.getMonthValue() ==startTime.getMonthValue());// 添加当前年份的过滤条件
                 }).collect(Collectors.groupingBy(
        m -> getVehicleModelPrefix(m.getVehicleModel()),
//                         m -> carModelToTypeMap.getOrDefault(m.getVehicleModel(), "Unknown"), // 使用newtype中的type属性表示车型
        Collectors.groupingBy(
                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                Collectors.summingLong(MarketSalesTable::getNumber)
        )
));

        System.out.println("取出当年的，各个车型的各个月的总数"+curMonth);
        // 取出去年，各个主要车型的各个月的总数
            //取出去年的年份。
        Map<String, Map<String, Long>> before = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter(a -> {
                    LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int current = LocalDate.now().getYear(); // 获取当前年份
                    int previousYear = current - 1; // 获取当前年份的前一年
                    System.out.println("去年的"+previousYear);
                    // 筛选出时间为前一年的数据，且月份与当前年份相同
                    return acceptanceTime.getYear() == previousYear;
//                            && (acceptanceTime.getMonthValue() == startTime.getMonthValue() );

                }).collect(Collectors.groupingBy(
                        m -> getVehicleModelPrefix(m.getVehicleModel()),
//                        m -> carModelToTypeMap.getOrDefault(m.getVehicleModel(), "Unknown"), // 使用newtype中的type属性表示车型
                        Collectors.groupingBy(
                                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                                Collectors.summingLong(MarketSalesTable::getNumber)
                        )
                ));
        System.out.println("取出当前年份前一年对应月份的数据：" + before);
//
//        // 初始化结果Map
//        Map<String, Map<String, Long>> res = new HashMap<>(curMonth);
//
//// 遍历before中的每一个条目
//        before.forEach((prefix, beforeMap) ->
//                // 对于beforeMap中的每一项，将其合并到res中的对应prefix下
//                beforeMap.forEach((date, value) ->
//                        // 使用merge方法合并对应的值
//                        res.computeIfAbsent(prefix, k -> new HashMap<>()).merge(date, value, Long::sum)
//                )
//        );
         Map<String, Map<String, Double>> stringMapMap = divideMaps(curMonth, before);
        System.out.println("比值"+stringMapMap);
        // 过滤掉 "Unknown" 的数据
//        stringMapMap.entrySet().removeIf(entry -> "Unknown".equals(entry.getKey()));
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(stringMapMap);

        return voEntities;

    }
    //计算指标14的比值
    public static Map<String, Map<String, Double>> divideMaps(
            Map<String, Map<String, Long>> curMonth,
            Map<String, Map<String, Long>> before) {

        Map<String, Map<String, Double>> result = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> curMonthEntry : curMonth.entrySet()) {
            String vehicleModel = curMonthEntry.getKey();
            Map<String, Long> curMonthValues = curMonthEntry.getValue();

            if (before.containsKey(vehicleModel)) {
                Map<String, Long> beforeValues = before.get(vehicleModel);
                Map<String, Double> resultValues = new HashMap<>();

                for (Map.Entry<String, Long> curMonthValueEntry : curMonthValues.entrySet()) {
                    String curMonthDate = curMonthValueEntry.getKey();
                    Long curMonthValue = curMonthValueEntry.getValue();

                    // Parse the year from the date string
                    String[] parts = curMonthDate.split("-");
                    int curYear = Integer.parseInt(parts[0]);
                    int beforeYear = curYear - 1;
                    String beforeDate = beforeYear + "-" + parts[1];

                    if (beforeValues.containsKey(beforeDate)) {
                        Long beforeValue = beforeValues.get(beforeDate);
                        if (beforeValue != 0) { // Check for division by zero
                            resultValues.put(curMonthDate, (double) curMonthValue / beforeValue);
                        } else {
                            resultValues.put(curMonthDate, 0.0); // Or handle division by zero as you see fit
                        }
                    }
                }

                result.put(vehicleModel, resultValues);
            }
        }

        return result;
    }

    // 获取 VehicleModel 的前缀方法
    private static String getVehicleModelPrefix(String vehicleModel) {
        // 这里根据你的业务逻辑来提取前缀，比如提取第一个单词或者特定的前缀D
        if (vehicleModel.startsWith("CPCD")) {
            return "CPCD";
        } else if (vehicleModel.startsWith("CPD")) {
            return "CPD";
        } else if (vehicleModel.startsWith("CPC")) {
            return "CPC";
        } else {
            // 如果没有匹配到特定的前缀，可以返回默认值或者抛出异常，根据你的业务需求来处理
            return "未填写车型";
        }
    }
    /*
    * 指标15 各销售网点月度各主要车型分布表
    * */
    @PostMapping("/ModelBranchProportion")
    public List<VoEntity> ModelBranchProportion(@RequestBody MarketSalesTable marketSalesTable){

        //TODO 前端日期选择器的变更；

              int    currentMonth  =1;
        System.out.println("获取到的开始时间"+marketSalesTable.getStartTime());
        //把开始时间做为查询条件
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 获取newtype表中的数据
//        List<NewType> newTypes = iNewTypeService.selectNewTypeList(); // 假设有一个iNewTypeService来获取newtype表的数据

        // 创建一个Map来存储carmodel到type的映射
//        Map<String, String> carModelToTypeMap = newTypes.stream()
//                .collect(Collectors.toMap(NewType::getCarModel, NewType::getType));


        marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter(a -> {
            LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return acceptanceTime.getYear() == startTime.getYear() &&
                    acceptanceTime.getMonthValue() == startTime.getMonthValue();// 添加当前年月的过滤条件
        });
        //获取到每个月的数据
         List<MarketSalesTable> monthMarketSalesTables = marketSalesTables.stream()
                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                 .filter(a -> a.getOrderAcceptanceTime().getMonth()+1 == (currentMonth)).collect(Collectors.toList());
        System.out.println("按照月份筛选的数据"+monthMarketSalesTables);



//      筛选完月份的数据，获取每个月按照地区分类的主要车型的数据
        Map<String, Map<String, Long>> collect1 = monthMarketSalesTables.stream()
                .collect(Collectors.groupingBy(
                        MarketSalesTable::getBranch, // 按照地区分组
                        Collectors.groupingBy(
                                a -> getVehicleModelPrefix(a.getVehicleModel()), // 按照车型前缀分组
//                                a -> carModelToTypeMap.getOrDefault(a.getVehicleModel(), "Unknown"), // 使用newtype中的type属性表示车型

                                Collectors.summingLong(MarketSalesTable::getNumber) // 计算每个分组中 Number 的和
                        )
                ));


        System.out.println("按照网点和月份的分组"+collect1);

        // 过滤掉 "Unknown" 的数据
//        collect1.entrySet().removeIf(entry -> "Unknown".equals(entry.getKey()));

         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect1);


        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMapMap(collect1);
        return voEntities;

    }
    /*
     * 指标16各网点已接单未发车总台数
     * */
    @PostMapping("/OrderWaitSend")
    public  List<VoEntity> OrderWaitSend(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                 .filter(a -> {
            LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
            return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
        }).collect(Collectors.groupingBy(
                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
                , Collectors.groupingBy(
                        MarketSalesTable::getBranch
                        , Collectors.summingLong(MarketSalesTable::getNumber))
        ));

        // 对结果进行排序
        Map<String, Map<String, Long>> sortedCollect = collect.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

//        marketSalesTables.stream().filter((MarketSalesTable a) ->
//        {
//            return a.getActualDepartureDate() == null &&
//                    marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
//                    && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth();
//
//        })

        System.out.println("选出实际发车日期为空的,再按照地区和月份统计为发车的数量"+collect);
        System.out.println("选出实际发车日期为空的,再按照地区和月份统计为发车的数量"+sortedCollect);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(sortedCollect);
        return voEntities;
    }
    /*
     * 指标17各网点未排产订单台数
     * */
    @PostMapping("/BranchWaitProduction")
    public  List<VoEntity> BranchWaitProduction(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                 .filter((MarketSalesTable a) ->
        {          LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查日期是否在起止时间范围内，包括等于起止时间的情况,并且满足车型为空或者车型中不包含中文的数据
            return
                    (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
                    && (a.getCarNumber() == null || containsChinese(a.getCarNumber()));
//                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());
//                   LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
//            return (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
//                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());

        })
                .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
                        , Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.summingLong(MarketSalesTable::getNumber))
                ));

        System.out.println("选出车号为空或者为汉字的,再按照地区和月份统计数量"+collect);


         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);
        return voEntities;
    }
    //处理包含中文的车型
    private static boolean containsChinese(String str) {
        for (char c : str.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
                return true;
            }
        }
        return false;
    }

    /*
     * 指标18各网点已完工未发车订单数
     * */
    @PostMapping("/OverWorkWaitDelivery")
    public  List<VoEntity> OverWorkWaitDelivery(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                 .filter((MarketSalesTable a) ->
        {
            LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查日期是否在起止时间范围内，包括等于起止时间的情况,系统交货期有日期且实际发车日期为空
            return
                    (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
                            && a.getActualDepartureDate() == null && a.getSystemDeliveryTime() != null;


        })
                .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)


                        , Collectors.groupingBy(
                                MarketSalesTable::getBranch
                                , Collectors.summingLong(MarketSalesTable::getNumber))
                ));
        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);

         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);
        return voEntities;
    }

    /*
    TODO 只返回数量吗？

     * 指标19各网点已到期未完工订单数
     * 销售台账）
    未排产=当日>订单系统交货期 且 车号为空或汉字的台数
    （商品车台账）
    排产未完工=精整日期为空 且 当日 > 计划完工期
     * */
    @PostMapping("/OvertimedOrderNumber")
    public   List<VoEntity> OvertimedOrderNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        //获取到销售台账全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //获取到商品车台账全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();


        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素

                .filter((MarketSalesTable a) ->
        {          LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //获取系统交货日期
            LocalDate SystemDeliveryTime = Optional.ofNullable(a.getSystemDeliveryTime())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            if(SystemDeliveryTime!=null){
                System.out.println("系统交货期"+SystemDeliveryTime);
            }

            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            // 检查日期是否在起止时间范围内，包括等于起止时间的情况,并且满足车型为空或者车型中不包含中文的数据
            return
                    SystemDeliveryTime!=null&&  (!SystemDeliveryTime.isBefore(startTime) && !SystemDeliveryTime.isAfter(endTime)
                            || SystemDeliveryTime.isEqual(startTime) || SystemDeliveryTime.isEqual(endTime))
            //当日>订单系统交货期
                            && (currentDate.isAfter(SystemDeliveryTime))
            //且车号为空或汉字
                            &&(a.getCarNumber() == null || containsChinese(a.getCarNumber()));
//                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());
//                   LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
//            return (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
//                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());

        })
                .filter(a -> a.getSystemDeliveryTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素

                .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
                        , Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.summingLong(MarketSalesTable::getNumber))
                ));
        System.out.println("选出车号为空或者为汉字的,再按照地区和月份统计数量"+collect);


        //规定年月日的格式
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect1 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            // 获取当前日期
            LocalDate currentDate = LocalDate.now();
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                         //  当日 > 计划完工期
                            &&(currentDate.isAfter(plancompletion))&&
                          //  精整日期为空
                             precisioncompletion == null ;
//            precisioncompletion.isBefore(plancompletion);

        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));
        System.out.println("排产未完工=精整日期为空 且 当日 > 计划完工期,再按照地区和月份统计数量"+collect);


        //销售台账
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);
        //商品车台账
        List<VoEntity> voEntities1 = VoEntity.convertCpdToVoEntities(collect1);
        return voEntities;
    }
    /*
     * 指标39 商品车计划兑现率
     * */
    @PostMapping("/CommercialVehicleRate")
    public  List<VoEntity> CommercialVehicleRate(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
         Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream()
                 .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                 .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                 .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&
            precisioncompletion != null && precisioncompletion.isBefore(plancompletion);
//            precisioncompletion.isBefore(plancompletion);

        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));

        System.out.println("计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期"+collect);

        //筛选出计划完工日期在起止时间内的数据，
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect1 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
            &&precisioncompletion!=null;



        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));
        System.out.println("筛选出计划完工日期在起止时间内的数据"+collect1);

        Map<String, Map<String, Double>> ratioMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> entry1 : collect1.entrySet()) {
            String year = entry1.getKey();
            Map<String, Long> monthlyTotal = entry1.getValue();
            Map<String, Long> collectData = collect.getOrDefault(year, new HashMap<>());

            Map<String, Double> ratioYearMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : collectData.entrySet()) {
                String month = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(month, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(ratioMap);
        return voEntities;
    }

    /*
     * 指标40 订单兑现率
     * */
    @PostMapping("/OrderAchieveRate")
    public  List<VoEntity> OrderAchieveRate(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
//        Map<String, Map<String, Long>> collect =
        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                .filter(a -> a.getActualDepartureDate() != null) // 过滤掉 getActualDepartureDate 为空的元素

                .filter(a ->
        {
            LocalDate actualDepartureDate = Optional.ofNullable(a.getActualDepartureDate())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
           //防止空指针异常


            LocalDate systemDeliveryTime = Optional.ofNullable(a.getSystemDeliveryTime())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);

            LocalDate orderSystemDeliveryTime = Optional.ofNullable(a.getOrderSystemDeliveryTime())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);

            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //实际发车日期W，系统交货日期X任一列有日期且小于等于订单系统交货期T的数量
            return
//                    (!OrderSystemDeliveryTime.isBefore(startTime) && !OrderSystemDeliveryTime.isAfter(endTime)
//                            || OrderSystemDeliveryTime.isEqual(startTime) || OrderSystemDeliveryTime.isEqual(endTime))
//                            && OrderSystemDeliveryTime != null&&
//                            //实际发车日期W，系统交货日期X任一列有日期且小于等于订单系统交货期T的数量
//                            (ActualDepartureDate.isBefore(OrderSystemDeliveryTime)||SystemDeliveryTime.isBefore(OrderSystemDeliveryTime)
//                                    || ActualDepartureDate.isEqual(OrderSystemDeliveryTime) || SystemDeliveryTime.isEqual(OrderSystemDeliveryTime)
//                            );
//
//
//        })
                    orderSystemDeliveryTime != null &&
                            (!orderSystemDeliveryTime.isBefore(startTime) && !orderSystemDeliveryTime.isAfter(endTime)
                                    || orderSystemDeliveryTime.isEqual(startTime) || orderSystemDeliveryTime.isEqual(endTime)) &&
                            (actualDepartureDate != null && actualDepartureDate.isBefore(orderSystemDeliveryTime)
                                    || systemDeliveryTime != null && systemDeliveryTime.isBefore(orderSystemDeliveryTime)
                                    || actualDepartureDate != null && actualDepartureDate.isEqual(orderSystemDeliveryTime)
                                    || systemDeliveryTime != null && systemDeliveryTime.isEqual(orderSystemDeliveryTime));
        })
        .collect(Collectors.groupingBy(
                a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1)
                        , Collectors.summingLong(MarketSalesTable::getNumber))));


        System.out.println("实际发车日期W，系统交货日期X任一列有日期且小于等于订单系统交货期T的数量"+collect);

        //筛选出计划完工日期在起止时间内的数据，
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量

         Map<String, Map<String, Long>> collect1 = marketSalesTables.stream()
                 .filter(a -> a.getOrderSystemDeliveryTime() != null) // 过滤掉 getOrderSystemDeliveryTime 为空的元素

                 .filter(a ->
        {
//            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
//                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                    .orElse(null);
            LocalDate OrderSystemDeliveryTime = a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            return
                    (!OrderSystemDeliveryTime.isBefore(startTime) && !OrderSystemDeliveryTime.isAfter(endTime)
                            || OrderSystemDeliveryTime.isEqual(startTime) || OrderSystemDeliveryTime.isEqual(endTime))
                            && OrderSystemDeliveryTime != null;


        }).collect(Collectors.groupingBy(
                a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1)
                        , Collectors.summingLong(MarketSalesTable::getNumber))));
//                        .collect(Collectors.groupingBy(
//                a -> a.get().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
//                Collectors.groupingBy(
//                        a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
//                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
//                )
//        ));
        System.out.println("按照每月筛选出订单系统交货日期在起止时间内的数据"+collect1);

        Map<String, Map<String, Double>> ratioMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> entry1 : collect1.entrySet()) {
            String year = entry1.getKey();
            Map<String, Long> monthlyTotal = entry1.getValue();
            Map<String, Long> collectData = collect.getOrDefault(year, new HashMap<>());

            Map<String, Double> ratioYearMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : collectData.entrySet()) {
                String month = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(month, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("实际发车日期W，系统交货日期X任一列有日期且小于等于订单系统交货期T的数量"+collect);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(ratioMap);
        return voEntities;
    }

    /*
     * 指标42 大叉位叉车整机交货天数平均数
     * TODO 筛选出平均值>20%的数据，过滤对应的车型
     * */
    @PostMapping("/AvergaeDay")
    public  List<VoEntity> AvergaeDay(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&
                            precisioncompletion != null && precisioncompletion.isBefore(plancompletion);
//            precisioncompletion.isBefore(plancompletion);

        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));

        System.out.println("计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期"+collect);

        //筛选出计划完工日期在起止时间内的数据，
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect1 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&precisioncompletion!=null;



        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));
        System.out.println("筛选出计划完工日期在起止时间内的数据"+collect1);

        Map<String, Map<String, Double>> ratioMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> entry1 : collect1.entrySet()) {
            String year = entry1.getKey();
            Map<String, Long> monthlyTotal = entry1.getValue();
            Map<String, Long> collectData = collect.getOrDefault(year, new HashMap<>());

            Map<String, Double> ratioYearMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : collectData.entrySet()) {
                String month = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(month, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(ratioMap);
        return voEntities;
    }

    /*
     * 指标43 电动车整机交货天数
超过平均数20%统计台数
     * */
    @PostMapping("/ElCarAvergaeDay")
    public  List<VoEntity> ElCarAvergaeDay(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&
                            precisioncompletion != null && precisioncompletion.isBefore(plancompletion);
//            precisioncompletion.isBefore(plancompletion);

        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));

        System.out.println("计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期"+collect);

        //筛选出计划完工日期在起止时间内的数据，
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect1 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&precisioncompletion!=null;



        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));
        System.out.println("筛选出计划完工日期在起止时间内的数据"+collect1);

        Map<String, Map<String, Double>> ratioMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> entry1 : collect1.entrySet()) {
            String year = entry1.getKey();
            Map<String, Long> monthlyTotal = entry1.getValue();
            Map<String, Long> collectData = collect.getOrDefault(year, new HashMap<>());

            Map<String, Double> ratioYearMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : collectData.entrySet()) {
                String month = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(month, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(ratioMap);
        return voEntities;
    }

    /*
     * 指标44 小吨位内燃叉车整机交货天数
超过平均数20%统计台数
     * */
    @PostMapping("/LitterTAvergaeDay")
    public  List<VoEntity> LitterTAvergaeDay(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&
                            precisioncompletion != null && precisioncompletion.isBefore(plancompletion);
//            precisioncompletion.isBefore(plancompletion);

        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));

        System.out.println("计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期"+collect);

        //筛选出计划完工日期在起止时间内的数据，
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect1 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
        {
            LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                    .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .orElse(null);
            LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            return
                    (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                            || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                            &&precisioncompletion!=null;



        }).collect(Collectors.groupingBy(
                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter1),
                        Collectors.summingLong(MarketCommercialVehicleTable::getNumber)
                )
        ));
        System.out.println("筛选出计划完工日期在起止时间内的数据"+collect1);

        Map<String, Map<String, Double>> ratioMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> entry1 : collect1.entrySet()) {
            String year = entry1.getKey();
            Map<String, Long> monthlyTotal = entry1.getValue();
            Map<String, Long> collectData = collect.getOrDefault(year, new HashMap<>());

            Map<String, Double> ratioYearMap = new HashMap<>();
            for (Map.Entry<String, Long> entry : collectData.entrySet()) {
                String month = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(month, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(ratioMap);
        return voEntities;
    }


    /*
     * 指标72  日/月/年接单数
     * */
    @PostMapping("/OrderReceiveNumber")
    public   List<VoEntity>  OrderReceiveNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM-dd");
        //TODO 修改了时间格式，后续可能要改
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("YYYY");


        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter(a -> {
                    LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 获取结束时间前一个月的 LocalDate
                    //For example, 2007-03-31 minus one month would result in the invalid date 2007-02-31.
                    // Instead of returning an invalid result,
                    // the last valid day of the month, 2007-02-28, is selected instead.

                    LocalDate oneMonthBefore = endTime.minusMonths(1);
                    //TODO 把startTime时间改为了oneMonthBefore
//                    return !acceptanceTime.isBefore(oneMonthBefore) && !acceptanceTime.isAfter(endTime)
//                            || acceptanceTime.isEqual(oneMonthBefore) || acceptanceTime.isEqual(endTime);
                    // 打印结果
//                    System.out.println("结束时间: " + endTime);
//                    System.out.println("结束时间前一个月: " + oneMonthBefore);
                    // 检查日期是否在起止时间范围内，包括等于起止时间的情况
                    return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
                })
                .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(yearFormatter),
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(monthFormatter),
                                        Collectors.summingLong(MarketSalesTable::getNumber)
                                ),
                                map -> {
                                    // 对内部的 Map 进行排序
                                    return map.entrySet().stream()
                                            .sorted(Map.Entry.comparingByKey())
                                            .collect(Collectors.toMap(
                                                    Map.Entry::getKey,
                                                    Map.Entry::getValue,
                                                    (e1, e2) -> e1,
                                                    LinkedHashMap::new
                                            ));
                                }
                        )
                ));

// 对外层的 Map 进行排序
        Map<String, Map<String, Long>> sortedCollect = collect.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照地区和月份统计数量"+collect);
        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照地区和月份统计数量"+sortedCollect);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(sortedCollect);
        return voEntities;
    }

    /*
     * 指标73  日/月/年交货数
     * 可能需要修改 传出参数；YY-MM-DD
     * */
    @PostMapping("/OrderDeliveryNumber")
    public   List<VoEntity> OrderDeliveryNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");


        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                //过滤掉为空的日期
                .filter(a -> a.getSystemDeliveryTime() != null)
                .filter(a -> {
                    LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    // 检查日期是否在起止时间范围内，包括等于起止时间的情况
                    return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
                })
                .collect(Collectors.groupingBy(
                        a -> a.getSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Year),
                        Collectors.groupingBy(
                                a -> a.getSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                                Collectors.summingLong(MarketSalesTable::getNumber)
                        )
                ));

//        Map<Date, Long> collect = marketSalesTables.stream()
//                .collect(Collectors.groupingBy(
//                        a -> a.getSystemDeliveryTime()
//                        , Collectors.summingLong(MarketSalesTable::getNumber))
//                );

        System.out.println("按照交货日期分组，统计台数"+collect);


         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);

        return voEntities;
    }

}
