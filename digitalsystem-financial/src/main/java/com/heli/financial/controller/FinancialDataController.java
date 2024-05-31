package com.heli.financial.controller;

import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialDailyInProgressTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 数据填报聚合类
 * @author: hong
 * @date: 2024/4/8 14:18
 **/
@RestController
@RequestMapping("/financial/data")
public class FinancialDataController extends BaseController {

    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;
    @Autowired
    private IFinancialDailyInProgressTableService financialDailyInProgressTableService;
    @Autowired
    private IFinancialDataService financialDataService;


    @Log(title = "[财务]数据填报", businessType = BusinessType.INSERT)
    @PostMapping("/handFillData")
    public AjaxResult handFillData(FinancialIndicatorsHandfillTable FITable) {

        Date lastMonth = DateUtils.getLastMonth(FITable.getYearAndMonth());
        if (!financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月数据还未填报");
        } else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月资产负债表未上传");
        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月利润表未上传");
        }

        if (financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(FITable.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }

        FITable.setCreateBy(getUsername());
        FITable.setCreateTime(DateUtils.getNowDate());

        financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable);

        // 检查当月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(FITable.getYearAndMonth())) {
            // 开始计算
            financialDataService.calculateCurrentMonthFinancialData(FITable.getYearAndMonth());
        }

        return AjaxResult.success();

    }

    @Log(title = "[财务]利润表上传", businessType = BusinessType.INSERT)
    @PostMapping("/interests")
    public AjaxResult importIndicatorsTable(Date yearAndMonth, MultipartFile InterestsFile) {

        Date lastMonth = DateUtils.getLastMonth(yearAndMonth);
        if (!financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月数据还未填报");
        } else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月资产负债表未上传");
        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月利润表未上传");
        }

        if (financialInterestsTableService.checkInterestsDataIsExisted(yearAndMonth)) {
            return AjaxResult.error("当月利润表已上传,请检查");
        }

        try {
            toAjax(financialInterestsTableService.importInterestsTable(getUsername(), DateUtils.getNowDate(), yearAndMonth, InterestsFile));
        } catch (Exception e) {
            logger.info(String.valueOf(e));
        }

        // 检查当月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(yearAndMonth)) {
            // 开始计算
            financialDataService.calculateCurrentMonthFinancialData(yearAndMonth);
        }

        return AjaxResult.success();
    }

    @Log(title = "[财务]资产负债表上传", businessType = BusinessType.INSERT)
    @PostMapping("/balance")
    public AjaxResult importBalanceTable(Date yearAndMonth, MultipartFile BalanceFile) {

        Date lastMonth = DateUtils.getLastMonth(yearAndMonth);
        if (!financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月数据还未填报");
        } else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月资产负债表未上传");
        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
            return AjaxResult.error("上月利润表未上传");
        }

        if (financialBalanceTableService.checkBalanceDataIsExisted(yearAndMonth)) {
            return AjaxResult.error("当月资产负债表已上传");
        }

        try {
            financialBalanceTableService.importBalanceTable(getUsername(), DateUtils.getNowDate(), yearAndMonth, BalanceFile);
        } catch (Exception e) {
            logger.info(String.valueOf(e));
        }

