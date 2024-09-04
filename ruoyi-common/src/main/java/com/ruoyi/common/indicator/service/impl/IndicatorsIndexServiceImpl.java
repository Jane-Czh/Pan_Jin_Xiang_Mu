package com.ruoyi.common.indicator.service.impl;


import com.ruoyi.common.indicator.domain.IndicatorsIndex;
import com.ruoyi.common.indicator.mapper.IndicatorsIndexMapper;
import com.ruoyi.common.indicator.service.IIndicatorsIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标-索引Service业务层处理
 *
 * @author ruoyi
 * @date 2024-07-30
 */
@Service
public class IndicatorsIndexServiceImpl implements IIndicatorsIndexService {
    @Autowired
    private IndicatorsIndexMapper indicatorsIndexMapper;

    /**
     * 查询指标-索引
     *
     * @param indicatorId 指标-索引主键
     * @return 指标-索引
     */
    @Override
    public IndicatorsIndex selectIndicatorsIndexByIndicatorId(Integer indicatorId) {
        return indicatorsIndexMapper.selectIndicatorsIndexByIndicatorId(indicatorId);
    }


    /**
     * 查询指标-索引列表
     *
     * @param indicatorsIndex 指标-索引
     * @return 指标-索引
     */
    @Override
    public List<IndicatorsIndex> selectIndicatorsIndexList(IndicatorsIndex indicatorsIndex) {
        return indicatorsIndexMapper.selectIndicatorsIndexList(indicatorsIndex);
    }

    @Override
    public List<IndicatorsIndex> selectIndicatorsIndexAllList() {
        return indicatorsIndexMapper.selectIndicatorsIndexAllList();
    }

    /**
     * 新增指标-索引
     *
     * @param indicatorsIndex 指标-索引
     * @return 结果
     */
    @Override
    public int insertIndicatorsIndex(IndicatorsIndex indicatorsIndex) {
        return indicatorsIndexMapper.insertIndicatorsIndex(indicatorsIndex);
    }

    /**
     * 修改指标-索引
     *
     * @param indicatorsIndex 指标-索引
     * @return 结果
     */
    @Override
    public int updateIndicatorsIndex(IndicatorsIndex indicatorsIndex) {
        return indicatorsIndexMapper.updateIndicatorsIndex(indicatorsIndex);
    }

    /**
     * 批量删除指标-索引
     *
     * @param indicatorIds 需要删除的指标-索引主键
     * @return 结果
     */
    @Override
    public int deleteIndicatorsIndexByIndicatorIds(Integer[] indicatorIds) {
        return indicatorsIndexMapper.deleteIndicatorsIndexByIndicatorIds(indicatorIds);
    }

    /**
     * 删除指标-索引信息
     *
     * @param indicatorId 指标-索引主键
     * @return 结果
     */
    @Override
    public int deleteIndicatorsIndexByIndicatorId(Integer indicatorId) {
        return indicatorsIndexMapper.deleteIndicatorsIndexByIndicatorId(indicatorId);
    }
}
