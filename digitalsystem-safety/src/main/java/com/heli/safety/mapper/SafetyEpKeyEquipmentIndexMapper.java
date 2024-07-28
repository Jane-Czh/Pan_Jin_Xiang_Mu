package com.heli.safety.mapper;

import java.util.List;

import com.heli.safety.domain.SafetyEpKeyEquipmentIndex;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import org.apache.ibatis.annotations.Param;

/**
 * 重点设备字典Mapper接口
 *
 * @author hong
 * @date 2024-05-31
 */
public interface SafetyEpKeyEquipmentIndexMapper {

    void truncateTable();

    int batchInsertKeyEquipment(@Param("safetyEpKeyEquipmentList") List<SafetyEpKeyEquipmentIndex> safetyEpKeyEquipmentList);

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
     * 删除重点设备字典
     *
     * @param skId 重点设备字典主键
     * @return 结果
     */
    public int deleteSafetyEpKeyEquipmentIndexBySkId(Long skId);

    /**
     * 批量删除重点设备字典
     *
     * @param skIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyEpKeyEquipmentIndexBySkIds(Long[] skIds);
}

//public interface SafetyEpKeyEquipmentIndexMapper {
//
//    Boolean checkSafetyEpKeyEquipmentIndexIsExisted(@Param("seKeyEquipmentId") String seKeyEquipmentId);
//
//
//    /**
//     * 查询重点设备字典
//     *
//     * @param skId 重点设备字典主键
//     * @return 重点设备字典
//     */
//    public SafetyEpKeyEquipmentIndex selectSafetyEpKeyEquipmentIndexBySkId(Long skId);
//
//    /**
//     * 查询重点设备字典列表
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 重点设备字典集合
//     */
//    public List<SafetyEpKeyEquipmentIndex> selectSafetyEpKeyEquipmentIndexList(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);
//
//    /**
//     * 新增重点设备字典
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 结果
//     */
//    public int insertSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);
//
//    /**
//     * 修改重点设备字典
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 结果
//     */
//    public int updateSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex);
//
//    /**
//     * 删除重点设备字典
//     *
//     * @param skId 重点设备字典主键
//     * @return 结果
//     */
//    public int deleteSafetyEpKeyEquipmentIndexBySkId(Long skId);
//
//    /**
//     * 批量删除重点设备字典
//     *
//     * @param skIds 需要删除的数据主键集合
//     * @return 结果
//     */
//    public int deleteSafetyEpKeyEquipmentIndexBySkIds(Long[] skIds);
//}
