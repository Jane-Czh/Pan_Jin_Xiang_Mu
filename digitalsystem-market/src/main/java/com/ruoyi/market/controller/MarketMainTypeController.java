package com.ruoyi.market.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
import com.ruoyi.market.domain.MarketMainType;
import com.ruoyi.market.service.IMarketMainTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 主要车型字典表Controller
 * 
 * @author Teandron
 * @date 2024-09-13
 */
@RestController
@RequestMapping("/market/mainCarType")
public class MarketMainTypeController extends BaseController
{
    @Autowired
    private IMarketMainTypeService marketMainTypeService;


    @Log(title = "[市场]主要车型字典表上传", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('market:mainCarType:import')")
    @PostMapping("/import")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------mainCarType-------import------------");
        try {
            marketMainTypeService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    /**
     * 查询主要车型字典表列表
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketMainType marketMainType)
    {
        startPage();
        List<MarketMainType> list = marketMainTypeService.selectMarketMainTypeList(marketMainType);
        return getDataTable(list);
    }

    /**
     * 导出主要车型字典表列表
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:export')")
    @Log(title = "主要车型字典表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketMainType marketMainType)
    {
        List<MarketMainType> list = marketMainTypeService.selectMarketMainTypeList(marketMainType);
        ExcelUtil<MarketMainType> util = new ExcelUtil<MarketMainType>(MarketMainType.class);
        util.exportExcel(response, list, "主要车型字典表数据");
    }

    /**
     * 获取主要车型字典表详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketMainTypeService.selectMarketMainTypeById(id));
    }

    /**
     * 新增主要车型字典表
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:add')")
    @Log(title = "主要车型字典表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketMainType marketMainType)
    {
        return toAjax(marketMainTypeService.insertMarketMainType(marketMainType));
    }

    /**
     * 修改主要车型字典表
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:edit')")
    @Log(title = "主要车型字典表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketMainType marketMainType)
    {
        return toAjax(marketMainTypeService.updateMarketMainType(marketMainType));
    }

    /**
     * 删除主要车型字典表
     */
    @PreAuthorize("@ss.hasPermi('market:mainCarType:remove')")
    @Log(title = "主要车型字典表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketMainTypeService.deleteMarketMainTypeByIds(ids));
    }
}
