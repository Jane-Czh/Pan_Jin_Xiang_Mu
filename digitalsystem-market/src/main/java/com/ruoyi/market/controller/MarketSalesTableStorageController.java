package com.ruoyi.market.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.market.domain.MarketSalesTableStorage;
import com.ruoyi.market.service.IMarketSalesTableStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售台账存储Controller
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
@RestController
@RequestMapping("/market/Storage")
public class MarketSalesTableStorageController extends BaseController
{
    @Autowired
    private IMarketSalesTableStorageService marketSalesTableStorageService;

    /**
     * 查询销售台账存储列表
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketSalesTableStorage marketSalesTableStorage)
    {
        startPage();
        List<MarketSalesTableStorage> list = marketSalesTableStorageService.selectMarketSalesTableStorageList(marketSalesTableStorage);
        return getDataTable(list);
    }

    /**
     * 导出销售台账存储列表
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:export')")
    @Log(title = "销售台账存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketSalesTableStorage marketSalesTableStorage)
    {
        List<MarketSalesTableStorage> list = marketSalesTableStorageService.selectMarketSalesTableStorageList(marketSalesTableStorage);
        ExcelUtil<MarketSalesTableStorage> util = new ExcelUtil<MarketSalesTableStorage>(MarketSalesTableStorage.class);
        util.exportExcel(response, list, "销售台账存储数据");
    }

    /**
     * 获取销售台账存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:query')")
    @GetMapping(value = "/{mstsId}")
    public AjaxResult getInfo(@PathVariable("mstsId") Long mstsId)
    {
        return success(marketSalesTableStorageService.selectMarketSalesTableStorageByMstsId(mstsId));
    }

    /**
     * 新增销售台账存储
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:add')")
    @Log(title = "销售台账存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketSalesTableStorage marketSalesTableStorage)
    {
        return toAjax(marketSalesTableStorageService.insertMarketSalesTableStorage(marketSalesTableStorage));
    }

    /**
     * 修改销售台账存储
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:edit')")
    @Log(title = "销售台账存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketSalesTableStorage marketSalesTableStorage)
    {
        return toAjax(marketSalesTableStorageService.updateMarketSalesTableStorage(marketSalesTableStorage));
    }

    /**
     * 删除销售台账存储
     */
    @PreAuthorize("@ss.hasPermi('market:Storage:remove')")
    @Log(title = "销售台账存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mstsIds}")
    public AjaxResult remove(@PathVariable Long[] mstsIds)
    {
        return toAjax(marketSalesTableStorageService.deleteMarketSalesTableStorageByMstsIds(mstsIds));
    }
}
