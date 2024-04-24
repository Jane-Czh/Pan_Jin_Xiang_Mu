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
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.service.IMarketSalesTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 市场科销售台账Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@RequestMapping("/market/import")
public class MarketSalesTableController extends BaseController
{
    @Autowired
    private IMarketSalesTableService marketSalesTableService;

    /**
     * 导入excel表格功能
     */
    @Log(title = "[市场]销售台账表表上传", businessType = BusinessType.INSERT)
    @PostMapping("/import")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        try {
            marketSalesTableService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    /**
     * 查询市场科销售台账列表
     */
    @PreAuthorize("@ss.hasPermi('market:import:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketSalesTable marketSalesTable)
    {
        startPage();
        List<MarketSalesTable> list = marketSalesTableService.selectMarketSalesTableList(marketSalesTable);
        System.out.println(list);
        return getDataTable(list);
    }
//    @GetMapping("/list1")
//    public TableDataInfo list1(MarketSalesTable marketSalesTable)
//    {
////        startPage();
//        List<MarketSalesTable> list = marketSalesTableService.selectMarketSalesTableList(marketSalesTable);
//        System.out.println("测试list"+list.size());
//        return getDataTable(list);
//    }
    /**
     * 导出市场科销售台账列表
     */
    @PreAuthorize("@ss.hasPermi('market:import:export')")
    @Log(title = "市场科销售台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketSalesTable marketSalesTable)
    {
        List<MarketSalesTable> list = marketSalesTableService.selectMarketSalesTableList(marketSalesTable);
        ExcelUtil<MarketSalesTable> util = new ExcelUtil<MarketSalesTable>(MarketSalesTable.class);
        util.exportExcel(response, list, "市场科销售台账数据");
    }

    /**
     * 获取市场科销售台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:import:query')")
    @GetMapping(value = "/{msId}")
    public AjaxResult getInfo(@PathVariable("msId") Long msId)
    {
        return success(marketSalesTableService.selectMarketSalesTableByMsId(msId));
    }

    /**
     * 新增市场科销售台账
     */
    @PreAuthorize("@ss.hasPermi('market:import:add')")
    @Log(title = "市场科销售台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketSalesTable marketSalesTable)
    {
        return toAjax(marketSalesTableService.insertMarketSalesTable(marketSalesTable));
    }

    /**
     * 修改市场科销售台账
     */
    @PreAuthorize("@ss.hasPermi('market:import:edit')")
    @Log(title = "市场科销售台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketSalesTable marketSalesTable)
    {
        return toAjax(marketSalesTableService.updateMarketSalesTable(marketSalesTable));
    }

    /**
     * 删除市场科销售台账
     */
    @PreAuthorize("@ss.hasPermi('market:import:remove')")
    @Log(title = "市场科销售台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{msIds}")
    public AjaxResult remove(@PathVariable Long[] msIds)
    {
        return toAjax(marketSalesTableService.deleteMarketSalesTableByMsIds(msIds));
    }
}
