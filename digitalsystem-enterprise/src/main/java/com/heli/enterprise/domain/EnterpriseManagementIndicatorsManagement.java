package com.heli.enterprise.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 十一项管理指标
 * 对象 Enterprise_Management_Indicators_Management
 *
 * @author hong
 * @date 2024-09-13
 */
@Data
public class EnterpriseManagementIndicatorsManagement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String emId;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * SD 销售订单有效性考核得分
     */
    @Excel(name = "SD 销售订单有效性考核得分")
    private BigDecimal sdSalesordervalidityscore;

    /**
     * SD 销售订单有效性考核
     */
    @Excel(name = "SD 销售订单有效性考核")
    private BigDecimal sdSalesordervalidity;

    /**
     * PP 手工创建生产订单得分
     */
    @Excel(name = "PP 手工创建生产订单得分")
    private BigDecimal ppManualpocreationratioscore;

    /**
     * PP 手工创建生产订单比例
     */
    @Excel(name = "PP 手工创建生产订单比例")
    private BigDecimal ppManualpocreationratio;

    /**
     * PP 生产订单已收货未报工的得分
     */
    @Excel(name = "PP 生产订单已收货未报工的得分")
    private BigDecimal ppDeliveredunreportedratioscore;

    /**
     * PP 生产订单已收货未报工的比例
     */
    @Excel(name = "PP 生产订单已收货未报工的比例")
    private BigDecimal ppDeliveredunreportedratio;

    /**
     * MES 报工不及时得分
     */
    @Excel(name = "MES 报工不及时得分")
    private BigDecimal mesLateworkreportingscore;

    /**
     * MES 报工不及时率比率
     */
    @Excel(name = "MES 报工不及时率比率")
    private BigDecimal mesLateworkreportingrate;

    /**
     * QM 外检业务不及时得分
     */
    @Excel(name = "QM 外检业务不及时得分")
    private BigDecimal qmExternalinspectiondelayscore;

    /**
     * QM 外检业务不及时率
     */
    @Excel(name = "QM 外检业务不及时率")
    private BigDecimal qmExternalinspectiondelay;

    /**
     * MM 采购订单交货不及时得分
     */
    @Excel(name = "MM 采购订单交货不及时得分")
    private BigDecimal mmPurchaseorderlatedeliveryscore;

    /**
     * MM 采购订单交货不及时的比例
     */
    @Excel(name = "MM 采购订单交货不及时的比例")
    private BigDecimal mmPurchaseorderlatedelivery;

    /**
     * MM 手工创建采购订单得分
     */
    @Excel(name = "MM 手工创建采购订单得分")
    private BigDecimal mmManualpocreationscore;

    /**
     * MM 手工创建采购订单比例
     */
    @Excel(name = "MM 手工创建采购订单比例")
    private BigDecimal mmManualpocreation;

    /**
     * MM 未清采购申请得分
     */
    @Excel(name = "MM 未清采购申请得分")
    private BigDecimal mmUnsettledpurchaserequestsscore;

    /**
     * MM 未清采购申请
     */
    @Excel(name = "MM 未清采购申请")
    private BigDecimal mmUnsettledpurchaserequests;

    /**
     * FICO 月度标准价格与周期单位价格综合差异得分
     */
    @Excel(name = "FICO 月度标准价格与周期单位价格综合差异得分")
    private BigDecimal ficoMonthlystandardpricevariationscore;

    /**
     * FICO 月度标准价格与周期单位价格综合差异率
     */
    @Excel(name = "FICO 月度标准价格与周期单位价格综合差异率")
    private BigDecimal ficoMonthlystandardpricevariation;

    /**
     * 跨月生产订单得分
     */
    @Excel(name = "跨月生产订单得分")
    private BigDecimal crossMonthProductionOrdersScore;

    /**
     * 跨月生产订单比例
     */
    @Excel(name = "跨月生产订单比例")
    private BigDecimal crossMonthProductionOrders;

    /**
     * PM 维修订单完工不及时得分
     */
    @Excel(name = "PM 维修订单完工不及时得分")
    private BigDecimal pmLatemaintenanceordercompletionscore;

    /**
     * PM 维修订单完工不及时率
     */
    @Excel(name = "PM 维修订单完工不及时率")
    private BigDecimal pmLatemaintenanceordercompletion;

}


//package com.heli.enterprise.domain;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import org.apache.commons.lang3.builder.ToStringBuilder;
//import org.apache.commons.lang3.builder.ToStringStyle;
//import com.ruoyi.common.annotation.Excel;
//import com.ruoyi.common.core.domain.BaseEntity;
//
///**
// * 十一项管理指标
// * 对象 Enterprise_Management_Indicators_Management
// *
// * @author hong
// * @date 2024-05-15
// */
//@Data
//public class EnterpriseManagementIndicatorsManagement extends BaseEntity {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * id
//     */
//    private Long emId;
//
//    /**
//     * $column.columnComment
//     */
//    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
//    private Date yearAndMonth;
//
//    /**
//     * SD 销售订单有效性考核
//     */
//    @Excel(name = "SD 销售订单有效性考核")
//    private BigDecimal sdSalesordervalidity;
//
//    /**
//     * PP 手工创建生产订单比例
//     */
//    @Excel(name = "PP 手工创建生产订单比例")
//    private BigDecimal ppManualpocreationratio;
//
//    /**
//     * PP 生产订单已收货未报工的比例
//     */
//    @Excel(name = "PP 生产订单已收货未报工的比例")
//    private BigDecimal ppDeliveredunreportedratio;
//
//    /**
//     * MES 报工不及时率比率
//     */
//    @Excel(name = "MES 报工不及时率比率")
//    private BigDecimal mesLateworkreportingrate;
//
//    /**
//     * QM 外检业务不及时率
//     */
//    @Excel(name = "QM 外检业务不及时率")
//    private BigDecimal qmExternalinspectiondelay;
//
//    /**
//     * MM 采购订单交货不及时的比例
//     */
//    @Excel(name = "MM 采购订单交货不及时的比例")
//    private BigDecimal mmPurchaseorderlatedelivery;
//
//    /**
//     * MM 手工创建采购订单比例
//     */
//    @Excel(name = "MM 手工创建采购订单比例")
//    private BigDecimal mmManualpocreation;
//
//    /**
//     * MM 未清采购申请
//     */
//    @Excel(name = "MM 未清采购申请")
//    private BigDecimal mmUnsettledpurchaserequests;
//
//    /**
//     * FICO 月度标准价格与周期单位价格综合差异率
//     */
//    @Excel(name = "FICO 月度标准价格与周期单位价格综合差异率")
//    private BigDecimal ficoMonthlystandardpricevariation;
//
//    /**
//     * 跨月生产订单比例
//     */
//    @Excel(name = "跨月生产订单比例")
//    private BigDecimal crossMonthProductionOrders;
//
//    /**
//     * PM 维修订单完工不及时率
//     */
//    @Excel(name = "PM 维修订单完工不及时率")
//    private BigDecimal pmLatemaintenanceordercompletion;
//
//
//}
