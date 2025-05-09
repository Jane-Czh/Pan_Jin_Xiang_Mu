package com.ruoyi.market.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
//import com.heli.production.domain.ProductionTable;



import com.ruoyi.common.core.controller.BaseController;

import com.ruoyi.market.domain.*;

import com.ruoyi.market.mapper.AverageResultMapper;
import com.ruoyi.market.mapper.MarketCommercialVehicleTableMapper;
import com.ruoyi.market.mapper.MarketOrderSumnumberMapper;
import com.ruoyi.market.service.*;
import com.ruoyi.market.mapper.MarketSalesTableMapper;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
import com.ruoyi.market.service.IMarketSalesTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
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

    @Autowired
    private IMarketMainTypeService iMarketMainTypeService;

    //用来获取订单总数的列表
    @Autowired
    private MarketOrderSumnumberMapper marketOrderSumnumberMapper;

    @Autowired
    private MarketSalesTableMapper marketSalesTableMapper;

    @Autowired
    private MarketCommercialVehicleTableMapper marketCommercialVehicleTableMapper;


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

        System.out.println("收集到的list"+cpd);

        //ToDo 按照地区和日期统计地区的订单总数。还需要添加车型区分CPD   day:10-12
        List<MarketOrderSumnumber> marketOrderSumnumbers = marketOrderSumnumberMapper.selectMarketOrderSumnumberList1();
//        System.out.println("测试查询"+marketOrderSumnumbers);


        Map<String, Map<String, Long>> result = marketOrderSumnumbers.stream()
                //获取车型不为空且为cpd的总数。
                .filter(a->a.getCreatPeople()!=null&&a.getCreatPeople().equals("CPD"))
                // 根据时间分组
                .collect(Collectors.groupingBy(
                        a -> a.getMarketTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        // 根据地区分组并求和
                        Collectors.groupingBy(
                                MarketOrderSumnumber::getMarketLedger,
                                Collectors.summingLong(MarketOrderSumnumber::getMarketOrderSumnumber)
                        )
                ));
        System.out.println("测试订单结果"+result);

        MarketIndexResult marketIndexResult = new MarketIndexResult();
//        marketIndexResult.setMapMap(cpd);
//
        Map<String, Map<String, Double>> stringMapMap = calculateOrderRatios(cpd, result);
//        System.out.println(stringMapMap);
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(stringMapMap);
        return voEntities;
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
        Map<String, Map<String, Double>> cpc = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter((MarketSalesTable a) ->
                {         LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    //判段是否超出长度
                    String model = a.getVehicleModel();
                    String branch = a.getBranch();
//            String category = vehicleCategoryMap.get(model);

                    return  model != null && model.length() >= 3 && model.substring(0, 3).equals("CPC") &&
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

        System.out.println("收集到的list"+cpc);

        //ToDo 按照地区和日期统计地区的订单总数。还需要添加车型区分CPD   day:10-12
        List<MarketOrderSumnumber> marketOrderSumnumbers = marketOrderSumnumberMapper.selectMarketOrderSumnumberList1();
//        System.out.println("测试查询"+marketOrderSumnumbers);


        Map<String, Map<String, Long>> result = marketOrderSumnumbers.stream()
                //获取车型不为空且为cpd的总数。
                .filter(a->a.getCreatPeople()!=null&&a.getCreatPeople().equals("CPC"))
                // 根据时间分组
                .collect(Collectors.groupingBy(
                        a -> a.getMarketTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        // 根据地区分组并求和
                        Collectors.groupingBy(
                                MarketOrderSumnumber::getMarketLedger,
                                Collectors.summingLong(MarketOrderSumnumber::getMarketOrderSumnumber)
                        )
                ));
        System.out.println("测试订单结果"+result);

        MarketIndexResult marketIndexResult = new MarketIndexResult();
//        marketIndexResult.setMapMap(cpd);
//
        Map<String, Map<String, Double>> stringMapMap = calculateOrderRatios(cpc, result);
//        System.out.println(stringMapMap);
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(stringMapMap);
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
        List<MarketMainType> newTypes = iMarketMainTypeService.selectMarketMainTypeList1(); // 假设有一个iNewTypeService来获取newtype表的数据

        // 创建一个Map来存储carmodel到type的映射
        Map<String, String> carModelToTypeMap = newTypes.stream()
                .collect(Collectors.toMap(MarketMainType::getCarType, MarketMainType::getMainCarType));

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
//                                a -> getVehicleModelPrefix(a.getVehicleModel()),
                                a -> carModelToTypeMap.getOrDefault(a.getVehicleModel(), "其他"), // 使用newtype中的type属性表示车型
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
        System.out.println("开始时间"+marketSalesTable.getStartTime());
         Date startTime1 = marketSalesTable.getStartTime();
         //前端传进来的日期
        LocalDate test = startTime1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


// 获取月份
        int newMonth = test.getMonthValue();

        System.out.println("月份是: " + newMonth);
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();


        // 获取newtype表中的数据
        List<MarketMainType> newTypes = iMarketMainTypeService.selectMarketMainTypeList1(); // 假设有一个iNewTypeService来获取newtype表的数据

        // 创建一个Map来存储carmodel到type的映射
        Map<String, String> carModelToTypeMap = newTypes.stream()
                .collect(Collectors.toMap(MarketMainType::getCarType, MarketMainType::getMainCarType));
        int currents = LocalDate.now().getYear();

        //取出今年，取出当年的，各个主要车型的各个月的总数
//        System.out.println(marketSalesTables);
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        List<MarketSalesTable> collect = marketSalesTables.stream().filter(a->{
                    LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     int year = acceptanceTime.getYear();
                    return year == currents;
                }

               ).collect(Collectors.toList());
        System.out.println("获取2024的数据"+collect);
        //规定年月日的格式s
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        int currentYear = Year.now().getValue();
        //这里只处理前端选择的月份
        int currentmonth =newMonth;
//        int currentmonth = LocalDate.now().getMonthValue();// 获取当前月份
        Map<String, Map<String, Long>> curMonth = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter(a -> {
                    LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int current = LocalDate.now().getYear();// 获取当前年份

                    //筛选2024年满足起止时间的数据，在选出年份为2024，且月份是开始那个月的数据

                    return  (acceptanceTime.getYear() == current)&& (acceptanceTime.getMonthValue()==currentmonth);//获取当前月份
//                             && (acceptanceTime.getYear() == current)&&(acceptanceTime.getMonthValue() ==startTime.getMonthValue());// 添加当前年份的过滤条件
                }) .peek(a -> System.out.println("符合当前月份的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .collect(Collectors.groupingBy(
//        m -> getVehicleModelPrefix(m.getVehicleModel()),
                        m -> carModelToTypeMap.getOrDefault(m.getVehicleModel(), "其他"), // 使用newtype中的type属性表示车型
                        Collectors.groupingBy(
//                                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                                result -> String.valueOf("当月度同比"),//因为只需要当前月份，所以这样改
                                Collectors.summingLong(MarketSalesTable::getNumber)
                        )
                ));

        System.out.println("取出当年的，各个车型的当前月分总数"+curMonth);
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
//                    System.out.println("去年的"+previousYear);
                    // 筛选出时间为前一年的数据，且月份与当前年份相同
                    return acceptanceTime.getYear() == previousYear&&(acceptanceTime.getMonthValue()==currentmonth);
                    //获取去年的，当前月份的数据
//                            && (acceptanceTime.getMonthValue() == startTime.getMonthValue() );

                }).peek(a -> System.out.println("符合的去年的当前月份的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .collect(Collectors.groupingBy(
//                        m -> getVehicleModelPrefix(m.getVehicleModel()),
                        m -> carModelToTypeMap.getOrDefault(m.getVehicleModel(), "其他"), // 使用newtype中的type属性表示车型
                        Collectors.groupingBy(
//                                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                                result -> String.valueOf("当月度同比"),//因为只需要当前月份，所以这样改
                                Collectors.summingLong(MarketSalesTable::getNumber)
                        )
                ));
        System.out.println("取出当前年份前一年对应月份的数据：" + before);

        Map<String, Map<String, Double>> stringMapMap = divideMaps(curMonth, before);
        System.out.println("比值"+stringMapMap);
        // 过滤掉 "Unknown" 的数据
