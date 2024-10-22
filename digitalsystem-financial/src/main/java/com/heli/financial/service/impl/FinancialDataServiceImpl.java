package com.heli.financial.service.impl;

import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialDataService;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.utils.DateUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /**
     * @description: 更新按钮，重新计算全部数据
     * @author: hong
     * @date: 2024/9/5 17:47
     * @version: 1.0
     */
    @Override
    public int batchCalculateBalanceIndicator() {
        // 查询利润表最小月份
        Date minYearAndMonth = financialBalanceTableService.selectMinYearAndMonth();
        Date maxYearAndMonth = financialBalanceTableService.selectMaxYearAndMonth();
        int i = 0;
        //从最小时间遍历到最大时间
        for (Date date = minYearAndMonth; date.before(DateUtils.getNextMonth(maxYearAndMonth)); date = DateUtils.getNextMonth(date)) {
//            if (checkDataUploadedForCurrentMonth(date) && checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(date))) {
                calculateCurrentMonthFinancialData(date);
                i++;
//            }
        }

        // 获取当前时间
        return i;
    }


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
//            if (checkDataUploadedForCurrentMonth(date) && checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(date))) {
                calculateCurrentMonthFinancialData(date);
                i++;
//            }
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
     * @description: 财务当月数据上传完成后，计算   - outdated   已经在下面重写
     * @author: hong
     * @date: 2024/4/23 10:56
     **/
