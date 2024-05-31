package com.heli.enterprise.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工资-部分字段对象 Enterprise_Management_Salary_Table
 *
 * @author hong
 * @date 2024-05-07
 */
@Data
@ExcelIgnoreUnannotated
public class EnterpriseManagementSalaryTable extends BaseEntity{
//    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long esId;

    /** 薪资核算年月 */
    @ExcelProperty(value = "薪资核算年月")
    @DateTimeFormat("yyyyMM")
    private Date salaryCalculationYearMonth;

    /**
     * 员工编号
     */
    @ExcelProperty(value = "员工编号")
    private String employeeId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 职位
     */
    @ExcelProperty(value = "职位")
    private String position;

    /**
     * 三级组织
     */
    @ExcelProperty(value = "三级组织")
    private String department;

    /**
     * 员工组
     */
    @ExcelProperty(value = "员工组")
    private String groupCode;

    /**
     * 员工子组
     */
    @ExcelProperty(value = "员工子组")
    private String subgroupCode;

    /**
     * 加班合计
     */
    @ExcelProperty(value = "加班合计")
    private BigDecimal overtimeSalary;

    /**
     * 工资合计
     */
    @ExcelProperty(value = "工资合计")
    private BigDecimal totalSalary;

    /**
     * 将年月-员工编号拼接成唯一索引
     */
    @ExcelIgnore
    private String salaryTableIndex;

}
