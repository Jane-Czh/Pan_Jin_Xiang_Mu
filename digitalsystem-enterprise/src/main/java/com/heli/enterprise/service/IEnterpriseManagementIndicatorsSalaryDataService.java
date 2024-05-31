package com.heli.enterprise.service;

import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementIndicatorsSalaryData;

/**
 * [企业管理]指标工资数据Service接口
 *
 * @author hong
 * @date 2024-05-08
 */
public interface IEnterpriseManagementIndicatorsSalaryDataService {


    public void salaryCalculator(Date date);

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
     * 批量删除[企业管理]指标工资数据
     *
     * @param esIds 需要删除的[企业管理]指标工资数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsIds(Long[] esIds);

    /**
     * 删除[企业管理]指标工资数据信息
     *
     * @param esId [企业管理]指标工资数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementIndicatorsSalaryDataByEsId(Long esId);
}
