package com.heli.tech.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.tech.mapper.TechMapper;
import com.heli.tech.domain.Tech;
import com.heli.tech.service.ITechService;

/**
 * @description: [技术]指标Service业务层处理
 * @author: hong
 * @date: 2024/4/9 15:51
 **/
@Service
public class TechServiceImpl implements ITechService {
    @Autowired
    private TechMapper techMapper;

    /**
     * @description: 新增数据的同时计算出 研发项目计划进度完成率
     * @author: hong
     * @date: 2024/4/9 14:10
     **/
    @Override
    public int insertTech(Tech tech) {
        return techMapper.insertTech(calculateCompletionRate(tech));
    }


    /**
     * @description: ----->> 待定，是否允许用户直接修改数据
     * @author: hong
     * @date: 2024/4/9 14:11
     **/
    @Override
    public int updateTech(Tech tech) {
        return 1;
    }


    /**
     * @description: 计算研发项目计划进度完成率
     * @author: hong
     * @date: 2024/4/9 11:25
     **/
    public Tech calculateCompletionRate(Tech tech) {
        Long annualCompletionNumber = techMapper.countAnnualCompletionNumber(new SimpleDateFormat("yyyy").format(tech.getYearAndMonth()));
        tech.setPrdscheduleCompletionrate((annualCompletionNumber + tech.getCompletedmonthlyPlancounts()) / tech.getAnnualPlancounts().doubleValue() * 100);
        return tech;
    }

    /**
     * @description: 按年查询数据列表
     * @author: hong
     * @date: 2024/4/9 14:10
     **/
    @Override
    public List<Tech> selectTechListByYear(int year) {
        return techMapper.selectTechListByYear(year);
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