//    @Override
//    public int calculateCurrentMonthFinancialData(Date yearAndMonth) {
//
////        System.out.println(yearAndMonth);
//        log.info("当前计算的月份为：" + DateUtils.parseDateToStr("yyyy-MM", yearAndMonth));
//
//        FinancialIndicatorsHandfillTable handFillDate = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);
//        FinancialInterestsTable interestsTable = financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth);
//        FinancialBalanceTable balanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
//
//
////        System.out.println(handFillDate);
////        System.out.println(interestsTable);
////        System.out.println(balanceTable);
//
//        /**
//         * @description: 计算当月库存商品存货额
//         * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
//         * monthAmountInStock =（资产负债表） inventoryVehicles + pcvFinished - reserveCarAmount
//         **/
////        BigDecimal monthAmountInStock = balanceTable.getInventoryVehicles().add(balanceTable.getPcvFinished()
////                .subtract(handFillDate.getReserveCarAmount()));
//        BigDecimal monthAmountInStock = balanceTable.getInventoryVehicles().add(balanceTable.getPcvFinished());
//        log.info("当月库存商品存货额为：" + monthAmountInStock);
//        balanceTable.setMonthAmountInStock(monthAmountInStock);
//
//
//        // 计算存货增长率/销售增长率
//        FinancialBalanceTable financialBalanceTable = countGrowthRateInventoryAndSales(balanceTable);
//        log.info("计算结果为：" + financialBalanceTable);
//
//        int i = financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
//
//        countTurnoverRateReceivable(yearAndMonth);
//
//        return i;
//
//    }

    /**
     * @description: 财务当月数据上传完成后，计算
     * @author: hong
     * @date: 2024/9/27 17:55
     * @version: 1.0
     */
    @Override
    public int calculateCurrentMonthFinancialData(Date yearAndMonth) {
        log.info("当前计算的月份为：" + DateUtils.parseDateToStr("yyyy-MM", yearAndMonth));
        countRawMaterialInventory(yearAndMonth);
        countWorkInProgressInventory(yearAndMonth);
        countMonthAmountInStock(yearAndMonth);
        countMonthlyInventoryTotalAmount(yearAndMonth);
        countGrowthRateInventory(yearAndMonth);
        countTurnoverRateReceivable(yearAndMonth);

        return 1;

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
//    public FinancialBalanceTable countGrowthRateInventoryAndSales(FinancialBalanceTable balanceTable) {
//
//        Date lastMonth = DateUtils.getLastMonth(balanceTable.getYearAndMonth());
//
//        // 获取 本月和上月 月度存货总金额
//        BigDecimal monthlyInventoryTotalAmount = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(balanceTable.getYearAndMonth());
//        BigDecimal monthlyInventoryTotalAmountLastMonth = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(lastMonth);
////
//        // 获取 本月和上月 营业收入
//        BigDecimal operatingRevenue = financialInterestsTableService.selectOperatingRevenueByMonth(balanceTable.getYearAndMonth());
//        BigDecimal operatingRevenueLastMonth = financialInterestsTableService.selectOperatingRevenueByMonth(lastMonth);
//
//        System.out.println("本月月度存货总金额" + monthlyInventoryTotalAmount
//                + "上月月度存货总金额" + monthlyInventoryTotalAmountLastMonth
//                + "本月营业收入" + operatingRevenue
//                + "上月营业收入" + operatingRevenueLastMonth
//        );
//
//        //分别计算比率
////        BigDecimal monthlyInventoryTotalAmountRate = monthlyInventoryTotalAmount.divide(monthlyInventoryTotalAmountLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));
////        BigDecimal operatingRevenueRate = operatingRevenue.divide(operatingRevenueLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));
//
//        //新计算方式
//        BigDecimal totalAmountSubtract = monthlyInventoryTotalAmount.subtract(monthlyInventoryTotalAmountLastMonth);
//        BigDecimal operatingRevenueSubtract = operatingRevenue.subtract(operatingRevenueLastMonth);
//        log.info("存货增长额" + totalAmountSubtract + "--销售增长额" + operatingRevenueSubtract);
//
//        //如果上面两个值一个为0，则直接返回0
//        if (totalAmountSubtract.equals(BigDecimal.ZERO) || operatingRevenueSubtract.equals(BigDecimal.ZERO)) {
//            balanceTable.setGrowthRateInventory(0.0);
//        } else {
//            log.info("jinru");
//            balanceTable.setGrowthRateInventory(totalAmountSubtract.divide(operatingRevenueSubtract, 4, RoundingMode.HALF_UP).doubleValue() * 100);
//        }
//
//        //计算总比率
//        return balanceTable;
//    }

    /**
     * @description: 计算当月原材料存货额
     * @author: hong
     * @date: 2024/9/27 16:42
     * @version: 1.0
     */
    public void countRawMaterialInventory(Date yearAndMonth) {
        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);

        if (financialBalanceTable != null) {
            financialBalanceTable.setMonthlyRawMaterialInventory(financialBalanceTable.getInTransitInventory()
                    .add(financialBalanceTable.getMaterials())
                    .add(financialBalanceTable.getMaterialCostVariance())
                    .add(financialBalanceTable.getMaterialCostVarianceUnallocated()));
            log.info("当月原材料存货额为：" + financialBalanceTable.getMonthlyRawMaterialInventory());
        } else {
            financialBalanceTable.setMonthlyRawMaterialInventory(null);
            log.info("当月未生成数据，无法计算原材料存货额");
        }

        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * @description: 计算当月在制品存货额
     * @author: hong
     * @date: 2024/9/27 16:49
     * @version: 1.0
     */
    public void countWorkInProgressInventory(Date yearAndMonth) {
        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);

        if (financialBalanceTable != null) {
            financialBalanceTable.setMonthlyWorkInProgressInventory(
                    financialBalanceTable.getWorkInProgressSemiFinishedGoods()
                            .add(financialBalanceTable.getProductCostVarianceSemiFinishedGoods())
                            .add(financialBalanceTable.getWorkInProgressEndOfMonth()));
            log.info("当月在制品存货额为：" + financialBalanceTable.getMonthlyWorkInProgressInventory());
        } else {
            financialBalanceTable.setMonthlyWorkInProgressInventory(null);
            log.info("当月未生成数据，无法计算在制品存货额");
        }
        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * @description: 计算当月库存商品存货额
     * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
     * @author: hong
     * @date: 2024/9/27 16:51
     * @version: 1.0
     */
    public void countMonthAmountInStock(Date yearAndMonth) {
        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
        FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);

        if (financialBalanceTable != null && financialIndicatorsHandfillTable != null) {
            financialBalanceTable.setMonthAmountInStock(
                    financialBalanceTable.getInventoryVehicles()
                            .add(financialBalanceTable.getPcvFinished())
                            .subtract(financialIndicatorsHandfillTable.getReserveCarAmount()));
            log.info("当月库存商品存货额为：" + financialBalanceTable.getMonthAmountInStock());
        } else {
            financialBalanceTable.setMonthAmountInStock(null);
            log.info("当月当月库存商品存货额缺少数据，无法计算库存商品存货额");
        }
        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * @description: 计算当月存货总额
     * 月度存货金额=当月原材料存货额 +当月在制品存货额 + 当月库存商品存货额 + 当月低值易耗品金额
     * @author: hong
     * @date: 2024/10/22 16:15
     * @version: 2.0
     */
    public void countMonthlyInventoryTotalAmount(Date yearAndMonth) {
        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);

        if (financialBalanceTable != null && financialBalanceTable.getMonthlyRawMaterialInventory() != null
                && financialBalanceTable.getMonthlyWorkInProgressInventory() != null && financialBalanceTable.getMonthAmountInStock() != null ) {

            financialBalanceTable.setMonthlyInventoryTotalAmount(
                    financialBalanceTable.getMonthlyRawMaterialInventory()
                            .add(financialBalanceTable.getMonthlyWorkInProgressInventory())
                            .add(financialBalanceTable.getMonthAmountInStock())
                            .add(BigDecimal.valueOf(financialBalanceTable.getLowValueConsumablesAmount())));
            log.info("当月存货总额为：" + financialBalanceTable.getMonthlyInventoryTotalAmount());

        } else {
            financialBalanceTable.setMonthlyInventoryTotalAmount(null);
            log.info("当月当月存货总额缺少数据，无法计算存货总额");
        }
        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * @description: 计算当月存货总额
     * 月度存货金额=当月原材料存货额 +当月在制品存货额 + 当月库存商品存货额
     * @author: hong
     * @date: 2024/9/27 16:51
     * @version: 1.0
     */
//    public void countMonthlyInventoryTotalAmount(Date yearAndMonth) {
//        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
//
//        if (financialBalanceTable != null && financialBalanceTable.getMonthlyRawMaterialInventory() != null
//                && financialBalanceTable.getMonthlyWorkInProgressInventory() != null && financialBalanceTable.getMonthAmountInStock() != null ) {
//
//            financialBalanceTable.setMonthlyInventoryTotalAmount(
//                    financialBalanceTable.getMonthlyRawMaterialInventory()
//                            .add(financialBalanceTable.getMonthlyWorkInProgressInventory())
//                            .add(financialBalanceTable.getMonthAmountInStock()));
//            log.info("当月存货总额为：" + financialBalanceTable.getMonthlyInventoryTotalAmount());
//
//        } else {
//            financialBalanceTable.setMonthlyInventoryTotalAmount(null);
//             log.info("当月当月存货总额缺少数据，无法计算存货总额");
//        }
//        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
//    }

    /**
     * @description: 计算存货增长率/销售增长率
     * (资产负债表)(b29(本月) / b29(上月) - 1)
     * (利润表)(b2(本月) / (b2(上月) - 1))
     * b29: monthlyInventoryTotalAmount 月度存货总金额
     * b2: operatingRevenue 营业收入
     * @author: hong
     * @date: 2024/9/27 17:44
     * @version: 1.0
     */
    public void countGrowthRateInventory(Date yearAndMonth) {

        FinancialBalanceTable balanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);

        if (balanceTable == null) {
            log.info("当月数据不存在，无法计算存货增长率/销售增长率");
            return;
        }

        Date lastMonth = DateUtils.getLastMonth(balanceTable.getYearAndMonth());

        // 获取 本月和上月 月度存货总金额
        BigDecimal monthlyInventoryTotalAmount = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(balanceTable.getYearAndMonth());
        BigDecimal monthlyInventoryTotalAmountLastMonth = financialBalanceTableService.selectMonthlyInventoryTotalAmountByYearAndMonth(lastMonth);

        // 获取 本月和上月 营业收入
        BigDecimal operatingRevenue = financialInterestsTableService.selectOperatingRevenueByMonth(balanceTable.getYearAndMonth());
        BigDecimal operatingRevenueLastMonth = financialInterestsTableService.selectOperatingRevenueByMonth(lastMonth);

        System.out.println("本月月度存货总金额" + monthlyInventoryTotalAmount
                + "上月月度存货总金额" + monthlyInventoryTotalAmountLastMonth
                + "本月营业收入" + operatingRevenue
                + "上月营业收入" + operatingRevenueLastMonth
        );

        if (monthlyInventoryTotalAmount != null && operatingRevenue != null && monthlyInventoryTotalAmountLastMonth != null && operatingRevenueLastMonth != null){
            //新计算方式
            BigDecimal totalAmountSubtract = monthlyInventoryTotalAmount.subtract(monthlyInventoryTotalAmountLastMonth);
            BigDecimal operatingRevenueSubtract = operatingRevenue.subtract(operatingRevenueLastMonth);
            log.info("存货增长额" + totalAmountSubtract + "--销售增长额" + operatingRevenueSubtract);

            //如果上面两个值一个为0，则直接返回0
            if (totalAmountSubtract.equals(BigDecimal.ZERO) || operatingRevenueSubtract.equals(BigDecimal.ZERO)) {
                balanceTable.setGrowthRateInventory(0.0);
            } else {
                balanceTable.setGrowthRateInventory(totalAmountSubtract.divide(operatingRevenueSubtract, 4, RoundingMode.HALF_UP).doubleValue() * 100);
                log.info("计算成功，当月存货增长率为：" + balanceTable.getGrowthRateInventory());
            }
        }else {
            balanceTable.setGrowthRateInventory(null);
            log.info("计算失败，当月数据缺少数据，无法计算存货增长率/销售增长率");
        }
        financialBalanceTableService.updateFinancialBalanceTable(balanceTable);
    }

    /**
     * @description: 计算应收帐款周转率
     * turnoverRateReceivable （利润表） operatingRevenue  (资产负债表) receivables
     * 第N月应收账款周转率=（SUM本年各月营业收入/N）/（（年初应收账款+SUM本年各月应收账款）/（N+1））
     * @author: hong
     * @date: 2024/9/6 14:02
     * @version: 1.0
     */
    public void countTurnoverRateReceivable(Date yearAndMonth) {
        FinancialBalanceTable balanceTable = financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
        if (balanceTable == null) {
            log.info("当月资产负债表数据不存在，无法计算");
            return;
        }
        log.info("资产负债表" + balanceTable);

        // 根据yearAndMonth ,获取去年十二月的date
        int lastYear = DateUtils.getYear(yearAndMonth) - 1;
        Date lastYearAndMonth = new Date(lastYear - 1900, 11, 1);

        log.info("lastYearAndMonth:" + DateUtils.parseDateToStr("yyyy-MM-dd", lastYearAndMonth));

        if (!financialBalanceTableService.checkReceivablesDataIsExisted(lastYearAndMonth)) {
            log.info("年初应收账款不存在，无法计算");        // 检查年初应收账款数据是否存在
            balanceTable.setTurnoverRateReceivable(null);
        } else if (financialInterestsTableService.countMonthDataNumber(yearAndMonth) != DateUtils.getMonth(yearAndMonth)) {
            log.info("截至当月营业收入数据不全,无法计算");// 统计当年营业收入数据条数，截止当月
            balanceTable.setTurnoverRateReceivable(null);
        } else if (financialBalanceTableService.countMonthDataNumber(yearAndMonth) != DateUtils.getMonth(yearAndMonth)) {
            log.info("截至当月应收账款数据不全,无法计算");// 统计当年资产负债表条数，截止当月
            balanceTable.setTurnoverRateReceivable(null);
        } else {
            // 数据齐全，开始计算
            log.info("数据齐全，开始计算");
            double receivable = financialBalanceTableService.selectReceivablesByDate(lastYearAndMonth);
            log.info("年初应收账款为：" + receivable);
            double operatingRevenue = financialInterestsTableService.countOperatingRevenueByYear(yearAndMonth);
            log.info("截至当月营业收入为：" + operatingRevenue);
            double receivables = financialBalanceTableService.countReceivablesByYear(yearAndMonth);
            log.info("截至当月应收账款为：" + receivables);

            // 第N月应收账款周转率=（SUM本年各月营业收入/N）/（（年初应收账款+SUM本年各月应收账款）/（N+1））
            double v = (operatingRevenue / DateUtils.getMonth(yearAndMonth)) / ((receivable + receivables) / (DateUtils.getMonth(yearAndMonth) + 1)) * 12;

            log.info("第N月应收账款周转率为：" + v);

            // 保留两位小数
//            v = new BigDecimal(v).setScale(2, RoundingMode.HALF_UP).doubleValue();

            balanceTable.setTurnoverRateReceivable(v);
        }
        financialBalanceTableService.updateBalanceTableTurnoverRateReceivable(balanceTable);


    }


}