//        stringMapMap.entrySet().removeIf(entry -> "Unknown".equals(entry.getKey()));
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(stringMapMap);

        return voEntities;

    }
    public static Map<String, Map<String, Double>> divideMaps(
            Map<String, Map<String, Long>> curMonth,
            Map<String, Map<String, Long>> before) {

        Map<String, Map<String, Double>> result = new HashMap<>();

        for (Map.Entry<String, Map<String, Long>> curMonthEntry : curMonth.entrySet()) {
            String category = curMonthEntry.getKey();
            Map<String, Long> curMonthValues = curMonthEntry.getValue();

            // 初始化结果映射中的子映射
            Map<String, Double> resultValues = new HashMap<>();

            // 如果before映射中存在当前类别
            if (before.containsKey(category)) {
                Map<String, Long> beforeValues = before.get(category);

                for (Map.Entry<String, Long> curMonthValueEntry : curMonthValues.entrySet()) {
                    String key = curMonthValueEntry.getKey();
                    Long curMonthValue = curMonthValueEntry.getValue();
                    Long beforeValue = beforeValues.get(key);

                    // 如果before映射中存在相同的键
                    if (beforeValue != null && beforeValue != 0) {
                        double divisionResult = (curMonthValue - beforeValue) / (double) beforeValue;
                        resultValues.put(key, divisionResult);
                    } else {
                        // 如果before中没有这个键或值为0，可以返回NaN或0
                        resultValues.put(key, Double.NaN); // 或者0.0
                    }
                }
            }

            // 将计算结果放入结果映射
            result.put(category, resultValues);
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

        int currentMonth  =1;
        System.out.println("获取到的开始时间"+marketSalesTable.getStartTime());
        //把开始时间做为查询条件
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 获取newtype表中的数据
        List<MarketMainType> newTypes = iMarketMainTypeService.selectMarketMainTypeList1(); // 假设有一个iNewTypeService来获取newtype表的数据

        // 创建一个Map来存储carmodel到type的映射
        Map<String, String> carModelToTypeMap = newTypes.stream()
                .collect(Collectors.toMap(MarketMainType::getCarType, MarketMainType::getMainCarType));


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
//                                a -> getVehicleModelPrefix(a.getVehicleModel()), // 按照车型前缀分组
//                                a -> carModelToTypeMap.getOrDefault(a.getVehicleModel(), "其他"), // 使用newtype中的type属性表示车型
                                //啥都不返回，对面提出的需求
                                a-> " ",
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
//    @PostMapping("/OrderWaitSend")
//    public  List<VoEntity> OrderWaitSend(@RequestBody MarketSalesTable marketSalesTable){
//
//        System.out.println("获取到的实体类"+marketSalesTable);
//        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
//        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
//        // 获取当前时间
//        LocalDate currentDate = LocalDate.now();
//        //规定年月日的格式
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        //获取到全部的数据
//        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
//                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
//                 .filter(a -> a.getActualDepartureDate() == null)// 过滤掉 已经发车的 元素
//                 .filter(a -> {
//                     System.out.println(a);
//            LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
//            return !acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
//        }).collect(Collectors.groupingBy(
//                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
//                , Collectors.groupingBy(
//                        MarketSalesTable::getBranch
//                        , Collectors.summingLong(MarketSalesTable::getNumber))
//        ));
//
//        // 对结果进行排序
//        Map<String, Map<String, Long>> sortedCollect = collect.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey())
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//
////        marketSalesTables.stream().filter((MarketSalesTable a) ->
////        {
////            return a.getActualDepartureDate() == null &&
////                    marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
////                    && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth();
////
////        })
//
//        System.out.println("选出实际发车日期为空的,再按照地区和月份统计为发车的数量"+collect);
//        System.out.println("选出实际发车日期为空的,再按照地区和月份统计为发车的数量"+sortedCollect);
//
//
//        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(sortedCollect);
//        return voEntities;
//    }

    @PostMapping("/OrderWaitSend")
    public List<VoEntity> OrderWaitSend(@RequestBody MarketSalesTable marketSalesTable) {

        System.out.println("获取到的实体类" + marketSalesTable);
        System.out.println("获取订单总台数" + marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间" + marketSalesTable.getStartTime() + marketSalesTable.getEndTime());

        // 获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 只过滤实际发车时间为空的记录，并按网点统计
        Map<String, List<MarketSalesTable>> branchOrderDetails = marketSalesTables.stream()
                .filter(a -> a.getSystemDeliveryTime() == null) // 只保留实际发车时间为空的记录
                .collect(Collectors.groupingBy(MarketSalesTable::getBranch));

        // 打印网点为“河北”的记录
        if (branchOrderDetails.containsKey("北京")) {
            System.out.println("网点为“北京”的未发车订单详情: ");
            int index = 1;
            for (MarketSalesTable table : branchOrderDetails.get("北京")) {
                System.out.println("订单序号: " + index);
                System.out.println("订单详情: " + table);
                System.out.println("订单数量: " + table.getNumber());
                System.out.println("----------------------------------------"); // 分割线
                index++;
            }
        } else {
            System.out.println("网点为“北京”的未发车订单数量: 0");
        }

        // 统计数量
        Map<String, Long> branchOrderCounts = branchOrderDetails.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToLong(MarketSalesTable::getNumber).sum()
                ));

        System.out.println("按照网点统计实际发车时间为空的车型数量" + branchOrderCounts);

        // 将结果转换为 VoEntity 列表
        Map<String, Map<String, Long>> adjustedBranchOrderCounts = branchOrderCounts.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            Map<String, Long> innerMap = new HashMap<>();
                            innerMap.put("", entry.getValue());  // 或者用任何合适的键替代
                            return innerMap;
                        }
                ));

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(adjustedBranchOrderCounts);
        return voEntities;
    }




    /*
     * 指标17各网点未排产订单台数
     * */
