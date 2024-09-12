package com.heli.tech.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.heli.tech.domain.TechNonStandardOrder;
import com.heli.tech.service.ITechNonStandardOrderService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DisplayRequestParam;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Tech_Non_Standard_OrderController
 *
 * @author hong
 * @date 2024-08-02
 */
@Slf4j
@RestController
@RequestMapping("/tech/data/nonStandardOrder")
public class TechNonStandardOrderController extends BaseController {
    @Autowired
    private ITechNonStandardOrderService techNonStandardOrderService;

    /**
     * @description: 检查当月数据是否上传
     * @author: hong
     * @date: 2024/8/2 17:14
     * @version: 1.0
     */
    @PostMapping("/check")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
        public R checkNonStandardOrderUploadStatus(@RequestBody DisplayRequestParam displayRequestParam) {
        log.info("当前时间:"+displayRequestParam.getStartTime());
        Boolean status = techNonStandardOrderService.checkNonStandardOrderUploadStatus(displayRequestParam.getStartTime());
        log.info("检查当月数据是否上传"+status);
        return R.ok(status);
    }


    /**
     * @description: 非标订单导入
     * @author: hong
     * @date: 2024/8/2 13:58
     * @version: 1.0
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:import')")
    @PostMapping("/read")
    public R<String> simpleRead(Date yearAndMonth, MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {

            R<String> r = techNonStandardOrderService.readSalaryExcelToDB(multipartFile.getOriginalFilename(), inputStream, yearAndMonth);

            log.info("计算完毕，清空数据库");

            return r;
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }




    /**
     * 查询Tech_Non_Standard_Order列表
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TechNonStandardOrder techNonStandardOrder) {
        startPage();
        List<TechNonStandardOrder> list = techNonStandardOrderService.selectTechNonStandardOrderList(techNonStandardOrder);
        return getDataTable(list);
    }

    /**
     * 导出Tech_Non_Standard_Order列表
     */
//    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:export')")
//    @Log(title = "Tech_Non_Standard_Order", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, TechNonStandardOrder techNonStandardOrder) {
//        List<TechNonStandardOrder> list = techNonStandardOrderService.selectTechNonStandardOrderList(techNonStandardOrder);
//        ExcelUtil<TechNonStandardOrder> util = new ExcelUtil<TechNonStandardOrder>(TechNonStandardOrder.class);
//        util.exportExcel(response, list, "Tech_Non_Standard_Order数据");
//    }

    /**
     * 获取Tech_Non_Standard_Order详细信息
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:query')")
    @GetMapping(value = "/{tnId}")
    public AjaxResult getInfo(@PathVariable("tnId") Long tnId) {
        return success(techNonStandardOrderService.selectTechNonStandardOrderByTnId(tnId));
    }

    /**
     * 新增Tech_Non_Standard_Order
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:add')")
    @Log(title = "Tech_Non_Standard_Order", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TechNonStandardOrder techNonStandardOrder) {
        return toAjax(techNonStandardOrderService.insertTechNonStandardOrder(techNonStandardOrder));
    }

    /**
     * 修改Tech_Non_Standard_Order
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:edit')")
    @Log(title = "Tech_Non_Standard_Order", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TechNonStandardOrder techNonStandardOrder) {
        return toAjax(techNonStandardOrderService.updateTechNonStandardOrder(techNonStandardOrder));
    }

    /**
     * 删除Tech_Non_Standard_Order
     */
    @PreAuthorize("@ss.hasPermi('tech:Tech_Non_Standard_Order:remove')")
    @Log(title = "Tech_Non_Standard_Order", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tnIds}")
    public AjaxResult remove(@PathVariable Long[] tnIds) {
        return toAjax(techNonStandardOrderService.deleteTechNonStandardOrderByTnIds(tnIds));
    }
}
