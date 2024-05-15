package com.ruoyi.market.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import com.ruoyi.market.service.IMarketFunctionComparisonDeliverydaysService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品车台账Controller
 * 
 * @author ruoyi
 * @date 2024-04-12
 */
@RestController
@RequestMapping("/market/CVimport")
public class MarketCommercialVehicleTableController extends BaseController
{
    @Autowired
    private IMarketCommercialVehicleTableService marketCommercialVehicleTableService;

    /*表单导入功能*/
    @Log(title = "[市场]商品车台账表表上传", businessType = BusinessType.INSERT)
    @PostMapping("/CVimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {

            marketCommercialVehicleTableService.CVimportInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }



    /**
     * 查询商品车台账列表
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        startPage();
        List<MarketCommercialVehicleTable> list = marketCommercialVehicleTableService.selectMarketCommercialVehicleTableList(marketCommercialVehicleTable);
        return getDataTable(list);
    }

    /**
     * 导出商品车台账列表
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:export')")
    @Log(title = "商品车台账", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        List<MarketCommercialVehicleTable> list = marketCommercialVehicleTableService.selectMarketCommercialVehicleTableList(marketCommercialVehicleTable);
        ExcelUtil<MarketCommercialVehicleTable> util = new ExcelUtil<MarketCommercialVehicleTable>(MarketCommercialVehicleTable.class);
        util.exportExcel(response, list, "商品车台账数据");
    }

    /**
     * 获取商品车台账详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:query')")
    @GetMapping(value = "/{mcvId}")
    public AjaxResult getInfo(@PathVariable("mcvId") Long mcvId)
    {
        return success(marketCommercialVehicleTableService.selectMarketCommercialVehicleTableByMcvId(mcvId));
    }

    /**
     * 新增商品车台账
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:add')")
    @Log(title = "商品车台账", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        return toAjax(marketCommercialVehicleTableService.insertMarketCommercialVehicleTable(marketCommercialVehicleTable));
    }

    /**
     * 修改商品车台账
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:edit')")
    @Log(title = "商品车台账", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketCommercialVehicleTable marketCommercialVehicleTable)
    {
        return toAjax(marketCommercialVehicleTableService.updateMarketCommercialVehicleTable(marketCommercialVehicleTable));
    }

    /**
     * 删除商品车台账
     */
    @PreAuthorize("@ss.hasPermi('market:CVimport:remove')")
    @Log(title = "商品车台账", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mcvIds}")
    public AjaxResult remove(@PathVariable Long[] mcvIds)
    {
        return toAjax(marketCommercialVehicleTableService.deleteMarketCommercialVehicleTableByMcvIds(mcvIds));
    }
}
