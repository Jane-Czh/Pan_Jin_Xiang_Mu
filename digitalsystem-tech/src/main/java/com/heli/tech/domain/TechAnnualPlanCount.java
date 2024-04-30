package com.heli.tech.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【技术】总计划年初填报对象 Tech_Annual_Plan_Count
 *
 * @author hong
 * @date 2024-04-27
 */
@Data
public class TechAnnualPlanCount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long tapcId;

    /**
     * 自然年
     */
    @Excel(name = "自然年")
    private Integer naturalYear;

    /**
     * 年度计划总数
     */
    @Excel(name = "年度计划总数")
    private Long annualPlancounts;

}
