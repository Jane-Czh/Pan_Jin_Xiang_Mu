package com.heli.financial.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.heli.financial.domain.FinancialInterestsTable;
import com.heli.financial.service.IFinancialInterestsTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-利润Controller
 *
 * @author hong
 * @date 2024-03-31
 */
@RestController
@RequestMapping("/financial/interests")
public class FinancialInterestsTableController extends BaseController {
    @Autowired
    private IFinancialInterestsTableService financialInterestsTableService;


    /**
     * @description: 利润表excel导入接口
     * @author: hong
     * @date: 2024/3/31 13:42
     * @return: success
     */
//    @Log(title = "[财务]利润表表上传", businessType = BusinessType.INSERT)
    @PostMapping("/importTable")
    public AjaxResult importTable(MultipartFile excelFile) {
        System.out.println(excelFile);
        try {
            financialInterestsTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }


    /**
     * 查询财务-利润列表
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinancialInterestsTable financialInterestsTable) {
        startPage();
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableList(financialInterestsTable);
        return getDataTable(list);
    }

    /**
     * 导出财务-利润列表
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:export')")
    @Log(title = "财务-利润", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinancialInterestsTable financialInterestsTable) {
        List<FinancialInterestsTable> list = financialInterestsTableService.selectFinancialInterestsTableList(financialInterestsTable);
        ExcelUtil<FinancialInterestsTable> util = new ExcelUtil<FinancialInterestsTable>(FinancialInterestsTable.class);
        util.exportExcel(response, list, "财务-利润数据");
    }

    /**
     * 获取财务-利润详细信息
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:query')")
    @GetMapping(value = "/{fiId}")
    public AjaxResult getInfo(@PathVariable("fiId") Long fiId) {
        return success(financialInterestsTableService.selectFinancialInterestsTableByFiId(fiId));
    }

    /**
     * 新增财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:add')")
    @Log(title = "财务-利润", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinancialInterestsTable financialInterestsTable) {
        return toAjax(financialInterestsTableService.insertFinancialInterestsTable(financialInterestsTable));
    }

    /**
     * 修改财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:edit')")
    @Log(title = "财务-利润", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinancialInterestsTable financialInterestsTable) {
        return toAjax(financialInterestsTableService.updateFinancialInterestsTable(financialInterestsTable));
    }

    /**
     * 删除财务-利润
     */
    @PreAuthorize("@ss.hasPermi('financial:interests:remove')")
    @Log(title = "财务-利润", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fiIds}")
    public AjaxResult remove(@PathVariable Long[] fiIds) {
        return toAjax(financialInterestsTableService.deleteFinancialInterestsTableByFiIds(fiIds));
    }
}

