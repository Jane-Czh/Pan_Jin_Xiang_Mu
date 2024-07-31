package com.ruoyi.ef.vo;


import com.ruoyi.ef.entity.LineEntity;
import com.ruoyi.ef.entity.NodeEntity;
import lombok.Data;

import java.util.List;

/**
 * @author liuchengbiao
 * @date 2020-05-22 22:00
 */
@Data
public class ProjectVo {
    private String id;
    private String name;
    //0731新增字段
    //流程编号
    private String number;
    //主责部门
    private String department;
    //流程等级
    private String level;
    //流程目的
    private String purpose;
    //适用范围
    private String applicationScope;

    private List<NodeEntity> nodeList;
    private List<LineEntity> lineList;
}
