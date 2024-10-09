package com.ruoyi.market.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AverageResult {
    private final static String Year="2024";
    private String Month;

    private Double Average_Days_Difference;

    private Long Over_20_Percent_Count;

    private String Branch;

    private int  Number;

    private Date Order_System_Delivery_Time;

    private int  TotalNumber;


}
