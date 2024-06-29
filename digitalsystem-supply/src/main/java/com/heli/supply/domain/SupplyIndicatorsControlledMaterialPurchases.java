package com.heli.supply.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: 供应-指标-集团管控物资占比对象 Supply_Indicators_Controlled_Material_Purchases
 * @author hong
 * @date 2024-04-29
 */
@Data
public class SupplyIndicatorsControlledMaterialPurchases extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long scpId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 当月采购总金额
     */
    @Excel(name = "当月采购总金额")
    private BigDecimal totalPurchaseAmount;

    /**
     * 当月管控物资采购金额
     */
    @Excel(name = "当月管控物资采购金额")
    private BigDecimal controlledMaterialPurchases;

    /**
     * 比例
     */
    @Excel(name = "比例")
    private BigDecimal controlledPurchaseAmountRatio;

}
