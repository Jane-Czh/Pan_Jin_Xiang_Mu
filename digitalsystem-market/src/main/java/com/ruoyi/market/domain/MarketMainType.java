package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 主要车型字典表对象 market_main_type
 * 
 * @author Teandron
 * @date 2024-09-13
 */
public class MarketMainType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 主要车型 */
    @Excel(name = "主要车型")
    private String mainCarType;

    /** 车型  eg：CPC30-Q26H2 */
    @Excel(name = "车型  eg：CPC30-Q26H2")
    private String carType;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatPeople;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMainCarType(String mainCarType) 
    {
        this.mainCarType = mainCarType;
    }

    public String getMainCarType() 
    {
        return mainCarType;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }
    public void setCreatPeople(String creatPeople) 
    {
        this.creatPeople = creatPeople;
    }

    public String getCreatPeople() 
    {
        return creatPeople;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainCarType", getMainCarType())
            .append("carType", getCarType())
            .append("creatTime", getCreatTime())
            .append("creatPeople", getCreatPeople())
            .toString();
    }
}
