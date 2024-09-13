package com.ruoyi.ef.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ruoyi.ef.dao.ProjectDao;
import com.ruoyi.ef.entity.IndicatorRespondEntity;
import com.ruoyi.ef.entity.LineEntity;
import com.ruoyi.ef.entity.NodeEntity;
import com.ruoyi.ef.entity.ProjectEntity;
import com.ruoyi.ef.service.ILineService;
import com.ruoyi.ef.service.INodeService;
import com.ruoyi.ef.service.IProjectService;
import com.ruoyi.ef.vo.ProjectVo;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:30
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements IProjectService {

    @Autowired
    private INodeService nodeService;
    @Autowired
    private ILineService lineService;
    @Autowired
    private ProjectDao projectDao;


    @Override
    public ProjectVo queryPreData(String projectId) {
        //当前project
        ProjectEntity projectEntity = getById(projectId);
        //preProject
        String preId = projectEntity.getOldVersion();
        ProjectEntity proProjectEntity = getById(preId);

        if (proProjectEntity == null) {
            throw new IllegalArgumentException("proProjectEntity项目不存在");
        }

        List<NodeEntity> nodeList = nodeService.queryByProjectId(preId);
        List<LineEntity> lineList = lineService.queryByProjectId(preId);

        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(preId);
        projectVo.setName(proProjectEntity.getName());
        projectVo.setNodeList(nodeList);
        projectVo.setLineList(lineList);

        return projectVo;
    }


    public List<IndicatorRespondEntity> selectIndicatorsUpdateTimes(Date startTime, Date endTime, String projectId) {
//        System.out.println("== startTime:" + startTime + " == endTime:" + endTime);


//        try {
//            // 创建日期格式化对象，指定目标日期字符串的格式
//            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            // 将Date对象格式化为目标日期字符串
//            String formattedStartDate = targetFormat.format(startTime);
//            String formattedEndDate = targetFormat.format(endTime);
//
//            System.out.println("Converted1 start Date: " + formattedStartDate);//Converted1 start Date: 2024-01-01
//            System.out.println("Converted1 end Date: " + formattedEndDate);// Converted1 end Date: 2024-05-31
//
//            // 将格式化后的日期字符串解析为Date对象
//            Date convertedStartDate = targetFormat.parse(formattedStartDate);
//            Date convertedEndDate = targetFormat.parse(formattedEndDate);
//
//            // 输出结果
//            System.out.println("Converted2 start Date: " + convertedStartDate);//Converted2 start Date: Mon Jan 01 00:00:00 CST 2024
//            System.out.println("Converted2 end Date: " + convertedEndDate);//Converted2 end Date: Fri May 31 00:00:00 CST 2024
//
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        //根据传递的historyId查询当前流程的历史流程list(包括其自身)
        List<ProjectEntity> historyProjects = queryHistoryAllDatas(projectId);

        int count = 1;
        for (ProjectEntity historyProject : historyProjects) {
            System.out.println("historyProject==> count ==>" + count + " //" + historyProject);
            count++;
        }

        //根据传递的时间区间 [startTime, endTime] 进行统计数据
        //example：
        //historyProject==> count ==>1 //ProjectEntity( id=KkQWMxQC7oq74Uvg6ns-0, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=2024-05-06 14:35:49.0, updateBy=admin/研发, oldVersion=e9wi3CUTHpl4O9L14WS6o, newest=1, state=[193], type=[149])
        //historyProject==> count ==>2 //ProjectEntity( id=e9wi3CUTHpl4O9L14WS6o, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=2024-05-06 14:32:53.0, updateBy=admin/研发, oldVersion=MR_gLCUwVKtcjWE1gXwwL, newest=0, state=null, type=null)
        //historyProject==> count ==>3 //ProjectEntity( id=MR_gLCUwVKtcjWE1gXwwL, name=test001, createDate=2024-05-06 14:32:22.0, createBy=admin/研发, updateDate=null, updateBy=null, oldVersion=null, newest=0, state=null, type=null)

        // 过滤规则：去掉updateDate=null的数据(这是第一次创建的流程), then 根据updateDate进行统计次数(按照MM-dd进行统计), 同时还需要保留原始的updateDate作为详细的时间节点作为返回值的一个属性
        try {
            List<IndicatorRespondEntity> res = new ArrayList<>();

            Map<String, IndicatorRespondEntity> entityMap = new HashMap<>();

            // 创建包含指定时间范围内所有年份和月份的 [startTime, endTime] --> IndicatorRespondEntity 对象
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            while (calendar.getTime().before(endTime) || calendar.getTime().equals(endTime)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                String yearMonth = dateFormat.format(calendar.getTime());
                IndicatorRespondEntity entity = new IndicatorRespondEntity();
                entity.setYearAndMonth(yearMonth);
                //默认数值counts = 0
                entity.setTimes(0);
                entityMap.put(yearMonth, entity);
                calendar.add(Calendar.MONTH, 1);
            }


            //正式 统计历史流程数据, 按照year-month进行统计更改次数counts
            for (ProjectEntity project : historyProjects) {
                System.out.println("project==>" + project);
                String updateDate = project.getUpdateDate();


                // 转换string --> date. 跳过updateDate为null的数据.
                if (updateDate == null) {
                    continue;
                } else {
                    //else 创建日期格式化对象，指定目标日期字符串的格式
                    SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM");
                    // 将格式化后的日期字符串解析为Date对象
                    Date convertedUpdateDate = tf.parse(updateDate);
                    // == convertedUpdateDate ==>Thu May 09 00:00:00 CST 2024
                    // == convertedUpdateDate ==>Tue May 07 00:00:00 CST 2024
                    // == convertedUpdateDate ==>Tue May 07 00:00:00 CST 2024
//                    System.out.println(" == convertedUpdateDate ==>" + convertedUpdateDate);

                    // 检查updateDate是否在时间区间内、equals
                    if ((convertedUpdateDate.toInstant().isAfter(startTime.toInstant()) && convertedUpdateDate.toInstant().isBefore(endTime.toInstant()))
                            || convertedUpdateDate.toInstant().equals(startTime.toInstant()) || convertedUpdateDate.toInstant().equals(endTime.toInstant())
                    ) {

                        // 提取年份和月份
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                        String yearMonth = dateFormat.format(convertedUpdateDate);

                        // 从Map中获取对应的IndicatorRespondEntity对象，并更新计数
                        IndicatorRespondEntity entity = entityMap.get(yearMonth);
                        if (entity != null) {
                            entity.setTimes(entity.getTimes() + 1); // 更新计数
                            entity.addUpdateDate(updateDate); // 添加具体的日期--updateDate
                        }
                    }
                }
            }

            // 将Map中的所有值添加到结果列表中
            res.addAll(entityMap.values());

            // 对结果列表按照yearAndMonth字段进行排序
            Collections.sort(res, new Comparator<IndicatorRespondEntity>() {
                @Override
                public int compare(IndicatorRespondEntity o1, IndicatorRespondEntity o2) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                    try {
                        Date date1 = dateFormat.parse(o1.getYearAndMonth());
                        Date date2 = dateFormat.parse(o2.getYearAndMonth());
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid date format", e);
                    }
                }
            });

            for (IndicatorRespondEntity re : res) {
                System.out.println("res ==>  " + re);
            }

            //做封装处理返回respond
            return res;


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //返回过滤后的数据

    }

    /**
     * 查询所有的记录 且 最新的记录
     *
     * @return
     */
    public List<ProjectEntity> queryDatas() {
        List<ProjectEntity> projectEntities = projectDao.selectList(null);
        List<ProjectEntity> filteredEntities = new ArrayList<>();

        for (ProjectEntity entity : projectEntities) {
            if (entity.getNewest() != null && entity.getNewest() == 1) {
                filteredEntities.add(entity);
            }
        }

        return filteredEntities;
    }

    public List<ProjectEntity> queryDatasByTime(Date startTime, Date endTime) {
//        System.out.println("hhh startTime==>" + startTime);
//        System.out.println("hhh endTime==>" + endTime);
        //hhh startTime==>Mon Jan 01 00:00:00 CST 2024
        //hhh endTime==>Mon Sep 30 00:00:00 CST 2024
        List<ProjectEntity> projectEntities = projectDao.selectList(null);
        List<ProjectEntity> filteredEntities = new ArrayList<>();


        for (ProjectEntity entity : projectEntities) {
            if (entity.getNewest() != null && entity.getNewest() == 1 ) {
                //添加在时间区间内的数据
                String createDate = entity.getCreateDate();
                //2024-08-03 12:32:06
                //需要将createDate转换为Date类型再比较
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(createDate);
                    //判断时间是否在时间区间内
                    if (date.toInstant().isAfter(startTime.toInstant()) && date.toInstant().isBefore(endTime.toInstant())) {
                        filteredEntities.add(entity);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


            }
        }
        System.out.println("hhhh filteredEntities==>" + filteredEntities);
        return filteredEntities;
    }


    /**
     * 根据id查询流程(单条记录)
     */
    @Override
    public ProjectVo queryData(String projectId) {
        ProjectEntity projectEntity = getById(projectId);
        if (projectEntity == null) {
            throw new IllegalArgumentException("项目不存在");
        }
        List<NodeEntity> nodeList = nodeService.queryByProjectId(projectId);
        List<LineEntity> lineList = lineService.queryByProjectId(projectId);
        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(projectId);
        projectVo.setName(projectEntity.getName());
        //流程编号 number;
        //主责部门 department;
        //流程等级 level;
        //流程目的 purpose;
        //适用范围 applicationScope;
        projectVo.setNumber(projectEntity.getNumber());
        projectVo.setDepartment(projectEntity.getDepartment());
        projectVo.setLevel(projectEntity.getLevel());
        projectVo.setPurpose(projectEntity.getPurpose());
        projectVo.setApplicationScope(projectEntity.getApplicationScope());
        //业务模块名称 businessesModules
        //细分业务名称 subBusinesses
        projectVo.setBusinessesModules(projectEntity.getBusinessesModules());
        projectVo.setSubBusinesses(projectEntity.getSubBusinesses());

        projectVo.setNodeList(nodeList);
        projectVo.setLineList(lineList);
        return projectVo;
    }

    /**
     * 根据id查询其历史流程：不包括当前流程, 只包括其关联的历史流程
     */
    public List<ProjectEntity> queryHistoryDatas(String projectId) {
        List<ProjectEntity> filteredEntities = new ArrayList<>();
        //查找历史记录，传递的是当前最新一条的流程的id，他的上一条历史记录id = getOldVersion()
        //然后一直迭代，直到第一个历史版本的记录，其getOldVersion() ==   null
        ProjectEntity projectEntity = getById(projectId);
        if (projectEntity == null) {
            throw new IllegalArgumentException("项目不存在");
        }
        String oldVersion = projectEntity.getOldVersion();

        while (!StringUtils.isBlank(oldVersion)) {
            projectEntity = getById(oldVersion);
            if (projectEntity == null) {
                break;
            }
            if (projectEntity.getNewest() == 0) {
                //将符合历史记录的流程加入返回数据中
                filteredEntities.add(projectEntity);
            }
            oldVersion = projectEntity.getOldVersion();
        }
        return filteredEntities;
    }

    /**
     * 根据id查询其历史流程+流程自身
     */
    public List<ProjectEntity> queryHistoryAllDatas(String projectId) {
        List<ProjectEntity> filteredEntities = new ArrayList<>();
        //查找历史记录，传递的是当前最新一条的流程的id，他的上一条历史记录id = getOldVersion()
        //然后一直迭代，直到第一个历史版本的记录，其getOldVersion() ==   null
        ProjectEntity projectEntity = getById(projectId);
        filteredEntities.add(projectEntity);
        if (projectEntity == null) {
            throw new IllegalArgumentException("项目不存在");
        }
        String oldVersion = projectEntity.getOldVersion();

        while (!StringUtils.isBlank(oldVersion)) {
            projectEntity = getById(oldVersion);
            if (projectEntity == null) {
                break;
            }
            if (projectEntity.getNewest() == 0) {
                //将符合历史记录的流程加入返回数据中
                filteredEntities.add(projectEntity);
            }
            oldVersion = projectEntity.getOldVersion();
        }


        return filteredEntities;
    }


    @Override
    public void delete(String projectId) {
        removeById(projectId);
        nodeService.deleteByProjectId(projectId);
        lineService.deleteByProjectId(projectId);
    }

    public void deleteHistory(String projectId) {

        ProjectEntity projectEntity = getById(projectId);

        List<ProjectEntity> projectEntities = projectDao.selectList(null);
        for (ProjectEntity entity : projectEntities) {
            //将其上一个流程的old_version更新为其old_version字段
            if (!StringUtils.isBlank(entity.getOldVersion()) && entity.getOldVersion().equals(projectId)) {
                entity.setOldVersion(projectEntity.getOldVersion());
                updateById(entity);
                break;
            }
        }
        //删除当前流程
        removeById(projectId);
        nodeService.deleteByProjectId(projectId);
        lineService.deleteByProjectId(projectId);

    }


    @Override
    public List<ProjectEntity> queryNewestEqualsOneDatas() {
        List<ProjectEntity> projectEntities = projectDao.queryDatas();
        return projectEntities;
    }

    @Override
    public List<ProjectEntity> filterProjectEntities(Long[] fileIds, List<ProjectEntity> projectEntitys) {
        // 判断fileIds和projectEntitys是否为空
        if (fileIds == null || fileIds.length == 0 || projectEntitys == null || projectEntitys.isEmpty()) {
            return null;
        }

        List<ProjectEntity> res = new ArrayList<>();

        // 遍历projectEntitys
        for (ProjectEntity entity : projectEntitys) {
            String state = entity.getState();

            // 判断state是否为null
            if (state == null) {
                continue;
            }

            // 去掉state中的方括号，并将state转换为与fileIds一致的Long数组
            state = state.replaceAll("[\\[\\]]", "");
            String[] stateArray = state.split(",");
            Long[] stateArrayLong = new Long[stateArray.length];
            try {
                for (int i = 0; i < stateArray.length; i++) {
                    stateArrayLong[i] = Long.parseLong(stateArray[i].trim());
                }
            } catch (NumberFormatException e) {
                // 如果转换过程中出现异常，跳过此entity
                continue;
            }

            // 将stateArrayLong与fileIds进行对比
            for (Long fileId : fileIds) {
                for (Long stateId : stateArrayLong) {
                    if (stateId.equals(fileId)) {
                        res.add(entity);
                        break;
                    }
                }
                // 如果已经找到匹配的fileId，跳出外层循环
                if (res.contains(entity)) {
                    break;
                }
            }
        }

        return res;

    }

}
