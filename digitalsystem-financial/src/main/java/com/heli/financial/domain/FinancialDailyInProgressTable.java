package com.heli.financial.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * [财务]每日填报指标[当日再制品金额]对象 Financial_Daily_InProgress_table
 *
 * @author hong
 * @date 2024-05-06
 */
@Data
public class FinancialDailyInProgressTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 当日在制品金额
     */
    @Excel(name = "当日在制品金额")
    private BigDecimal inProgressDayRevenue;

    /**
     * 日期-年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期-年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dataTime;

}
