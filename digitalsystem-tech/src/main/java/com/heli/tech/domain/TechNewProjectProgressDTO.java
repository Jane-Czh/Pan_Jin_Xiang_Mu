package com.heli.tech.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TechNewProjectProgressDTO {
    private Date yearAndMonth;

    // （1）已完成：已完成的项目数量
    private Long completedProjectCount;
    //（2）进行中：进行中的项目数量，
    private Long ongoingProjectCount;
    //（3）暂未开展：暂未开展的项目数量
    private Long unstartedProjectCount;
}
