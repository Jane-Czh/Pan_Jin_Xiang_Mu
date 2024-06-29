package com.heli.tech.service;

import java.util.List;

import com.heli.tech.domain.TechAnnualPlanCount;

/**
 * 【技术】总计划年初填报Service接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface ITechAnnualPlanCountService {


    Boolean checkTechAnnualDataIsExisted(Integer naturalYear);

    Long selectTechAnnualNumberByYear(Integer naturalYear);

    /**
     * 查询【技术】总计划年初填报
     *
     * @param tapcId 【技术】总计划年初填报主键
     * @return 【技术】总计划年初填报
     */
    public TechAnnualPlanCount selectTechAnnualPlanCountByTapcId(Long tapcId);

    /**
     * 查询【技术】总计划年初填报列表
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 【技术】总计划年初填报集合
     */
    public List<TechAnnualPlanCount> selectTechAnnualPlanCountList(TechAnnualPlanCount techAnnualPlanCount);

    /**
     * 新增【技术】总计划年初填报
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 结果
     */
    public int insertTechAnnualPlanCount(TechAnnualPlanCount techAnnualPlanCount);

    /**
     * 修改【技术】总计划年初填报
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 结果
     */
    public int updateTechAnnualPlanCount(TechAnnualPlanCount techAnnualPlanCount);

    /**
     * 批量删除【技术】总计划年初填报
     *
     * @param tapcIds 需要删除的【技术】总计划年初填报主键集合
     * @return 结果
     */
    public int deleteTechAnnualPlanCountByTapcIds(Long[] tapcIds);

    /**
     * 删除【技术】总计划年初填报信息
     *
     * @param tapcId 【技术】总计划年初填报主键
     * @return 结果
     */
    public int deleteTechAnnualPlanCountByTapcId(Long tapcId);
}
