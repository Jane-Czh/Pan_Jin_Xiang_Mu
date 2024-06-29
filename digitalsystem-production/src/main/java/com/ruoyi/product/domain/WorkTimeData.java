package com.ruoyi.product.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class WorkTimeData {

    private Date startTime; // 正常上班时间
    private Date endTime;   // 正常下班时间
    public WorkTimeData() {
        // 无参构造函数
    }

    // 构造函数
    @JsonCreator
    public WorkTimeData(@JsonProperty("startTime") Date startTime, @JsonProperty("endTime") Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

