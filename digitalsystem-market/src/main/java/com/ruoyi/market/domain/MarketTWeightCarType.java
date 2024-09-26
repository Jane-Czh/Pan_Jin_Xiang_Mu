package com.ruoyi.market.domain;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 market_t_weight_car_type
 * 
 * @author ruoyi
 * @date 2024-09-15
 */
@Data
public class MarketTWeightCarType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 车型系列   H2系列；K2系列
 */
//    @Excel(name = "车型系列   H2系列；K2系列 ")
    @ExcelProperty(index = 0)
    private String series;



    /** 车型  eg:CPC30-Q26H2;CPC20-WX6K2 */
//    @Excel(name = "车型  eg:CPC30-Q26H2;CPC20-WX6K2")
    @ExcelProperty(index = 2)
    private String carType;

    /** 引擎，发动机/主要配置 */
//    @Excel(name = "引擎，发动机/主要配置")

    @ExcelProperty(index = 3)
    private String Engines;

    /** 标配 */
//    @Excel(name = "标配")

    @ExcelProperty(index = 4)
    private String configure;

    /** 吨位（0表示小吨位，1表示大吨位，2表示电动车，3表示外销车型） */
//    @Excel(name = "吨位", readConverterExp = "0=表示小吨位，1表示大吨位，2表示电动车，3表示外销车型")
//    @ExcelProperty(value = "吨位大小")
    private Long tWeight;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatPeople;

    /** 吨位描述 eg：3-3.5t
经济型；
2-3.8t
低温启动 */
    @Excel(name = "吨位描述 eg：3-3.5t 经济型； 2-3.8t 低温启动")
    @ExcelProperty(index = 1)
    private String tWeightDescribe;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setSeries(String series) 
    {
        this.series = series;
    }

    public String getSeries() 
    {
        return series;
    }
    public void settWeight(Long tWeight) 
    {
        this.tWeight = tWeight;
    }

    public Long gettWeight() 
    {
        return tWeight;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setEngines(String Engines) 
    {
        this.Engines = Engines;
    }

    public String getEngines() 
    {
        return Engines;
    }
    public void setConfigure(String configure) 
    {
        this.configure = configure;
    }

    public String getConfigure() 
    {
        return configure;
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
    public void settWeightDescribe(String tWeightDescribe) 
    {
        this.tWeightDescribe = tWeightDescribe;
    }

    public String gettWeightDescribe() 
    {
        return tWeightDescribe;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("series", getSeries())
            .append("tWeight", gettWeight())
            .append("carType", getCarType())
            .append("Engines", getEngines())
            .append("configure", getConfigure())
            .append("creatTime", getCreatTime())
            .append("creatPeople", getCreatPeople())
            .append("tWeightDescribe", gettWeightDescribe())
            .toString();
    }
}
