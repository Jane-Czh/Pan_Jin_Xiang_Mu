package com.ruoyi.ef.controller;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.ruoyi.ef.entity.IndicatorRespondEntity;
import com.ruoyi.ef.entity.LineEntity;
import com.ruoyi.ef.entity.NodeEntity;
import com.ruoyi.ef.entity.ProjectEntity;
import com.ruoyi.ef.service.IProjectService;
import com.ruoyi.ef.vo.ProjectVo;
import com.ruoyi.ef.vo.R;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IFormInfoTableService;
import com.ruoyi.file.service.IRegulationsInfoTableService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * @author liuchengbiao
 * @date 2020-05-23 20:34
 */

@RestController
@RequestMapping("/project")
public class ProjectController extends SuperController<ProjectEntity> {

    @Autowired
    private IProjectService projectService;
    //权限     @PreAuthorize("@ss.hasPermi('manage:index:list')")

    //制度文件注入
    @Autowired
    private IRegulationsInfoTableService regulationsInfoTableService;
    //表单文件注入
    @Autowired
    private IFormInfoTableService formInfoTableService;


    /**
     * 根据projectId更新流程名称
     */
    @GetMapping("/updateNameById/{id}")
    public R updateNameById(@PathVariable("id") String id) {

        return success();
    }


    /**
     * 根据projectId获取流程数据projectEntity
     */
    @GetMapping("/queryData/{id}")
    public ProjectEntity queryData(@PathVariable("id") String id) {
//        ProjectEntity projectEntity = projectService.getOne(new QueryWrapper<ProjectEntity>().eq("id", id));
        ProjectEntity projectEntity = projectService.getById(id);

        return projectEntity;
    }


    /**
     * 对比更新的内容，将文字性描述写入当前project的file字段
     */
    @GetMapping("/compareData/{id}")
    public R compareData(@PathVariable("id") String id) {
        //根据传递过来的id查询当前的projectVo
        ProjectVo projectVo = projectService.queryData(id);
        System.out.println("当前的 11111compare projectVo ==>" + projectVo);
        //根据id查询最近一条的preProjectVo
        ProjectVo preProjectVo = projectService.queryPreData(id);
        System.out.println("原来的 22222compare preProjectVo ==>" + preProjectVo);
        // 对比 projectVo 与 preProjectVo，将差异changes set 到 file 字段
        String changes = compareProjectVo(preProjectVo, projectVo);

        System.out.println("changes ==>" + changes);
        // 更新project by id --> 将changes set到file字段
        ProjectEntity projectEntity = projectService.getById(id);
        projectEntity.setFile(changes);
        projectService.getBaseMapper().updateById(projectEntity);

        return success();
    }

    private String compareProjectVo(ProjectVo oldVo, ProjectVo newVo) {
        StringBuilder sb = new StringBuilder();

        // 比较节点
        List<NodeEntity> oldNodes = oldVo.getNodeList();
        List<NodeEntity> newNodes = newVo.getNodeList();
        sb.append(compareNodes(oldNodes, newNodes));

        // 比较连线
        List<LineEntity> oldLines = oldVo.getLineList();
        List<LineEntity> newLines = newVo.getLineList();
        sb.append(compareLines(oldLines, newLines, newNodes, oldNodes));

        return sb.toString();
    }

