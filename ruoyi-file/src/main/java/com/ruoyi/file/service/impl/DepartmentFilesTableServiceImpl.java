package com.ruoyi.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.file.mapper.DepartmentFilesTableMapper;
import com.ruoyi.file.domain.DepartmentFilesTable;
import com.ruoyi.file.service.IDepartmentFilesTableService;

/**
 * departmentfileService业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
@Service
public class DepartmentFilesTableServiceImpl implements IDepartmentFilesTableService 
{
    @Autowired
    private DepartmentFilesTableMapper departmentFilesTableMapper;

    /**
     * 查询departmentfile
     * 
     * @param id departmentfile主键
     * @return departmentfile
     */
    @Override
    public DepartmentFilesTable selectDepartmentFilesTableById(Long id)
    {
        return departmentFilesTableMapper.selectDepartmentFilesTableById(id);
    }

    /**
     * 查询departmentfile列表
     * 
     * @param departmentFilesTable departmentfile
     * @return departmentfile
     */
    @Override
    public List<DepartmentFilesTable> selectDepartmentFilesTableList(DepartmentFilesTable departmentFilesTable)
    {
        return departmentFilesTableMapper.selectDepartmentFilesTableList(departmentFilesTable);
    }

    //查询当前文件夹以及子文件夹下的所有文件
    @Override
    public List<DepartmentFilesTable> getAllfilesByFolderId(Long folderId,String fileName)
    {
        return departmentFilesTableMapper.getAllfilesByFolderId(folderId,fileName);
    }

    /**
     * 新增departmentfile
     * 
     * @param departmentFilesTable departmentfile
     * @return 结果
     */
    @Override
    public int insertDepartmentFilesTable(DepartmentFilesTable departmentFilesTable)
    {
        return departmentFilesTableMapper.insertDepartmentFilesTable(departmentFilesTable);
    }

    /**
     * 修改departmentfile
     * 
     * @param departmentFilesTable departmentfile
     * @return 结果
     */
    @Override
    public int updateDepartmentFilesTable(DepartmentFilesTable departmentFilesTable)
    {
        return departmentFilesTableMapper.updateDepartmentFilesTable(departmentFilesTable);
    }

    /**
     * 批量删除departmentfile
     * 
     * @param ids 需要删除的departmentfile主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFilesTableByIds(Long[] ids)
    {
        return departmentFilesTableMapper.deleteDepartmentFilesTableByIds(ids);
    }

    /**
     * 删除departmentfile信息
     * 
     * @param id departmentfile主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentFilesTableById(Long id)
    {
        return departmentFilesTableMapper.deleteDepartmentFilesTableById(id);
    }
}
