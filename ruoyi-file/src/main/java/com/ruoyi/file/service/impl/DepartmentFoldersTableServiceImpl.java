package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.DepartmentFoldersTableMapper;
import com.ruoyi.file.domain.DepartmentFoldersTable;
import com.ruoyi.file.service.IDepartmentFoldersTableService;

/**
 * foldersService业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@Service
public class DepartmentFoldersTableServiceImpl implements IDepartmentFoldersTableService 
{
    @Autowired
    private DepartmentFoldersTableMapper departmentFoldersTableMapper;

    /**
     * 查询folders
     * 
     * @param id folders主键
     * @return folders
     */
    @Override
    public DepartmentFoldersTable selectDepartmentFoldersTableById(Long id)
    {
        return departmentFoldersTableMapper.selectDepartmentFoldersTableById(id);
    }

    /**
     * 查询folders列表
     * 
     * @param departmentFoldersTable folders
     * @return folders
     */
    @Override
    public List<DepartmentFoldersTable> selectDepartmentFoldersTableList(DepartmentFoldersTable departmentFoldersTable)
    {
        return departmentFoldersTableMapper.selectDepartmentFoldersTableList(departmentFoldersTable);
    }

    /**
     * 新增folders
     * 
     * @param departmentFoldersTable folders
     * @return 结果
     */
    @Override
    public int insertDepartmentFoldersTable(DepartmentFoldersTable departmentFoldersTable)
    {
        return departmentFoldersTableMapper.insertDepartmentFoldersTable(departmentFoldersTable);
    }

    /**
     * 修改folders
     * 
     * @param departmentFoldersTable folders
     * @return 结果
     */
    @Override
    public int updateDepartmentFoldersTable(DepartmentFoldersTable departmentFoldersTable)
    {
        return departmentFoldersTableMapper.updateDepartmentFoldersTable(departmentFoldersTable);
    }

    /**
     * 批量删除folders
     * 
     * @param ids 需要删除的folders主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFoldersTableByIds(Long[] ids)
    {
        return departmentFoldersTableMapper.deleteDepartmentFoldersTableByIds(ids);
    }

    /**
     * 删除folders信息
     * 
     * @param id folders主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFoldersTableById(Long id)
    {
        return departmentFoldersTableMapper.deleteDepartmentFoldersTableById(id);
    }
}
