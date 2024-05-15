package com.heli.supply.service;

import java.io.InputStream;

import com.ruoyi.common.core.domain.R;

/**
 * @author hong
 * @description: 供应科-指标-采购订单Service接口
 * @date 2024-04-28
 */
public interface ISupplyIndicatorsPurchaseOrderTableService {

    /**
     * @description: 计算月度管控采购金额比例
     * @author: hong
     * @date: 2024/4/29 11:04
     **/
    int calculateControlledPurchaseAmountRatio(String username);

    /**
     * @description: 读取供应订单文件，并存入数据库
     * @author: hong
     * @date: 2024/4/29 16:42
     **/
    R<String> readSupplyOrderExcelToDB(String fileName, InputStream inputStream);

}
