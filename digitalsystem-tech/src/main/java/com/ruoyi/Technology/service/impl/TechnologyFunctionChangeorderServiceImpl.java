package com.ruoyi.Technology.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Technology.mapper.TechnologyFunctionChangeorderMapper;
import com.ruoyi.Technology.domain.TechnologyFunctionChangeorder;
import com.ruoyi.Technology.service.ITechnologyFunctionChangeorderService;

/**
 * 变更单留存Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-09
 */
@Service
public class TechnologyFunctionChangeorderServiceImpl implements ITechnologyFunctionChangeorderService 
{
    @Autowired
    private TechnologyFunctionChangeorderMapper technologyFunctionChangeorderMapper;

    /**
     * 查询变更单留存
     * 
     * @param tfcoId 变更单留存主键
     * @return 变更单留存
     */
    @Override
    public TechnologyFunctionChangeorder selectTechnologyFunctionChangeorderByTfcoId(Long tfcoId)
    {
        return technologyFunctionChangeorderMapper.selectTechnologyFunctionChangeorderByTfcoId(tfcoId);
    }

    /**
     * 查询变更单留存列表
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 变更单留存
     */
    @Override
    public List<TechnologyFunctionChangeorder> selectTechnologyFunctionChangeorderList(TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        return technologyFunctionChangeorderMapper.selectTechnologyFunctionChangeorderList(technologyFunctionChangeorder);
    }

    /**
     * 新增变更单留存
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 结果
     */
    @Override
    public int insertTechnologyFunctionChangeorder(TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        return technologyFunctionChangeorderMapper.insertTechnologyFunctionChangeorder(technologyFunctionChangeorder);
    }

    /**
     * 修改变更单留存
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 结果
     */
    @Override
    public int updateTechnologyFunctionChangeorder(TechnologyFunctionChangeorder technologyFunctionChangeorder)
    {
        return technologyFunctionChangeorderMapper.updateTechnologyFunctionChangeorder(technologyFunctionChangeorder);
    }

    /**
     * 批量删除变更单留存
     * 
     * @param tfcoIds 需要删除的变更单留存主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionChangeorderByTfcoIds(Long[] tfcoIds)
    {
        return technologyFunctionChangeorderMapper.deleteTechnologyFunctionChangeorderByTfcoIds(tfcoIds);
    }

    /**
     * 删除变更单留存信息
     * 
     * @param tfcoId 变更单留存主键
     * @return 结果
     */
    @Override
    public int deleteTechnologyFunctionChangeorderByTfcoId(Long tfcoId)
    {
        return technologyFunctionChangeorderMapper.deleteTechnologyFunctionChangeorderByTfcoId(tfcoId);
    }
}
