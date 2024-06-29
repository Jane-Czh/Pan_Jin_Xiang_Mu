package com.ruoyi.Enterprisemanagement.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 特种作业台账导入Service接口
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
public interface IEnterpriseManagementSpecialOperationsManagementLedgerService 
{
    /**
     * 查询特种作业台账导入
     * 
     * @param emsiId 特种作业台账导入主键
     * @return 特种作业台账导入
     */
    public EnterpriseManagementSpecialOperationsManagementLedger selectEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(Long emsiId);

    /**
     * 查询特种作业台账导入列表
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 特种作业台账导入集合
     */
    public List<EnterpriseManagementSpecialOperationsManagementLedger> selectEnterpriseManagementSpecialOperationsManagementLedgerList(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger);

    /**
     * 新增特种作业台账导入
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 结果
     */
    public int insertEnterpriseManagementSpecialOperationsManagementLedger(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger);

    /**
     * 修改特种作业台账导入
     * 
     * @param enterpriseManagementSpecialOperationsManagementLedger 特种作业台账导入
     * @return 结果
     */
    public int updateEnterpriseManagementSpecialOperationsManagementLedger(EnterpriseManagementSpecialOperationsManagementLedger enterpriseManagementSpecialOperationsManagementLedger);

    /**
     * 批量删除特种作业台账导入
     * 
     * @param emsiIds 需要删除的特种作业台账导入主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiIds(Long[] emsiIds);

    /**
     * 删除特种作业台账导入信息
     * 
     * @param emsiId 特种作业台账导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(Long emsiId);
    public int SOimportInterests(MultipartFile excelFile) throws IOException;

    public List<EnterpriseManagementSpecialOperationsManagementLedger> selectEnterpriseManagementSpecialOperationsManagementLedgerList1();
}
