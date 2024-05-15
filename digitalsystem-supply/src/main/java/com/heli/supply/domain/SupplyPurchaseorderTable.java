package com.heli.supply.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单导入对象 Supply_PurchaseOrder_table
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public class SupplyPurchaseorderTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long spId;

    /** 导入人 */
    @Excel(name = "导入人")
    private String createdBy;

    /** 导入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "导入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 凭证日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "凭证日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date documentDate;

    /** 采购凭证 */
    @Excel(name = "采购凭证")
    private String purchasingDocuments;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialNumber;

    /** 是否为集采(0/1) */
    @Excel(name = "是否为集采(0/1)")
    private Integer collectiveProcurement;

    /** 短文本 */
    @Excel(name = "短文本")
    private String ShortText;

    /** 单位 */
    @Excel(name = "单位")
    private String Unit;

    /** 数量 */
    @Excel(name = "数量")
    private Long Quantity;

    /** 订单净值 */
    @Excel(name = "订单净值")
    private BigDecimal orderNetvalue;

    /** 供应商 */
    @Excel(name = "供应商")
    private String Supplier;

    public void setSpId(Long spId) 
    {
        this.spId = spId;
    }

    public Long getSpId() 
    {
        return spId;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setDocumentDate(Date documentDate) 
    {
        this.documentDate = documentDate;
    }

    public Date getDocumentDate() 
    {
        return documentDate;
    }
    public void setPurchasingDocuments(String purchasingDocuments) 
    {
        this.purchasingDocuments = purchasingDocuments;
    }

    public String getPurchasingDocuments() 
    {
        return purchasingDocuments;
    }
    public void setMaterialNumber(String materialNumber) 
    {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() 
    {
        return materialNumber;
    }
    public void setCollectiveProcurement(Integer collectiveProcurement) 
    {
        this.collectiveProcurement = collectiveProcurement;
    }

    public Integer getCollectiveProcurement() 
    {
        return collectiveProcurement;
    }
    public void setShortText(String ShortText) 
    {
        this.ShortText = ShortText;
    }

    public String getShortText() 
    {
        return ShortText;
    }
    public void setUnit(String Unit) 
    {
        this.Unit = Unit;
    }

    public String getUnit() 
    {
        return Unit;
    }
    public void setQuantity(Long Quantity) 
    {
        this.Quantity = Quantity;
    }

    public Long getQuantity() 
    {
        return Quantity;
    }
    public void setOrderNetvalue(BigDecimal orderNetvalue) 
    {
        this.orderNetvalue = orderNetvalue;
    }

    public BigDecimal getOrderNetvalue() 
    {
        return orderNetvalue;
    }
    public void setSupplier(String Supplier) 
    {
        this.Supplier = Supplier;
    }

    public String getSupplier() 
    {
        return Supplier;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("spId", getSpId())
            .append("createdBy", getCreatedBy())
            .append("createdTime", getCreatedTime())
            .append("documentDate", getDocumentDate())
            .append("purchasingDocuments", getPurchasingDocuments())
            .append("materialNumber", getMaterialNumber())
            .append("collectiveProcurement", getCollectiveProcurement())
            .append("ShortText", getShortText())
            .append("Unit", getUnit())
            .append("Quantity", getQuantity())
            .append("orderNetvalue", getOrderNetvalue())
            .append("Supplier", getSupplier())
            .toString();
    }
}
