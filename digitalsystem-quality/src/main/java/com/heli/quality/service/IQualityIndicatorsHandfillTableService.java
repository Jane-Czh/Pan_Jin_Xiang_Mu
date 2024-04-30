package com.heli.quality.service;

import java.util.Date;
import java.util.List;

import com.heli.quality.domain.QualityIndicatorsHandfillTable;

/**
 * [质量]指标填报Service接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
public interface IQualityIndicatorsHandfillTableService {


    Boolean checkQualityFillingDataIsExisted(Date yearAndMonth);





    /**
     * 查询[质量]指标填报
     *
     * @param qihfId [质量]指标填报主键
     * @return [质量]指标填报
     */
    public QualityIndicatorsHandfillTable selectQualityIndicatorsHandfillTableByQihfId(Long qihfId);

    /**
     * 查询[质量]指标填报列表
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return [质量]指标填报集合
     */
    public List<QualityIndicatorsHandfillTable> selectQualityIndicatorsHandfillTableList(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable);

    /**
     * 新增[质量]指标填报
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return 结果
     */
    public int insertQualityIndicatorsHandfillTable(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable);

    /**
     * 修改[质量]指标填报
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return 结果
     */
    public int updateQualityIndicatorsHandfillTable(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable);

    /**
     * 批量删除[质量]指标填报
     *
     * @param qihfIds 需要删除的[质量]指标填报主键集合
     * @return 结果
     */
    public int deleteQualityIndicatorsHandfillTableByQihfIds(Long[] qihfIds);

    /**
     * 删除[质量]指标填报信息
     *
     * @param qihfId [质量]指标填报主键
     * @return 结果
     */
    public int deleteQualityIndicatorsHandfillTableByQihfId(Long qihfId);
}
