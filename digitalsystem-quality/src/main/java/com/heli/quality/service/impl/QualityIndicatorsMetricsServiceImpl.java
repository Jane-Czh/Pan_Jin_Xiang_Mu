package com.heli.quality.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityIndicatorsMetricsMapper;
import com.heli.quality.domain.QualityIndicatorsMetrics;
import com.heli.quality.service.IQualityIndicatorsMetricsService;

/**
 * 质量指标-统计Service业务层处理
 *
 * @author hong
 * @date 2024-05-21
 */
@Service
public class QualityIndicatorsMetricsServiceImpl implements IQualityIndicatorsMetricsService
{
    @Autowired
    private QualityIndicatorsMetricsMapper qualityIndicatorsMetricsMapper;

    /**
     * 查询质量指标-统计
     *
     * @param qcId 质量指标-统计主键
     * @return 质量指标-统计
     */
    @Override
    public QualityIndicatorsMetrics selectQualityIndicatorsMetricsByQcId(Long qcId)
    {
        return qualityIndicatorsMetricsMapper.selectQualityIndicatorsMetricsByQcId(qcId);
    }

    /**
     * 查询质量指标-统计列表
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 质量指标-统计
     */
    @Override
    public List<QualityIndicatorsMetrics> selectQualityIndicatorsMetricsList(QualityIndicatorsMetrics qualityIndicatorsMetrics)
    {
        return qualityIndicatorsMetricsMapper.selectQualityIndicatorsMetricsList(qualityIndicatorsMetrics);
    }

    /**
     * 新增质量指标-统计
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 结果
     */
    @Override
    public int insertQualityIndicatorsMetrics(QualityIndicatorsMetrics qualityIndicatorsMetrics)
    {
        qualityIndicatorsMetrics.setCreateTime(DateUtils.getNowDate());
        return qualityIndicatorsMetricsMapper.insertQualityIndicatorsMetrics(qualityIndicatorsMetrics);
    }

    /**
     * 修改质量指标-统计
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 结果
     */
    @Override
    public int updateQualityIndicatorsMetrics(QualityIndicatorsMetrics qualityIndicatorsMetrics)
    {
        qualityIndicatorsMetrics.setUpdateTime(DateUtils.getNowDate());
        return qualityIndicatorsMetricsMapper.updateQualityIndicatorsMetrics(qualityIndicatorsMetrics);
    }

    /**
     * 批量删除质量指标-统计
     *
     * @param qcIds 需要删除的质量指标-统计主键
     * @return 结果
     */
    @Override
    public int deleteQualityIndicatorsMetricsByQcIds(Long[] qcIds)
    {
        return qualityIndicatorsMetricsMapper.deleteQualityIndicatorsMetricsByQcIds(qcIds);
    }

    /**
     * 删除质量指标-统计信息
     *
     * @param qcId 质量指标-统计主键
     * @return 结果
     */
    @Override
    public int deleteQualityIndicatorsMetricsByQcId(Long qcId)
    {
        return qualityIndicatorsMetricsMapper.deleteQualityIndicatorsMetricsByQcId(qcId);
    }
}
