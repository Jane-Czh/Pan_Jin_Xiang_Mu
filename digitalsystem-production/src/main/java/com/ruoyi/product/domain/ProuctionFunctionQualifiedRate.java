package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自制件合格率对象 Prouction_function_Qualified_rate
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public class ProuctionFunctionQualifiedRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long pqrId;

    /** 合格率 */
    @Excel(name = "合格率")
    private Long qualificationRate;

    /** 合格数量 */
    @Excel(name = "合格数量")
    private Integer qualifiedNumber;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Integer productionNumber;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    /** 不合格数量 */
    @Excel(name = "不合格数量")
    private Integer disqualifiedNumber;

    public void setPqrId(Long pqrId) 
    {
        this.pqrId = pqrId;
    }

    public Long getPqrId() 
    {
        return pqrId;
    }
    public void setQualificationRate(Long qualificationRate) 
    {
        this.qualificationRate = qualificationRate;
    }

    public Long getQualificationRate() 
    {
        return qualificationRate;
    }
    public void setQualifiedNumber(Integer qualifiedNumber) 
    {
        this.qualifiedNumber = qualifiedNumber;
    }

    public Integer getQualifiedNumber() 
    {
        return qualifiedNumber;
    }
    public void setProductionNumber(Integer productionNumber) 
    {
        this.productionNumber = productionNumber;
    }

    public Integer getProductionNumber() 
    {
        return productionNumber;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setDisqualifiedNumber(Integer disqualifiedNumber) 
    {
        this.disqualifiedNumber = disqualifiedNumber;
    }

    public Integer getDisqualifiedNumber() 
    {
        return disqualifiedNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pqrId", getPqrId())
            .append("qualificationRate", getQualificationRate())
            .append("qualifiedNumber", getQualifiedNumber())
            .append("productionNumber", getProductionNumber())
            .append("date", getDate())
            .append("model", getModel())
            .append("disqualifiedNumber", getDisqualifiedNumber())
            .toString();
    }
}