//    @PostMapping("/BranchWaitProduction")
//    public  List<VoEntity> BranchWaitProduction(@RequestBody MarketSalesTable marketSalesTable){
//
//        System.out.println("获取到的实体类"+marketSalesTable);
//        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
//        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
//        //规定年月日的格式
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        //获取到全部的数据
//        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//
//         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
//                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
//                 .filter((MarketSalesTable a) ->
//        {          LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况,并且满足车型为空或者车型中不包含中文的数据
//            return
//                    (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
//                    && (a.getCarNumber() == null || containsChinese(a.getCarNumber()));
////                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());
////                   LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
////
////            // 检查日期是否在起止时间范围内，包括等于起止时间的情况
////            return (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
////                    || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime);
////                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth());
//
//        })
//                .collect(Collectors.groupingBy(
//                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
//                        , Collectors.groupingBy(MarketSalesTable::getBranch
//                                , Collectors.summingLong(MarketSalesTable::getNumber))
//                ));
//
//        System.out.println("选出车号为空或者为汉字的,再按照地区和月份统计数量"+collect);
//
//
//         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);
//        return voEntities;
//    }
//    //处理包含中文的车型
//    private static boolean containsChinese(String str) {
//        for (char c : str.toCharArray()) {
//            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
//                return true;
//            }
//        }
//        return false;
//    }


    @PostMapping("/BranchWaitProduction")
    public List<VoEntity> BranchWaitProduction(@RequestBody MarketSalesTable marketSalesTable) {

        System.out.println("获取到的实体类" + marketSalesTable);
        System.out.println("获取订单总台数" + marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间" + marketSalesTable.getStartTime() + marketSalesTable.getEndTime());

        // 获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 按网点分类，统计车号为空或者为汉字的数量，并保存符合条件的记录
        Map<String, Long> branchCounts = new HashMap<>();
        Map<String, List<MarketSalesTable>> branchDetails = new HashMap<>();

        for (MarketSalesTable table : marketSalesTables) {
            // 确保首先检查有接单时间且车号为空、为空字符串或为汉字
            if (table.getOrderAcceptanceTime() != null &&
                    (table.getCarNumber() == null ||
                            table.getCarNumber().isEmpty() || // 添加空字符串的检查
                            containsChinese(table.getCarNumber()))) {

                String branch = table.getBranch();
                // 统计数量
                branchCounts.put(branch, branchCounts.getOrDefault(branch, 0L) + table.getNumber());

                // 保存符合条件的记录
                branchDetails.computeIfAbsent(branch, k -> new ArrayList<>()).add(table);
            }
        }

        // 输出河北网点的车号为空或为汉字的订单详情
        if (branchDetails.containsKey("北京")) {
            System.out.println("网点为“北京”的车号为空或为汉字的订单详情: ");
            int index = 1;
            for (MarketSalesTable table : branchDetails.get("北京")) {
                System.out.println("订单序号: " + index);
                System.out.println("订单详情: " + table);
                System.out.println("订单数量: " + table.getNumber());
                System.out.println("----------------------------------------"); // 分割线
                index++;
            }
        } else {
            System.out.println("网点为“河北”的未发车订单数量: 0");
        }

        System.out.println("按地区统计车号为空或为汉字的数量: " + branchCounts);

        // 将 branchCounts 转换为 Map<String, Map<String, Long>> 类型
        Map<String, Map<String, Long>> adjustedBranchCounts = new HashMap<>();
        for (Map.Entry<String, Long> entry : branchCounts.entrySet()) {
            String branch = entry.getKey();
            Long count = entry.getValue();

            Map<String, Long> detailMap = new HashMap<>();
            detailMap.put("", count);

            adjustedBranchCounts.put(branch, detailMap);
        }

        // 将结果转换为 VoEntity 列表
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(adjustedBranchCounts);
        return voEntities;
    }

    // 处理包含中文的车型
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
//    @PostMapping("/OverWorkWaitDelivery")
//    public  List<VoEntity> OverWorkWaitDelivery(@RequestBody MarketSalesTable marketSalesTable){
//
//        System.out.println("获取到的实体类"+marketSalesTable);
//        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
//        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
//
//        //获取到全部的数据
//        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//        //规定年月日的格式
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//
//         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
//                 .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
//                 .filter((MarketSalesTable a) ->
//        {
//            LocalDate acceptanceTime = a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            // 检查日期是否在起止时间范围内，包括等于起止时间的情况,系统交货期有日期且实际发车日期为空
//            return
//                    (!acceptanceTime.isBefore(startTime) && !acceptanceTime.isAfter(endTime)
//                            || acceptanceTime.isEqual(startTime) || acceptanceTime.isEqual(endTime))
//                            && a.getActualDepartureDate() == null && a.getSystemDeliveryTime() != null;
//
//
//        })
//                .collect(Collectors.groupingBy(
//                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
//
//
//                        , Collectors.groupingBy(
//                                MarketSalesTable::getBranch
//                                , Collectors.summingLong(MarketSalesTable::getNumber))
//                ));
//        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照月份和地区统计数量"+collect);
//
//         List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(collect);
//        return voEntities;
//    }



    @PostMapping("/OverWorkWaitDelivery")
    public List<VoEntity> OverWorkWaitDelivery(@RequestBody MarketSalesTable marketSalesTable) {

        System.out.println("获取到的实体类" + marketSalesTable);
        System.out.println("获取订单总台数" + marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间" + marketSalesTable.getStartTime() + marketSalesTable.getEndTime());

        // 获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 按网点分类，筛选出系统交货期有日期且实际发车日期为空的台数
        Map<String, Long> collect = marketSalesTables.stream()
                .filter(a -> a.getOrderAcceptanceTime() != null) // 过滤掉 getOrderAcceptanceTime 为空的元素
                .filter(a -> a.getActualDepartureDate() == null && a.getSystemDeliveryTime() != null) // 筛选符合条件的记录
                .collect(Collectors.groupingBy(
                        MarketSalesTable::getBranch, // 按网点分组
                        Collectors.summingLong(MarketSalesTable::getNumber) // 统计数量
                ));

        System.out.println("筛选出系统交货期有日期且实际发车日期为空，再按照网点统计数量: " + collect);

        Map<String, Map<String, Long>> adjustedBranchCounts = new HashMap<>();
        for (Map.Entry<String, Long> entry : collect.entrySet()) {
            String branch = entry.getKey();
            Long count = entry.getValue();

            Map<String, Long> detailMap = new HashMap<>();
            detailMap.put("", count);

            adjustedBranchCounts.put(branch, detailMap);
        }

        // 将结果转换为 VoEntity 列表
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(adjustedBranchCounts);
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
//    @PostMapping("/OvertimedOrderNumber")
//    public   List<VoEntity> OvertimedOrderNumber(@RequestBody MarketSalesTable marketSalesTable){

    @PostMapping("/OvertimedOrderNumber")
    public List<VoEntity> OvertimedOrderNumber(@RequestBody MarketSalesTable marketSalesTable) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());


        // 定义指定的网点列表
        List<String> specifiedBranches = Arrays.asList("哈尔滨", "辽宁", "天津", "长春", "内蒙古",
                "上海", "山东", "进出口公司","福建","北京","山西","山东齐鲁","河北","永恒力");

        // 获取销售台账全部数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();


        DateTimeFormatter Yearmonth = DateTimeFormatter.ofPattern("yyyy-MM");
        // 获取符合条件的销售台账并按日期和网点分类：今天 > 订单系统交货期 且 车号为空或包含中文
        Map<String, Map<String, Long>> groupedData = marketSalesTables.stream()
                .filter(a -> a.getCarNumber() == null || containsChinese(a.getCarNumber())||a.getCarNumber().isEmpty()) // 车号为空或包含中文
                .peek(a -> System.out.println("符合车号为空或者汉字条件的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .filter(a -> {
                    LocalDate systemDeliveryTime = a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return currentDate.isAfter(systemDeliveryTime); // 今天 > 订单系统交货期
                })
                .peek(a -> System.out.println("符合今天 > 订单系统交货期的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .filter(a -> a.getCarNumber() == null || containsChinese(a.getCarNumber())||a.getCarNumber().isEmpty()) // 车号为空或包含中文
                .peek(a -> System.out.println("符合车号为空或者汉字条件的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .filter(a -> specifiedBranches.contains(a.getBranch())) // 只保留指定的网点
                .peek(a -> System.out.println("选完网点的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .filter(a ->
        {

            LocalDate systemDeliveryTime = a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
            //并且精准完工期要早于计划完工期
            return
                    ((systemDeliveryTime.getMonthValue() >= startTime.getMonthValue() && systemDeliveryTime.getMonthValue() <= endTime.getMonthValue())
                            || systemDeliveryTime.isEqual(startTime) || systemDeliveryTime.isEqual(endTime));
        }) //过滤出时间在时间选择器区间内的数据
                .peek(a -> System.out.println("符合时间条件的销售台账数据: " + a)) // 打印每条符合条件的销售台账
                .collect(Collectors.groupingBy(
                        a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Yearmonth), // 按订单系统交货期分组
                        Collectors.groupingBy(
                                MarketSalesTable::getBranch, // 按网点分组
                                Collectors.summingLong(MarketSalesTable::getNumber) // 统计每个网点符合条件的车辆台数
                        )
                ));

        // 打印销售台账中每个网点的数量，便于调试
        System.out.println("测试销售台账数据，按照月份和网点分类，统计台数"+groupedData);

        // 获取商品车台账全部数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        // 获取符合条件的商品车台账并按网点分类：精整日期为空 且 今天 > 计划完工期，且客户符合指定网点
        Map<String, Map<String, Long>> groupedData2 = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() == null||a.getPrecisionCompletionPeriod().toString().isEmpty()) // 精整日期为空
                .filter(a -> {
                    LocalDate systemDeliveryTime = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return currentDate.isAfter(systemDeliveryTime); // 今天 > 计划完工期
                })
                .peek(a -> System.out.println("符合条件的商品车台账数据: " + a)) // 打印每条符合条件的商品车台账数据
                .filter(a -> specifiedBranches.stream().anyMatch(branch -> a.getCustomer().contains(branch))) //过滤网点。
                .peek(a -> System.out.println("过滤网点后符合条件的商品车台账数据: " + a)) // 过滤网点后符合条件的商品车台账数据
                .filter(a ->
                {

                    LocalDate PlannedCompletionPeriod = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    return
                            ((PlannedCompletionPeriod.getMonthValue() >= startTime.getMonthValue() && PlannedCompletionPeriod.getMonthValue() <= endTime.getMonthValue())
                                    || PlannedCompletionPeriod.isEqual(startTime) || PlannedCompletionPeriod.isEqual(endTime));
                }) //过滤出时间在时间选择器区间内的数据
                .peek(a -> System.out.println("符合时间条件的商品车台账数据: " + a)) //过滤出时间在时间选择器区间内的数据
                .collect(Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Yearmonth),
                        Collectors.groupingBy(
                                a -> {
                                    String customer = a.getCustomer();
                                    return specifiedBranches.stream()
                                            .filter(branch -> customer.contains(branch))
                                            .findFirst()
                                            .orElse(customer); // 如果没有匹配的分支，则使用原始 customer 名称
                                },
                                Collectors.counting()
                        )
                ));
//                .collect(Collectors.groupingBy(
//                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(Yearmonth), // 计划完工期分组
//                        Collectors.groupingBy(
//                                MarketCommercialVehicleTable::getCustomer, // 按网点分组
//                                Collectors.counting() // 统计每个网点符合条件的行数
//                        )
//                ));
        System.out.println("商品车的指标19"+groupedData2);
        //
         Map<String, Map<String, Long>> stringMapMap = mergeMaps(groupedData, groupedData2);
        System.out.println("得到的最新合并后的指标19"+stringMapMap);
        // 将结果转换为 VoEntity 列表
        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntities(stringMapMap);

        // 返回 VoEntity 列表
        return voEntities;
    }

    private static Map<String, Map<String, Long>> mergeMaps(
            Map<String, Map<String, Long>> groupedData,
            Map<String, Map<String, Long>> groupedData2) {

        // 创建一个新的Map来存储合并后的结果
        Map<String, Map<String, Long>> newGroupedData = new HashMap<>();

        // 遍历第一个Map的所有键
        for (Map.Entry<String, Map<String, Long>> entry : groupedData.entrySet()) {
            String key = entry.getKey();
            Map<String, Long> subMap = entry.getValue();

            // 如果新Map中还没有这个键，则创建一个新的子Map
            if (!newGroupedData.containsKey(key)) {
                newGroupedData.put(key, new HashMap<>());
            }

            // 将第一个Map中的子Map的值添加到新Map中
            for (Map.Entry<String, Long> subEntry : subMap.entrySet()) {
                String subKey = subEntry.getKey();
                Long value = subEntry.getValue();
                newGroupedData.get(key).merge(subKey, value, Long::sum);
            }
        }

        // 遍历第二个Map的所有键
        for (Map.Entry<String, Map<String, Long>> entry : groupedData2.entrySet()) {
            String key = entry.getKey();
            Map<String, Long> subMap = entry.getValue();

            // 如果新Map中还没有这个键，则创建一个新的子Map
            if (!newGroupedData.containsKey(key)) {
                newGroupedData.put(key, new HashMap<>());
            }

            // 将第二个Map中的子Map的值添加到新Map中
            for (Map.Entry<String, Long> subEntry : subMap.entrySet()) {
                String subKey = subEntry.getKey();
                Long value = subEntry.getValue();
                newGroupedData.get(key).merge(subKey, value, Long::sum);
            }
        }

        return newGroupedData;
    }

    /*
     * 指标39 商品车计划兑现率
     * */
    /**
     * @description: 更新，将此处指标select数据从市场科商品车台账 -> 生产科商品车台账，避免生产科数据不全问题。
     * 此处有不规范操作，将直接使用市场科商品车台账mapper 直接查询生产科商品车台账表
     * @author: hong
     * @date: 2025/5/8 12:17
     * @version: 1.0
     */

    @PostMapping("/CommercialVehicleRate")
    public  List<VoEntity> CommercialVehicleRate(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
//        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        // 将原本从市场科的商品车台账，修改为生产科商品车台账。
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectListByProductionDept();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
        Map<String, Map<String, Long>> collect = marketCommercialVehicleTables.stream()
                .filter(a -> a.getPrecisionCompletionPeriod() != null) // 过滤掉 getPrecisionCompletionPeriod 为空的元素
                .filter(a -> a.getPlannedCompletionPeriod() != null) // 过滤掉 getPlannedCompletionPeriod 为空的元素

                .filter(a ->
                {       //R
                    LocalDate precisioncompletion = Optional.ofNullable(a.getPrecisionCompletionPeriod())
                            .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .orElse(null);
                    //L
                    LocalDate plancompletion = a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate startTime = marketCommercialVehicleTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endTime = marketCommercialVehicleTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
                    //并且精准完工期要早于计划完工期
                    return
                            (!plancompletion.isBefore(startTime) && !plancompletion.isAfter(endTime)
                                    || plancompletion.isEqual(startTime) || plancompletion.isEqual(endTime))
                                    &&
                                    precisioncompletion.isBefore(plancompletion)||precisioncompletion.equals(plancompletion);
//            precisioncompletion.isBefore(plancompletion);

                }).collect(Collectors.groupingBy(
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.groupingBy(
                                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(month),
                                Collectors.counting()
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
                        a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.groupingBy(
                                a -> a.getPlannedCompletionPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(month),
                                Collectors.counting()
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
                String months = entry.getKey();
                Long collectCount = entry.getValue();
                Long totalCount = monthlyTotal.getOrDefault(months, 0L);

                // 计算比例
                double ratio = totalCount != 0 ? (double) collectCount / totalCount : 0.0;
                ratioYearMap.put(months, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }

        System.out.println("按照计划完工期分月，当月R-L（≤0）的数量"+collect);
        //按照月份排序
        TreeMap<String, Map<String, Double>> sortedRatioMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); // 默认字符串比较，将按照字典顺序排序
            }
        });

        // 将所有元素从HashMap转移到TreeMap
        sortedRatioMap.putAll(ratioMap);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(sortedRatioMap);
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
//        marketSalesTableMapper.selectOrderAchieveRate();
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");
        //筛选出计划完工日期在起止时间内的数据，并且精准完工期要早于计划完工期
        //然后按照年，分组，再按照月分组，再统计每年的每月的数量
//        Map<String, Map<String, Long>> collect =
//        marketSalesTableMapper.orderAchieveRate()
        LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                .filter(a -> {
                    LocalDate wColumn = Optional.ofNullable(a.getActualDepartureDate())
                            .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .orElse(null);
                    LocalDate xColumn = Optional.ofNullable(a.getSystemDeliveryTime())
                            .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .orElse(null);
                    LocalDate tColumn = Optional.ofNullable(a.getOrderSystemDeliveryTime())
                            .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                            .orElse(null);

                    // 确保T列在起止时间范围内，并且W或X列有日期且小于等于T列
                    return tColumn != null && !tColumn.isBefore(startTime) && !tColumn.isAfter(endTime) &&
                            ((wColumn != null && (wColumn.isEqual(tColumn) || wColumn.isBefore(tColumn))) ||
                                    (xColumn != null && (xColumn.isEqual(tColumn) || xColumn.isBefore(tColumn))));
                })
                .collect(Collectors.groupingBy(
                        a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),

//                        a -> Optional.ofNullable(a.getOrderSystemDeliveryTime())
//                                .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
//                                .filter(date -> !date.isBefore(startTime) && !date.isAfter(endTime)) // 确保T列在起止时间范围内
//                                .map(LocalDate::getYear)
//                                .map(String::valueOf)
//                                .orElse(null), // 年份作为外层Map的Key
                        Collectors.groupingBy(
                                a -> Optional.ofNullable(a.getOrderSystemDeliveryTime())
                                        .map(date -> date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                        .filter(date -> !date.isBefore(startTime) && !date.isAfter(endTime)) // 确保T列在起止时间范围内
                                        .map(LocalDate::getMonthValue)
                                        .map(value -> String.format("%02d", value))
                                        .orElse(null), // 月份作为内层Map的Key，格式化为两位数字
                                Collectors.summingLong(MarketSalesTable::getNumber) // 按照年月分组后的总数作为Value
                        )
                ));

        System.out.println("实际发车日期W，系统交货日期X任一列有日期且小于订单系统交货期T的数量"+collect);

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
//            LocalDate startTime = marketSalesTable.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endTime = marketSalesTable.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            // 检查计划完工日期是否在起止时间范围内，包括等于起止时间的情况,
                    return
                            (!OrderSystemDeliveryTime.isBefore(startTime) && !OrderSystemDeliveryTime.isAfter(endTime)
                                    || OrderSystemDeliveryTime.isEqual(startTime) || OrderSystemDeliveryTime.isEqual(endTime))
                                    && OrderSystemDeliveryTime != null;


                }).collect(Collectors.groupingBy(
                        a -> a.getOrderSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
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

                // 使用BigDecimal来计算比例，并保留两位小数
                double ratio = totalCount != 0 ? BigDecimal.valueOf(collectCount)
                        .divide(BigDecimal.valueOf(totalCount), 6, RoundingMode.HALF_UP)
                        .doubleValue() : 0.0;
                ratioYearMap.put(month, ratio);
            }
            ratioMap.put(year, ratioYearMap);
        }
        //按照月份排序
        TreeMap<String, Map<String, Double>> sortedRatioMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); // 默认字符串比较，将按照字典顺序排序
            }
        });

        // 将所有元素从HashMap转移到TreeMap
        sortedRatioMap.putAll(ratioMap);

        System.out.println("实际发车日期W，系统交货日期X任一列有日期且小于等于订单系统交货期T的数量"+collect1+ratioMap);

        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(sortedRatioMap);
        return voEntities;
    }

    /*
     * 指标42 大吨位叉车整机交货天数平均数
     * TODO 筛选出平均值>20%的数据，过滤对应的车型
     * */
    @PostMapping("/AvergaeDay")
    public  List<VoEntity> AvergaeDay(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();

        List<AverageResult> averageResults = iMarketCommercialVehicleTableService.selectBigWeight();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;
        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        AverageResult::getMonth,
                        Collectors.groupingBy(
                                result -> String.valueOf(result.getOver_20_Percent_Count()), // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
//                                        e -> "超过平均数20% " + e.getKey(), // 添加 "平均天数 " 前缀
                                        e -> "" ,
                                        Map.Entry::getValue
                                ))
                ));

        System.out.println("测试分组"+groupedResults);




        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
