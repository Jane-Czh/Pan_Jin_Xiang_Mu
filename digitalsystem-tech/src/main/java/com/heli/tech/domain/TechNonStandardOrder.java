package com.heli.tech.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tech_Non_Standard_Order对象 Tech_Non_Standard_Order
 *
 * @author hong
 * @date 2024-08-02
 */
@Data
public class TechNonStandardOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    @ExcelIgnore
    private Long tnId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    @ExcelIgnore
    private Date yearAndMonth;

    /**
     * 序号
     */
    @Excel(name = "序号")
    @ExcelProperty(value = "序号")
    private String tableId;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    @ExcelProperty(value = "订单号")
    private String orderId;

    /**
     * 车型
     */
    @Excel(name = "车型")
    @ExcelProperty(value = "车型")
    private String carModel;

    /**
     * 配置信息
     */
    @Excel(name = "配置信息")
    @ExcelProperty(value = "配置信息")
    private String carConfiguration;

    /**
     * 负责人
     */
    @Excel(name = "负责人")
    @ExcelProperty(value = "负责人")
    private String principal;

    /**
     * 参与人
     */
    @Excel(name = "参与人")
    @ExcelProperty(value = "参与人")
    private String participants;

    /**
     * 接单日期
     */
    @Excel(name = "接单日期")
    @ExcelProperty(value = "接单日期")
    private String pickupDate;

    /**
     * 转单日期
     */
    @Excel(name = "转单日期")
    @ExcelProperty(value = "转单日期")
    private String transferDate;

    /**
     * 累计时长
     */
//    @Excel(name = "累计时长")
    @ExcelProperty(value = "累计时长\n" +
            "h")
    private BigDecimal totalDurationHours;

    /**
     * 累计天数
     */
//    @Excel(name = "累计天数")
    @ExcelProperty(value = "累计天数")
    private BigDecimal totalDurationDays;

    /**
     * 超时说明
     */
    @Excel(name = "超时说明")
    @ExcelProperty(value = "超时说明")
    private String overtimeRemark;

    /**
     * 备注
     */
    @Excel(name = "备注")
    @ExcelProperty(value = "备注")
    private String comments;

//    public void setTnId(Long tnId) {
//        this.tnId = tnId;
//    }
//
//    public Long getTnId() {
//        return tnId;
//    }
//
//    public void setYearAndMonth(Date yearAndMonth) {
//        this.yearAndMonth = yearAndMonth;
//    }
//
//    public Date getYearAndMonth() {
//        return yearAndMonth;
//    }
//
//    public void setTableId(String tableId) {
//        this.tableId = tableId;
//    }
//
//    public String getTableId() {
//        return tableId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setCarModel(String carModel) {
//        this.carModel = carModel;
//    }
//
//    public String getCarModel() {
//        return carModel;
//    }
//
//    public void setCarConfiguration(String carConfiguration) {
//        this.carConfiguration = carConfiguration;
//    }
//
//    public String getCarConfiguration() {
//        return carConfiguration;
//    }
//
//    public void setPrincipal(String principal) {
//        this.principal = principal;
//    }
//
//    public String getPrincipal() {
//        return principal;
//    }
//
//    public void setParticipants(String participants) {
//        this.participants = participants;
//    }
//
//    public String getParticipants() {
//        return participants;
//    }
//
//    public void setPickupDate(String pickupDate) {
//        this.pickupDate = pickupDate;
//    }
//
//    public String getPickupDate() {
//        return pickupDate;
//    }
//
//    public void setTransferDate(String transferDate) {
//        this.transferDate = transferDate;
//    }
//
//    public String getTransferDate() {
//        return transferDate;
//    }
//
//    public void setTotalDurationHours(BigDecimal totalDurationHours) {
//        this.totalDurationHours = totalDurationHours;
//    }
//
//    public BigDecimal getTotalDurationHours() {
//        return totalDurationHours;
//    }
//
//    public void setTotalDurationDays(BigDecimal totalDurationDays) {
//        this.totalDurationDays = totalDurationDays;
//    }
//
//    public BigDecimal getTotalDurationDays() {
//        return totalDurationDays;
//    }
//
//    public void setOvertimeRemark(String overtimeRemark) {
//        this.overtimeRemark = overtimeRemark;
//    }
//
//    public String getOvertimeRemark() {
//        return overtimeRemark;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//                .append("tnId", getTnId())
//                .append("yearAndMonth", getYearAndMonth())
//                .append("tableId", getTableId())
//                .append("orderId", getOrderId())
//                .append("carModel", getCarModel())
//                .append("carConfiguration", getCarConfiguration())
//                .append("principal", getPrincipal())
//                .append("participants", getParticipants())
//                .append("pickupDate", getPickupDate())
//                .append("transferDate", getTransferDate())
//                .append("totalDurationHours", getTotalDurationHours())
//                .append("totalDurationDays", getTotalDurationDays())
//                .append("overtimeRemark", getOvertimeRemark())
//                .append("comments", getComments())
//                .append("createBy", getCreateBy())
//                .append("createTime", getCreateTime())
//                .append("updateBy", getUpdateBy())
//                .append("updateTime", getUpdateTime())
//                .toString();
//    }
}
