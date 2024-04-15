package com.heli.safety.service;

import java.io.InputStream;
import java.util.List;

import com.heli.safety.domain.SafetyEpMaintenanceTable;
import com.ruoyi.common.core.domain.R;

/**
 * 安环科
 * 维修数据Service接口
 *
 * @author ruoyi
 * @date 2024-04-10
 */
public interface ISafetyEpMaintenanceTableService {


    public R<String> readSafetyEpMaintenanceTableToDB(String fileName, InputStream inputStream);




    /**
     * 查询安环科
     * 维修数据
     *
     * @param semId 安环科
     *              维修数据主键
     * @return 安环科
     * 维修数据
     */
    public SafetyEpMaintenanceTable selectSafetyEpMaintenanceTableBySemId(String semId);

    /**
     * 查询安环科
     * 维修数据列表
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 安环科
     * 维修数据集合
     */
    public List<SafetyEpMaintenanceTable> selectSafetyEpMaintenanceTableList(SafetyEpMaintenanceTable safetyEpMaintenanceTable);

    /**
     * 新增安环科
     * 维修数据
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 结果
     */
    public int insertSafetyEpMaintenanceTable(SafetyEpMaintenanceTable safetyEpMaintenanceTable);

    /**
     * 修改安环科
     * 维修数据
     *
     * @param safetyEpMaintenanceTable 安环科
     *                                 维修数据
     * @return 结果
     */
    public int updateSafetyEpMaintenanceTable(SafetyEpMaintenanceTable safetyEpMaintenanceTable);

    /**
     * 批量删除安环科
     * 维修数据
     *
     * @param semIds 需要删除的安环科
     *               维修数据主键集合
     * @return 结果
     */
    public int deleteSafetyEpMaintenanceTableBySemIds(String[] semIds);

    /**
     * 删除安环科
     * 维修数据信息
     *
     * @param semId 安环科
     *              维修数据主键
     * @return 结果
     */
    public int deleteSafetyEpMaintenanceTableBySemId(String semId);
}
