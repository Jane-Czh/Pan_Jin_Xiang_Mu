package com.heli.supply.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购订单导入对象 Supply_Purchaseorder_table
 * 
 * @author Teandron
 * @date 2024-07-30
 */
public class SupplyPurchaseorderTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long spId;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialNumber;

    /** 采购数量 */
    @Excel(name = "采购数量")
    private Long purchaseQuantity;

    /** 净价 */
    @Excel(name = "净价")
    private BigDecimal netPrice;

    /** 订单净值 */
    @Excel(name = "订单净值")
    private BigDecimal netValueOrder;

    /** 凭证日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "凭证日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date documentDate;

    /** 供应商 */
    @Excel(name = "供应商")
    private String Supplier;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    public void setSpId(Long spId) 
    {
        this.spId = spId;
    }

    public Long getSpId() 
    {
        return spId;
    }
    public void setMaterialNumber(String materialNumber) 
    {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() 
    {
        return materialNumber;
    }
    public void setPurchaseQuantity(Long purchaseQuantity) 
    {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Long getPurchaseQuantity() 
    {
        return purchaseQuantity;
    }
    public void setNetPrice(BigDecimal netPrice) 
    {
        this.netPrice = netPrice;
    }

    public BigDecimal getNetPrice() 
    {
        return netPrice;
    }
    public void setNetValueOrder(BigDecimal netValueOrder) 
    {
        this.netValueOrder = netValueOrder;
    }

    public BigDecimal getNetValueOrder() 
    {
        return netValueOrder;
    }
    public void setDocumentDate(Date documentDate) 
    {
        this.documentDate = documentDate;
    }

    public Date getDocumentDate() 
    {
        return documentDate;
    }
    public void setSupplier(String Supplier) 
    {
        this.Supplier = Supplier;
    }

    public String getSupplier() 
    {
        return Supplier;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("spId", getSpId())
            .append("materialNumber", getMaterialNumber())
            .append("purchaseQuantity", getPurchaseQuantity())
            .append("netPrice", getNetPrice())
            .append("netValueOrder", getNetValueOrder())
            .append("documentDate", getDocumentDate())
            .append("Supplier", getSupplier())
            .append("supplierName", getSupplierName())
            .toString();
    }
}
