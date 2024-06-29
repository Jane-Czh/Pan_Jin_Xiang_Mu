package com.ruoyi.Enterprisemanagement.service;

import java.io.IOException;
import java.util.List;

import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementLaborContractLedger;
import com.ruoyi.Enterprisemanagement.domain.EnterpriseManagementPersonnelRoster;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工花名单导入Service接口
 * 
 * @author ruoyi
 * @date 2024-05-06
 */
public interface IEnterpriseManagementPersonnelRosterService 
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
     * 批量删除员工花名单导入
     * 
     * @param emprIds 需要删除的员工花名单导入主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementPersonnelRosterByEmprIds(Long[] emprIds);

    /**
     * 删除员工花名单导入信息
     * 
     * @param emprId 员工花名单导入主键
     * @return 结果
     */
    public int deleteEnterpriseManagementPersonnelRosterByEmprId(Long emprId);

    public int PRimportInterests(MultipartFile excelFile) throws IOException;
    public List<EnterpriseManagementPersonnelRoster> selectEnterpriseManagementPersonnelRosterList1();
}
