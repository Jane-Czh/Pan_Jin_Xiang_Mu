package com.ruoyi.Technology.service;

import java.util.List;
import com.ruoyi.Technology.domain.TechnologyFunctionMaterialRetention;

/**
 * 申报材料统计Service接口
 * 
 * @author ruoyi
 * @date 2024-05-28
 */
public interface ITechnologyFunctionMaterialRetentionService 
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
     * 批量删除申报材料统计
     * 
     * @param tfemIds 需要删除的申报材料统计主键集合
     * @return 结果
     */
    public int deleteTechnologyFunctionMaterialRetentionByTfemIds(Long[] tfemIds);

    /**
     * 删除申报材料统计信息
     * 
     * @param tfemId 申报材料统计主键
     * @return 结果
     */
    public int deleteTechnologyFunctionMaterialRetentionByTfemId(Long tfemId);
    /**
     * 查询历史版本文件管理列表
     *
     * @param currentId 文件管理
     * @return 文件管理集合
     */
    public List<TechnologyFunctionMaterialRetention> getRegulationsHistory(Long currentId);

}
