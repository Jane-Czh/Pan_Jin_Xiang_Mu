package com.ruoyi.file.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.entity.regulationRespondEntity;
import com.ruoyi.file.entity.regulationCountsRespondEntity;
import com.ruoyi.file.entity.regulationCountsByClassificationRespondEntity;

/**
 * 文件管理Service接口
 *
 * @author ruoyi
 * @date 2024-04-17
 */
public interface IRegulationsInfoTableService {
    /**
     * 根据id查找filename
     * 流程部分依赖使用
     */

    List<String> selectFileNamesByIds(String regulationsIds);


    /**
     * 查询文件管理
     *
     * @param regulationsId 文件管理主键
     * @return 文件管理
     */
    public RegulationsInfoTable selectRegulationsInfoTableByRegulationsId(Long regulationsId);

    /**
     * 查询文件管理列表
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理集合
     */
    public List<RegulationsInfoTable> selectRegulationsInfoTableList(RegulationsInfoTable regulationsInfoTable);

    /**
     * 查询文件管理列表(全部)
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理集合
     */
    public List<RegulationsInfoTable> selectAllRegulationsList(RegulationsInfoTable regulationsInfoTable);

    /**
     * 查询回收站列表
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理集合
     */
    public List<RegulationsInfoTable> selectRegulationRecycleList(RegulationsInfoTable regulationsInfoTable);


    /**
     * 查询所有历史版本文件管理列表
     *
     * */
    public List<RegulationsInfoTable> listHistoryFilemanagement(RegulationsInfoTable regulationsInfoTable);

    /**
     * 查询历史版本文件管理列表
     *
     * @param currentId 文件管理
     * @return 文件管理集合
     */
    public List<RegulationsInfoTable> getRegulationsHistory(Long currentId);

    /**
     * 新增文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    public int insertRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable);

    /**
     * 修改文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    public int updateRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable);

    /**
     * 批量删除文件管理
     *
     * @param regulationsIds 需要删除的文件管理主键集合
     * @return 结果
     */
    public int deleteRegulationsInfoTableByRegulationsIds(Long[] regulationsIds);

    /**
     * 删除文件管理信息
     *
     * @param regulationsId 文件管理主键
     * @return 结果
     */
    public int deleteRegulationsInfoTableByRegulationsId(Long regulationsId);

    /**
     * 制度修订频率
     */
    List<regulationRespondEntity> selectRevisionFrequency(Date startTime, Date endTime, Long regulationId);

    List<regulationCountsRespondEntity> selectRegulationsCounts(Date startTime, Date endTime);

    List<regulationCountsByClassificationRespondEntity> selectRegulationsCountsByClassification(Date startTime, Date endTime, String mainResponsibleDepartment);
}
