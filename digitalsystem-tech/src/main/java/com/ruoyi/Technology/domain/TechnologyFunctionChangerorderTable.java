package com.ruoyi.Technology.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 变更单留存对象 Technology_Function_ChangerOrder_Table
 * 
 * @author ruoyi
 * @date 2024-08-02
 */
public class TechnologyFunctionChangerorderTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long changetableId;

    /** 序号 */
    @Excel(name = "序号")
    private Long number;

    /** 变更单号 */
    @Excel(name = "变更单号")
    private Long changeorderId;

    /** 信息描述 */
    @Excel(name = "信息描述")
    private String information;

    /** 接收人/实施人 */
    @Excel(name = "接收人/实施人")
    private String receiver;

    /** 完成情况 */
    @Excel(name = "完成情况")
    private String isfinish;

    /** 分配时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date assignmentTime;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    /** 凭证归档 */
    @Excel(name = "凭证归档")
    private String voucher;

    /** 类型/生效时间 */
    @Excel(name = "类型/生效时间")
    private String type;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String filepath;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imagepath;

    public void setChangetableId(Long changetableId) 
    {
        this.changetableId = changetableId;
    }

    public Long getChangetableId() 
    {
        return changetableId;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setChangeorderId(Long changeorderId) 
    {
        this.changeorderId = changeorderId;
    }

    public Long getChangeorderId() 
    {
        return changeorderId;
    }
    public void setInformation(String information) 
    {
        this.information = information;
    }

    public String getInformation() 
    {
        return information;
    }
    public void setReceiver(String receiver) 
    {
        this.receiver = receiver;
    }

    public String getReceiver() 
    {
        return receiver;
    }
    public void setIsfinish(String isfinish) 
    {
        this.isfinish = isfinish;
    }

    public String getIsfinish() 
    {
        return isfinish;
    }
    public void setAssignmentTime(Date assignmentTime) 
    {
        this.assignmentTime = assignmentTime;
    }

    public Date getAssignmentTime() 
    {
        return assignmentTime;
    }
    public void setFinishTime(Date finishTime) 
    {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() 
    {
        return finishTime;
    }
    public void setVoucher(String voucher) 
    {
        this.voucher = voucher;
    }

    public String getVoucher() 
    {
        return voucher;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFilepath(String filepath) 
    {
        this.filepath = filepath;
    }

    public String getFilepath() 
    {
        return filepath;
    }
    public void setImagepath(String imagepath) 
    {
        this.imagepath = imagepath;
    }

    public String getImagepath() 
    {
        return imagepath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("changetableId", getChangetableId())
            .append("number", getNumber())
            .append("changeorderId", getChangeorderId())
            .append("information", getInformation())
            .append("receiver", getReceiver())
            .append("isfinish", getIsfinish())
            .append("assignmentTime", getAssignmentTime())
            .append("finishTime", getFinishTime())
            .append("voucher", getVoucher())
            .append("type", getType())
            .append("remark", getRemark())
            .append("filepath", getFilepath())
            .append("imagepath", getImagepath())
            .toString();
    }
}
