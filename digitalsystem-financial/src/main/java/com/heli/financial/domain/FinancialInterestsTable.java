package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.RecordTemplate;
import com.ruoyi.common.enums.FillCommentEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 财务-利润对象 Financial_Interests_table
 *
 * @author hong
 * @date 2024-03-31
 */
public class FinancialInterestsTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id(主键)
     */
    private Long fiId;

    /**
     * 导入人
     */
    private String createdBy;

    /**
     * 导入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 营业收入
     */
    @RecordTemplate(rowNo = 1, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "营业收入")
    private BigDecimal operatingRevenue;

    /**
     * 集团内主营业务收入
     */
    @RecordTemplate(rowNo = 2, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "集团内主营业务收入")
    private BigDecimal internalMainRevenue;

    /**
     * 集团外主营业务收入
     */
    @RecordTemplate(rowNo = 3, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "集团内主营业务收入")
    private BigDecimal externalMainRevenue;

    /**
     * 主营业务收入(上面俩个指标的计算和)
     */
    private BigDecimal mainRevenue;

    /**
     * 主营业务成本-产品销售SD
     */
    @RecordTemplate(rowNo = 9, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "主营业务成本-产品销售SD")
    private BigDecimal cogsProductSalesSd;

    /**
     * 主营业务成本-运费
     */
    @RecordTemplate(rowNo = 10, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "主营业务成本-运费")
    private BigDecimal cogsFreight;

    /**
     * 主营业务成本-运费
     */
    @RecordTemplate(rowNo = 11, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "主营业务成本-运费")
    private BigDecimal cogsVariation;

    /**
     * 主营业务成本（上面三个指标的计算和）
     */
    private BigDecimal COGS;

    /**
     * 净利润
     */
    @RecordTemplate(rowNo = 145, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "净利润")
    private BigDecimal netProfit;

    /**
     * 管理费用
     */
    @RecordTemplate(rowNo = 33, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "管理费用")
    private BigDecimal managementExpense;

    /**
     * 研发费用
     */
    @RecordTemplate(rowNo = 87, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "研发费用")
    private BigDecimal rdExpense;

    public Long getFiId() {
        return fiId;
    }

    public void setFiId(Long fiId) {
        this.fiId = fiId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public BigDecimal getOperatingRevenue() {
        return operatingRevenue;
    }

    public void setOperatingRevenue(BigDecimal operatingRevenue) {
        this.operatingRevenue = operatingRevenue;
    }

    public BigDecimal getInternalMainRevenue() {
        return internalMainRevenue;
    }

    public void setInternalMainRevenue(BigDecimal internalMainRevenue) {
        this.internalMainRevenue = internalMainRevenue;
    }

    public BigDecimal getExternalMainRevenue() {
        return externalMainRevenue;
    }

    public void setExternalMainRevenue(BigDecimal externalMainRevenue) {
        this.externalMainRevenue = externalMainRevenue;
    }

    public BigDecimal getMainRevenue() {
        return mainRevenue;
    }

    public void setMainRevenue(BigDecimal mainRevenue) {
        this.mainRevenue = mainRevenue;
    }

    public BigDecimal getCogsProductSalesSd() {
        return cogsProductSalesSd;
    }

    public void setCogsProductSalesSd(BigDecimal cogsProductSalesSd) {
        this.cogsProductSalesSd = cogsProductSalesSd;
    }

    public BigDecimal getCogsFreight() {
        return cogsFreight;
    }

    public void setCogsFreight(BigDecimal cogsFreight) {
        this.cogsFreight = cogsFreight;
    }

    public BigDecimal getCogsVariation() {
        return cogsVariation;
    }

    public void setCogsVariation(BigDecimal cogsVariation) {
        this.cogsVariation = cogsVariation;
    }

    public BigDecimal getCOGS() {
        return COGS;
    }

    public void setCOGS(BigDecimal COGS) {
        this.COGS = COGS;
    }

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    public BigDecimal getManagementExpense() {
        return managementExpense;
    }

    public void setManagementExpense(BigDecimal managementExpense) {
        this.managementExpense = managementExpense;
    }

    public BigDecimal getRdExpense() {
        return rdExpense;
    }

    public void setRdExpense(BigDecimal rdExpense) {
        this.rdExpense = rdExpense;
    }

    @Override
    public String toString() {
        return "FinancialInterestsTable{" +
                "fiId=" + fiId +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", yearAndMonth=" + yearAndMonth +
                ", operatingRevenue=" + operatingRevenue +
                ", internalMainRevenue=" + internalMainRevenue +
                ", externalMainRevenue=" + externalMainRevenue +
                ", mainRevenue=" + mainRevenue +
                ", cogsProductSalesSd=" + cogsProductSalesSd +
                ", cogsFreight=" + cogsFreight +
                ", cogsVariation=" + cogsVariation +
                ", COGS=" + COGS +
                ", netProfit=" + netProfit +
                ", managementExpense=" + managementExpense +
                ", rdExpense=" + rdExpense +
                '}';
    }
}
