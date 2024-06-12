package com.heli.safety.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.safety.mapper.SafetyEpMapper;
import com.heli.safety.domain.SafetyEp;
import com.heli.safety.service.ISafetyEpService;

/**
 * [安全环保]指标填报Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-16
 */
@Service
public class SafetyEpServiceImpl implements ISafetyEpService {
    @Autowired
    private SafetyEpMapper safetyEpMapper;

    @Override
    public Boolean checkSafetyFillingDataIsExisted(Date date) {
        return safetyEpMapper.checkSafetyFillingDataIsExisted(date);
    }

    /**
     * 安全新增-[安全环保]-指标填报
     * 数据存在则更新，不存在则插入
     */
    @Override
    public int InsertOrUpdateSafetyEp(SafetyEp safetyEp) {
        safetyEp.setCreateTime(DateUtils.getNowDate());
        return safetyEpMapper.InsertOrUpdateSafetyEp(safetyEp);
    }

    /**
     * 查询[安全环保]指标填报
     *
     * @param safetyEpId [安全环保]指标填报主键
     * @return [安全环保]指标填报
     */
    @Override
    public SafetyEp selectSafetyEpBySafetyEpId(Long safetyEpId) {
        return safetyEpMapper.selectSafetyEpBySafetyEpId(safetyEpId);
    }

    /**
     * 查询[安全环保]指标填报列表
     *
     * @param safetyEp [安全环保]指标填报
     * @return [安全环保]指标填报
     */
    @Override
    public List<SafetyEp> selectSafetyEpList(SafetyEp safetyEp) {
        return safetyEpMapper.selectSafetyEpList(safetyEp);
    }

    /**
     * 新增[安全环保]指标填报
     *
     * @param safetyEp [安全环保]指标填报
     * @return 结果
     */
    @Override
    public int insertSafetyEp(SafetyEp safetyEp) {
        safetyEp.setCreateTime(DateUtils.getNowDate());
        return safetyEpMapper.insertSafetyEp(safetyEp);
    }

    /**
     * 修改[安全环保]指标填报
     *
     * @param safetyEp [安全环保]指标填报
     * @return 结果
     */
    @Override
    public int updateSafetyEp(SafetyEp safetyEp) {
        safetyEp.setUpdateTime(DateUtils.getNowDate());
        return safetyEpMapper.updateSafetyEp(safetyEp);
    }

    /**
     * 批量删除[安全环保]指标填报
     *
     * @param safetyEpIds 需要删除的[安全环保]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpBySafetyEpIds(Long[] safetyEpIds) {
        return safetyEpMapper.deleteSafetyEpBySafetyEpIds(safetyEpIds);
    }

    /**
     * 删除[安全环保]指标填报信息
     *
     * @param safetyEpId [安全环保]指标填报主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpBySafetyEpId(Long safetyEpId) {
        return safetyEpMapper.deleteSafetyEpBySafetyEpId(safetyEpId);
    }
}
