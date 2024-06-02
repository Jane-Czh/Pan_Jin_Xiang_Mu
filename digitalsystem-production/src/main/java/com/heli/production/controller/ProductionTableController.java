package com.heli.production.controller;

import com.heli.production.domain.ProductionTable;
import com.heli.production.service.IProductionTableService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @description: [生产]上传excel并计算指标功能Controller
 * @author: hong
 * @date: 2024/4/11 20:11
 **/
@RestController
@RequestMapping("/production/table")
public class ProductionTableController extends BaseController {

    @Autowired
    private IProductionTableService productionTableService;
    private static final Logger log = LoggerFactory.getLogger(ProductionTableController.class);

    /**
     * @description: 上传 商品车台账excel，并存入数据库
     * @author: hong
     * @date: 2024/4/11 16:38
     */
    @PostMapping("/import")
    public R<String> simpleRead(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            return productionTableService.readProductionExcelToDB(multipartFile.getOriginalFilename(), inputStream);
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }
//
//
//    /**
//     * 查询商品车台账列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(ProductionTable productionTable) {
//        startPage();
//        List<ProductionTable> list = productionTableService.selectProductionTableList(productionTable);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出商品车台账列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:export')")
//    @Log(title = "商品车台账", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, ProductionTable productionTable) {
//        List<ProductionTable> list = productionTableService.selectProductionTableList(productionTable);
//        ExcelUtil<ProductionTable> util = new ExcelUtil<ProductionTable>(ProductionTable.class);
//        util.exportExcel(response, list, "商品车台账数据");
//    }
//
//    /**
//     * 获取商品车台账详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:query')")
//    @GetMapping(value = "/{mcvId}")
//    public AjaxResult getInfo(@PathVariable("mcvId") Long mcvId) {
//        return success(productionTableService.selectProductionTableByMcvId(mcvId));
//    }
//
//    /**
//     * 新增商品车台账
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:add')")
//    @Log(title = "商品车台账", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody ProductionTable productionTable) {
//        return toAjax(productionTableService.insertProductionTable(productionTable));
//    }
//
//    /**
//     * 修改商品车台账
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:edit')")
//    @Log(title = "商品车台账", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody ProductionTable productionTable) {
//        return toAjax(productionTableService.updateProductionTable(productionTable));
//    }
//
//    /**
//     * 删除商品车台账
//     */
//    @PreAuthorize("@ss.hasPermi('system:Table:remove')")
//    @Log(title = "商品车台账", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{mcvIds}")
//    public AjaxResult remove(@PathVariable Long[] mcvIds) {
//        return toAjax(productionTableService.deleteProductionTableByMcvIds(mcvIds));
//    }


}




