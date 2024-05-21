package com.heli.quality.mapper;

import java.util.List;

import com.heli.quality.domain.QualityAfterSalesRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 售后台账部分字段Mapper接口
 *
 * @author hong
 * @date 2024-05-16
 */
public interface QualityAfterSalesRecordMapper {


    int batchInsertAfterSalesRecord(@Param("afterSalesRecordList") List<QualityAfterSalesRecord> afterSalesRecordList);



    /**
     * 查询售后台账部分字段
     *
     * @param qaId 售后台账部分字段主键
     * @return 售后台账部分字段
     */
    public QualityAfterSalesRecord selectQualityAfterSalesRecordByQaId(Long qaId);

    /**
     * 查询售后台账部分字段列表
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 售后台账部分字段集合
     */
    public List<QualityAfterSalesRecord> selectQualityAfterSalesRecordList(QualityAfterSalesRecord qualityAfterSalesRecord);

    /**
     * 新增售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    public int insertQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord);

    /**
     * 修改售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    public int updateQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord);

    /**
     * 删除售后台账部分字段
     *
     * @param qaId 售后台账部分字段主键
     * @return 结果
     */
    public int deleteQualityAfterSalesRecordByQaId(Long qaId);

    /**
     * 批量删除售后台账部分字段
     *
     * @param qaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQualityAfterSalesRecordByQaIds(Long[] qaIds);
}
