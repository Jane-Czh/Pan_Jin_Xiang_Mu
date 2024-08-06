package com.heli.enterprise.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;
import org.apache.ibatis.annotations.Param;

/**
 * [企业管理]指标月度数据Mapper接口
 *
 * @author hong
 * @date 2024-05-09
 */
public interface EnterpriseManagementMonthlyDataMapper {
    EnterpriseManagementMonthlyData selectMonthDateByDate(@Param("yearAndMonth") Date yearAndMonth);

    int selectFrontLineEmployeeNumbers();
    int selectInternNumbers();

    //统计当年数据条数
    int countMonthDataNumber(@Param("yearAndMonth") Date yearAndMonth);
    int countEmployeesNumberByYear(@Param("yearAndMonth") Date yearAndMonth);
    int updateMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    int insertOrUpdateEmployeesData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    int countMonthSalaryFillDataNumber(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal countSalaryFillNumberByYear(@Param("yearAndMonth") Date yearAndMonth);

    //新-工资表统计
    BigDecimal selectMonthlyCumulativeAverageIncome();
    BigDecimal selectMonthlyProductionAverageIncome();
    BigDecimal selectMonthlyFunctionalAverageIncome();
    BigDecimal selectMonthlyFunctionalAverageOvertimeCost();
    BigDecimal selectMonthlyFunctionalAverageOtherBonuses();
    int countMonthDataSalaryNumber(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal selectCumulativeAverageIncome(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal selectProductionAVGIncome(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal selectFunctionalAVGIncome(@Param("yearAndMonth") Date yearAndMonth);



    int selectCountEmployeesNumberByYear(@Param("yearAndMonth") Date yearAndMonth);
    int selectCountMonthsByYear(@Param("yearAndMonth") Date yearAndMonth);


    Boolean checkEMEmployeesDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);
    Boolean checkEMMonthlyDataIsMinMonth(@Param("yearAndMonth") Date yearAndMonth);
    Long selectEmployeesNumberByMonth(@Param("yearAndMonth") Date yearAndMonth);

    Integer selectEmployeesAVGMonthlyNumber(@Param("yearAndMonth") Date yearAndMonth);

    Date selectMaxMonths(@Param("year") Date year);

    Date selectMaxMonthByYear(@Param("yearAndMonth") Date yearAndMonth);
    Date selectMinMonthByYear(@Param("yearAndMonth") Date yearAndMonth);

    int insertMonthlyFillingDataByMonth(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);
    int updateCalculateMonthlyDataByMonth(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    Boolean checkEMMonthlyDataIsExisted();


    Boolean checkEMSalaryDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlySalary(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectAnnualSalary(@Param("year") Integer year);

    BigDecimal selectSalaryTotalNumber(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyAverageIncome(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal selectAnnualAllIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyProductionAverageIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyFunctionalAverageIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyFunctionalAverageOvertimeCost(@Param("yearAndMonth") Date yearAndMonth);

    public int updateCalculateSalaryDataByMonth(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);







    /**
     * 查询[企业管理]指标月度数据
     *
     * @param esId [企业管理]指标月度数据主键
     * @return [企业管理]指标月度数据
     */
    public EnterpriseManagementMonthlyData selectEnterpriseManagementMonthlyDataByEsId(Long esId);

    /**
     * 查询[企业管理]指标月度数据列表
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return [企业管理]指标月度数据集合
     */
    public List<EnterpriseManagementMonthlyData> selectEnterpriseManagementMonthlyDataList(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    /**
     * 新增[企业管理]指标月度数据
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return 结果
     */
    public int insertEnterpriseManagementMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    /**
     * 修改[企业管理]指标月度数据
     *
     * @param enterpriseManagementMonthlyData [企业管理]指标月度数据
     * @return 结果
     */
    public int updateEnterpriseManagementMonthlyData(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    /**
     * 删除[企业管理]指标月度数据
     *
     * @param esId [企业管理]指标月度数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEsId(Long esId);

    /**
     * 批量删除[企业管理]指标月度数据
     *
     * @param esIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEsIds(Long[] esIds);

    BigDecimal selectMonthlyFunctionalAverageOtherBonuses(Date date);


}
