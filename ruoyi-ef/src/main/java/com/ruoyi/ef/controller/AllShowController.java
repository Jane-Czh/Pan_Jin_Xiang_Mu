package com.ruoyi.ef.controller;


import com.ruoyi.ef.entity.AllShowEntity;
import com.ruoyi.ef.entity.ProjectEntity;
import com.ruoyi.ef.service.IProjectService;
import com.ruoyi.file.domain.FormInfoTable;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IFormInfoTableService;
import com.ruoyi.file.service.IRegulationsInfoTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/project")
public class AllShowController {
    //制度文件注入
    @Autowired
    private IRegulationsInfoTableService regulationsInfoTableService;
    //表单文件注入
    @Autowired
    private IFormInfoTableService formInfoTableService;
    @Autowired
    private IProjectService projectService;


    //String[] formIds = project.getType().replace("[", "").replace("]", "").split(",");
    //String[] projectIds = regular.getProjectIds().replace("[", "").replace("]", "").split(",");
//    @GetMapping("/getAll")
//    public List<AllShowEntity> list() {
//        List<AllShowEntity> allShowEntities = new ArrayList<>();
//
//        // 获取所有表单信息
//        List<FormInfoTable> forms = formInfoTableService.selectAllFormList(new FormInfoTable());
//
//        // 获取所有制度信息
//        List<RegulationsInfoTable> regulars = regulationsInfoTableService.selectRegulationsInfoTableList(new RegulationsInfoTable());
//
//        // 获取所有流程信息
//        List<ProjectEntity> projects = projectService.queryDatas();
//
//        // 1. 以流程为主，联立制度和表单
//        for (ProjectEntity project : projects) {
//            String[] regularIds = project.getState() != null ? project.getState().replace("[", "").replace("]", "").split(",") : new String[0];
//            System.out.println("regularID===");
//            for (String regularId : regularIds) {
//                System.out.print(" "+ regularId);
//            }
//            String[] formIds = project.getType() != null ? project.getType().replace("[", "").replace("]", "").split(",") : new String[0];
//            System.out.println("formIds ==>" );
//            for (String formId : formIds) {
//                System.out.print(" "+ formId);
//            }
//
//            // 如果流程既没有关联制度也没有关联表单，跳过该流程
//            if (regularIds.length == 0 && formIds.length == 0) {
//                continue;
//            }
//            //TODO 流程为主时，设置的制度和表单为ids，需要进行转换
//            for (String regularId : regularIds) {
//                RegulationsInfoTable regular = regulars.stream()
//                        .filter(r -> r.getRegulationsId().equals(regularId))
//                        .findFirst()
//                        .orElse(null);
//
//                if (regular != null) {
//                    for (String formId : formIds) {
//                        FormInfoTable form = forms.stream()
//                                .filter(f -> f.getFormId().toString().equals(formId))
//                                .findFirst()
//                                .orElse(null);
//
//                        AllShowEntity entity = new AllShowEntity();
//                        entity.setDepartment(project.getDepartment());
//                        entity.setBusinessesModules(project.getBusinessesModules());
//                        entity.setSubBusinesses(project.getSubBusinesses());
//                        entity.setProcessName(project.getName());
//                        entity.setProcessLevel(project.getLevel());
//                        entity.setRegularName(regular.getFileName());
//                        entity.setRegularLevel(regular.getRegulationLeval());
//                        entity.setFormName(form != null ? form.getFormName() : null);
//                        allShowEntities.add(entity);
//                    }
//                } else {
//                    // 流程没有关联制度，直接关联表单
//                    for (String formId : formIds) {
//                        FormInfoTable form = forms.stream()
//                                .filter(f -> f.getFormId().toString().equals(formId))
//                                .findFirst()
//                                .orElse(null);
//
//                        AllShowEntity entity = new AllShowEntity();
//                        entity.setDepartment(project.getDepartment());
//                        entity.setBusinessesModules(project.getBusinessesModules());
//                        entity.setSubBusinesses(project.getSubBusinesses());
//                        entity.setProcessName(project.getName());
//                        entity.setProcessLevel(project.getLevel());
//                        entity.setFormName(form != null ? form.getFormName() : null);
//                        allShowEntities.add(entity);
//                    }
//                }
//            }
//
//            // 处理没有关联制度但有表单的情况
//            if (regularIds.length == 0) {
//                for (String formId : formIds) {
//                    FormInfoTable form = forms.stream()
//                            .filter(f -> f.getFormId().toString().equals(formId))
//                            .findFirst()
//                            .orElse(null);
//
//                    AllShowEntity entity = new AllShowEntity();
//                    entity.setDepartment(project.getDepartment());
//                    entity.setBusinessesModules(project.getBusinessesModules());
//                    entity.setSubBusinesses(project.getSubBusinesses());
//                    entity.setProcessName(project.getName());
//                    entity.setProcessLevel(project.getLevel());
//                    entity.setFormName(form != null ? form.getFormName() : null);
//                    allShowEntities.add(entity);
//                }
//            }
//        }
//
//        // 2. 处理没有绑定流程的制度
//        for (RegulationsInfoTable regular : regulars) {
//            boolean isRegularLinked = allShowEntities.stream()
//                    .anyMatch(e -> regular.getRegulationsTitle().equals(e.getRegularName()));
//
//            if (!isRegularLinked) {
//                AllShowEntity entity = new AllShowEntity();
//
//                entity.setDepartment(regular.getMainResponsibleDepartment());//部门
//                entity.setBusinessesModules(regular.getBusinesses());//业务模块
//                entity.setSubBusinesses(regular.getSubBusinesses());//  细分业务
//
//                entity.setRegularName(regular.getRegulationsTitle());
//                entity.setRegularLevel(regular.getRegulationLeval());
//                allShowEntities.add(entity);
//            }
//        }
//
//        // 3. 处理没有绑定流程的表单
//        for (FormInfoTable form : forms) {
//            boolean isFormLinked = allShowEntities.stream()
//                    .anyMatch(e -> form.getFormName().equals(e.getFormName()));
//
//            if (!isFormLinked) {
//                AllShowEntity entity = new AllShowEntity();
//                entity.setDepartment(form.getDepartmentCategory());//部门
//                entity.setBusinessesModules(form.getBusinesses());//业务模块
//                entity.setSubBusinesses(form.getSubBusinesses());//  细分业务
//
//                entity.setFormName(form.getFormName());
//                allShowEntities.add(entity);
//            }
//        }
//
//        return allShowEntities;
//    }

