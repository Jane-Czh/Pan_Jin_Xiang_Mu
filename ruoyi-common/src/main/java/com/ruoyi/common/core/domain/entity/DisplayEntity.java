package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: hong
 * @CreateTime: 2024-04-01 11:54
 * @Description: 单个数据展示实体
 */
@Data
public class DisplayEntity {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date yearAndMonth;

    private BigDecimal data;

}
