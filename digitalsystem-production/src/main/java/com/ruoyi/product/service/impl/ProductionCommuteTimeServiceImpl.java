package com.ruoyi.product.service.impl;

import com.ruoyi.product.domain.ProductionCommuteTime;
import com.ruoyi.product.domain.WorkTimeData;
import com.ruoyi.product.mapper.ProductionCommuteTimeMapper;
import com.ruoyi.product.service.IProductionCommuteTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置上下班时间Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-24
 */
@Service
public class ProductionCommuteTimeServiceImpl implements IProductionCommuteTimeService 
{
    @Autowired
    private ProductionCommuteTimeMapper productionCommuteTimeMapper;

    /**
     * 查询设置上下班时间
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 设置上下班时间
     */
    @Override
    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeId(Long commuteTimeId)
    {
        return productionCommuteTimeMapper.selectProductionCommuteTimeByCommuteTimeId(commuteTimeId);
    }

    @Override
    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeMAXId() {
        long maxid = productionCommuteTimeMapper.selectProductionCommuteTimeByCommuteTimeMAXId();
        ProductionCommuteTime rellytime = productionCommuteTimeMapper.selectProductionCommuteTimeByCommuteTimeId(maxid);
        System.out.println("//////////"+rellytime+"////////");
        return rellytime;
    }

    /**
     * 查询设置上下班时间列表
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 设置上下班时间
     */
    @Override
    public List<ProductionCommuteTime> selectProductionCommuteTimeList(ProductionCommuteTime productionCommuteTime)
    {
        return productionCommuteTimeMapper.selectProductionCommuteTimeList(productionCommuteTime);
    }

    @Override
    public List<ProductionCommuteTime> selectProductionCommuteTimeList1() {
        return productionCommuteTimeMapper.selectProductionCommuteTimeList1();
    }

    /**
     * 新增设置上下班时间
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 结果
     */
    @Override
    public int insertProductionCommuteTime(ProductionCommuteTime productionCommuteTime)
    {
        return productionCommuteTimeMapper.insertProductionCommuteTime(productionCommuteTime);
    }

    /**
     * 修改设置上下班时间
     * 
     * @param productionCommuteTime 设置上下班时间
     * @return 结果
     */
    @Override
    public int updateProductionCommuteTime(ProductionCommuteTime productionCommuteTime)
    {
        return productionCommuteTimeMapper.updateProductionCommuteTime(productionCommuteTime);
    }

    /**
     * 批量删除设置上下班时间
     * 
     * @param commuteTimeIds 需要删除的设置上下班时间主键
     * @return 结果
     */
    @Override
    public int deleteProductionCommuteTimeByCommuteTimeIds(Long[] commuteTimeIds)
    {
        return productionCommuteTimeMapper.deleteProductionCommuteTimeByCommuteTimeIds(commuteTimeIds);
    }

    /**
     * 删除设置上下班时间信息
     * 
     * @param commuteTimeId 设置上下班时间主键
     * @return 结果
     */
    @Override
    public int deleteProductionCommuteTimeByCommuteTimeId(Long commuteTimeId)
    {
        return productionCommuteTimeMapper.deleteProductionCommuteTimeByCommuteTimeId(commuteTimeId);
    }

    @Override
    public ProductionCommuteTime swtichdate(WorkTimeData workTimeData) {
//        新增上下班時間
        ProductionCommuteTime productionCommuteTime = new ProductionCommuteTime();
        productionCommuteTime.setWorkingHours(workTimeData.getStartTime());
        productionCommuteTime.setOffHours(workTimeData.getEndTime());
        productionCommuteTimeMapper.insertProductionCommuteTime(productionCommuteTime);
        System.out.println("++++時間設置完成"+productionCommuteTime.getWorkingHours()+productionCommuteTime.getOffHours());
        return null;
    }
}
