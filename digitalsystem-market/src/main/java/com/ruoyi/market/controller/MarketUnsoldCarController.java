package com.ruoyi.market.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.domain.MarketAfterSaleRegionalClassification;
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
import com.ruoyi.market.domain.MarketUnsoldCar;
import com.ruoyi.market.service.IMarketUnsoldCarService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 统计库存车数量Controller
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/market/unsoldcar")
public class MarketUnsoldCarController extends BaseController
{
    @Autowired
    private IMarketUnsoldCarService marketUnsoldCarService;

    @PreAuthorize("@ss.hasPermi('market:unsoldcar:syncReport')")
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketAfterSaleRegionalClassification marketAfterSaleRegionalClassification){
        System.out.println("------同步------");
        marketUnsoldCarService.Synchronization();
    }

    /**
     * 查询统计库存车数量列表
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketUnsoldCar marketUnsoldCar)
    {
        startPage();
        List<MarketUnsoldCar> list = marketUnsoldCarService.selectMarketUnsoldCarList(marketUnsoldCar);
        return getDataTable(list);
    }

    /**
     * 导出统计库存车数量列表
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:export')")
    @Log(title = "统计库存车数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketUnsoldCar marketUnsoldCar)
    {
        List<MarketUnsoldCar> list = marketUnsoldCarService.selectMarketUnsoldCarList(marketUnsoldCar);
        ExcelUtil<MarketUnsoldCar> util = new ExcelUtil<MarketUnsoldCar>(MarketUnsoldCar.class);
        util.exportExcel(response, list, "统计库存车数量数据");
    }

    /**
     * 获取统计库存车数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:query')")
    @GetMapping(value = "/{ucId}")
    public AjaxResult getInfo(@PathVariable("ucId") Long ucId)
    {
        return success(marketUnsoldCarService.selectMarketUnsoldCarByUcId(ucId));
    }

    /**
     * 新增统计库存车数量
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:add')")
    @Log(title = "统计库存车数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketUnsoldCar marketUnsoldCar)
    {
        return toAjax(marketUnsoldCarService.insertMarketUnsoldCar(marketUnsoldCar));
    }

    /**
     * 修改统计库存车数量
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:edit')")
    @Log(title = "统计库存车数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketUnsoldCar marketUnsoldCar)
    {
        return toAjax(marketUnsoldCarService.updateMarketUnsoldCar(marketUnsoldCar));
    }

    /**
     * 删除统计库存车数量
     */
    @PreAuthorize("@ss.hasPermi('market:unsoldcar:remove')")
    @Log(title = "统计库存车数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ucIds}")
    public AjaxResult remove(@PathVariable Long[] ucIds)
    {
        return toAjax(marketUnsoldCarService.deleteMarketUnsoldCarByUcIds(ucIds));
    }
}
