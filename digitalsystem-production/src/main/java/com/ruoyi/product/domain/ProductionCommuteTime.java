package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设置上下班时间对象 Production_commute_time
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public class ProductionCommuteTime extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long commuteTimeId;

    /** 上班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date workingHours;

    /** 下班时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "下班时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date offHours;

    public void setCommuteTimeId(Long commuteTimeId) 
    {
        this.commuteTimeId = commuteTimeId;
    }

    public Long getCommuteTimeId() 
    {
        return commuteTimeId;
    }
    public void setWorkingHours(Date workingHours) 
    {
        this.workingHours = workingHours;
    }

    public Date getWorkingHours() 
    {
        return workingHours;
    }
    public void setOffHours(Date offHours) 
    {
        this.offHours = offHours;
    }

    public Date getOffHours() 
    {
        return offHours;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commuteTimeId", getCommuteTimeId())
            .append("workingHours", getWorkingHours())
            .append("offHours", getOffHours())
            .toString();
    }
}
