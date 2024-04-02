package com.heli.financial.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01  11:54
 * @Description: 单个数据展示实体
 * @Version: 1.0
 */

public class DisplayEntity extends BaseEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    private BigDecimal data;

    public DisplayEntity(Date yearAndMonth, BigDecimal data) {
        this.yearAndMonth = yearAndMonth;
        this.data = data;
    }

    public DisplayEntity() {
    }

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }


}
