package com.ruoyi.product.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.service.IMarketCommercialVehicleTableService;
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
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;
import com.ruoyi.product.service.IProductionFunctionStatisticsOfPlanCompletionStatusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 计划完成情况Controller
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
@RestController
@RequestMapping("/product/status")
public class ProductionFunctionStatisticsOfPlanCompletionStatusController extends BaseController
{
    @Autowired
    private IProductionFunctionStatisticsOfPlanCompletionStatusService productionFunctionStatisticsOfPlanCompletionStatusService;
    @Autowired
    private IMarketCommercialVehicleTableService marketCommercialVehicleTableService;
    /**
     * 同步功能
     */
    @PostMapping("/synchronization")
    public void Database_Synchronization(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus1){
        System.out.println("-----------同步-------同步------------");
        List<MarketCommercialVehicleTable> list = marketCommercialVehicleTableService.selectMarketCommercialVehicleTableList1();
        productionFunctionStatisticsOfPlanCompletionStatusService.Synchronization(list, productionFunctionStatisticsOfPlanCompletionStatus1);

        System.out.println("-----------同步-------同步------------");

    }
    /**
     * 查询计划完成情况列表
     */
    @PreAuthorize("@ss.hasPermi('product:status:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        startPage();
        List<ProductionFunctionStatisticsOfPlanCompletionStatus> list = productionFunctionStatisticsOfPlanCompletionStatusService.selectProductionFunctionStatisticsOfPlanCompletionStatusList(productionFunctionStatisticsOfPlanCompletionStatus);
        return getDataTable(list);
    }

    /**
     * 导出计划完成情况列表
     */
    @PreAuthorize("@ss.hasPermi('product:status:export')")
    @Log(title = "计划完成情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        List<ProductionFunctionStatisticsOfPlanCompletionStatus> list = productionFunctionStatisticsOfPlanCompletionStatusService.selectProductionFunctionStatisticsOfPlanCompletionStatusList(productionFunctionStatisticsOfPlanCompletionStatus);
        ExcelUtil<ProductionFunctionStatisticsOfPlanCompletionStatus> util = new ExcelUtil<ProductionFunctionStatisticsOfPlanCompletionStatus>(ProductionFunctionStatisticsOfPlanCompletionStatus.class);
        util.exportExcel(response, list, "计划完成情况数据");
    }

    /**
     * 获取计划完成情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:status:query')")
    @GetMapping(value = "/{mpcId}")
    public AjaxResult getInfo(@PathVariable("mpcId") Long mpcId)
    {
        return success(productionFunctionStatisticsOfPlanCompletionStatusService.selectProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(mpcId));
    }

    /**
     * 新增计划完成情况
     */
    @PreAuthorize("@ss.hasPermi('product:status:add')")
    @Log(title = "计划完成情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        return toAjax(productionFunctionStatisticsOfPlanCompletionStatusService.insertProductionFunctionStatisticsOfPlanCompletionStatus(productionFunctionStatisticsOfPlanCompletionStatus));
    }

    /**
     * 修改计划完成情况
     */
    @PreAuthorize("@ss.hasPermi('product:status:edit')")
    @Log(title = "计划完成情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus)
    {
        return toAjax(productionFunctionStatisticsOfPlanCompletionStatusService.updateProductionFunctionStatisticsOfPlanCompletionStatus(productionFunctionStatisticsOfPlanCompletionStatus));
    }

    /**
     * 删除计划完成情况
     */
    @PreAuthorize("@ss.hasPermi('product:status:remove')")
    @Log(title = "计划完成情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mpcIds}")
    public AjaxResult remove(@PathVariable Long[] mpcIds)
    {
        return toAjax(productionFunctionStatisticsOfPlanCompletionStatusService.deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(mpcIds));
    }
}
