package com.ruoyi.common.indicator.mapper;

import com.ruoyi.common.indicator.domain.IndicatorsIndex;

import java.util.List;

/**
 * 指标-索引Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-30
 */
public interface IndicatorsIndexMapper {
    /**
     * 查询指标-索引
     *
     * @param indicatorId 指标-索引主键
     * @return 指标-索引
     */
    public IndicatorsIndex selectIndicatorsIndexByIndicatorId(Integer indicatorId);

    /**
     * 查询指标-索引列表
     *
     * @param indicatorsIndex 指标-索引
     * @return 指标-索引集合
     */
    public List<IndicatorsIndex> selectIndicatorsIndexList(IndicatorsIndex indicatorsIndex);
    public List<IndicatorsIndex> selectIndicatorsIndexAllList();

    /**
     * 新增指标-索引
     *
     * @param indicatorsIndex 指标-索引
     * @return 结果
     */
    public int insertIndicatorsIndex(IndicatorsIndex indicatorsIndex);

    /**
     * 修改指标-索引
     *
     * @param indicatorsIndex 指标-索引
     * @return 结果
     */
    public int updateIndicatorsIndex(IndicatorsIndex indicatorsIndex);

    /**
     * 删除指标-索引
     *
     * @param indicatorId 指标-索引主键
     * @return 结果
     */
    public int deleteIndicatorsIndexByIndicatorId(Integer indicatorId);

    /**
     * 批量删除指标-索引
     *
     * @param indicatorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIndicatorsIndexByIndicatorIds(Integer[] indicatorIds);
}
