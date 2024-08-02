package com.heli.financial.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.financial.domain.FinancialInterestsTable;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Param;

/**
 * 财务-利润Mapper接口
 *
 * @author hong
 * @date 2024-03-31
 */
//@DataSource(value = DataSourceType.SLAVE)
public interface FinancialInterestsTableMapper {


    /**
     * @description: 检查当月利润表数据是否存在
     * @author: hong
     * @date: 2024/4/15 14:59
     **/
    boolean checkInterestsDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    boolean checkDataExists();

    Date selectMaxYearAndMonth();

    FinancialInterestsTable selectFinancialInterestsTableByYearAndMonth(@Param("yearAndMonth") Date yearAndMonth);
    FinancialInterestsTable selectInterestsSumInfoByYear(@Param("yearAndMonth") Date yearAndMonth);
    FinancialInterestsTable selectMaxMonthInterests();

    /**
     *
     */

//    public BigDecimal selectOperatingRevenue(Date yearAndMonth);
    public BigDecimal selectOperatingRevenueByMonth(@Param("yearAndMonth") Date yearAndMonth);

//    按年查询利润表的全部数据
    List<FinancialInterestsTable> selectFinancialInterestsTableByYear(@Param("year") String year);

//    按时间段查询利润表数据
    List<FinancialInterestsTable> selectFinancialInterestsTableByTime(@Param("beginTime") Date beginTime,
                                                                      @Param("endTime") Date endTime);

    List<Date> selectAllInterestsYearAndMonth();


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
     * 删除财务-利润
     *
     * @param fiId 财务-利润主键
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiId(Long fiId);

    /**
     * 批量删除财务-利润
     *
     * @param fiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFinancialInterestsTableByFiIds(Long[] fiIds);


}
