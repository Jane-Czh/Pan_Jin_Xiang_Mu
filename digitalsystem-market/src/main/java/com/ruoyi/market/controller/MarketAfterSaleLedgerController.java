package com.ruoyi.market.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.market.domain.MarketAfterSaleLedger;
import com.ruoyi.market.service.IMarketAfterSaleLedgerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 售后台账导入Controller
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/market/ledger")
public class MarketAfterSaleLedgerController extends BaseController
{
    @Autowired
    private IMarketAfterSaleLedgerService marketAfterSaleLedgerService;


    /**
     * 导入excel
     */
    @Log(title = "[市场]售后台账表表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('market:ledger:import')")
    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {
            marketAfterSaleLedgerService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }


    /**
     * 查询售后台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketAfterSaleLedger marketAfterSaleLedger)
    {
        startPage();
        List<MarketAfterSaleLedger> list = marketAfterSaleLedgerService.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
        return getDataTable(list);
    }

    /**
     * 导出售后台账导入列表
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:export')")
    @Log(title = "售后台账导入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketAfterSaleLedger marketAfterSaleLedger)
    {
        List<MarketAfterSaleLedger> list = marketAfterSaleLedgerService.selectMarketAfterSaleLedgerList(marketAfterSaleLedger);
        ExcelUtil<MarketAfterSaleLedger> util = new ExcelUtil<MarketAfterSaleLedger>(MarketAfterSaleLedger.class);
        util.exportExcel(response, list, "售后台账导入数据");
    }

    /**
     * 获取售后台账导入详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:query')")
    @GetMapping(value = "/{maslId}")
    public AjaxResult getInfo(@PathVariable("maslId") Long maslId)
    {
        return success(marketAfterSaleLedgerService.selectMarketAfterSaleLedgerByMaslId(maslId));
    }

    /**
     * 新增售后台账导入
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:add')")
    @Log(title = "售后台账导入", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketAfterSaleLedger marketAfterSaleLedger)
    {
        return toAjax(marketAfterSaleLedgerService.insertMarketAfterSaleLedger(marketAfterSaleLedger));
    }

    /**
     * 修改售后台账导入
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:edit')")
    @Log(title = "售后台账导入", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketAfterSaleLedger marketAfterSaleLedger)
    {
        return toAjax(marketAfterSaleLedgerService.updateMarketAfterSaleLedger(marketAfterSaleLedger));
    }

    /**
     * 删除售后台账导入
     */
    @PreAuthorize("@ss.hasPermi('market:ledger:remove')")
    @Log(title = "售后台账导入", businessType = BusinessType.DELETE)
	@DeleteMapping("/{maslIds}")
    public AjaxResult remove(@PathVariable Long[] maslIds)
    {
        return toAjax(marketAfterSaleLedgerService.deleteMarketAfterSaleLedgerByMaslIds(maslIds));
    }
}
