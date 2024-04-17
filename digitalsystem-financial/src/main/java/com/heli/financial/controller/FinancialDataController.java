package com.heli.financial.controller;

import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialDataService;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 数据填报聚合类
 * @author: hong
 * @date: 2024/4/8 14:18
 **/
@RestController
@RequestMapping("/financial")
public class FinancialDataController extends BaseController {

    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;
    @Autowired
    private IFinancialDataService finacialDataService;




    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/handFillData")
    @Transactional
    public AjaxResult handFillData(FinancialIndicatorsHandfillTable FITable) {
        if(financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(FITable.getYearAndMonth())){
            return AjaxResult.error("当月数据已填报,请检查");
        } else {
            return toAjax(financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable));
        }
    }

//    @Log(title = "[财务]利润表文件上传", businessType = BusinessType.INSERT)
//    @PostMapping("/handFillData")
//    @Transactional
//    public AjaxResult importIndicatorsTable(Date yearAndMonth,MultipartFile InterestsFile) {
//        if(financialInterestsTableService.checkInterestsDataIsExisted(yearAndMonth)){
//            return AjaxResult.error("当月利润表已上传,请检查");
//        } else {
//            return toAjax(financialInterestsTableService.importInterestsTable(yearAndMonth, InterestsFile));
//        }
//    }
//
//    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
//    @PostMapping("/handFillData")
//    @Transactional
//    public AjaxResult importBalanceTable(Date yearAndMonth,MultipartFile BalanceFile) {
//        if(financialBalanceTableService.checkBalanceDataIsExisted(yearAndMonth)){
//            return AjaxResult.error("当月资产负债表已上传,请检查");
//        } else {
//            return toAjax(financialBalanceTableService.importBalanceTable(yearAndMonth,BalanceFile));
//        }
//    }




    /**
     * @description: 测试-检查手动填报数据是否存在
     * @author: hong
     * @date: 2024/4/15 15:08
     **/
    @GetMapping("/test/isExist")
    public void checkData(Date date){
        boolean b = financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(date);

        System.out.println(financialInterestsTableService.checkInterestsDataIsExisted(date));
        System.out.println(financialBalanceTableService.checkBalanceDataIsExisted(date));

        System.out.println(b);

    }


    /**
     * @description: 按年查询利润表
     * @author: hong
     * @date: 2024/4/15 13:11
     * @param: [year]
     * @return: com.ruoyi.common.core.page.TableDataInfo
     **/
    @GetMapping("/interests/test/{year}")
    public TableDataInfo list(@PathVariable("year") String year) {

        System.out.println(year);
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableByYear(year);
        return getDataTable(list);
    }

    /**
     * @description: 按时间段查询利润表
     * @author: hong
     * @date: 2024/4/15 13:10
     * @param: [beginTime, endTime]
     * @return: com.ruoyi.common.core.page.TableDataInfo
     **/
    @GetMapping("/interests/test")
    public TableDataInfo list(Date beginTime,Date endTime) {

        System.out.println(beginTime);
        System.out.println(endTime);
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableByTime(beginTime,endTime);
        return getDataTable(list);
    }


    /**
     * @description: 数据填报和表格上传
     * @author: hong
     * @date: 2024/4/7 16:17
     * @param: [FinancialIndicatorsHandfillTable, InterestsFile, BalanceFile]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/uploadDataAndTable")
    @Transactional
    public AjaxResult uploadDataAndTable(FinancialIndicatorsHandfillTable FITable, MultipartFile InterestsFile, MultipartFile BalanceFile) throws IOException {
        financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable);
        financialInterestsTableService.importInterestsTable(FITable.getReporter(), FITable.getReportingDate(), FITable.getYearAndMonth(), InterestsFile);
        return toAjax(financialBalanceTableService.importBalanceTable(FITable.getReporter(), FITable.getReportingDate()
                , FITable.getYearAndMonth(), FITable.getReserveCarAmount(), BalanceFile));
    }

    /**
     * @description: 获取已上传数据的月份,防止重复上传
     * @author: hong
     * @date: 2024/4/8 14:55
     **/
    @GetMapping("/getExistedYearAndMonth")
    public TableDataInfo getAllYearAndMonth() {
        List<Date> list = financialIndicatorsHandfillTableService.selectExistedYearAndMonth();
        return getDataTable(list);
    }

    /**
     * @description: 获取单条财务的全部信息
     * @author: hong
     * @date: 2024/4/8 15:07
     * @param: 月份
     **/

    @GetMapping(value = "/test")
    public TableDataInfo getInfoByYearMonth() {
        long id = 100;
        FinancialBalanceTable financialBalanceTable = financialBalanceTableService.selectFinancialBalanceTableByFbId((long) 40);
        FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByFihfId((long) 375);
        FinancialInterestsTable financialInterestsTable = financialInterestsTableService.selectFinancialInterestsTableByFiId((long) 64);
        List<Object> list = new LinkedList<Object>();
        list.add(financialBalanceTable);
        list.add(financialIndicatorsHandfillTable);
        list.add(financialInterestsTable);

        System.out.println(list);
        System.out.println(getDataTable(list));
        return getDataTable(list);
    }


    /**
     * @description: 删除单条财务的全部信息
     * @author: hong
     * @date: 2024/4/8 15:07
     * @param: 月份
     **/
