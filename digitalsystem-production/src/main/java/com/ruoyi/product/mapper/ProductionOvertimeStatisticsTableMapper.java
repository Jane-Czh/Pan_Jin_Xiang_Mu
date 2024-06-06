package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProductionOvertimeStatisticsTable;

/**
 * 加班统计Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public interface ProductionOvertimeStatisticsTableMapper 
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
     * 删除加班统计
     * 
     * @param postId 加班统计主键
     * @return 结果
     */
    public int deleteProductionOvertimeStatisticsTableByPostId(Long postId);

    /**
     * 批量删除加班统计
     * 
     * @param postIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionOvertimeStatisticsTableByPostIds(Long[] postIds);

    public Long selectLastId();
}
