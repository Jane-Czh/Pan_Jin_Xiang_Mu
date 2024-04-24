package com.heli.financial.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class DisplayRequestParam {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
     Date StartTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
     Date EndTime;
}
