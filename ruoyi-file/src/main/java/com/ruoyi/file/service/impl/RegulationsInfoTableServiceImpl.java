package com.ruoyi.file.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.RegulationsInfoTableMapper;
import com.ruoyi.file.domain.RegulationsInfoTable;
import com.ruoyi.file.service.IRegulationsInfoTableService;

/**
 * 文件管理Service业务层处理
 *
 * @author ruoyi
 * @date 2024-04-10
 */
@Service
public class RegulationsInfoTableServiceImpl implements IRegulationsInfoTableService
{
    @Autowired
    private RegulationsInfoTableMapper regulationsInfoTableMapper;

    /**
     * 查询文件管理
     *
     * @param regulationsId 文件管理主键
     * @return 文件管理
     */
    @Override
    public RegulationsInfoTable selectRegulationsInfoTableByRegulationsId(Long regulationsId)
    {
        return regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(regulationsId);
    }

    /**
     * 查询文件管理列表
     *
     * @param regulationsInfoTable 文件管理
     * @return 文件管理
     */
    @Override
    public List<RegulationsInfoTable> selectRegulationsInfoTableList(RegulationsInfoTable regulationsInfoTable)
    {
        return regulationsInfoTableMapper.selectRegulationsInfoTableList(regulationsInfoTable);
    }


    /**
     * 查询历史版本文件列表
     *
     * @param currentId 文件管理
     * @return 文件管理
     */
    @Override
    public List<RegulationsInfoTable> getRegulationsHistory(Long currentId) {
        //返回结果
        List<RegulationsInfoTable> historyList = new ArrayList<>();
        Long oldId = regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(currentId).getOldRegulationsId();
        while (oldId != null) {
            RegulationsInfoTable record = regulationsInfoTableMapper.selectRegulationsInfoTableByRegulationsId(oldId);
            if (record != null) {
                historyList.add(record);
                oldId = record.getOldRegulationsId();
            } else {
                break;
            }
        }
        return historyList;
    }

    /**
     * 新增文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    @Override
    public int insertRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable)
    {
        regulationsInfoTableMapper.insertRegulationsInfoTable(regulationsInfoTable);
        Long rId = regulationsInfoTable.getRegulationsId();
        return Math.toIntExact(rId);
    }

    /**
     * 修改文件管理
     *
     * @param regulationsInfoTable 文件管理
     * @return 结果
     */
    @Override
    public int updateRegulationsInfoTable(RegulationsInfoTable regulationsInfoTable)
    {
        return regulationsInfoTableMapper.updateRegulationsInfoTable(regulationsInfoTable);
    }

    /**
     * 批量删除文件管理
     *
     * @param regulationsIds 需要删除的文件管理主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsInfoTableByRegulationsIds(Long[] regulationsIds)
    {
        return regulationsInfoTableMapper.deleteRegulationsInfoTableByRegulationsIds(regulationsIds);
    }

    /**
     * 删除文件管理信息
     *
     * @param regulationsId 文件管理主键
     * @return 结果
     */
    @Override
    public int deleteRegulationsInfoTableByRegulationsId(Long regulationsId)
    {
        return regulationsInfoTableMapper.deleteRegulationsInfoTableByRegulationsId(regulationsId);
    }
}
