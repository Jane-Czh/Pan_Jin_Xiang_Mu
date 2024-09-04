package com.heli.enterprise.service;

import java.io.InputStream;
import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import com.ruoyi.common.core.domain.R;

/**
 * 工资-部分字段Service接口
 *
 * @author hong
 * @date 2024-05-07
 */
public interface IEnterpriseManagementSalaryTableService {

    void clearSalaryTableAllInfo();

    public R<String> readSalaryExcelToDB(String fileName, InputStream inputStream, String username);

    /**
     * 查询工资-部分字段
     *
     * @param esId 工资-部分字段主键
     * @return 工资-部分字段
     */
    public EnterpriseManagementSalaryTable selectEnterpriseManagementSalaryTableByEsId(Long esId);

    /**
     * 查询工资-部分字段列表
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 工资-部分字段集合
     */
    public List<EnterpriseManagementSalaryTable> selectEnterpriseManagementSalaryTableList(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable);

    /**
     * 新增工资-部分字段
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 结果
     */
    public int insertEnterpriseManagementSalaryTable(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable);

    /**
     * 修改工资-部分字段
     *
     * @param enterpriseManagementSalaryTable 工资-部分字段
     * @return 结果
     */
    public int updateEnterpriseManagementSalaryTable(EnterpriseManagementSalaryTable enterpriseManagementSalaryTable);

    /**
     * 批量删除工资-部分字段
     *
     * @param esIds 需要删除的工资-部分字段主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementSalaryTableByEsIds(Long[] esIds);

    /**
     * 删除工资-部分字段信息
     *
     * @param esId 工资-部分字段主键
     * @return 结果
     */
    public int deleteEnterpriseManagementSalaryTableByEsId(Long esId);
}
