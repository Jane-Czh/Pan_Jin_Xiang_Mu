package com.ruoyi.ef.service.impl;

import java.util.List;

import com.ruoyi.ef.dao.ModelDao;
import com.ruoyi.ef.entity.ModelEntity;
import com.ruoyi.ef.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@Service
public class ModelServiceImpl implements IModelService
{
    @Autowired
    private ModelDao modelDao;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public ModelEntity selectModelById(Long id)
    {
        return modelDao.selectModelById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param flowModel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<ModelEntity> selectModelList(ModelEntity modelEntity)
    {
        return modelDao.selectModelList(modelEntity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param flowModel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertModel(ModelEntity modelEntity)
    {
        return modelDao.insertModel(modelEntity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param flowModel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateModel(ModelEntity modelEntity)
    {
        return modelDao.updateModel(modelEntity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteModelByIds(Long[] ids)
    {
        return modelDao.deleteModelByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteModelById(String id)
    {
        return modelDao.deleteModelById(id);
    }
}
