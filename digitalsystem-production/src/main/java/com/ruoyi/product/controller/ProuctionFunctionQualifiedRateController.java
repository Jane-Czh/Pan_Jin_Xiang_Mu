package com.ruoyi.product.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import com.ruoyi.product.service.IProuctionFunctionQualifiedRateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 自制件合格率Controller
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@RestController
@RequestMapping("/product/rate")
public class ProuctionFunctionQualifiedRateController extends BaseController
{
    @Autowired
    private IProuctionFunctionQualifiedRateService prouctionFunctionQualifiedRateService;
    /**
     * 导入excel表格功能
     */
    @PreAuthorize("@ss.hasPermi('product:rate:import')")
    @Log(title = "[生产]表上传", businessType = BusinessType.INSERT)
    @PostMapping("/Dimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {
            prouctionFunctionQualifiedRateService.importInterests(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }
    @PostMapping("/synchronization")
    public void Database_Synchronization(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate1){
        System.out.println("-----------同步-------同步------------");
        List<ProuctionFunctionQualifiedRate> list = prouctionFunctionQualifiedRateService.selectProuctionFunctionQualifiedRateList1();
        prouctionFunctionQualifiedRateService.Synchronization(list, prouctionFunctionQualifiedRate1);

        System.out.println("-----------同步-------同步------------");

    }

    /**
     * 查询自制件合格率列表
     */
    @PreAuthorize("@ss.hasPermi('product:rate:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        startPage();
        List<ProuctionFunctionQualifiedRate> list = prouctionFunctionQualifiedRateService.selectProuctionFunctionQualifiedRateList(prouctionFunctionQualifiedRate);
        return getDataTable(list);
    }

    /**
     * 导出自制件合格率列表
     */
    @PreAuthorize("@ss.hasPermi('product:rate:export')")
    @Log(title = "自制件合格率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        List<ProuctionFunctionQualifiedRate> list = prouctionFunctionQualifiedRateService.selectProuctionFunctionQualifiedRateList(prouctionFunctionQualifiedRate);
        ExcelUtil<ProuctionFunctionQualifiedRate> util = new ExcelUtil<ProuctionFunctionQualifiedRate>(ProuctionFunctionQualifiedRate.class);
        util.exportExcel(response, list, "自制件合格率数据");
    }

    /**
     * 获取自制件合格率详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:rate:query')")
    @GetMapping(value = "/{pqrId}")
    public AjaxResult getInfo(@PathVariable("pqrId") Long pqrId)
    {
        return success(prouctionFunctionQualifiedRateService.selectProuctionFunctionQualifiedRateByPqrId(pqrId));
    }

    /**
     * 新增自制件合格率
     */
    @PreAuthorize("@ss.hasPermi('product:rate:add')")
    @Log(title = "自制件合格率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        return toAjax(prouctionFunctionQualifiedRateService.insertProuctionFunctionQualifiedRate(prouctionFunctionQualifiedRate));
    }

    /**
     * 修改自制件合格率
     */
    @PreAuthorize("@ss.hasPermi('product:rate:edit')")
    @Log(title = "自制件合格率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        return toAjax(prouctionFunctionQualifiedRateService.updateProuctionFunctionQualifiedRate(prouctionFunctionQualifiedRate));
    }

    /**
     * 删除自制件合格率
     */
    @PreAuthorize("@ss.hasPermi('product:rate:remove')")
    @Log(title = "自制件合格率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pqrIds}")
    public AjaxResult remove(@PathVariable Long[] pqrIds)
    {
        return toAjax(prouctionFunctionQualifiedRateService.deleteProuctionFunctionQualifiedRateByPqrIds(pqrIds));
    }
}