//        List<VoEntity> voEntities1 = VoEntity.convertCpdToVoEntitiesDouble(groupedResults);
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
        List<AverageResult> averageResults = iMarketCommercialVehicleTableService.selectcountAverage();
        System.out.println("数据库测试2"+averageResults+"测试完成");

        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;

        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        AverageResult::getMonth,
                        Collectors.groupingBy(
                                result -> String.valueOf(result.getOver_20_Percent_Count()), // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
//                                        e -> "超过平均数20% " + e.getKey(), // 添加 "平均天数 " 前缀
                                        e -> "" ,
                                        Map.Entry::getValue

                                ))
                ));

//        System.out.println("测试分组"+groupedResults);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
        return voEntities;
    }


    /*
    * 指标45 电动车整机制造天数
平均数
    * */
    @PostMapping("/ElCarAvergaeDay45")
    public  List<VoEntity> ElCarAvergaeDay45(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        List<AverageResult> averageResults = marketCommercialVehicleTableMapper.selectcountAverage45();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;


// TODO改sql语句 获取当前年份
        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        result -> String.valueOf("2024年"),
                        Collectors.groupingBy(
                                AverageResult::getMonth,  // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .sorted(Map.Entry.comparingByKey()) // 按照key排序
                                .collect(Collectors.toMap(
                                        e -> e.getKey() + "月份", // 添加 "月份" 后缀
                                        Map.Entry::getValue,
                                        (oldValue, newValue) -> oldValue, // 如果有相同的key，保留旧的值
                                        LinkedHashMap::new // 使用LinkedHashMap保持插入顺序
                                ))
                ));

