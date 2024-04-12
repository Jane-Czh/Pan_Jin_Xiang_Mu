package com.heli.production.mapper;

import java.util.List;

import com.heli.production.domain.ProductionFilling;

/**
 * @description: [生产]手动填报指标功能Mapper接口
 * @author: hong
 * @date: 2024/4/11 20:13
 **/
public interface ProductionFillingMapper {
    /**
     * 查询[生产]手动填报指标功能
     *
     * @param productionId [生产]手动填报指标功能主键
     * @return [生产]手动填报指标功能
     */
    public ProductionFilling selectProductionByProductionId(Long productionId);

    /**
     * 查询[生产]手动填报指标功能列表
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return [生产]手动填报指标功能集合
     */
    public List<ProductionFilling> selectProductionList(ProductionFilling ProductionFilling);

    /**
     * 新增[生产]手动填报指标功能
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return 结果
     */
    public int insertProduction(ProductionFilling ProductionFilling);

    /**
     * 修改[生产]手动填报指标功能
     *
     * @param ProductionFilling [生产]手动填报指标功能
     * @return 结果
     */
    public int updateProduction(ProductionFilling ProductionFilling);

    /**
     * 删除[生产]手动填报指标功能
     *
     * @param productionId [生产]手动填报指标功能主键
     * @return 结果
     */
    public int deleteProductionByProductionId(Long productionId);

    /**
     * 批量删除[生产]手动填报指标功能
     *
     * @param productionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionByProductionIds(Long[] productionIds);
}
