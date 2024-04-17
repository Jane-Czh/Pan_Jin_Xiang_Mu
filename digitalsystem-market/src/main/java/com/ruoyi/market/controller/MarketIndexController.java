package com.ruoyi.market.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.domain.MarketIndexResult;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.service.IMarketSalesTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/market/Index")
public class MarketIndexController extends BaseController {


    /*
    指标11
    */
    @Autowired
     private IMarketSalesTableService iMarketSalesTableService;
    @PostMapping("/IClassProportion")
    public MarketIndexResult ProportionOfClassITrams(@RequestBody MarketSalesTable marketSalesTable){

         Date orderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime();
         String vehicleModel = marketSalesTable.getVehicleModel();
         String substring = vehicleModel.substring(0, 3);

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        //获取到全部的数据的number  做为分母
         long allSum = marketSalesTables.stream().mapToLong(MarketSalesTable::getNumber).sum();

        //获取到的符合条件的销售台账实体
         List<MarketSalesTable> collect = marketSalesTables.stream().filter(mt ->

                  mt.getOrderAcceptanceTime().equals(orderAcceptanceTime)&&mt.getVehicleModel().startsWith(substring)

                  ).collect(Collectors.toList());
         Map<String, List<MarketSalesTable>> collect1 = collect.stream().collect(Collectors.groupingBy(MarketSalesTable::getBranch));

        //获取销售台账实体的Number并统计和做为分子
        //  MarketSalesTable::getNumber()  不加括号是因为只是引用getnumber方法，而不是需要返回结果
//         long sum = collect.stream().mapToLong(MarketSalesTable::getNumber).sum();



         //取出cpd开头的型号和对应日期的车辆，并且按照网点分组；
//         Map<String, List<MarketSalesTable>> collect1 = marketSalesTables.stream().collect(Collectors.groupingBy(MarketSalesTable::getBranch));
//         Map<String, List<MarketSalesTable>> cpd = marketSalesTables.stream()
//                .filter(a -> a.getVehicleModel().startsWith("CPD") && a.getOrderAcceptanceTime().equals(orderAcceptanceTime))
//                .collect(Collectors.groupingBy((MarketSalesTable::getBranch),
//                        Collectors.summingInt(MarketSalesTable::getNumber)));
        Map<String, Long> sumByBranch = marketSalesTables.stream()
                .filter(a -> a.getVehicleModel().startsWith(substring) && a.getOrderAcceptanceTime().equals(orderAcceptanceTime))
                .collect(Collectors.groupingBy(MarketSalesTable::getBranch, Collectors.summingLong(MarketSalesTable::getNumber)));
        System.out.println("按照地区分类，选出车型为cpd和日期符合的数据"+sumByBranch);



        System.out.println("收集到的list"+collect);

        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMap(sumByBranch);
//         Set<String> keys = sumByBranch.keySet();
//        for (String key : keys) {
//             Long aLong = sumByBranch.get(key);
//             marketIndexResult.setNumber(aLong);
//             marketIndexResult.setBranch(key);
//        }

        return marketIndexResult;
//        return getDataTable(collect);
    }
    /*
    *指标12
    **/
    @PostMapping("/IVVClassProportion")
    public  MarketIndexResult ProportionOfClassIVVTrams(@RequestBody MarketSalesTable marketSalesTable){

        Date orderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime();
        String vehicleModel = marketSalesTable.getVehicleModel();
        String substring = vehicleModel.substring(0, 3);

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        Map<String, Long> sumByBranch = marketSalesTables.stream()
                .filter(a -> a.getVehicleModel().startsWith(substring) && a.getOrderAcceptanceTime().equals(orderAcceptanceTime))
                .collect(Collectors.groupingBy(MarketSalesTable::getBranch, Collectors.summingLong(MarketSalesTable::getNumber)));
        System.out.println("按照地区分类，选出车型为cpc和日期符合的数据"+sumByBranch);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMap(sumByBranch);
//        Set<String> keys = sumByBranch.keySet();
//        for (String key : keys) {
//            Long aLong = sumByBranch.get(key);
//            marketIndexResult.setNumber(aLong);
//            marketIndexResult.setBranch(key);
//        }

        JSONObject json = new JSONObject(sumByBranch);
//       marketIndexResult.setJson(json);
        return marketIndexResult;
    }

