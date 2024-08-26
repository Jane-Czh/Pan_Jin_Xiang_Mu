package com.heli.financial.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.heli.financial.domain.FinancialIndicatorsHandfillTable;
import com.heli.financial.service.IFinancialBalanceTableService;
import com.heli.financial.service.IFinancialIndicatorsHandfillTableService;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@Slf4j
@RestController
@RequestMapping("/financial/data/fill")
public class FinancialIndicatorsHandfillTableController extends BaseController {
    @Autowired
    private IFinancialIndicatorsHandfillTableService financialIndicatorsHandfillTableService;
//    @Autowired
//    private IFinancialBalanceTableService financialBalanceTableService;
//    @Autowired
//    private IFinancialInterestsTableService financialInterestsTableService;

    /**
     * @description: 数据填报和表格上传
     * @author: hong
     * @date: 2024/4/7 16:17
     * @param: [FinancialIndicatorsHandfillTable, InterestsFile, BalanceFile]
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
//    @Log(title = "[财务]数据和文件上传", businessType = BusinessType.INSERT)
//    @PostMapping("/uploadDataAndTable")
//    public AjaxResult uploadDataAndTable(FinancialIndicatorsHandfillTable FITable, MultipartFile InterestsFile, MultipartFile BalanceFile) throws IOException {
//
//
//        financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(FITable);
//        financialInterestsTableService.importInterestsTable(FITable.getReporter(), FITable.getReportingDate(), FITable.getYearAndMonth(), InterestsFile);
//        return toAjax(financialBalanceTableService.importBalanceTable(FITable.getReporter(), FITable.getReportingDate()
//                , FITable.getYearAndMonth(), FITable.getReserveCarAmount(), BalanceFile));
//
//
//    }


//    @PostMapping("/upload")
//    @OperationLog(logType = "1",operationName = "hihi", content = "excel上传")
//    public String importExcel(@RequestParam("file") MultipartFile file, @RequestParam Map<String, Object> params) {

//        LogUtils.get().setLog("operationName", (String) params.get("userName"));

//        LogUtils.get().setLog("content", "上传航标参数Excel文件");//操作or修改的内容
//        System.out.println(file+"_______________--------------------");
//        if (file.isEmpty()) {
//            return "上传失败，请选择文件";
//        }
//        try {
//            List<DBExcelEntity> dbExcelEntities = ExcelUtils.parseExcel(file);
//
//            for (DBExcelEntity data : dbExcelEntities) {
//                System.out.println("No.data===" + data);
//                dbExcelService.insertData(data);
//            }
//
//            //第一次的文件导入后，自动生成编码填写到库中Message_HB_subTLVA00N
//            try {
//                Statement s = ConnectDBUtils.JdbcConnect().createStatement();
//                //只插入没有插入过的记录，将其转化为编码
//                String sql = "select HB_ID from Message_BuoyInformation where HB_ID not in (select HB_ID from Message_HB_subTLVA00N)";
//                ResultSet resultSet = s.executeQuery(sql);
//                while (resultSet.next()) {
//                    Long id = Long.valueOf(resultSet.getString("HB_ID"));
//                    SetArgs.setArgs(id);
//                }
//                //关闭数据库连接
//                s.close();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            LogUtils.get().setLog("module","上传成功");
//
//            return "导入成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "导入失败";
//        }

//        return "待返回";
//
//}

    /**
     * @description: 统计财务截至当年数据
     * @author: hong
     * @date: 2024/7/26 15:24
     */
    @PreAuthorize("@ss.hasPermi('financial:fill:sum')")
    @PostMapping("/sum")
    public AjaxResult selectHandfillSumInfoByYear(@RequestBody DisplayRequestParam time) {
        FinancialIndicatorsHandfillTable sumInfoByYear = financialIndicatorsHandfillTableService.selectHandfillSumInfoByYear(time.getStartTime());
//        logger.info("sumInfoByYear: " + sumInfoByYear);
        return AjaxResult.success(sumInfoByYear);
    }
    @PreAuthorize("@ss.hasPermi('financial:fill:sum')")
    @PostMapping("/rate")
    public AjaxResult selectHandfillRateByYear(@RequestBody DisplayRequestParam time) {
        FinancialIndicatorsHandfillTable sumInfoByYear = financialIndicatorsHandfillTableService.selectHandfillRateByYear(time.getStartTime());
//        logger.info("sumInfoByYear: " + sumInfoByYear);
        return AjaxResult.success(sumInfoByYear);
    }
    @PreAuthorize("@ss.hasPermi('financial:fill:sum')")
    @PostMapping("/newData")
    public AjaxResult selectMaxMonthHandfill() {
        FinancialIndicatorsHandfillTable sumInfoByYear = financialIndicatorsHandfillTableService.selectMaxMonthHandfill();
        return AjaxResult.success(sumInfoByYear);
    }


    /**
     * 查询[财务]手动填报指标列表
     */
    @PreAuthorize("@ss.hasPermi('financial:fill:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {

        log.info("list: " + financialIndicatorsHandfillTable);
        System.out.println("-------------test___________________");
        startPage();

        List<FinancialIndicatorsHandfillTable> list = financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableList(financialIndicatorsHandfillTable);
        return getDataTable(list);
    }

    /**
     * 获取[财务]手动填报指标详细信息
     */
    @PreAuthorize("@ss.hasPermi('financial:fill:query')")
    @GetMapping(value = "/{fihfId}")
    public AjaxResult getInfo(@PathVariable("fihfId") Long fihfId) {
        return success(financialIndicatorsHandfillTableService.selectFinancialIndicatorsHandfillTableByFihfId(fihfId));
    }

    /**
     * 新增[财务]手动填报指标
     */
//    @PreAuthorize("@ss.hasPermi('financial:data:add')")
//    @Log(title = "[财务]手动填报指标", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody FinancialIndicatorsHandfillTable financialIndicatorsHandfillTable) {
//
//        System.out.println(financialIndicatorsHandfillTable);
//        return toAjax(financialIndicatorsHandfillTableService.insertFinancialIndicatorsHandfillTable(financialIndicatorsHandfillTable));
//    }


    /**
     * 删除[财务]手动填报指标
     */
    @PreAuthorize("@ss.hasPermi('financial:fill:remove')")
    @Log(title = "[财务]手动填报指标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fihfIds}")
    public AjaxResult remove(@PathVariable Long[] fihfIds) {
        return toAjax(financialIndicatorsHandfillTableService.deleteFinancialIndicatorsHandfillTableByFihfIds(fihfIds));
    }
}
