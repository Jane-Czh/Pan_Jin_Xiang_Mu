package com.ruoyi.file.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.file.domain.RegulationsImplementationTable;
import com.ruoyi.file.entity.regulationImplementationRateRespondEntity;
import com.ruoyi.file.entity.regulationRespondEntity;

/**
 * 制度执行率Service接口
 *
 * @author ruoyi
 * @date 2024-05-30
 */
public interface IRegulationsImplementationTableService
{
    /**
     * 查询制度执行率
     *
     * @param id 制度执行率主键
     * @return 制度执行率
     */
    public RegulationsImplementationTable selectRegulationsImplementationTableById(Long id);

    /**
     * 查询制度执行率列表
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 制度执行率集合
     */
    public List<RegulationsImplementationTable> selectRegulationsImplementationTableList(RegulationsImplementationTable regulationsImplementationTable);

    /**
     * 新增制度执行率
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 结果
     */
    public int insertRegulationsImplementationTable(RegulationsImplementationTable regulationsImplementationTable);

    /**
     * 修改制度执行率
     *
     * @param regulationsImplementationTable 制度执行率
     * @return 结果
     */
    public int updateRegulationsImplementationTable(RegulationsImplementationTable regulationsImplementationTable);

    /**
     * 批量删除制度执行率
     *
     * @param ids 需要删除的制度执行率主键集合
     * @return 结果
     */
    public int deleteRegulationsImplementationTableByIds(Long[] ids);

    /**
     * 删除制度执行率信息
     *
     * @param id 制度执行率主键
     * @return 结果
     */
    public int deleteRegulationsImplementationTableById(Long id);

    /**
     * 制度执行率统计
     */
    List<regulationImplementationRateRespondEntity> countImplementationRate(Date startTime, Date endTime, RegulationsImplementationTable regulationsImplementationTable);
}
