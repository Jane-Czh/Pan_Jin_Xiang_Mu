package com.heli.enterprise.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [企业管理]指标月度数据对象 Enterprise_Management_Monthly_Data
 *
 * @author hong
 * @date 2024-04-27
 */

@Data
public class EnterpriseManagementEmployeesData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * EM_ID
     */
    private Long emId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM")
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



}
