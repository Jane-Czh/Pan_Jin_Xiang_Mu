package com.ruoyi.Enterprisemanagement.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import com.ruoyi.market.domain.MarketCommercialVehicleTable;
import com.ruoyi.market.domain.MarketFunctionComparisonDeliverydays;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管特种作业证到期预警Service接口
 * 
 * @author ruoyi
 * @date 2024-04-22
 */
public interface IEnterpriseManagementFunctionSpecialOperationWarningService 
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
     * 批量删除企管特种作业证到期预警
     * 
     * @param emsoIds 需要删除的企管特种作业证到期预警主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoIds(Long[] emsoIds);

    /**
     * 删除企管特种作业证到期预警信息
     * 
     * @param emsoId 企管特种作业证到期预警主键
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionSpecialOperationWarningByEmsoId(Long emsoId);

//    public int SOimportInterests(MultipartFile excelFile) throws IOException;

    public void Synchronization(List<EnterpriseManagementSpecialOperationsManagementLedger> list, EnterpriseManagementFunctionSpecialOperationWarning enterpriseManagementFunctionSpecialOperationWarning1);

    public List<EnterpriseManagementFunctionSpecialOperationWarning> selectEnterpriseManagementFunctionSpecialOperationWarningList1();
}
