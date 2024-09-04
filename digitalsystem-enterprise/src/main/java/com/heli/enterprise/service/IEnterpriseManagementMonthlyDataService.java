package com.heli.enterprise.service;

import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;

/**
 * [企业管理]指标月度数据Service接口
 *
 * @author hong
 * @date 2024-05-09
 */
public interface IEnterpriseManagementMonthlyDataService {
    //更新
    int calculationAllData();
    //统计一线和实习生人数
    int countEmployeesNumber(Date yearAndMonth);
    //计算月度和年度人数
    int calculateEmployeesNumber(Date yearAndMonth);

    boolean checkEMMonthlyDataIsMinMonth(Date yearAndMonth);
    int calculateSalaryFillNumber(Date yearAndMonth);

    public int calculateHandFillIndicators(Date yearAndMonth);

    public void calculateSalaryTableIndicators(Date date);
    public void statisticsSalaryTableIndicators(Date date);

    Boolean checkEMEmployeesDataIsExisted(Date date);


    int insertMonthlyFillingDataByMonth(EnterpriseManagementMonthlyData enterpriseManagementMonthlyData);

    public int calculateMonthlyDataSalary(Date yearAndMonth);

    Boolean checkEMMonthlyDataIsExisted();


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
     * 批量删除[企业管理]指标月度数据
     *
     * @param esIds 需要删除的[企业管理]指标月度数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEsIds(Long[] esIds);

    /**
     * 删除[企业管理]指标月度数据信息
     *
     * @param esId [企业管理]指标月度数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEsId(Long esId);
}
