package com.ruoyi.file.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
//import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class regulationRespondEntity {



    // "yyyy-MM-dd"
    private Date yearAndMonth;
    private Integer times;

    // initialize updateDates list
    private List<Date> updateDates = new ArrayList<>();

    // Method to add update date
    public void addUpdateDate(Date updateDate) {
        this.updateDates.add(updateDate);
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }


}