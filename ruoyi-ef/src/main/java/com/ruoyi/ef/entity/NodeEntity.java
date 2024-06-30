package com.ruoyi.ef.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:22
 */
@Data
@TableName("flow_node")
public class NodeEntity {
//    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String projectId;
    private String name;
    //绑定的表单文件ids[]
    private String type;
    @TableField(value = "`left`")
    private String left;
    @TableField(value = "`top`")
    private String top;
    private String ico;
    //绑定的制度文件ids[]
    private String state;
}
