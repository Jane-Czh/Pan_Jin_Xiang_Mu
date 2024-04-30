package com.ruoyi.Enterprisemanagement.service;

import java.io.IOException;
import java.util.List;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 劳动合同台账导入Service接口
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
public interface IEnterpriseManagementLaborContractLedgerService 
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
     * 批量删除劳动合同台账导入
     * 
     * @param emlcIds 需要删除的劳动合同台账导入主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcIds(Long[] emlcIds);

    /**
     * 删除劳动合同台账导入信息
     * 
     * @param emlcId 劳动合同台账导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementLaborContractLedgerByEmlcId(Long emlcId);
    public int LRimportInterests(MultipartFile excelFile) throws IOException;
    public List<EnterpriseManagementLaborContractLedger> selectEnterpriseManagementLaborContractLedgerList1();

}
