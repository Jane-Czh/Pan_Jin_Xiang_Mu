package com.ruoyi.Technology.mapper;

import java.util.List;
import com.ruoyi.Technology.domain.TechnologyFunctionMaterialRetention;

/**
 * 申报材料统计Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-28
 */
public interface TechnologyFunctionMaterialRetentionMapper 
{
    /**
     * 查询申报材料统计
     * 
     * @param tfemId 申报材料统计主键
     * @return 申报材料统计
     */
    public TechnologyFunctionMaterialRetention selectTechnologyFunctionMaterialRetentionByTfemId(Long tfemId);

    /**
     * 查询申报材料统计列表
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 申报材料统计集合
     */
    public List<TechnologyFunctionMaterialRetention> selectTechnologyFunctionMaterialRetentionList(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention);

    /**
     * 新增申报材料统计
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 结果
     */
    public int insertTechnologyFunctionMaterialRetention(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention);

    /**
     * 修改申报材料统计
     * 
     * @param technologyFunctionMaterialRetention 申报材料统计
     * @return 结果
     */
    public int updateTechnologyFunctionMaterialRetention(TechnologyFunctionMaterialRetention technologyFunctionMaterialRetention);

    /**
     * 删除申报材料统计
     * 
     * @param tfemId 申报材料统计主键
     * @return 结果
     */
    public int deleteTechnologyFunctionMaterialRetentionByTfemId(Long tfemId);

    /**
     * 批量删除申报材料统计
     * 
     * @param tfemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTechnologyFunctionMaterialRetentionByTfemIds(Long[] tfemIds);
}
