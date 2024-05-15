package com.heli.financial.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import com.heli.financial.mapper.FinancialIndicatorsHandfillTableMapper;
import com.heli.financial.mapper.FinancialInterestsTableMapper;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.stereotype.Service;
import com.heli.financial.mapper.FinancialBalanceTableMapper;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-资产负债Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-03
 */
@Service
public class FinancialBalanceTableServiceImpl implements IFinancialBalanceTableService {
    @Autowired
    private FinancialBalanceTableMapper financialBalanceTableMapper;
    @Autowired
    private FinancialInterestsTableMapper financialInterestsTableMapper;
    @Autowired
    private IFinancialInterestsTableService iFinancialInterestsTableService;

    @Override
    public List<Date> selectAllBalanceYearAndMonth() {
        return financialBalanceTableMapper.selectAllBalanceYearAndMonth();
    }

    @Override
    public boolean checkBalanceDataIsExisted(Date date) {
        return financialBalanceTableMapper.checkBalanceDataIsExisted(date);
    }

    /**
     * @description: 资产负债表导入
     * @author: hong
     * @date: 2024/4/3 11:00
     * @param: [excelFile]
     * @return: int
     **/
    @Override
    public int importBalanceTable(String createdBy, Date createdTime, Date yearAndMonth, MultipartFile excelFile) throws IOException {
        FinancialBalanceTable financialBalanceTable;
        InputStream is = null;
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            financialBalanceTable = (FinancialBalanceTable) ReadExcelCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialBalanceTable", is, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }

        /**
         * @description: 导入时间和导入者
         **/
        financialBalanceTable.setCreateBy(createdBy);
        financialBalanceTable.setCreateTime(createdTime);
        financialBalanceTable.setYearAndMonth(yearAndMonth);

        /**
         * @description: 计算当月原材料存货额
         **/
        financialBalanceTable.setMonthlyRawMaterialInventory(
                financialBalanceTable.getInTransitInventory()
                        .add(financialBalanceTable.getMaterials()
                                .add(financialBalanceTable.getMaterialCostVariance()
                                        .add(financialBalanceTable.getMaterialCostVarianceUnallocated())))
        );
        /**
         * @description: 计算当月在制品存货额
         **/
        financialBalanceTable.setMonthlyWorkInProgressInventory(
                financialBalanceTable.getWorkInProgressSemiFinishedGoods()
                        .add(financialBalanceTable.getProductCostVarianceSemiFinishedGoods())
                        .add(financialBalanceTable.getWorkInProgressEndOfMonth())
        );
        /**
         * @description: 计算当月库存商品存货额
         * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
         * monthAmountInStock =（资产负债表） inventoryVehicles + pcvFinished - reserveCarAmount
         **/
//        financialBalanceTable.setMonthAmountInStock(
//                financialBalanceTable.getInventoryVehicles()
//                        .add(financialBalanceTable.getPcvFinished()
//                                .subtract(reserveCarAmount))
//        );


        /**
         * @description: 计算应收帐款周转率
         * 应收帐款周转率 = （利润表）营业收入 / (资产负债表) 应收账款
         * turnoverRateReceivable =（利润表） operatingRevenue / (资产负债表) receivables
         * 由于计算比率，所以保留2位小数，并四舍五入
         **/
//        financialBalanceTable.setTurnoverRateReceivable(
//                (financialInterestsTableMapper.selectOperatingRevenueByMonth(yearAndMonth)
//                        .divide(financialBalanceTable.getReceivables(), 2, RoundingMode.HALF_UP).doubleValue())
//        );
        return financialBalanceTableMapper.insertFinancialBalanceTable(financialBalanceTable);
    }


//    @Override
//    public int importBalanceTable(String createdBy, Date createdTime, Date yearAndMonth, BigDecimal reserveCarAmount, MultipartFile excelFile) throws IOException {
//        FinancialBalanceTable financialBalanceTable;
//        InputStream is = null;
//        try {
//            System.out.println(excelFile);
//            is = excelFile.getInputStream();
//            financialBalanceTable = (FinancialBalanceTable) ReadExcelCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialBalanceTable", is, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new ServiceException("excel解析失败");
//        } finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
//        /**
//         * @description: 导入时间和导入者
//         **/
//        financialBalanceTable.setCreateBy(createdBy);
//        financialBalanceTable.setCreateTime(createdTime);
//        financialBalanceTable.setYearAndMonth(yearAndMonth);
//
//        /**
//         * @description: 计算当月原材料存货额
//         **/
//        financialBalanceTable.setMonthlyRawMaterialInventory(
//                financialBalanceTable.getInTransitInventory()
//                        .add(financialBalanceTable.getMaterials()
//                                .add(financialBalanceTable.getMaterialCostVariance()
//                                        .add(financialBalanceTable.getMaterialCostVarianceUnallocated())))
//        );
//        /**
//         * @description: 计算当月在制品存货额
//         **/
//        financialBalanceTable.setMonthlyWorkInProgressInventory(
//                financialBalanceTable.getWorkInProgressSemiFinishedGoods()
//                        .add(financialBalanceTable.getProductCostVarianceSemiFinishedGoods())
//                        .add(financialBalanceTable.getWorkInProgressEndOfMonth())
//        );
//        /**
//         * @description: 计算当月库存商品存货额
//         * 当月库存商品存货额 = 库存商品-整车 + 产品成本差异-产成品 - 储备车金额（填报)
//         * monthAmountInStock =（资产负债表） inventoryVehicles + pcvFinished - reserveCarAmount
//         **/
//        financialBalanceTable.setMonthAmountInStock(
//                financialBalanceTable.getInventoryVehicles()
//                        .add(financialBalanceTable.getPcvFinished()
//                                .subtract(reserveCarAmount))
//        );
//
//
//        /**
//         * @description: 计算应收帐款周转率
//         * 应收帐款周转率 = （利润表）营业收入 / (资产负债表) 应收账款
//         * turnoverRateReceivable =（利润表） operatingRevenue / (资产负债表) receivables
//         * 由于计算比率，所以保留2位小数，并四舍五入
//         **/
//        financialBalanceTable.setTurnoverRateReceivable(
//                (financialInterestsTableMapper.selectOperatingRevenueByMonth(yearAndMonth)
//                        .divide(financialBalanceTable.getReceivables(), 2, RoundingMode.HALF_UP).doubleValue())
//        );
//
//
//        return financialBalanceTableMapper.insertFinancialBalanceTable(countGrowthRateInventorySales(financialBalanceTable));
//    }


