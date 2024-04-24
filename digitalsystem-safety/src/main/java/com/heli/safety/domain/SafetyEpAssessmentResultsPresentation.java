package com.heli.safety.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成绩考核展示对象 Safety_EP_Assessment_Results_Presentation
 * 
 * @author heli
 * @date 2024-04-21
 */
public class SafetyEpAssessmentResultsPresentation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long searpId;

    /** 班组 */
    @Excel(name = "班组")
    private String checkTeam;

    /** 一月成绩 */
    @Excel(name = "一月成绩")
    private BigDecimal januaryResults;

    /** 二月成绩 */
    @Excel(name = "二月成绩")
    private BigDecimal februaryResults;

    /** 三月成绩 */
    @Excel(name = "三月成绩")
    private BigDecimal marchResults;

    /** 四月成绩 */
    @Excel(name = "四月成绩")
    private BigDecimal aprilResults;

    /** 五月成绩 */
    @Excel(name = "五月成绩")
    private BigDecimal mayResults;

    /** 六月成绩 */
    @Excel(name = "六月成绩")
    private BigDecimal juneResults;

    /** 七月成绩 */
    @Excel(name = "七月成绩")
    private BigDecimal julyResults;

    /** 八月成绩 */
    @Excel(name = "八月成绩")
    private BigDecimal augustResults;

    /** 九月成绩 */
    @Excel(name = "九月成绩")
    private BigDecimal septemberResults;

    /** 十月成绩 */
    @Excel(name = "十月成绩")
    private BigDecimal octoberResults;

    /** 十一月成绩 */
    @Excel(name = "十一月成绩")
    private BigDecimal novemberResults;

    /** 十二月成绩 */
    @Excel(name = "十二月成绩")
    private BigDecimal decemberResults;

    /** 第一季度成绩 */
    @Excel(name = "第一季度成绩")
    private BigDecimal firstQuarterResults;

    /** 第二季度成绩 */
    @Excel(name = "第二季度成绩")
    private BigDecimal secondQuarterResults;

    /** 第三季度成绩 */
    @Excel(name = "第三季度成绩")
    private BigDecimal thirdQuarterResults;

    /** 第四季度成绩 */
    @Excel(name = "第四季度成绩")
    private BigDecimal fourthQuarterResults;

    /** 全年成绩 */
    @Excel(name = "全年成绩")
    private BigDecimal annualResults;

    public void setSearpId(Long searpId) 
    {
        this.searpId = searpId;
    }

    public Long getSearpId() 
    {
        return searpId;
    }
    public void setCheckTeam(String checkTeam) 
    {
        this.checkTeam = checkTeam;
    }

    public String getCheckTeam() 
    {
        return checkTeam;
    }
    public void setJanuaryResults(BigDecimal januaryResults) 
    {
        this.januaryResults = januaryResults;
    }

    public BigDecimal getJanuaryResults() 
    {
        return januaryResults;
    }
    public void setFebruaryResults(BigDecimal februaryResults) 
    {
        this.februaryResults = februaryResults;
    }

    public BigDecimal getFebruaryResults() 
    {
        return februaryResults;
    }
    public void setMarchResults(BigDecimal marchResults) 
    {
        this.marchResults = marchResults;
    }

    public BigDecimal getMarchResults() 
    {
        return marchResults;
    }
    public void setAprilResults(BigDecimal aprilResults) 
    {
        this.aprilResults = aprilResults;
    }

    public BigDecimal getAprilResults() 
    {
        return aprilResults;
    }
    public void setMayResults(BigDecimal mayResults) 
    {
        this.mayResults = mayResults;
    }

    public BigDecimal getMayResults() 
    {
        return mayResults;
    }
    public void setJuneResults(BigDecimal juneResults) 
    {
        this.juneResults = juneResults;
    }

    public BigDecimal getJuneResults() 
    {
        return juneResults;
    }
    public void setJulyResults(BigDecimal julyResults) 
    {
        this.julyResults = julyResults;
    }

    public BigDecimal getJulyResults() 
    {
        return julyResults;
    }
    public void setAugustResults(BigDecimal augustResults) 
    {
        this.augustResults = augustResults;
    }

    public BigDecimal getAugustResults() 
    {
        return augustResults;
    }
    public void setSeptemberResults(BigDecimal septemberResults) 
    {
        this.septemberResults = septemberResults;
    }

    public BigDecimal getSeptemberResults() 
    {
        return septemberResults;
    }
    public void setOctoberResults(BigDecimal octoberResults) 
    {
        this.octoberResults = octoberResults;
    }

    public BigDecimal getOctoberResults() 
    {
        return octoberResults;
    }
    public void setNovemberResults(BigDecimal novemberResults) 
    {
        this.novemberResults = novemberResults;
    }

    public BigDecimal getNovemberResults() 
    {
        return novemberResults;
    }
    public void setDecemberResults(BigDecimal decemberResults) 
    {
        this.decemberResults = decemberResults;
    }

    public BigDecimal getDecemberResults() 
    {
        return decemberResults;
    }
    public void setFirstQuarterResults(BigDecimal firstQuarterResults) 
    {
        this.firstQuarterResults = firstQuarterResults;
    }

    public BigDecimal getFirstQuarterResults() 
    {
        return firstQuarterResults;
    }
    public void setSecondQuarterResults(BigDecimal secondQuarterResults) 
    {
        this.secondQuarterResults = secondQuarterResults;
    }

    public BigDecimal getSecondQuarterResults() 
    {
        return secondQuarterResults;
    }
    public void setThirdQuarterResults(BigDecimal thirdQuarterResults) 
    {
        this.thirdQuarterResults = thirdQuarterResults;
    }

    public BigDecimal getThirdQuarterResults() 
    {
        return thirdQuarterResults;
    }
    public void setFourthQuarterResults(BigDecimal fourthQuarterResults) 
    {
        this.fourthQuarterResults = fourthQuarterResults;
    }

    public BigDecimal getFourthQuarterResults() 
    {
        return fourthQuarterResults;
    }
    public void setAnnualResults(BigDecimal annualResults) 
    {
        this.annualResults = annualResults;
    }

    public BigDecimal getAnnualResults() 
    {
        return annualResults;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("searpId", getSearpId())
            .append("checkTeam", getCheckTeam())
            .append("januaryResults", getJanuaryResults())
            .append("februaryResults", getFebruaryResults())
            .append("marchResults", getMarchResults())
            .append("aprilResults", getAprilResults())
            .append("mayResults", getMayResults())
            .append("juneResults", getJuneResults())
            .append("julyResults", getJulyResults())
            .append("augustResults", getAugustResults())
            .append("septemberResults", getSeptemberResults())
            .append("octoberResults", getOctoberResults())
            .append("novemberResults", getNovemberResults())
            .append("decemberResults", getDecemberResults())
            .append("firstQuarterResults", getFirstQuarterResults())
            .append("secondQuarterResults", getSecondQuarterResults())
            .append("thirdQuarterResults", getThirdQuarterResults())
            .append("fourthQuarterResults", getFourthQuarterResults())
            .append("annualResults", getAnnualResults())
            .toString();
    }
}