//        System.out.println("测试分组"+groupedResults);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
        return voEntities;
    }


    /*
    * 指标46 小吨位整机制造天数
平均数
    * */
    @PostMapping("/ElCarAvergaeDay46")
    public  List<VoEntity> ElCarAvergaeDay46(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        List<AverageResult> averageResults = marketCommercialVehicleTableMapper.selectcountAverage46();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth();
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth();


// TODO改sql语句 获取当前年份
        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        result -> String.valueOf("2024年"),
                        Collectors.groupingBy(
                                AverageResult::getMonth,  // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .sorted(Map.Entry.comparingByKey()) // 按照key排序
                                .collect(Collectors.toMap(
                                        e -> e.getKey() + "月份", // 添加 "月份" 后缀
                                        Map.Entry::getValue,
                                        (oldValue, newValue) -> oldValue, // 如果有相同的key，保留旧的值
                                        LinkedHashMap::new // 使用LinkedHashMap保持插入顺序
                                ))
                ));

//        System.out.println("测试分组"+groupedResults);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
        return voEntities;
    }

    /*
* 指标47 大吨位叉车整机制造天数
平均数
* */
    @PostMapping("/ElCarAvergaeDay47")
    public  List<VoEntity> ElCarAvergaeDay47(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        List<AverageResult> averageResults = marketCommercialVehicleTableMapper.selectcountAverage47();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;


// TODO改sql语句 获取当前年份
        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        result -> String.valueOf("2024年"),
                        Collectors.groupingBy(
                                AverageResult::getMonth,  // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .sorted(Map.Entry.comparingByKey()) // 按照key排序
                                .collect(Collectors.toMap(
                                        e -> e.getKey() + "月份", // 添加 "月份" 后缀
                                        Map.Entry::getValue,
                                        (oldValue, newValue) -> oldValue, // 如果有相同的key，保留旧的值
                                        LinkedHashMap::new // 使用LinkedHashMap保持插入顺序
                                ))
                ));


