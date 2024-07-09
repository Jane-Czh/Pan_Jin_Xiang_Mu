package com.ruoyi.product.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;
import org.springframework.web.multipart.MultipartFile;

/**
 * 自制件合格率Service接口
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public interface IProuctionFunctionQualifiedRateService 
{
    /**
     * 查询自制件合格率
     * 
     * @param pqrId 自制件合格率主键
     * @return 自制件合格率
     */
    public ProuctionFunctionQualifiedRate selectProuctionFunctionQualifiedRateByPqrId(Long pqrId);

    /**
     * 查询自制件合格率列表
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 自制件合格率集合
     */
    public List<ProuctionFunctionQualifiedRate> selectProuctionFunctionQualifiedRateList(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate);

    /**
     * 新增自制件合格率
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 结果
     */
    public int insertProuctionFunctionQualifiedRate(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate);

    /**
     * 修改自制件合格率
     * 
     * @param prouctionFunctionQualifiedRate 自制件合格率
     * @return 结果
     */
    public int updateProuctionFunctionQualifiedRate(ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate);

    /**
     * 批量删除自制件合格率
     * 
     * @param pqrIds 需要删除的自制件合格率主键集合
     * @return 结果
     */
    public int deleteProuctionFunctionQualifiedRateByPqrIds(Long[] pqrIds);

    /**
     * 删除自制件合格率信息
     * 
     * @param pqrId 自制件合格率主键
     * @return 结果
     */
    public int deleteProuctionFunctionQualifiedRateByPqrId(Long pqrId);

    public int importInterests(MultipartFile excelFile) throws IOException;

    public List<ProuctionFunctionQualifiedRate> selectProuctionFunctionQualifiedRateList1();

    public void Synchronization(List<ProuctionFunctionQualifiedRate> list, ProuctionFunctionQualifiedRate prouctionFunctionQualifiedRate1);
}
