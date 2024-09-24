package com.ruoyi.ef.entity;

import lombok.Data;

@Data
public class AllShowEntity {

    //主责部门
    private String department;
    //业务模块名称
    private String businessesModules;
    //细分业务名称
    private String subBusinesses;

    //绑定的制度文件名称
    private String regularName;
    //制度等级
    private String regularLevel;

    //流程名称
    private String processName;
    //流程等级
    private String processLevel;

    //绑定的表单文件名称
    private String formName;





}
