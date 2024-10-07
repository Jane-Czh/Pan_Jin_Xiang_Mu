package com.ruoyi.common.indicator.service;

import com.ruoyi.common.indicator.domain.IndicatorsTarget;
import com.ruoyi.common.indicator.domain.TargetByNameParam;

import java.util.List;

/**
 * 指标-目标值Service接口
 *
 * @author hong
 * @date 2024-07-30
 */
public interface IIndicatorsTargetService
{
    /**
     * 查询指标-目标值
     *
     * @param itId 指标-目标值主键
     * @return 指标-目标值
     */
    public IndicatorsTarget selectIndicatorsTargetByItId(Long itId);

    /**
     * 查询指标-目标值列表
     *
     * @param indicatorsTarget 指标-目标值
     * @return 指标-目标值集合
     */
    public List<IndicatorsTarget> selectIndicatorsTargetList(IndicatorsTarget indicatorsTarget);

    /**
     * 新增指标-目标值
     *
     * @param indicatorsTarget 指标-目标值
     * @return 结果
     */
    public int insertIndicatorsTarget(IndicatorsTarget indicatorsTarget);

    /**
     * 修改指标-目标值
     *
     * @param indicatorsTarget 指标-目标值
     * @return 结果
     */
    public int updateIndicatorsTarget(IndicatorsTarget indicatorsTarget);

    /**
     * 批量删除指标-目标值
     *
     * @param itIds 需要删除的指标-目标值主键集合
     * @return 结果
     */
    public int deleteIndicatorsTargetByItIds(Long[] itIds);

    /**
     * 删除指标-目标值信息
     *
     * @param itId 指标-目标值主键
     * @return 结果
     */
    public int deleteIndicatorsTargetByItId(Long itId);

    List<IndicatorsTarget> selectTargetListByNameAndDate(TargetByNameParam targetByNameParam);
}
