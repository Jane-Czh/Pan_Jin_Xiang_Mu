package com.ruoyi.Technology.service;

import java.util.List;
import com.ruoyi.Technology.domain.TechnologyFunctionChangeorder;

/**
 * 变更单留存Service接口
 * 
 * @author ruoyi
 * @date 2024-06-09
 */
public interface ITechnologyFunctionChangeorderService 
{
    /**
     * 查询变更单留存
     * 
     * @param tfcoId 变更单留存主键
     * @return 变更单留存
     */
    public TechnologyFunctionChangeorder selectTechnologyFunctionChangeorderByTfcoId(Long tfcoId);

    /**
     * 查询变更单留存列表
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 变更单留存集合
     */
    public List<TechnologyFunctionChangeorder> selectTechnologyFunctionChangeorderList(TechnologyFunctionChangeorder technologyFunctionChangeorder);

    /**
     * 新增变更单留存
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 结果
     */
    public int insertTechnologyFunctionChangeorder(TechnologyFunctionChangeorder technologyFunctionChangeorder);

    /**
     * 修改变更单留存
     * 
     * @param technologyFunctionChangeorder 变更单留存
     * @return 结果
     */
    public int updateTechnologyFunctionChangeorder(TechnologyFunctionChangeorder technologyFunctionChangeorder);

    /**
     * 批量删除变更单留存
     * 
     * @param tfcoIds 需要删除的变更单留存主键集合
     * @return 结果
     */
    public int deleteTechnologyFunctionChangeorderByTfcoIds(Long[] tfcoIds);

    /**
     * 删除变更单留存信息
     * 
     * @param tfcoId 变更单留存主键
     * @return 结果
     */
    public int deleteTechnologyFunctionChangeorderByTfcoId(Long tfcoId);
}