    private String compareNodes(List<NodeEntity> oldNodes, List<NodeEntity> newNodes) {
        StringBuilder sb = new StringBuilder();

        // 找到新增的节点
        List<NodeEntity> addedNodes = newNodes.stream()
                .filter(newNode -> oldNodes.stream().noneMatch(oldNode -> oldNode.getName().equals(newNode.getName())))
                .collect(Collectors.toList());
        if (!addedNodes.isEmpty()) {
            AtomicInteger count4 = new AtomicInteger(1);
            sb.append("新增节点: ");
            addedNodes.forEach(node -> sb.append(count4.getAndIncrement()).append("、 ").append(node.getName()).append(" "));
            //加入区分换行
            sb.append(". \\");
        }

        // 找到删除的节点
//        List<NodeEntity> removedNodes = oldNodes.stream()
//                .filter(oldNode -> newNodes.stream().noneMatch(newNode -> newNode.getName().equals(oldNode.getName())))
//                .collect(Collectors.toList());
//        if (!removedNodes.isEmpty()) {
//            AtomicInteger count5 = new AtomicInteger(1);
//            sb.append("删除节点: ");
//            removedNodes.forEach(node -> sb.append(count5.getAndIncrement()).append("、 ").append(node.getName()).append(" "));
//            //加入区分换行
//            sb.append(". \\");
//        }

        AtomicInteger count6 = new AtomicInteger(1);
        AtomicInteger count7 = new AtomicInteger(1);
        // 找到修改的节点
        oldNodes.forEach(oldNode -> newNodes.stream()
                .filter(newNode -> newNode.getName().equals(oldNode.getName()))
                .forEach(newNode -> {
                    //对表单文件的更改情况
                    if (!newNode.getType().equals(oldNode.getType())) {

                        sb.append(newNode.getName());
//                        sb.append(count6.getAndIncrement())
                        sb.append(" ").append(" 表单文件的更改: ");

                        //当oldNode.getType() == "no"说明原本无文件绑定, 将 List<String> oldFormNames add "原无文件绑定"
                        //否则将oldNode.getType() 得到的 ids 替换为 filenames
                        List<String> oldFormNames;
                        if ("no".equals(oldNode.getType())) {
                            oldFormNames = new ArrayList<>();
                            oldFormNames.add("原无文件绑定");
                        } else {
                            oldFormNames = formInfoTableService.selectFileNamesByIds(oldNode.getType());
                            System.out.println("oldFormNames ==>" + oldFormNames);
                        }

                        //当newNode.getType()== null，将newFormNames设置为"取消文件绑定"
                        //否则将newNode 得到的 ids 替换为 filenames
                        List<String> newFormNames = formInfoTableService.selectFileNamesByIds(newNode.getType());
                        System.out.println("newFormNames size==>" + newFormNames.size());
                        if (newFormNames.size() == 0) {
                            newFormNames = new ArrayList<>();
                            newFormNames.add("取消文件绑定");
                        }
                        System.out.println("newFormNames ==>" + newFormNames);

                        //sb.append("文件(旧值): ").append(oldNode.getType()).append(" 更改为文件(新值): ").append(newNode.getType()).append(" ");
                        sb.append("文件(旧值): ").append(oldFormNames).append(" 更改为文件(新值): ").append(newFormNames).append(" ");

                        //加入区分换行
                        sb.append(". \\");
                    }

                    //对制度文件的更改情况
                    if (!newNode.getState().equals(oldNode.getState())) {
                        sb.append(newNode.getName());
//                        sb.append(count7.getAndIncrement());
                        sb.append(" ").append(" 制度文件的更改: ");

                        //当oldNode.getState() == "no"说明原本无文件绑定, 将 List<String> oldFileName add "原无文件绑定"
                        // 否则将oldNode.getState() 得到的 ids 替换为 filenames
                        List<String> oldFileName;
                        if ("no".equals(oldNode.getState())) {
                            oldFileName = new ArrayList<>();
                            oldFileName.add("原无文件绑定");
                        } else {
                            oldFileName = regulationsInfoTableService.selectFileNamesByIds(oldNode.getState());
                            System.out.println("oldFileName ==>" + oldFileName);
                        }

                        //当newNode.getState() == "no"说明原本无文件绑定, 将 List<String> newFileName add "取消文件绑定"
                        // 否则将newNode.getState() 得到的 ids 替换为 filenames
                        List<String> newFileName = regulationsInfoTableService.selectFileNamesByIds(newNode.getState());
                        System.out.println("newFileName size==>" + newFileName.size());
                        if (newFileName.size() == 0) {
                            newFileName = new ArrayList<>();
                            newFileName.add("取消文件绑定");
                        }
                        System.out.println("newFileName ==>" + newFileName);

                        //sb.append(" 的文件(旧值): ").append(oldNode.getState()).append(" 更改为文件(新值): ").append(newNode.getState()).append(" ");
                        sb.append(" 的文件(旧值): ").append(oldFileName).append(" 更改为文件(新值): ").append(newFileName).append(" ");
                        //加入区分换行
                        sb.append(". \\");
                    }
                }));

        return sb.toString();
    }

