package com.heli.financial.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialInterestsTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务-利润Service接口
 *
 * @author hong
 * @date 2024-03-31
 */
public interface IFinancialInterestsTableService {


    /**
     * @description: 利润表导入
     * @author: hong
     * @date: 2024/3/31 13:48
     */
    public int importInterestsTable(String createdBy, Date createdTime, Date yearAndMonth, MultipartFile excelFile) throws IOException;

    public int importInterests(MultipartFile excelFile) throws IOException;

    public BigDecimal selectOperatingRevenueByMonth(Date date);


    /**
     * 查询财务-利润
     *
     * @param fiId 财务-利润主键
     * @return 财务-利润
     */
    public FinancialInterestsTable selectFinancialInterestsTableByFiId(Long fiId);

    /**
     * 查询财务-利润列表
     *
     * @param financialInterestsTable 财务-利润
     * @return 财务-利润集合
     */
    public List<FinancialInterestsTable> selectFinancialInterestsTableList(FinancialInterestsTable financialInterestsTable);

    /**
     * 新增财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    public int insertFinancialInterestsTable(FinancialInterestsTable financialInterestsTable);

    /**
     * 修改财务-利润
     *
     * @param financialInterestsTable 财务-利润
     * @return 结果
     */
    public int updateFinancialInterestsTable(FinancialInterestsTable financialInterestsTable);

    /**
     * 批量删除财务-利润
     *
     * @param fiIds 需要删除的财务-利润主键集合
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiIds(Long[] fiIds);

    /**
     * 删除财务-利润信息
     *
     * @param fiId 财务-利润主键
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiId(Long fiId);
}
