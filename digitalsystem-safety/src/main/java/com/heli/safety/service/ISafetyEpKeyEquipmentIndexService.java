package com.heli.safety.service;

import java.util.List;

import com.heli.safety.domain.SafetyEpKeyEquipmentIndex;
import org.apache.ibatis.annotations.Param;

/**
 * 重点设备字典Service接口
 *
 * @author hong
 * @date 2024-05-31
 */
public interface ISafetyEpKeyEquipmentIndexService {

    Boolean checkSafetyEpKeyEquipmentIndexIsExisted(String seKeyEquipmentId);


    /**
     * 查询重点设备字典
     *
     * @param skId 重点设备字典主键
     * @return 重点设备字典
     */
    public SafetyEpKeyEquipmentIndex selectSafetyEpKeyEquipmentIndexBySkId(Long skId);

    /**
     * 查询重点设备字典列表
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 重点设备字典集合
     */
    public List<SafetyEpKeyEquipmentIndex> selectSafetyEpKeyEquipmentIndexList(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);

    /**
     * 新增重点设备字典
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 结果
     */
    public int insertSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);

    /**
     * 修改重点设备字典
     *
     * @param safetyEpKeyEquipmentIndex 重点设备字典
     * @return 结果
     */
    public int updateSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);

    /**
     * 批量删除重点设备字典
     *
     * @param skIds 需要删除的重点设备字典主键集合
     * @return 结果
     */
    public int deleteSafetyEpKeyEquipmentIndexBySkIds(Long[] skIds);

    /**
     * 删除重点设备字典信息
     *
     * @param skId 重点设备字典主键
     * @return 结果
     */
    public int deleteSafetyEpKeyEquipmentIndexBySkId(Long skId);
}
