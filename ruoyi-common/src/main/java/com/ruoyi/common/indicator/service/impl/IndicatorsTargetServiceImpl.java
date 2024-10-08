package com.ruoyi.common.indicator.service.impl;

import com.ruoyi.common.indicator.domain.IndicatorsIndex;
import com.ruoyi.common.indicator.domain.IndicatorsTarget;
import com.ruoyi.common.indicator.domain.TargetByNameParam;
import com.ruoyi.common.indicator.mapper.IndicatorsTargetMapper;
import com.ruoyi.common.indicator.service.IIndicatorsIndexService;
import com.ruoyi.common.indicator.service.IIndicatorsTargetService;
import com.ruoyi.common.utils.DateUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标-目标值Service业务层处理
 *
 * @author hong
 * @date 2024-07-30
 */
@Service
@Slf4j
public class IndicatorsTargetServiceImpl implements IIndicatorsTargetService {
    @Autowired
    private IndicatorsTargetMapper indicatorsTargetMapper;


    /**
     * 查询指标-目标值
     *
     * @param itId 指标-目标值主键
     * @return 指标-目标值
     */
    @Override
    public IndicatorsTarget selectIndicatorsTargetByItId(Long itId) {
        return indicatorsTargetMapper.selectIndicatorsTargetByItId(itId);
    }

    /**
     * 查询指标-目标值列表
     *
     * @param indicatorsTarget 指标-目标值
     * @return 指标-目标值
     */
    @Override
    public List<IndicatorsTarget> selectIndicatorsTargetList(IndicatorsTarget indicatorsTarget) {
        return indicatorsTargetMapper.selectIndicatorsTargetList(indicatorsTarget);
    }

    /**
     * 新增指标-目标值
     *
     * @param indicatorsTarget 指标-目标值
     * @return 结果
     */
    @Override
    public int insertIndicatorsTarget(IndicatorsTarget indicatorsTarget) {




        indicatorsTarget.setCreateTime(DateUtils.getNowDate());
        return indicatorsTargetMapper.insertIndicatorsTarget(indicatorsTarget);
    }

    /**
     * 修改指标-目标值
     *
     * @param indicatorsTarget 指标-目标值
     * @return 结果
     */
    @Override
    public int updateIndicatorsTarget(IndicatorsTarget indicatorsTarget) {
        indicatorsTarget.setUpdateTime(DateUtils.getNowDate());
        return indicatorsTargetMapper.updateIndicatorsTarget(indicatorsTarget);
    }

    /**
     * 批量删除指标-目标值
     *
     * @param itIds 需要删除的指标-目标值主键
     * @return 结果
     */
    @Override
    public int deleteIndicatorsTargetByItIds(Long[] itIds) {
        return indicatorsTargetMapper.deleteIndicatorsTargetByItIds(itIds);
    }

    /**
     * 删除指标-目标值信息
     *
     * @param itId 指标-目标值主键
     * @return 结果
     */
    @Override
    public int deleteIndicatorsTargetByItId(Long itId) {
        return indicatorsTargetMapper.deleteIndicatorsTargetByItId(itId);
    }

    @Override
    public List<IndicatorsTarget> selectTargetListByNameAndDate(TargetByNameParam targetByNameParam) {
        return indicatorsTargetMapper.selectTargetListByNameAndDate(targetByNameParam.getName(), targetByNameParam.getStartDate(), targetByNameParam.getEndDate());
    }
}
