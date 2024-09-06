package com.heli.financial.controller;

import com.heli.financial.domain.*;
import com.heli.financial.service.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
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
    @Autowired
    private IFinancialTempTableService financialTempTableService;
    private static final Logger log = LoggerFactory.getLogger(FinancialDataController.class);


    @PostMapping("/test")
    public AjaxResult test( Date yearAndMonth) {
        log.info(String.valueOf(yearAndMonth));
        financialDataService.countTurnoverRateReceivable(yearAndMonth);
        return AjaxResult.success();
    }

    @Log(title = "[财务]资产负债表数据批量计算", businessType = BusinessType.UPDATE)
    @GetMapping("/calculate")
    public AjaxResult batchCalculateBalanceIndicator() {
        return AjaxResult.success(financialDataService.batchCalculateBalanceIndicator());
    }


    @Log(title = "[财务]数据填报", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('financial:fill:add')")
    @PostMapping("/fill")
    public AjaxResult handFillData(@RequestBody FinancialIndicatorsHandfillTable FITable) {
//        System.out.println(FITable);
//        Date lastMonth = DateUtils.getLastMonth(FITable.getYearAndMonth());

//        boolean b = financialIndicatorsHandfillTableService.checkDataExists();
//        log.info(String.valueOf(b));


//        if (!financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(lastMonth)
//            && financialIndicatorsHandfillTableService.checkDataExists()) {
//            return AjaxResult.error("上月数据还未填报");
//        }
//        else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月资产负债表未上传");
//        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月利润表未上传");
//        }

        if (financialIndicatorsHandfillTableService.checkHandFillDataIsExisted(FITable.getYearAndMonth())) {
            return AjaxResult.error("当月数据已填报");
        }

        FITable.setCreateBy(getUsername());
        FITable.setCreateTime(DateUtils.getNowDate());

        int status = 0;
        status = financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable);
        if (status == 0) {
            return AjaxResult.error("数据填报失败");
        }

        // 检查当月 和 上月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(FITable.getYearAndMonth())
                && financialDataService.checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(FITable.getYearAndMonth()))) {
            // 开始计算
            status = financialDataService.calculateCurrentMonthFinancialData(FITable.getYearAndMonth());
        }

        return toAjax(status);

    }

//    @Log(title = "[财务]利润表上传", businessType = BusinessType.INSERT)
//    @PreAuthorize("@ss.hasPermi('financial:interests:import')")
//    @PostMapping("/interests/import")
//    public void importIndicatorsTable(@RequestParam("yearAndMonth") Date yearAndMonth,@RequestParam("InterestsFile") MultipartFile InterestsFile) {

//        log.info("12312312312------------------------------------");
//
//        log.info("yearAndMonth: " + yearAndMonth);
//        log.info("InterestsFile: " + InterestsFile.getName());
//    }

//    @Log(title = "[财务]利润表上传", businessType = BusinessType.INSERT)
//    @PreAuthorize("@ss.hasPermi('financial:interests:import')")
//    @PostMapping("/interests/import")
//    public void importIndicatorsTable( @RequestBody FinancialImportEntity financialImportEntity) {
//
//        log.info("yearAndMonth: " + financialImportEntity.getYearAndMonth());
//        log.info("InterestsFile: " + financialImportEntity.getInterestsFile());
//    }