    /*
     *指标13
//     **/
//    @PostMapping("/ModelMonthlyProduction")
//    public  MarketIndexResult ProportionOfClassIVVTrams(@RequestBody MarketSalesTable marketSalesTable){
//
//        Date orderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime();
//        String vehicleModel = marketSalesTable.getVehicleModel();
//        String substring = vehicleModel.substring(0, 3);
//
//        //获取到全部的数据
//        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
//
//        Map<String, Long> sumByBranch = marketSalesTables.stream()
//                .filter(a -> a.getVehicleModel().startsWith(substring) && a.getOrderAcceptanceTime().equals(orderAcceptanceTime))
//                .collect(Collectors.groupingBy(MarketSalesTable::getBranch, Collectors.summingLong(MarketSalesTable::getNumber)));
//        System.out.println("按照地区分类，选出车型为cpc和日期符合的数据"+sumByBranch);
//
//
//
//        MarketIndexResult marketIndexResult = new MarketIndexResult();
//        marketIndexResult.setMap(sumByBranch);
////        Set<String> keys = sumByBranch.keySet();
////        for (String key : keys) {
////            Long aLong = sumByBranch.get(key);
////            marketIndexResult.setNumber(aLong);
////            marketIndexResult.setBranch(key);
////        }
//        return marketIndexResult;
//    }
 /*
     *指标14
     **/
    @PostMapping("/ModelGrowthProportion")
    public MarketIndexResult ModelGrowthProportion(){


        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        // 定义日期格式化器，将日期转换为月份字符串

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//        Map<String, Map<String, List<MarketSalesTable>>>
         Map<String, Map<String, Long>> collect = marketSalesTables.stream()
                .collect(Collectors.groupingBy(   m -> getVehicleModelPrefix(m.getVehicleModel()),
                        Collectors.groupingBy(m -> m.getOrderAcceptanceTime().
                                toInstant().
                                atZone(ZoneId.systemDefault()).
                                toLocalDate().format(formatter), Collectors.summingLong(a -> a.getNumber()))

                ));


        System.out.println("按照车型和月份的分组"+collect);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMapMap(collect);

        return marketIndexResult;

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
    * 指标15
    * */
    @PostMapping("/ModelBranchProportion")
    public MarketIndexResult ModelBranchProportion(@RequestBody MarketSalesTable OrderAcceptanceTime){

        //获取月份
         LocalDate localDate = OrderAcceptanceTime.getOrderAcceptanceTime().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
         int monthValue = localDate.getMonthValue();


        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //获取到每个月的数据
         List<MarketSalesTable> monthMarketSalesTables = marketSalesTables.stream().filter(a -> a.getOrderAcceptanceTime().getMonth()+1 == (monthValue)).collect(Collectors.toList());
        System.out.println("按照月份筛选的数据"+monthMarketSalesTables);



//      筛选完月份的数据，获取每个月按照地区分类的主要车型的数据
        Map<String, Map<String, Long>> collect1 = monthMarketSalesTables.stream()
                .collect(Collectors.groupingBy(
                        MarketSalesTable::getBranch, // 按照地区分组
                        Collectors.groupingBy(
                                a -> getVehicleModelPrefix(a.getVehicleModel()), // 按照车型前缀分组
                                Collectors.summingLong(MarketSalesTable::getNumber) // 计算每个分组中 Number 的和
                        )
                ));


        System.out.println("按照网点和月份的分组"+collect1);




        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMapMap(collect1);
        return marketIndexResult;

    }
}
