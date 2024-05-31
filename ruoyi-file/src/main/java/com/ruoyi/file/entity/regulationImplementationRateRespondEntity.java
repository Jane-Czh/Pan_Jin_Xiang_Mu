package com.ruoyi.file.entity;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class regulationImplementationRateRespondEntity {
    // "yyyy-MM"
    private Date yearAndMonth;
    private BigDecimal rate;

    public Date getYearAndMonth() {
        return yearAndMonth;
    }

    public void setYearAndMonth(Date yearAndMonth) {
        this.yearAndMonth = yearAndMonth;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
