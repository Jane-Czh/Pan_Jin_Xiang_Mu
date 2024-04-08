package com.heli.financial.controller;

import com.heli.financial.domain.DisplayEntity;
import com.heli.financial.domain.FinancialBalanceTable;
import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class DataController extends BaseController {

    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialBalanceTableService financialBalanceTableService;
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;

    /**
     * @description: 数据填报和表格上传
     * @author: hong
     * @date: 2024/4/7 16:17
     * @param: [FinancialIndicatorsHandfillTable, InterestsFile, BalanceFile]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/uploadDataAndTable")
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





}
