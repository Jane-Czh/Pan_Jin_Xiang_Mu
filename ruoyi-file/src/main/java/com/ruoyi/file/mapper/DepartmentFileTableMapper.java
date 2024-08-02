package com.ruoyi.file.mapper;

import java.util.List;
import com.ruoyi.file.domain.DepartmentFileTable;

/**
 * 部门文件Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
public interface DepartmentFileTableMapper 
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
     * 删除部门文件
     * 
     * @param fileId 部门文件主键
     * @return 结果
     */
    public int deleteDepartmentFileTableByFileId(Long fileId);

    /**
     * 批量删除部门文件
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentFileTableByFileIds(Long[] fileIds);
}
