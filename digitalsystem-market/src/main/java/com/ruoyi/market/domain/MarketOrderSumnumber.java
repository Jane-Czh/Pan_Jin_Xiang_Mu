package com.ruoyi.market.domain;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 Market_order_sumNumber
 * 
 * @author ruoyi
 * @date 2024-08-16
 */
@Data
public class MarketOrderSumnumber extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** ID */
    private Long id;

    /** 地区名称 */
    @ExcelProperty(index = 0)
    private String marketLedger;

    /** 订单总数 */
    @ExcelProperty(index = 1)
    private Long marketOrderSumnumber;

    /** 统计时间 */
    @ExcelProperty(index = 2)
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date marketTime;

    /** 创建时间 */
    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date creatTime;

    /** 创建人 */
    @ExcelProperty("创建人")
    private String creatPeople;
    /** $column.columnComment */
//    private Long id;
//
//    /** 地区名称 */
//    @Excel(name = "地区名称")
//    private String marketLedger;
//
//    /** 订单总数 */
//    @Excel(name = "订单总数")
//    private Long marketOrderSumnumber;
//
//    /** 统计时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "统计时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date marketTime;
//
//    /** 创建时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
//    private Date creatTime;
//
//    /** 创建人 */
//    @Excel(name = "创建人")
//    private String creatPeople;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMarketLedger(String marketLedger) 
    {
        this.marketLedger = marketLedger;
    }

    public String getMarketLedger() 
    {
        return marketLedger;
    }
    public void setMarketOrderSumnumber(Long marketOrderSumnumber) 
    {
        this.marketOrderSumnumber = marketOrderSumnumber;
    }

    public Long getMarketOrderSumnumber() 
    {
        return marketOrderSumnumber;
    }
    public void setMarketTime(Date marketTime) 
    {
        this.marketTime = marketTime;
    }

    public Date getMarketTime() 
    {
        return marketTime;
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
            .append("marketLedger", getMarketLedger())
            .append("marketOrderSumnumber", getMarketOrderSumnumber())
            .append("marketTime", getMarketTime())
            .append("creatTime", getCreatTime())
            .append("creatPeople", getCreatPeople())
            .toString();
    }
}
