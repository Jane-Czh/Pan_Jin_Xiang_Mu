package com.ruoyi.product.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.market.domain.MarketSalesTable;
import com.ruoyi.market.utils.ExcelUtils;
import com.ruoyi.market.utils.GenerateId;
import com.ruoyi.market.utils.getTime;
import com.ruoyi.product.utils.ExcelUtilsPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ProuctionFunctionQualifiedRateMapper;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import com.ruoyi.product.service.IProuctionFunctionQualifiedRateService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 自制件合格率Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
@Service
public class ProuctionFunctionQualifiedRateServiceImpl implements IProuctionFunctionQualifiedRateService 
{
    @Autowired
    private ProuctionFunctionQualifiedRateMapper prouctionFunctionQualifiedRateMapper;

    /**
     * 查询自制件合格率
     * 
     * @param pqrId 自制件合格率主键
     * @return 自制件合格率
     */
    @Override
    public ProuctionFunctionQualifiedRate selectProuctionFunctionQualifiedRateByPqrId(Long pqrId)
    {
        return prouctionFunctionQualifiedRateMapper.selectProuctionFunctionQualifiedRateByPqrId(pqrId);
    }

    /**
     * 查询自制件合格率列表
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 自制件合格率
     */
    @Override
    public List<ProuctionFunctionQualifiedRate> selectProuctionFunctionQualifiedRateList(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        return prouctionFunctionQualifiedRateMapper.selectProuctionFunctionQualifiedRateList(prouctionFunctionQualifiedRate);
    }

    /**
     * 新增自制件合格率
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 结果
     */
    @Override
    public int insertProuctionFunctionQualifiedRate(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        return prouctionFunctionQualifiedRateMapper.insertProuctionFunctionQualifiedRate(prouctionFunctionQualifiedRate);
    }

    /**
     * 修改自制件合格率
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 结果
     */
    @Override
    public int updateProuctionFunctionQualifiedRate(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate)
    {
        return prouctionFunctionQualifiedRateMapper.updateProuctionFunctionQualifiedRate(prouctionFunctionQualifiedRate);
    }

    /**
     * 批量删除自制件合格率
     * 
     * @param pqrIds 需要删除的自制件合格率主键
     * @return 结果
     */
    @Override
    public int deleteProuctionFunctionQualifiedRateByPqrIds(Long[] pqrIds)
    {
        return prouctionFunctionQualifiedRateMapper.deleteProuctionFunctionQualifiedRateByPqrIds(pqrIds);
    }

    /**
     * 删除自制件合格率信息
     * 
     * @param pqrId 自制件合格率主键
     * @return 结果
     */
    @Override
    public int deleteProuctionFunctionQualifiedRateByPqrId(Long pqrId)
    {
        return prouctionFunctionQualifiedRateMapper.deleteProuctionFunctionQualifiedRateByPqrId(pqrId);
    }
    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:50
     * @param: excel文件
     * @return: 影响的行数
     **/
    @Override
    public int importInterests(MultipartFile excelFile) throws IOException {
        ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate;
        InputStream is = null;
        try {
            List<ProuctionFunctionQualifiedRate> prouctionFunctionQualifiedRates = ExcelUtilsPro.parseExcel(excelFile);
            int i = 0;
            while (i < prouctionFunctionQualifiedRates.size()){
                prouctionFunctionQualifiedRate = prouctionFunctionQualifiedRates.get(i);
                Long lastid = selectLastId();
                if(lastid == null){
                    lastid = 0L;
                }
                long PQR_id = GenerateId.getNextId(lastid);
                prouctionFunctionQualifiedRate.setPqrId(PQR_id);
                if (prouctionFunctionQualifiedRate.getModel() == null){
                    continue;
                }
                prouctionFunctionQualifiedRateMapper.insertProuctionFunctionQualifiedRate(prouctionFunctionQualifiedRate);
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
    //查询数据库最后一条数据id
    public Long selectLastId(){
        return prouctionFunctionQualifiedRateMapper.selectLastId();
    }
}

