package com.ruoyi.file.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.DisplayEntity;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.entity.regulationRespondEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 文件管理Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-17
 */
public interface RegulationsInfoTableMapper
{
    /**
     * 根据id查找filename
     * 流程部分依赖使用
     */
    List<String> selectFileNamesByIds(@Param("regulationsIds") List<String> regulationsIds);


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

//    /**
//     * 制度修订频率
//     */
//     List<regulationRespondEntity> selectRevisionFrequency(@Param("startTime") Date startTime,
//                                                           @Param("endTime") Date endTime);
}
