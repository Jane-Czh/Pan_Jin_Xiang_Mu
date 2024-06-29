package com.ruoyi.product.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.product.domain.ProductionClockInForm;
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * 加班申请Service接口
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
public interface IProductionOvertimeApplicationFormService 
{
    /**
     * 查询加班申请
     * 
     * @param poafId 加班申请主键
     * @return 加班申请
     */
    public ProductionOvertimeApplicationForm selectProductionOvertimeApplicationFormByPoafId(Long poafId);

    /**
     * 查询加班申请列表
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 加班申请集合
     */
    public List<ProductionOvertimeApplicationForm> selectProductionOvertimeApplicationFormList(ProductionOvertimeApplicationForm productionOvertimeApplicationForm);

    /**
     * 新增加班申请
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 结果
     */
    public int insertProductionOvertimeApplicationForm(ProductionOvertimeApplicationForm productionOvertimeApplicationForm);

    /**
     * 修改加班申请
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 结果
     */
    public int updateProductionOvertimeApplicationForm(ProductionOvertimeApplicationForm productionOvertimeApplicationForm);

    /**
     * 批量删除加班申请
     * 
     * @param poafIds 需要删除的加班申请主键集合
     * @return 结果
     */
    public int deleteProductionOvertimeApplicationFormByPoafIds(Long[] poafIds);

    /**
     * 删除加班申请信息
     * 
     * @param poafId 加班申请主键
     * @return 结果
     */
    public int deleteProductionOvertimeApplicationFormByPoafId(Long poafId);

    public int importInterests(MultipartFile excelFile) throws IOException;

    //空参
    public List<ProductionOvertimeApplicationForm> selectProductionOvertimeApplicationFormList1();
}
