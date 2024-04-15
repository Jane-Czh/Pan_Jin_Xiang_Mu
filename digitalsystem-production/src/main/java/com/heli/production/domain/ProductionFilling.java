package com.heli.production.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: [生产]手动填报指标功能对象 ProductionFilling
 * @author: hong
 * @date: 2024/4/11 20:13
 **/
@Data
public class ProductionFilling extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Production_ID
     */
    private Long productionId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

    /**
     * 当月单台非BOM物料费用
     */
    private BigDecimal curNonBomMaterialCost;

    /**
     * 当月单台低值易耗费用
     */
    private BigDecimal curLowValueConsumables;

    /**
     * 在制物资年化周转天数
     */
    private BigDecimal inventoryTurnoverdays;

    /**
     * 人均生产台数
     */
    private BigDecimal outputPercapitacounts;

    /**
     * 人均产值
     */
    private BigDecimal outputPercapitavalue;

    /**
     * 上线及时率
     */
    private BigDecimal onlineOntimerate;

    /**
     * 一线当月加班时长
     */
    private BigDecimal overtimeFrontlinemonth;

}
