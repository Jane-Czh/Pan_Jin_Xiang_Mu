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
 * [企业管理]指标工资数据对象 Enterprise_Management_Indicators_Salary_Data
 *
 * @author hong
 * @date 2024-05-08
 */
@Data
public class EnterpriseManagementIndicatorsSalaryData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long esId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 一线从业人数
     */
    @Excel(name = "一线从业人数")
    private Long employeesNumber;

    /**
     * 公司平均从业人数（月度）
     */
    @Excel(name = "公司平均从业人数", readConverterExp = "月度")
    private Long employeesAvgMonthlyNumber;

    /**
     * 公司平均从业人数（年度）
     */
    @Excel(name = "公司平均从业人数", readConverterExp = "年度")
    private Long employeesAvgAnnualNumber;

    /**
     * 工资总额月度值
     */
    @Excel(name = "工资总额月度值")
    private BigDecimal totalMonthlySalary;

    /**
     * 工资总额月度占比
     */
    @Excel(name = "工资总额月度占比")
    private BigDecimal monthlyAalaryRatio;

    /**
     * 工资总额年度占比
     */
    @Excel(name = "工资总额年度占比")
    private BigDecimal annualSalaryRatio;

    /**
     * 累计人均收入
     */
    @Excel(name = "累计人均收入")
    private BigDecimal cumulativeAverageIncome;

    /**
     * 月度累计生产人均收入
     */
    @Excel(name = "月度累计生产人均收入")
    private BigDecimal monthlyProductionAVGIncome;

    /**
     * 月度累计职能人均收入
     */
    @Excel(name = "月度累计职能人均收入")
    private BigDecimal monthlyFunctionalAVGIncome;

    /**
     * 职能部门人均加班费用
     */
    @Excel(name = "职能部门人均加班费用")
    private BigDecimal functionalDeptOvertimeCost;

}
