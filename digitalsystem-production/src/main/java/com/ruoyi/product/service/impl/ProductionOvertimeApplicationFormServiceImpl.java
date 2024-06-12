package com.ruoyi.product.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import com.ruoyi.product.utils.ExcelUtilsPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProductionOvertimeApplicationFormMapper;
import com.ruoyi.product.domain.ProductionOvertimeApplicationForm;
import com.ruoyi.product.service.IProductionOvertimeApplicationFormService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 加班申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-13
 */
@Service
public class ProductionOvertimeApplicationFormServiceImpl implements IProductionOvertimeApplicationFormService 
{
    @Autowired
    private ProductionOvertimeApplicationFormMapper productionOvertimeApplicationFormMapper;

    /**
     * 查询加班申请
     * 
     * @param poafId 加班申请主键
     * @return 加班申请
     */
    @Override
    public ProductionOvertimeApplicationForm selectProductionOvertimeApplicationFormByPoafId(Long poafId)
    {
        return productionOvertimeApplicationFormMapper.selectProductionOvertimeApplicationFormByPoafId(poafId);
    }

    /**
     * 查询加班申请列表
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 加班申请
     */
    @Override
    public List<ProductionOvertimeApplicationForm> selectProductionOvertimeApplicationFormList(ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        return productionOvertimeApplicationFormMapper.selectProductionOvertimeApplicationFormList(productionOvertimeApplicationForm);
    }

    /**
     * 新增加班申请
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 结果
     */
    @Override
    public int insertProductionOvertimeApplicationForm(ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        return productionOvertimeApplicationFormMapper.insertProductionOvertimeApplicationForm(productionOvertimeApplicationForm);
    }

    /**
     * 修改加班申请
     * 
     * @param productionOvertimeApplicationForm 加班申请
     * @return 结果
     */
    @Override
    public int updateProductionOvertimeApplicationForm(ProductionOvertimeApplicationForm productionOvertimeApplicationForm)
    {
        return productionOvertimeApplicationFormMapper.updateProductionOvertimeApplicationForm(productionOvertimeApplicationForm);
    }

    /**
     * 批量删除加班申请
     * 
     * @param poafIds 需要删除的加班申请主键
     * @return 结果
     */
    @Override
    public int deleteProductionOvertimeApplicationFormByPoafIds(Long[] poafIds)
    {
        return productionOvertimeApplicationFormMapper.deleteProductionOvertimeApplicationFormByPoafIds(poafIds);
    }

    /**
     * 删除加班申请信息
     * 
     * @param poafId 加班申请主键
     * @return 结果
     */
    @Override
    public int deleteProductionOvertimeApplicationFormByPoafId(Long poafId)
    {
        return productionOvertimeApplicationFormMapper.deleteProductionOvertimeApplicationFormByPoafId(poafId);
    }

    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        ProductionOvertimeApplicationForm productionOvertimeApplicationForm;
        InputStream is = null;
        try {
            List<ProductionOvertimeApplicationForm> productionOvertimeApplicationForms = ExcelUtilsPro.OaparseExcel(excelFile);
            int i = 0;
            while (i < productionOvertimeApplicationForms.size()){
                productionOvertimeApplicationForm = productionOvertimeApplicationForms.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                long PQR_id = GenerateId.getNextId(lastid);
                productionOvertimeApplicationForm.setPoafId(PQR_id);
                if (productionOvertimeApplicationForm.getIdNumber() == null){
                    continue;
                }
                productionOvertimeApplicationFormMapper.insertProductionOvertimeApplicationForm(productionOvertimeApplicationForm);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("excel解析失败");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return 0;
    }

    @Override
    public List<ProductionOvertimeApplicationForm> selectProductionOvertimeApplicationFormList1() {
        return productionOvertimeApplicationFormMapper.selectProductionOvertimeApplicationFormList1();
    }

    //查询数据库最后一条数据id
    public Long selectLastId(){
        return productionOvertimeApplicationFormMapper.selectLastId();
    }
}
