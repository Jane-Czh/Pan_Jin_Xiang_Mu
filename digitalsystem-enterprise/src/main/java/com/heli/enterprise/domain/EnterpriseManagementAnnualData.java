package com.heli.enterprise.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [企业管理]指标年度数据对象 Enterprise_Management_Annual_Data
 *
 * @author hong
 * @date 2024-04-27
 */

@Data
public class EnterpriseManagementAnnualData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long emasId;

    /**
     * 年
     */
    @Excel(name = "年")
    private Integer naturalYear;

    /**
     * 工资总额年度值
     */
    @Excel(name = "工资总额年度值")
    private BigDecimal annualTotalSalary;

    /**
     * 日清日结-股份目标值
     */
    @Excel(name = "日清日结-股份目标值")
    private BigDecimal stockTargetValue;

    /**
     * 日清日结-盘锦目标值
     */
    @Excel(name = "日清日结-盘锦目标值")
    private BigDecimal panjinTargetValue;

}