    private String compareLines(List<LineEntity> oldLines, List<LineEntity> newLines, List<NodeEntity> newNodes, List<NodeEntity> oldNodes) {
        StringBuilder sb = new StringBuilder();

        // 获取所有节点名称到ID的映射
        Map<String, String> oldNodeMap = oldNodes.stream().collect(Collectors.toMap(NodeEntity::getId, NodeEntity::getName));
        Map<String, String> newNodeMap = newNodes.stream().collect(Collectors.toMap(NodeEntity::getId, NodeEntity::getName));

        // 找到新增的连线
        List<LineEntity> addedLines = newLines.stream()
                .filter(newLine -> oldLines.stream().noneMatch(oldLine ->
                        oldNodeMap.get(oldLine.getFrom()).equals(newNodeMap.get(newLine.getFrom())) &&
                                oldNodeMap.get(oldLine.getTo()).equals(newNodeMap.get(newLine.getTo()))))
                .collect(Collectors.toList());
        if (!addedLines.isEmpty()) {
            AtomicInteger count1 = new AtomicInteger(1);
            sb.append("新增连线: ");
            addedLines.forEach(line -> sb.append(count1.getAndIncrement()).append("、 ").append(" 从: [").append(newNodeMap.get(line.getFrom())).append("] 到: [").append(newNodeMap.get(line.getTo())).append("] "));
            //加入区分换行
            sb.append(". \\");
        }

        // 找到删除的连线
        List<LineEntity> removedLines = oldLines.stream()
                .filter(oldLine -> newLines.stream().noneMatch(newLine ->
                        oldNodeMap.get(oldLine.getFrom()).equals(newNodeMap.get(newLine.getFrom())) &&
                                oldNodeMap.get(oldLine.getTo()).equals(newNodeMap.get(newLine.getTo()))))
                .collect(Collectors.toList());
        if (!removedLines.isEmpty()) {
            AtomicInteger count2 = new AtomicInteger(1);
            sb.append("删除连线: ");
            removedLines.forEach(line -> sb.append(count2.getAndIncrement()).append("、 ").append(" 从: ").append(oldNodeMap.get(line.getFrom())).append(" 到: ").append(oldNodeMap.get(line.getTo())).append(" "));
            //加入区分换行
            sb.append(". \\");
        }

//        AtomicInteger count3 = new AtomicInteger(1);
        // 找到修改的连线
//        oldLines.forEach(oldLine -> newLines.stream()
//                .filter(newLine ->
//                        oldNodeMap.get(oldLine.getFrom()).equals(newNodeMap.get(newLine.getFrom())) &&
//                                oldNodeMap.get(oldLine.getTo()).equals(newNodeMap.get(newLine.getTo())) &&
//                                !newLine.getLabel().equals(oldLine.getLabel()))
//                .forEach(newLine -> sb.append(count3.getAndIncrement()).append("、 ").append("修改连线条件: ").append(newLine.getId())
//                        .append(" 从: ").append(oldNodeMap.get(oldLine.getFrom()))
//                        .append(" 到: ").append(oldNodeMap.get(oldLine.getTo()))
//                        .append(" 旧值: ").append(oldLine.getLabel())
//                        .append(" 新值: ").append(newLine.getLabel()).append(" ")));
//
//        //加入区分换行
//        sb.append(" \\");

        return sb.toString();
    }
//    ------------------------------------------------------

    /**
     * 指标：流程更新次数
     */
    @PostMapping("/indicators/updateCounts")
    public List<IndicatorRespondEntity> updateCounts(@RequestBody ProjectEntity time) {
        System.out.println("time start ==>" + time);
        List<IndicatorRespondEntity> list = projectService.selectIndicatorsUpdateTimes(time.getStartTime(), time.getEndTime(), time.getId());
        return list;
    }


    /**
     * 查询所有流程基本信息
     *
     * @param
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:manage:test')")
    @GetMapping("/list")
    public List<ProjectEntity> list() {
        List<ProjectEntity> projectEntitys = projectService.queryDatas();
        return projectEntitys;
    }

    /**
     * 给panel无权限使用的
     * @return
     */
    @GetMapping("/list/list")
    public List<ProjectEntity> list2() {
        List<ProjectEntity> projectEntitys = projectService.queryDatas();
        return projectEntitys;
    }

    /**
     * 按照name查询流程
     *
     * @param
     * @return newest == 1
     */
    @PostMapping("/searchList")
    public List<ProjectEntity> searchList(@RequestBody ProjectEntity projectEntity) {
//        System.out.println("searchList projectEntity ==>" + projectEntity);
        //精准查询
//        List<ProjectEntity> projectEntitys = projectService.getBaseMapper().selectList(new QueryWrapper<ProjectEntity>().eq("name", projectEntity.getName()).eq("newest", 1));
        //模糊查询


//对name 进行模糊查询
//        List<ProjectEntity> projectEntitys = projectService.getBaseMapper().selectList(new QueryWrapper<ProjectEntity>().like("name", projectEntity.getName()).eq("newest", 1));

        //对name、department、level三个字段 进行模糊查询
        List<ProjectEntity> projectEntitys = projectService.getBaseMapper().selectList(
                new QueryWrapper<ProjectEntity>()
                        .eq("newest", 1)
                        .and(wrapper ->
                                wrapper.like("name", projectEntity.getName())
                                        .or()
                                        .like("department", projectEntity.getDepartment())
                                        .or()
                                        .like("level", projectEntity.getLevel())
                        )
        );


        System.out.println("projectEntitys ==>" + projectEntitys);
        return projectEntitys;
    }

