package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: hong
 * @CreateTime: 2024-04-17  13:59
 * @Description: TODO
 * @Version: 1.0
 */


@Data
public class DisplayRequestParam {
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endTime;
}
