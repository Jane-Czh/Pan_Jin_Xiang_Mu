package com.heli.production.service.impl;

import java.util.List;

import com.heli.production.domain.ProductionFilling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heli.production.mapper.ProductionFillingMapper;
import com.heli.production.service.IProductionFillingService;

/**
 * @description: [生产]手动填报指标功能Service业务层处理
 * @author: hong
 * @date: 2024/4/11 20:14
 * @param:
 * @return:
 **/
@Service
public class ProductionFillingServiceImpl implements IProductionFillingService {
    @Autowired
    private ProductionFillingMapper productionFillingMapper;

    /**
     * 查询[生产]手动填报指标功能
     *
     * @param productionId [生产]手动填报指标功能主键
     * @return [生产]手动填报指标功能
     */
    @Override
    public ProductionFilling selectProductionByProductionId(Long productionId) {
        return productionFillingMapper.selectProductionByProductionId(productionId);
    }

    /**
     * 查询[生产]手动填报指标功能列表
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return [生产]手动填报指标功能
     */
    @Override
    public List<ProductionFilling> selectProductionList(ProductionFilling ProductionFilling) {
        return productionFillingMapper.selectProductionList(ProductionFilling);
    }

    /**
     * 新增[生产]手动填报指标功能
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return 结果
     */
    @Override
    public int insertProduction(ProductionFilling ProductionFilling) {
        return productionFillingMapper.insertProduction(ProductionFilling);
    }

    /**
     * 修改[生产]手动填报指标功能
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return 结果
     */
    @Override
    public int updateProduction(ProductionFilling ProductionFilling) {
        return productionFillingMapper.updateProduction(ProductionFilling);
    }

    /**
     * 批量删除[生产]手动填报指标功能
     *
     * @param productionIds 需要删除的[生产]手动填报指标功能主键
     * @return 结果
     */
    @Override
    public int deleteProductionByProductionIds(Long[] productionIds) {
        return productionFillingMapper.deleteProductionByProductionIds(productionIds);
    }

    /**
     * 删除[生产]手动填报指标功能信息
     *
     * @param productionId [生产]手动填报指标功能主键
     * @return 结果
     */
    @Override
    public int deleteProductionByProductionId(Long productionId) {
        return productionFillingMapper.deleteProductionByProductionId(productionId);
    }
}
