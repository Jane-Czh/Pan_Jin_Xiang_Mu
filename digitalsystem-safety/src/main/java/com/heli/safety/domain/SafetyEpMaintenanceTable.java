package com.heli.safety.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: 维修数据Excel实体
 * @author: hong
 * @date: 2024/4/12 10:11
 * @param:
 * @return:
 **/
@Data
public class SafetyEpMaintenanceTable {
    private static final long serialVersionUID = 1L;

    /**
     * SEM_ID
     */
    private Long semId;

    /**
     * 设备编号
     */
    @ExcelProperty(value = "设备编号")
    private String equipmentNumber;

    /**
     * 设备名称
     */
    @ExcelProperty(value = "设备名称")
    private String equipmentName;

    /**
     * 工单状态
     */
    @ExcelProperty(value = "工单状态")
    private String workOrderStatus;

    /**
     * 问题类型
     */
    @ExcelProperty(value = "问题类型")
    private String problemType;

    /**
     * 维修部门
     */
    @ExcelProperty(value = "维修部门")
    private String maintenanceDepartment;

    /**
     * 申请人
     */
    @ExcelProperty(value = "申请人")
    private String applicant;

    /**
     * 申请部门
     */
    @ExcelProperty(value = "申请部门")
    private String applicationDepartment;

    /**
     * 维修人员
     */
    @ExcelProperty(value = "维修人员")
    private String maintenancePersonnel;

    /**
     * 故障现象
     */
    @ExcelProperty(value = "故障现象")
    private String faultPhenomenon;

    /**
     * 维修分析
     */
    @ExcelProperty(value = "维修分析")
    private String maintenanceAnalysis;

    /**
     * 维修内容
     */
    @ExcelProperty(value = "维修内容")
    private String maintenanceContent;

    /**
     * 维修时长
     */
    @ExcelProperty(value = "维修时长")
    private BigDecimal maintenanceDuration;

    /**
     * 故障时长
     */
    @ExcelProperty(value = "故障时长")
    private BigDecimal faultDuration;

    /**
     * 维修时间
     */
    @ExcelProperty(value = "维修时间")
    private BigDecimal maintenanceTime;

    /**
     * 待件状态
     */
    @ExcelProperty(value = "待件状态")
    private String pendingStatus;

    /**
     * 待件开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelIgnore
    private Date waitingStartTime;

    /**
     * 待件结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelIgnore
    private Date waitingEndTime;

    /**
     * 待件时长
     */
    @ExcelProperty(value = "待件时长")
    private BigDecimal waitingTime;

    /**
     * 维护申请号
     */
    @ExcelProperty(value = "维护申请号")
    private String maintenanceApplicationNumber;

    /**
     * 验收人
     */
    @ExcelProperty(value = "验收人")
    private String acceptancePerson;

    /**
     * 验收结果
     */
    @ExcelProperty(value = "验收结果")
    private String acceptanceResults;

    /**
     * 验收评价
     */
    @ExcelProperty(value = "验收评价")
    private String acceptanceEvaluation;

    /**
     * 累计故障次数
     */
    @ExcelProperty(value = "累计故障次数")
    private Integer accumulatedNumberOfFailures;

    /**
     * 工单发送标记
     */
    @ExcelProperty(value = "工单发送标记")
    private String workOrderSendingFlag;

    /**
     * 工单发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelIgnore
    private Date workOrderSendingTime;

    /**
     * 工单发送结果
     */
    @ExcelProperty(value = "工单发送结果")
    private String workOrderSendingResults;

    /**
     * 是否停机
     */
    @ExcelProperty(value = "是否停机")
    private String whetherToShutdown;

    @ExcelIgnore
    private String createBy;

    @ExcelIgnore
    private Date createTime;

}
