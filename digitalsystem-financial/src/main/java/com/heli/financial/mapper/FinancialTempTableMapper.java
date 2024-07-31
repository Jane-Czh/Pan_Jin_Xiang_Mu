package com.heli.financial.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.heli.financial.domain.FinancialTempTable;
import org.apache.ibatis.annotations.Param;

/**
 * 中转表Mapper接口
 *
 * @author hong
 * @date 2024-07-31
 */
public interface FinancialTempTableMapper {

    void clearTempTable();

    String selectAmountByName(@Param("rowName") String rowName);

    int batchInsertTempTable(@Param("tempTableList") List<FinancialTempTable> tempTableList);

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
     * 删除中转表
     *
     * @param ftId 中转表主键
     * @return 结果
     */
    public int deleteFinancialTempTableByFtId(Long ftId);

    /**
     * 批量删除中转表
     *
     * @param ftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialTempTableByFtIds(Long[] ftIds);


}
