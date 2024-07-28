package com.heli.financial.service.impl;

import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialDataService;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @BelongsProject: ruoyi
 * @BelongsPackage: com.heli.financial.service.impl
 * @Author: hong
 * @CreateTime: 2024-04-15  13:16
 * @Description: TODO
 * @Version: 1.0
 */
@Aspect
@Service
public class FinancialDataServiceImpl implements IFinancialDataService {


    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;

    private static final Logger log = LoggerFactory.getLogger(FinancialDataServiceImpl.class);


    public int batchUpdateFinancialData(Date yearAndMonth) {
        Date fillingMaxMonth = financialIndicatorsHandfillTableService.selectMaxYearAndMonth();
        Date interestsMaxMonth = financialInterestsTableService.selectMaxYearAndMonth();
        Date balanceMaxMonth = financialBalanceTableService.selectMaxYearAndMonth();


        List<Date> dates = new ArrayList<>();
        dates.add(fillingMaxMonth);
        // 假设这里添加了更多的日期
        dates.add(interestsMaxMonth);
        dates.add(balanceMaxMonth);

        Date minDate = Collections.min(dates);
        System.out.println("三个数据中最小的时间是：" + minDate);


        int i = 1;
        for (Date date = yearAndMonth; date.before(DateUtils.getNextMonth(minDate)); date = DateUtils.getNextMonth(date)) {
            if (checkDataUploadedForCurrentMonth(date) && checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(date))) {
                calculateCurrentMonthFinancialData(date);
                i++;
            }
        }

        return i;

    }

    /**
     * @description: 检查当月数据是否全部上传
     * @author: hong
     * @date: 2024/4/23 10:49
     **/
    @Override
    public boolean checkDataUploadedForCurrentMonth(Date yearAndMonth) {
        return financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(yearAndMonth)
                && financialInterestsTableService.checkInterestsDataIsExisted(yearAndMonth)
                && financialBalanceTableService.checkBalanceDataIsExisted(yearAndMonth);
    }


    /**
     * @description: 财务当月数据上传完成后，计算
     * @author: hong
     * @date: 2024/4/23 10:56
     **/

//    @Override
////    @After(
////            value = "execution(* com.heli.financial.controller.FinancialDataController.importBalanceTable(..)) || " +
////                    "execution(* com.heli.financial.controller.FinancialDataController.importIndicatorsTable(..)) || " +
////                    "execution(* com.heli.financial.controller.FinancialDataController.handFillData(..))")
//    public int calculateCurrentMonthFinancialData(JoinPoint joinPoint) {
//        System.out.println(Arrays.toString(joinPoint.getArgs()));
//
////        System.out.println(joinPoint.getArgs()[2].toString());
//
//        Date yearAndMonth = (Date) joinPoint.getArgs()[0];
//
//        System.out.println("当前月份为：" + yearAndMonth);
//
//        // 检查当月文件是否上传完成，全部上传后开始计算
//        if (!checkDataUploadedForCurrentMonth(yearAndMonth)) {
//            System.out.println("当月数据不全");
//            return 0;
//        }
//
//        FinancialIndicatorsHandfillTable handFillDate = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);
//        FinancialInterestsTable interestsTable = financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth);
//        FinancialBalanceTable balanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
//
//        System.out.println(handFillDate);
//        System.out.println(interestsTable);
//        System.out.println(balanceTable);
//
//
//        /**
//         * @description: 计算当月库存商品存货额
//         * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
//         * monthAmountInStock =（资产负债表） inventoryVehicles + pcvFinished - reserveCarAmount
//         **/
//        balanceTable.setMonthAmountInStock(
//                balanceTable.getInventoryVehicles()
//                        .add(balanceTable.getPcvFinished()
//                                .subtract(handFillDate.getReserveCarAmount()))
//        );
//
//
//
//        /**
//         * @description: 计算应收帐款周转率
//         * 应收帐款周转率 = （利润表）营业收入 / (资产负债表) 应收账款
//         * turnoverRateReceivable =（利润表） operatingRevenue / (资产负债表) receivables
//         * 由于计算比率，所以保留2位小数，并四舍五入
//         **/
//        balanceTable.setTurnoverRateReceivable(
//                (interestsTable.getOperatingRevenue()
//                        .divide(balanceTable.getReceivables(), 2, RoundingMode.HALF_UP).doubleValue())
//        );
//
//        // 计算
////        balanceTable.setGrowthRateInventorySales(countGrowthRateInventorySales(yearAndMonth));
//
//
////        return financialBalanceTableService.updateFinancialBalanceTable(countGrowthRateInventoryAndSales(balanceTable));
//
//
//
//        return financialBalanceTableService.updateFinancialBalanceTable(countGrowthRateInventoryAndSales(balanceTable));
//
//    }


    /**
     * @description: 财务当月数据上传完成后，计算
     * @author: hong
     * @date: 2024/4/23 10:56
     **/

    @Override
    public int calculateCurrentMonthFinancialData(Date yearAndMonth) {

//        System.out.println(yearAndMonth);
        log.info("当前计算的月份为：" + DateUtils.parseDateToStr("yyyy-MM", yearAndMonth));

        FinancialIndicatorsHandfillTable handFillDate = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);
        FinancialInterestsTable interestsTable = financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth);
        FinancialBalanceTable balanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);


