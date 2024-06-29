package com.ruoyi.product.service;

import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;

/**
 * 计划完成情况Service接口
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public interface IProductionFunctionStatisticsOfPlanCompletionStatusService 
{

    /**
     * 同步数据库销售快报数据
     *
     *
     *
     */
    public void Synchronization(List<MarketCommercialVehicleTable> list, ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus1);
    /**
     * 查询计划完成情况
     * 
     * @param mpcId 计划完成情况主键
     * @return 计划完成情况
     */
    public ProductionFunctionStatisticsOfPlanCompletionStatus selectProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(Long mpcId);

    /**
     * 查询计划完成情况列表
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 计划完成情况集合
     */
    public List<ProductionFunctionStatisticsOfPlanCompletionStatus> selectProductionFunctionStatisticsOfPlanCompletionStatusList(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus);

    /**
     * 新增计划完成情况
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 结果
     */
    public int insertProductionFunctionStatisticsOfPlanCompletionStatus(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus);

    /**
     * 修改计划完成情况
     * 
     * @param productionFunctionStatisticsOfPlanCompletionStatus 计划完成情况
     * @return 结果
     */
    public int updateProductionFunctionStatisticsOfPlanCompletionStatus(ProductionFunctionStatisticsOfPlanCompletionStatus productionFunctionStatisticsOfPlanCompletionStatus);

    /**
     * 批量删除计划完成情况
     * 
     * @param mpcIds 需要删除的计划完成情况主键集合
     * @return 结果
     */
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(Long[] mpcIds);

    /**
     * 删除计划完成情况信息
     * 
     * @param mpcId 计划完成情况主键
     * @return 结果
     */
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(Long mpcId);
}
