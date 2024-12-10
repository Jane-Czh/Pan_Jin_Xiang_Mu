package com.ruoyi.ef.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.ruoyi.ef.entity.IndicatorRespondEntity;
import com.ruoyi.ef.entity.ProjectEntity;
import com.ruoyi.ef.vo.ProjectVo;
import com.ruoyi.file.entity.regulationCountsByClassificationRespondEntity;


import java.util.Date;
import java.util.List;

/**
 * @author liuchengbiao
 * @date 2020-05-22 21:28
 */

public interface IProjectService extends IService<ProjectEntity> {


    /**
     * 根据传进的id，查询其上一条流程数据
     */
    ProjectVo queryPreData(String projectId);

    /**
     * 根据传入的时间参数，对符合参数区间的流程数据进行统计
     *
     * @return
     */
    List<IndicatorRespondEntity> selectIndicatorsUpdateTimes(Date startTime, Date endTime, String id);

    /**
     * 获取所有流程数据(最新newest == 1)
     * @return
     */
    List<ProjectEntity> queryDatas();

    /**
     * 根据时间区间查找所有流程数据
     */
    List<ProjectEntity> queryDatasByTime(Date startTime, Date endTime);

    /**
     * 获取指定id流程的相关历史流程数据
     * @param projectId
     * @return
     */
    List<ProjectEntity> queryHistoryDatas(String projectId);

    /**
     * 1210 新增修改
     * 获取指定时间区间内，不同分类下的流程数据统计
     * @param startTime
     * @param endTime
     * @param mainResponsibleDepartment
     * @return
     */
    List<regulationCountsByClassificationRespondEntity> getProcessCountsByClassification(Date startTime, Date endTime,String mainResponsibleDepartment);

    /**
     * 获取流程数据,包括 project、node、line
     *
     * @param projectId
     * @return
     */
    ProjectVo queryData(String projectId);


    /**
     * 删除流程
     * @param projectId
     */
    void delete(String projectId);

    /**
     * 删除历史流程
     * @param id
     */
    void deleteHistory(String id);

    /**
     * 自定义的mapper方法
     */
    List<ProjectEntity> queryNewestEqualsOneDatas();

    public List<ProjectEntity> filterProjectEntities(Long[] fileIds, List<ProjectEntity> projectEntitys);
}
