package com.ruoyi.market.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
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
import com.ruoyi.market.domain.MarketAfterSalesRecurrenceRate;
import com.ruoyi.market.service.IMarketAfterSalesRecurrenceRateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售后台账问题复发率Controller
 * 
 * @author ruoyi
 * @date 2024-04-17
 */
@RestController
@RequestMapping("/market/recurrence")
public class MarketAfterSalesRecurrenceRateController extends BaseController
{
    @Autowired
    private IMarketAfterSalesRecurrenceRateService marketAfterSalesRecurrenceRateService;
    @Autowired
    private IMarketAfterSaleLedgerService marketAfterSaleLedgerService;


    /**
     * 同步售后台账数据库信息功能
     */
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate){
        System.out.println("同步-------同步");
        MarketAfterSaleLedger marketAfterSaleLedger = new MarketAfterSaleLedger();
        List<MarketAfterSaleLedger> list = marketAfterSaleLedgerService.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
        marketAfterSalesRecurrenceRateService.Synchronization(list, marketAfterSalesRecurrenceRate);
    }
    /**
     * 查询售后台账问题复发率列表
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        startPage();
        List<MarketAfterSalesRecurrenceRate> list = marketAfterSalesRecurrenceRateService.selectMarketAfterSalesRecurrenceRateList(marketAfterSalesRecurrenceRate);
        return getDataTable(list);
    }

    /**
     * 导出售后台账问题复发率列表
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:export')")
    @Log(title = "售后台账问题复发率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        List<MarketAfterSalesRecurrenceRate> list = marketAfterSalesRecurrenceRateService.selectMarketAfterSalesRecurrenceRateList(marketAfterSalesRecurrenceRate);
        ExcelUtil<MarketAfterSalesRecurrenceRate> util = new ExcelUtil<MarketAfterSalesRecurrenceRate>(MarketAfterSalesRecurrenceRate.class);
        util.exportExcel(response, list, "售后台账问题复发率数据");
    }

    /**
     * 获取售后台账问题复发率详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:query')")
    @GetMapping(value = "/{masrrId}")
    public AjaxResult getInfo(@PathVariable("masrrId") Long masrrId)
    {
        return success(marketAfterSalesRecurrenceRateService.selectMarketAfterSalesRecurrenceRateByMasrrId(masrrId));
    }

    /**
     * 新增售后台账问题复发率
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:add')")
    @Log(title = "售后台账问题复发率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        return toAjax(marketAfterSalesRecurrenceRateService.insertMarketAfterSalesRecurrenceRate(marketAfterSalesRecurrenceRate));
    }

    /**
     * 修改售后台账问题复发率
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:edit')")
    @Log(title = "售后台账问题复发率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketAfterSalesRecurrenceRate marketAfterSalesRecurrenceRate)
    {
        return toAjax(marketAfterSalesRecurrenceRateService.updateMarketAfterSalesRecurrenceRate(marketAfterSalesRecurrenceRate));
    }

    /**
     * 删除售后台账问题复发率
     */
    @PreAuthorize("@ss.hasPermi('market:recurrence:remove')")
    @Log(title = "售后台账问题复发率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{masrrIds}")
    public AjaxResult remove(@PathVariable Long[] masrrIds)
    {
        return toAjax(marketAfterSalesRecurrenceRateService.deleteMarketAfterSalesRecurrenceRateByMasrrIds(masrrIds));
    }
}
