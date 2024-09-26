package com.ruoyi.market.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.ruoyi.market.domain.MarketOrderSumnumber;
import com.ruoyi.market.mapper.MarketOrderSumnumberMapper;
import com.ruoyi.market.mapper.MarketTWeightCarTypeMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.market.domain.MarketTWeightCarType;
import com.ruoyi.market.service.IMarketTWeightCarTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-09-15
 */
@RestController
@RequestMapping("/system/type")
public class MarketTWeightCarTypeController extends BaseController
{
    @Autowired
    private IMarketTWeightCarTypeService marketTWeightCarTypeService;
    @Autowired
    private MarketTWeightCarTypeMapper marketTWeightCarTypeMapper;
    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(MarketTWeightCarType marketTWeightCarType)
    {
        startPage();
        List<MarketTWeightCarType> list = marketTWeightCarTypeService.selectMarketTWeightCarTypeList(marketTWeightCarType);
        return getDataTable(list);
    }

    @PostMapping("/import")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile excelFile)
    {
        System.out.println("---------import--------------");
        try {

            EasyExcel.read(excelFile.getInputStream(), MarketTWeightCarType.class, new PageReadListener<MarketTWeightCarType>(dataList -> {
                for (MarketTWeightCarType user : dataList) {
                    //将导入的数据用mybatisPlus一个个添加进数据库
                    System.out.println(user);
                    marketTWeightCarTypeMapper.insertMarketTWeightCarType(user);
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
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MarketTWeightCarType marketTWeightCarType)
    {
        List<MarketTWeightCarType> list = marketTWeightCarTypeService.selectMarketTWeightCarTypeList(marketTWeightCarType);
        ExcelUtil<MarketTWeightCarType> util = new ExcelUtil<MarketTWeightCarType>(MarketTWeightCarType.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(marketTWeightCarTypeService.selectMarketTWeightCarTypeById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MarketTWeightCarType marketTWeightCarType)
    {
        return toAjax(marketTWeightCarTypeService.insertMarketTWeightCarType(marketTWeightCarType));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MarketTWeightCarType marketTWeightCarType)
    {
        return toAjax(marketTWeightCarTypeService.updateMarketTWeightCarType(marketTWeightCarType));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(marketTWeightCarTypeService.deleteMarketTWeightCarTypeByIds(ids));
    }
}
