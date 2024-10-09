package com.heli.financial.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.listener.TempTableListener;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.core.domain.R;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.financial.mapper.FinancialTempTableMapper;
import com.heli.financial.domain.FinancialTempTable;
import com.heli.financial.service.IFinancialTempTableService;

/**
 * 中转表Service业务层处理
 *
 * @author hong
 * @date 2024-07-31
 */
@Slf4j
@Service
public class FinancialTempTableServiceImpl implements IFinancialTempTableService {
    @Autowired
    private FinancialTempTableMapper financialTempTableMapper;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;

    @Override
    public void clearTempTable() {
        financialTempTableMapper.clearTempTable();
    }


    @Override
    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream) {
        try {
            log.info("开始读取文件: {}", fileName);
            // 读取文件前清空数据库
//            log.info("开始清空数据库");
//            enterpriseManagementSalaryTableMapper.clearSalaryTableAllInfo();
            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, FinancialTempTable.class, new TempTableListener(financialTempTableMapper)).sheet().doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,当前上传的文件为：" + fileName);
        }
    }

    @Override
    public void tempTableToBalanceTable(Date yearAndMonth) {
        FinancialBalanceTable balanceTable = new FinancialBalanceTable();
        balanceTable.setYearAndMonth(yearAndMonth);


        BigDecimal inTransitInventory = stringToBigDecimal(financialTempTableMapper.selectAmountByName("1402000000  在途物资"));
        balanceTable.setInTransitInventory(inTransitInventory);

        BigDecimal materials = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1403000000  原材料"));
        balanceTable.setMaterials(materials);

        BigDecimal materialCostVariance = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1404010000  材料成本差异"));
        balanceTable.setMaterialCostVariance(materialCostVariance);

        BigDecimal materialCostVarianceUnallocated = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1404030000  材料成本差异-差异待分摊"));
        balanceTable.setMaterialCostVarianceUnallocated(materialCostVarianceUnallocated);

        balanceTable.setMonthlyRawMaterialInventory(materials.add(inTransitInventory).add(materialCostVariance).add(materialCostVarianceUnallocated));

        BigDecimal workInProgressSemiFinishedGoods = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1405010000  库存商品-半成品"));
        balanceTable.setWorkInProgressSemiFinishedGoods(workInProgressSemiFinishedGoods);

        BigDecimal productCostVarianceSemiFinishedGoods = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1407010000  产品成本差异-半成品"));
        balanceTable.setProductCostVarianceSemiFinishedGoods(productCostVarianceSemiFinishedGoods);

        BigDecimal workInProgressEndOfMonth = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("5001090000  月末在制品"));
        balanceTable.setWorkInProgressEndOfMonth(workInProgressEndOfMonth);

        balanceTable.setMonthlyWorkInProgressInventory(workInProgressSemiFinishedGoods.add(productCostVarianceSemiFinishedGoods).add(workInProgressEndOfMonth));


        balanceTable.setInventoryVehicles(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1405020000  库存商品-整车")));

        balanceTable.setPcvFinished(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("1407020000  产品成本差异-产成品")));

//        balanceTable.setMonthlyInventoryTotalAmount(stringToBigDecimal(
//                financialTempTableMapper.selectAmountByName("*    存货")));

        balanceTable.setReceivables(stringToBigDecimal(
                        financialTempTableMapper.selectAmountByName("    1122010000  应收账款-控股公司"))
                .add(
                        stringToBigDecimal(
                                financialTempTableMapper.selectAmountByName("    1122030000  应收账款-直接客户"))
                ).add(
                        stringToBigDecimal(
                                financialTempTableMapper.selectAmountByName("    1231020100  坏账准备-应收账款-账龄计提"))
                )

        );


        financialBalanceTableService.insertFinancialBalanceTable(balanceTable);
        log.info("插入成功");
    }

    @Override
    public void tempTableToInterestsTable(Date yearAndMonth) {

        FinancialInterestsTable interestsTable = new FinancialInterestsTable();

        interestsTable.setYearAndMonth(yearAndMonth);


        interestsTable.setOperatingRevenue(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("*    一、营业收入")));

        BigDecimal internalMainRevenue = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("6001010000  主营业务收入-集团内"));
        interestsTable.setInternalMainRevenue(internalMainRevenue);

        BigDecimal externalMainRevenue = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("6001020000  主营业务收入-集团外"));
        interestsTable.setExternalMainRevenue(externalMainRevenue);

        interestsTable.setMainRevenue(internalMainRevenue.add(externalMainRevenue));

        BigDecimal cogsProductSalesSd = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("6401010000  主营业务成本-产品销售SD"));
        interestsTable.setCogsProductSalesSd(cogsProductSalesSd);

        BigDecimal cogsFreight = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("6401040000  主营业务成本-运费"));
        interestsTable.setCogsFreight(cogsFreight);

        BigDecimal cogsVariation = stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("6401090000  主营业务成本-差异"));
        interestsTable.setCogsVariation(cogsVariation);

        interestsTable.setCOGS(cogsProductSalesSd.add(cogsFreight).add(cogsVariation));

        interestsTable.setNetProfit(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("**** 四、净利润")));


        interestsTable.setManagementExpense(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("*          管理费用")));

        interestsTable.setRdExpense(stringToBigDecimal(
                financialTempTableMapper.selectAmountByName("*          研发费用")));


        financialInterestsTableService.insertFinancialInterestsTable(interestsTable);
        log.info("插入成功");
    }

    //将传入的string转为BigDecimal
    public BigDecimal stringToBigDecimal(String str) {
        if (str == null || str.trim().length() == 0) {
            return BigDecimal.valueOf(0);
        }
        return new BigDecimal(str).divide(BigDecimal.valueOf(10000), 6, RoundingMode.HALF_UP);
    }


    /**
     * 查询中转表
     *
     * @param ftId 中转表主键
     * @return 中转表
     */
    @Override
    public FinancialTempTable selectFinancialTempTableByFtId(Long ftId) {
        return financialTempTableMapper.selectFinancialTempTableByFtId(ftId);
    }

    /**
     * 查询中转表列表
     *
     * @param financialTempTable 中转表
     * @return 中转表
     */
    @Override
    public List<FinancialTempTable> selectFinancialTempTableList(FinancialTempTable financialTempTable) {
        return financialTempTableMapper.selectFinancialTempTableList(financialTempTable);
    }

    /**
     * 新增中转表
     *
     * @param financialTempTable 中转表
     * @return 结果
     */
    @Override
    public int insertFinancialTempTable(FinancialTempTable financialTempTable) {
        return financialTempTableMapper.insertFinancialTempTable(financialTempTable);
    }

    /**
     * 修改中转表
     *
     * @param financialTempTable 中转表
     * @return 结果
     */
    @Override
    public int updateFinancialTempTable(FinancialTempTable financialTempTable) {
        return financialTempTableMapper.updateFinancialTempTable(financialTempTable);
    }

    /**
     * 批量删除中转表
     *
     * @param ftIds 需要删除的中转表主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTempTableByFtIds(Long[] ftIds) {
        return financialTempTableMapper.deleteFinancialTempTableByFtIds(ftIds);
    }

    /**
     * 删除中转表信息
     *
     * @param ftId 中转表主键
     * @return 结果
     */
    @Override
    public int deleteFinancialTempTableByFtId(Long ftId) {
        return financialTempTableMapper.deleteFinancialTempTableByFtId(ftId);
    }


}
