package com.ruoyi.product.controller;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import com.ruoyi.Enterprisemanagement.service.IEnterpriseManagementPersonnelRosterService;

import com.ruoyi.product.domain.ProductionClockInForm;

import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.service.IProductionClockInFormService;
import com.ruoyi.product.service.IProductionOvertimeApplicationFormService;

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
import com.ruoyi.product.domain.ProductionOvertimeStatisticsTable;
import com.ruoyi.product.service.IProductionOvertimeStatisticsTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 加班统计Controller
 *
 * @author ruoyi
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/product/OsTable")
public class ProductionOvertimeStatisticsTableController extends BaseController
{
    @Autowired
    private IProductionOvertimeStatisticsTableService productionOvertimeStatisticsTableService;
    @Autowired
    private IProductionClockInFormService productionClockInFormService;
    @Autowired
    private IProductionOvertimeApplicationFormService productionOvertimeApplicationFormService;
    @Autowired
    private IEnterpriseManagementPersonnelRosterService enterpriseManagementPersonnelRosterService;

    @PostMapping("/synchronization")
    public void Database_Synchronization(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable1) throws ParseException {
        System.out.println("-----------同步-------同步------------");
        List<ProductionClockInForm> list1 = productionClockInFormService.selectProductionClockInFormList1();
        List<ProductionOvertimeApplicationForm> list2 =productionOvertimeApplicationFormService.selectProductionOvertimeApplicationFormList1();
        List<EnterpriseManagementPersonnelRoster> list3 = enterpriseManagementPersonnelRosterService.selectEnterpriseManagementPersonnelRosterList1();
        productionOvertimeStatisticsTableService.Synchronization(list1, list2,list3,productionOvertimeStatisticsTable1);
        System.out.println("-----------同步-------同步------------");

    }

    /**
     * 查询加班统计列表
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        startPage();
        List<ProductionOvertimeStatisticsTable> list = productionOvertimeStatisticsTableService.selectProductionOvertimeStatisticsTableList(productionOvertimeStatisticsTable);
        return getDataTable(list);
    }

    /**
     * 导出加班统计列表
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:export')")
    @Log(title = "加班统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        List<ProductionOvertimeStatisticsTable> list = productionOvertimeStatisticsTableService.selectProductionOvertimeStatisticsTableList(productionOvertimeStatisticsTable);
        ExcelUtil<ProductionOvertimeStatisticsTable> util = new ExcelUtil<ProductionOvertimeStatisticsTable>(ProductionOvertimeStatisticsTable.class);
        util.exportExcel(response, list, "加班统计数据");
    }

    /**
     * 获取加班统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:query')")
    @GetMapping(value = "/{postId}")
    public AjaxResult getInfo(@PathVariable("postId") Long postId)
    {
        return success(productionOvertimeStatisticsTableService.selectProductionOvertimeStatisticsTableByPostId(postId));
    }

    /**
     * 新增加班统计
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:add')")
    @Log(title = "加班统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        return toAjax(productionOvertimeStatisticsTableService.insertProductionOvertimeStatisticsTable(productionOvertimeStatisticsTable));
    }

    /**
     * 修改加班统计
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:edit')")
    @Log(title = "加班统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable)
    {
        return toAjax(productionOvertimeStatisticsTableService.updateProductionOvertimeStatisticsTable(productionOvertimeStatisticsTable));
    }

    /**
     * 删除加班统计
     */
    @PreAuthorize("@ss.hasPermi('product:OsTable:remove')")
    @Log(title = "加班统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return toAjax(productionOvertimeStatisticsTableService.deleteProductionOvertimeStatisticsTableByPostIds(postIds));
    }
}
