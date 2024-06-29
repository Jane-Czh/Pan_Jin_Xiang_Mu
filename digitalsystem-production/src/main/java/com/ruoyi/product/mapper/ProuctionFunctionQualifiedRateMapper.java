package com.ruoyi.product.mapper;

import java.util.List;
import com.ruoyi.product.domain.ProuctionFunctionQualifiedRate;

/**
 * 自制件合格率Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-16
 */
public interface ProuctionFunctionQualifiedRateMapper 
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
     * 删除自制件合格率
     * 
     * @param pqrId 自制件合格率主键
     * @return 结果
     */
    public int deleteProuctionFunctionQualifiedRateByPqrId(Long pqrId);

    /**
     * 批量删除自制件合格率
     * 
     * @param pqrIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProuctionFunctionQualifiedRateByPqrIds(Long[] pqrIds);

    //查询数据库最后一条数据id
    public Long selectLastId();
}
