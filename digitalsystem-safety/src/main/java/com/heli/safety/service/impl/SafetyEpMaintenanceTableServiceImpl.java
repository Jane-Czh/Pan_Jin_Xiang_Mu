package com.heli.safety.service.impl;

import java.io.InputStream;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.heli.safety.controller.SafetyEpMaintenanceTableController;
import com.heli.safety.listener.MaintainTableListener;
import com.ruoyi.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.safety.mapper.SafetyEpMaintenanceTableMapper;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import com.heli.safety.service.ISafetyEpMaintenanceTableService;

/**
 * 安环科
 * 维修数据Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class SafetyEpMaintenanceTableServiceImpl implements ISafetyEpMaintenanceTableService {
    @Autowired
    private SafetyEpMaintenanceTableMapper safetyEpMaintenanceTableMapper;
    private static final Logger log = LoggerFactory.getLogger(SafetyEpMaintenanceTableServiceImpl.class);


    @Override
    public R<String> readSafetyEpMaintenanceTableToDB(String fileName, InputStream inputStream) {
        try {
            // 读取文件内容
            EasyExcel.read(inputStream, SafetyEpMaintenanceTable.class, new MaintainTableListener(safetyEpMaintenanceTableMapper)).sheet().doRead();


            return R.ok("读取" + fileName + "文件成功");
        } catch (Exception e) {
            log.error("读取 " + fileName + " 文件失败, 原因: {}", e.getMessage());
            return R.fail("读取文件失败,您需要上传商品车台账,当前上传的文件为：" + fileName);
        }
    }

    /**
     * 查询安环科
     * 维修数据
     *
     * @param semId 安环科
     *              维修数据主键
     * @return 安环科
     * 维修数据
     */
    @Override
    public SafetyEpMaintenanceTable selectSafetyEpMaintenanceTableBySemId(String semId) {
        return safetyEpMaintenanceTableMapper.selectSafetyEpMaintenanceTableBySemId(semId);
    }

    /**
     * 查询安环科
     * 维修数据列表
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 安环科
     * 维修数据
     */
    @Override
    public List<SafetyEpMaintenanceTable> selectSafetyEpMaintenanceTableList(SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        return safetyEpMaintenanceTableMapper.selectSafetyEpMaintenanceTableList(safetyEpMaintenanceTable);
    }

    /**
     * 新增安环科
     * 维修数据
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 结果
     */
    @Override
    public int insertSafetyEpMaintenanceTable(SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        return safetyEpMaintenanceTableMapper.insertSafetyEpMaintenanceTable(safetyEpMaintenanceTable);
    }

    /**
     * 修改安环科
     * 维修数据
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 结果
     */
    @Override
    public int updateSafetyEpMaintenanceTable(SafetyEpMaintenanceTable safetyEpMaintenanceTable) {
        return safetyEpMaintenanceTableMapper.updateSafetyEpMaintenanceTable(safetyEpMaintenanceTable);
    }

    /**
     * 批量删除安环科
     * 维修数据
     *
     * @param semIds 需要删除的安环科
     *               维修数据主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpMaintenanceTableBySemIds(String[] semIds) {
        return safetyEpMaintenanceTableMapper.deleteSafetyEpMaintenanceTableBySemIds(semIds);
    }

    /**
     * 删除安环科
     * 维修数据信息
     *
     * @param semId 安环科
     *              维修数据主键
     * @return 结果
     */
    @Override
    public int deleteSafetyEpMaintenanceTableBySemId(String semId) {
        return safetyEpMaintenanceTableMapper.deleteSafetyEpMaintenanceTableBySemId(semId);
    }
}