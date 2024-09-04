package com.ruoyi.product.service;

import java.util.List;
import com.ruoyi.product.domain.ProductionCommuteTime;
import com.ruoyi.product.domain.WorkTimeData;

/**
 * 设置上下班时间Service接口
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
public interface IProductionCommuteTimeService 
{
    /**
     * 查询设置上下班时间
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 设置上下班时间
     */
    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeId(Long commuteTimeId);

    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeMAXId();

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
     * 批量删除设置上下班时间
     * 
     * @param commuteTimeIds 需要删除的设置上下班时间主键集合
     * @return 结果
     */
    public int deleteProductionCommuteTimeByCommuteTimeIds(Long[] commuteTimeIds);

    /**
     * 删除设置上下班时间信息
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 结果
     */
    public int deleteProductionCommuteTimeByCommuteTimeId(Long commuteTimeId);

    public ProductionCommuteTime swtichdate(WorkTimeData workTimeData) ;


}