    /**
     * 按照制度文件的 file name查询流程
     *
     * @param
     * @return newest == 1
     */
    @PostMapping("/searchListByFileName")
    public List<ProjectEntity> searchListByFileName(@RequestBody ProjectEntity projectEntity) {
        System.out.println("searchListByFileName projectEntity ==>" + projectEntity);
        //精准查询
        // List<ProjectEntity> projectEntitys = projectService.getBaseMapper().selectList(new QueryWrapper<ProjectEntity>().eq("name", projectEntity.getName()).eq("newest", 1));
        //模糊查询
        // 获取
        String filename = projectEntity.getFilename();
        // 在制度文件中按照 filename 进行 模糊查询
        RegulationsInfoTable regulationsInfoTable = new RegulationsInfoTable();
        regulationsInfoTable.setFileName(filename);
        List<RegulationsInfoTable> regulationsFileList = regulationsInfoTableService.selectRegulationsInfoTableList(regulationsInfoTable);
        System.out.println("regulationsFileList==" + regulationsFileList);
        //将regulationsFileList中的regulationsId加入到 Long fileIds[]
        Long fileIds[] = regulationsFileList.stream().map(RegulationsInfoTable::getRegulationsId).toArray(Long[]::new);
        for (Long fileId : fileIds) {
            System.out.println("fileId==" + fileId);
        }


        /**
         * state为 String类型,先将其转换为[],再与fileIds进行对比, 返回满足条件的ProjectEntity
         * 对projectEntitys进行遍历
         * 将state转换为[]
         * 将stateArrayLong与fileIds进行对比
         * 将满足条件的ProjectEntity加入到res中
         */
        List<ProjectEntity> projectEntitys = projectService.queryDatas();

        // 根据 fileIds查询 projectEntitys 中的 state 中包含 fileIds的流程Entity
        List<ProjectEntity> filteredProjectEntities = projectService.filterProjectEntities(fileIds, projectEntitys);

        // 打印结果
        if (filteredProjectEntities != null) {
            for (ProjectEntity entity : filteredProjectEntities) {
                System.out.println("Matched ProjectEntity with state: " + entity.getState());
            }
        } else {
            System.out.println("No matching ProjectEntity found.");
        }

        System.out.println("filteredProjectEntities ==>" + filteredProjectEntities);
        return filteredProjectEntities;
    }


    /**
     * 查询指定id的完整流程信息json：由id查询project、node、line
     *
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @GetMapping("/data/{id}")
    public R<ProjectVo> data(@PathVariable("id") String id) {
        ProjectVo projectVo = projectService.queryData(id);
        System.out.println("getById projectVo ==>" + projectVo);
        return new R<ProjectVo>().setCode(0).setData(projectVo);
    }

    @PreAuthorize("@ss.hasPermi('system:project:update')")
    @GetMapping("/data1/{id}")
    public R<ProjectVo> data1(@PathVariable("id") String id) {
        ProjectVo projectVo = projectService.queryData(id);
        System.out.println("getById projectVo ==>" + projectVo);
        return new R<ProjectVo>().setCode(0).setData(projectVo);
    }

    @PreAuthorize("@ss.hasPermi('system:project:view')")
    @GetMapping("/data2/{id}")
    public R<ProjectVo> data2(@PathVariable("id") String id) {
        ProjectVo projectVo = projectService.queryData(id);
        System.out.println("getById projectVo ==>" + projectVo);
        return new R<ProjectVo>().setCode(0).setData(projectVo);
    }

    @GetMapping("/data0/{id}")
    public R<ProjectVo> data0(@PathVariable("id") String id) {
        ProjectVo projectVo = projectService.queryData(id);
        System.out.println("getById projectVo ==>" + projectVo);
        return new R<ProjectVo>().setCode(0).setData(projectVo);
    }

    /**
     * 查询指定id流程的 历史相关流程列表
     */
    @GetMapping("/historyList/{id}")
    public List<ProjectEntity> historyList(@PathVariable("id") String id) {
        // 查询流程中绑定的历史流程 [oldVersion] == id. 查询的结果不包含当前的流程记录, 只包含其历史流程记录.
        List<ProjectEntity> projectEntities = projectService.queryHistoryDatas(id);
        return projectEntities;
    }


