package com.heli.safety.service.impl;

import java.util.List;

import com.heli.safety.utils.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.safety.mapper.SafetyEpAssessEnteringMapper;
import com.heli.safety.domain.SafetyEpAssessEntering;
import com.heli.safety.service.ISafetyEpAssessEnteringService;

/**
 * 安环科考核录入Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-18
 */
@Service
public class SafetyEpAssessEnteringServiceImpl implements ISafetyEpAssessEnteringService 
{
    @Autowired
    private SafetyEpAssessEnteringMapper safetyEpAssessEnteringMapper;

    /**
     * 查询安环科考核录入
     * 
     * @param seaeId 安环科考核录入主键
     * @return 安环科考核录入
     */
    @Override
    public SafetyEpAssessEntering selectSafetyEpAssessEnteringBySeaeId(Long seaeId)
    {
        return safetyEpAssessEnteringMapper.selectSafetyEpAssessEnteringBySeaeId(seaeId);
    }

    /**
     * 查询安环科考核录入列表
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 安环科考核录入
     */
    @Override
    public List<SafetyEpAssessEntering> selectSafetyEpAssessEnteringList(SafetyEpAssessEntering safetyEpAssessEntering)
    {
        return safetyEpAssessEnteringMapper.selectSafetyEpAssessEnteringList(safetyEpAssessEntering);
    }

    /**
     * 新增安环科考核录入
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 结果
     */
    @Override
    public int insertSafetyEpAssessEntering(SafetyEpAssessEntering safetyEpAssessEntering)
    {
        Long lastid = safetyEpAssessEnteringMapper.selectLastId();
        if (lastid == null){
            lastid = 0L;
        }
        Long insertid = GenerateId.getNextId(lastid);
        safetyEpAssessEntering.setSeaeId(insertid);
//        System.out.println("===========>"+safetyEpAssessEntering);
        return safetyEpAssessEnteringMapper.insertSafetyEpAssessEntering(safetyEpAssessEntering);
    }

    /**
     * 修改安环科考核录入
     * 
     * @param safetyEpAssessEntering 安环科考核录入
     * @return 结果
     */
    @Override
    public int updateSafetyEpAssessEntering(SafetyEpAssessEntering safetyEpAssessEntering)
    {
        return safetyEpAssessEnteringMapper.updateSafetyEpAssessEntering(safetyEpAssessEntering);
    }

    /**
     * 批量删除安环科考核录入
     * 
     * @param seaeIds 需要删除的安环科考核录入主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpAssessEnteringBySeaeIds(Long[] seaeIds)
    {
        return safetyEpAssessEnteringMapper.deleteSafetyEpAssessEnteringBySeaeIds(seaeIds);
    }

    /**
     * 删除安环科考核录入信息
     * 
     * @param seaeId 安环科考核录入主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpAssessEnteringBySeaeId(Long seaeId)
    {
        return safetyEpAssessEnteringMapper.deleteSafetyEpAssessEnteringBySeaeId(seaeId);
    }
}
