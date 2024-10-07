package com.heli.tech.service;

import java.util.Date;
import java.util.List;

import com.heli.tech.domain.Tech;
import com.heli.tech.domain.TechNewProjectCompletionDTO;
import com.heli.tech.domain.TechNewProjectProgressDTO;
import com.heli.tech.domain.TechNonStandardDisplayDTO;
import com.ruoyi.common.core.domain.DisplayEntity;

/**
 * [技术]指标填报Service接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface ITechService {

    int insertOrUpdateNonStandardData(Tech tech);

    void insertOrUpdateProjectData(Tech tech);


    Boolean checkTechMonthlyDataIsExisted(Date date);

    Boolean checkDataExist();


//    public Tech calculateCompletionRate(Tech tech);

//    public int batchUpdateTech(Date date);

    boolean checkTechMonthlyDataIsExistedByYear(Integer naturalYear);

    List<TechNonStandardDisplayDTO> selectNonStandardAVGDaysAndNum(Date startTime, Date endTime);

    List<TechNewProjectProgressDTO> selectProjectProgress(Date startTime, Date endTime);

    List<TechNewProjectCompletionDTO> selectProjectCompletion(Date startTime, Date endTime);

    int updateTechAllData();





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


    Date selectMaxMonth();

    Tech selectTechByDate(Date date);
}


