package com.ruoyi.product.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.product.domain.ProductionCommuteTime;
import com.ruoyi.product.domain.WorkTimeData;
import com.ruoyi.product.service.IProductionCommuteTimeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.service.IProductionClockInFormService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工打卡表Controller
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@RestController
@RequestMapping("/product/form")
public class ProductionClockInFormController extends BaseController
{
    @Autowired
    private IProductionClockInFormService productionClockInFormService;
    @Autowired
    private IProductionCommuteTimeService productionCommuteTimeService;

    /**
     * 导入excel表格功能
     */
//    @Log(title = "[生产]表上传", businessType = BusinessType.INSERT)
    @PostMapping("/FOimport")
    public AjaxResult importTable(@RequestParam("file") MultipartFile excelFile) {
        System.out.println("------------import-------import------------");
        System.out.println("excelFile" + excelFile);
        try {
            ProductionCommuteTime productionCommuteTime =productionCommuteTimeService.selectProductionCommuteTimeByCommuteTimeMAXId();
            productionClockInFormService.importInterests(excelFile,productionCommuteTime);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel上传失败");
        }
        return success();
    }

    @Log(title = "设置上下班时间", businessType = BusinessType.INSERT)
    @PostMapping("/updatetime")
    public AjaxResult updateWorkTime(@RequestBody WorkTimeData workTimeData) throws ParseException {
        System.out.println("------------update------------");
        System.out.println("正常上班时间" + workTimeData.getStartTime());
        System.out.println("正常下班时间" + workTimeData.getEndTime());
        List<ProductionClockInForm> list1 = productionClockInFormService.selectProductionClockInFormList1();
        productionClockInFormService.updateTime(list1,workTimeData);
        return AjaxResult.success("Work time updated");
    }

    /**
//     * 导入excel表格功能
//     */
//    @Log(title = "日期修改", businessType = BusinessType.INSERT)
//    @PostMapping("/updateFormTime")
//    public AjaxResult updatetime(@RequestParam String combinedDateTime){
//        List<ProductionClockInForm> lsit = productionClockInFormService.selectProductionClockInFormList1();
//        productionClockInFormService.updatetime(lsit,combinedDateTime);
//        return success();
//    }
    /**
     * 查询员工打卡表列表
     */
    @PreAuthorize("@ss.hasPermi('product:form:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductionClockInForm productionClockInForm)
    {
        startPage();
        List<ProductionClockInForm> list = productionClockInFormService.selectProductionClockInFormList(productionClockInForm);
        return getDataTable(list);
    }

    /**
     * 导出员工打卡表列表
     */
    @PreAuthorize("@ss.hasPermi('product:form:export')")
    @Log(title = "员工打卡表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductionClockInForm productionClockInForm)
    {
        List<ProductionClockInForm> list = productionClockInFormService.selectProductionClockInFormList(productionClockInForm);
        ExcelUtil<ProductionClockInForm> util = new ExcelUtil<ProductionClockInForm>(ProductionClockInForm.class);
        util.exportExcel(response, list, "员工打卡表数据");
    }

    /**
     * 获取员工打卡表详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:form:query')")
    @GetMapping(value = "/{pcifId}")
    public AjaxResult getInfo(@PathVariable("pcifId") Long pcifId)
    {
        return success(productionClockInFormService.selectProductionClockInFormByPcifId(pcifId));
    }

    /**
     * 新增员工打卡表
     */
    @PreAuthorize("@ss.hasPermi('product:form:add')")
    @Log(title = "员工打卡表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductionClockInForm productionClockInForm)
    {
        return toAjax(productionClockInFormService.insertProductionClockInForm(productionClockInForm));
    }

    /**
     * 修改员工打卡表
     */
    @PreAuthorize("@ss.hasPermi('product:form:edit')")
    @Log(title = "员工打卡表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductionClockInForm productionClockInForm)
    {
        System.out.println("++++++执行中+++++++");
        return toAjax(productionClockInFormService.updateProductionClockInForm(productionClockInForm));
    }

    /**
     * 删除员工打卡表
     */
    @PreAuthorize("@ss.hasPermi('product:form:remove')")
    @Log(title = "员工打卡表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pcifIds}")
    public AjaxResult remove(@PathVariable Long[] pcifIds)
    {
        return toAjax(productionClockInFormService.deleteProductionClockInFormByPcifIds(pcifIds));
    }
}
