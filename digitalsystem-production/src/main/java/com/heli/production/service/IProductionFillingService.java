package com.heli.production.service;

import java.util.Date;
import java.util.List;

import com.heli.production.domain.ProductionFilling;
import org.apache.ibatis.annotations.Param;

/**
 * [生产]手动填报指标功能Service接口
 *
 * @author ruoyi
 * @date 2024-04-10
 */
public interface IProductionFillingService {



    Boolean checkProductionFillingDataIsExisted(Date date);

    List<Date> selectExistedYearAndMonth();

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
     * 批量删除[生产]手动填报指标功能
     *
     * @param productionIds 需要删除的[生产]手动填报指标功能主键集合
     * @return 结果
     */
    public int deleteProductionByProductionIds(Long[] productionIds);

    /**
     * 删除[生产]手动填报指标功能信息
     *
     * @param productionId [生产]手动填报指标功能主键
     * @return 结果
     */
    public int deleteProductionByProductionId(Long productionId);
}
