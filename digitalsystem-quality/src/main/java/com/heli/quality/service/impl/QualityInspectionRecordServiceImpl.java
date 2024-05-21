package com.heli.quality.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.excel.ReadExcelCellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityInspectionRecordMapper;
import com.heli.quality.domain.QualityInspectionRecord;
import com.heli.quality.service.IQualityInspectionRecordService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 质检部分字段Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-16
 */
@Service
public class QualityInspectionRecordServiceImpl implements IQualityInspectionRecordService {
    @Autowired
    private QualityInspectionRecordMapper qualityInspectionRecordMapper;

    @Override
    public void importQualityInspectionTable(MultipartFile excelFile) throws IOException {
        QualityInspectionRecord qualityInspectionRecord;
        InputStream is = null;

        System.out.println("开始");
        try {
            System.out.println(excelFile);
            is = excelFile.getInputStream();
            qualityInspectionRecord = (QualityInspectionRecord) ReadExcelCellUtils.parseExcelToModel("com.heli.quality.domain.QualityInspectionRecord", is, "");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }

        System.out.println(qualityInspectionRecord);
        System.out.println("------------");

        qualityInspectionRecordMapper.insertQualityInspectionRecord(qualityInspectionRecord);
        System.out.println("上传成功");
    }

    /**
     * 查询质检部分字段
     *
     * @param qiId 质检部分字段主键
     * @return 质检部分字段
     */
    @Override
    public QualityInspectionRecord selectQualityInspectionRecordByQiId(Long qiId) {
        return qualityInspectionRecordMapper.selectQualityInspectionRecordByQiId(qiId);
    }

    /**
     * 查询质检部分字段列表
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 质检部分字段
     */
    @Override
    public List<QualityInspectionRecord> selectQualityInspectionRecordList(QualityInspectionRecord qualityInspectionRecord) {
        return qualityInspectionRecordMapper.selectQualityInspectionRecordList(qualityInspectionRecord);
    }

    /**
     * 新增质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    @Override
    public int insertQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord) {
        qualityInspectionRecord.setCreateTime(DateUtils.getNowDate());
        return qualityInspectionRecordMapper.insertQualityInspectionRecord(qualityInspectionRecord);
    }

    /**
     * 修改质检部分字段
     *
     * @param qualityInspectionRecord 质检部分字段
     * @return 结果
     */
    @Override
    public int updateQualityInspectionRecord(QualityInspectionRecord qualityInspectionRecord) {
        qualityInspectionRecord.setUpdateTime(DateUtils.getNowDate());
        return qualityInspectionRecordMapper.updateQualityInspectionRecord(qualityInspectionRecord);
    }

    /**
     * 批量删除质检部分字段
     *
     * @param qiIds 需要删除的质检部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionRecordByQiIds(Long[] qiIds) {
        return qualityInspectionRecordMapper.deleteQualityInspectionRecordByQiIds(qiIds);
    }

    /**
     * 删除质检部分字段信息
     *
     * @param qiId 质检部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityInspectionRecordByQiId(Long qiId) {
        return qualityInspectionRecordMapper.deleteQualityInspectionRecordByQiId(qiId);
    }
}
