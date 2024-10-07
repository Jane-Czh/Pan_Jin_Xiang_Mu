package com.heli.tech.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.tech.domain.TechNewProjectDesign;
import org.apache.ibatis.annotations.Param;

/**
 * 技术-项目计划完成统计Mapper接口
 *
 * @author hong
 * @date 2024-08-03
 */
public interface TechNewProjectDesignMapper {

    boolean checkTechNewProjectDesignIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    void deleteTechNewProjectDesignByYearAndMonth(@Param("yearAndMonth") Date yearAndMonth);

    void batchInsertNewProjectDesign(@Param("newProjectDesignList") List<TechNewProjectDesign> newProjectDesignList);

    Integer selectTechNewProjectDesignCount(@Param("yearAndMonth") Date yearAndMonth);

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
     * 删除技术-项目计划完成统计
     *
     * @param tdId 技术-项目计划完成统计主键
     * @return 结果
     */
    public int deleteTechNewProjectDesignByTdId(Long tdId);

    /**
     * 批量删除技术-项目计划完成统计
     *
     * @param tdIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechNewProjectDesignByTdIds(Long[] tdIds);


    Integer selectTechTotalProjectCount(@Param("yearAndMonth") Date yearAndMonth);

    Integer selectTechUnstartedProjectCount(@Param("yearAndMonth") Date yearAndMonth);


    Integer selectTechCompletedProjectCount(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectTechTotalPoints(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectTechAccumulatedScore(@Param("yearAndMonth") Date yearAndMonth);
}
