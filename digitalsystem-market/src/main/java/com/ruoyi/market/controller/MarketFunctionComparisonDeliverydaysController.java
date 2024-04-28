package com.ruoyi.market.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
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
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import com.ruoyi.market.service.IMarketFunctionComparisonDeliverydaysService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 同配置车型交货天数对比功能Controller
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/market/compare")
public class MarketFunctionComparisonDeliverydaysController extends BaseController
{
    @Autowired
    private IMarketFunctionComparisonDeliverydaysService marketFunctionComparisonDeliverydaysService;
    @Autowired
    private IMarketCommercialVehicleTableService marketCommercialVehicleTableService;
    /*tongb*/
    @PostMapping("/synchronization")
    public void Database_Synchronization(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays1){
        System.out.println("-----------同步-------同步------------");
        List<MarketCommercialVehicleTable> list = marketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        marketFunctionComparisonDeliverydaysService.Synchronization(list, marketFunctionComparisonDeliverydays1);
        System.out.println("-----------同步-------同步------------");
    }
    /**
     * 查询同配置车型交货天数对比功能列表
     */
    @PreAuthorize("@ss.hasPermi('market:compare:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        startPage();
        List<MarketFunctionComparisonDeliverydays> list = marketFunctionComparisonDeliverydaysService.selectMarketFunctionComparisonDeliverydaysList(marketFunctionComparisonDeliverydays);
        return getDataTable(list);
    }

    /**
     * 导出同配置车型交货天数对比功能列表
     */
    @PreAuthorize("@ss.hasPermi('market:compare:export')")
    @Log(title = "同配置车型交货天数对比功能", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        List<MarketFunctionComparisonDeliverydays> list = marketFunctionComparisonDeliverydaysService.selectMarketFunctionComparisonDeliverydaysList(marketFunctionComparisonDeliverydays);
        ExcelUtil<MarketFunctionComparisonDeliverydays> util = new ExcelUtil<MarketFunctionComparisonDeliverydays>(MarketFunctionComparisonDeliverydays.class);
        util.exportExcel(response, list, "同配置车型交货天数对比功能数据");
    }

    /**
     * 获取同配置车型交货天数对比功能详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:compare:query')")
    @GetMapping(value = "/{mfcdId}")
    public AjaxResult getInfo(@PathVariable("mfcdId") Long mfcdId)
    {
        return success(marketFunctionComparisonDeliverydaysService.selectMarketFunctionComparisonDeliverydaysByMfcdId(mfcdId));
    }

    /**
     * 新增同配置车型交货天数对比功能
     */
    @PreAuthorize("@ss.hasPermi('market:compare:add')")
    @Log(title = "同配置车型交货天数对比功能", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        return toAjax(marketFunctionComparisonDeliverydaysService.insertMarketFunctionComparisonDeliverydays(marketFunctionComparisonDeliverydays));
    }

    /**
     * 修改同配置车型交货天数对比功能
     */
    @PreAuthorize("@ss.hasPermi('market:compare:edit')")
    @Log(title = "同配置车型交货天数对比功能", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketFunctionComparisonDeliverydays marketFunctionComparisonDeliverydays)
    {
        return toAjax(marketFunctionComparisonDeliverydaysService.updateMarketFunctionComparisonDeliverydays(marketFunctionComparisonDeliverydays));
    }

    /**
     * 删除同配置车型交货天数对比功能
     */
    @PreAuthorize("@ss.hasPermi('market:compare:remove')")
    @Log(title = "同配置车型交货天数对比功能", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mfcdIds}")
    public AjaxResult remove(@PathVariable Long[] mfcdIds)
    {
        return toAjax(marketFunctionComparisonDeliverydaysService.deleteMarketFunctionComparisonDeliverydaysByMfcdIds(mfcdIds));
    }
}