//        Date lastMonth = DateUtils.getLastMonth(financialImportEntity.getYearAndMonth());
//        if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)
//                && financialInterestsTableService.checkDataExists()) {
//            return AjaxResult.error("上月利润表未上传");
//        }
////        else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
////            return AjaxResult.error("上月资产负债表未上传");
////        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
////            return AjaxResult.error("上月利润表未上传");
////        }
//
//        if (financialInterestsTableService.checkInterestsDataIsExisted(financialImportEntity.getYearAndMonth())) {
//            return AjaxResult.error("当月利润表已上传");
//        }
//
//        try {
//            toAjax(financialInterestsTableService.importInterestsTable(getUsername(), DateUtils.getNowDate(), financialImportEntity.getYearAndMonth(), InterestsFile));
//        } catch (Exception e) {
//            logger.info(String.valueOf(e));
//        }
//
//        // 检查当月 和 上月文件是否上传完成，全部上传后开始计算
//        if (financialDataService.checkDataUploadedForCurrentMonth(financialImportEntity.getYearAndMonth())
//                && financialDataService.checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(financialImportEntity.getYearAndMonth()))) {
//            // 开始计算
//            financialDataService.calculateCurrentMonthFinancialData(financialImportEntity.getYearAndMonth());
//        }
//
//        return AjaxResult.success();
//    }

    @Log(title = "[财务]利润表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('financial:interests:import')")
    @PostMapping("/interests/import")
    @Transactional
    public AjaxResult importIndicatorsTable(Date yearAndMonth, MultipartFile excelFile) {

//        log.info("yearAndMonth: " + yearAndMonth);
//        log.info("InterestsFile: " + excelFile);
//        log.info("InterestsFile: " + excelFile.getOriginalFilename());

//        将yearAndMonth时间转化为中国标准时间
//        Date test = DateUtils.parseDate(yearAndMonth);
//        log.info("中国时间" + test);

//        Date lastMonth = DateUtils.getLastMonth(yearAndMonth);

//        log.info("上月判断   "+ financialInterestsTableService.checkInterestsDataIsExisted(lastMonth));
//        log.info("全部数据判断   "+ financialInterestsTableService.checkDataExists());
//
//        log.info("上月判断   "+ !financialInterestsTableService.checkInterestsDataIsExisted(lastMonth));
//        log.info("全部数据判断   "+ financialInterestsTableService.checkDataExists());

//        if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth) && financialInterestsTableService.checkDataExists()) {
//            return AjaxResult.error("上月利润表未上传");
//        }
//        else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月资产负债表未上传");
//        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月利润表未上传");
//        }

        if (financialInterestsTableService.checkInterestsDataIsExisted(yearAndMonth)) {
            return AjaxResult.error("当月利润表已上传");
        }

        int status = 0;

//        try {
//            status = financialInterestsTableService.importInterestsTable(getUsername(), DateUtils.getNowDate(), yearAndMonth, excelFile);
//        } catch (Exception e) {
//            logger.info(String.valueOf(e));
//        }
//        if (status == 0) {
//            return AjaxResult.error("excel格式错误");
//        }

        try (InputStream inputStream = excelFile.getInputStream()) {
            //清空数据库
            log.info("清空数据库");
            financialTempTableService.clearTempTable();

            log.info("开始读取 " + excelFile.getName() + " 文件");
            financialTempTableService.readSalaryExcelToDB(excelFile.getOriginalFilename(), inputStream);

            //资产负债表信息转换
            financialTempTableService.tempTableToInterestsTable(yearAndMonth);

            status = 1;


        } catch (Exception e) {
            log.error("读取 " + excelFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + excelFile.getName() + " 文件失败");
        } finally {
            log.info("清空数据库");
            financialTempTableService.clearTempTable();

        }


        // 检查当月 和 上月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(yearAndMonth)
                && financialDataService.checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(yearAndMonth))) {
            // 开始计算
            status = financialDataService.calculateCurrentMonthFinancialData(yearAndMonth);
        }

        return toAjax(status);
    }

    @Log(title = "[财务]资产负债表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('financial:balance:import')")
    @PostMapping("/balance/import")
    @Transactional
    public AjaxResult importBalanceTable(Date yearAndMonth, MultipartFile BalanceFile) {

//        Date lastMonth = DateUtils.getLastMonth(yearAndMonth);
//        if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)
//                && financialBalanceTableService.checkDataExists()) {
//            return AjaxResult.error("上月资产负债表未上传");
//        }
//        else if (!financialBalanceTableService.checkBalanceDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月资产负债表未上传");
//        } else if (!financialInterestsTableService.checkInterestsDataIsExisted(lastMonth)) {
//            return AjaxResult.error("上月利润表未上传");
//        }

        if (financialBalanceTableService.checkBalanceDataIsExisted(yearAndMonth)) {
            return AjaxResult.error("当月资产负债表已上传");
        }

        int status = 0;
//        try {
//            status = financialBalanceTableService.importBalanceTable(getUsername(), DateUtils.getNowDate(), yearAndMonth, BalanceFile);
//        } catch (Exception e) {
//            logger.info(String.valueOf(e));
//        }
//        if (status == 0) {
//            return AjaxResult.error("excel格式错误");
//        }
        try (InputStream inputStream = BalanceFile.getInputStream()) {
            //清空数据库
            log.info("清空数据库");
            financialTempTableService.clearTempTable();

            log.info("开始读取 " + BalanceFile.getName() + " 文件");
            financialTempTableService.readSalaryExcelToDB(BalanceFile.getOriginalFilename(), inputStream);

            //资产负债表信息转换
            financialTempTableService.tempTableToBalanceTable(yearAndMonth);
            status = 1;
        } catch (Exception e) {
            log.error("读取 " + BalanceFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + BalanceFile.getName() + " 文件失败");
        } finally {
            log.info("清空数据库");
            financialTempTableService.clearTempTable();
        }


        // 检查当月 和 上月文件是否上传完成，全部上传后开始计算
        if (financialDataService.checkDataUploadedForCurrentMonth(yearAndMonth)
                && financialDataService.checkDataUploadedForCurrentMonth(DateUtils.getLastMonth(yearAndMonth))) {
            // 开始计算
            status = financialDataService.calculateCurrentMonthFinancialData(yearAndMonth);
        }


        return toAjax(status);
    }


    /**
     * 修改财务-资产负债
     */
    @PreAuthorize("@ss.hasPermi('financial:balance:edit')")
    @Log(title = "财务-资产负债", businessType = BusinessType.UPDATE)
    @PutMapping("/balance")
    public AjaxResult edit(@RequestBody FinancialBalanceTable financialBalanceTable) {
        financialBalanceTableService.updateFinancialBalanceTable(financialBalanceTable);
        return toAjax(financialDataService.batchUpdateFinancialData(financialBalanceTable.getYearAndMonth()));
    }

    /**
     * 修改[财务]手动填报指标
     */
    @PreAuthorize("@ss.hasPermi('financial:fill:edit')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.UPDATE)
    @PutMapping("/fill")
    public AjaxResult edit(@RequestBody FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        financialIndicatorsHandfillTableService.updateFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable);
        return toAjax(financialDataService.batchUpdateFinancialData(financialIndicatorsHandfillTable.getYearAndMonth()));
    }

    /**
     * 修改财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:edit')")
    @Log(title = "财务-利润", businessType = BusinessType.UPDATE)
    @PutMapping("/interests")
    public AjaxResult edit(@RequestBody FinancialInterestsTable financialInterestsTable) {
        financialInterestsTableService.updateFinancialInterestsTable(financialInterestsTable);
        return toAjax(financialDataService.batchUpdateFinancialData(financialInterestsTable.getYearAndMonth()));

    }


    /**
     * 新增[财务]每日填报指标[当日再制品金额]
     */
    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:add')")
    @Log(title = "[财务]每日填报指标[当日在制品金额]", businessType = BusinessType.INSERT)
    @PostMapping("/dailyInProgress")
    public AjaxResult add(@RequestBody FinancialDailyInProgressTable financialDailyInProgressTable) {
        if (financialDailyInProgressTableService.checkDailyInProgressTableIsExist(financialDailyInProgressTable.getDataTime())) {
            return AjaxResult.error("当日在制品金额已填报");
        }
        financialDailyInProgressTable.setCreateBy(getUsername());
        return toAjax(financialDailyInProgressTableService.insertFinancialDailyInProgressTable(financialDailyInProgressTable));
    }

    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:sum')")
    @PostMapping("/dailyInProgress/sum")
    public AjaxResult selectHandfillRateByYear(@RequestBody DisplayRequestParam time) {

        FinancialDailyInProgressTable newDailyInProgressTable =  financialDailyInProgressTableService.selectNewDateDailyInProgress();

        return AjaxResult.success(newDailyInProgressTable);
    }

    /**
     * 修改[财务]每日填报指标[当日再制品金额]
     */
    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:edit')")
    @Log(title = "[财务]每日填报指标[当日再制品金额]", businessType = BusinessType.UPDATE)
    @PutMapping("/dailyInProgress")
    public AjaxResult edit(@RequestBody FinancialDailyInProgressTable financialDailyInProgressTable) {
        financialDailyInProgressTable.setUpdateBy(getUsername());
        return toAjax(financialDailyInProgressTableService.updateFinancialDailyInProgressTable(financialDailyInProgressTable));
    }

    /**
     * 删除[财务]每日填报指标[当日再制品金额]
     */
    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:remove')")
    @Log(title = "[财务]每日填报指标[当日再制品金额]", businessType = BusinessType.DELETE)
    @DeleteMapping("/dailyInProgress/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(financialDailyInProgressTableService.deleteFinancialDailyInProgressTableByIds(ids));
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]列表
     */
    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:list')")
    @GetMapping("/dailyInProgress/list")
    public TableDataInfo list(FinancialDailyInProgressTable financialDailyInProgressTable) {
        startPage();
        List<FinancialDailyInProgressTable> list = financialDailyInProgressTableService.selectFinancialDailyInProgressTableList(financialDailyInProgressTable);
        return getDataTable(list);
    }

    /**
     * 查询[财务]每日填报指标[当日再制品金额]
     */
    @PreAuthorize("@ss.hasPermi('financial:dailyInProgress:list')")
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
//    @GetMapping("/interests/{year}")
//    public TableDataInfo list(@PathVariable("year") String year) {
//        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableByYear(year);
//        return getDataTable(list);
//    }

    /**
     * @description: 按时间段查询利润表
     * @author: hong
     * @date: 2024/4/15 13:10
     * @param: [beginTime, endTime]
     * @return: com.ruoyi.common.core.page.TableDataInfo
     **/
