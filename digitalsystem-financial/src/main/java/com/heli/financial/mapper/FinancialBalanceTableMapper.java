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
//@DataSource(value = DataSourceType.SLAVE)
public interface FinancialBalanceTableMapper {


    List<Date> selectAllBalanceYearAndMonth();


    /**
     * @description: 检查当月资产负债表数据是否存在
     * @author: hong
     * @date: 2024/4/15 14:59
     **/
    boolean checkBalanceDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    /**
     * @description: 按年月查询资产负债表数据
     * @author: hong
     * @date: 2024/4/23 11:01
     **/
    FinancialBalanceTable selectFinancialBalanceTableByYearAndMonth(@Param("yearAndMonth") Date yearAndMonth);


    /**
     * @description: 按月查询-月度存货总金额
     * @author: hong
     * @date: 2024/4/23 11:24
     **/
    BigDecimal selectMonthlyInventoryTotalAmountByYearAndMonth(@Param("yearAndMonth") Date yearAndMonth);



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
