package com.heli.tech.service;

import java.util.Date;
import java.util.List;

import com.heli.tech.domain.Tech;
import com.ruoyi.common.core.domain.DisplayEntity;

/**
 * [技术]指标填报Service接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface ITechService {

    Boolean checkTechMonthlyDataIsExisted(Date date);


    public Tech calculateCompletionRate(Tech tech, Long annualNumber);

    public int batchUpdateTech(Date date);


    List<DisplayEntity> selectNonStandardAVGPreparationDays(Date startTime, Date endTime);
    List<DisplayEntity> selectPRDScheduleCompletionRate(Date startTime, Date endTime);


    /**
     * 查询[技术]指标填报
     *
     * @param techId [技术]指标填报主键
     * @return [技术]指标填报
     */
    public Tech selectTechByTechId(Long techId);

    /**
     * 查询[技术]指标填报列表
     *
     * @param tech [技术]指标填报
     * @return [技术]指标填报集合
     */
    public List<Tech> selectTechList(Tech tech);

    /**
     * 新增[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    public int insertTech(Tech tech);

    /**
     * 修改[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    public int updateTech(Tech tech);

    /**
     * 批量删除[技术]指标填报
     *
     * @param techIds 需要删除的[技术]指标填报主键集合
     * @return 结果
     */
    public int deleteTechByTechIds(Long[] techIds);

    /**
     * 删除[技术]指标填报信息
     *
     * @param techId [技术]指标填报主键
     * @return 结果
     */
    public int deleteTechByTechId(Long techId);

    boolean checkTechMonthlyDataIsExistedByYear(Integer naturalYear);
}


