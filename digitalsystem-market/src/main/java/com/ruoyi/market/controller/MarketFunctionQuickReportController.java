package com.ruoyi.market.controller;

import java.time.LocalDateTime;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.JudgmentString;
import com.ruoyi.market.utils.SplitDate;
import com.ruoyi.market.utils.getTime;
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
import com.ruoyi.market.domain.MarketFunctionQuickReport;
import com.ruoyi.market.service.IMarketFunctionQuickReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.market.service.IMarketSalesTableService;
import com.ruoyi.market.domain.*;
/**
 * 市场科销售快报功能Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/market/report")
public class MarketFunctionQuickReportController extends BaseController
{
    @Autowired
    private IMarketFunctionQuickReportService marketFunctionQuickReportService;
    @Autowired
    private IMarketSalesTableService marketSalesTableService;

    /**
      * 同步销售台账数据库信息功能
      */
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketFunctionQuickReport marketFunctionQuickReport1){
        System.out.println("-----------同步-------同步------------");
        List<MarketSalesTable> list = marketSalesTableService.selectMarketSalesTableList1();
        marketFunctionQuickReportService.Synchronization(list, marketFunctionQuickReport1);
        System.out.println("-----------同步-------同步------------");
    }



    /**
     * 查询市场科销售快报功能列表
     */
    @PreAuthorize("@ss.hasPermi('market:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketFunctionQuickReport marketFunctionQuickReport)
    {
        startPage();
        List<MarketFunctionQuickReport> list = marketFunctionQuickReportService.selectMarketFunctionQuickReportList(marketFunctionQuickReport);
        System.out.println("-------------test--------test-------------");
        return getDataTable(list);
    }

    /**
     * 导出市场科销售快报功能列表
     */
    @PreAuthorize("@ss.hasPermi('market:report:export')")
    @Log(title = "市场科销售快报功能", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketFunctionQuickReport marketFunctionQuickReport)
    {
        List<MarketFunctionQuickReport> list = marketFunctionQuickReportService.selectMarketFunctionQuickReportList(marketFunctionQuickReport);
        ExcelUtil<MarketFunctionQuickReport> util = new ExcelUtil<MarketFunctionQuickReport>(MarketFunctionQuickReport.class);
        util.exportExcel(response, list, "市场科销售快报功能数据");
    }

    /**
     * 获取市场科销售快报功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:report:query')")
    @GetMapping(value = "/{mfqrId}")
    public AjaxResult getInfo(@PathVariable("mfqrId") Long mfqrId)
    {
        return success(marketFunctionQuickReportService.selectMarketFunctionQuickReportByMfqrId(mfqrId));
    }

    /**
     * 新增市场科销售快报功能
     */
    @PreAuthorize("@ss.hasPermi('market:report:add')")
    @Log(title = "市场科销售快报功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketFunctionQuickReport marketFunctionQuickReport)
    {
        return toAjax(marketFunctionQuickReportService.insertMarketFunctionQuickReport(marketFunctionQuickReport));
    }

    /**
     * 修改市场科销售快报功能
     */
    @PreAuthorize("@ss.hasPermi('market:report:edit')")
    @Log(title = "市场科销售快报功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketFunctionQuickReport marketFunctionQuickReport)
    {
        return toAjax(marketFunctionQuickReportService.updateMarketFunctionQuickReport(marketFunctionQuickReport));
    }

    /**
     * 删除市场科销售快报功能
     */
    @PreAuthorize("@ss.hasPermi('market:report:remove')")
    @Log(title = "市场科销售快报功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mfqrIds}")
    public AjaxResult remove(@PathVariable Long[] mfqrIds)
    {
        return toAjax(marketFunctionQuickReportService.deleteMarketFunctionQuickReportByMfqrIds(mfqrIds));
    }
}
