package com.heli.supply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 月供货比例统计对象 Supply_Ratio_Formula_Table
 * 
 * @author Teandron
 * @date 2024-04-24
 */
public class SupplyRatioFormulaTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long srftId;

    /** 物料号 */
    @Excel(name = "物料号")
    private String materialNumber;

    /** 物料类别 */
    @Excel(name = "物料类别")
    private String materialClass;

    /** 供应商代码 */
    @Excel(name = "供应商代码")
    private String supplierCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String supplierName;

    /** 供货比例 */
    @Excel(name = "供货比例")
    private String supplyProportion;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String paymentMethod;

    /** 比例统计方式 */
    @Excel(name = "比例统计方式")
    private String proportionStatisticalMethod;

    /** 是否入驻第三方 */
    @Excel(name = "是否入驻第三方")
    private String thirdParty;

    /** 备注 */
    @Excel(name = "备注")
    private String Remarks;

    /** 实际比例 */
    @Excel(name = "实际比例")
    private String actualProportion;

    /** 差异 */
    @Excel(name = "差异")
    private String Difference;

    public void setSrftId(Long srftId) 
    {
        this.srftId = srftId;
    }

    public Long getSrftId() 
    {
        return srftId;
    }
    public void setMaterialNumber(String materialNumber) 
    {
        this.materialNumber = materialNumber;
    }

    public String getMaterialNumber() 
    {
        return materialNumber;
    }
    public void setMaterialClass(String materialClass) 
    {
        this.materialClass = materialClass;
    }

    public String getMaterialClass() 
    {
        return materialClass;
    }
    public void setSupplierCode(String supplierCode) 
    {
        this.supplierCode = supplierCode;
    }

    public String getSupplierCode() 
    {
        return supplierCode;
    }
    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }
    public void setSupplyProportion(String supplyProportion) 
    {
        this.supplyProportion = supplyProportion;
    }

    public String getSupplyProportion() 
    {
        return supplyProportion;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setProportionStatisticalMethod(String proportionStatisticalMethod) 
    {
        this.proportionStatisticalMethod = proportionStatisticalMethod;
    }

    public String getProportionStatisticalMethod() 
    {
        return proportionStatisticalMethod;
    }
    public void setThirdParty(String thirdParty) 
    {
        this.thirdParty = thirdParty;
    }

    public String getThirdParty() 
    {
        return thirdParty;
    }
    public void setRemarks(String Remarks) 
    {
        this.Remarks = Remarks;
    }

    public String getRemarks() 
    {
        return Remarks;
    }
    public void setActualProportion(String actualProportion) 
    {
        this.actualProportion = actualProportion;
    }

    public String getActualProportion() 
    {
        return actualProportion;
    }
    public void setDifference(String Difference) 
    {
        this.Difference = Difference;
    }

    public String getDifference() 
    {
        return Difference;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("srftId", getSrftId())
            .append("materialNumber", getMaterialNumber())
            .append("materialClass", getMaterialClass())
            .append("supplierCode", getSupplierCode())
            .append("supplierName", getSupplierName())
            .append("supplyProportion", getSupplyProportion())
            .append("paymentMethod", getPaymentMethod())
            .append("proportionStatisticalMethod", getProportionStatisticalMethod())
            .append("thirdParty", getThirdParty())
            .append("Remarks", getRemarks())
            .append("actualProportion", getActualProportion())
            .append("Difference", getDifference())
            .toString();
    }
}
