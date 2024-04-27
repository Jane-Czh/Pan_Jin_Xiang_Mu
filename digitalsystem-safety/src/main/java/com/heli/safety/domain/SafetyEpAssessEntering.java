package com.heli.safety.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 安环科考核录入对象 Safety_EP_Assess_Entering
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
public class SafetyEpAssessEntering extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long seaeId;

    /** 检查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkTime;

    /** 检查班组 */
    @Excel(name = "检查班组")
    private String checkTeam;

    /** 设备问题点 */
    @Excel(name = "设备问题点")
    private String equipmentProblemSpot;

    /** 责任人 */
    @Excel(name = "责任人")
    private String chargePerson;

    /** 整改完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "整改完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rectificationCompletionTime;

    /** 整改前照片 */
    @Excel(name = "整改前照片")
    private String preRectificationPhoto;

    /** 整改后照片 */
    @Excel(name = "整改后照片")
    private String postRectificationPhoto;

    /** 考核分数 */
    @Excel(name = "考核分数")
    private Long assessmentScore;

    /** 验收人 */
    @Excel(name = "验收人")
    private String Accepter;

    public void setSeaeId(Long seaeId) 
    {
        this.seaeId = seaeId;
    }

    public Long getSeaeId() 
    {
        return seaeId;
    }
    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
    }
    public void setCheckTeam(String checkTeam) 
    {
        this.checkTeam = checkTeam;
    }

    public String getCheckTeam() 
    {
        return checkTeam;
    }
    public void setEquipmentProblemSpot(String equipmentProblemSpot) 
    {
        this.equipmentProblemSpot = equipmentProblemSpot;
    }

    public String getEquipmentProblemSpot() 
    {
        return equipmentProblemSpot;
    }
    public void setChargePerson(String chargePerson) 
    {
        this.chargePerson = chargePerson;
    }

    public String getChargePerson() 
    {
        return chargePerson;
    }
    public void setRectificationCompletionTime(Date rectificationCompletionTime) 
    {
        this.rectificationCompletionTime = rectificationCompletionTime;
    }

    public Date getRectificationCompletionTime() 
    {
        return rectificationCompletionTime;
    }
    public void setPreRectificationPhoto(String preRectificationPhoto) 
    {
        this.preRectificationPhoto = preRectificationPhoto;
    }

    public String getPreRectificationPhoto() 
    {
        return preRectificationPhoto;
    }
    public void setPostRectificationPhoto(String postRectificationPhoto) 
    {
        this.postRectificationPhoto = postRectificationPhoto;
    }

    public String getPostRectificationPhoto() 
    {
        return postRectificationPhoto;
    }
    public void setAssessmentScore(Long assessmentScore) 
    {
        this.assessmentScore = assessmentScore;
    }

    public Long getAssessmentScore() 
    {
        return assessmentScore;
    }
    public void setAccepter(String Accepter) 
    {
        this.Accepter = Accepter;
    }

    public String getAccepter() 
    {
        return Accepter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seaeId", getSeaeId())
            .append("checkTime", getCheckTime())
            .append("checkTeam", getCheckTeam())
            .append("equipmentProblemSpot", getEquipmentProblemSpot())
            .append("chargePerson", getChargePerson())
            .append("rectificationCompletionTime", getRectificationCompletionTime())
            .append("preRectificationPhoto", getPreRectificationPhoto())
            .append("postRectificationPhoto", getPostRectificationPhoto())
            .append("assessmentScore", getAssessmentScore())
            .append("Accepter", getAccepter())
            .toString();
    }
}