    @GetMapping("/getAll")
    public List<AllShowEntity> list() {
        List<AllShowEntity> allShowEntities = new ArrayList<>();

        // 获取所有表单信息
        List<FormInfoTable> forms = formInfoTableService.selectAllFormList(new FormInfoTable());

        System.out.println("forms == > ");
        for (FormInfoTable form : forms) {
            System.out.println(" "+ forms);
        }

        // 获取所有制度信息
        List<RegulationsInfoTable> regulars = regulationsInfoTableService.selectRegulationsInfoTableList(new RegulationsInfoTable());
        System.out.println("regulars == > ");
        for (RegulationsInfoTable regular : regulars) {
            System.out.println(" "+ regular);
        }

        // 获取所有流程信息
        List<ProjectEntity> projects = projectService.queryDatas();

        // 保存流程中已经绑定的制度和表单的ID
        Set<String> linkedRegularIds = new HashSet<>();
        Set<String> linkedFormIds = new HashSet<>();

        // 1. 以流程为主，将流程信息转换为AllShowEntity
        for (ProjectEntity project : projects) {
            String[] regularIds = project.getState() != null ? project.getState().replace("[", "").replace("]", "").split(",") : new String[0];
            String[] formIds = project.getType() != null ? project.getType().replace("[", "").replace("]", "").split(",") : new String[0];

            int maxLength = Math.max(regularIds.length, formIds.length);
            for (int i = 0; i < maxLength; i++) {
                AllShowEntity entity = new AllShowEntity();

                // 设置流程信息
                entity.setDepartment(project.getDepartment());
                entity.setBusinessesModules(project.getBusinessesModules());
                entity.setSubBusinesses(project.getSubBusinesses());
                entity.setProcessName(project.getName());
                entity.setProcessLevel(project.getLevel());

                // 设置制度信息
                if (i < regularIds.length) {
                    String regularId = regularIds[i];
                    linkedRegularIds.add(regularId);
                    RegulationsInfoTable regular = regulars.stream()
                            .filter(r -> r.getRegulationsId().toString().equals(regularId))
                            .findFirst()
                            .orElse(null);
                    if (regular != null) {
                        System.out.println("regular.getRegulationsTitle() == " + regular.getRegulationsTitle());
                        entity.setRegularName(regular.getRegulationsTitle());
                        entity.setRegularLevel(regular.getRegulationLeval());
                    }
                }

                // 设置表单信息
                if (i < formIds.length) {
                    String formId = formIds[i];
                    linkedFormIds.add(formId);
                    FormInfoTable form = forms.stream()
                            .filter(f -> f.getFormId().toString().equals(formId))
                            .findFirst()
                            .orElse(null);
                    if (form != null) {
                        entity.setFormName(form.getFormName());
                    }
                }

                allShowEntities.add(entity);
            }
        }

//         2. 处理没有绑定到流程的制度
        for (RegulationsInfoTable regular : regulars) {
            if (!linkedRegularIds.contains(regular.getRegulationsId().toString())) {
                AllShowEntity entity = new AllShowEntity();

                entity.setDepartment(regular.getMainResponsibleDepartment()); // 部门
                entity.setBusinessesModules(regular.getBusinesses()); // 业务模块
                entity.setSubBusinesses(regular.getSubBusinesses()); // 细分业务

                entity.setRegularName(regular.getRegulationsTitle());
                entity.setRegularLevel(regular.getRegulationLeval());
                allShowEntities.add(entity);
            }
        }

        // 3. 处理没有绑定到流程的表单
        for (FormInfoTable form : forms) {
            if (!linkedFormIds.contains(form.getFormId().toString())) {
                AllShowEntity entity = new AllShowEntity();
                entity.setDepartment(form.getDepartmentCategory()); // 部门
                entity.setBusinessesModules(form.getBusinesses()); // 业务模块
                entity.setSubBusinesses(form.getSubBusinesses()); // 细分业务

                entity.setFormName(form.getFormName());
                allShowEntities.add(entity);
            }
        }

        return allShowEntities;
    }



}
