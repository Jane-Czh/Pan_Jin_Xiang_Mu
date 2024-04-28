package com.ruoyi.Enterprisemanagement.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 企管劳动合同到期提醒Service接口
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public interface IEnterpriseManagementFunctionLaborReminderService 
{
    /**
     * 查询企管劳动合同到期提醒
     * 
     * @param emlwId 企管劳动合同到期提醒主键
     * @return 企管劳动合同到期提醒
     */
    public EnterpriseManagementFunctionLaborReminder selectEnterpriseManagementFunctionLaborReminderByEmlwId(Long emlwId);

    /**
     * 查询企管劳动合同到期提醒列表
     * 
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 企管劳动合同到期提醒集合
     */
    public List<EnterpriseManagementFunctionLaborReminder> selectEnterpriseManagementFunctionLaborReminderList(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder);

    /**
     * 新增企管劳动合同到期提醒
     * 
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 结果
     */
    public int insertEnterpriseManagementFunctionLaborReminder(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder);

    /**
     * 修改企管劳动合同到期提醒
     * 
     * @param enterpriseManagementFunctionLaborReminder 企管劳动合同到期提醒
     * @return 结果
     */
    public int updateEnterpriseManagementFunctionLaborReminder(EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder);

    /**
     * 批量删除企管劳动合同到期提醒
     * 
     * @param emlwIds 需要删除的企管劳动合同到期提醒主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(Long[] emlwIds);

    /**
     * 删除企管劳动合同到期提醒信息
     * 
     * @param emlwId 企管劳动合同到期提醒主键
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwId(Long emlwId);

//    public int LRimportInterests(MultipartFile excelFile) throws IOException;

   public List<EnterpriseManagementFunctionLaborReminder> selectEnterpriseManagementFunctionLaborReminderList1();


   public void Synchronization(List<EnterpriseManagementLaborContractLedger> list, EnterpriseManagementFunctionLaborReminder enterpriseManagementFunctionLaborReminder1);
}
