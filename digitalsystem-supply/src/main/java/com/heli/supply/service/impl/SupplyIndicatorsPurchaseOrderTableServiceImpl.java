package com.heli.supply.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.supply.domain.SupplyIndicatorsControlledMaterialPurchases;
import com.heli.supply.listener.SupplyOrderTableListener;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.DisplayEntity;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.supply.mapper.SupplyIndicatorsPurchaseOrderTableMapper;
import com.heli.supply.domain.SupplyIndicatorsPurchaseOrderTable;
import com.heli.supply.service.ISupplyIndicatorsPurchaseOrderTableService;

/**
 * 供应科-指标-采购订单Service业务层处理
 *
 * @author hong
 * @date 2024-04-28
 */
@Service
public class SupplyIndicatorsPurchaseOrderTableServiceImpl implements ISupplyIndicatorsPurchaseOrderTableService {
    @Autowired
    private SupplyIndicatorsPurchaseOrderTableMapper supplyIndicatorsPurchaseOrderTableMapper;
    @Autowired
    private SupplyIndicatorsControlledMaterialPurchasesServiceImpl supplyIndicatorsControlledMaterialPurchasesService;

    private static final Logger log = LoggerFactory.getLogger(SupplyIndicatorsPurchaseOrderTableServiceImpl.class);

    /**
     * @description: 计算集中采购比例
     * @author: hong
     * @date: 2024/4/29 23:02
     **/
    public int calculateControlledPurchaseAmountRatio(String username){
//        查询月度管控采购金额、查询月度采购总金额
        List<DisplayEntity> controlledMaterialPurchases = supplyIndicatorsPurchaseOrderTableMapper.selectMonthlyControlledMaterialPurchases();
        List<DisplayEntity> totalPurchaseAmount = supplyIndicatorsPurchaseOrderTableMapper.selectMonthlyTotalPurchaseAmount();

        //计算并存入List<SupplyIndicatorsControlledMaterialPurchases>
        List<SupplyIndicatorsControlledMaterialPurchases> controlledMaterialPurchasesList = new ArrayList<SupplyIndicatorsControlledMaterialPurchases>();
        for (int i = 0; i < controlledMaterialPurchases.size(); i++) {
            SupplyIndicatorsControlledMaterialPurchases cmp = new SupplyIndicatorsControlledMaterialPurchases();
            cmp.setTotalPurchaseAmount(totalPurchaseAmount.get(i).getData());
            cmp.setControlledMaterialPurchases(controlledMaterialPurchases.get(i).getData());
            cmp.setControlledPurchaseAmountRatio(controlledMaterialPurchases.get(i).getData().multiply(BigDecimal.valueOf(100)).divide(totalPurchaseAmount.get(i).getData(),2, RoundingMode.HALF_UP));
            cmp.setYearAndMonth(totalPurchaseAmount.get(i).getYearAndMonth());
            cmp.setCreateBy(username);
            cmp.setCreateTime(DateUtils.getNowDate());
            controlledMaterialPurchasesList.add(cmp);
        }

        log.info(controlledMaterialPurchasesList.toString());
        //安全插入、更新表格
        return supplyIndicatorsControlledMaterialPurchasesService.batchInsertOrUpdateControlledMaterialPurchases(controlledMaterialPurchasesList);

    }


    @Override
    public R<String> readSupplyOrderExcelToDB(String fileName, InputStream inputStream) {
        try {

            // 清空表格
            supplyIndicatorsPurchaseOrderTableMapper.truncatePurchaseOrderTable();
            // 读取文件内容
            EasyExcel.read(inputStream, SupplyIndicatorsPurchaseOrderTable.class, new SupplyOrderTableListener(supplyIndicatorsPurchaseOrderTableMapper)).sheet().doRead();

            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要上传采购订单汇总表,当前上传的文件为：" + fileName);
        }

    }

}
