package com.ruoyi.ef.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
//import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01 11:54
 * @Description: 单个数据展示实体
 */

@Data
public class IndicatorRespondEntity {

    // "yyyy-MM-dd"
    private String yearAndMonth;
    private Integer times;

    // initialize updateDates list
    private List<String> updateDates = new ArrayList<>();

    // Method to add update date
    public void addUpdateDate(String updateDate) {
        this.updateDates.add(updateDate);
    }




}
