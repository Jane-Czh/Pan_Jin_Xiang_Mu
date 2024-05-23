package com.heli.quality.mapper;

import java.util.List;

import com.heli.quality.domain.QualityIndicatorsMetrics;
import org.apache.ibatis.annotations.Param;

/**
 * 质量指标-统计Mapper接口
 *
 * @author hong
 * @date 2024-05-21
 */
public interface QualityIndicatorsMetricsMapper {


    int batchInsertMetrics(@Param("metricsList") List<QualityIndicatorsMetrics> metricsList);

    /**
     * 查询质量指标-统计
     *
     * @param qcId 质量指标-统计主键
     * @return 质量指标-统计
     */
    public QualityIndicatorsMetrics selectQualityIndicatorsMetricsByQcId(Long qcId);

    /**
     * 查询质量指标-统计列表
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 质量指标-统计集合
     */
    public List<QualityIndicatorsMetrics> selectQualityIndicatorsMetricsList(QualityIndicatorsMetrics qualityIndicatorsMetrics);

    /**
     * 新增质量指标-统计
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 结果
     */
    public int insertQualityIndicatorsMetrics(QualityIndicatorsMetrics qualityIndicatorsMetrics);

    /**
     * 修改质量指标-统计
     *
     * @param qualityIndicatorsMetrics 质量指标-统计
     * @return 结果
     */
    public int updateQualityIndicatorsMetrics(QualityIndicatorsMetrics qualityIndicatorsMetrics);

    /**
     * 删除质量指标-统计
     *
     * @param qcId 质量指标-统计主键
     * @return 结果
     */
    public int deleteQualityIndicatorsMetricsByQcId(Long qcId);

    /**
     * 批量删除质量指标-统计
     *
     * @param qcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQualityIndicatorsMetricsByQcIds(Long[] qcIds);
}
