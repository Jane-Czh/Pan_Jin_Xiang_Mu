package com.ruoyi.ef.service;

import com.ruoyi.ef.entity.ModelEntity;

import java.util.List;


/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
public interface IModelService
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public ModelEntity selectModelById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param flowModel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<ModelEntity> selectModelList(ModelEntity modelEntity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param flowModel 【请填写功能名称】
     * @return 结果
     */
    public int insertModel(ModelEntity modelEntity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param flowModel 【请填写功能名称】
     * @return 结果
     */
    public int updateModel(ModelEntity modelEntity);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteModelByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteModelById(String id);
}
