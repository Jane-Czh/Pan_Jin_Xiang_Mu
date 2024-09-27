package com.heli.tech.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TechNonStandardDisplayDTO {

    private Date yearAndMonth;
    private String nonStandardNum;
    private String nonStandardAvgPreparationDays;
}