    @Override
    public int deleteFinancialBalanceTableByYearAndMonth(Date yearAndMonth) {
        return 0;
    }

    /**
     * @description: 按时间查询资产负债表
     * @author: hong
     * @date: 2024/4/23 11:05
     **/
    @Override
    public FinancialBalanceTable selectFinancialBalanceTableByYearAndMonth(Date yearAndMonth) {
        return financialBalanceTableMapper.selectFinancialBalanceTableByYearAndMonth(yearAndMonth);
    }


    /**
     * @description: 按月查询-月度存货总金额
     * @author: hong
     * @date: 2024/4/23 11:24
     **/
    @Override
    public BigDecimal selectMonthlyInventoryTotalAmountByYearAndMonth(Date yearAndMonth) {
        return financialBalanceTableMapper.selectMonthlyInventoryTotalAmountByYearAndMonth(yearAndMonth);
    }


    @Override
    public int importBalance(MultipartFile excelFile) throws IOException {
        FinancialBalanceTable financialBalanceTable;
        InputStream is = null;
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            financialBalanceTable = (FinancialBalanceTable) ReadExcelCellUtils.parseExcelToModel("com.heli.financial.domain.FinancialBalanceTable", is, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return financialBalanceTableMapper.insertFinancialBalanceTable(financialBalanceTable);
    }


    /**
     * @description: 计算当月库存商品存货额，b35+b37-储备车金额（填报）
     * @author: hong
     * @date: 2024/4/3 15:25
     **/
    public FinancialBalanceTable countMonthAmountInStock(FinancialBalanceTable financialBalanceTable) {
        BigDecimal reserveCarAmount;


        return financialBalanceTable;
    }








    /**
     * @description: 计算存货增长率/销售增长率 growthRateInventorySales
     * (资产负债表)(b29(本月) / b29(上月) - 1)  /  (利润表)(b2(本月) / (b2(上月) - 1))
     * b29: monthlyInventoryTotalAmount 月度存货总金额
     * b2: operatingRevenue 营业收入
     * @author: hong
     * @date: 2024/4/3 15:32
     **/
    public FinancialBalanceTable countGrowthRateInventorySales(FinancialBalanceTable financialBalanceTable) {

        //将date转化为localDateTime
        Date date = financialBalanceTable.getYearAndMonth();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        //利用localDateTime工具得到当前月份的上一个月，并且重新转化为Date类型
        Date lastMonth = Date.from(localDateTime.minusMonths(1).atZone(ZoneId.systemDefault()).toInstant());

        //获取上月 月度存货总金额
        BigDecimal monthlyInventoryTotalAmount = financialBalanceTable.getMonthlyInventoryTotalAmount();
        BigDecimal monthlyInventoryTotalAmountLastMonth = financialBalanceTableMapper.selectMonthlyInventoryTotalAmountByYearAndMonth(lastMonth);

        //获取 本月和上月 营业收入
        BigDecimal operatingRevenue = iFinancialInterestsTableService.selectOperatingRevenueByMonth(date);
        BigDecimal operatingRevenueLastMonth = iFinancialInterestsTableService.selectOperatingRevenueByMonth(lastMonth);

        System.out.println("本月月度存货总金额" + financialBalanceTable.getMonthlyInventoryTotalAmount()
                + "上月月度存货总金额" + monthlyInventoryTotalAmountLastMonth
                + "本月营业收入" + operatingRevenue
                + "上月营业收入" +operatingRevenueLastMonth
        );

        //分别计算比率
        BigDecimal monthlyInventoryTotalAmountRate = monthlyInventoryTotalAmount.divide(monthlyInventoryTotalAmountLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));
        BigDecimal operatingRevenueRate = operatingRevenue.divide(operatingRevenueLastMonth, 2, RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(1));

        System.out.println("monthlyInventoryTotalAmountRate" + monthlyInventoryTotalAmountRate);
        System.out.println("operatingRevenueRate" + operatingRevenueRate);

        financialBalanceTable.setGrowthRateInventory(monthlyInventoryTotalAmountRate.doubleValue());
        financialBalanceTable.setGrowthRateSales(operatingRevenueRate.doubleValue());

        return financialBalanceTable;
    }







