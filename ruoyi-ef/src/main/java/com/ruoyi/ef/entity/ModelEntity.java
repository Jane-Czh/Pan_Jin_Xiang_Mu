package com.ruoyi.ef.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.DataSource;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【流程模板】对象 flow_model
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@Data
@TableName("flow_model")
public class ModelEntity
{
    private String id;
    private String name;
    private String data;

    private String zz;

    private String sb;

    private String ds;

}
