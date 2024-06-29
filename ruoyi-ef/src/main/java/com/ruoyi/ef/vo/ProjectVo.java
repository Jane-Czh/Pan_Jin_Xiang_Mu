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
    private List<NodeEntity> nodeList;
    private List<LineEntity> lineList;
}
