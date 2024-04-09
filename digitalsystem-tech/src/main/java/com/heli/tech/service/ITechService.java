package com.heli.tech.service;

import java.util.List;

import com.heli.tech.domain.Tech;

/**
 * @description: [技术]指标Service接口
 * @author: hong
 * @date: 2024/4/9 15:52
 **/
public interface ITechService {

    List<Tech> selectTechListByYear(int year);

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



}
