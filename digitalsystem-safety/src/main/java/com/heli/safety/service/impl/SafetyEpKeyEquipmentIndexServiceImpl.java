package com.heli.safety.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.safety.mapper.SafetyEpKeyEquipmentIndexMapper;
import com.heli.safety.domain.SafetyEpKeyEquipmentIndex;
import com.heli.safety.service.ISafetyEpKeyEquipmentIndexService;

/**
 * 重点设备字典Service业务层处理
 *
 * @author hong
 * @date 2024-05-31
 */
@Service
public class SafetyEpKeyEquipmentIndexServiceImpl implements ISafetyEpKeyEquipmentIndexService {
    @Autowired
    private SafetyEpKeyEquipmentIndexMapper safetyEpKeyEquipmentIndexMapper;

    @Override
    public Boolean checkSafetyEpKeyEquipmentIndexIsExisted(String seKeyEquipmentId) {
        return safetyEpKeyEquipmentIndexMapper.checkSafetyEpKeyEquipmentIndexIsExisted(seKeyEquipmentId);
    }

    /**
     * 查询重点设备字典
     *
     * @param skId 重点设备字典主键
     * @return 重点设备字典
     */
    @Override
    public SafetyEpKeyEquipmentIndex selectSafetyEpKeyEquipmentIndexBySkId(Long skId) {
        return safetyEpKeyEquipmentIndexMapper.selectSafetyEpKeyEquipmentIndexBySkId(skId);
    }

    /**
     * 查询重点设备字典列表
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 重点设备字典
     */
    @Override
    public List<SafetyEpKeyEquipmentIndex> selectSafetyEpKeyEquipmentIndexList(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        return safetyEpKeyEquipmentIndexMapper.selectSafetyEpKeyEquipmentIndexList(safetyEpKeyEquipmentIndex);
    }

    /**
     * 新增重点设备字典
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 结果
     */
    @Override
    public int insertSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        safetyEpKeyEquipmentIndex.setCreateTime(DateUtils.getNowDate());
        return safetyEpKeyEquipmentIndexMapper.insertSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex);
    }

    /**
     * 修改重点设备字典
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 结果
     */
    @Override
    public int updateSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
        safetyEpKeyEquipmentIndex.setUpdateTime(DateUtils.getNowDate());
        return safetyEpKeyEquipmentIndexMapper.updateSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex);
    }

    /**
     * 批量删除重点设备字典
     *
     * @param skIds 需要删除的重点设备字典主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpKeyEquipmentIndexBySkIds(Long[] skIds) {
        return safetyEpKeyEquipmentIndexMapper.deleteSafetyEpKeyEquipmentIndexBySkIds(skIds);
    }

    /**
     * 删除重点设备字典信息
     *
     * @param skId 重点设备字典主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpKeyEquipmentIndexBySkId(Long skId) {
        return safetyEpKeyEquipmentIndexMapper.deleteSafetyEpKeyEquipmentIndexBySkId(skId);
    }
}
