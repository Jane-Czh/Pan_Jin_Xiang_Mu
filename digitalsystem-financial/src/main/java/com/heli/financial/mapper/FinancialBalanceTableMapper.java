package com.heli.financial.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.heli.financial.domain.FinancialBalanceTable;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

/**
 * 财务-资产负债Mapper接口
 *
 * @author ruoyi
 * @date 2024-04-03
 */
@DataSource(value = DataSourceType.SLAVE)
public interface FinancialBalanceTableMapper {

    /**
     *
     *
     */

    public BigDecimal selectLastMonthMonthlyInventoryTotalAmount(@Param("yearAndMonth") Date yearAndMonth);



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