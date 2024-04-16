package com.ruoyi.market.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 售后区域分类对象 Market_After_Sale_Regional_Classification
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public class MarketAfterSaleRegionalClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long masrcId;

    /** 区域 */
    @Excel(name = "区域")
    private String Area;

    /** 区域问题占比 */
    @Excel(name = "区域问题占比")
    private BigDecimal regionalProblemsProportion;

    /** 未处理数目 */
    @Excel(name = "未处理数目")
    private Long unprocessedNmber;

    public void setMasrcId(Long masrcId) 
    {
        this.masrcId = masrcId;
    }

    public Long getMasrcId() 
    {
        return masrcId;
    }
    public void setArea(String Area) 
    {
        this.Area = Area;
    }

    public String getArea() 
    {
        return Area;
    }
    public void setRegionalProblemsProportion(BigDecimal regionalProblemsProportion) 
    {
        this.regionalProblemsProportion = regionalProblemsProportion;
    }

    public BigDecimal getRegionalProblemsProportion() 
    {
        return regionalProblemsProportion;
    }
    public void setUnprocessedNmber(Long unprocessedNmber) 
    {
        this.unprocessedNmber = unprocessedNmber;
    }

    public Long getUnprocessedNmber() 
    {
        return unprocessedNmber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("masrcId", getMasrcId())
            .append("Area", getArea())
            .append("regionalProblemsProportion", getRegionalProblemsProportion())
            .append("unprocessedNmber", getUnprocessedNmber())
            .toString();
    }
}
