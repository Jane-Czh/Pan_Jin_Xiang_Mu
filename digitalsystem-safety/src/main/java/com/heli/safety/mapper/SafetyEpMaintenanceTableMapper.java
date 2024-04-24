package com.heli.safety.mapper;

import java.util.Date;
import java.util.List;

import com.heli.safety.domain.SafetyEpDeviceFaultData;
import com.heli.safety.domain.SafetyEpMaintenanceTable;
import org.apache.ibatis.annotations.Param;

/**
 * 安环科
 * 维修数据Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-10
 */
public interface SafetyEpMaintenanceTableMapper {


    int batchInsertSafetyEpMaintenanceTable(@Param("safetyEpMaintenanceTableList") List<SafetyEpMaintenanceTable> safetyEpMaintenanceTableList);

    List<SafetyEpDeviceFaultData> countDeviceFaultData();

    int deviceFaultCategoryCountDataForDistribution(@Param("deviceFaultDataList") List<SafetyEpDeviceFaultData> deviceFaultDataList,
                                                    @Param("date") Date date);

    Boolean checkSafetyFillingDataIsExisted(@Param("date") Date date);


    void truncateTable();



    int countMajorEquipmentFailuresInCurrentMonth();


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
     * 删除安环科
     * 维修数据
     *
     * @param semId 安环科
     *              维修数据主键
     * @return 结果
     */
    public int deleteSafetyEpMaintenanceTableBySemId(String semId);

    /**
     * 批量删除安环科
     * 维修数据
     *
     * @param semIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSafetyEpMaintenanceTableBySemIds(String[] semIds);
}
