package com.ruoyi.common.enums;

/**
 * @description: 单元格 填充枚举
 * @author: hong
 * @date: 2024/3/31 10:51
 **/
public enum FillCommentEnum {

    FILL(0, "必填"),
    EMPTY(1, "非必填");

    private int code;
    private String description;

    FillCommentEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
