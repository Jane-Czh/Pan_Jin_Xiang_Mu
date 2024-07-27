package com.ruoyi.product.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.product.domain.ProductionCommuteTime;
import com.ruoyi.product.domain.WorkTimeData;
import com.ruoyi.product.service.IProductionCommuteTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * 设置上下班时间Controller
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@RestController
@RequestMapping("/product/time")
public class ProductionCommuteTimeController extends BaseController
{
    @Autowired
    private IProductionCommuteTimeService productionCommuteTimeService;

    @Log(title = "设置上下班时间", businessType = BusinessType.INSERT)
    @PostMapping("/updatetime")
    public AjaxResult updateWorkTime(@RequestBody WorkTimeData workTimeData) throws ParseException {
        System.out.println("------------update------------");
        System.out.println("正常上班时间" + workTimeData.getStartTime());
        System.out.println("正常下班时间" + workTimeData.getEndTime());
        productionCommuteTimeService.swtichdate(workTimeData);
        return AjaxResult.success("Work time updated");
    }
    /**
     * 查询设置上下班时间列表
     */
    @PreAuthorize("@ss.hasPermi('product:time:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionCommuteTime productionCommuteTime)
    {
        startPage();
        List<ProductionCommuteTime> list = productionCommuteTimeService.selectProductionCommuteTimeList(productionCommuteTime);
        return getDataTable(list);
    }

    /**
     * 导出设置上下班时间列表
     */
    @PreAuthorize("@ss.hasPermi('product:time:export')")
    @Log(title = "设置上下班时间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionCommuteTime productionCommuteTime)
    {
        List<ProductionCommuteTime> list = productionCommuteTimeService.selectProductionCommuteTimeList(productionCommuteTime);
        ExcelUtil<ProductionCommuteTime> util = new ExcelUtil<ProductionCommuteTime>(ProductionCommuteTime.class);
        util.exportExcel(response, list, "设置上下班时间数据");
    }

    /**
     * 获取设置上下班时间详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:time:query')")
    @GetMapping(value = "/{commuteTimeId}")
    public AjaxResult getInfo(@PathVariable("commuteTimeId") Long commuteTimeId)
    {
        return success(productionCommuteTimeService.selectProductionCommuteTimeByCommuteTimeId(commuteTimeId));
    }

    @GetMapping("reltime")
    public AjaxResult getrelTime()
    {
        return success(productionCommuteTimeService.selectProductionCommuteTimeByCommuteTimeMAXId());
    }

    /**
     * 新增设置上下班时间
     */
    @PreAuthorize("@ss.hasPermi('product:time:add')")
    @Log(title = "设置上下班时间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionCommuteTime productionCommuteTime)
    {
        return toAjax(productionCommuteTimeService.insertProductionCommuteTime(productionCommuteTime));
    }

    /**
     * 修改设置上下班时间
     */
    @PreAuthorize("@ss.hasPermi('product:time:edit')")
    @Log(title = "设置上下班时间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionCommuteTime productionCommuteTime)
    {
        return toAjax(productionCommuteTimeService.updateProductionCommuteTime(productionCommuteTime));
    }

    /**
     * 删除设置上下班时间
     */
    @PreAuthorize("@ss.hasPermi('product:time:remove')")
    @Log(title = "设置上下班时间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commuteTimeIds}")
    public AjaxResult remove(@PathVariable Long[] commuteTimeIds)
    {
        return toAjax(productionCommuteTimeService.deleteProductionCommuteTimeByCommuteTimeIds(commuteTimeIds));
    }
}
