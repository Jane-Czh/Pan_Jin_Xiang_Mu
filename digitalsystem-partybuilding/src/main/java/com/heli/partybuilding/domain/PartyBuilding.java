package com.heli.partybuilding.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @description: [党建]指标对象 PartyBuilding
 * @author: hong
 * @date: 2024/4/9 15:47
 **/
@Data
public class PartyBuilding extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * PB_ID
     */
    private String pbId;

    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date yearAndMonth;

    /**
     * 党建排名
     */
    @Excel(name = "党建排名")
    private Integer Ranking;

    /**
     * 党建分数
     */
    @Excel(name = "党建分数")
    private Double score;

}
