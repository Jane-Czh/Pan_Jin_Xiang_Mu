package com.heli.enterprise.mapper;

import java.util.List;

import com.heli.enterprise.domain.EnterpriseManagementSalaryTable;
import org.apache.ibatis.annotations.Param;

/**
 * 工资-部分字段Mapper接口
 *
 * @author hong
 * @date 2024-05-07
 */
public interface EnterpriseManagementSalaryTableMapper {




    int batchInsertSalaryTable(@Param("salaryTableList") List<EnterpriseManagementSalaryTable> salaryTableList);

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
     * 删除工资-部分字段
     *
     * @param esId 工资-部分字段主键
     * @return 结果
     */
    public int deleteEnterpriseManagementSalaryTableByEsId(Long esId);

    /**
     * 批量删除工资-部分字段
     *
     * @param esIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseManagementSalaryTableByEsIds(Long[] esIds);


}
