package com.heli.financial.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
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
import com.heli.financial.domain.FinancialTempTable;
import com.heli.financial.service.IFinancialTempTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 中转表Controller
 *
 * @author hong
 * @date 2024-07-31
 */
@Slf4j
@RestController
@RequestMapping("/system/TempTable")
public class FinancialTempTableController extends BaseController {
    @Autowired
    private IFinancialTempTableService financialTempTableService;


    /**
     * @return
     * @description: 上传工资表，存入数据库,计算相关数据
     * @author: hong
     * @date: 2024/4/11 16:38
     */
//    @PreAuthorize("@ss.hasPermi('enterprise:salary:import')")
    @PostMapping("/balance")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            //清空数据库
            log.info("清空数据库");
            financialTempTableService.clearTempTable();

            log.info("开始读取 " + multipartFile.getName() + " 文件");
            financialTempTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream);

            //资产负债表信息转换
            financialTempTableService.tempTableToBalanceTable(yearAndMonth);
            log.info("清空数据库");
//            financialTempTableService.clearTempTable();

            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }

    @PostMapping("/interests")
    public R<String> simpleRead1(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            //清空数据库
            log.info("清空数据库");
            financialTempTableService.clearTempTable();

            log.info("开始读取 " + multipartFile.getName() + " 文件");
            financialTempTableService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream);

            //资产负债表信息转换
            financialTempTableService.tempTableToInterestsTable(yearAndMonth);
            log.info("清空数据库");
//            financialTempTableService.clearTempTable();

            return R.ok("上传成功");
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


    /**
     * 查询中转表列表
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialTempTable financialTempTable) {
        startPage();
        List<FinancialTempTable> list = financialTempTableService.selectFinancialTempTableList(financialTempTable);
        return getDataTable(list);
    }

    /**
     * 导出中转表列表
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:export')")
    @Log(title = "中转表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialTempTable financialTempTable) {
        List<FinancialTempTable> list = financialTempTableService.selectFinancialTempTableList(financialTempTable);
        ExcelUtil<FinancialTempTable> util = new ExcelUtil<FinancialTempTable>(FinancialTempTable.class);
        util.exportExcel(response, list, "中转表数据");
    }

    /**
     * 获取中转表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:query')")
    @GetMapping(value = "/{ftId}")
    public AjaxResult getInfo(@PathVariable("ftId") Long ftId) {
        return success(financialTempTableService.selectFinancialTempTableByFtId(ftId));
    }

    /**
     * 新增中转表
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:add')")
    @Log(title = "中转表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialTempTable financialTempTable) {
        return toAjax(financialTempTableService.insertFinancialTempTable(financialTempTable));
    }

    /**
     * 修改中转表
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:edit')")
    @Log(title = "中转表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialTempTable financialTempTable) {
        return toAjax(financialTempTableService.updateFinancialTempTable(financialTempTable));
    }

    /**
     * 删除中转表
     */
    @PreAuthorize("@ss.hasPermi('system:TempTable:remove')")
    @Log(title = "中转表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ftIds}")
    public AjaxResult remove(@PathVariable Long[] ftIds) {
        return toAjax(financialTempTableService.deleteFinancialTempTableByFtIds(ftIds));
    }
}
