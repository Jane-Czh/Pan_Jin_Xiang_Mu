package com.heli.quality.service.impl;

import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.quality.listener.AfterSalesTableListener;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityAfterSalesRecordMapper;
import com.heli.quality.domain.QualityAfterSalesRecord;
import com.heli.quality.service.IQualityAfterSalesRecordService;

/**
 * 售后台账部分字段Service业务层处理
 *
 * @author hong
 * @date 2024-05-16
 */
@Service
public class QualityAfterSalesRecordServiceImpl implements IQualityAfterSalesRecordService {
    @Autowired
    private QualityAfterSalesRecordMapper qualityAfterSalesRecordMapper;

    private static final Logger log = LoggerFactory.getLogger(QualityAfterSalesRecordServiceImpl.class);


    @Override
    public R<String> readSalesAfterExcelToDB(String fileName, InputStream inputStream) {
        try {
            // 读取文件内容
            log.info("开始读取文件: {}", fileName);
            EasyExcel.read(inputStream, QualityAfterSalesRecord.class, new AfterSalesTableListener(qualityAfterSalesRecordMapper)).sheet(0).doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要售后台账,当前上传的文件为：" + fileName);
        }
    }





    /**
     * 查询售后台账部分字段
     *
     * @param qaId 售后台账部分字段主键
     * @return 售后台账部分字段
     */
    @Override
    public QualityAfterSalesRecord selectQualityAfterSalesRecordByQaId(Long qaId) {
        return qualityAfterSalesRecordMapper.selectQualityAfterSalesRecordByQaId(qaId);
    }

    /**
     * 查询售后台账部分字段列表
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 售后台账部分字段
     */
    @Override
    public List<QualityAfterSalesRecord> selectQualityAfterSalesRecordList(QualityAfterSalesRecord qualityAfterSalesRecord) {
        return qualityAfterSalesRecordMapper.selectQualityAfterSalesRecordList(qualityAfterSalesRecord);
    }

    /**
     * 新增售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    @Override
    public int insertQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord) {
        qualityAfterSalesRecord.setCreateTime(DateUtils.getNowDate());
        return qualityAfterSalesRecordMapper.insertQualityAfterSalesRecord(qualityAfterSalesRecord);
    }

    /**
     * 修改售后台账部分字段
     *
     * @param qualityAfterSalesRecord 售后台账部分字段
     * @return 结果
     */
    @Override
    public int updateQualityAfterSalesRecord(QualityAfterSalesRecord qualityAfterSalesRecord) {
        qualityAfterSalesRecord.setUpdateTime(DateUtils.getNowDate());
        return qualityAfterSalesRecordMapper.updateQualityAfterSalesRecord(qualityAfterSalesRecord);
    }

    /**
     * 批量删除售后台账部分字段
     *
     * @param qaIds 需要删除的售后台账部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityAfterSalesRecordByQaIds(Long[] qaIds) {
        return qualityAfterSalesRecordMapper.deleteQualityAfterSalesRecordByQaIds(qaIds);
    }

    /**
     * 删除售后台账部分字段信息
     *
     * @param qaId 售后台账部分字段主键
     * @return 结果
     */
    @Override
    public int deleteQualityAfterSalesRecordByQaId(Long qaId) {
        return qualityAfterSalesRecordMapper.deleteQualityAfterSalesRecordByQaId(qaId);
    }
}
