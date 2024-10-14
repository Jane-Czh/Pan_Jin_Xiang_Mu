package com.ruoyi.market.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.ruoyi.market.mapper.MarketOrderSumnumberMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.market.domain.MarketOrderSumnumber;
import com.ruoyi.market.service.IMarketOrderSumnumberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@RestController
@RequestMapping("/system/sumNumber")
public class MarketOrderSumnumberController extends BaseController
{
    @Autowired
    private IMarketOrderSumnumberService marketOrderSumnumberService;
    @Autowired
    private MarketOrderSumnumberMapper marketOrderSumnumberMapper;
    @Log(title = "[市场]订单总数表", businessType = BusinessType.INSERT)
//    @PreAuthorize("@ss.hasPermi('market:ledger:import')")

    @PreAuthorize("@ss.hasPermi('market:sumNumber:import')")
    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {

            EasyExcel.read(excelFile.getInputStream(), MarketOrderSumnumber.class, new PageReadListener<MarketOrderSumnumber>(dataList -> {
                for (MarketOrderSumnumber user : dataList) {
                    //将导入的数据用mybatisPlus一个个添加进数据库
                    System.out.println(user);
                    marketOrderSumnumberMapper.insertMarketOrderSumnumber(user);
//                    loginMapper.insert(user);
                }
            })).sheet().doRead();

//            marketOrderSumnumberService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success();
    }
    /**
     * 查询【请填写功能名称】列表
     */
    //TODO 权限配置有误,已修改10-13
//    @PreAuthorize("@ss.hasPermi('system:sumNumber:list')")

    @PreAuthorize("@ss.hasPermi('market:sumNumber:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketOrderSumnumber marketOrderSumnumber)
    {
        startPage();
        List<MarketOrderSumnumber> list = marketOrderSumnumberService.selectMarketOrderSumnumberList(marketOrderSumnumber);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:sumNumber:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketOrderSumnumber marketOrderSumnumber)
    {
        List<MarketOrderSumnumber> list = marketOrderSumnumberService.selectMarketOrderSumnumberList(marketOrderSumnumber);
        ExcelUtil<MarketOrderSumnumber> util = new ExcelUtil<MarketOrderSumnumber>(MarketOrderSumnumber.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:sumNumber:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketOrderSumnumberService.selectMarketOrderSumnumberById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:sumNumber:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketOrderSumnumber marketOrderSumnumber)
    {
        return toAjax(marketOrderSumnumberService.insertMarketOrderSumnumber(marketOrderSumnumber));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:sumNumber:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketOrderSumnumber marketOrderSumnumber)
    {
        return toAjax(marketOrderSumnumberService.updateMarketOrderSumnumber(marketOrderSumnumber));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:sumNumber:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketOrderSumnumberService.deleteMarketOrderSumnumberByIds(ids));
    }
}