    /**
     * 新增
     *
     * @param project
     * @return
     */
    @PreAuthorize("@ss.hasPermi('process:ef:add')")
    @PostMapping("/saveProject")
    public R save(@RequestBody ProjectEntity project) {
        //保存流程还需要进行验证是否当前流程已存在(流程名重复) --> 在前端实现了

        //创建人
        String createBy = project.getCreateBy();
        System.out.println("vue传递过来的创建人===》" + createBy);
//        System.out.println("save project ===>"+project);
        //日期--对应的字段为[创建时间]
        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        project.setCreateDate(dateTime.format(formatter));
        //创建新流程时，即为最新版本
        project.setNewest(1);
        projectService.save(project);
        return success();
    }

    /**
     * 更新 project
     * 对比功能，需要管理原始的 project主键
     *
     * @param project
     * @return
     */

    @PostMapping("/updateProjectFromOld")
    public R update(@RequestBody ProjectEntity project) {

//        projectService.updateById(project);
        System.out.println("update project==>" + project);
        //更新人
//        String updateBy = project.getUpdateBy();
//        System.out.println("vue传递过来的更新人 ===》 "+updateBy);


        //根据oldId将标志位 newest 1->0
        ProjectEntity oldProject = projectService.getById(project.getOldVersion());
        oldProject.setNewest(0);
        //获取原始的[创建时间、创建人]
        String createDate = oldProject.getCreateDate();
        String createBy = oldProject.getCreateBy();
        projectService.updateById(oldProject);


        // 新增的日期为[修改时间]
        LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        project.setUpdateDate(dateTime.format(formatter));
        //标记当前的为最新的流程版本
        project.setNewest(1);
        project.setCreateDate(createDate);
        project.setCreateBy(createBy);

        // 处理state -> 绑定的[制度文件ids] -> 其序列化和反序列化
        if (project.getState() != null && project.getState().length() != 0) {
            String stateStr = project.getState();
            // 检查并移除首尾双引号
            if (stateStr.startsWith("\"") && stateStr.endsWith("\"")) {
                stateStr = stateStr.substring(1, stateStr.length() - 1);
            }
            List<Integer> list = JSONObject.parseArray(stateStr, Integer.class);
            project.setState(list.toString());
        }

        // 处理type -> 绑定的[表单文件ids] -> 同上
        if (project.getType() != null && project.getType().length() != 0) {
            String typeStr = project.getType();
            // 检查并移除首尾双引号
            if (typeStr.startsWith("\"") && typeStr.endsWith("\"")) {
                typeStr = typeStr.substring(1, typeStr.length() - 1);
            }
            List<Integer> list2 = JSONObject.parseArray(typeStr, Integer.class);
            project.setType(list2.toString());
        }

        projectService.save(project);
        return success();
    }

    /**
     * 删除流程
     *
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:project:delete')")
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable("id") String id) {
        System.out.println("删除的id为" + id);
        projectService.delete(id);
        //接口层同时删除了  与流程project相关的node、line
        return success();
    }

    /**
     * 删除单个的历史流程
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteHistory/{id}")
    public R deleteHistory(@PathVariable("id") String id) {

        projectService.deleteHistory(id);
        //接口层同时删除了  与流程project相关的node、line
        return success();
    }


    /**
     * 更新 project
     *
     * @param
     * @return
     */
    @PostMapping("/updateProjectName")
    public R updateProjectName(@RequestBody ProjectEntity projectEntity) {
        System.out.println("params==" + projectEntity);
        //根据id字段将名称更改为name字段
        ProjectEntity getByIdEntity = projectService.getById(projectEntity.getId());
        getByIdEntity.setName(projectEntity.getName());
        //流程编号 number;
        //主责部门 department;
        //流程等级 level;
        //流程目的 purpose;
        //适用范围 applicationScope;
        getByIdEntity.setNumber(projectEntity.getNumber());
        getByIdEntity.setDepartment(projectEntity.getDepartment());
        getByIdEntity.setLevel(projectEntity.getLevel());
        getByIdEntity.setPurpose(projectEntity.getPurpose());
        getByIdEntity.setApplicationScope(projectEntity.getApplicationScope());



        projectService.updateById(getByIdEntity);

        return success();
    }

    /**
     *
     */
    @GetMapping("/projectName/{id}")
    public String projectName(@PathVariable("id") String id) {
        // 查询流程中绑定的历史流程 [oldVersion] == id. 查询的结果不包含当前的流程记录, 只包含其历史流程记录.
        ProjectEntity byId = projectService.getById(id);
        System.out.println("find by by entity ==>" + byId);
        String name = byId.getName();
        return name;
    }


}
