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
import com.ruoyi.market.domain.MarketAfterSaleProblemClassification;
import com.ruoyi.market.service.IMarketAfterSaleProblemClassificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 售后问题分类Controller
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
@RestController
@RequestMapping("/market/classification")
public class MarketAfterSaleProblemClassificationController extends BaseController
{
    @Autowired
    private IMarketAfterSaleProblemClassificationService marketAfterSaleProblemClassificationService;

    @Autowired
    private IMarketAfterSaleLedgerService marketAfterSaleLedgerService;

    /**
     * 同步售后台账数据库信息功能
     */
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification){
        System.out.println("-------同步------同步------");
        MarketAfterSaleLedger marketAfterSaleLedger = new MarketAfterSaleLedger();
        List<MarketAfterSaleLedger> list = marketAfterSaleLedgerService.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
        marketAfterSaleProblemClassificationService.Synchronization(list);
    }
    /**
     * 查询售后问题分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:classification:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        startPage();
        List<MarketAfterSaleProblemClassification> list = marketAfterSaleProblemClassificationService.selectMarketAfterSaleProblemClassificationList(marketAfterSaleProblemClassification);
        return getDataTable(list);
    }

    /**
     * 导出售后问题分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:classification:export')")
    @Log(title = "售后问题分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        List<MarketAfterSaleProblemClassification> list = marketAfterSaleProblemClassificationService.selectMarketAfterSaleProblemClassificationList(marketAfterSaleProblemClassification);
        ExcelUtil<MarketAfterSaleProblemClassification> util = new ExcelUtil<MarketAfterSaleProblemClassification>(MarketAfterSaleProblemClassification.class);
        util.exportExcel(response, list, "售后问题分类数据");
    }

    /**
     * 获取售后问题分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:classification:query')")
    @GetMapping(value = "/{maspcId}")
    public AjaxResult getInfo(@PathVariable("maspcId") Long maspcId)
    {
        return success(marketAfterSaleProblemClassificationService.selectMarketAfterSaleProblemClassificationByMaspcId(maspcId));
    }

    /**
     * 新增售后问题分类
     */
    @PreAuthorize("@ss.hasPermi('market:classification:add')")
    @Log(title = "售后问题分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        return toAjax(marketAfterSaleProblemClassificationService.insertMarketAfterSaleProblemClassification(marketAfterSaleProblemClassification));
    }

    /**
     * 修改售后问题分类
     */
    @PreAuthorize("@ss.hasPermi('market:classification:edit')")
    @Log(title = "售后问题分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketAfterSaleProblemClassification marketAfterSaleProblemClassification)
    {
        return toAjax(marketAfterSaleProblemClassificationService.updateMarketAfterSaleProblemClassification(marketAfterSaleProblemClassification));
    }

    /**
     * 删除售后问题分类
     */
    @PreAuthorize("@ss.hasPermi('market:classification:remove')")
    @Log(title = "售后问题分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{maspcIds}")
    public AjaxResult remove(@PathVariable Long[] maspcIds)
    {
        return toAjax(marketAfterSaleProblemClassificationService.deleteMarketAfterSaleProblemClassificationByMaspcIds(maspcIds));
    }
}
