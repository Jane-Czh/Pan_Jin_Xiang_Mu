package com.ruoyi.product.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 计划完成情况对象 Production_function_Statistics_of_plan_completion_status
 *
 * @author ruoyi
 * @date 2024-04-17
 */
public class ProductionFunctionStatisticsOfPlanCompletionStatus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** MPC_ID */
    private Long mpcId;

    /** 车号 */
    @Excel(name = "车号")
    private String carNumber;

    /** 接单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "接单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acceptanceDate;

    /** 上线日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date launchDate;

    /** 待排产时长 */
    @Excel(name = "待排产时长")
    private Long waitingTimeForProduction;

    /** 车体上线日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车体上线日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vehicleLaunchDate;

    /** 计划完工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedCompletionPeriod;

    /** 生产周期 */
    @Excel(name = "生产周期")
    private String productionCycle;

    /** 精整完工期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "精整完工期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date precisionCompletionPeriod;

    /** 超期天数 */
    @Excel(name = "超期天数")
    private Long overdueDays;

    /** 车体上线日期总条数 */
    @Excel(name = "车体上线日期总条数")
    private Long totalNumberWaitingTimeForProduction;

    /** 门架合装完工期总条数 */
    @Excel(name = "门架合装完工期总条数")
    private Long totalNumberCompletionPeriodOfDoorFrameAssembly;

    /** 试车完工期总条数 */
    @Excel(name = "试车完工期总条数")
    private Long totalNumberTrialCompletionPeriod;

    /** 特种作业总条数 */
    @Excel(name = "特种作业总条数")
    private Long totalNumberSpecialOperations;

    /** 精整完工期总条数 */
    @Excel(name = "精整完工期总条数")
    private Long totalNumberPrecisionCompletionPeriod;

    /** 计划兑现率 */
    @Excel(name = "计划兑现率")
    private Long planRate;

    public void setMpcId(Long mpcId)
    {
        this.mpcId = mpcId;
    }

    public Long getMpcId()
    {
        return mpcId;
    }
    public void setCarNumber(String carNumber)
    {
        this.carNumber = carNumber;
    }

    public String getCarNumber()
    {
        return carNumber;
    }
    public void setAcceptanceDate(Date acceptanceDate)
    {
        this.acceptanceDate = acceptanceDate;
    }

    public Date getAcceptanceDate()
    {
        return acceptanceDate;
    }
    public void setLaunchDate(Date launchDate)
    {
        this.launchDate = launchDate;
    }

    public Date getLaunchDate()
    {
        return launchDate;
    }
    public void setWaitingTimeForProduction(Long waitingTimeForProduction)
    {
        this.waitingTimeForProduction = waitingTimeForProduction;
    }

    public Long getWaitingTimeForProduction()
    {
        return waitingTimeForProduction;
    }
    public void setVehicleLaunchDate(Date vehicleLaunchDate)
    {
        this.vehicleLaunchDate = vehicleLaunchDate;
    }

    public Date getVehicleLaunchDate()
    {
        return vehicleLaunchDate;
    }
    public void setPlannedCompletionPeriod(Date plannedCompletionPeriod)
    {
        this.plannedCompletionPeriod = plannedCompletionPeriod;
    }

    public Date getPlannedCompletionPeriod()
    {
        return plannedCompletionPeriod;
    }
    public void setProductionCycle(String productionCycle)
    {
        this.productionCycle = productionCycle;
    }

    public String getProductionCycle()
    {
        return productionCycle;
    }
    public void setPrecisionCompletionPeriod(Date precisionCompletionPeriod)
    {
        this.precisionCompletionPeriod = precisionCompletionPeriod;
    }

    public Date getPrecisionCompletionPeriod()
    {
        return precisionCompletionPeriod;
    }
    public void setOverdueDays(Long overdueDays)
    {
        this.overdueDays = overdueDays;
    }

    public Long getOverdueDays()
    {
        return overdueDays;
    }
    public void setTotalNumberWaitingTimeForProduction(Long totalNumberWaitingTimeForProduction)
    {
        this.totalNumberWaitingTimeForProduction = totalNumberWaitingTimeForProduction;
    }

    public Long getTotalNumberWaitingTimeForProduction()
    {
        return totalNumberWaitingTimeForProduction;
    }
    public void setTotalNumberCompletionPeriodOfDoorFrameAssembly(Long totalNumberCompletionPeriodOfDoorFrameAssembly)
    {
        this.totalNumberCompletionPeriodOfDoorFrameAssembly = totalNumberCompletionPeriodOfDoorFrameAssembly;
    }

    public Long getTotalNumberCompletionPeriodOfDoorFrameAssembly()
    {
        return totalNumberCompletionPeriodOfDoorFrameAssembly;
    }
    public void setTotalNumberTrialCompletionPeriod(Long totalNumberTrialCompletionPeriod)
    {
        this.totalNumberTrialCompletionPeriod = totalNumberTrialCompletionPeriod;
    }

    public Long getTotalNumberTrialCompletionPeriod()
    {
        return totalNumberTrialCompletionPeriod;
    }
    public void setTotalNumberSpecialOperations(Long totalNumberSpecialOperations)
    {
        this.totalNumberSpecialOperations = totalNumberSpecialOperations;
    }

    public Long getTotalNumberSpecialOperations()
    {
        return totalNumberSpecialOperations;
    }
    public void setTotalNumberPrecisionCompletionPeriod(Long totalNumberPrecisionCompletionPeriod)
    {
        this.totalNumberPrecisionCompletionPeriod = totalNumberPrecisionCompletionPeriod;
    }

    public Long getTotalNumberPrecisionCompletionPeriod()
    {
        return totalNumberPrecisionCompletionPeriod;
    }
    public void setPlanRate(Long planRate)
    {
        this.planRate = planRate;
    }

    public Long getPlanRate()
    {
        return planRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("mpcId", getMpcId())
                .append("carNumber", getCarNumber())
                .append("acceptanceDate", getAcceptanceDate())
                .append("launchDate", getLaunchDate())
                .append("waitingTimeForProduction", getWaitingTimeForProduction())
                .append("vehicleLaunchDate", getVehicleLaunchDate())
                .append("plannedCompletionPeriod", getPlannedCompletionPeriod())
                .append("productionCycle", getProductionCycle())
                .append("precisionCompletionPeriod", getPrecisionCompletionPeriod())
                .append("overdueDays", getOverdueDays())
                .append("totalNumberWaitingTimeForProduction", getTotalNumberWaitingTimeForProduction())
                .append("totalNumberCompletionPeriodOfDoorFrameAssembly", getTotalNumberCompletionPeriodOfDoorFrameAssembly())
                .append("totalNumberTrialCompletionPeriod", getTotalNumberTrialCompletionPeriod())
                .append("totalNumberSpecialOperations", getTotalNumberSpecialOperations())
                .append("totalNumberPrecisionCompletionPeriod", getTotalNumberPrecisionCompletionPeriod())
                .append("planRate", getPlanRate())
                .toString();
    }
}