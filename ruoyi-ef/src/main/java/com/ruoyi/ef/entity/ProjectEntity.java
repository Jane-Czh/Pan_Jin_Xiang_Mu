package com.ruoyi.ef.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.beans.Transient;
import java.util.Date;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:22
 */
@Data
@TableName("flow_project")
public class ProjectEntity {
    // 指标查询流程更改次数时通过api传递过来的 时间参数：startTime、endTime
    @TableField(exist = false)
    private Date startTime;
    @TableField(exist = false)
    private Date endTime;
    @TableField(exist = false)
    private String filename;

//    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    //流程名称
    private String name;

    //xx
    private String file;

    //创建日期
    private String createDate;
    //创建人
    private String createBy;
    //更新日期
    private String updateDate;
    //更新人
    private String updateBy;
    //链接旧版的流程图id
    private String oldVersion;
    //最新版本标志位
    private Integer newest;

    /** 绑定的制度文件ids */
    private String state;
    /** 绑定的表单文件ids */
    private String type;

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
}
