package com.heli.financial.mapper;

import java.util.List;
import com.heli.financial.domain.FinancialBalanceTable;

/**
 * 财务-资产负债Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-03
 */
public interface FinancialBalanceTableMapper {
    /**
     * 查询财务-资产负债
     *
     * @param fbId 财务-资产负债主键
     * @return 财务-资产负债
     */
    public FinancialBalanceTable selectFinancialBalanceTableByFbId(Long fbId);

    /**
     * 查询财务-资产负债列表
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 财务-资产负债集合
     */
    public List<FinancialBalanceTable> selectFinancialBalanceTableList(FinancialBalanceTable financialBalanceTable);

    /**
     * 新增财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    public int insertFinancialBalanceTable(FinancialBalanceTable financialBalanceTable);

    /**
     * 修改财务-资产负债
     *
     * @param financialBalanceTable 财务-资产负债
     * @return 结果
     */
    public int updateFinancialBalanceTable(FinancialBalanceTable financialBalanceTable);

    /**
     * 删除财务-资产负债
     *
     * @param fbId 财务-资产负债主键
     * @return 结果
     */
    public int deleteFinancialBalanceTableByFbId(Long fbId);

    /**
     * 批量删除财务-资产负债
     *
     * @param fbIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialBalanceTableByFbIds(Long[] fbIds);
}
