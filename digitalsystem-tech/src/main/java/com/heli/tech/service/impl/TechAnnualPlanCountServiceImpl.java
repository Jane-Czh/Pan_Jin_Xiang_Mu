package com.heli.tech.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.tech.mapper.TechAnnualPlanCountMapper;
import com.heli.tech.domain.TechAnnualPlanCount;
import com.heli.tech.service.ITechAnnualPlanCountService;

/**
 * 【技术】总计划年初填报Service业务层处理
 *
 * @author hong
 * @date 2024-04-27
 */
@Service
public class TechAnnualPlanCountServiceImpl implements ITechAnnualPlanCountService {
    @Autowired
    private TechAnnualPlanCountMapper techAnnualPlanCountMapper;

    @Override
    public Boolean checkTechAnnualDataIsExisted(Integer naturalYear) {
        return techAnnualPlanCountMapper.checkTechAnnualDataIsExisted(naturalYear);
    }

    @Override
    public Long selectTechAnnualNumberByYear(Integer naturalYear) {
        return techAnnualPlanCountMapper.selectTechAnnualNumberByYear(naturalYear);
    }

    /**
     * 查询【技术】总计划年初填报
     *
     * @param tapcId 【技术】总计划年初填报主键
     * @return 【技术】总计划年初填报
     */
    @Override
    public TechAnnualPlanCount selectTechAnnualPlanCountByTapcId(Long tapcId) {
        return techAnnualPlanCountMapper.selectTechAnnualPlanCountByTapcId(tapcId);
    }

    /**
     * 查询【技术】总计划年初填报列表
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 【技术】总计划年初填报
     */
    @Override
    public List<TechAnnualPlanCount> selectTechAnnualPlanCountList(TechAnnualPlanCount techAnnualPlanCount) {
        return techAnnualPlanCountMapper.selectTechAnnualPlanCountList(techAnnualPlanCount);
    }

    /**
     * 新增【技术】总计划年初填报
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 结果
     */
    @Override
    public int insertTechAnnualPlanCount(TechAnnualPlanCount techAnnualPlanCount) {
        techAnnualPlanCount.setCreateTime(DateUtils.getNowDate());
        return techAnnualPlanCountMapper.insertTechAnnualPlanCount(techAnnualPlanCount);
    }

    /**
     * 修改【技术】总计划年初填报
     *
     * @param techAnnualPlanCount 【技术】总计划年初填报
     * @return 结果
     */
    @Override
    public int updateTechAnnualPlanCount(TechAnnualPlanCount techAnnualPlanCount) {
        techAnnualPlanCount.setUpdateTime(DateUtils.getNowDate());
        return techAnnualPlanCountMapper.updateTechAnnualPlanCount(techAnnualPlanCount);
    }

    /**
     * 批量删除【技术】总计划年初填报
     *
     * @param tapcIds 需要删除的【技术】总计划年初填报主键
     * @return 结果
     */
    @Override
    public int deleteTechAnnualPlanCountByTapcIds(Long[] tapcIds) {
        return techAnnualPlanCountMapper.deleteTechAnnualPlanCountByTapcIds(tapcIds);
    }

    /**
     * 删除【技术】总计划年初填报信息
     *
     * @param tapcId 【技术】总计划年初填报主键
     * @return 结果
     */
    @Override
    public int deleteTechAnnualPlanCountByTapcId(Long tapcId) {
        return techAnnualPlanCountMapper.deleteTechAnnualPlanCountByTapcId(tapcId);
    }
}
