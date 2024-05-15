package com.heli.enterprise.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsSalaryData;
import org.apache.ibatis.annotations.Param;

/**
 * [企业管理]指标工资数据Mapper接口
 *
 * @author hong
 * @date 2024-05-08
 */
public interface EnterpriseManagementIndicatorsSalaryDataMapper {


    Boolean checkEMSalaryDataIsExisted(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlySalary(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectAnnualSalary(@Param("year") Integer year);

    BigDecimal selectSalaryTotalNumber(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyAverageIncome(@Param("yearAndMonth") Date yearAndMonth);
    BigDecimal selectAnnualAllIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyProductionAverageIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyFunctionalAverageIncome(@Param("yearAndMonth") Date yearAndMonth);

    BigDecimal selectMonthlyFunctionalAverageOvertimeCost(@Param("yearAndMonth") Date yearAndMonth);

    public int updateSalaryTableByMonth(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData);


    /**
     * 查询[企业管理]指标工资数据
     *
     * @param esId [企业管理]指标工资数据主键
     * @return [企业管理]指标工资数据
     */
    public EnterpriseManagementIndicatorsSalaryData selectEnterpriseManagementIndicatorsSalaryDataByEsId(Long esId);

    /**
     * 查询[企业管理]指标工资数据列表
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return [企业管理]指标工资数据集合
     */
    public List<EnterpriseManagementIndicatorsSalaryData> selectEnterpriseManagementIndicatorsSalaryDataList(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData);

    /**
     * 新增[企业管理]指标工资数据
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return 结果
     */
    public int insertEnterpriseManagementIndicatorsSalaryData(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData);
    /**
     * 修改[企业管理]指标工资数据
     *
     * @param enterpriseManagementIndicatorsSalaryData [企业管理]指标工资数据
     * @return 结果
     */
    public int updateEnterpriseManagementIndicatorsSalaryData(EnterpriseManagementIndicatorsSalaryData enterpriseManagementIndicatorsSalaryData);

    /**
     * 删除[企业管理]指标工资数据
     *
     * @param esId [企业管理]指标工资数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsId(Long esId);

    /**
     * 批量删除[企业管理]指标工资数据
     *
     * @param esIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsIds(Long[] esIds);
}
