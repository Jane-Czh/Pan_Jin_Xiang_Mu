package com.heli.supply.controller;

import java.io.InputStream;

import com.heli.supply.service.ISupplyIndicatorsCollectibleMaterialsTableService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.heli.supply.service.ISupplyIndicatorsPurchaseOrderTableService;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 采购订单表导入并计算，集采物资字典表导入
 * @author: hong
 * @date: 2024-04-28
 **/
@RestController
@RequestMapping("/supply/data")
public class SupplyIndicatorsPurchaseOrderTableController extends BaseController {
    @Autowired
    private ISupplyIndicatorsPurchaseOrderTableService supplyIndicatorsPurchaseOrderTableService;
    @Autowired
    private ISupplyIndicatorsCollectibleMaterialsTableService supplyIndicatorsCollectibleMaterialsTableService;

    private static final Logger log = LoggerFactory.getLogger(SupplyIndicatorsPurchaseOrderTableController.class);

    /**
     * @description: 上传采购订单汇总excel，读取采购订单sheet，并存入数据库，计算占比存入表中
     * @author: hong
     * @date: 2024/4/28 14:25
     **/
    @PreAuthorize("@ss.hasPermi('supply:order:read')")
    @PostMapping("/readPurchaseOrderTable")
    @Transactional
    public R<String> readPurchaseOrderTable(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {

        try (InputStream inputStream = multipartFile.getInputStream()) {
            //将采购订单存入数据库
            supplyIndicatorsPurchaseOrderTableService.readSupplyOrderExcelToDB(multipartFile.getOriginalFilename(), inputStream);
            //计算
            supplyIndicatorsPurchaseOrderTableService.calculateControlledPurchaseAmountRatio(getUsername());
            return R.ok();
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }


    /**
     * @description: 上传字典-采购订单汇总excel，读取集采物料sheet，并存入数据库
     * @author: hong
     * @date: 2024/4/28 14:25
     **/
    @PreAuthorize("@ss.hasPermi('supply:dictionary:read')")
    @PostMapping("/readCollectibleMaterialsTable")
    @Transactional
    public R<String> readCollectibleMaterialsTable(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            return supplyIndicatorsCollectibleMaterialsTableService.readCollectibleMaterialsTableToDB(multipartFile.getOriginalFilename(), inputStream);
        } catch (Exception e) {
            log.error("读取 " + multipartFile.getName() + " 文件失败, 原因: {}", e.getMessage());
            throw new ServiceException("读取 " + multipartFile.getName() + " 文件失败");
        }
    }

}
