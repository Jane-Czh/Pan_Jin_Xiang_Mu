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
import com.ruoyi.market.domain.MarketCarType;
import com.ruoyi.market.service.IMarketCarTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要车型维护Controller
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
@RestController
@RequestMapping("/market/cartype")
public class MarketCarTypeController extends BaseController
{
    @Autowired
    private IMarketCarTypeService marketCarTypeService;

    @PostMapping("/import")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        try {
            marketCarTypeService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    /**
     * 查询主要车型维护列表
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketCarType marketCarType)
    {
        startPage();
        List<MarketCarType> list = marketCarTypeService.selectMarketCarTypeList(marketCarType);
        return getDataTable(list);
    }

    /**
     * 导出主要车型维护列表
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:export')")
    @Log(title = "主要车型维护", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketCarType marketCarType)
    {
        List<MarketCarType> list = marketCarTypeService.selectMarketCarTypeList(marketCarType);
        ExcelUtil<MarketCarType> util = new ExcelUtil<MarketCarType>(MarketCarType.class);
        util.exportExcel(response, list, "主要车型维护数据");
    }

    /**
     * 获取主要车型维护详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:query')")
    @GetMapping(value = "/{mctId}")
    public AjaxResult getInfo(@PathVariable("mctId") Long mctId)
    {
        return success(marketCarTypeService.selectMarketCarTypeByMctId(mctId));
    }

    /**
     * 新增主要车型维护
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:add')")
    @Log(title = "主要车型维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketCarType marketCarType)
    {
        return toAjax(marketCarTypeService.insertMarketCarType(marketCarType));
    }

    /**
     * 修改主要车型维护
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:edit')")
    @Log(title = "主要车型维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketCarType marketCarType)
    {
        return toAjax(marketCarTypeService.updateMarketCarType(marketCarType));
    }

    /**
     * 删除主要车型维护
     */
    @PreAuthorize("@ss.hasPermi('market:cartype:remove')")
    @Log(title = "主要车型维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mctIds}")
    public AjaxResult remove(@PathVariable Long[] mctIds)
    {
        return toAjax(marketCarTypeService.deleteMarketCarTypeByMctIds(mctIds));
    }
}