//    @DeleteMapping("/{yearAndMonth}")
//    public AjaxResult getInfoByYearMonth(@PathVariable() Date yearAndMonth) {
//        financialBalanceTableService.deleteFinancialBalanceTableByYearAndMonth(yearAndMonth);
//        financialInterestsTableService.deleteFinancialInterestsTableByYearAndMonth(yearAndMonth);
//        return financialIndicatorsHandfillTableService.deleteFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth);
//
//    }






//    /**
//     * 主营业务收入 指标1
//     */
//    @GetMapping("/mainRevenue/{year}")
//    public TableDataInfo mainRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectMainRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售收入 指标2
//     */
//    @GetMapping("/totalSalesRevenue/{year}")
//    public TableDataInfo totalSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectTotalSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 集团外部销售收入 指标3
//     */
//    @GetMapping("/externalGroupSalesRevenue/{year}")
//    public TableDataInfo externalGroupSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectExternalGroupSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车产量 指标4
//     */
//    @GetMapping("/totalVehicleProduction/{year}")
//    public TableDataInfo totalVehicleProduction(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectTotalVehicleProduction(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 整车销量 指标5
//     */
//    @GetMapping("/totalVehicleSales/{year}")
//    public TableDataInfo totalVehicleSales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectTotalVehicleSales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 新产品销售收入 指标6
//     */
//    @GetMapping("/newProductSalesRevenue/{year}")
//    public TableDataInfo newProductSalesRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectNewProductSalesRevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 特色产品收入 指标7
//     */
//    @GetMapping("/specialtyProductRevenue/{year}")
//    public TableDataInfo specialtyProductRevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectSpecialtyProductRevenue(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 主营业务成本 指标8
//     */
//    @GetMapping("/COGS/{year}")
//    public TableDataInfo COGS(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.COGS(year);
//        return getDataTable(list);
//    }
//
//
//    /**
//     * 整机销售成本 指标9
//     */
//    @GetMapping("/totalSalesCost/{year}")
//    public TableDataInfo totalSalesCost(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectTotalSalesCost(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 净利润 指标10
//     */
//    @GetMapping("/netProfit/{year}")
//    public TableDataInfo netProfit(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectNetProfit(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月管理费用 指标20
//     */
//    @GetMapping("/managementExpense/{year}")
//    public TableDataInfo managementExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectManagementExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月研发费用 指标21
//     */
//    @GetMapping("/rdExpense/{year}")
//    public TableDataInfo rdExpense(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectRdExpense(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月制造费用 指标22
//     */
//    @GetMapping("/manufacturingExpensesMonth/{year}")
//    public TableDataInfo manufacturingExpensesMonth(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectManufacturingExpensesMonth(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月原材料存货额 指标26
//     */
//    @GetMapping("/monthlyRawMaterialInventory/{year}")
//    public TableDataInfo monthlyRawMaterialInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectMonthlyRawMaterialInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月在制品存货额 指标27
//     */
//    @GetMapping("/monthlyWorkInProgressInventory/{year}")
//    public TableDataInfo monthlyWorkInProgressInventory(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectMonthlyWorkInProgressInventory(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月库存商品存货额 指标28
//     */
//    @GetMapping("/monthAmountInStock/{year}")
//    public TableDataInfo monthAmountInStock(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectMonthAmountInStock(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 存货增长率/销售增长率 指标30
//     */
//    @GetMapping("/growthRateInventorySales/{year}")
//    public TableDataInfo growthRateInventorySales(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectGrowthRateInventorySales(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 应收帐款周转率 指标32
//     */
//    @GetMapping("/turnoverRateReceivable/{year}")
//    public TableDataInfo turnoverRateReceivable(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectTurnoverRateReceivable(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 资金周转率 指标33
//     */
//    @GetMapping("/capitalTurnoverRate/{year}")
//    public TableDataInfo capitalTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectCapitalTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 库存商品周转率 指标34
//     */
//    @GetMapping("/inventoryTurnoverRate/{year}")
//    public TableDataInfo inventoryTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectInventoryTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 原材料周转率 指标35
//     */
//    @GetMapping("/rawMaterialTurnoverRate/{year}")
//    public TableDataInfo rawMaterialTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectRawMaterialTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 在制品周转率 指标36
//     */
//    @GetMapping("/inprogressTurnoverRate/{year}")
//    public TableDataInfo inprogressTurnoverRate(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectInprogressTurnoverRate(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 一年以上暂估行项目 指标66
//     */
//    @GetMapping("/longEstimatedItems/{year}")
//    public TableDataInfo longEstimatedItems(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectLongEstimatedItems(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当日在制品金额 指标70
//     */
//    @GetMapping("/inprogressDayrevenue/{year}")
//    public TableDataInfo inprogressDayrevenue(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectInprogressDayrevenue(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 月度存货总金额 指标77
//     */
//    @GetMapping("/monthlyInventoryTotalAmount/{year}")
//    public TableDataInfo monthlyInventoryTotalAmount(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectMonthlyInventoryTotalAmount(year);
//        return getDataTable(list);
//    }
//
//    /**
//     * 当月经济增加值 指标78
//     */
//    @GetMapping("/addedValueMonthly/{year}")
//    public TableDataInfo addedValueMonthly(@PathVariable("year") String year) {
//        List<DisplayEntity> list = finacialDataService.selectAddedValueMonthly(year);
//        return getDataTable(list);
//    }
//
//


}
