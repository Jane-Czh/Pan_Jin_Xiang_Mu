package com.ruoyi.market.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
//import com.heli.production.domain.ProductionTable;



import com.ruoyi.common.core.controller.BaseController;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketIndexResult;
import com.ruoyi.market.domain.MarketSalesTable;

import com.ruoyi.market.domain.VoEntity;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
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

    @Autowired
     private IMarketSalesTableService iMarketSalesTableService;
    @Autowired
    private IMarketAfterSaleLedgerService iMarketAfterSaleLedgerService;
    @PostMapping("/IClassProportion")
    public  List<VoEntity>  ProportionOfClassITrams(@RequestBody MarketSalesTable marketSalesTable){
        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());
        Date orderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime();
         String vehicleModel = marketSalesTable.getVehicleModel();
        System.out.println("获取到的车型"+vehicleModel);
//        String substring = vehicleModel.substring(0, 3);

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        //获取到全部的数据的number  做为分母
         long allSum = marketSalesTables.stream().mapToLong(MarketSalesTable::getNumber).sum();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
         Map<String, Map<String, Long>> cpd = marketSalesTables.stream().filter((MarketSalesTable a) ->
        {
            return a.getVehicleModel().substring(0, 3).equals("CPD") &&
                    marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
                    && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth();

        })
//                .collect(Collectors.groupingBy(MarketSalesTable::getBranch
//                        , Collectors.groupingBy(
//                                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
//                                , Collectors.summingLong(MarketSalesTable::getNumber))
//                ));
                        .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.groupingBy(MarketSalesTable::getBranch, Collectors.summingLong(MarketSalesTable::getNumber))
                ));
//        {2024-01={天津={10},上海={2},合肥={4}}}

        System.out.println("收集到的list"+cpd);

         List<VoEntity> convert = VoEntity.convert(cpd);
        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMapMap(cpd);
//

        return convert;
