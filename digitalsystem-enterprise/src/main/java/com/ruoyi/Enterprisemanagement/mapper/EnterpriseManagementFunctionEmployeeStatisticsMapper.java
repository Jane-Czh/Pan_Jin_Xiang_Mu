package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionEmployeeStatistics;

/**
 * 员工统计Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
public interface EnterpriseManagementFunctionEmployeeStatisticsMapper 
{
    /**
     * 查询员工统计
     * 
     * @param emesId 员工统计主键
     * @return 员工统计
     */
    public EnterpriseManagementFunctionEmployeeStatistics selectEnterpriseManagementFunctionEmployeeStatisticsByEmesId(Long emesId);

    /**
     * 查询员工统计列表
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 员工统计集合
     */
    public List<EnterpriseManagementFunctionEmployeeStatistics> selectEnterpriseManagementFunctionEmployeeStatisticsList(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics);

    /**
     * 新增员工统计
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 结果
     */
    public int insertEnterpriseManagementFunctionEmployeeStatistics(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics);

    /**
     * 修改员工统计
     * 
     * @param enterpriseManagementFunctionEmployeeStatistics 员工统计
     * @return 结果
     */
    public int updateEnterpriseManagementFunctionEmployeeStatistics(EnterpriseManagementFunctionEmployeeStatistics enterpriseManagementFunctionEmployeeStatistics);

    /**
     * 删除员工统计
     * 
     * @param emesId 员工统计主键
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesId(Long emesId);

    /**
     * 批量删除员工统计
     * 
     * @param emesIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionEmployeeStatisticsByEmesIds(Long[] emesIds);
}