//        System.out.println("测试分组"+groupedResults);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
        return voEntities;
    }


    /*
* 指标48 越野车整机制造天数
平均数
* */
    @PostMapping("/ElCarAvergaeDay48")
    public  List<VoEntity> ElCarAvergaeDay48(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable){

        System.out.println("获取到的实体类"+marketCommercialVehicleTable);
        System.out.println("获取订单总台数"+marketCommercialVehicleTable.getNumberInput());
        System.out.println("获取到起止时间"+marketCommercialVehicleTable.getStartTime()+marketCommercialVehicleTable.getEndTime());

        //获取到全部的数据
        List<MarketCommercialVehicleTable> marketCommercialVehicleTables = iMarketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        List<AverageResult> averageResults = marketCommercialVehicleTableMapper.selectcountAverage48();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;


// TODO改sql语句 获取当前年份
        Map<String, Map<String, Double>> groupedResults = Optional.ofNullable(averageResults)
                .orElseGet(Collections::emptyList) // 如果averageResults为null，则使用空列表
                .stream()
                .filter(result -> result != null) // 过滤掉流中的null元素
                .filter(result -> {
                    String monthStr = result.getMonth();
                    if (monthStr == null) return false; // 如果月份为null，则跳过该结果
                    int resultMonth = Integer.parseInt(monthStr);
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        AverageResult::getCompletionYear,
                        Collectors.groupingBy(
                                AverageResult::getMonth,
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));


        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .sorted(Map.Entry.comparingByKey()) // 按照key排序
                                .collect(Collectors.toMap(
                                        e -> e.getKey() + "月份", // 添加 "月份" 后缀
                                        Map.Entry::getValue,
                                        (oldValue, newValue) -> oldValue, // 如果有相同的key，保留旧的值
                                        LinkedHashMap::new // 使用LinkedHashMap保持插入顺序
                                ))
                ));

