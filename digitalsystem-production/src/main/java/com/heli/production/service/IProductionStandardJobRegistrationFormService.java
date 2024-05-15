package com.heli.production.service;

import java.util.List;
import com.heli.production.domain.ProductionStandardJobRegistrationForm;

/**
 * 记录标准作业信息Service接口
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public interface IProductionStandardJobRegistrationFormService 
{
    /**
     * 查询记录标准作业信息
     * 
     * @param psjrfId 记录标准作业信息主键
     * @return 记录标准作业信息
     */
    public ProductionStandardJobRegistrationForm selectProductionStandardJobRegistrationFormByPsjrfId(Long psjrfId);

    /**
     * 查询记录标准作业信息列表
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 记录标准作业信息集合
     */
    public List<ProductionStandardJobRegistrationForm> selectProductionStandardJobRegistrationFormList(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm);

    /**
     * 新增记录标准作业信息
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 结果
     */
    public int insertProductionStandardJobRegistrationForm(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm);

    /**
     * 修改记录标准作业信息
     * 
     * @param productionStandardJobRegistrationForm 记录标准作业信息
     * @return 结果
     */
    public int updateProductionStandardJobRegistrationForm(ProductionStandardJobRegistrationForm productionStandardJobRegistrationForm);

    /**
     * 批量删除记录标准作业信息
     * 
     * @param psjrfIds 需要删除的记录标准作业信息主键集合
     * @return 结果
     */
    public int deleteProductionStandardJobRegistrationFormByPsjrfIds(Long[] psjrfIds);

    /**
     * 删除记录标准作业信息信息
     * 
     * @param psjrfId 记录标准作业信息主键
     * @return 结果
     */
    public int deleteProductionStandardJobRegistrationFormByPsjrfId(Long psjrfId);
}
