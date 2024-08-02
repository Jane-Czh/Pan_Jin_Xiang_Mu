package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.DepartmentFileTable;

/**
 * 部门文件Service接口
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
public interface IDepartmentFileTableService 
{
    /**
     * 查询部门文件
     * 
     * @param fileId 部门文件主键
     * @return 部门文件
     */
    public DepartmentFileTable selectDepartmentFileTableByFileId(Long fileId);

    /**
     * 查询部门文件列表
     * 
     * @param departmentFileTable 部门文件
     * @return 部门文件集合
     */
    public List<DepartmentFileTable> selectDepartmentFileTableList(DepartmentFileTable departmentFileTable);

    /**
     * 新增部门文件
     * 
     * @param departmentFileTable 部门文件
     * @return 结果
     */
    public int insertDepartmentFileTable(DepartmentFileTable departmentFileTable);

    /**
     * 修改部门文件
     * 
     * @param departmentFileTable 部门文件
     * @return 结果
     */
    public int updateDepartmentFileTable(DepartmentFileTable departmentFileTable);

    /**
     * 批量删除部门文件
     * 
     * @param fileIds 需要删除的部门文件主键集合
     * @return 结果
     */
    public int deleteDepartmentFileTableByFileIds(Long[] fileIds);

    /**
     * 删除部门文件信息
     * 
     * @param fileId 部门文件主键
     * @return 结果
     */
    public int deleteDepartmentFileTableByFileId(Long fileId);
}
