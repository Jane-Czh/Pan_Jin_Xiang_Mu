package com.ruoyi.file.mapper;

import java.util.List;
import com.ruoyi.file.domain.RegulationsInfoTable;

/**
 * 文件管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-10
 */
public interface RegulationsInfoTableMapper
{
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
     * 删除文件管理
     *
     * @param regulationsId 文件管理主键
     * @return 结果
     */
    public int deleteRegulationsInfoTableByRegulationsId(Long regulationsId);

    /**
     * 批量删除文件管理
     *
     * @param regulationsIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRegulationsInfoTableByRegulationsIds(Long[] regulationsIds);
}
