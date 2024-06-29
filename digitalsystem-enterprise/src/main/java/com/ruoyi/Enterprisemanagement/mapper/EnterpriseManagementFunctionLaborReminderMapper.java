package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionLaborReminder;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;

/**
 * 企管劳动合同到期提醒Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-23
 */
public interface EnterpriseManagementFunctionLaborReminderMapper 
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
     * 删除企管劳动合同到期提醒
     * 
     * @param emlwId 企管劳动合同到期提醒主键
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwId(Long emlwId);

    /**
     * 批量删除企管劳动合同到期提醒
     * 
     * @param emlwIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementFunctionLaborReminderByEmlwIds(Long[] emlwIds);

    public Long selectLastId();

    //无参数方法
    public List<EnterpriseManagementFunctionLaborReminder> selectEnterpriseManagementFunctionLaborReminderList1();


}
