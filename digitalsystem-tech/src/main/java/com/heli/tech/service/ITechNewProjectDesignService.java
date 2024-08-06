package com.heli.tech.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.heli.tech.domain.TechNewProjectDesign;
import com.ruoyi.common.core.domain.R;
import org.apache.ibatis.annotations.Param;

/**
 * 技术-项目计划完成统计Service接口
 *
 * @author hong
 * @date 2024-08-03
 */
public interface ITechNewProjectDesignService {

    boolean checkTechNewProjectDesignIsExisted( Date yearAndMonth);


    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream , Date yearAndMonth) ;

    /**
     * 查询技术-项目计划完成统计
     *
     * @param tdId 技术-项目计划完成统计主键
     * @return 技术-项目计划完成统计
     */
    public TechNewProjectDesign selectTechNewProjectDesignByTdId(Long tdId);

    /**
     * 查询技术-项目计划完成统计列表
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 技术-项目计划完成统计集合
     */
    public List<TechNewProjectDesign> selectTechNewProjectDesignList(TechNewProjectDesign techNewProjectDesign);

    /**
     * 新增技术-项目计划完成统计
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 结果
     */
    public int insertTechNewProjectDesign(TechNewProjectDesign techNewProjectDesign);

    /**
     * 修改技术-项目计划完成统计
     *
     * @param techNewProjectDesign 技术-项目计划完成统计
     * @return 结果
     */
    public int updateTechNewProjectDesign(TechNewProjectDesign techNewProjectDesign);

    /**
     * 批量删除技术-项目计划完成统计
     *
     * @param tdIds 需要删除的技术-项目计划完成统计主键集合
     * @return 结果
     */
    public int deleteTechNewProjectDesignByTdIds(Long[] tdIds);

    /**
     * 删除技术-项目计划完成统计信息
     *
     * @param tdId 技术-项目计划完成统计主键
     * @return 结果
     */
    public int deleteTechNewProjectDesignByTdId(Long tdId);
}
