package com.ruoyi.market.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 市场科销售快报功能对象 Market_function_quick_report
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public class MarketFunctionQuickReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id(主键) */
    private Long mfqrId;

    /** 上月结余订单 */
    @Excel(name = "上月结余订单")
    private Integer lastMonthBalanceOrder;

    /** 销售网点 */
    @Excel(name = "销售网点")
    private String commercialNetworks;

    /** 今日接单 */
    @Excel(name = "今日接单")
    private Integer orderReceivedToday;

    /** 本月累计接单 */
    @Excel(name = "本月累计接单")
    private Integer ordersReceivedThisMonth;

    /** 本年订单累计 */
    @Excel(name = "本年订单累计")
    private Integer orderAccumulationThisYear;

    /** 今日交货台数 */
    @Excel(name = "今日交货台数")
    private Integer deliveryToday;

    /** 本月系统内交货 */
    @Excel(name = "本月系统内交货")
    private Integer deliveryWithinTheSystemThisMonth;

    /** 本年累计系统交货数 */
    @Excel(name = "本年累计系统交货数")
    private Integer cumulativeSystemDeliveriesForTheYear;

    /** 未交货订单 */
    @Excel(name = "未交货订单")
    private Integer unfilledOrders;

    /** 今日实发 */
    @Excel(name = "今日实发")
    private Integer issuedThisDay;

    /** 本月实发 */
    @Excel(name = "本月实发")
    private Integer issuedThisMonth;

    /** 本年累计实发 */
    @Excel(name = "本年累计实发")
    private Integer cumulativeIssuedThisMonth;

    /** 系统交货实物未提 */
    @Excel(name = "系统交货实物未提")
    private Integer physicalDeliveryOfTheSystemWasNotMentioned;

    /** 网点未排订单 */
    @Excel(name = "网点未排订单")
    private Integer theNetworkHasNoOrders;

    /** 上传日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadDate;

    public void setMfqrId(Long mfqrId) 
    {
        this.mfqrId = mfqrId;
    }

    public Long getMfqrId() 
    {
        return mfqrId;
    }
    public void setLastMonthBalanceOrder(Integer lastMonthBalanceOrder) 
    {
        this.lastMonthBalanceOrder = lastMonthBalanceOrder;
    }

    public Integer getLastMonthBalanceOrder() 
    {
        return lastMonthBalanceOrder;
    }
    public void setCommercialNetworks(String commercialNetworks) 
    {
        this.commercialNetworks = commercialNetworks;
    }

    public String getCommercialNetworks() 
    {
        return commercialNetworks;
    }
    public void setOrderReceivedToday(Integer orderReceivedToday) 
    {
        this.orderReceivedToday = orderReceivedToday;
    }

    public Integer getOrderReceivedToday() 
    {
        return orderReceivedToday;
    }
    public void setOrdersReceivedThisMonth(Integer ordersReceivedThisMonth) 
    {
        this.ordersReceivedThisMonth = ordersReceivedThisMonth;
    }

    public Integer getOrdersReceivedThisMonth() 
    {
        return ordersReceivedThisMonth;
    }
    public void setOrderAccumulationThisYear(Integer orderAccumulationThisYear) 
    {
        this.orderAccumulationThisYear = orderAccumulationThisYear;
    }

    public Integer getOrderAccumulationThisYear() 
    {
        return orderAccumulationThisYear;
    }
    public void setDeliveryToday(Integer deliveryToday) 
    {
        this.deliveryToday = deliveryToday;
    }

    public Integer getDeliveryToday() 
    {
        return deliveryToday;
    }
    public void setDeliveryWithinTheSystemThisMonth(Integer deliveryWithinTheSystemThisMonth) 
    {
        this.deliveryWithinTheSystemThisMonth = deliveryWithinTheSystemThisMonth;
    }

    public Integer getDeliveryWithinTheSystemThisMonth() 
    {
        return deliveryWithinTheSystemThisMonth;
    }
    public void setCumulativeSystemDeliveriesForTheYear(Integer cumulativeSystemDeliveriesForTheYear) 
    {
        this.cumulativeSystemDeliveriesForTheYear = cumulativeSystemDeliveriesForTheYear;
    }

    public Integer getCumulativeSystemDeliveriesForTheYear() 
    {
        return cumulativeSystemDeliveriesForTheYear;
    }
    public void setUnfilledOrders(Integer unfilledOrders) 
    {
        this.unfilledOrders = unfilledOrders;
    }

    public Integer getUnfilledOrders() 
    {
        return unfilledOrders;
    }
    public void setIssuedThisDay(Integer issuedThisDay) 
    {
        this.issuedThisDay = issuedThisDay;
    }

    public Integer getIssuedThisDay() 
    {
        return issuedThisDay;
    }
    public void setIssuedThisMonth(Integer issuedThisMonth) 
    {
        this.issuedThisMonth = issuedThisMonth;
    }

    public Integer getIssuedThisMonth() 
    {
        return issuedThisMonth;
    }
    public void setCumulativeIssuedThisMonth(Integer cumulativeIssuedThisMonth) 
    {
        this.cumulativeIssuedThisMonth = cumulativeIssuedThisMonth;
    }

    public Integer getCumulativeIssuedThisMonth() 
    {
        return cumulativeIssuedThisMonth;
    }
    public void setPhysicalDeliveryOfTheSystemWasNotMentioned(Integer physicalDeliveryOfTheSystemWasNotMentioned) 
    {
        this.physicalDeliveryOfTheSystemWasNotMentioned = physicalDeliveryOfTheSystemWasNotMentioned;
    }

    public Integer getPhysicalDeliveryOfTheSystemWasNotMentioned() 
    {
        return physicalDeliveryOfTheSystemWasNotMentioned;
    }
    public void setTheNetworkHasNoOrders(Integer theNetworkHasNoOrders) 
    {
        this.theNetworkHasNoOrders = theNetworkHasNoOrders;
    }

    public Integer getTheNetworkHasNoOrders() 
    {
        return theNetworkHasNoOrders;
    }
    public void setUploadDate(Date uploadDate) 
    {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() 
    {
        return uploadDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mfqrId", getMfqrId())
            .append("lastMonthBalanceOrder", getLastMonthBalanceOrder())
            .append("commercialNetworks", getCommercialNetworks())
            .append("orderReceivedToday", getOrderReceivedToday())
            .append("ordersReceivedThisMonth", getOrdersReceivedThisMonth())
            .append("orderAccumulationThisYear", getOrderAccumulationThisYear())
            .append("deliveryToday", getDeliveryToday())
            .append("deliveryWithinTheSystemThisMonth", getDeliveryWithinTheSystemThisMonth())
            .append("cumulativeSystemDeliveriesForTheYear", getCumulativeSystemDeliveriesForTheYear())
            .append("unfilledOrders", getUnfilledOrders())
            .append("issuedThisDay", getIssuedThisDay())
            .append("issuedThisMonth", getIssuedThisMonth())
            .append("cumulativeIssuedThisMonth", getCumulativeIssuedThisMonth())
            .append("physicalDeliveryOfTheSystemWasNotMentioned", getPhysicalDeliveryOfTheSystemWasNotMentioned())
            .append("theNetworkHasNoOrders", getTheNetworkHasNoOrders())
            .append("uploadDate", getUploadDate())
            .toString();
    }
}
