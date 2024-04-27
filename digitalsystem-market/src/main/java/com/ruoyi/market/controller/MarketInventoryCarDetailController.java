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
import com.ruoyi.market.domain.MarketInventoryCarDetail;
import com.ruoyi.market.service.IMarketInventoryCarDetailService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 库存车明细Controller
 * 
 * @author ruoyi
 * @date 2024-04-10
 */
@RestController
@RequestMapping("/market/detail")
public class MarketInventoryCarDetailController extends BaseController
{
    @Autowired
    private IMarketInventoryCarDetailService marketInventoryCarDetailService;

    /**
     * 导入excel库存车明细列表
     */
    @PostMapping("/import")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile){
        System.out.println("------------import-------import------------");
        try {
            marketInventoryCarDetailService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }

        return success();
    }

    /**
     * 查询库存车明细列表
     */
    @PreAuthorize("@ss.hasPermi('market:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketInventoryCarDetail marketInventoryCarDetail)
    {
        startPage();
        List<MarketInventoryCarDetail> list = marketInventoryCarDetailService.selectMarketInventoryCarDetailList(marketInventoryCarDetail);
        System.out.println(list.size());
        return getDataTable(list);
    }

    /**
     * 导出库存车明细列表
     */
    @PreAuthorize("@ss.hasPermi('market:detail:export')")
    @Log(title = "库存车明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketInventoryCarDetail marketInventoryCarDetail)
    {
        List<MarketInventoryCarDetail> list = marketInventoryCarDetailService.selectMarketInventoryCarDetailList(marketInventoryCarDetail);
        ExcelUtil<MarketInventoryCarDetail> util = new ExcelUtil<MarketInventoryCarDetail>(MarketInventoryCarDetail.class);
        util.exportExcel(response, list, "库存车明细数据");
    }

    /**
     * 获取库存车明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:detail:query')")
    @GetMapping(value = "/{micdId}")
    public AjaxResult getInfo(@PathVariable("micdId") Long micdId)
    {
        return success(marketInventoryCarDetailService.selectMarketInventoryCarDetailByMicdId(micdId));
    }

    /**
     * 新增库存车明细
     */
    @PreAuthorize("@ss.hasPermi('market:detail:add')")
    @Log(title = "库存车明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketInventoryCarDetail marketInventoryCarDetail)
    {
        return toAjax(marketInventoryCarDetailService.insertMarketInventoryCarDetail(marketInventoryCarDetail));
    }

    /**
     * 修改库存车明细
     */
    @PreAuthorize("@ss.hasPermi('market:detail:edit')")
    @Log(title = "库存车明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketInventoryCarDetail marketInventoryCarDetail)
    {
        return toAjax(marketInventoryCarDetailService.updateMarketInventoryCarDetail(marketInventoryCarDetail));
    }

    /**
     * 删除库存车明细
     */
    @PreAuthorize("@ss.hasPermi('market:detail:remove')")
    @Log(title = "库存车明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{micdIds}")
    public AjaxResult remove(@PathVariable Long[] micdIds)
    {
        return toAjax(marketInventoryCarDetailService.deleteMarketInventoryCarDetailByMicdIds(micdIds));
    }
}
