package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;

/**
 * 计划完成情况Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-15
 */
public interface ProductionFunctionStatisticsOfPlanCompletionStatusMapper 
{
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
     * 删除计划完成情况
     * 
     * @param mpcId 计划完成情况主键
     * @return 结果
     */
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcId(Long mpcId);

    /**
     * 批量删除计划完成情况
     * 
     * @param mpcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionFunctionStatisticsOfPlanCompletionStatusByMpcIds(Long[] mpcIds);

    void insertBatch(List<ProductionFunctionStatisticsOfPlanCompletionStatus> batchLedgers);
}
