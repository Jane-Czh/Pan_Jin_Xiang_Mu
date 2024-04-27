package com.heli.enterprise.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementMonthlyData;

/**
 * [企业管理]指标月度数据Service接口
 *
 * @author hong
 * @date 2024-04-27
 */
public interface IEnterpriseManagementMonthlyDataService {


    Boolean checkEMMonthlyDataIsExisted(Date date);








    /**
     * 查询[企业管理]指标月度数据
     *
     * @param emId [企业管理]指标月度数据主键
     * @return [企业管理]指标月度数据
     */
    public EnterpriseManagementMonthlyData selectEnterpriseManagementMonthlyDataByEmId(Long emId);

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
     * @param emIds 需要删除的[企业管理]指标月度数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEmIds(Long[] emIds);

    /**
     * 删除[企业管理]指标月度数据信息
     *
     * @param emId [企业管理]指标月度数据主键
     * @return 结果
     */
    public int deleteEnterpriseManagementMonthlyDataByEmId(Long emId);
}
