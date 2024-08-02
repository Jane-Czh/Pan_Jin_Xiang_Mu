package com.heli.quality.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.heli.quality.domain.QualityInspectionRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * 质检部分字段Service接口
 *
 * @author ruoyi
 * @date 2024-05-16
 */
public interface IQualityInspectionRecordService {

    QualityInspectionRecord selectMaxMonthInspection();


    void importQualityInspectionTable(MultipartFile excelFile, Date yearAndMonth,String userName) throws IOException;

    Boolean checkQualityInspectionTableIsExisted(Date date);


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
     * 批量删除质检部分字段
     *
     * @param qiIds 需要删除的质检部分字段主键集合
     * @return 结果
     */
    public int deleteQualityInspectionRecordByQiIds(Long[] qiIds);

    /**
     * 删除质检部分字段信息
     *
     * @param qiId 质检部分字段主键
     * @return 结果
     */
    public int deleteQualityInspectionRecordByQiId(Long qiId);
}
