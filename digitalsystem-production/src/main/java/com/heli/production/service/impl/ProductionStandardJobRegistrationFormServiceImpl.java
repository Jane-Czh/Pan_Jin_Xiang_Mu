package com.heli.production.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.production.mapper.ProductionStandardJobRegistrationFormMapper;
import com.heli.production.domain.ProductionStandardJobRegistrationForm;
import com.heli.production.service.IProductionStandardJobRegistrationFormService;

/**
 * 记录标准作业信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@Service
public class ProductionStandardJobRegistrationFormServiceImpl implements IProductionStandardJobRegistrationFormService 
{
    @Autowired
    private ProductionStandardJobRegistrationFormMapper productionStandardJobRegistrationFormMapper;

    /**
     * 查询记录标准作业信息
     * 
     * @param psjrfId 记录标准作业信息主键
     * @return 记录标准作业信息
     */
    @Override
    public ProductionStandardJobRegistrationForm selectProductionStandardJobRegistrationFormByPsjrfId(Long psjrfId)
    {
        return productionStandardJobRegistrationFormMapper.selectProductionStandardJobRegistrationFormByPsjrfId(psjrfId);
    }

    /**
     * 查询记录标准作业信息列表
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 记录标准作业信息
     */
    @Override
    public List<ProductionStandardJobRegistrationForm> selectProductionStandardJobRegistrationFormList(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        return productionStandardJobRegistrationFormMapper.selectProductionStandardJobRegistrationFormList(productionStandardJobRegistrationForm);
    }

    /**
     * 新增记录标准作业信息
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 结果
     */
    @Override
    public int insertProductionStandardJobRegistrationForm(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        return productionStandardJobRegistrationFormMapper.insertProductionStandardJobRegistrationForm(productionStandardJobRegistrationForm);
    }

    /**
     * 修改记录标准作业信息
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 结果
     */
    @Override
    public int updateProductionStandardJobRegistrationForm(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm)
    {
        return productionStandardJobRegistrationFormMapper.updateProductionStandardJobRegistrationForm(productionStandardJobRegistrationForm);
    }

    /**
     * 批量删除记录标准作业信息
     * 
     * @param psjrfIds 需要删除的记录标准作业信息主键
     * @return 结果
     */
    @Override
    public int deleteProductionStandardJobRegistrationFormByPsjrfIds(Long[] psjrfIds)
    {
        return productionStandardJobRegistrationFormMapper.deleteProductionStandardJobRegistrationFormByPsjrfIds(psjrfIds);
    }

    /**
     * 删除记录标准作业信息信息
     * 
     * @param psjrfId 记录标准作业信息主键
     * @return 结果
     */
    @Override
    public int deleteProductionStandardJobRegistrationFormByPsjrfId(Long psjrfId)
    {
        return productionStandardJobRegistrationFormMapper.deleteProductionStandardJobRegistrationFormByPsjrfId(psjrfId);
    }
}