//        System.out.println(handFillDate);
//        System.out.println(interestsTable);
//        System.out.println(balanceTable);

        /**
         * @description: 计算当月库存商品存货额
         * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
         * monthAmountInStock =（资产负债表） inventoryVehicles + pcvFinished - reserveCarAmount
         **/
        BigDecimal monthAmountInStock = balanceTable.getInventoryVehicles().add(balanceTable.getPcvFinished()
                .subtract(handFillDate.getReserveCarAmount()));
        log.info("当月库存商品存货额为：" + monthAmountInStock);
        balanceTable.setMonthAmountInStock(monthAmountInStock);


        /**
         * @description: 计算应收帐款周转率
         * 应收帐款周转率 = （利润表）营业收入 / (资产负债表) 应收账款
         * turnoverRateReceivable =（利润表） operatingRevenue / (资产负债表) receivables
         * 由于计算比率，所以保留2位小数，并四舍五入
         **/

        double turnoverRateReceivable = interestsTable.getOperatingRevenue()
                .divide(balanceTable.getReceivables(), 2, RoundingMode.HALF_UP).doubleValue();
        log.info("应收帐款周转率为：" + turnoverRateReceivable);
        balanceTable.setTurnoverRateReceivable(turnoverRateReceivable);

        // 计算
//        balanceTable.setGrowthRateInventorySales(countGrowthRateInventorySales(yearAndMonth));

        FinancialBalanceTable financialBalanceTable = countGrowthRateInventoryAndSales(balanceTable);
        log.info("计算结果为：" + financialBalanceTable);

        return financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
    }


    /**
     * @description: 计算存货增长率/销售增长率 growthRateInventorySales
     * (资产负债表)(b29(本月) / b29(上月) - 1)
     * (利润表)(b2(本月) / (b2(上月) - 1))
     * b29: monthlyInventoryTotalAmount 月度存货总金额
     * b2: operatingRevenue 营业收入
     * @author: hong
     * @date: 2024/4/3 15:32
     **/
    public FinancialBalanceTable countGrowthRateInventoryAndSales(FinancialBalanceTable balanceTable) {

        Date lastMonth = DateUtils.getLastMonth(balanceTable.getYearAndMonth());

        // 获取 本月和上月 月度存货总金额
        BigDecimal monthlyInventoryTotalAmount = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(balanceTable.getYearAndMonth());
        BigDecimal monthlyInventoryTotalAmountLastMonth = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(lastMonth);
//
        // 获取 本月和上月 营业收入
        BigDecimal operatingRevenue = financialInterestsTableService.selectOperatingRevenueByMonth(balanceTable.getYearAndMonth());
        BigDecimal operatingRevenueLastMonth = financialInterestsTableService.selectOperatingRevenueByMonth(lastMonth);

//        System.out.println("本月月度存货总金额" + monthlyInventoryTotalAmount
//                + "上月月度存货总金额" + monthlyInventoryTotalAmountLastMonth
//                + "本月营业收入" + operatingRevenue
//                + "上月营业收入" + operatingRevenueLastMonth
//        );

        //分别计算比率
        BigDecimal monthlyInventoryTotalAmountRate = monthlyInventoryTotalAmount.divide(monthlyInventoryTotalAmountLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));
        BigDecimal operatingRevenueRate = operatingRevenue.divide(operatingRevenueLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));

//        System.out.println("monthlyInventoryTotalAmountRate" + monthlyInventoryTotalAmountRate);
//        System.out.println("operatingRevenueRate" + operatingRevenueRate);

        balanceTable.setGrowthRateInventory(monthlyInventoryTotalAmountRate.doubleValue());
        balanceTable.setGrowthRateSales(operatingRevenueRate.doubleValue());


        //计算总比率
        return balanceTable;
    }

}
