package com.ruoyi.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 加班统计对象 Production_Overtime_Statistics_Table
 *
 * @author ruoyi
 * @date 2024-05-13
 */
public class ProductionOvertimeStatisticsTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long postId;

    /** 序号 */
    @Excel(name = "序号")
    private Long Number;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String idNumber;

    /** 姓名 */
    @Excel(name = "姓名")
    private String Name;

    /** 性别 */
    @Excel(name = "性别")
    private String Gender;

    /** 出勤次数 */
    @Excel(name = "出勤次数")
    private Long attendanceSituation;

    /** 加班时长(单位:小时) */
    @Excel(name = "加班时长(单位:小时)")
    private Long overtimeDuration;

    /** 异常情况 */
    @Excel(name = "异常情况")
    private String abnormalSituation;

    /** 标志是否存在 */
    @Excel(name = "标志是否存在")
    private Long isExist;

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public Long getPostId()
    {
        return postId;
    }
    public void setNumber(Long Number)
    {
        this.Number = Number;
    }

    public Long getNumber()
    {
        return Number;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getName()
    {
        return Name;
    }
    public void setGender(String Gender)
    {
        this.Gender = Gender;
    }

    public String getGender()
    {
        return Gender;
    }
    public void setAttendanceSituation(Long attendanceSituation)
    {
        this.attendanceSituation = attendanceSituation;
    }

    public Long getAttendanceSituation()
    {
        return attendanceSituation;
    }
    public void setOvertimeDuration(Long overtimeDuration)
    {
        this.overtimeDuration = overtimeDuration;
    }

    public Long getOvertimeDuration()
    {
        return overtimeDuration;
    }
    public void setAbnormalSituation(String abnormalSituation)
    {
        this.abnormalSituation = abnormalSituation;
    }

    public String getAbnormalSituation()
    {
        return abnormalSituation;
    }

    public void setIsExist(Long isExist)
    {
        this.isExist = isExist;
    }

    public Long getIsExist()
    {
        return isExist;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("postId", getPostId())
                .append("Number", getNumber())
                .append("idNumber", getIdNumber())
                .append("Name", getName())
                .append("Gender", getGender())
                .append("attendanceSituation", getAttendanceSituation())
                .append("overtimeDuration", getOvertimeDuration())
                .append("abnormalSituation", getAbnormalSituation())
                .append("isExist", getIsExist())
                .append("createTime", getCreateTime())
                .toString();
    }
}