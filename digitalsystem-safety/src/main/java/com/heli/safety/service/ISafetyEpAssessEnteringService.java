package com.heli.safety.service;

import java.util.List;
import com.heli.safety.domain.SafetyEpAssessEntering;

/**
 * 安环科考核录入Service接口
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
public interface ISafetyEpAssessEnteringService 
{
    /**
     * 查询安环科考核录入
     * 
     * @param seaeId 安环科考核录入主键
     * @return 安环科考核录入
     */
    public SafetyEpAssessEntering selectSafetyEpAssessEnteringBySeaeId(Long seaeId);

    /**
     * 查询安环科考核录入列表
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 安环科考核录入集合
     */
    public List<SafetyEpAssessEntering> selectSafetyEpAssessEnteringList(SafetyEpAssessEntering safetyEpAssessEntering);

    /**
     * 新增安环科考核录入
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 结果
     */
    public int insertSafetyEpAssessEntering(SafetyEpAssessEntering safetyEpAssessEntering);

    /**
     * 修改安环科考核录入
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 结果
     */
    public int updateSafetyEpAssessEntering(SafetyEpAssessEntering safetyEpAssessEntering);

    /**
     * 批量删除安环科考核录入
     * 
     * @param seaeIds 需要删除的安环科考核录入主键集合
     * @return 结果
     */
    public int deleteSafetyEpAssessEnteringBySeaeIds(Long[] seaeIds);

    /**
     * 删除安环科考核录入信息
     * 
     * @param seaeId 安环科考核录入主键
     * @return 结果
     */
    public int deleteSafetyEpAssessEnteringBySeaeId(Long seaeId);
}
