package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;

/**
 * 劳动合同台账导入Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
public interface EnterpriseManagementLaborContractLedgerMapper 
{
    /**
     * 查询劳动合同台账导入
     * 
     * @param emlcId 劳动合同台账导入主键
     * @return 劳动合同台账导入
     */
    public EnterpriseManagementLaborContractLedger selectEnterpriseManagementLaborContractLedgerByEmlcId(Long emlcId);

    /**
     * 查询劳动合同台账导入列表
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 劳动合同台账导入集合
     */
    public List<EnterpriseManagementLaborContractLedger> selectEnterpriseManagementLaborContractLedgerList(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger);

    /**
     * 新增劳动合同台账导入
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 结果
     */
    public int insertEnterpriseManagementLaborContractLedger(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger);

    /**
     * 修改劳动合同台账导入
     * 
     * @param enterpriseManagementLaborContractLedger 劳动合同台账导入
     * @return 结果
     */
    public int updateEnterpriseManagementLaborContractLedger(EnterpriseManagementLaborContractLedger enterpriseManagementLaborContractLedger);

    /**
     * 删除劳动合同台账导入
     * 
     * @param emlcId 劳动合同台账导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcId(Long emlcId);

    /**
     * 批量删除劳动合同台账导入
     * 
     * @param emlcIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcIds(Long[] emlcIds);
    //查询数据库最后一条数据id
    public Long selectLastId();

    //无参数方法
    public List<EnterpriseManagementLaborContractLedger> selectEnterpriseManagementLaborContractLedgerList1();
}
