package com.heli.quality.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.quality.mapper.QualityIndicatorsHandfillTableMapper;
import com.heli.quality.domain.QualityIndicatorsHandfillTable;
import com.heli.quality.service.IQualityIndicatorsHandfillTableService;

/**
 * [质量]指标填报Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-26
 */
@Service
public class QualityIndicatorsHandfillTableServiceImpl implements IQualityIndicatorsHandfillTableService {
    @Autowired
    private QualityIndicatorsHandfillTableMapper qualityIndicatorsHandfillTableMapper;

    @Override
    public Boolean checkQualityFillingDataIsExisted(Date yearAndMonth) {
        return qualityIndicatorsHandfillTableMapper.checkQualityFillingDataIsExisted(yearAndMonth);
    }

    /**
     * 查询[质量]指标填报
     *
     * @param qihfId [质量]指标填报主键
     * @return [质量]指标填报
     */
    @Override
    public QualityIndicatorsHandfillTable selectQualityIndicatorsHandfillTableByQihfId(Long qihfId) {
        return qualityIndicatorsHandfillTableMapper.selectQualityIndicatorsHandfillTableByQihfId(qihfId);
    }

    /**
     * 查询[质量]指标填报列表
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return [质量]指标填报
     */
    @Override
    public List<QualityIndicatorsHandfillTable> selectQualityIndicatorsHandfillTableList(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        return qualityIndicatorsHandfillTableMapper.selectQualityIndicatorsHandfillTableList(qualityIndicatorsHandfillTable);
    }

    /**
     * 新增[质量]指标填报
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return 结果
     */
    @Override
    public int insertQualityIndicatorsHandfillTable(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        qualityIndicatorsHandfillTable.setCreateTime(DateUtils.getNowDate());
        return qualityIndicatorsHandfillTableMapper.insertQualityIndicatorsHandfillTable(qualityIndicatorsHandfillTable);
    }

    /**
     * 修改[质量]指标填报
     *
     * @param qualityIndicatorsHandfillTable [质量]指标填报
     * @return 结果
     */
    @Override
    public int updateQualityIndicatorsHandfillTable(QualityIndicatorsHandfillTable qualityIndicatorsHandfillTable) {
        qualityIndicatorsHandfillTable.setUpdateTime(DateUtils.getNowDate());
        return qualityIndicatorsHandfillTableMapper.updateQualityIndicatorsHandfillTable(qualityIndicatorsHandfillTable);
    }

    /**
     * 批量删除[质量]指标填报
     *
     * @param qihfIds 需要删除的[质量]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteQualityIndicatorsHandfillTableByQihfIds(Long[] qihfIds) {
        return qualityIndicatorsHandfillTableMapper.deleteQualityIndicatorsHandfillTableByQihfIds(qihfIds);
    }

    /**
     * 删除[质量]指标填报信息
     *
     * @param qihfId [质量]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteQualityIndicatorsHandfillTableByQihfId(Long qihfId) {
        return qualityIndicatorsHandfillTableMapper.deleteQualityIndicatorsHandfillTableByQihfId(qihfId);
    }
}
