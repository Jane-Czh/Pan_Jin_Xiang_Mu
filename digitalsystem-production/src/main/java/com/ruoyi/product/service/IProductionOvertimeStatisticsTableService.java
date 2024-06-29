package com.ruoyi.product.service;

import java.text.ParseException;
import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.ProductionFunctionStatisticsOfPlanCompletionStatus;
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.domain.ProductionOvertimeStatisticsTable;

/**
 * 加班统计Service接口
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public interface IProductionOvertimeStatisticsTableService 
{
    /**
     * 查询加班统计
     * 
     * @param postId 加班统计主键
     * @return 加班统计
     */
    public ProductionOvertimeStatisticsTable selectProductionOvertimeStatisticsTableByPostId(Long postId);

    /**
     * 查询加班统计列表
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 加班统计集合
     */
    public List<ProductionOvertimeStatisticsTable> selectProductionOvertimeStatisticsTableList(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable);

    /**
     * 新增加班统计
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 结果
     */
    public int insertProductionOvertimeStatisticsTable(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable);

    /**
     * 修改加班统计
     * 
     * @param productionOvertimeStatisticsTable 加班统计
     * @return 结果
     */
    public int updateProductionOvertimeStatisticsTable(ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable);

    /**
     * 批量删除加班统计
     * 
     * @param postIds 需要删除的加班统计主键集合
     * @return 结果
     */
    public int deleteProductionOvertimeStatisticsTableByPostIds(Long[] postIds);

    /**
     * 删除加班统计信息
     * 
     * @param postId 加班统计主键
     * @return 结果
     */
    public int deleteProductionOvertimeStatisticsTableByPostId(Long postId);

    public void Synchronization(List<ProductionClockInForm> list1,List<ProductionOvertimeApplicationForm> list2, ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable) throws ParseException;

//    public void Synchronization1(List<ProductionOvertimeApplicationForm> list2, ProductionOvertimeStatisticsTable productionOvertimeStatisticsTable1);
}
