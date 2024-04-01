package com.ruoyi.common.annotation;

import com.ruoyi.common.enums.FillCommentEnum;

import java.lang.annotation.*;

/**
 * @description: 自定义导入EXCEl指定行列注解
 * @author: hong
 * @date: 2024/3/31 10:50
 **/

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordTemplate {
    //行号
    int rowNo();

    //列号
    int columnNo();

    //是否必填
    FillCommentEnum comment();

    //单元格名称
    String name();

}
