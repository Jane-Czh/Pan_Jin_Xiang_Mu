package com.heli.quality.mapper;

import java.util.Date;
import java.util.List;

import com.heli.quality.domain.QualityIndicatorsHandfillTable;
import org.apache.ibatis.annotations.Param;

/**
 * [质量]指标填报Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-26
 */
public interface QualityIndicatorsHandfillTableMapper {

    QualityIndicatorsHandfillTable selectMaxMonthHandfill();


    Boolean checkQualityFillingDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

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
     * 删除[质量]指标填报
     *
     * @param qihfId [质量]指标填报主键
     * @return 结果
     */
    public int deleteQualityIndicatorsHandfillTableByQihfId(Long qihfId);

    /**
     * 批量删除[质量]指标填报
     *
     * @param qihfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQualityIndicatorsHandfillTableByQihfIds(Long[] qihfIds);
}