//        System.out.println("测试分组"+groupedResults);


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
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

        List<AverageResult> averageResults = iMarketCommercialVehicleTableService.selectLightWeight();
        System.out.println("数据库测试2"+averageResults+"测试完成");
        int startMonth1 = marketCommercialVehicleTable.getStartTime().getMonth()+1;
        int endMonth = marketCommercialVehicleTable.getEndTime().getMonth()+1;
        Map<String, Map<String, Double>> groupedResults = averageResults.stream()
                .filter(result -> {
                    int resultMonth = Integer.parseInt(result.getMonth());
                    return resultMonth >= startMonth1 && resultMonth <= endMonth;
                })
                .collect(Collectors.groupingBy(
                        AverageResult::getMonth,
                        Collectors.groupingBy(
                                result -> String.valueOf(result.getOver_20_Percent_Count()), // 将Over_20_Percent_Count转换为字符串
                                Collectors.averagingDouble(AverageResult::getAverage_Days_Difference)
                        )
                ));

        System.out.println("测试分组"+groupedResults);

        Map<String, Map<String, Double>> modifiedResults = groupedResults.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
//                                        e -> "超过平均数20% " + e.getKey(), // 添加 "平均天数 " 前缀
                                        e -> "" ,
                                        Map.Entry::getValue
                                ))
                ));


        List<VoEntity> voEntities = VoEntity.convertCpdToVoEntitiesDouble(modifiedResults);
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
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");


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
//                                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(monthFormatter),
                                        result -> String.valueOf("年接单数"),
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
     * 指标72  日/月/年接单数
     * */
    @PostMapping("/OrderReceiveNumberB")
    public   List<VoEntity>  OrderReceiveNumberB(@RequestBody MarketSalesTable marketSalesTable){

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
     * 指标72  日/月/年接单数
     * */
    @PostMapping("/OrderReceiveNumberC")
    public   List<VoEntity>  OrderReceiveNumberC(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM-dd");
        //TODO 修改了时间格式，后续可能要改
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("YYYY");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");


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
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dayFormatter),
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(
                                        result -> String.valueOf("日接单"),
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
        System.out.println("返回给前端的vo对象"+voEntities);
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
                                result -> String.valueOf("年交货"),
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


    /*
     * 指标73  日/月/年交货数
     * 可能需要修改 传出参数；YY-MM-DD
     * */
    @PostMapping("/OrderDeliveryNumberB")
    public   List<VoEntity> OrderDeliveryNumberB(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        DateTimeFormatter Yearmonth = DateTimeFormatter.ofPattern("YYYY-MM");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY");


        Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                //过滤掉为空的日期
                .filter(a -> a.getSystemDeliveryTime() != null)
                .filter(a -> {
                    //TODO acceptanceTime需要改为 getSystemDeliveryTime；命名问题
                    LocalDate acceptanceTime = a.getSystemDeliveryTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

    /*
     * 指标73  日/月/年交货数
     * 可能需要修改 传出参数；YY-MM-DD
     * */
    @PostMapping("/OrderDeliveryNumberC")
    public   List<VoEntity> OrderDeliveryNumberC(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter Year = DateTimeFormatter.ofPattern("YYYY-MM-dd");


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
                                result -> String.valueOf("日交货"),
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
