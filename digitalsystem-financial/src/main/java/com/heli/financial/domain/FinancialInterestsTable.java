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
    private BigDecimal MainRevenue;

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
    private BigDecimal NetProfit;

    /**
     * 管理费用
     */
    @RecordTemplate(rowNo = 33, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "管理费用")
    private BigDecimal ManagementExpense;

    /**
     * 研发费用
     */
    @RecordTemplate(rowNo = 87, columnNo = 1, comment = FillCommentEnum.EMPTY, name = "研发费用")
    private BigDecimal rdExpense;

    public void setFiId(Long fiId) {
        this.fiId = fiId;
    }

    public Long getFiId() {
        return fiId;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearAndMonth = yearMonth;
    }

    public Date getYearMonth() {
        return yearAndMonth;
    }

    public void setInternalMainRevenue(BigDecimal internalMainRevenue) {
        this.internalMainRevenue = internalMainRevenue;
    }

    public BigDecimal getInternalMainRevenue() {
        return internalMainRevenue;
    }

    public void setExternalMainRevenue(BigDecimal externalMainRevenue) {
        this.externalMainRevenue = externalMainRevenue;
    }

    public BigDecimal getExternalMainRevenue() {
        return externalMainRevenue;
    }

    public void setMainRevenue(BigDecimal MainRevenue) {
        this.MainRevenue = MainRevenue;
    }

    public BigDecimal getMainRevenue() {
        return MainRevenue;
    }

    public void setCogsProductSalesSd(BigDecimal cogsProductSalesSd) {
        this.cogsProductSalesSd = cogsProductSalesSd;
    }

    public BigDecimal getCogsProductSalesSd() {
        return cogsProductSalesSd;
    }

    public void setCogsFreight(BigDecimal cogsFreight) {
        this.cogsFreight = cogsFreight;
    }

    public BigDecimal getCogsFreight() {
        return cogsFreight;
    }

    public void setCogsVariation(BigDecimal cogsVariation) {
        this.cogsVariation = cogsVariation;
    }

    public BigDecimal getCogsVariation() {
        return cogsVariation;
    }

    public void setCOGS(BigDecimal COGS) {
        this.COGS = COGS;
    }

    public BigDecimal getCOGS() {
        return COGS;
    }

    public void setNetProfit(BigDecimal NetProfit) {
        this.NetProfit = NetProfit;
    }

    public BigDecimal getNetProfit() {
        return NetProfit;
    }

    public void setManagementExpense(BigDecimal ManagementExpense) {
        this.ManagementExpense = ManagementExpense;
    }

    public BigDecimal getManagementExpense() {
        return ManagementExpense;
    }

    public void setRdExpense(BigDecimal rdExpense) {
        this.rdExpense = rdExpense;
    }

    public BigDecimal getRdExpense() {
        return rdExpense;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fiId", getFiId())
                .append("createdBy", getCreatedBy())
                .append("createdTime", getCreatedTime())
                .append("yearAndMonth", getYearMonth())
                .append("internalMainRevenue", getInternalMainRevenue())
                .append("externalMainRevenue", getExternalMainRevenue())
                .append("MainRevenue", getMainRevenue())
                .append("cogsProductSalesSd", getCogsProductSalesSd())
                .append("cogsFreight", getCogsFreight())
                .append("cogsVariation", getCogsVariation())
                .append("COGS", getCOGS())
                .append("NetProfit", getNetProfit())
                .append("ManagementExpense", getManagementExpense())
                .append("rdExpense", getRdExpense())
                .toString();
    }
}