//    @GetMapping("/interests/test")
//    public TableDataInfo list(Date beginTime, Date endTime) {
//
//        System.out.println(beginTime);
//        System.out.println(endTime);
//        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableByTime(beginTime, endTime);
//        return getDataTable(list);
//    }


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
//    @GetMapping("/getAllHandFillYearAndMonth")
//    public TableDataInfo getAllHandFillYearAndMonth() {
//        List<Date> list = financialIndicatorsHandfillTableService.selectAllHandFillYearAndMonth();
//        return getDataTable(list);
//    }

//    @GetMapping("/getAllBalanceYearAndMonth")
//    public TableDataInfo getAllBalanceYearAndMonth() {
//        List<Date> list = financialBalanceTableService.selectAllBalanceYearAndMonth();
//        return getDataTable(list);
//    }
//
//    @GetMapping("/getAllInterestsYearAndMonth")
//    public TableDataInfo getAllInterestsYearAndMonth() {
//        List<Date> list = financialInterestsTableService.selectAllInterestsYearAndMonth();
//        return getDataTable(list);
//    }

    /**
     * @description: 获取单条财务的全部信息
     * @author: hong
     * @date: 2024/4/8 15:07
     * @param: yearAndMonth
     **/
//    @GetMapping(value = "/yearAndMonth={yearAndMonth}")
//    public TableDataInfo getInfoByYearMonth(@PathVariable("yearAndMonth") @DateTimeFormat(pattern = "yyyy-mm-dd") Date yearAndMonth) {
//
//        List<Object> list = new LinkedList<Object>();
//        list.add(financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByYearAndMonth(yearAndMonth));
//        list.add(financialInterestsTableService.selectFinancialInterestsTableByYearAndMonth(yearAndMonth));
//        list.add(financialBalanceTableService.selectFinancialBalanceTableByYearAndMonth(yearAndMonth));
//
//        return getDataTable(list);
//    }


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
