package com.heli.quality.mapper;

import java.util.List;
import com.heli.quality.domain.QualityInspectionRecord;

/**
 * 质检部分字段Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-16
 */
public interface QualityInspectionRecordMapper
{
    /**
     * 查询质检部分字段
     *
     * @param qiId 质检部分字段主键
     * @return 质检部分字段
     */
    public QualityInspectionRecord selectQualityInspectionRecordByQiId(Long qiId);

    /**
     * 查询质检部分字段列表
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 质检部分字段集合
     */
    public List<QualityInspectionRecord> selectQualityInspectionRecordList(QualityInspectionRecord qualityInspectionRecord);

    /**
     * 新增质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    public int insertQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord);

    /**
     * 修改质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    public int updateQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord);

    /**
     * 删除质检部分字段
     *
     * @param qiId 质检部分字段主键
     * @return 结果
     */
    public int deleteQualityInspectionRecordByQiId(Long qiId);

    /**
     * 批量删除质检部分字段
     *
     * @param qiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQualityInspectionRecordByQiIds(Long[] qiIds);
}
