package com.heli.safety.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [安全环保]指标填报对象 Safety_EP
 *
 * @author ruoyi
 * @date 2024-04-16
 */
@Data
public class SafetyEp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Safety_EP_ID
     */
    private Long safetyEpId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 当月设备维修总费用
     */
    @Excel(name = "当月设备维修总费用")
    private BigDecimal curEquipmentMaintenanceCost;

    /**
     * 当月设备故障累计停产时间
     */
    @Excel(name = "当月设备故障累计停产时间")
    private BigDecimal curEquipmentFailuresTotaltime;

    /**
     * 当月设备维修替换件成本
     */
    @Excel(name = "当月设备维修替换件成本")
    private BigDecimal curEquipmentReplacementCost;

    /**
     * 重点设备故障率
     */
    @Excel(name = "重点设备故障率")
    private Long keyEquipmentFailureRate;

    /**
     * 主要设备故障总次数
     */
    @Excel(name = "主要设备故障总次数")
    private Integer keyEquipmentTotalFailureCount;

}
