package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProductionCommuteTime;

/**
 * 设置上下班时间Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public interface ProductionCommuteTimeMapper 
{
    /**
     * 查询设置上下班时间
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 设置上下班时间
     */
    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeId(Long commuteTimeId);

    public long selectProductionCommuteTimeByCommuteTimeMAXId();

    /**
     * 查询设置上下班时间列表
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 设置上下班时间集合
     */
    public List<ProductionCommuteTime> selectProductionCommuteTimeList(ProductionCommuteTime productionCommuteTime);

    public List<ProductionCommuteTime> selectProductionCommuteTimeList1();

    /**
     * 新增设置上下班时间
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 结果
     */
    public int insertProductionCommuteTime(ProductionCommuteTime productionCommuteTime);

    /**
     * 修改设置上下班时间
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 结果
     */
    public int updateProductionCommuteTime(ProductionCommuteTime productionCommuteTime);

    /**
     * 删除设置上下班时间
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 结果
     */
    public int deleteProductionCommuteTimeByCommuteTimeId(Long commuteTimeId);

    /**
     * 批量删除设置上下班时间
     * 
     * @param commuteTimeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionCommuteTimeByCommuteTimeIds(Long[] commuteTimeIds);


}
