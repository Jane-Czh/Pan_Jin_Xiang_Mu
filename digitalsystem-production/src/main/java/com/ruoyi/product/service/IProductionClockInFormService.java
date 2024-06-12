package com.ruoyi.product.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.WorkTimeData;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工打卡表Service接口
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public interface IProductionClockInFormService 
{
    /**
     * 查询员工打卡表
     * 
     * @param pcifId 员工打卡表主键
     * @return 员工打卡表
     */
    public ProductionClockInForm selectProductionClockInFormByPcifId(Long pcifId);

    /**
     * 查询员工打卡表列表
     * 
     * @param productionClockInForm 员工打卡表
     * @return 员工打卡表集合
     */
    public List<ProductionClockInForm> selectProductionClockInFormList(ProductionClockInForm productionClockInForm);

    //空参
    public List<ProductionClockInForm> selectProductionClockInFormList1();
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
     * 批量删除员工打卡表
     * 
     * @param pcifIds 需要删除的员工打卡表主键集合
     * @return 结果
     */
    public int deleteProductionClockInFormByPcifIds(Long[] pcifIds);

    /**
     * 删除员工打卡表信息
     * 
     * @param pcifId 员工打卡表主键
     * @return 结果
     */
    public int deleteProductionClockInFormByPcifId(Long pcifId);

    public int importInterests(MultipartFile excelFile) throws IOException;

    public void updateTime(List<ProductionClockInForm> list1, WorkTimeData workTimeData) throws ParseException;


//    void updatetime(List<ProductionClockInForm> lsit, String combinedDateTime);
}