//         检查当月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(yearAndMonth)) {
            // 开始计算
            financialDataService.calculateCurrentMonthFinancialData(yearAndMonth);
        }

        return AjaxResult.success();
    }

    /**
     * 新增[财务]每日填报指标[当日再制品金额]
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:add')")
    @Log(title = "[财务]每日填报指标[当日在制品金额]", businessType = BusinessType.INSERT)
    @PostMapping("/dailyInProgress")
    public AjaxResult add(@RequestBody FinancialDailyInProgressTable financialDailyInProgressTable) {
        if (financialDailyInProgressTableService.checkDailyInProgressTableIsExist(financialDailyInProgressTable.getDataTime())) {
            return AjaxResult.error("当日在制品金额已填报");
        }
        financialDailyInProgressTable.setCreateBy(getUsername());
        return toAjax(financialDailyInProgressTableService.insertFinancialDailyInProgressTable(financialDailyInProgressTable));
    }

    /**
     * 修改[财务]每日填报指标[当日再制品金额]
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:edit')")
    @Log(title = "[财务]每日填报指标[当日再制品金额]", businessType = BusinessType.UPDATE)
    @PutMapping("/dailyInProgress")
    public AjaxResult edit(@RequestBody FinancialDailyInProgressTable financialDailyInProgressTable) {
        financialDailyInProgressTable.setUpdateBy(getUsername());
        return toAjax(financialDailyInProgressTableService.updateFinancialDailyInProgressTable(financialDailyInProgressTable));
    }

    /**
     * 删除[财务]每日填报指标[当日再制品金额]
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:remove')")
    @Log(title = "[财务]每日填报指标[当日再制品金额]", businessType = BusinessType.DELETE)
    @DeleteMapping("/dailyInProgress/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(financialDailyInProgressTableService.deleteFinancialDailyInProgressTableByIds(ids));
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]列表
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:list')")
    @GetMapping("/dailyInProgress/list")
    public TableDataInfo list(FinancialDailyInProgressTable financialDailyInProgressTable) {
        startPage();
        List<FinancialDailyInProgressTable> list = financialDailyInProgressTableService.selectFinancialDailyInProgressTableList(financialDailyInProgressTable);
        return getDataTable(list);
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:list')")
    @GetMapping("/dailyInProgress/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(financialDailyInProgressTableService.selectFinancialDailyInProgressTableById(id));

    }



    /**
     * @description: 按年查询利润表
     * @author: hong
     * @date: 2024/4/15 13:11
     * @param: [year]
     * @return: com.ruoyi.common.core.page.TableDataInfo
     **/
    @GetMapping("/interests/{year}")
    public TableDataInfo list(@PathVariable("year") String year) {
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
    public TableDataInfo list(Date beginTime, Date endTime) {

        System.out.println(beginTime);
        System.out.println(endTime);
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableByTime(beginTime, endTime);
        return getDataTable(list);
    }


    /**
     * @description: 数据填报和表格同时上传
     * @author: hong
     * @date: 2024/4/7 16:17
     * @param: [FinancialIndicatorsHandfillTable, InterestsFile, BalanceFile]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
//    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
//    @PostMapping("/uploadDataAndTable")
//    @Transactional
//    public AjaxResult uploadDataAndTable(FinancialIndicatorsHandfillTable FITable, MultipartFile InterestsFile, MultipartFile BalanceFile) throws IOException {
//        financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable);
//        financialInterestsTableService.importInterestsTable(getUsername(), DateUtils.getNowDate(), FITable.getYearAndMonth(), InterestsFile);
//        return toAjax(financialBalanceTableService.importBalanceTable(getUsername(), DateUtils.getNowDate()
//                , FITable.getYearAndMonth(), FITable.getReserveCarAmount(), BalanceFile));
//    }


    /**
     * @description: 获取已上传数据的月份, 防止重复上传
     * @author: hong
     * @date: 2024/4/8 14:55
     **/
    @GetMapping("/getAllHandFillYearAndMonth")
    public TableDataInfo getAllHandFillYearAndMonth() {
        List<Date> list = financialIndicatorsHandfillTableService.selectAllHandFillYearAndMonth();
        return getDataTable(list);
    }

    @GetMapping("/getAllBalanceYearAndMonth")
    public TableDataInfo getAllBalanceYearAndMonth() {
        List<Date> list = financialBalanceTableService.selectAllBalanceYearAndMonth();
        return getDataTable(list);
    }

    @GetMapping("/getAllInterestsYearAndMonth")
    public TableDataInfo getAllInterestsYearAndMonth() {
        List<Date> list = financialInterestsTableService.selectAllInterestsYearAndMonth();
        return getDataTable(list);
    }

    /**
     * @description: 获取单条财务的全部信息
     * @author: hong
     * @date: 2024/4/8 15:07
     * @param: yearAndMonth
     **/
    @GetMapping(value = "/yearAndMonth={yearAndMonth}")
    public TableDataInfo getInfoByYearMonth(@PathVariable("yearAndMonth") @DateTimeFormat(pattern = "yyyy-mm-dd") Date yearAndMonth) {

        List<Object> list = new LinkedList<Object>();
        list.add(financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth));
        list.add(financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth));
        list.add(financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth));

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


}
