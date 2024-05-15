package com.heli.tech.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.tech.mapper.TechMapper;
import com.heli.tech.domain.Tech;
import com.heli.tech.service.ITechService;

/**
 * [技术]指标填报Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class TechServiceImpl implements ITechService {


    @Autowired
    private TechMapper techMapper;


    /**
     * @description: 新增数据
     * @author: hong
     * @date: 2024/4/24 14:10
     **/
    @Override
    public int insertTech(Tech tech) {
        tech.setCreateTime(DateUtils.getNowDate());
        return techMapper.insertTech(tech);
    }


    /**
     * @description: 计算研发项目计划进度完成率
     * @author: hong
     * @date: 2024/4/27 11:25
     **/
    public Tech calculateCompletionRate(Tech tech, Long annualNumber) {
        Long annualCompletionNumber = techMapper.countAnnualCompletionNumber(DateUtils.getYear(tech.getYearAndMonth())) + tech.getCompletedmonthlyPlancounts();
        tech.setPrdscheduleCompletionrate(BigDecimal.valueOf((annualCompletionNumber.doubleValue() / annualNumber.doubleValue()) * 100));
        return tech;
    }

    @Override
    public List<DisplayEntity> selectNonStandardAVGPreparationDays(Date startTime, Date endTime) {
        return techMapper.selectNonStandardAVGPreparationDays(startTime, endTime);
    }

    @Override
    public List<DisplayEntity> selectPRDScheduleCompletionRate(Date startTime, Date endTime) {
        return techMapper.selectPRDScheduleCompletionRate(startTime, endTime);
    }


    @Override
    public Boolean checkTechMonthlyDataIsExisted(Date date) {
        return techMapper.checkTechMonthlyDataIsExisted(date);
    }

    /**
     * 查询[技术]指标填报
     *
     * @param techId [技术]指标填报主键
     * @return [技术]指标填报
     */
    @Override
    public Tech selectTechByTechId(Long techId) {
        return techMapper.selectTechByTechId(techId);
    }

    /**
     * 查询[技术]指标填报列表
     *
     * @param tech [技术]指标填报
     * @return [技术]指标填报
     */
    @Override
    public List<Tech> selectTechList(Tech tech) {
        return techMapper.selectTechList(tech);
    }

    /**
     * 新增[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
//    @Override
//    public int insertTech(Tech tech)
//    {
//        tech.setCreateTime(DateUtils.getNowDate());
//        return techMapper.insertTech(tech);
//    }

    /**
     * 修改[技术]指标填报
     *
     * @param tech [技术]指标填报
     * @return 结果
     */
    @Override
    public int updateTech(Tech tech) {
        tech.setUpdateTime(DateUtils.getNowDate());
        return techMapper.updateTech(tech);
    }

    /**
     * 批量删除[技术]指标填报
     *
     * @param techIds 需要删除的[技术]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteTechByTechIds(Long[] techIds) {
        return techMapper.deleteTechByTechIds(techIds);
    }

    /**
     * 删除[技术]指标填报信息
     *
     * @param techId [技术]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteTechByTechId(Long techId) {
        return techMapper.deleteTechByTechId(techId);
    }
}
