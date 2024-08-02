package com.heli.financial.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialTempTable;
import com.ruoyi.common.core.domain.R;

/**
 * 中转表Service接口
 *
 * @author hong
 * @date 2024-07-31
 */
public interface IFinancialTempTableService {

    void tempTableToBalanceTable(Date yearAndMonth);
    void tempTableToInterestsTable(Date yearAndMonth) ;

    void clearTempTable();

    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream);


    /**
     * 查询中转表
     *
     * @param ftId 中转表主键
     * @return 中转表
     */
    public FinancialTempTable selectFinancialTempTableByFtId(Long ftId);

    /**
     * 查询中转表列表
     *
     * @param financialTempTable 中转表
     * @return 中转表集合
     */
    public List<FinancialTempTable> selectFinancialTempTableList(FinancialTempTable financialTempTable);

    /**
     * 新增中转表
     *
     * @param financialTempTable 中转表
     * @return 结果
     */
    public int insertFinancialTempTable(FinancialTempTable financialTempTable);

    /**
     * 修改中转表
     *
     * @param financialTempTable 中转表
     * @return 结果
     */
    public int updateFinancialTempTable(FinancialTempTable financialTempTable);

    /**
     * 批量删除中转表
     *
     * @param ftIds 需要删除的中转表主键集合
     * @return 结果
     */
    public int deleteFinancialTempTableByFtIds(Long[] ftIds);

    /**
     * 删除中转表信息
     *
     * @param ftId 中转表主键
     * @return 结果
     */
    public int deleteFinancialTempTableByFtId(Long ftId);


}
