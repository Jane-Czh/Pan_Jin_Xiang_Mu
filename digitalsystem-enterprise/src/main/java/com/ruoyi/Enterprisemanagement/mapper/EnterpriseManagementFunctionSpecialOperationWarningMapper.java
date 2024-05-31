package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;

/**
 * 企管特种作业证到期预警Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface EnterpriseManagementFunctionSpecialOperationWarningMapper 
{
    /**
     * 查询企管特种作业证到期预警
     * 
     * @param emsoId 企管特种作业证到期预警主键
     * @return 企管特种作业证到期预警
     */
    public EnterpriseManagementFunctionSpecialOperationWarning selectEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(Long emsoId);

    /**
     * 查询企管特种作业证到期预警列表
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 企管特种作业证到期预警集合
     */
    public List<EnterpriseManagementFunctionSpecialOperationWarning> selectEnterpriseManagementFunctionSpecialOperationWarningList(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning);

    /**
     * 新增企管特种作业证到期预警
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 结果
     */
    public int insertEnterpriseManagementFunctionSpecialOperationWarning(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning);

    /**
     * 修改企管特种作业证到期预警
     * 
     * @param enterpriseManagementFunctionSpecialOperationWarning 企管特种作业证到期预警
     * @return 结果
     */
    public int updateEnterpriseManagementFunctionSpecialOperationWarning(EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning);

    /**
     * 删除企管特种作业证到期预警
     * 
     * @param emsoId 企管特种作业证到期预警主键
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(Long emsoId);

    /**
     * 批量删除企管特种作业证到期预警
     * 
     * @param emsoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(Long[] emsoIds);

    //查询数据库最后一条数据id
    public Long selectLastId();

    //无参数方法
    public List<EnterpriseManagementFunctionSpecialOperationWarning> selectEnterpriseManagementFunctionSpecialOperationWarningList1();

}
