package com.heli.tech.mapper;

import java.util.List;

import com.heli.tech.domain.TechAnnualPlanCount;
import org.apache.ibatis.annotations.Param;

/**
 * 【技术】总计划年初填报Mapper接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface TechAnnualPlanCountMapper {


    Boolean checkTechAnnualDataIsExisted(@Param("naturalYear") Integer naturalYear);
    Long selectTechAnnualNumberByYear(@Param("naturalYear") Integer naturalYear);


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
     * 删除【技术】总计划年初填报
     *
     * @param tapcId 【技术】总计划年初填报主键
     * @return 结果
     */
    public int deleteTechAnnualPlanCountByTapcId(Long tapcId);

    /**
     * 批量删除【技术】总计划年初填报
     *
     * @param tapcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechAnnualPlanCountByTapcIds(Long[] tapcIds);
}