    /**
     * @description: 计算应收帐款周转率
     * @author: hong
     * @date: 2024/4/3 15:33
     **/
    public FinancialBalanceTable countTurnoverRateReceivable(FinancialBalanceTable financialBalanceTable) {

        return financialBalanceTable;
    }


    /**
     * 查询财务-资产负债
     *
     * @param fbId 财务-资产负债主键
     * @return 财务-资产负债
     */
    @Override
    public FinancialBalanceTable selectFinancialBalanceTableByFbId(Long fbId) {
        return financialBalanceTableMapper.selectFinancialBalanceTableByFbId(fbId);
    }

    /**
     * 查询财务-资产负债列表
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 财务-资产负债
     */
    @Override
    public List<FinancialBalanceTable> selectFinancialBalanceTableList(FinancialBalanceTable financialBalanceTable) {
        return financialBalanceTableMapper.selectFinancialBalanceTableList(financialBalanceTable);
    }

    /**
     * 新增财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    @Override
    public int insertFinancialBalanceTable(FinancialBalanceTable financialBalanceTable) {
        return financialBalanceTableMapper.insertFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * 修改财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    @Override
    public int updateFinancialBalanceTable(FinancialBalanceTable financialBalanceTable) {
        return financialBalanceTableMapper.updateFinancialBalanceTable(financialBalanceTable);
    }

    /**
     * 批量删除财务-资产负债
     *
     * @param fbIds 需要删除的财务-资产负债主键
     * @return 结果
     */
    @Override
    public int deleteFinancialBalanceTableByFbIds(Long[] fbIds) {
        return financialBalanceTableMapper.deleteFinancialBalanceTableByFbIds(fbIds);
    }

    /**
     * 删除财务-资产负债信息
     *
     * @param fbId 财务-资产负债主键
     * @return 结果
     */
    @Override
    public int deleteFinancialBalanceTableByFbId(Long fbId) {
        return financialBalanceTableMapper.deleteFinancialBalanceTableByFbId(fbId);
    }
}