//
    }
    /*
    *指标12
    **/
    @PostMapping("/IVVClassProportion")
    public   List<VoEntity> ProportionOfClassIVVTrams(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //规定年月日的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

         Map<String, Map<String, Long>> cpc = marketSalesTables.stream().filter((MarketSalesTable a) ->
        {
            return a.getVehicleModel().substring(0, 3).equals("CPC") &&
                    marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
                    && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth();

        })
//                .collect(Collectors.groupingBy(MarketSalesTable::getBranch
//                        , Collectors.groupingBy(
//                                a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter)
//                                , Collectors.summingLong(MarketSalesTable::getNumber))
//                ));
                        .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(formatter),
                        Collectors.groupingBy(MarketSalesTable::getBranch, Collectors.summingLong(MarketSalesTable::getNumber))
                ));

        System.out.println("按照地区分类，选出车型为cpc和日期符合的数据"+cpc);

         List<VoEntity> convert = VoEntity.convert(cpc);

        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setMapMap(cpc);

        return convert;
    }

    /*
     *指标13各主要车型产量月度比率
//     **/
    @PostMapping("/ModelMonthlyProduction")
    public  MarketIndexResult ModelMonthlyProduction(@RequestBody MarketAfterSaleLedger marketAfterSaleLedger){
            //获取到商品车台账表全部数据
         List<MarketAfterSaleLedger> marketAfterSaleLedgers = iMarketAfterSaleLedgerService.selectMarketAfterSaleLedgerList();
        System.out.println("获取所有数据"+marketAfterSaleLedgers);
        //没有车型表，先写具体主要车型
        //筛选出cpc,cpd,cpcd的所有月份对应的数量

         Map<String, Map<Integer, Long>> collect = marketAfterSaleLedgers.stream().filter((MarketAfterSaleLedger a) ->
        {
            return
                    marketAfterSaleLedger.getStartTime().getMonth() <= a.getFeedbackDate().getMonth()
                            && a.getFeedbackDate().getMonth() <= marketAfterSaleLedger.getEndTime().getMonth();

        })
                .collect(Collectors.groupingBy(a -> a.getVehicle()
                        , Collectors.groupingBy(
                                a -> a.getFeedbackDate().getMonth(), Collectors.counting()
                        )
                ));


        MarketIndexResult marketIndexResult = new MarketIndexResult();
//         List<Map<String, Map<Integer, Long>>> listMap = marketIndexResult.getListMap();
        System.out.println("筛选出cpc,cpd,cpcd的所有月份对应的数量"+collect);
//        System.out.println(cpd);
//        System.out.println(cpcd);
//        listMap.add(cpc);listMap.add(cpd);listMap.add(cpcd);
        marketIndexResult.setIntegerMap(collect);


        return marketIndexResult;
    }
 /*
     *指标14
     **/
    @PostMapping("/ModelGrowthProportion")
    public MarketIndexResult ModelGrowthProportion(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("接收对象的起止时间"+marketSalesTable);
        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //取出今年，取出当年的，各个主要车型的各个月的总数
        System.out.println(marketSalesTables);
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        Map<String, Map<Integer, Long>> curMonth = marketSalesTables.stream().filter(a -> marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
                && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth()).
                collect(Collectors.groupingBy(
                        m -> getVehicleModelPrefix(m.getVehicleModel())
                        , Collectors.groupingBy(a -> a.getOrderAcceptanceTime().getMonth()
                                , Collectors.summingLong(MarketSalesTable::getNumber))
                ));
        System.out.println("取出当年的，各个车型的各个月的总数"+curMonth);
        // 取出去年，各个主要车型的各个月的总数
            //取出去年的年份。
            int currentYear = Year.now().getValue();
            int beforeYear=currentYear-1;
        System.out.println("去年的数据"+beforeYear);
        Map<String, Map<Integer, Long>> beforeMonth = marketSalesTables.stream().filter(a->a.getOrderAcceptanceTime().getYear()==beforeYear
                && marketSalesTable.getStartTime().getMonth() <= a.getOrderAcceptanceTime().getMonth()
                && a.getOrderAcceptanceTime().getMonth() <= marketSalesTable.getEndTime().getMonth()).
                collect(Collectors.groupingBy(
                        m -> getVehicleModelPrefix(m.getVehicleModel())
                        , Collectors.groupingBy(a -> a.getOrderAcceptanceTime().getMonth()
                                , Collectors.summingLong(MarketSalesTable::getNumber))
                ));
        System.out.println("取出去年的，各个车型的各个月的总数"+beforeMonth);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        MarketIndexResult marketIndexResult = new MarketIndexResult();
         List<Map<String, Map<Integer, Long>>> listMap = new LinkedList<>();
        if(!curMonth.isEmpty()){
            listMap.add(curMonth);

        }
        if(!beforeMonth.isEmpty())
        {
            listMap.add(beforeMonth);
        }
        marketIndexResult.setListMap(listMap);

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
    public MarketIndexResult ModelBranchProportion(@RequestBody MarketSalesTable marketSalesTable){

        //获取月份
//         LocalDate localDate = marketSalesTable.getOrderAcceptanceTime().toInstant()
//                .atZone(ZoneId.systemDefault()).toLocalDate();
//         int monthValue = localDate.getMonthValue();
        LocalDate currentDate = LocalDate.now();

        // 获取当前月份
        int currentMonth = currentDate.getMonthValue();
        System.out.println("当前月份为：" + currentMonth);

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //获取到每个月的数据
         List<MarketSalesTable> monthMarketSalesTables = marketSalesTables.stream().filter(a -> a.getOrderAcceptanceTime().getMonth()+1 == (currentMonth)).collect(Collectors.toList());
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
    /*
     * 指标16各网点已接单未发车总台数
     * */
    @PostMapping("/OrderWaitSend")
    public  MarketIndexResult OrderWaitSend(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();
        //可能需要改日期；以及过滤条件改成一个月；
        Map<String, Map<Integer, Long>> cpc =
                marketSalesTables.stream().filter((MarketSalesTable a) ->
                {        return  a.getActualDepartureDate()==null&&
                        marketSalesTable.getStartTime().getMonth()<= a.getOrderAcceptanceTime().getMonth()
                        && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth();

                })
                        .collect(Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.groupingBy(
                                        a -> a.getOrderAcceptanceTime().getMonth()
                                        , Collectors.summingLong(MarketSalesTable::getNumber))
                        ));
        System.out.println("选出实际发车日期为空的,再按照地区和月份统计为发车的数量"+cpc);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setIntegerMap(cpc);
        return marketIndexResult;
    }
    /*
     * 指标17各网点已接单未发车总台数
     * */
    @PostMapping("/BranchWaitProduction")
    public  MarketIndexResult BranchWaitProduction(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        Map<String, Map<Integer, Long>> cpc =
                marketSalesTables.stream().filter((MarketSalesTable a) ->
                {        return  a.getCarNumber()==null|| containsChinese(a.getCarNumber());
//                   && marketSalesTable.getStartTime().getMonth()<= a.getOrderAcceptanceTime().getMonth()
//                            && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth();

                })
                        .collect(Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.groupingBy(
                                        a -> a.getOrderAcceptanceTime().getMonth()
                                        , Collectors.summingLong(MarketSalesTable::getNumber))
                        ));
        System.out.println("选出车号为空或者为汉字的,再按照地区和月份统计数量"+cpc);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setIntegerMap(cpc);
        return marketIndexResult;
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
    public  MarketIndexResult OverWorkWaitDelivery(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        Map<String, Map<Integer, Long>> cpc =
                marketSalesTables.stream().filter((MarketSalesTable a) ->
                {         return  a.getActualDepartureDate()==null&&a.getSystemDeliveryTime()!=null&&
                        marketSalesTable.getStartTime().getMonth()<= a.getOrderAcceptanceTime().getMonth()
                        && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth();

                })
                        .collect(Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.groupingBy(
                                        a -> a.getOrderAcceptanceTime().getMonth()
                                        , Collectors.summingLong(MarketSalesTable::getNumber))
                        ));
        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照地区和月份统计数量"+cpc);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setIntegerMap(cpc);
        return marketIndexResult;
    }

    /*
     * 指标19各网点已到期未完工订单数
     * */
    @PostMapping("/OvertimedOrderNumber")
    public  MarketIndexResult OvertimedOrderNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();

        Map<String, Map<Integer, Long>> cpc =
                marketSalesTables.stream().filter((MarketSalesTable a) ->
                {         return  a.getActualDepartureDate()==null&&a.getSystemDeliveryTime()!=null&&
                        marketSalesTable.getStartTime().getMonth()<= a.getOrderAcceptanceTime().getMonth()
                        && a.getOrderAcceptanceTime().getMonth()<= marketSalesTable.getEndTime().getMonth();

                })
                        .collect(Collectors.groupingBy(MarketSalesTable::getBranch
                                , Collectors.groupingBy(
                                        a -> a.getOrderAcceptanceTime().getMonth()
                                        , Collectors.summingLong(MarketSalesTable::getNumber))
                        ));
        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照地区和月份统计数量"+cpc);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setIntegerMap(cpc);
        return marketIndexResult;
    }

    /*
     * 指标72  日/月/年接单数
     * */
    @PostMapping("/OrderReceiveNumber")
    public  MarketIndexResult OrderReceiveNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();


        Map<Date, Long> collect = marketSalesTables.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getOrderAcceptanceTime()
                        , Collectors.summingLong(MarketSalesTable::getNumber))
                );
        System.out.println("筛选出实际发车日期为空，系统发车日期不为空,再按照地区和月份统计数量"+collect);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setDatemap(collect);
        return marketIndexResult;
    }

    /*
     * 指标73  日/月/年交货数
     * 可能需要修改 传出参数；YY-MM-DD
     * */
    @PostMapping("/OrderDeliveryNumber")
    public  MarketIndexResult OrderDeliveryNumber(@RequestBody MarketSalesTable marketSalesTable){

        System.out.println("获取到的实体类"+marketSalesTable);
        System.out.println("获取订单总台数"+marketSalesTable.getNumberInput());
        System.out.println("获取到起止时间"+marketSalesTable.getStartTime()+marketSalesTable.getEndTime());

        //获取到全部的数据
        List<MarketSalesTable> marketSalesTables = iMarketSalesTableService.selectMarketSalesTableList1();


        Map<Date, Long> collect = marketSalesTables.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getSystemDeliveryTime()
                        , Collectors.summingLong(MarketSalesTable::getNumber))
                );

        System.out.println("按照日期分组，统计台数"+collect);



        MarketIndexResult marketIndexResult = new MarketIndexResult();
        marketIndexResult.setDatemap(collect);
        return marketIndexResult;
    }

}
