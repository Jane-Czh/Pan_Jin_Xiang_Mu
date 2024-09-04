package com.ruoyi.product.mapper;

import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.ProductionCommuteTime;

/**
 * 员工打卡表Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public interface ProductionClockInFormMapper 
{
    /**
     * 查询员工打卡表
     * 
     * @param pcifId 员工打卡表主键
     * @return 员工打卡表
     */
    public ProductionClockInForm selectProductionClockInFormByPcifId(Long pcifId);

    public long selectProductionCommuteTimeByCommuteTimeMAXId();

    public ProductionCommuteTime selectProductionCommuteTimeByCommuteTimeId(Long commuteTimeId);

    /**
     * 查询员工打卡表列表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 员工打卡表集合
     */
    public List<ProductionClockInForm> selectProductionClockInFormList(ProductionClockInForm productionClockInForm);

    /**
     * 新增员工打卡表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 结果
     */
    public int insertProductionClockInForm(ProductionClockInForm productionClockInForm);

    /**
     * 修改员工打卡表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 结果
     */
    public int updateProductionClockInForm(ProductionClockInForm productionClockInForm);

    /**
     * 删除员工打卡表
     * 
     * @param pcifId 员工打卡表主键
     * @return 结果
     */
    public int deleteProductionClockInFormByPcifId(Long pcifId);

    /**
     * 批量删除员工打卡表
     * 
     * @param pcifIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductionClockInFormByPcifIds(Long[] pcifIds);

    public Long selectLastId();

    public List<ProductionClockInForm> selectProductionClockInFormList1();

    void insertBatch(List<ProductionClockInForm> formList);
}
