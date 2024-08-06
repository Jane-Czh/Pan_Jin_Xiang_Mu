package com.ruoyi.Enterprisemanagement.mapper;

import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;

/**
 * 员工花名单导入Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
public interface EnterpriseManagementPersonnelRosterMapper 
{
    /**
     * 查询员工花名单导入
     * 
     * @param emprId 员工花名单导入主键
     * @return 员工花名单导入
     */
    public EnterpriseManagementPersonnelRoster selectEnterpriseManagementPersonnelRosterByEmprId(Long emprId);

    /**
     * 查询员工花名单导入列表
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 员工花名单导入集合
     */
    public List<EnterpriseManagementPersonnelRoster> selectEnterpriseManagementPersonnelRosterList(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster);

    /**
     * 新增员工花名单导入
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 结果
     */
    public int insertEnterpriseManagementPersonnelRoster(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster);

    /**
     * 修改员工花名单导入
     * 
     * @param enterpriseManagementPersonnelRoster 员工花名单导入
     * @return 结果
     */
    public int updateEnterpriseManagementPersonnelRoster(EnterpriseManagementPersonnelRoster enterpriseManagementPersonnelRoster);

    /**
     * 删除员工花名单导入
     * 
     * @param emprId 员工花名单导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementPersonnelRosterByEmprId(Long emprId);

    /**
     * 批量删除员工花名单导入
     * 
     * @param emprIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementPersonnelRosterByEmprIds(Long[] emprIds);

    //查询数据库最后一条数据id
    public Long selectLastId();

    void insertBatch(List<EnterpriseManagementPersonnelRoster> rosterList);

    //无参数方法
    public List<EnterpriseManagementPersonnelRoster> selectEnterpriseManagementPersonnelRosterList1();
}
