package com.heli.enterprise.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 日清日结
 * 对象 Enterprise_Management_Indicators_Daily_Clearing_Settlement
 *
 * @author hong
 * @date 2024-05-15
 */
@Data
public class EnterpriseManagementIndicatorsDailyClearingSettlement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long edId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 销售订单录入不及时比例
     */
    @Excel(name = "销售订单录入不及时比例")
    private BigDecimal orderEntryDelayRatio;

    /**
     * 销售订单不及时发货比例
     */
    @Excel(name = "销售订单不及时发货比例")
    private BigDecimal shipmentDelayRatio;

    /**
     * 生产订单不及时报工比例
     */
    @Excel(name = "生产订单不及时报工比例")
    private BigDecimal productionReportDelayRatio;

    /**
     * 成品检验业务不及时率
     */
    @Excel(name = "成品检验业务不及时率")
    private BigDecimal inspectionDelayRate;

    /**
     * 销售发票过账不及时率
     */
    @Excel(name = "销售发票过账不及时率")
    private BigDecimal invoicePostingDelayRate;

    /**
     * 客户未清账比例
     */
    @Excel(name = "客户未清账比例")
    private BigDecimal unsettledAccountsRatio;

}
