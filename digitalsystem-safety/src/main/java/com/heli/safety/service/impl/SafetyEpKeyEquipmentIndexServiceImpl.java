package com.heli.safety.service.impl;

import java.awt.event.KeyListener;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.safety.domain.SafetyEp;
import com.heli.safety.domain.SafetyEpDeviceFaultData;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import com.heli.safety.listener.KeyEquipmentIndexListener;
import com.heli.safety.listener.MaintainTableListener;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(SafetyEpKeyEquipmentIndexServiceImpl.class);


    @Override
    public R<String> readSafetyEpKeyEquipmentTableToDB(String fileName, InputStream inputStream, String username) {

        //清空维修数据表
        safetyEpKeyEquipmentIndexMapper.truncateTable();

        try {
            // 读取文件内容
            EasyExcel.read(inputStream, SafetyEpKeyEquipmentIndex.class, new KeyEquipmentIndexListener(safetyEpKeyEquipmentIndexMapper)).sheet().doRead();
            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取" + fileName + "文件失败");
        }
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


//@Service
//public class SafetyEpKeyEquipmentIndexServiceImpl implements ISafetyEpKeyEquipmentIndexService {
//    @Autowired
//    private SafetyEpKeyEquipmentIndexMapper safetyEpKeyEquipmentIndexMapper;
//
//    @Override
//    public Boolean checkSafetyEpKeyEquipmentIndexIsExisted(String seKeyEquipmentId) {
//        return safetyEpKeyEquipmentIndexMapper.checkSafetyEpKeyEquipmentIndexIsExisted(seKeyEquipmentId);
//    }
//
//    /**
//     * 查询重点设备字典
//     *
//     * @param skId 重点设备字典主键
//     * @return 重点设备字典
//     */
//    @Override
//    public SafetyEpKeyEquipmentIndex selectSafetyEpKeyEquipmentIndexBySkId(Long skId) {
//        return safetyEpKeyEquipmentIndexMapper.selectSafetyEpKeyEquipmentIndexBySkId(skId);
//    }
//
//    /**
//     * 查询重点设备字典列表
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 重点设备字典
//     */
//    @Override
//    public List<SafetyEpKeyEquipmentIndex> selectSafetyEpKeyEquipmentIndexList(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        return safetyEpKeyEquipmentIndexMapper.selectSafetyEpKeyEquipmentIndexList(safetyEpKeyEquipmentIndex);
//    }
//
//    /**
//     * 新增重点设备字典
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 结果
//     */
//    @Override
//    public int insertSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        safetyEpKeyEquipmentIndex.setCreateTime(DateUtils.getNowDate());
//        return safetyEpKeyEquipmentIndexMapper.insertSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex);
//    }
//
//    /**
//     * 修改重点设备字典
//     *
//     * @param safetyEpKeyEquipmentIndex 重点设备字典
//     * @return 结果
//     */
//    @Override
//    public int updateSafetyEpKeyEquipmentIndex(SafetyEpKeyEquipmentIndex safetyEpKeyEquipmentIndex) {
//        safetyEpKeyEquipmentIndex.setUpdateTime(DateUtils.getNowDate());
//        return safetyEpKeyEquipmentIndexMapper.updateSafetyEpKeyEquipmentIndex(safetyEpKeyEquipmentIndex);
//    }
//
//    /**
//     * 批量删除重点设备字典
//     *
//     * @param skIds 需要删除的重点设备字典主键
//     * @return 结果
//     */
//    @Override
//    public int deleteSafetyEpKeyEquipmentIndexBySkIds(Long[] skIds) {
//        return safetyEpKeyEquipmentIndexMapper.deleteSafetyEpKeyEquipmentIndexBySkIds(skIds);
//    }
//
//    /**
//     * 删除重点设备字典信息
//     *
//     * @param skId 重点设备字典主键
//     * @return 结果
//     */
//    @Override
//    public int deleteSafetyEpKeyEquipmentIndexBySkId(Long skId) {
//        return safetyEpKeyEquipmentIndexMapper.deleteSafetyEpKeyEquipmentIndexBySkId(skId);
//    }
//}
