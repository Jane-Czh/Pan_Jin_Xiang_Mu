package com.heli.financial.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * [财务]手动填报指标Controller
 *
 * @author loophong
 * @date 2024-03-29
 */
@RestController
@RequestMapping("/financial/data")
public class FinancialIndicatorsHandfillTableController extends BaseController {
    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
    @Autowired
    private IFinancialBalanceTableService iFinancialBalanceTableService;
    @Autowired
    private IFinancialInterestsTableService iFinancialInterestsTableService;

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
        iFinancialInterestsTableService.importInterestsTable(FITable.getReporter(), FITable.getReportingDate(), FITable.getYearAndMonth(), InterestsFile);
        iFinancialBalanceTableService.importBalanceTable(FITable.getReporter(), FITable.getReportingDate()
                , FITable.getYearAndMonth(), FITable.getReserveCarAmount(), BalanceFile);


        return null;
//        return toAjax(financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable));
    }


    /**
     * 查询[财务]手动填报指标列表
     */
    @PreAuthorize("@ss.hasPermi('financial:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        startPage();
        List<FinancialIndicatorsHandfillTable> list = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableList(financialIndicatorsHandfillTable);
        return getDataTable(list);
    }

    /**
     * 导出[财务]手动填报指标列表
     */
    @PreAuthorize("@ss.hasPermi('financial:data:export')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        List<FinancialIndicatorsHandfillTable> list = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableList(financialIndicatorsHandfillTable);
        ExcelUtil<FinancialIndicatorsHandfillTable> util = new ExcelUtil<FinancialIndicatorsHandfillTable>(FinancialIndicatorsHandfillTable.class);
        util.exportExcel(response, list, "[财务]手动填报指标数据");
    }

    /**
     * 获取[财务]手动填报指标详细信息
     */
    @PreAuthorize("@ss.hasPermi('financial:data:query')")
    @GetMapping(value = "/{fihfId}")
    public AjaxResult getInfo(@PathVariable("fihfId") Long fihfId) {
        return success(financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByFihfId(fihfId));
    }

    /**
     * 新增[财务]手动填报指标
     */
    @PreAuthorize("@ss.hasPermi('financial:data:add')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        return toAjax(financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable));
    }

    /**
     * 修改[财务]手动填报指标
     */
    @PreAuthorize("@ss.hasPermi('financial:data:edit')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
        return toAjax(financialIndicatorsHandfillTableService.updateFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable));
    }

    /**
     * 删除[财务]手动填报指标
     */
    @PreAuthorize("@ss.hasPermi('financial:data:remove')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fihfIds}")
    public AjaxResult remove(@PathVariable Long[] fihfIds) {
        return toAjax(financialIndicatorsHandfillTableService.deleteFinancialIndicatorsHandfillTableByFihfIds(fihfIds));
    }
}
