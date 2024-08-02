package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.DepartmentFileTableMapper;
import com.ruoyi.file.domain.DepartmentFileTable;
import com.ruoyi.file.service.IDepartmentFileTableService;

/**
 * 部门文件Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-31
 */
@Service
public class DepartmentFileTableServiceImpl implements IDepartmentFileTableService 
{
    @Autowired
    private DepartmentFileTableMapper departmentFileTableMapper;

    /**
     * 查询部门文件
     * 
     * @param fileId 部门文件主键
     * @return 部门文件
     */
    @Override
    public DepartmentFileTable selectDepartmentFileTableByFileId(Long fileId)
    {
        return departmentFileTableMapper.selectDepartmentFileTableByFileId(fileId);
    }

    /**
     * 查询部门文件列表
     * 
     * @param departmentFileTable 部门文件
     * @return 部门文件
     */
    @Override
    public List<DepartmentFileTable> selectDepartmentFileTableList(DepartmentFileTable departmentFileTable)
    {
        return departmentFileTableMapper.selectDepartmentFileTableList(departmentFileTable);
    }

    /**
     * 新增部门文件
     * 
     * @param departmentFileTable 部门文件
     * @return 结果
     */
    @Override
    public int insertDepartmentFileTable(DepartmentFileTable departmentFileTable)
    {
        return departmentFileTableMapper.insertDepartmentFileTable(departmentFileTable);
    }

    /**
     * 修改部门文件
     * 
     * @param departmentFileTable 部门文件
     * @return 结果
     */
    @Override
    public int updateDepartmentFileTable(DepartmentFileTable departmentFileTable)
    {
        return departmentFileTableMapper.updateDepartmentFileTable(departmentFileTable);
    }

    /**
     * 批量删除部门文件
     * 
     * @param fileIds 需要删除的部门文件主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFileTableByFileIds(Long[] fileIds)
    {
        return departmentFileTableMapper.deleteDepartmentFileTableByFileIds(fileIds);
    }

    /**
     * 删除部门文件信息
     * 
     * @param fileId 部门文件主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFileTableByFileId(Long fileId)
    {
        return departmentFileTableMapper.deleteDepartmentFileTableByFileId(fileId);
    }
}
