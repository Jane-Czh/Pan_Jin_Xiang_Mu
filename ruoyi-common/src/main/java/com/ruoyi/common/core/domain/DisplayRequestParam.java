package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: hong
 * @CreateTime: 2024-04-17  13:59
 * @Description: TODO
 * @Version: 1.0
 */

public class DisplayRequestParam {
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endTime;

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

    public DisplayRequestParam() {
    }

    public DisplayRequestParam(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DisplayRequestParam{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
