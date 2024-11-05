package com.ruoyi.file.service;

import java.util.List;
import com.ruoyi.file.domain.DepartmentFilesTable;

/**
 * departmentfileService接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public interface IDepartmentFilesTableService 
{
    /**
     * 查询departmentfile
     * 
     * @param id departmentfile主键
     * @return departmentfile
     */
    public DepartmentFilesTable selectDepartmentFilesTableById(Long id);

    /**
     * 查询departmentfile列表
     * 
     * @param departmentFilesTable departmentfile
     * @return departmentfile集合
     */
    public List<DepartmentFilesTable> selectDepartmentFilesTableList(DepartmentFilesTable departmentFilesTable);

    public List<DepartmentFilesTable> getAllfilesByFolderId(Long folderId,String fileName);

    /**
     * 新增departmentfile
     * 
     * @param departmentFilesTable departmentfile
     * @return 结果
     */
    public int insertDepartmentFilesTable(DepartmentFilesTable departmentFilesTable);

    /**
     * 修改departmentfile
     * 
     * @param departmentFilesTable departmentfile
     * @return 结果
     */
    public int updateDepartmentFilesTable(DepartmentFilesTable departmentFilesTable);

    /**
     * 批量删除departmentfile
     * 
     * @param ids 需要删除的departmentfile主键集合
     * @return 结果
     */
    public int deleteDepartmentFilesTableByIds(Long[] ids);

    /**
     * 删除departmentfile信息
     * 
     * @param id departmentfile主键
     * @return 结果
     */
    public int deleteDepartmentFilesTableById(Long id);
}
