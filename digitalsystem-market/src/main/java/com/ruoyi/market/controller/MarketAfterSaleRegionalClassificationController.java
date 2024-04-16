package com.ruoyi.market.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketAfterSaleProblemClassification;
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
import com.ruoyi.market.domain.MarketAfterSaleRegionalClassification;
import com.ruoyi.market.service.IMarketAfterSaleRegionalClassificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售后区域分类Controller
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/market/regional")
public class MarketAfterSaleRegionalClassificationController extends BaseController
{
    @Autowired
    private IMarketAfterSaleRegionalClassificationService marketAfterSaleRegionalClassificationService;
    @Autowired
    private IMarketAfterSaleLedgerService marketAfterSaleLedgerService;

    /**
     * 同步售后台账数据库信息功能
     */
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification){
        System.out.println("------同步------");
        MarketAfterSaleLedger marketAfterSaleLedger = new MarketAfterSaleLedger();
        List<MarketAfterSaleLedger> list = marketAfterSaleLedgerService.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
        marketAfterSaleRegionalClassificationService.Synchronization(list);
    }

    /**
     * 查询售后区域分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:regional:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        startPage();
        List<MarketAfterSaleRegionalClassification> list = marketAfterSaleRegionalClassificationService.selectMarketAfterSaleRegionalClassificationList(marketAfterSaleRegionalClassification);
        return getDataTable(list);
    }

    /**
     * 导出售后区域分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:regional:export')")
    @Log(title = "售后区域分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        List<MarketAfterSaleRegionalClassification> list = marketAfterSaleRegionalClassificationService.selectMarketAfterSaleRegionalClassificationList(marketAfterSaleRegionalClassification);
        ExcelUtil<MarketAfterSaleRegionalClassification> util = new ExcelUtil<MarketAfterSaleRegionalClassification>(MarketAfterSaleRegionalClassification.class);
        util.exportExcel(response, list, "售后区域分类数据");
    }

    /**
     * 获取售后区域分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:regional:query')")
    @GetMapping(value = "/{masrcId}")
    public AjaxResult getInfo(@PathVariable("masrcId") Long masrcId)
    {
        return success(marketAfterSaleRegionalClassificationService.selectMarketAfterSaleRegionalClassificationByMasrcId(masrcId));
    }

    /**
     * 新增售后区域分类
     */
    @PreAuthorize("@ss.hasPermi('market:regional:add')")
    @Log(title = "售后区域分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        return toAjax(marketAfterSaleRegionalClassificationService.insertMarketAfterSaleRegionalClassification(marketAfterSaleRegionalClassification));
    }

    /**
     * 修改售后区域分类
     */
    @PreAuthorize("@ss.hasPermi('market:regional:edit')")
    @Log(title = "售后区域分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification)
    {
        return toAjax(marketAfterSaleRegionalClassificationService.updateMarketAfterSaleRegionalClassification(marketAfterSaleRegionalClassification));
    }

    /**
     * 删除售后区域分类
     */
    @PreAuthorize("@ss.hasPermi('market:regional:remove')")
    @Log(title = "售后区域分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{masrcIds}")
    public AjaxResult remove(@PathVariable Long[] masrcIds)
    {
        return toAjax(marketAfterSaleRegionalClassificationService.deleteMarketAfterSaleRegionalClassificationByMasrcIds(masrcIds));
    }
}
