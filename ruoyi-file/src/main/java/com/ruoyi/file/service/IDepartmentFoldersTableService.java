package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.DepartmentFoldersTable;

/**
 * foldersService接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public interface IDepartmentFoldersTableService 
{
    /**
     * 查询folders
     * 
     * @param id folders主键
     * @return folders
     */
    public DepartmentFoldersTable selectDepartmentFoldersTableById(Long id);

    /**
     * 查询folders列表
     * 
     * @param departmentFoldersTable folders
     * @return folders集合
     */
    public List<DepartmentFoldersTable> selectDepartmentFoldersTableList(DepartmentFoldersTable departmentFoldersTable);

    /**
     * 新增folders
     * 
     * @param departmentFoldersTable folders
     * @return 结果
     */
    public int insertDepartmentFoldersTable(DepartmentFoldersTable departmentFoldersTable);

    /**
     * 修改folders
     * 
     * @param departmentFoldersTable folders
     * @return 结果
     */
    public int updateDepartmentFoldersTable(DepartmentFoldersTable departmentFoldersTable);

    /**
     * 批量删除folders
     * 
     * @param ids 需要删除的folders主键集合
     * @return 结果
     */
    public int deleteDepartmentFoldersTableByIds(Long[] ids);

    /**
     * 删除folders信息
     * 
     * @param id folders主键
     * @return 结果
     */
    public int deleteDepartmentFoldersTableById(Long id);
}
