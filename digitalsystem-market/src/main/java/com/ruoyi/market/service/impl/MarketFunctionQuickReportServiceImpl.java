package com.ruoyi.market.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.JudgmentString;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.MarketFunctionQuickReportMapper;
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.service.IMarketFunctionQuickReportService;

/**
 * 市场科销售快报功能Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class MarketFunctionQuickReportServiceImpl implements IMarketFunctionQuickReportService 
{
    @Autowired
    private MarketFunctionQuickReportMapper marketFunctionQuickReportMapper;

    /**
     * 同步数据库销售快报数据
     *
     *
     *
     */
    @Override
    public void Synchronization(List<MarketSalesTable> list, MarketFunctionQuickReport marketFunctionQuickReport1){
        int count = 0;
        LocalDateTime currentDateTime = LocalDateTime.now(); //获取当前时间
        int year = getTime.getYear(currentDateTime);
        int month = getTime.getMonth(currentDateTime);
        int day = getTime.getDay(currentDateTime);
        HashMap<String, MarketFunctionQuickReport> result = new HashMap<String, MarketFunctionQuickReport>();
        while (count < list.size()){
            MarketSalesTable marketSalesTable = list.get(count);
            String branch = marketSalesTable.getBranch();
            Date OrderAcceptanceTime = marketSalesTable.getOrderAcceptanceTime(); //接单日期，对应表格D列
            Date SystemDeliveryTime = marketSalesTable.getSystemDeliveryTime(); //系统交货期，对应表格X列
            Date ActualDepartureDate = marketSalesTable.getActualDepartureDate(); //实际发车日期，对应表格W列
            String CarNumber = marketSalesTable.getCarNumber(); //车号，对应表格N列

            //hash中没有当前网点数据，新建一个MarketFunctionQuickReport类插入其中，分别给出其初始值
            if (result.get(branch) == null){
                MarketFunctionQuickReport marketFunctionQuickReport = new MarketFunctionQuickReport();
                //存入网点名称
                marketFunctionQuickReport.setCommercialNetworks(branch);
                //判断接单日期
                if (OrderAcceptanceTime != null){
                    //年相等，本年累计接单加一
                    if (SplitDate.splitDate(OrderAcceptanceTime)[0] == year){
                        marketFunctionQuickReport.setOrderAccumulationThisYear(1);
                        //月相等，本月累计接单加一
                        if (SplitDate.splitDate(OrderAcceptanceTime)[1] == month){
                            marketFunctionQuickReport.setOrdersReceivedThisMonth(1);
                            //日相等，本日接单加一
                            if (SplitDate.splitDate(OrderAcceptanceTime)[2] == day){
                                marketFunctionQuickReport.setOrderReceivedToday(1);
                            } else{
                                marketFunctionQuickReport.setOrderReceivedToday(0);
                            }
                        }else {
                            marketFunctionQuickReport.setOrderReceivedToday(0);
                            marketFunctionQuickReport.setOrdersReceivedThisMonth(0);
                        }
                    }else {
                        marketFunctionQuickReport.setOrderReceivedToday(0);
                        marketFunctionQuickReport.setOrdersReceivedThisMonth(0);
                        marketFunctionQuickReport.setOrderAccumulationThisYear(0);
                    }
                }else {
                    marketFunctionQuickReport.setOrderReceivedToday(0);
                    marketFunctionQuickReport.setOrdersReceivedThisMonth(0);
                    marketFunctionQuickReport.setOrderAccumulationThisYear(0);
//                    System.out.println("接单日期为空");
                }

                //判断系统交货日期
                if (SystemDeliveryTime != null){
                    //年相等，本年累计系统交货数加一
                    if (SplitDate.splitDate(SystemDeliveryTime)[0] == year){
                        marketFunctionQuickReport.setCumulativeSystemDeliveriesForTheYear(1);
                        //月相等，本月累计系统交货数加一
                        if (SplitDate.splitDate(SystemDeliveryTime)[1] == month){
                            marketFunctionQuickReport.setDeliveryWithinTheSystemThisMonth(1);
                            //日相等，本日累计系统交货数加一
                            if (SplitDate.splitDate(SystemDeliveryTime)[2] == day){
                                marketFunctionQuickReport.setDeliveryToday(1);
                            } else{
                                marketFunctionQuickReport.setDeliveryToday(0);
                            }
                        }else {
                            marketFunctionQuickReport.setDeliveryToday(0);
                            marketFunctionQuickReport.setDeliveryWithinTheSystemThisMonth(0);
                        }
                    }else {
                        marketFunctionQuickReport.setDeliveryToday(0);
                        marketFunctionQuickReport.setDeliveryWithinTheSystemThisMonth(0);
                        marketFunctionQuickReport.setCumulativeSystemDeliveriesForTheYear(0);
                    }
                    marketFunctionQuickReport.setUnfilledOrders(0);
                }else {
                    marketFunctionQuickReport.setDeliveryToday(0);
                    marketFunctionQuickReport.setDeliveryWithinTheSystemThisMonth(0);
                    marketFunctionQuickReport.setCumulativeSystemDeliveriesForTheYear(0);
                    marketFunctionQuickReport.setUnfilledOrders(1);
                }

                //判断实际发车日期
                if (ActualDepartureDate != null){
                    //年相等，本年累计实发数加一
                    if (SplitDate.splitDate(ActualDepartureDate)[0] == year){
                        marketFunctionQuickReport.setCumulativeIssuedThisMonth(1);
                        //月相等，本月累计实发数加一
                        if (SplitDate.splitDate(ActualDepartureDate)[1] == month){
                            marketFunctionQuickReport.setIssuedThisMonth(1);
                            //日相等，本日累计实发数加一
                            if (SplitDate.splitDate(ActualDepartureDate)[2] == day){
                                marketFunctionQuickReport.setIssuedThisDay(1);
                            }else {
                                marketFunctionQuickReport.setIssuedThisDay(0);
                            }
                        }else {
                            marketFunctionQuickReport.setIssuedThisDay(0);
                            marketFunctionQuickReport.setIssuedThisMonth(0);
                        }
                    }else {
                        marketFunctionQuickReport.setIssuedThisDay(0);
                        marketFunctionQuickReport.setIssuedThisMonth(0);
                        marketFunctionQuickReport.setCumulativeIssuedThisMonth(0);
                    }
                }else {
//                    System.out.println("实际发车日期为空");
                    marketFunctionQuickReport.setIssuedThisDay(0);
                    marketFunctionQuickReport.setIssuedThisMonth(0);
                    marketFunctionQuickReport.setCumulativeIssuedThisMonth(0);
                }

                //判断系统交货实物未提
                if (SystemDeliveryTime != null && ActualDepartureDate == null){
                    marketFunctionQuickReport.setPhysicalDeliveryOfTheSystemWasNotMentioned(1);
                }else {
                    marketFunctionQuickReport.setPhysicalDeliveryOfTheSystemWasNotMentioned(0);
                }

                //判断网点未排订单
                if (CarNumber == null || JudgmentString.containsChinese(CarNumber)){
                    marketFunctionQuickReport.setTheNetworkHasNoOrders(1);
                }else {
                    marketFunctionQuickReport.setTheNetworkHasNoOrders(0);
                }

                //需要修改
                marketFunctionQuickReport.setLastMonthBalanceOrder(0);
                //插入hash
                result.put(branch,marketFunctionQuickReport);
            }else {
                //hash中存在当前网点数据,取出并更新数据
                MarketFunctionQuickReport marketFunctionQuickReport = result.get(branch);
                //判断接单日期
                if (OrderAcceptanceTime != null){
                    //年相等，本年累计接单加一
                    if (SplitDate.splitDate(OrderAcceptanceTime)[0] == year){
                        int i = marketFunctionQuickReport.getOrderAccumulationThisYear();
                        i++;
                        marketFunctionQuickReport.setOrderAccumulationThisYear(i);
                        //月相等，本月累计接单加一
                        if (SplitDate.splitDate(OrderAcceptanceTime)[1] == month){
                            int k = marketFunctionQuickReport.getOrdersReceivedThisMonth();
                            k++;
                            marketFunctionQuickReport.setOrdersReceivedThisMonth(k);
                            //日相等，本日接单加一
                            if (SplitDate.splitDate(OrderAcceptanceTime)[2] == day){
                                int j = marketFunctionQuickReport.getOrderReceivedToday();
                                j++;
                                marketFunctionQuickReport.setOrderReceivedToday(j);
                            }
                        }
                    }
                }else {
//                    System.out.println("接单日期为空");
                }

                //判断系统交货日期
                if (SystemDeliveryTime != null){
                    //年相等，本年累计系统交货数加一
                    if (SplitDate.splitDate(SystemDeliveryTime)[0] == year){
                        int i = marketFunctionQuickReport.getCumulativeSystemDeliveriesForTheYear();
                        i++;
                        marketFunctionQuickReport.setCumulativeSystemDeliveriesForTheYear(i);
                        //月相等，本月累计系统交货数加一
                        if (SplitDate.splitDate(SystemDeliveryTime)[1] == month){
                            int k = marketFunctionQuickReport.getDeliveryWithinTheSystemThisMonth();
                            k++;
                            marketFunctionQuickReport.setDeliveryWithinTheSystemThisMonth(k);
                            //日相等，本日累计系统交货数加一
                            if (SplitDate.splitDate(SystemDeliveryTime)[2] == day){
                                int j = marketFunctionQuickReport.getDeliveryToday();
                                j++;
                                marketFunctionQuickReport.setDeliveryToday(j);
                            }
                        }
                    }
                }else {
                    int i = marketFunctionQuickReport.getUnfilledOrders();
                    i++;
                    marketFunctionQuickReport.setUnfilledOrders(i);
                }

                //判断实际发车日期
                if (ActualDepartureDate != null){
                    //年相等，本年累计实发数加一
                    if (SplitDate.splitDate(ActualDepartureDate)[0] == year){
                        int i = marketFunctionQuickReport.getCumulativeIssuedThisMonth();
                        i++;
                        marketFunctionQuickReport.setCumulativeIssuedThisMonth(i);
                        //月相等，本月累计实发数加一
                        if (SplitDate.splitDate(ActualDepartureDate)[1] == month){
                            int k = marketFunctionQuickReport.getIssuedThisMonth();
                            k++;
                            marketFunctionQuickReport.setIssuedThisMonth(k);
                            //日相等，本日累计实发数加一
                            if (SplitDate.splitDate(ActualDepartureDate)[2] == day){
                                int j = marketFunctionQuickReport.getIssuedThisDay();
                                j++;
                                marketFunctionQuickReport.setIssuedThisDay(j);
                            }
                        }
                    }
                }else {
//                    System.out.println("实际发车日期为空");
                }

                //判断系统交货实物未提
                if (SystemDeliveryTime != null && ActualDepartureDate == null){
                    int i = marketFunctionQuickReport.getPhysicalDeliveryOfTheSystemWasNotMentioned();
                    i++;
                    marketFunctionQuickReport.setPhysicalDeliveryOfTheSystemWasNotMentioned(i);
                }

                //判断网点未排订单
                if (CarNumber == null || JudgmentString.containsChinese(CarNumber)){
                    int i = marketFunctionQuickReport.getTheNetworkHasNoOrders();
                    i++;
                    marketFunctionQuickReport.setTheNetworkHasNoOrders(i);
                }

                //更新hashmap中的内容
                result.replace(branch, marketFunctionQuickReport);
            }

            count++;
        }

        //先删除数据库中所有快报
        List<MarketFunctionQuickReport> list1 = selectMarketFunctionQuickReportList(marketFunctionQuickReport1);
        int x = 0;
        Long[] deleteId = new Long[list1.size()];
        while (x < list1.size()){
            MarketFunctionQuickReport value = list1.get(x);
            deleteId[x] = value.getMfqrId();
            x++;
        }
        deleteMarketFunctionQuickReportByMfqrIds(deleteId);
        System.out.println("删除成功");
        //依次插入新生成的快报
        Long id = 0L;
        for (Map.Entry<String, MarketFunctionQuickReport> entry : result.entrySet()){
            System.out.println("-------------------");
            MarketFunctionQuickReport insertValue = entry.getValue();
            insertValue.setUploadDate(getTime.getCurrentDate());
            insertValue.setMfqrId(GenerateId.getNextId(id));
            insertMarketFunctionQuickReport(insertValue);
            id++;
            System.out.println("插入成功");
        }
    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return marketFunctionQuickReportMapper.selectLastId();
    }

    /**
     * 查询市场科销售快报功能
     * 
     * @param mfqrId 市场科销售快报功能主键
     * @return 市场科销售快报功能
     */
    @Override
    public MarketFunctionQuickReport selectMarketFunctionQuickReportByMfqrId(Long mfqrId)
    {
        return marketFunctionQuickReportMapper.selectMarketFunctionQuickReportByMfqrId(mfqrId);
    }

    /**
     * 查询市场科销售快报功能列表
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 市场科销售快报功能
     */
    @Override
    public List<MarketFunctionQuickReport> selectMarketFunctionQuickReportList(MarketFunctionQuickReport marketFunctionQuickReport)
    {

        return marketFunctionQuickReportMapper.selectMarketFunctionQuickReportList(marketFunctionQuickReport);
    }

    /**
     * 新增市场科销售快报功能
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 结果
     */
    @Override
    public int insertMarketFunctionQuickReport(MarketFunctionQuickReport marketFunctionQuickReport)
    {
        return marketFunctionQuickReportMapper.insertMarketFunctionQuickReport(marketFunctionQuickReport);
    }

    /**
     * 修改市场科销售快报功能
     * 
     * @param marketFunctionQuickReport 市场科销售快报功能
     * @return 结果
     */
    @Override
    public int updateMarketFunctionQuickReport(MarketFunctionQuickReport marketFunctionQuickReport)
    {
        return marketFunctionQuickReportMapper.updateMarketFunctionQuickReport(marketFunctionQuickReport);
    }

    /**
     * 批量删除市场科销售快报功能
     * 
     * @param mfqrIds 需要删除的市场科销售快报功能主键
     * @return 结果
     */
    @Override
    public int deleteMarketFunctionQuickReportByMfqrIds(Long[] mfqrIds)
    {
        return marketFunctionQuickReportMapper.deleteMarketFunctionQuickReportByMfqrIds(mfqrIds);
    }

    /**
     * 删除市场科销售快报功能信息
     * 
     * @param mfqrId 市场科销售快报功能主键
     * @return 结果
     */
    @Override
    public int deleteMarketFunctionQuickReportByMfqrId(Long mfqrId)
    {
        return marketFunctionQuickReportMapper.deleteMarketFunctionQuickReportByMfqrId(mfqrId);
    }
}
