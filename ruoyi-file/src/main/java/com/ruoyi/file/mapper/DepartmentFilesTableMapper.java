package com.ruoyi.file.mapper;

import java.util.List;
import com.ruoyi.file.domain.DepartmentFilesTable;
import org.apache.ibatis.annotations.Param;

/**
 * departmentfileMapper接口
 * 
 * @author ruoyi
 * @date 2024-10-23
 */
public interface DepartmentFilesTableMapper 
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

    /**
     * 查询当前文件夹以及子文件夹下的所有文件
     */
    public List<DepartmentFilesTable> getAllfilesByFolderId(@Param("folderId") Long folderId, @Param("fileName") String fileName);

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
     * 删除departmentfile
     * 
     * @param id departmentfile主键
     * @return 结果
     */
    public int deleteDepartmentFilesTableById(Long id);

    /**
     * 批量删除departmentfile
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentFilesTableByIds(Long[] ids);
}
