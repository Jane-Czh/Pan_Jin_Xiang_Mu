package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementFunctionSpecialOperationWarning;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementSpecialOperationsManagementLedger;

/**
 * 特种作业台账导入Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-24
 */
public interface EnterpriseManagementSpecialOperationsManagementLedgerMapper 
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
     * 删除特种作业台账导入
     * 
     * @param emsiId 特种作业台账导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiId(Long emsiId);

    /**
     * 批量删除特种作业台账导入
     * 
     * @param emsiIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementSpecialOperationsManagementLedgerByEmsiIds(Long[] emsiIds);
    //查询数据库最后一条数据id
    public Long selectLastId();

    //无参数方法
    public List<EnterpriseManagementSpecialOperationsManagementLedger> selectEnterpriseManagementSpecialOperationsManagementLedgerList1();

    void insertBatch(List<EnterpriseManagementSpecialOperationsManagementLedger> ledgerList);
}
