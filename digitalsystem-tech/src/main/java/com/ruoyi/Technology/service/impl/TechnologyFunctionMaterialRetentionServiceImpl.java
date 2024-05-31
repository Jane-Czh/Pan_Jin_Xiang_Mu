package com.ruoyi.Technology.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Technology.mapper.TechnologyFunctionMaterialRetentionMapper;
import com.ruoyi.Technology.domain.TechnologyFunctionMaterialRetention;
import com.ruoyi.Technology.service.ITechnologyFunctionMaterialRetentionService;

/**
 * 申报材料统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-28
 */
@Service
public class TechnologyFunctionMaterialRetentionServiceImpl implements ITechnologyFunctionMaterialRetentionService 
{
    @Autowired
    private TechnologyFunctionMaterialRetentionMapper technologyFunctionMaterialRetentionMapper;

    /**
     * 查询申报材料统计
     * 
     * @param tfemId 申报材料统计主键
     * @return 申报材料统计
     */
    @Override
    public TechnologyFunctionMaterialRetention selectTechnologyFunctionMaterialRetentionByTfemId(Long tfemId)
    {
        return technologyFunctionMaterialRetentionMapper.selectTechnologyFunctionMaterialRetentionByTfemId(tfemId);
    }

    /**
     * 查询申报材料统计列表
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 申报材料统计
     */
    @Override
    public List<TechnologyFunctionMaterialRetention> selectTechnologyFunctionMaterialRetentionList(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        return technologyFunctionMaterialRetentionMapper.selectTechnologyFunctionMaterialRetentionList(technologyFunctionMaterialRetention);
    }

    /**
     * 新增申报材料统计
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 结果
     */
    @Override
    public int insertTechnologyFunctionMaterialRetention(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        return technologyFunctionMaterialRetentionMapper.insertTechnologyFunctionMaterialRetention(technologyFunctionMaterialRetention);
    }

    /**
     * 修改申报材料统计
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 结果
     */
    @Override
    public int updateTechnologyFunctionMaterialRetention(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention)
    {
        return technologyFunctionMaterialRetentionMapper.updateTechnologyFunctionMaterialRetention(technologyFunctionMaterialRetention);
    }

    /**
     * 批量删除申报材料统计
     * 
     * @param tfemIds 需要删除的申报材料统计主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionMaterialRetentionByTfemIds(Long[] tfemIds)
    {
        return technologyFunctionMaterialRetentionMapper.deleteTechnologyFunctionMaterialRetentionByTfemIds(tfemIds);
    }

    /**
     * 删除申报材料统计信息
     * 
     * @param tfemId 申报材料统计主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionMaterialRetentionByTfemId(Long tfemId)
    {
        return technologyFunctionMaterialRetentionMapper.deleteTechnologyFunctionMaterialRetentionByTfemId(tfemId);
    }

    @Override
    public List<TechnologyFunctionMaterialRetention> getRegulationsHistory(Long currentId) {
        //返回结果
        List<TechnologyFunctionMaterialRetention> historyList = new ArrayList<>();
        Long oldId = technologyFunctionMaterialRetentionMapper.selectTechnologyFunctionMaterialRetentionByTfemId(currentId).getOldRegulationsId();
        while (oldId != null) {
            TechnologyFunctionMaterialRetention record = technologyFunctionMaterialRetentionMapper.selectTechnologyFunctionMaterialRetentionByTfemId(oldId);
            if (record != null) {
                historyList.add(record);
                oldId = record.getOldRegulationsId();
            } else {
                break;
            }
        }
        return historyList;
    }
}
