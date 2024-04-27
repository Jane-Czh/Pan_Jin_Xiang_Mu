package com.heli.production.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 记录标准作业信息对象 Production_Standard_Job_Registration_Form
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public class ProductionStandardJobRegistrationForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long psjrfId;

    /** 关键字 */
    @Excel(name = "关键字")
    private String Keyword;

    /** 下发对象 */
    @Excel(name = "下发对象")
    private String deliverObject;

    /** 下发时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "下发时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    public void setPsjrfId(Long psjrfId) 
    {
        this.psjrfId = psjrfId;
    }

    public Long getPsjrfId() 
    {
        return psjrfId;
    }
    public void setKeyword(String Keyword) 
    {
        this.Keyword = Keyword;
    }

    public String getKeyword() 
    {
        return Keyword;
    }
    public void setDeliverObject(String deliverObject) 
    {
        this.deliverObject = deliverObject;
    }

    public String getDeliverObject() 
    {
        return deliverObject;
    }
    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("psjrfId", getPsjrfId())
            .append("Keyword", getKeyword())
            .append("deliverObject", getDeliverObject())
            .append("deliveryTime", getDeliveryTime())
            .append("fileName", getFileName())
            .toString();
    }
}
